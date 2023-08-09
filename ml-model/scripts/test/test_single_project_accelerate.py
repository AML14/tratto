import argparse
import os
import random

import torch
import numpy as np
import pandas as pd
from torch.utils.data import DataLoader, TensorDataset

from src.types.ClassificationType import ClassificationType
from src.types.DeviceType import DeviceType
from src.types.TransformerType import TransformerType
from src.types.TrattoModelType import TrattoModelType
from src.utils import utils
from src.pretrained.ModelClasses import ModelClasses
from accelerate import Accelerator


def predict(
        device,
        model,
        dl_data,
        tokenizer,
        classification_type,
        transformer_type,
        classificator_converter_out
):
    # Model in evaluation mode
    model.eval()
    # Stats and counter
    prediction_stats = []
    id_counter = 0
    num_beams = 1

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

    # The prediction is performed without accumulating the gradient descent and without updating the weights of the model
    with torch.no_grad():
        for batch_id, batch in enumerate(dl_data, 1):
            print(f"            Processing batch {batch_id} of {len(dl_data)}")
            # Extract the inputs, the attention masks and the targets from the batch
            src_input = batch[0]
            src_masks = batch[1]
            tgt_out = batch[2]
            javadoc_tags = batch[3]

            if transformer_type == TransformerType.DECODER:
                # Generate output from trained model
                outputs_generate = model.generate(
                    input_ids=src_input,
                    attention_mask=src_masks,
                    num_beams=num_beams,
                    num_return_sequences=num_beams,
                    do_sample=False
                )
                # Extract the predicted values and the expected output
                predicted_generate = np.array(
                    tokenizer.batch_decode(
                        outputs_generate,
                        skip_special_tokens=True
                    )
                )
                # Extract expected target values
                expected_out = np.array(
                    tokenizer.batch_decode(
                        tgt_out,
                        skip_special_tokens=True
                    )
                )
            elif transformer_type == TransformerType.ENCODER:
                outputs_generate = model(
                    input_ids=src_input,
                    attention_mask=src_masks
                )
                logits = outputs_generate.logits
                probabilities = torch.softmax(logits, dim=1)
                predicted_generate_encoded = torch.argmax(probabilities, dim=1)
                predicted_generate = np.array(list(map(lambda x: classificator_converter_out[x.item()], predicted_generate_encoded)))
                # Extract expected target values
                expected_out = np.array(list(map(lambda x: classificator_converter_out[x.item()], tgt_out)))
            # Extract original javadoc tags (for analysis purpose)
            javadoc_tags_str = np.array(
                tokenizer.batch_decode(
                    javadoc_tags,
                    skip_special_tokens=True
                )
            )
            if classification_type == ClassificationType.LABEL_PREDICTION:
                class_out = np.array(
                    tokenizer.batch_decode(
                        batch[4],
                        skip_special_tokens=True
                    )
                )
            for idx in range(len(predicted_generate)):
                original_javadoc_tag = javadoc_tags_str[idx]
                predicted = predicted_generate[idx]
                expected = expected_out[idx]
                if not classification_type == ClassificationType.CATEGORY_PREDICTION:
                    expected_class_out = class_out[idx]

                # Update the counter of the predictions
                prediction_result = (predicted == expected)
                # Log results
                print(f"Javadoc Tag: {original_javadoc_tag}")
                print(f"Prediction result: {prediction_result}")
                print(f"Predicted token class: {predicted}")
                print(f"Expected token class: {expected}")

                # Compute statistics
                stats = {
                    "id": id_counter,
                    "javadoc_tag": original_javadoc_tag,
                    "output": predicted,
                    "correct": str(prediction_result),
                    "token": expected if classification_type == ClassificationType.CATEGORY_PREDICTION else expected_class_out
                }
                id_counter += 1
                prediction_stats.append(stats)
        return prediction_stats


