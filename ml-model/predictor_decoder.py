import os.path

import torch
import numpy as np
import pandas as pd
from torch.utils.data import DataLoader, TensorDataset
from transformers import PreTrainedModel, PreTrainedTokenizer
from typing import Type
from src.utils import utils


def predict_next(
        device,
        model,
        dl_src,
        tokenizer
):
    # Model in evaluation mode
    model.eval()
    # The prediction is performed without accumulating the gradient descent and without updating the weights of the model
    with torch.no_grad():
        for batch_id, batch in enumerate(dl_src,1):
            print(f"Processing batch {batch_id} of {len(dl_src)}")
            # Extract the inputs, the attention masks and the targets from the batch
            src_input = batch[0].to(device)
            src_masks = batch[1].to(device)
            # Generate token class/value
            out = model.generate(
                input_ids=src_input,
                attention_mask=src_masks
            )
            # Decode the predicted token class/value
            next = np.array(
                tokenizer.batch_decode(
                    out,
                    skip_special_tokens=True
                )
            )
            assert len(next) == 1
        # Return next token class/value
        return next[0]


def pre_process_dataset(
        df_dataset
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
    # Map token class names
    _, value_mappings = utils.import_json(
        os.path.join(
            os.path.abspath(__file__),
            '..', 'resources',
            'tokenClassesValuesMapping.json'
        )
    )
    # Replace the values in the DataFrame column
    df_dataset['tokenClass'] = df_dataset['tokenClass'].replace(value_mappings)
    # Map token classes so far to new values and transform it from array to string
    df_dataset["tokenClassesSoFar"] = df_dataset["tokenClassesSoFar"].apply(lambda x: "[ " + " ".join([value_mappings[y] for y in x]) + " ]")
    # Delete spurious columns for predicting the next token class
    df_dataset = df_dataset.drop(['oracleId', 'projectName', 'classJavadoc', 'classSourceCode'], axis=1)
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
    df_dataset["eligibleTokenClasses"] = df_dataset["eligibleTokenClasses"].apply(lambda x: "[ " + " ".join(x) + " ]")
    # Set type of dataframe columns
    df_dataset['tokenClass'] = df_dataset['tokenClass'].astype('string')
    df_dataset['tokenClassesSoFar'] = df_dataset['tokenClassesSoFar'].astype('string')
    df_dataset['eligibleTokenClasses'] = df_dataset['eligibleTokenClasses'].astype('string')
    # Define the new order of columns
    new_columns_order = [
        'token', 'tokenInfo', 'tokenClass', 'oracleSoFar', 'tokenClassesSoFar', 'eligibleTokenClasses',
        'javadocTag', 'oracleType', 'packageName', 'className', 'methodJavadoc', 'methodSourceCode'
    ]
    # Reindex the DataFrame with the new order
    df_dataset = df_dataset.reindex(columns=new_columns_order)
    # Delete spurious columns for predicting the next token class
    df_dataset = df_dataset.drop(['token', 'tokenInfo', 'tokenClass'], axis=1)
    # Delete duplicates
    df_dataset.drop_duplicates()
    assert len(df_dataset) == 1
    # Generate string datapoints concatenating the fields of each column and separating them with a special token
    df_src_concat = df_dataset.apply(lambda row: tokenizer.sep_token.join(row.values), axis=1)
    # The pandas dataframe is transformed in a list of strings: each string is an input to the model
    src = df_src_concat.to_numpy().tolist()
    # Return source input and token classes dictionary
    return src

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
    # Define the new order of columns
    new_columns_order = [
        'tokenClass', 'token', 'tokenInfo', 'oracleSoFar', 'eligibleTokens', 'javadocTag', 'oracleType',
        'projectName', 'packageName', 'className', 'methodJavadoc', 'methodSourceCode'
    ]
    # Reindex the DataFrame with the new order
    df_dataset = df_dataset.reindex(columns=new_columns_order)
    # Delete spurious columns for predicting the next token class
    df_dataset = df_dataset.drop(['token'], axis=1)
    # Delete duplicates
    df_dataset.drop_duplicates()
    assert len(df_dataset) == 1
    # Generate string datapoints concatenating the fields of each column and separating them with a special token
    df_src_concat = df_dataset.apply(lambda row: tokenizer.sep_token.join(row.values), axis=1)
    # The pandas dataframe is transformed in a list of strings: each string is an input to the model
    src = df_src_concat.to_numpy().tolist()
    # Return source input and token classes dictionary
    return src


def tokenize_input(
        src,
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

    return (t_inputs, t_attention_masks)


def next_token(
        device,
        filename: str,
        model_classes: Type[PreTrainedModel],
        model_values: Type[PreTrainedModel],
        tokenizer: PreTrainedTokenizer
):
    # Collect partial dataframes from oracles
    print("Predict next token")
    df_dataset = pd.read_json(filename)
    # Pre-process dataset
    print("Pre-processing dataset")
    df_dataset = pre_process_dataset(df_dataset)
    # Get model token classes input
    print("Get model token classes input")
    src_token_classes = get_input_model_classes(df_dataset, tokenizer)
    # Tokenize input
    print("Tokenize model token classes input")
    t_src_token_classes = tokenize_input(src_token_classes, tokenizer)
    # Generate data loader
    t_t_src_token_classes = TensorDataset(*t_src_token_classes)
    dl_src_token_classes = DataLoader(
        t_t_src_token_classes,
        batch_size=32
    )
    # Predict next token class
    print("Predict next token class")
    next_token_class = predict_next(device, model_classes, dl_src_token_classes, tokenizer)
    # Get model token values input
    print("Get model token values input")
    src_token_values = get_input_model_values(df_dataset, next_token_class, tokenizer)
    # Tokenize input
    print("Tokenize model token values input")
    t_src_token_values = tokenize_input(src_token_values, tokenizer)
    # Generate data loader
    t_t_src_token_values = TensorDataset(*t_src_token_values)
    dl_src_token_values = DataLoader(
        t_t_src_token_values,
        batch_size=32
    )
    # Predict next token value
    print("Predict next token value")
    next_token_value = predict_next(device, model_values, dl_src_token_values, tokenizer)
    # Return next token
    return next_token_value





