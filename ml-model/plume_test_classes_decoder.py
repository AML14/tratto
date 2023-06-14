import argparse
import os
import torch
import numpy as np
import pandas as pd
from torch.utils.data import DataLoader, TensorDataset
from transformers import RobertaTokenizer, T5ForConditionalGeneration, T5Config
from src.types.DeviceType import DeviceType
from src.utils import utils


def predict(
        device,
        model,
        dl_data,
        tokenizer
):
    # Model in evaluation mode
    model.eval()
    # stats and counter
    prediction_stats = []
    id_counter = 0

    # The prediction is performed without accumulating the gradient descent and without updating the weights of the model
    with torch.no_grad():
        for batch_id, batch in enumerate(dl_data,1):
            print(f"            Processing batch {batch_id} of {len(dl_data)}")
            # Extract the inputs, the attention masks and the targets from the batch
            src_input = batch[0].to(device)
            src_masks = batch[1].to(device)
            tgt_out = batch[2].to(device)

            # Train the model
            print(f"                Model predictions...")
            outputs = model(
                input_ids=src_input,
                attention_mask=src_masks,
                labels=tgt_out
            )
            # Exctract the predicted values and the expected output
            predicted = np.array(
                tokenizer.batch_decode(
                    torch.argmax(torch.softmax(outputs.logits, dim=-1), dim=-1),
                    skip_special_tokens=True
                )
            )
            expected_out = np.array(
                tokenizer.batch_decode(
                    tgt_out,
                    skip_special_tokens=True
                )
            )
            # Update the counter of the predictions
            prediction_results = (predicted == expected_out)
            # Compute statistics
            for idx, result in enumerate(prediction_results):
                stats = {
                    "id": id_counter,
                    "output": predicted[idx],
                    "correct": str(result),
                    "tokenClass": expected_out[idx]
                }
                id_counter += 1
                prediction_stats.append(stats)
        return prediction_stats


def pre_processing(
        df_dataset,
        tokenizer
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

    # Keep only a single instance for each combination of oracleId and oracleSoFar
    df_dataset = df_dataset[df_dataset['label']=='True']
    # Map tokenClassesSoFar into string
    df_dataset["tokenClassesSoFar"] = df_dataset["tokenClassesSoFar"].apply(lambda x: "[" + " ".join(x) + "]")
    df_dataset['tokenClass'] = df_dataset['tokenClass'].astype('string')
    df_dataset['tokenClassesSoFar'] = df_dataset['tokenClassesSoFar'].astype('string')
    # Define the new order of columns
    new_columns_order = [
        'token', 'tokenInfo', 'tokenClass', 'oracleSoFar', 'tokenClassesSoFar', 'javadocTag', 'oracleType',
        'packageName', 'className', 'methodJavadoc', 'methodSourceCode', 'classJavadoc', 'classSourceCode',
        'projectName', 'oracleId', 'label'
    ]
    # Reindex the DataFrame with the new order
    df_dataset = df_dataset.reindex(columns=new_columns_order)
    # Group the rows by 'oracleId' and 'oracleSoFar'
    df_grouped = df_dataset.groupby(['oracleId', 'oracleSoFar'])
    # Create an empty dictionary to store the separate datasets
    datasets = []
    # Iterate through the groups and assign them to separate datasets
    for identifier, group_data in df_grouped:
        # Delete the tgt labels from the input dataset, and others less relevant columns
        group_data = group_data.drop(['label', 'oracleId', 'projectName', 'classJavadoc', 'classSourceCode'], axis=1)
        group_data = group_data.drop(['token', 'tokenInfo'], axis=1)
        # Get the list of target values from the dataframe
        tgt = group_data["tokenClass"].values
        group_data = group_data.drop(['tokenClass'], axis=1)
        df_src_concat = group_data.apply(lambda row: tokenizer.sep_token.join(row.values), axis=1)
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
            pad_to_max_length=True,
            truncation=True,
            return_tensors="pt"
        )
        t_tgt_dict = tokenizer.batch_encode_plus(
            targets,
            max_length=8,
            pad_to_max_length=True,
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
        checkpoint_path: str
):
    """
    The main method instantiate the model and execute the training and testing phases.
    """
    # Connect to device
    print("Connect to device")
    device = utils.connect_to_device(DeviceType.GPU)

    # list of partial dataframes
    dfs = []
    # datasets path
    oracles_dataset = os.path.join(".","dataset","token-classes-dataset")
    # collects partial dataframes from oracles
    for file_name in os.listdir(oracles_dataset):
        if "gs-core" in file_name:
            print(file_name)
            df = pd.read_json(os.path.join(oracles_dataset, file_name))
            dfs.append(df)
    df_dataset = pd.concat(dfs)

    print("Setup model")
    # Setup tokenizer
    tokenizer = RobertaTokenizer.from_pretrained('Salesforce/codet5-base')
    # Setup model
    config = T5Config.from_pretrained('Salesforce/codet5-base')
    model = T5ForConditionalGeneration.from_pretrained('Salesforce/codet5-base', config=config)
    model.to(device)
    checkpoint = torch.load(checkpoint_path)
    model.load_state_dict(checkpoint['model_state_dict'])

    print("Pre-processing dataset")
    # Pre-processing data
    datasets = pre_processing(df_dataset, tokenizer)
    # Process the data
    t_datasets = tokenize_datasets(datasets, tokenizer)
    print("Start predictions")
    predictions_stats = {}

    model_stats = {
        "totals": len(t_datasets),
        "false_positives": 0,
        "false_negatives": 0,
        "true_positives": 0,
        "true_negatives": 0,
        "confidence_score": 0,
        "ones": []
    }

    for idx, t_dataset in enumerate(t_datasets,1):
        identifier, t_data = t_dataset
        print(f"Parsing oracle group {idx} of  {len(t_datasets)}")
        t_t_data = TensorDataset(*t_data)
        dl_data= DataLoader(
            t_t_data,
            batch_size=32
        )
        if not str(identifier[0]) in predictions_stats:
            predictions_stats[str(identifier[0])] = {}
        p_stats = predict(device, model, dl_data, tokenizer)
        predictions_stats[str(identifier[0])][identifier[1] if not identifier[1] == "" else "_"] = p_stats
        ones_false = 0
        ones_true = 0
        correct_found = False
        for p_s in p_stats:
            if not p_s["correct"] == 'True':
                    model_stats["false_positives"] += 1
            else:
                ones_true += 1
                model_stats["true_positives"] += 1
        if (not ones_true == 1) or ones_false > 0:
            model_stats["ones"].append((str(identifier[0]),str(identifier[1]),ones_true,ones_false,correct_found))
    utils.export_stats("./gs_core_test_classes_predictions_stats_decoder_category.json", predictions_stats)
    utils.export_stats("./gs_core_test_classes_model_stats_decoder_category.json", model_stats)
    del model



if __name__ == "__main__":
    # Parse arguments
    parser = argparse.ArgumentParser()
    parser.add_argument(
        "--checkpoint_path",
        default=None,
        type=str,
        required=True,
        help="The directory of the dataset."
    )
    args = parser.parse_args()
    main(args.checkpoint_path)