def pre_processing(
        df_dataset,
        tokenizer,
        classification_type,
        tratto_model_type,
        transformer_type
):
    # Map empty cells to empty strings
    df_dataset.fillna('', inplace=True)
    # Specify the type of each column in the dataset
    df_dataset = df_dataset.astype({
        'label': 'str',
        'oracleId': 'int64',
        'oracleType': 'string',
        'packageName': 'string',
        'className': 'string',
        'javadocTag': 'string',
        'methodJavadoc': 'string',
        'methodSourceCode': 'string',
        'oracleSoFar': 'string',
        'token': 'string',
        'tokenClass': 'string',
        'tokenInfo': 'string'
    })
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
    # If the model is for the token values consider only a subset of words
    # (the other ones will never appear whitin the dataset)
    if tratto_model_type == TrattoModelType.TOKEN_VALUES:
        _, ignore_values = utils.import_json(
            os.path.join(
                os.path.dirname(os.path.abspath(__file__)),
                '..',
                '..',
                'src',
                'resources',
                'model_values_ignore_value_mappings.json'
            )
        )
    vocab = tokenizer.get_vocab()
    for old_word, new_word in value_mappings.items():
        if tratto_model_type == TrattoModelType.TOKEN_VALUES and old_word in ignore_values:
            continue
        for new_sub_word in new_word.split("_"):
            if not new_sub_word in vocab.keys():
                tokenizer.add_tokens([new_sub_word])

    # Replace the values in the DataFrame column
    df_dataset['tokenClass'] = df_dataset['tokenClass'].replace(value_mappings)

    if tratto_model_type == TrattoModelType.TOKEN_CLASSES:
        # Map token classes so far to new values and transform it from array to string
        df_dataset["tokenClassesSoFar"] = df_dataset["tokenClassesSoFar"].apply(lambda x: "[ " + " ".join(random.sample([value_mappings[y] for y in x], len(x))) + " ]")
        # Compute eligible token classes
        df_eligibleTokenClasses = df_dataset.groupby(['oracleId', 'oracleSoFar'])['tokenClass'].unique().to_frame()
        df_eligibleTokenClasses = df_eligibleTokenClasses.rename(columns={'tokenClass': 'eligibleTokenClasses'})
        df_dataset = pd.merge(df_dataset, df_eligibleTokenClasses, on=['oracleId', 'oracleSoFar']).reset_index()
        df_dataset["eligibleTokenClasses"] = df_dataset["eligibleTokenClasses"].apply(lambda x: "[ " + " ".join(random.sample(list(x),len(x))) + " ]")
        # Set type of dataframe columns
        df_dataset['eligibleTokenClasses'] = df_dataset['eligibleTokenClasses'].astype('string')
        df_dataset['tokenClass'] = df_dataset['tokenClass'].astype('string')
        df_dataset['tokenClassesSoFar'] = df_dataset['tokenClassesSoFar'].astype('string')
        # Define the new order of columns
        new_columns_order = [
            'token', 'tokenInfo', 'tokenClass', 'oracleSoFar', 'tokenClassesSoFar', 'eligibleTokenClasses',
            'javadocTag', 'oracleType', 'packageName', 'className', 'methodSourceCode', 'methodJavadoc',
            'oracleId', 'label'
        ]
        # Reindex the DataFrame with the new order
        df_dataset = df_dataset.reindex(columns=new_columns_order)
    else:
        # Compute eligible token values
        df_eligibleTokens = df_dataset.groupby(['oracleId', 'oracleSoFar'])['token'].unique().to_frame()
        df_eligibleTokens = df_eligibleTokens.rename(columns={'token': 'eligibleTokens'})
        df_dataset = pd.merge(df_dataset, df_eligibleTokens, on=['oracleId', 'oracleSoFar']).reset_index()
        df_dataset["eligibleTokens"] = df_dataset["eligibleTokens"].apply(lambda x: "[ " + " ".join(random.sample(list(x),len(x))) + " ]")
        # Set type of dataframe columns
        df_dataset['eligibleTokens'] = df_dataset['eligibleTokens'].astype('string')
        # Define the new order of columns
        new_columns_order = [
            'token', 'oracleSoFar', 'eligibleTokens', 'tokenInfo', 'tokenClass', 'javadocTag', 'oracleType',
            'packageName', 'className', 'methodSourceCode', 'methodJavadoc', 'oracleId', 'label'
        ]
        # Reindex the DataFrame with the new order
        df_dataset = df_dataset.reindex(columns=new_columns_order)

    if classification_type == ClassificationType.CATEGORY_PREDICTION:
        # Keep only a single instance for each combination of oracleId and oracleSoFar
        df_dataset = df_dataset[(df_dataset['label'] == 'True')]

    # Remove method source code (keep only signature)
    df_dataset['methodSourceCode'] = df_dataset['methodSourceCode'].str.split('{').str[0]

    # Group the rows by 'oracleId' and 'oracleSoFar'
    df_grouped = df_dataset.groupby(['oracleId', 'oracleSoFar'])
    # Create an empty dictionary to store the separate datasets
    datasets = []

    if classification_type == ClassificationType.CATEGORY_PREDICTION:
        if tratto_model_type == TrattoModelType.TOKEN_CLASSES:
            classificator_converter_in = {k: i for i, k in enumerate(sorted(list(df_dataset["tokenClass"].unique())))}
        else:
            classificator_converter_in = {k: i for i, k in enumerate(sorted(list(df_dataset["token"].unique())))}
    elif classification_type == ClassificationType.LABEL_PREDICTION:
        ids_tgt_labels_dict = {i: str(k) for i, k in enumerate(sorted(list(df_dataset["label"].unique())))}
        classificator_converter_in = {k: i for i, k in ids_tgt_labels_dict.items()}

    # Iterate through the groups and assign them to separate datasets
    for identifier, group_data in df_grouped:
        # Delete the tgt labels from the input dataset, and others less relevant columns
        src_group_data = group_data.drop(['label', 'oracleId'], axis=1)
        # If the model predicts token classes, remove the token values from the input, else remove
        # the token classes from the input
        if tratto_model_type == TrattoModelType.TOKEN_CLASSES:
            src_group_data = src_group_data.drop(['token', 'tokenInfo'], axis=1)
            # Remove the tokenClass column if the model will predict the tokenClass as target
            if classification_type == ClassificationType.CATEGORY_PREDICTION:
                src_group_data = src_group_data.drop(['tokenClass'], axis=1)
        else:
            # Remove the token column if the model will predict the token as target
            if classification_type == ClassificationType.CATEGORY_PREDICTION:
                src_group_data = src_group_data.drop(['token'], axis=1)
            # Remove the tokenInfo column if the classificator is a decoder
            if transformer_type == TransformerType.DECODER and not classification_type == ClassificationType.LABEL_PREDICTION:
                src_group_data = src_group_data.drop(['tokenInfo'], axis=1)

        df_src_concat = src_group_data.apply(lambda row: tokenizer.sep_token.join(row.values), axis=1)
        # The pandas dataframe is transformed in a list of strings: each string is an input to the model
        src = df_src_concat.to_numpy().tolist()

        # Get the list of target values from the dataframe
        if transformer_type == TransformerType.DECODER:
            if classification_type == ClassificationType.CATEGORY_PREDICTION:
                if tratto_model_type == TrattoModelType.TOKEN_CLASSES:
                    tgt = group_data["tokenClass"].values.tolist()
                elif tratto_model_type == TrattoModelType.TOKEN_VALUES:
                    tgt = group_data["token"].values.tolist()
            elif classification_type == ClassificationType.LABEL_PREDICTION:
                tgt = group_data["label"].values.tolist()
        else:
            if classification_type == ClassificationType.CATEGORY_PREDICTION:
                if tratto_model_type == TrattoModelType.TOKEN_CLASSES:
                    tgt = list(map(lambda t: classificator_converter_in[t], group_data["tokenClass"].values.tolist()))
                elif tratto_model_type == TrattoModelType.TOKEN_VALUES:
                    tgt = list(map(lambda t: classificator_converter_in[t], group_data["token"].values.tolist()))
            elif classification_type == ClassificationType.LABEL_PREDICTION:
                tgt = list(map(lambda t: classificator_converter_in[t], group_data["label"].values.tolist()))
        # Add javadoc tags and token classes for analysis purposes
        javadoc_tags = group_data["javadocTag"].values.tolist()
        if TrattoModelType.TOKEN_CLASSES:
            expected_out = group_data["tokenClass"].values.tolist()
        elif TrattoModelType.TOKEN_VALUES:
            expected_out = group_data["token"].values.tolist()
        # Append grouped dataset
        datasets.append((identifier, src, tgt, javadoc_tags, expected_out))
    # Return list of grouped datasets
    return datasets, {k: i for i, k in classificator_converter_in.items()}


