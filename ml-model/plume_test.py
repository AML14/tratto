import os

import torch
import numpy as np
import pandas as pd
from sklearn.model_selection import train_test_split
from torch.nn import CrossEntropyLoss
from torch.utils.data import DataLoader, WeightedRandomSampler, TensorDataset
from torch.nn import Softmax
from transformers import AdamW, get_linear_schedule_with_warmup, RobertaTokenizer, RobertaConfig, \
    RobertaForSequenceClassification

from src.types.ClassificationType import ClassificationType
from src.types.DatasetType import DatasetType
from src.types.DeviceType import DeviceType
from src.types.TrattoModelType import TrattoModelType
from src.model.OracleTrainer import OracleTrainer
from src.utils import logger
from src.processors.DataProcessor import DataProcessor
from src.utils import utils


def map_column_values_to_one_shot_vectors(
        df_dataset,
        column_name
):
    # Get unique values
    unique_values = np.unique(df_dataset[column_name])
    # Create a dictionary to map string values to their corresponding vector
    mapping = {}
    # Populate the dictionary and generate the one-shot vectors.
    for i, value in enumerate(unique_values):
        vector = np.zeros(len(unique_values))
        vector[i] = 1.0
        mapping[value] = list(vector)
    # Return the dictionary
    return mapping


def predict(
        device,
        model,
        dl_data,
        classes_ids
):
    # Model in evaluation mode
    model.eval()

    softmax = Softmax(dim=1)
    ids_classes = {i: k for k, i in classes_ids.items()}

    # Accumulate predictions and labels
    all_predictions = []
    all_labels = []

    prediction_stats = []
    id_counter = 0

    # The prediction is performed without accumulating the gradient descent and without updating the weights of the model
    with torch.no_grad():
        for batch_id, batch in enumerate(dl_data,1):
            print(f"Processing batch {batch_id} of {len(dl_data)}")
            # Extract the inputs, the attention masks and the targets from the batch
            src_input = batch[0].to(device)
            masks_input = batch[1].to(device)
            tgt_out = batch[2].to(device)
            tgt_classes = list(map(lambda i: ids_classes[i], batch[3].numpy()))
            print(f"Model predictions...")
            # Feed the model
            outputs = model(src_input, masks_input)[0]
            confidence_scores = softmax(outputs)
            # Exctract the predicted values and the expected output
            with torch.no_grad():
                _, predicted = outputs.max(1)
                _, expected_out = tgt_out.max(1)
            prediction_results = (predicted == expected_out)

            for idx, result in enumerate(prediction_results):
                stats = {
                    "id": id_counter,
                    "output": predicted[idx].detach().cpu().numpy().tolist(),
                    "correct": result.detach().cpu().numpy().tolist(),
                    "confidence_score": confidence_scores[idx].detach().cpu().numpy().tolist(),
                    "tokenClass": tgt_classes[idx]
                }
                id_counter += 1
                prediction_stats.append(stats)
        return prediction_stats


