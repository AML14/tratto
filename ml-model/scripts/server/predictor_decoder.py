import os.path

import torch
import numpy as np
import pandas as pd
import Levenshtein
from torch.utils.data import DataLoader, TensorDataset
from transformers import PreTrainedModel, PreTrainedTokenizer
from typing import Type, re
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

def predict_next(
        device,
        model,
        dl_src,
        tokenizer,
        eligible_tokens,
        model_type
):
    # Model in evaluation mode
    model.eval()
    num_beams = 1
    # The prediction is performed without accumulating the gradient descent and without updating the weights of the model
    with torch.no_grad():
        for batch_id, batch in enumerate(dl_src, 1):
            # Extract the inputs, the attention masks and the targets from the batch
            src_input = batch[0].to(device)
            src_masks = batch[1].to(device)
            # Model predictions with beam-search model
            outputs_generate = model.generate(
                input_ids=src_input,
                attention_mask=src_masks,
                num_beams=num_beams,
                num_return_sequences=num_beams,
                do_sample=False
            )
            predicted_generate = np.array(
                tokenizer.batch_decode(
                    outputs_generate,
                    skip_special_tokens=True
                )
            )
            assert len(predicted_generate) == num_beams
            # First-choice beam search
            predicted_1 = predicted_generate[0]

            # Heuristics to mitigate knowns prediction errors
            if model_type == TrattoModelType.TOKEN_CLASSES:
                if predicted_1 not in list(value_mappings.values()):
                    subwordSplit = predicted_1.split("_")
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
                return predicted_1
            # If no token has been found, compute the Levenshtein distance among the eligible token classes
            best_distance = float('inf')
            most_probable_token = None
            for eligible in eligible_tokens:
                distance = Levenshtein.distance(predicted_1, eligible)
                if distance < best_distance and eligible in eligible_tokens:
                    best_distance = distance
                    most_probable_token = eligible
            return most_probable_token


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
        tokenizer
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
        'javadocTag', 'oracleType', 'packageName', 'className', 'methodJavadoc', 'methodSourceCode'
    ]
    # Reindex the DataFrame with the new order
    df_dataset = df_dataset.reindex(columns=new_columns_order)
    # Delete spurious columns for predicting the next token class
    df_dataset = df_dataset.drop(['oracleId', 'token', 'tokenInfo', 'tokenClass'], axis=1)
    # Delete duplicates
    df_dataset = df_dataset.drop_duplicates()
    assert len(df_dataset) == 1
    # Generate string datapoints concatenating the fields of each column and separating them with a special token
    df_src_concat = df_dataset.apply(lambda row: tokenizer.sep_token.join(row.values), axis=1)
    # The pandas dataframe is transformed in a list of strings: each string is an input to the model
    src = df_src_concat.to_numpy().tolist()
    # Extract eligible token classes as list
    eligible_token_classes = df_dataset['eligibleTokenClasses'][0].strip("[]").split()
    # Return source input and token classes dictionary
    return src, eligible_token_classes

def get_input_model_values(
        df_dataset,
        next_token_class,
        tokenizer
):
    # Filter datapoints
    df_dataset = df_dataset[df_dataset['tokenClass'] == next_token_class]
    # Compute eligible token values
    df_eligibleTokens = df_dataset.groupby(['oracleId', 'oracleSoFar'])['token'].unique().to_frame()
    df_eligibleTokens = df_eligibleTokens.rename(columns={'token': 'eligibleTokens'})
    df_dataset = pd.merge(df_dataset, df_eligibleTokens, on=['oracleId', 'oracleSoFar']).reset_index()
    df_dataset["eligibleTokens"] = df_dataset["eligibleTokens"].apply(lambda x: "[ " + " ".join(x) + " ]")
    # Set type of dataframe columns
    df_dataset['eligibleTokens'] = df_dataset['eligibleTokens'].astype('string')
    # Temp empty tokenInfo
    df_dataset["tokenInfo"] = df_dataset["tokenInfo"].apply(lambda x: "")

    # Define the new order of columns
    new_columns_order = [
        'oracleId', 'tokenClass', 'token', 'tokenInfo', 'oracleSoFar', 'eligibleTokens', 'javadocTag', 'oracleType',
        'packageName', 'className', 'methodJavadoc', 'methodSourceCode'
    ]
    # Reindex the DataFrame with the new order
    df_dataset = df_dataset.reindex(columns=new_columns_order)
    # Delete spurious columns for predicting the next token class
    df_dataset = df_dataset.drop(['oracleId', 'tokenClass','token'], axis=1)
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
    return src, eligible_token_values


def tokenize_input(
        src,
        tokenizer
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
    t_inputs = torch.stack([torch.tensor(ids) for ids in inputs_dict['input_ids']])
    t_attention_masks = torch.stack([torch.tensor(mask) for mask in inputs_dict['attention_mask']])
    # Return tuple of inputs and attention masks
    return (t_inputs, t_attention_masks)


def next_token(
        device,
        filename: str,
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
    src_token_classes, eligible_token_classes = get_input_model_classes(df_dataset, tokenizer_token_classes)
    # Tokenize input
    print("Tokenize model token classes input")
    t_src_token_classes = tokenize_input(src_token_classes, tokenizer_token_classes)
    # Generate data loader
    t_t_src_token_classes = TensorDataset(*t_src_token_classes)
    dl_src_token_classes = DataLoader(
        t_t_src_token_classes,
        batch_size=32
    )
    # Predict next token class
    print("Predict next token class")
    next_token_class = predict_next(device, model_classes, dl_src_token_classes, tokenizer_token_classes, eligible_token_classes, TrattoModelType.TOKEN_CLASSES)
    # Get model token values input
    print("Get model token values input")
    src_token_values, eligible_token_values = get_input_model_values(df_dataset, next_token_class, tokenizer_token_values)
    # Tokenize input
    print("Tokenize model token values input")
    t_src_token_values = tokenize_input(src_token_values, tokenizer_token_values)
    # Generate data loader
    t_t_src_token_values = TensorDataset(*t_src_token_values)
    dl_src_token_values = DataLoader(
        t_t_src_token_values,
        batch_size=32
    )
    # Predict next token value
    print("Predict next token value")
    next_token_value = predict_next(device, model_values, dl_src_token_values, tokenizer_token_values, eligible_token_values, TrattoModelType.TOKEN_VALUES)
    # Return next token
    original_next_token_class = next((key for key, val in value_mappings.items() if val == next_token_class), None)
    return next_token_value + "\n" + original_next_token_class





