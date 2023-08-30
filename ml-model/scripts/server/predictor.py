import os.path

import torch
import numpy as np
import pandas as pd
import Levenshtein
import re
from torch.utils.data import DataLoader, TensorDataset
from transformers import PreTrainedModel, PreTrainedTokenizer
from typing import Type

from src.types.ClassificationType import ClassificationType
from src.types.TransformerType import TransformerType
from src.types.TrattoModelType import TrattoModelType
from src.utils import utils

# Map token class names
_, value_mappings = utils.import_json(
    os.path.join(
        os.path.dirname(os.path.abspath(__file__)),
        '..',
        '..',
        'src',
        'resources',
        'tokenClassesValuesMapping.json'
    )
)

# Classificator converter
_, classificator_converter_in_category = utils.import_json(
    os.path.join(
        os.path.dirname(os.path.abspath(__file__)),
        '..',
        '..',
        'src',
        'resources',
        'classificator_converter_in_category_token_classes.json'
    )
)
_, classificator_converter_in_label = utils.import_json(
    os.path.join(
        os.path.dirname(os.path.abspath(__file__)),
        '..',
        '..',
        'src',
        'resources',
        'classificator_converter_in_label.json'
    )
)
classificator_converter_out_category = {k: i for i, k in classificator_converter_in_category.items()}
classificator_converter_out_label = {k: i for i, k in classificator_converter_in_label.items()}

def predict_next(
        device,
        model,
        dl_src,
        tokenizer,
        eligible_tokens,
        classification_type,
        transformer_type,
        tratto_model_type
):
    # Model in evaluation mode
    model.eval()
    num_beams = 1
    predictions = []
    # The prediction is performed without accumulating the gradient descent and without updating the weights of the model
    with torch.no_grad():
        for batch_id, batch in enumerate(dl_src, 1):
            # Extract the inputs, the attention masks and the targets from the batch
            src_input = batch[0].to(device)
            src_masks = batch[1].to(device)
            tgt = batch[2].to(device)

            if transformer_type == TransformerType.DECODER:
                # Model predictions with beam-search
                outputs_generate = model.generate(
                    input_ids=src_input,
                    attention_mask=src_masks,
                    num_beams=num_beams,
                    output_scores=True,
                    return_dict_in_generate=True
                )
                predicted_generate = np.array(
                    tokenizer.batch_decode(
                        outputs_generate['sequences'],
                        skip_special_tokens=True
                    )
                )

                if classification_type == ClassificationType.CATEGORY_PREDICTION:
                    # First-choice beam search
                    predictions.append((predicted_generate[0], 1.0))
                else:
                    assert len(outputs_generate['scores']) == 3
                    ids_max_logits_predicted = [
                        torch.argmax(outputs_generate['scores'][1][i], dim=-1).item()
                        for i in range(len(outputs_generate['scores'][0]))
                    ]
                    max_logits_predicted = [
                        outputs_generate['scores'][1][i][k].item()
                        for i, k in enumerate(ids_max_logits_predicted)
                    ]
                    for i, p in enumerate(predicted_generate):
                        if p == 'True':
                            tgt_decoded = tokenizer.decode(tgt[i],skip_special_tokens=True)
                            predictions.append((tgt_decoded, max_logits_predicted[i]))
            else:
                # Model predictions with ranking
                outputs_generate = model(
                    input_ids=src_input,
                    attention_mask=src_masks
                )
                tgt_decoded = list(map(lambda t: t.replace(" ",""), np.array(
                    tokenizer.batch_decode(
                        tgt,
                        skip_special_tokens=True
                    )
                )))
                logits = outputs_generate.logits
                probabilities = torch.softmax(logits, dim=1)
                predicted_generate_encoded = torch.argmax(probabilities, dim=1)

                for i, p in enumerate(predicted_generate_encoded):
                    if classification_type == ClassificationType.CATEGORY_PREDICTION:
                        predicted_generate = classificator_converter_out_category[p.item()]
                        predictions.append((
                            probabilities[i][p.item()].item(),
                            predicted_generate
                        ))
                    elif classification_type == ClassificationType.LABEL_PREDICTION:
                        if classificator_converter_out_label[predicted_generate_encoded[p.item()]] == 'True':
                            predicted_generate = tgt_decoded[i]
                            predictions.append((
                                probabilities[i][p.item()].item(),
                                predicted_generate
                            ))

        if len(predictions) > 0:
            sorted_predictions = sorted(predictions, key=lambda p: p[0])
            first_choice = sorted_predictions[0][0]

            if classification_type == ClassificationType.CATEGORY_PREDICTION:
                # Heuristics to mitigate knowns prediction errors
                if tratto_model_type == TrattoModelType.TOKEN_CLASSES:
                    if first_choice not in list(value_mappings.values()):
                        subwordSplit = first_choice.split("_")
                        # Iterate over the list of possible token classes
                        for eligible in value_mappings.values():
                            # Check if last subword of predicted token matches the end of the current eligible token class
                            if eligible.endswith(subwordSplit[-1]):
                                return eligible
                        # Iterate over the list of the other alternatives in the beam-search model
                        for alternative_choice in predicted_generate[1:]:
                            for eligible in value_mappings.values():
                                # Check if current alternative choice matches eligible token class
                                if eligible == alternative_choice:
                                    return eligible
                        # Analyze subwords of the last camel-cased subword
                        camelCaseSplit = re.split(r"(?=[A-Z])", subwordSplit[-1])
                        # Iterate over the list of possible token classes
                        for eligible in value_mappings.values():
                            # Check if last subword of predicted token matches the end of the current eligible token class
                            if eligible.endswith(camelCaseSplit[-1]):
                                return eligible
                    else:
                        return first_choice
                else:
                    return first_choice
            elif classification_type == ClassificationType.LABEL_PREDICTION:
                if first_choice in value_mappings.values():
                    return first_choice
            # If no token has been found, compute the Levenshtein distance among the eligible token classes
            best_distance = float('inf')
            most_probable_token = None
            for eligible in eligible_tokens:
                distance = Levenshtein.distance(first_choice, eligible)
                if distance < best_distance and eligible in eligible_tokens:
                    best_distance = distance
                    most_probable_token = eligible
            return most_probable_token
        return ""