def tokenize_datasets(
        datasets,
        tokenizer,
        transformer_type
):
    t_datasets = []

    for identifier, inputs, targets, javadoc_tags, outputs in datasets:
        # Tokenize the inputs datapoints
        t_src_dict = tokenizer.batch_encode_plus(
            inputs,
            max_length=512,
            padding=True,
            truncation=True,
            return_tensors="pt"
        )
        if transformer_type == TransformerType.DECODER:
            t_tgt_dict = tokenizer.batch_encode_plus(
                targets,
                max_length=8,
                padding=True,
                truncation=True,
                return_tensors="pt"
            )
        t_javadoctag_dict = tokenizer.batch_encode_plus(
            javadoc_tags,
            max_length=512,
            padding=True,
            truncation=True,
            return_tensors="pt"
        )
        t_outputs_dict = tokenizer.batch_encode_plus(
            outputs,
            max_length=512,
            padding=True,
            truncation=True,
            return_tensors="pt"
        )
        # Transform the list into a tensor stack
        t_inputs = torch.stack([ids.clone().detach() for ids in t_src_dict['input_ids']])
        t_inputs_attention_masks = torch.stack([mask.clone().detach() for mask in t_src_dict['attention_mask']])
        if transformer_type == TransformerType.DECODER:
            t_targets = torch.stack([ids.clone().detach() for ids in t_tgt_dict['input_ids']])
        else:
            t_targets = torch.stack([torch.tensor(t) for t in targets])
        t_javadoc_tags = torch.stack([ids.clone().detach() for ids in t_javadoctag_dict['input_ids']])
        t_outputs = torch.stack([ids.clone().detach() for ids in t_outputs_dict['input_ids']])
        # Generate the tokenized dataset
        t_dataset = (t_inputs, t_inputs_attention_masks, t_targets, t_javadoc_tags, t_outputs)
        t_datasets.append((identifier,t_dataset))
    return t_datasets


