import argparse
import os
import torch
import numpy as np
import pandas as pd
import re
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

    # Map token class names
    _, value_mappings = utils.import_json(
        os.path.join(
            os.path.dirname(os.path.abspath(__file__)),
            '..',
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
            src_input = batch[0].to(device)
            src_masks = batch[1].to(device)
            tgt_out = batch[2].to(device)

            # Model predictions with model-token-by-token and whole-model
            print(f"                Model predictions...")
            outputs_model = model(
                input_ids=src_input,
                attention_mask=src_masks,
                labels=tgt_out
            )
            # Exctract the predicted values and the expected output
            predicted_model = np.array(
                tokenizer.batch_decode(
                    torch.argmax(torch.softmax(outputs_model.logits, dim=-1), dim=-1),
                    skip_special_tokens=True,
                )
            )
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

            expected_out = np.array(
                tokenizer.batch_decode(
                    tgt_out,
                    skip_special_tokens=True
                )
            )

            if classification_type == ClassificationType.LABEL_PREDICTION:
                token_classes_out = np.array(
                    tokenizer.batch_decode(
                        batch[3].to(device),
                        skip_special_tokens=True
                    )
                )

            final_token_class = []

            # assert len(predicted_model) == 1

            p = predicted_model[0]
            p_1 = predicted_generate[0]
            expected_out = [expected_out[0]]

            if (p not in list(value_mappings.values())) or (not p == p_1):
                subwordSplit = p.split("_")
                for eligible in value_mappings.values():
                    if eligible.endswith(subwordSplit[-1]):
                        final_token_class.append(eligible)
                        break
                if len(final_token_class) == 0:
                    for p_g in predicted_generate[1:]:
                        for eligible in value_mappings.values():
                            if eligible == p_g:
                                final_token_class.append(eligible)
                                break
                if len(final_token_class) == 0:
                    camelCaseSplit = re.split(r"(?=[A-Z])", subwordSplit[-1])
                    for eligible in value_mappings.values():
                        if eligible.endswith(camelCaseSplit[-1]):
                            final_token_class.append(eligible)
                            break
                if len(final_token_class) == 0:
                    final_token_class.append(p)
            else:
                final_token_class.append(p)

            # Update the counter of the predictions
            prediction_result = (final_token_class == expected_out)

            print(prediction_result)

            # Compute statistics
            stats = {
                "id": id_counter,
                "output": final_token_class[0],
                "correct": str(prediction_result),
                "token": expected_out[0] if classification_type == ClassificationType.CATEGORY_PREDICTION else
                token_classes_out[0]
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

    # Map token class names
    _, value_mappings = utils.import_json(
        os.path.join(
            os.path.dirname(os.path.abspath(__file__)),
            '..',
            '..',
            '..',
            'src',
            'resources',
            'tokenClassesValuesMapping.json'
        )
    )
    # Get tokenizer vocabulary
    vocab = tokenizer.get_vocab()
    # Add new tokens to vocabulary
    for new_word in value_mappings.values():
        for new_sub_word in new_word.split("_"):
            if not new_sub_word in vocab.keys():
                tokenizer.add_tokens([new_sub_word])

    # Replace the values in the DataFrame column
    df_dataset['tokenClass'] = df_dataset['tokenClass'].replace(value_mappings)
    # Map tokenClassesSoFar list to string representation
    df_dataset["tokenClassesSoFar"] = df_dataset["tokenClassesSoFar"].apply(lambda x: "[ " + " ".join([value_mappings[y] for y in x]) + " ]")
    # Get list of next eligible token classes
    df_eligibleTokenClasses = df_dataset.groupby(['oracleId', 'oracleSoFar'])['tokenClass'].unique().to_frame()
    df_eligibleTokenClasses = df_eligibleTokenClasses.rename(columns={'tokenClass': 'eligibleTokenClasses'})
    df_dataset = pd.merge(df_dataset, df_eligibleTokenClasses, on=['oracleId', 'oracleSoFar']).reset_index()
    # Map eligibleTokenClasses list to string representation
    df_dataset["eligibleTokenClasses"] = df_dataset["eligibleTokenClasses"].apply(lambda x: "[ " + " ".join(x) + " ]")
    # Convert columns to string
    df_dataset['eligibleTokenClasses'] = df_dataset['eligibleTokenClasses'].astype('string')
    df_dataset['tokenClass'] = df_dataset['tokenClass'].astype('string')
    df_dataset['tokenClassesSoFar'] = df_dataset['tokenClassesSoFar'].astype('string')
    # Define the new order of columns
    new_columns_order = [
        'token', 'tokenInfo', 'tokenClass', 'oracleSoFar', 'tokenClassesSoFar', 'eligibleTokenClasses', 'javadocTag', 'oracleType',
        'packageName', 'className', 'methodJavadoc', 'methodSourceCode', 'classJavadoc', 'classSourceCode',
        'projectName', 'oracleId', 'label'
    ]
    # Keep only a single instance for each combination of oracleId and oracleSoFar
    if classification_type == ClassificationType.CATEGORY_PREDICTION:
        df_dataset = df_dataset[df_dataset['label']=='True']
    # Reindex the DataFrame with the new order
    df_dataset = df_dataset.reindex(columns=new_columns_order)
    # Group the rows by 'oracleId' and 'oracleSoFar'
    df_grouped = df_dataset.groupby(['oracleId', 'oracleSoFar'])
    # Create an empty dictionary to store the separate datasets
    datasets = []
    # Iterate through the groups and assign them to separate datasets
    for identifier, group_data in df_grouped:
        # Delete the tgt labels from the input dataset, and others less relevant columns
        src_group_data = group_data.drop(['label','oracleId', 'projectName', 'classJavadoc', 'classSourceCode'], axis=1)
        src_group_data = src_group_data.drop(['token', 'tokenInfo'], axis=1)
        # Get the list of target values from the dataframe
        if classification_type == ClassificationType.CATEGORY_PREDICTION:
            tgt = group_data["tokenClass"].values.tolist()
            src_group_data = src_group_data.drop(['tokenClass'], axis=1)
        else:
            tgt = group_data['label'].values.tolist()
        df_src_concat = src_group_data.apply(lambda row: tokenizer.sep_token.join(row.values), axis=1)
        # The pandas dataframe is transformed in a list of strings: each string is an input to the model
        src = df_src_concat.to_numpy().tolist()
        datasets.append((identifier, src, tgt))
    return datasets


def tokenize_datasets(
        datasets,
        tokenizer
):
    t_datasets = []

    for identifier, inputs, targets in datasets:
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
        # Transform the list into a tensor stack
        t_inputs = torch.stack([torch.tensor(ids) for ids in t_src_dict['input_ids']])
        t_inputs_attention_masks = torch.stack([torch.tensor(mask) for mask in t_src_dict['attention_mask']])
        t_targets = torch.stack([torch.tensor(ids) for ids in t_tgt_dict['input_ids']])
        # Generate the tokenized dataset
        t_dataset = (t_inputs, t_inputs_attention_masks, t_targets)
        t_datasets.append((identifier,t_dataset))
    return t_datasets


def main(
        project_name: str,
        checkpoint_path: str,
        input_path: str,
        output_path: str,
        classification_type: str
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
            dfs.append(df)
    df_dataset = pd.concat(dfs)

    print("Setup model")
    # Get configuration class, model class, and tokenizer class from the corresponding model type
    config_class, model_class, tokenizer_class = ModelClasses.getModelClass(args.model_type)
    # Setup tokenizer
    tokenizer = tokenizer_class.from_pretrained(args.tokenizer_name)

    print("Pre-processing dataset")
    # Pre-processing data
    datasets = pre_processing(df_dataset, tokenizer, classification_type)
    # Process the data
    t_datasets = tokenize_datasets(datasets, tokenizer, classification_type)

    # Setup model
    config = config_class.from_pretrained(
        args.config_name if args.config_name else args.model_name_or_path
    )
    model = model_class.from_pretrained(
        args.model_name_or_path,
        config=config
    )
    model.resize_token_embeddings(len(tokenizer))
    model.to(device)

    # Load checkpoint
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
        args.classification_type
    )