def update_tokenizer_vocab(
        tokenizer,
        value_mappings
):
    # Get tokenizer vocabulary
    vocab = tokenizer.get_vocab()
    # Add new tokens to vocabulary
    for new_word in value_mappings.values():
        for new_sub_word in new_word.split("_"):
            if not new_sub_word in vocab.keys():
                tokenizer.add_tokens([new_sub_word])


def pre_process_dataset(
        df_dataset,
        tokenizer
):
    # Drop column id (it is not relevant for training the model)
    #df_dataset = df_dataset.drop(['id'], axis=1)
    # Map empty cells to empty strings
    df_dataset.fillna('', inplace=True)
    # Assign type to each column
    df_dataset = df_dataset.astype({
        'oracleId': 'int64',
        'oracleType': 'string',
        'projectName': 'string',
        'packageName': 'string',
        'className': 'string',
        'javadocTag': 'string',
        'methodJavadoc': 'string',
        'methodSourceCode': 'string',
        'classJavadoc': 'string',
        'classSourceCode': 'string',
        'oracleSoFar': 'string',
        'token': 'string',
        'tokenClass': 'string',
        'tokenInfo': 'string'
    })

    update_tokenizer_vocab(tokenizer, value_mappings)

    # Remove method source code
    df_dataset['methodSourceCode'] = df_dataset['methodSourceCode'].str.split('{').str[0]
    # Replace the values in the DataFrame column
    df_dataset['tokenClass'] = df_dataset['tokenClass'].replace(value_mappings)
    # Map token classes so far to new values and transform it from array to string
    df_dataset['tokenClassesSoFar'] = df_dataset['tokenClassesSoFar'].apply(lambda x: "[ " + " ".join([value_mappings[y] for y in x]) + " ]")
    # Delete spurious columns for predicting the next token class
    df_dataset = df_dataset.drop(['projectName', 'classJavadoc', 'classSourceCode'], axis=1)
    # Return pre-processed dataset
    return df_dataset