def main(
        project_name: str,
        checkpoint_path: str,
        input_path: str,
        output_path: str,
        classification_type: str,
        tratto_model_type,
        model_type: str,
        tokenizer_name: str,
        config_name: str,
        model_name_or_path: str
):
    """
    The main method load the checkpoint and perform the analysis of the performance of the model on a given
    unseen project.
    """

    # Initialize accelerator for model distribution on multiple GPUs
    accelerator = Accelerator()

    # Connect to device
    print("Connect to device")
    device = utils.connect_to_device(DeviceType.ACCELERATOR)

    # List of partial dataframes
    dfs = []
    # Collects partial dataframes from oracles
    for file_name in os.listdir(input_path):
        if project_name in file_name:
            print(file_name)
            df = pd.read_json(os.path.join(input_path, file_name))
            dfs.append(df)
    df_dataset = pd.concat(dfs)
    df_dataset.reset_index(drop=True, inplace=True)

    print("Setup model")
    # Get configuration class, model class, and tokenizer class from the corresponding model type
    config_class, model_class, tokenizer_class, transformer_type = ModelClasses.getModelClass(model_type)
    # Setup tokenizer
    tokenizer = tokenizer_class.from_pretrained(tokenizer_name)

    print("Pre-processing dataset")
    # Pre-processing data
    datasets, classificator_converter_out = pre_processing(
        df_dataset,
        tokenizer,
        classification_type,
        tratto_model_type,
        transformer_type
    )
    # Process the data
    t_datasets = tokenize_datasets(datasets, tokenizer, transformer_type)

    # Setup model
    config = config_class.from_pretrained(
        config_name if config_name else model_name_or_path
    )
    model = model_class.from_pretrained(
        model_name_or_path,
        config=config
    )
    model.resize_token_embeddings(len(tokenizer))

    # Load checkpoint
    model = utils.resume_checkpoint_accelerate(model, args.checkpoint_path)

    print("Start predictions")
    predictions_stats = {}

    model_stats = {
        "totals": len(t_datasets),
        "false_positives": 0,
        "true_positives": 0,
        "ones": []
    }

    for idx, t_dataset in enumerate(t_datasets, 1):
        empty_counter = 0
        identifier, t_data = t_dataset
        print(f"Parsing oracle group {idx} of  {len(t_datasets)}")
        t_t_data = TensorDataset(*t_data)
        dl_data_cpu = DataLoader(
            t_t_data,
            batch_size=32
        )
        dl_data = accelerator.prepare(dl_data_cpu)
        if not str(identifier[0]) in predictions_stats:
            predictions_stats[str(identifier[0])] = {}
        p_stats = predict(device, model, dl_data, tokenizer, classification_type, transformer_type, classificator_converter_out)
        oracle_so_far = identifier[1] if not identifier[1] == "" else f"_{empty_counter}"
        if identifier[1] == "":
            empty_counter += 1
        predictions_stats[str(identifier[0])][oracle_so_far] = p_stats
        ones_false = 0
        ones_true = 0
        for p_s in p_stats:
            if not p_s["correct"] == 'True':
                model_stats["false_positives"] += 1
                ones_false += 1
            else:
                ones_true += 1
                model_stats["true_positives"] += 1
        if (not ones_true == 1) or ones_false > 0:
            model_stats["ones"].append((str(identifier[0]), str(identifier[1]), ones_true, ones_false, ones_true > 0))
    # Save statistics
    if not os.path.exists(output_path):
        os.makedirs(output_path)
    utils.export_stats(os.path.join(output_path, project_name, "predictions_stats.json"), predictions_stats)
    utils.export_stats(os.path.join(output_path, project_name, "model_stats.json"), model_stats)
    del model
    utils.release_memory()