def pre_processing(
        df_dataset,
        tokenizer
):
    # Drop column id (it is not relevant for training the model)
    df_dataset = df_dataset.drop(['id'], axis=1)
    # Map empty cells to empty strings
    df_dataset.fillna('', inplace=True)
    # Assign type to each column
    df_dataset.astype({
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
    # Generate the mapping of the target column unique values to the corresponding one-shot representations
    tgt_map = map_column_values_to_one_shot_vectors(df_dataset, "label")
    classes_ids = {k: i for i, k in enumerate(df_dataset["tokenClass"].unique())}
    # Group the rows by 'oracleId' and 'oracleSoFar'
    df_grouped = df_dataset.groupby(['oracleId', 'oracleSoFar'])
    # Create an empty dictionary to store the separate datasets
    datasets = []
    # Iterate through the groups and assign them to separate datasets
    for identifier, group_data in df_grouped:
        # Specify the type of each column in the dataset
        group_data["tokenClassesSoFar"] = group_data["tokenClassesSoFar"].apply(lambda x: "[" + " ".join(x) + "]")
        group_data['tokenClass'] = group_data['tokenClass'].astype('string')
        group_data['tokenClassesSoFar'] = group_data['tokenClassesSoFar'].astype('string')
        # Define the new order of columns
        new_columns_order = [
            'token','tokenInfo','tokenClass','oracleSoFar','tokenClassesSoFar','javadocTag','label','oracleType','projectName',
            'packageName','className','oracleId','methodJavadoc','methodSourceCode','classJavadoc','classSourceCode'
        ]
        # Reindex the DataFrame with the new order
        group_data = group_data.reindex(columns=new_columns_order)
        # Delete the tgt labels from the input dataset, and others less relevant columns
        df_src = group_data.drop(['label', 'oracleId', 'projectName', 'classJavadoc', 'classSourceCode'], axis=1)
        # Remove token and tokenInfo
        df_src = df_src.drop(['token', 'tokenInfo'], axis=1)

        df_src_concat = df_src.apply(lambda row: tokenizer.sep_token.join(row.values), axis=1)
        # The pandas dataframe is transformed in a list of strings: each string is an input to the model
        src = df_src_concat.to_numpy().tolist()
        # Get the list of target values from the dataframe
        tgt = group_data["label"].values
        datasets.append((identifier,src, tgt))
    return datasets, tgt_map, classes_ids


def tokenize_datasets(
        datasets,
        tgt_map,
        tokenizer,
        classes_ids
):
    t_datasets = []

    for identifier, inputs, targets in datasets:
        # Tokenize the inputs datapoints
        inputs_dict = tokenizer(
            inputs,
            padding='max_length',
            truncation=True
        )
        # Transform the list into a tensor stack
        t_inputs = torch.stack([torch.tensor(ids) for ids in inputs_dict['input_ids']])
        t_attention_masks = torch.stack([torch.tensor(mask) for mask in inputs_dict['attention_mask']])
        # Map targets value into one-shot vectors
        targets_one_shot = list(map(lambda t: tgt_map[t], targets))
        # Transform the targets into a tensor list
        targets_tensor = torch.tensor(targets_one_shot)
        # Generate the tokenized dataset
        t_data = (t_inputs, t_attention_masks, targets_tensor)

        targets_classes = list(map(lambda i: i.split(tokenizer.sep_token)[0], inputs))
        targets_classes_tensor = torch.tensor(list(map(lambda l: classes_ids[l], targets_classes)))
        # Generate the tokenized dataset
        t_dataset = (t_inputs, t_attention_masks, targets_tensor, targets_classes_tensor)
        t_datasets.append((identifier,t_dataset))
    return t_datasets


def main():
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
        if "plume-lib" in file_name:
            df = pd.read_json(os.path.join(oracles_dataset, file_name))
            dfs.append(df)
    df_dataset = pd.concat(dfs)

    print("Setup model")
    # Setup tokenizer
    tokenizer = RobertaTokenizer.from_pretrained('roberta-base')
    # Setup model
    config = RobertaConfig.from_pretrained('roberta-base')
    model = RobertaForSequenceClassification.from_pretrained('roberta-base',config=config)
    model.to(device)
    checkpoint = torch.load(os.path.join(
        "output_token_classes",
        "checkpoints",
        "lr_1e-05",
        "batch_8",
        "epochs_8",
        "checkpoint_1_1.pt"
    ))
    model.load_state_dict(checkpoint['model_state_dict'])

    print("Pre-processing dataset")
    # Pre-processing data
    datasets, tgt_map, classes_ids = pre_processing(df_dataset, tokenizer)
    # Process the data
    t_datasets = tokenize_datasets(datasets, tgt_map, tokenizer, classes_ids)
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
        p_stats = predict(device, model, dl_data, classes_ids)
        predictions_stats[str(identifier[0])][identifier[1] if not identifier[1] == "" else "_"] = p_stats
        ones = 0
        for p_s in p_stats:
            if p_s["correct"] == False:
                if p_s["output"] == 0:
                    model_stats["false_negatives"] +=1
                else:
                    model_stats["false_positives"] += 1
                    ones += 1
                    for other_p_s in p_stats:
                        if not other_p_s["id"] == p_s["id"]:
                            if other_p_s["correct"] and other_p_s["output"] == 1 and other_p_s["confidence_score"][1] > p_s["confidence_score"][1]:
                                model_stats["confidence_score"] += 1
            else:
                if p_s["output"] == 0:
                    model_stats["true_negatives"] += 1
                else:
                    model_stats["true_positives"] += 1
    utils.export_stats("./plume_test_predictions_stats.json", predictions_stats)
    utils.export_stats("./plume_test_model_stats.json", model_stats)



if __name__ == "__main__":
    main()