def get_input_model_classes(
        df_dataset,
        tokenizer,
        classification_type,
        transformer_type
):
    # Compute eligible token classes
    df_eligibleTokenClasses = df_dataset.groupby(['oracleId', 'oracleSoFar'])['tokenClass'].unique().to_frame()
    df_eligibleTokenClasses = df_eligibleTokenClasses.rename(columns={'tokenClass': 'eligibleTokenClasses'})
    df_dataset = pd.merge(df_dataset, df_eligibleTokenClasses, on=['oracleId', 'oracleSoFar']).reset_index()
    df_dataset['eligibleTokenClasses'] = df_dataset["eligibleTokenClasses"].apply(lambda x: "[ " + " ".join(x) + " ]")
    # Set type of dataframe columns
    df_dataset['tokenClass'] = df_dataset['tokenClass'].astype('string')
    df_dataset['tokenClassesSoFar'] = df_dataset['tokenClassesSoFar'].astype('string')
    df_dataset['eligibleTokenClasses'] = df_dataset['eligibleTokenClasses'].astype('string')
    # Define the new order of columns
    new_columns_order = [
        'oracleId', 'token', 'tokenInfo', 'tokenClass', 'oracleSoFar', 'tokenClassesSoFar', 'eligibleTokenClasses',
        'javadocTag', 'oracleType', 'packageName', 'className', 'methodSourceCode', 'methodJavadoc'
    ]
    # Reindex the DataFrame with the new order
    df_dataset = df_dataset.reindex(columns=new_columns_order)
    # Delete spurious columns for predicting the next token class
    df_dataset = df_dataset.drop(['oracleId', 'token', 'tokenInfo'], axis=1)
    if classification_type == ClassificationType.CATEGORY_PREDICTION:
        # Remove category to predict
        df_dataset = df_dataset.drop(['tokenClass'], axis=1)
    if transformer_type == TransformerType.DECODER:
        # Delete duplicates
        df_dataset = df_dataset.drop_duplicates()
        if classification_type == ClassificationType.CATEGORY_PREDICTION:
            assert len(df_dataset) == 1
    if classification_type == ClassificationType.CATEGORY_PREDICTION:
        tgt = []
    else:
        tgt = df_dataset["tokenClass"].values.tolist()
    # Generate string datapoints concatenating the fields of each column and separating them with a special token
    df_src_concat = df_dataset.apply(lambda row: tokenizer.sep_token.join(row.values), axis=1)
    # The pandas dataframe is transformed in a list of strings: each string is an input to the model
    src = df_src_concat.to_numpy().tolist()

    # Extract eligible token classes as list
    eligible_token_classes = df_dataset['eligibleTokenClasses'][0].strip("[]").split()
    # Return source input and token classes dictionary
    return src, tgt, eligible_token_classes

def get_input_model_values(
        df_dataset,
        next_token_class,
        tokenizer,
        classification_type,
        transformer_type
):
    # Filter datapoints
    df_dataset = df_dataset[df_dataset['tokenClass'] == next_token_class]

    if len(df_dataset) == 1:
        df_dataset.reset_index(level=0,inplace=True)
        return_token = df_dataset.loc[0]['token']
        return None, None, None, return_token

    # Compute eligible token values
    df_eligibleTokens = df_dataset.groupby(['oracleId', 'oracleSoFar'])['token'].unique().to_frame()
    df_eligibleTokens = df_eligibleTokens.rename(columns={'token': 'eligibleTokens'})
    df_dataset = pd.merge(df_dataset, df_eligibleTokens, on=['oracleId', 'oracleSoFar']).reset_index()
    df_dataset["eligibleTokens"] = df_dataset["eligibleTokens"].apply(lambda x: "[ " + " ".join(x) + " ]")
    # Set type of dataframe columns
    df_dataset['eligibleTokens'] = df_dataset['eligibleTokens'].astype('string')

    # Define the new order of columns
    new_columns_order = [
        'oracleId', 'token', 'oracleSoFar', 'eligibleTokens', 'tokenInfo', 'tokenClass',
        'javadocTag', 'oracleType', 'packageName', 'className', 'methodSourceCode', 'methodJavadoc'
    ]
    # Reindex the DataFrame with the new order
    df_dataset = df_dataset.reindex(columns=new_columns_order)
    # Delete spurious columns for predicting the next token class
    df_dataset = df_dataset.drop(['oracleId'], axis=1)
    if classification_type == ClassificationType.CATEGORY_PREDICTION:
        # Remove category to predict
        df_dataset = df_dataset.drop(['token'], axis=1)
        tgt = []
    else:
        tgt = df_dataset["token"].values.tolist()
    if transformer_type == TransformerType.DECODER and classification_type == ClassificationType.CATEGORY_PREDICTION:
        df_dataset = df_dataset.drop(['tokenInfo'], axis=1)
        # Delete duplicates
        df_dataset = df_dataset.drop_duplicates()
        assert len(df_dataset) == 1
    # Generate string datapoints concatenating the fields of each column and separating them with a special token
    df_src_concat = df_dataset.apply(lambda row: tokenizer.sep_token.join(row.values), axis=1)
    # The pandas dataframe is transformed in a list of strings: each string is an input to the model
    src = df_src_concat.to_numpy().tolist()
    # Extract eligible token classes as list
    eligible_token_values = df_dataset['eligibleTokens'][0].strip("[]").split()
    # Return source input and token classes dictionary
    return src, tgt, eligible_token_values, None