if __name__ == "__main__":
    # Parse arguments
    parser = argparse.ArgumentParser()
    parser.add_argument(
        "--project_name",
        default=None,
        type=str,
        required=True,
        help="The name of the project to test, among ['gs-core','plume-lib','jgrapht','commons-math','commons-collections','guava-19']."
    )
    parser.add_argument(
        "--checkpoint_path",
        default=None,
        type=str,
        required=True,
        help="The path to the checkpoint to load."
    )
    parser.add_argument(
        "--input_path",
        default=None,
        type=str,
        required=True,
        help="The path to the dataset."
    )
    parser.add_argument(
        "--output_path",
        default=None,
        type=str,
        required=True,
        help="The path where to save the statistics."
    )
    parser.add_argument(
        "--model_type",
        default=None,
        type=str,
        required=True,
        help="Model type selected in the list: " + ", ".join(ModelClasses.get_available_model_classes()))
    parser.add_argument(
        "--tokenizer_name",
        default=None,
        type=str,
        help="Pretrained tokenizer name or path if not the same as model_name"
    )
    parser.add_argument(
        "--config_name",
        default=None,
        type=str,
        help="Pretrained config name or path if not the same as model_name"
    )
    parser.add_argument(
        "--model_name_or_path",
        default=None,
        type=str,
        required=True,
        help="Path to pre-trained model or shortcut name.")
    parser.add_argument(
        "--classification_type",
        default="label_prediction",
        type=str,
        help="Classification type: category prediction (category_prediction) or label prediction (label_prediction)."
    )
    parser.add_argument(
        "--tratto_model_type",
        default="token_classes",
        type=str,
        help="Tratto model type: token classes (token_classes) or token values (token_values)."
    )
    args = parser.parse_args()

    # Check if the project name exists
    if not args.project_name in ['gs-core', 'plume-lib', 'jgrapht', 'commons-math', 'commons-collections', 'guava-19']:
        raise ValueError(
            "Project not found. Please provide a project name, among the options: " \
            "['gs-core','plume-lib','jgrapht','commons-math','commons-collections','guava-19']"
        )
    # Check if the input path exists
    if not os.path.exists(args.input_path):
        raise ValueError("The input path argument contains a value that does not point to an existing folder.")
    # Check if the checkpoint path exists
    if not os.path.exists(args.checkpoint_path):
        raise ValueError("The checkpoint path argument contains a value that does not point to an existing checkpoint.")
    main(
        args.project_name,
        args.checkpoint_path,
        args.input_path,
        args.output_path,
        ClassificationType(args.classification_type.upper()),
        TrattoModelType(args.tratto_model_type.upper()),
        args.model_type,
        args.tokenizer_name,
        args.config_name,
        args.model_name_or_path
    )
