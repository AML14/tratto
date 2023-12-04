import os.path
import pandas as pd
import pytest
from transformers import AutoTokenizer

from src.processors.DataProcessor import DataProcessor
from src.utils.utils import import_json


@pytest.fixture(scope='session')
def data_processor(
        arg_dataset_path,
        tokenizer,
        arg_transformer_type,
        arg_classification_type,
        arg_tratto_model_type
):
    data_processor = DataProcessor(
        os.path.join(arg_dataset_path, "train"),
        os.path.join(arg_dataset_path, "validation"),
        tokenizer,
        arg_transformer_type,
        arg_classification_type,
        arg_tratto_model_type
    )
    return data_processor

@pytest.fixture(scope='function')
def df_projects(
        arg_dataset_path_ten_datapoint
):
    # list of partial dataframes
    dfs = []
    # Collects partial dataframes
    for dataset_type in ["train", "validation"]:
        oracles_dataset = os.path.join(arg_dataset_path_ten_datapoint, dataset_type)
        for file_name in os.listdir(arg_dataset_path_ten_datapoint):
            df = pd.read_json(os.path.join(oracles_dataset, file_name))
            dfs.append(df)
    df_dataset = pd.concat(dfs)
    df_dataset.reset_index(drop=True, inplace=True)
    return df_dataset


@pytest.fixture(scope='session')
def data_processor_single_datapoint(
        arg_dataset_path_single_datapoint,
        tokenizer,
        arg_transformer_type,
        arg_classification_type,
        arg_tratto_model_type
):
    data_processor = DataProcessor(
        os.path.join(arg_dataset_path_single_datapoint, "train"),
        os.path.join(arg_dataset_path_single_datapoint, "validation"),
        tokenizer,
        arg_transformer_type,
        arg_classification_type,
        arg_tratto_model_type
    )
    return data_processor

@pytest.fixture(scope='session')
def data_processor_ten_datapoints(
        arg_dataset_path_ten_datapoint,
        tokenizer,
        arg_transformer_type,
        arg_classification_type,
        arg_tratto_model_type
):
    data_processor = DataProcessor(
        os.path.join(arg_dataset_path_ten_datapoint, "train"),
        os.path.join(arg_dataset_path_ten_datapoint, "validation"),
        tokenizer,
        arg_transformer_type,
        arg_classification_type,
        arg_tratto_model_type
    )
    return data_processor

@pytest.fixture(scope='function')
def df_dataset_server(
        arg_dataset_path_server
):
    assert len(os.listdir(arg_dataset_path_server)) == 1
    file_name = os.listdir(arg_dataset_path_server)[0]
    _, dataset = import_json(os.path.join(arg_dataset_path_server, file_name))
    df_dataset = pd.DataFrame(dataset["datapoints"])
    return df_dataset

@pytest.fixture(scope='function')
def next_tokenClass_server(
        arg_dataset_path_server
):
    assert len(os.listdir(arg_dataset_path_server)) == 1
    file_name = os.listdir(arg_dataset_path_server)[0]
    _, dataset = import_json(os.path.join(arg_dataset_path_server, file_name))
    next_tokenClass = dataset["next_tokenClass"]
    return next_tokenClass


@pytest.fixture(scope='session')
def tokenizer(arg_tokenizer_name):
    if arg_tokenizer_name == 'Salesforce/codet5p-220m':
        tokenizer = AutoTokenizer.from_pretrained(arg_tokenizer_name)
    return tokenizer