def tokenize_input(
        src,
        tgt,
        tokenizer,
        classification_type
):
    # Tokenize the inputs datapoints
    inputs_dict = tokenizer.batch_encode_plus(
        src,
        max_length=512,
        padding=True,
        truncation=True,
        return_tensors="pt"
    )
    # Transform the list into a tensor stack
    t_inputs = torch.stack([ids.clone().detach() for ids in inputs_dict['input_ids']])
    t_attention_masks = torch.stack([mask.clone().detach() for mask in inputs_dict['attention_mask']])
    if classification_type == ClassificationType.CATEGORY_PREDICTION:
        t_tgt = torch.stack([torch.empty((0,1)) for i in range(len(src))])
    elif classification_type == ClassificationType.LABEL_PREDICTION:
        tgt_dict = tokenizer.batch_encode_plus(
            tgt,
            max_length=8,
            padding=True,
            truncation=True,
            return_tensors="pt"
        )
        t_tgt = torch.stack([ids.clone().detach() for ids in tgt_dict['input_ids']])
    # Return tuple of inputs and attention masks
    return (t_inputs, t_attention_masks, t_tgt)


def next_token(
        device,
        filename: str,
        classification_type_token_classes: Type[ClassificationType],
        classification_type_token_values: Type[ClassificationType],
        transformer_type_token_classes: Type[TransformerType],
        transformer_type_token_values: Type[TransformerType],
        model_classes: Type[PreTrainedModel],
        model_values: Type[PreTrainedModel],
        tokenizer_token_classes: PreTrainedTokenizer,
        tokenizer_token_values: PreTrainedTokenizer
):
    # Collect partial dataframes from oracles
    print("Predict next token")
    df_dataset = pd.read_json(filename)
    # Pre-process dataset
    print("Pre-processing dataset")
    df_dataset = pre_process_dataset(df_dataset, tokenizer_token_classes)

    # If there is a single row, there is no need to make predictions. The unique possible value is returned
    if len(df_dataset) == 1:
        return df_dataset['token'][0] + "\n" + next((key for key, val in value_mappings.items() if val == df_dataset['tokenClass'][0]), None)

    # Get model token classes input
    print("Get model token classes input")
    src_token_classes, tgt_token_classes, eligible_token_classes = get_input_model_classes(
        df_dataset,
        tokenizer_token_classes,
        classification_type_token_classes,
        transformer_type_token_classes
    )
    # Tokenize input
    print("Tokenize model token classes input")
    t_src_token_classes = tokenize_input(
        src_token_classes,
        tgt_token_classes,
        tokenizer_token_classes,
        classification_type_token_classes
    )
    # Generate data loader
    t_t_src_token_classes = TensorDataset(*t_src_token_classes)
    dl_src_token_classes = DataLoader(
        t_t_src_token_classes,
        batch_size=32
    )
    # Predict next token class
    print("Predict next token class")
    next_token_class = predict_next(
        device,
        model_classes,
        dl_src_token_classes,
        tokenizer_token_classes,
        eligible_token_classes,
        classification_type_token_classes,
        transformer_type_token_classes,
        TrattoModelType.TOKEN_CLASSES
    )
    # Get model token values input
    print("Get model token values input")
    src_token_values, tgt_token_values, eligible_token_values, next_token_value = get_input_model_values(
        df_dataset,
        next_token_class,
        tokenizer_token_values,
        classification_type_token_values,
        transformer_type_token_values
    )

    if not next_token_value is None:
        original_next_token_class = next((key for key, val in value_mappings.items() if val == next_token_class), None)
        return next_token_value + "\n" + original_next_token_class

    # Tokenize input
    print("Tokenize model token values input")
    t_src_token_values = tokenize_input(
        src_token_values,
        tgt_token_values,
        tokenizer_token_values,
        classification_type_token_values
    )
    # Generate data loader
    t_t_src_token_values = TensorDataset(*t_src_token_values)
    dl_src_token_values = DataLoader(
        t_t_src_token_values,
        batch_size=32
    )
    # Predict next token value
    print("Predict next token value")
    next_token_value = predict_next(
        device,
        model_values,
        dl_src_token_values,
        tokenizer_token_values,
        eligible_token_values,
        classification_type_token_values,
        transformer_type_token_values,
        TrattoModelType.TOKEN_VALUES
    )
    # Return next token
    original_next_token_class = next((key for key, val in value_mappings.items() if val == next_token_class), None)
    return next_token_value + "\n" + original_next_token_class





