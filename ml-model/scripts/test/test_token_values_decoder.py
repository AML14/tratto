import argparse
import os
import torch
import numpy as np
import pandas as pd
from torch.utils.data import DataLoader, TensorDataset
from src.types.ClassificationType import ClassificationType
from src.types.DeviceType import DeviceType
from src.utils import utils
from src.pretrained.ModelClasses import ModelClasses


def predict(
        device,
        model,
        dl_data,
        tokenizer,
        classification_type
):
    # Model in evaluation mode
    model.eval()
    # stats and counter
    prediction_stats = []
    id_counter = 0
    num_beams = 1

    # The prediction is performed without accumulating the gradient descent and without updating the weights of the model
    with torch.no_grad():
        for batch_id, batch in enumerate(dl_data, 1):
            print(f"            Processing batch {batch_id} of {len(dl_data)}")
            # Extract the inputs, the attention masks and the targets from the batch
            src_input = batch[0].to(device)
            src_masks = batch[1].to(device)
            tgt_out = batch[2].to(device)
            javadoc_tags = batch[3].to(device)

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
            # Extract original javadoc tags (for analysis purpose)
            javadoc_tags_str = np.array(
                tokenizer.batch_decode(
                    javadoc_tags,
                    skip_special_tokens=True
                )
            )
            if classification_type == ClassificationType.LABEL_PREDICTION:
                token_values_out = np.array(
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
                    expected_token_value = token_values_out[idx]

                # Update the counter of the predictions
                prediction_result = (predicted == expected)
                # Log results
                print(f"Javadoc Tag: {original_javadoc_tag}")
                print(f"Prediction result: {prediction_result}")
                print(f"Predicted token value: {predicted}")
                print(f"Expected token value: {expected}")

                # Compute statistics
                stats = {
                    "id": id_counter,
                    "javadoc_tag": original_javadoc_tag,
                    "output": predicted,
                    "correct": str(prediction_result),
                    "token": expected if classification_type == ClassificationType.CATEGORY_PREDICTION else expected_token_value
                }
                id_counter += 1
                prediction_stats.append(stats)
        return prediction_stats


def pre_processing(
        df_dataset,
        tokenizer,
        classification_type
):
    """
    The method pre-processes the loaded dataset that will be used to train and test the model.
    """
    # Drop column id (it is not relevant for training the model)
    df_dataset = df_dataset.drop(['id'], axis=1)
    # Map empty cells to empty strings
    df_dataset.fillna('', inplace=True)
    # Specify the type of each column in the dataset
    df_dataset = df_dataset.astype({
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

    # Compute eligible token values
    df_eligibleTokens = df_dataset.groupby(['oracleId', 'oracleSoFar'])['token'].unique().to_frame()
    df_eligibleTokens = df_eligibleTokens.rename(columns={'token': 'eligibleTokens'})
    df_dataset = pd.merge(df_dataset, df_eligibleTokens, on=['oracleId', 'oracleSoFar']).reset_index()
    df_dataset["eligibleTokens"] = df_dataset["eligibleTokens"].apply(lambda x: "[ " + " ".join(x) + " ]")
    # Set type of dataframe columns
    df_dataset['eligibleTokens'] = df_dataset['eligibleTokens'].astype('string')
    # Define the new order of columns
    new_columns_order = [
        'tokenClass', 'tokenInfo', 'token', 'oracleSoFar', 'eligibleTokens', 'javadocTag', 'oracleType',
        'projectName', 'packageName', 'className', 'methodJavadoc', 'methodSourceCode', 'classJavadoc',
        'classSourceCode', 'oracleId', 'label'
    ]
    # Temp empty tokenInfo
    df_dataset["tokenInfo"] = df_dataset["tokenInfo"].apply(lambda x: "")
    # Reindex the DataFrame with the new order
    df_dataset = df_dataset.reindex(columns=new_columns_order)
    # Group the rows by 'oracleId' and 'oracleSoFar'
    df_grouped = df_dataset.groupby(['oracleId', 'oracleSoFar'])
    # Create an empty dictionary to store the separate datasets
    datasets = []
    # Iterate through the groups and assign them to separate datasets
    for identifier, group_data in df_grouped:
        # Delete the tgt labels from the input dataset, and others less relevant columns
        src_group_data = group_data.drop(['label', 'oracleId', 'projectName', 'tokenClass', 'token_info', 'classJavadoc', 'classSourceCode'], axis=1)
        # Get the list of target values from the dataframe
        if classification_type == ClassificationType.CATEGORY_PREDICTION:
            tgt = src_group_data["token"].values.tolist()
            src_group_data = src_group_data.drop(['token'], axis=1)
        else:
            tgt = src_group_data["label"].values.tolist()
            src_group_data = src_group_data.drop(['label'], axis=1)
        df_src_concat = src_group_data.apply(lambda row: tokenizer.sep_token.join(row.values), axis=1)
        # The pandas dataframe is transformed in a list of strings: each string is an input to the model
        src = df_src_concat.to_numpy().tolist()
        # Add javadoc tags and token classes for analysis purposes
        javadoc_tags = group_data["javadocTag"].values.tolist()
        tokens = group_data["token"].values.tolist()
        # Append grouped dataset
        datasets.append((identifier, src, tgt, javadoc_tags, tokens))
        # Return list of grouped datasets
    return datasets


def tokenize_datasets(
        datasets,
        tokenizer
):
    t_datasets = []

    for identifier, inputs, targets, javadoc_tags, tokens in datasets:
        # Tokenize the inputs datapoints
        t_src_dict = tokenizer.batch_encode_plus(
            inputs,
            max_length=512,
            padding=True,
            truncation=True,
            return_tensors="pt"
        )
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
        t_tokens_dict = tokenizer.batch_encode_plus(
            tokens,
            max_length=512,
            padding=True,
            truncation=True,
            return_tensors="pt"
        )
        # Transform the list into a tensor stack
        t_inputs = torch.stack([ids.clone().detach() for ids in t_src_dict['input_ids']])
        t_inputs_attention_masks = torch.stack([mask.clone().detach() for mask in t_src_dict['attention_mask']])
        t_targets = torch.stack([ids.clone().detach() for ids in t_tgt_dict['input_ids']])
        t_javadoc_tags = torch.stack([ids.clone().detach() for ids in t_javadoctag_dict['input_ids']])
        t_tokens = torch.stack([ids.clone().detach() for ids in t_tokens_dict['input_ids']])
        # Generate the tokenized dataset
        t_dataset = (t_inputs, t_inputs_attention_masks, t_targets, t_javadoc_tags, t_tokens)
        t_datasets.append((identifier,t_dataset))
    return t_datasets


def main(
        project_name: str,
        checkpoint_path: str,
        input_path: str,
        output_path: str,
        classification_type: str,
        model_type: str,
        tokenizer_name: str,
        config_name: str,
        model_name_or_path: str
):
    """
    The main method load the checkpoint and perform the analysis of the performance of the model on a given
    unseen project.
    """

    # Connect to device
    print("Connect to device")
    device = utils.connect_to_device(DeviceType.GPU)

    # list of partial dataframes
    dfs = []
    # collects partial dataframes from oracles
    for file_name in os.listdir(input_path):
        if project_name in file_name:
            print(file_name)
            df = pd.read_json(os.path.join(input_path, file_name))
            if classification_type == ClassificationType.CATEGORY_PREDICTION:
                # Keep only a single instance for each combination of oracleId and oracleSoFar
                df_true_oracles = df[(df['label'] == True)]
            dfs.append(df_true_oracles)
    df_dataset = pd.concat(dfs)

    print("Setup model")
    # Get configuration class, model class, and tokenizer class from the corresponding model type
    config_class, model_class, tokenizer_class = ModelClasses.getModelClass(model_type)
    # Setup tokenizer
    tokenizer = tokenizer_class.from_pretrained(tokenizer_name)

    # Setup model
    config = config_class.from_pretrained(
        config_name if config_name else model_name_or_path,
    )
    model = model_class.from_pretrained(
        model_name_or_path,
        config=config
    )
    model.to(device)
    # Load checkpoint
    checkpoint = torch.load(checkpoint_path, map_location=torch.device(device))
    model.load_state_dict(checkpoint['model_state_dict'])

    print("Pre-processing dataset")
    # Pre-processing data
    datasets = pre_processing(df_dataset, tokenizer, classification_type)
    # Process the data
    t_datasets = tokenize_datasets(datasets, tokenizer)
    print("Start predictions")
    predictions_stats = {}

    model_stats = {
        "totals": len(t_datasets),
        "false_positives": 0,
        "true_positives": 0,
        "ones": []
    }

    for idx, t_dataset in enumerate(t_datasets, 1):
        identifier, t_data = t_dataset
        print(f"Parsing oracle group {idx} of  {len(t_datasets)}")
        t_t_data = TensorDataset(*t_data)
        dl_data = DataLoader(
            t_t_data,
            batch_size=32
        )
        if not str(identifier[0]) in predictions_stats:
            predictions_stats[str(identifier[0])] = {}
        p_stats = predict(device, model, dl_data, tokenizer, classification_type)
        predictions_stats[str(identifier[0])][identifier[1] if not identifier[1] == "" else "_"] = p_stats
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
        help="Model type selected in the list: " + ", ".join(ModelClasses.get_available_model_classes())
    )
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
    print(args.checkpoint_path)
    if not os.path.exists(args.checkpoint_path):
        raise ValueError("The checkpoint path argument contains a value that does not point to an existing checkpoint.")

    main(
        args.project_name,
        args.checkpoint_path,
        args.input_path,
        args.output_path,
        args.classification_type,
        args.model_type,
        args.tokenizer_name,
        args.config_name,
        args.model_name_or_path
    )
