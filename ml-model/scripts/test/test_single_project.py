import argparse
import os
import random

import torch
import numpy as np
import pandas as pd
from typing import Type
from torch.utils.data import DataLoader, TensorDataset
from src.parser.ArgumentParser import ArgumentParser
from src.types.ClassificationType import ClassificationType
from src.types.DeviceType import DeviceType
from src.types.TransformerType import TransformerType
from src.types.TrattoModelType import TrattoModelType
from src.processors.DataProcessor import DataProcessor
from src.utils import utils
from src.pretrained.ModelClasses import ModelClasses

def get_default_converter_in_category_token_classes():
    _, classificator_converter_in = utils.import_json(
        os.path.join(
            os.path.dirname(os.path.abspath(__file__)),
            '..',
            'server',
            'resources',
            'classificator_converter_in_category_token_classes.json'
        )
    )
    return classificator_converter_in

def get_default_converter_in_label():
    _, classificator_converter_in = utils.import_json(
        os.path.join(
            os.path.dirname(os.path.abspath(__file__)),
            '..',
            'server',
            'resources',
            'classificator_converter_in_label.json'
        )
    )
    return classificator_converter_in


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
    value_mappings = DataProcessor.get_token_classes_value_mappings()

    # The prediction is performed without accumulating the gradient descent and without updating the weights of the model
    with torch.no_grad():
        for batch_id, batch in enumerate(dl_data, 1):
            print(f"Processing batch {batch_id} of {len(dl_data)}")
            # Extract the inputs, the attention masks and the targets from the batch
            src_input = batch[0].to(device)
            src_masks = batch[1].to(device)
            tgt_out = batch[2].to(device)
            javadoc_tags = batch[3].to(device)

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
                        batch[4].to(device),
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
        t_df,
        tokenizer,
        classification_type,
        tratto_model_type,
        transformer_type
):
    # Map empty cells to empty strings
    t_df.fillna('', inplace=True)
    if tratto_model_type == TrattoModelType.ORACLES:
        # Specify the type of each column in the dataset
        df_types = {
            'label': 'str',
            'oracleId': 'int64',
            'oracle': 'str',
            'oracleType': 'str',
            'javadocTag': 'str',
            'methodJavadoc': 'str',
            'methodSourceCode': 'str'
        }
        # Set the type of each column in the dataset
        t_df = t_df.astype(df_types)
        # Define the new order of columns
        new_columns_order = [
            'javadocTag', 'oracleType', 'methodSourceCode', 'methodJavadoc', 'oracleId', 'oracle', 'label'
        ]
        # Reindex the DataFrame with the new order
        t_df = t_df.reindex(columns=new_columns_order)
        # Get the list of target values from the dataframe
        if transformer_type == TransformerType.DECODER:
            t_tgt = t_df['label'].values.tolist()
        else:
            labels_ids_dict = {k: i for i, k in enumerate(sorted(list(t_df["label"].unique())))}
            t_tgt = list(map(lambda t: labels_ids_dict[t], t_df['label'].values.tolist()))
    else:
        # Specify the type of each column in the dataset
        t_df = t_df.astype({
            'label': 'str',
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
        # Get value mappings values
        value_mappings = DataProcessor.get_token_classes_value_mappings()
        # Replace the values in the DataFrame column
        t_df['tokenClass'] = t_df['tokenClass'].replace(value_mappings)

        if tratto_model_type == TrattoModelType.TOKEN_CLASSES:
            # Map token classes so far to new values and transform it from array to string
            t_df["tokenClassesSoFar"] = t_df["tokenClassesSoFar"].apply(lambda x: [value_mappings[y] for y in x])
            # Map token classes so far to new values and transform it from array to string
            t_df = DataProcessor.compute_tokenClassesSoFar(t_df)
            # Compute eligible token classes
            t_df = DataProcessor.compute_eligible_token_classes(t_df)
            # Define the new order of columns
            new_columns_order = [
                'tokenClass', 'oracleSoFar', 'tokenClassesSoFar', 'eligibleTokenClasses',
                'javadocTag', 'oracleType', 'packageName', 'className', 'methodSourceCode', 'methodJavadoc',
                'classJavadoc', 'classSourceCode', 'oracleId', 'label', 'token', 'tokenInfo', 'projectName'
            ]
            # Reindex the DataFrame with the new order
            t_df = t_df.reindex(columns=new_columns_order)
        else:
            # Compute eligible token values
            t_df = DataProcessor.compute_eligible_token_values(t_df)
            # Define the new order of columns
            new_columns_order = [
                'token', 'oracleSoFar', 'eligibleTokens', 'javadocTag', 'oracleType',
                'packageName', 'className', 'methodSourceCode', 'methodJavadoc', 'tokenInfo',
                'classJavadoc', 'classSourceCode', 'oracleId', 'label', 'projectName', 'tokenClass'
            ]
            # Reindex the DataFrame with the new order
            t_df = t_df.reindex(columns=new_columns_order)
    # Keep only the instance with label 'True', for each combination of oracleId and oracleSoFar, if the model
    # predicts the categories (tokenClasses or tokenValues)
    if classification_type == ClassificationType.CATEGORY_PREDICTION:
        # Keep only a single instance for each combination of oracleId and oracleSoFar
        t_df = t_df[(t_df['label'] == 'True')]

    # Group the rows according to the tratto model type
    if tratto_model_type == TrattoModelType.ORACLES:
        # Group the rows by 'oracleId'
        df_grouped = t_df.groupby(['oracleId'])
    else:
        # Group the rows by 'oracleId' and 'oracleSoFar'
        df_grouped = t_df.groupby(['oracleId', 'oracleSoFar'])
    # Create an empty dictionary to store the separate datasets
    datasets = []

    if classification_type == ClassificationType.CATEGORY_PREDICTION:
        if tratto_model_type == TrattoModelType.TOKEN_CLASSES:
            classificator_converter_in = get_default_converter_in_category_token_classes()
        else:
            classificator_converter_in = {k: i for i, k in enumerate(sorted(list(t_df["token"].unique())))}
    elif classification_type == ClassificationType.LABEL_PREDICTION:
        classificator_converter_in = get_default_converter_in_label()

    # Iterate through the groups and assign them to separate datasets
    for identifier, group_data in df_grouped:
        # Delete the tgt labels from the input dataset, and others less relevant columns
        if tratto_model_type == TrattoModelType.ORACLES:
            src_group_data = group_data.drop(['label', 'oracleId', 'oracle'], axis=1)
        else:
            src_group_data = group_data.drop(['label', 'oracleId', 'projectName', 'classJavadoc', 'classSourceCode'], axis=1)
            # If the model predicts the token classes, remove the token values from the input.
            # Else remove the token classes from the input
            if tratto_model_type == TrattoModelType.TOKEN_CLASSES:
                src_group_data = src_group_data.drop(['token', 'tokenInfo'], axis=1)
                # Remove the tokenClass column if the model will predict the tokenClass as target
                if classification_type == ClassificationType.CATEGORY_PREDICTION:
                    src_group_data = src_group_data.drop(['tokenClass'], axis=1)
            else:
                src_group_data = src_group_data.drop(['tokenClass'], axis=1)
                # Remove the token column if the model will predict the token as target
                if classification_type == ClassificationType.CATEGORY_PREDICTION:
                    src_group_data = src_group_data.drop(['token'], axis=1)
                # TODO: Check if tokenInfo must be removed
                # Remove the tokenInfo column if the classificator is a decoder and the model will predict the token as target
                if transformer_type == TransformerType.DECODER and classification_type == ClassificationType.CATEGORY_PREDICTION:
                    src_group_data = src_group_data.drop(['tokenInfo'], axis=1)
        # The pandas dataframe is transformed in a list of strings: each string is an input to the model
        src = DataProcessor.concat_src(src_group_data, tokenizer)
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
                else:
                    raise ValueError(f"Invalid combination of tratto model type and classification type: {tratto_model_type} - {classification_type}")
            elif classification_type == ClassificationType.LABEL_PREDICTION:
                tgt = list(map(lambda t: classificator_converter_in[t], group_data["label"].values.tolist()))
        # Add javadoc tags and token classes for analysis purposes
        javadoc_tags = group_data["javadocTag"].values.tolist()
        if tratto_model_type == TrattoModelType.TOKEN_CLASSES:
            expected_out = group_data["tokenClass"].values.tolist()
        elif tratto_model_type == TrattoModelType.TOKEN_VALUES:
            expected_out = group_data["token"].values.tolist()
        elif tratto_model_type == TrattoModelType.ORACLES:
            expected_out = group_data["oracle"].values.tolist()
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
        # Tokenize group of data
        tokenized_group = DataProcessor.tokenize_dataset(inputs, targets, tokenizer, transformer_type)
        t_inputs = tokenized_group[0]
        t_inputs_attention_masks = tokenized_group[1]
        t_targets = tokenized_group[2]
        # Tokenize additional information to put it within tensors
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
        t_javadoc_tags = torch.stack([ids.clone().detach() for ids in t_javadoctag_dict['input_ids']])
        t_outputs = torch.stack([ids.clone().detach() for ids in t_outputs_dict['input_ids']])
        # Generate the tuple of tokenized dataset group
        t_dataset = (t_inputs, t_inputs_attention_masks, t_targets, t_javadoc_tags, t_outputs)
        # Append the tuple to the list of groups representing the whole dataset
        t_datasets.append((identifier,t_dataset))
    return t_datasets


def main(
        project_name: str,
        checkpoint_path: str,
        input_path: str,
        output_path: str,
        classification_type: Type[ClassificationType],
        transformer_type: Type[TransformerType],
        tratto_model_type: Type[TrattoModelType],
        model_type: str,
        tokenizer_name: str,
        config_name: str,
        model_name_or_path: str,
        pre_processing: bool = True
):
    """
    The main method load the checkpoint and perform the analysis of the performance of the model on a given
    unseen project.
    """
    # Check if is a valid combination of TrattoModelType, TransformerType, and ClassificationType
    if not utils.is_valid_combination(tratto_model_type, transformer_type, classification_type):
        raise ValueError(f"Invalid combination of tratto model type, model type, and classification type: {tratto_model_type} - {model_type} - {classification_type}")
    # Connect to device
    print("Connect to device")
    device = utils.connect_to_device(DeviceType.GPU)
    # Load test dataset
    t_df = DataProcessor.load_dataset_as_dataframe(input_path, tratto_model_type, pre_processing)
    print("Setup model")
    # Get configuration class, model class, and tokenizer class from the corresponding model type
    config_class, model_class, tokenizer_class = ModelClasses.getModelClass(model_type)
    # Setup tokenizer
    tokenizer = tokenizer_class.from_pretrained(tokenizer_name)
    print("Pre-processing dataset")
    # Pre-processing data
    datasets, classificator_converter_out = pre_processing(
        t_df,
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
    model.to(device)
    # Load checkpoint
    if checkpoint_path.endswith(".bin"):
        model.load_state_dict(torch.load(checkpoint_path, map_location=torch.device(device)))
    elif checkpoint_path.endswith(".pt"):
        checkpoint = torch.load(checkpoint_path, map_location=torch.device(device))
        model.load_state_dict(checkpoint['model_state_dict'])
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
        dl_data = DataLoader(
            t_t_data,
            batch_size=32
        )
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
    ArgumentParser.add_test_arguments(parser)
    args = parser.parse_args()
    # Check if the input path exists
    if not os.path.exists(args.input_path):
        raise ValueError(f"The input path argument contains a value that does not point to an existing folder: {args.input_path}")
    else:
        file_list = os.listdir(args.input_path)
        for file_name in file_list:
            if not args.project_name in file_name:
                raise ValueError(f"The input path argument contains a value that does not point to a folder containing the project name: {args.input_path}")
    # Check if the checkpoint path exists
    if not os.path.exists(args.checkpoint_path):
        raise ValueError(f"The checkpoint path argument contains a value that does not point to an existing checkpoint: {args.checkpoint_path}")
    # Set the RAPIDS cuDF library if available
    if args.rapids_cudf == "True":
        utils.set_rapids_available()
    main(
        args.project_name,
        args.checkpoint_path,
        args.input_path,
        args.output_path,
        ClassificationType(args.classification_type.upper()),
        TransformerType(args.transformer_type.upper()),
        TrattoModelType(args.tratto_model_type.upper()),
        args.model_type,
        args.tokenizer_name,
        args.config_name,
        args.model_name_or_path,
        True if args.pre_processing == "True" else False
    )
