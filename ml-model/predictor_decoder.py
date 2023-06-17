import os

import torch
import numpy as np
import pandas as pd
from torch.utils.data import DataLoader, TensorDataset
from torch.nn import Softmax
from transformers import RobertaTokenizer, RobertaConfig, RobertaForSequenceClassification, PreTrainedModel, \
    PreTrainedTokenizer
from src.types.DeviceType import DeviceType
from src.utils import utils
from typing import Type


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


def predict_token_class(
        device,
        model,
        dl_data,
        classes_ids
):
    # Model in evaluation mode
    model.eval()

    softmax = Softmax(dim=1)
    ids_classes = {i: k for k, i in classes_ids.items()}

    predictions = []
    id_counter = 0

    # The prediction is performed without accumulating the gradient descent and without updating the weights of the model
    with torch.no_grad():
        for batch_id, batch in enumerate(dl_data,1):
            print(f"Processing batch {batch_id} of {len(dl_data)}")
            # Extract the inputs, the attention masks and the targets from the batch
            src_input = batch[0].to(device)
            masks_input = batch[1].to(device)
            tokens_classes = batch[2].to(device)
            print(f"Model predictions...")
            # Feed the model
            outputs = model(src_input, masks_input)[0]
            confidence_scores = softmax(outputs)
            # Exctract the predicted values and the expected output
            with torch.no_grad():
                _, predicted = outputs.max(1)

            for idx, result in enumerate(predicted):
                prediction = {
                    "id": id_counter,
                    "output": result[idx].detach().cpu().numpy().tolist(),
                    "confidence_score": confidence_scores[idx].detach().cpu().numpy().tolist()[1],
                    "tokenClass": tokens_classes[idx]
                }
                id_counter += 1
                predictions.append(prediction)
            # Filter the predictions with output = 1
            filtered_predictions = [p for p in predictions if p['output'] == 1]
            # Sort the predictions by confidence score
            sorted_predictions = sorted(filtered_predictions, key=lambda p: p['confidence_score'], reverse=True)
        return sorted_predictions


def pre_processing_model_classes(
        df_dataset,
        tokenizer
):
    # Drop column id (it is not relevant for training the model)
    df_dataset = df_dataset.drop(['id'], axis=1)
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
    # Parse array into string and assign type
    df_dataset["tokenClassesSoFar"] = df_dataset["tokenClassesSoFar"].apply(lambda x: "[" + " ".join(x) + "]")
    df_dataset['tokenClassesSoFar'] = df_dataset['tokenClassesSoFar'].astype('string')
    # Map classes assigning integer ids (tensor cannot contain strings, only numbers)
    classes_ids = {k: i for i, k in enumerate(df_dataset["tokenClass"].unique())}
    # Define the new order of columns
    new_columns_order = [
        'tokenClass','oracleSoFar', 'tokenClassesSoFar', 'oracleType', 'packageName', 'className', 'javadocTag',
        'methodSourceCode', 'methodJavadoc', 'classJavadoc', 'classSourceCode', 'oracleId', 'projectName',
        'token', 'tokenInfo'
    ]
    # Reindex the DataFrame with the new order
    df_dataset = df_dataset.reindex(columns=new_columns_order)
    # Delete spurious columns for predicting the next token class
    df_src = df_dataset.drop(['oracleId', 'projectName', 'classJavadoc', 'classSourceCode','token', 'tokenInfo'], axis=1)
    # Get the token class of each datapoints (it represents the output to retrieve whenever the model returns 1
    token_classes = df_src["tokenClass"].to_numpy().tolist()
    # Generate string datapoints concatenating the fields of each column and separating them with a special token
    df_src_concat = df_src.apply(lambda row: tokenizer.sep_token.join(row.values), axis=1)
    # The pandas dataframe is transformed in a list of strings: each string is an input to the model
    src = df_src_concat.to_numpy().tolist()
    # Return source input and token classes dictionary
    return src, token_classes, classes_ids


def tokenize_input(
        src,
        token_classes,
        tokenizer
):
    # Tokenize the inputs datapoints
    inputs_dict = tokenizer(
        src,
        padding='max_length',
        truncation=True
    )
    # Transform the list into a tensor stack
    t_inputs = torch.stack([torch.tensor(ids) for ids in inputs_dict['input_ids']])
    t_attention_masks = torch.stack([torch.tensor(mask) for mask in inputs_dict['attention_mask']])

    return (t_inputs, t_attention_masks, token_classes)



def nextToken(
        device,
        filename: str,
        model_classes: Type[PreTrainedModel],
        model_values: Type[PreTrainedModel],
        tokenizer: PreTrainedTokenizer
):
    print("Predict next token")
    # collects partial dataframes from oracles
    df_dataset = pd.read_json(filename)

    print("Pre-processing dataset")
    # Pre-processing data
    src, token_classes, classes_ids = pre_processing_model_classes(df_dataset, tokenizer)
    # Process the data
    t_dataset = tokenize_input(src, token_classes, tokenizer)
    # Generate data loader
    t_t_data = TensorDataset(*t_dataset)
    dl_data= DataLoader(
        t_t_data,
        batch_size=32
    )
    print("Predict next token class")
    # Predict next token class
    predictions = predict_token_class(device, model_classes, dl_data, classes_ids)





