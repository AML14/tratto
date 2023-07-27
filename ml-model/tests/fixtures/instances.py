import os.path
import pandas as pd
import pytest
from transformers import AutoTokenizer

from src.processors.DataProcessor import DataProcessor
from src.utils.utils import import_json


@pytest.fixture(scope='function')
def data_processor(
        arg_data_dir,
        arg_test_ratio,
        tokenizer,
        arg_transformer_type,
        arg_classification_type,
        arg_tratto_model_type,
        arg_folds
):
    data_processor = DataProcessor(
        arg_data_dir,
        arg_test_ratio,
        tokenizer,
        arg_transformer_type,
        arg_classification_type,
        arg_tratto_model_type,
        arg_folds  # if folds = 1 no cross-validation is performed
    )
    return data_processor

@pytest.fixture(scope='function')
def data_processor_single_datapoint(
        arg_data_dir_single_datapoint,
        arg_test_ratio,
        tokenizer,
        arg_transformer_type,
        arg_classification_type,
        arg_tratto_model_type,
        arg_folds
):
    data_processor = DataProcessor(
        arg_data_dir_single_datapoint,
        arg_test_ratio,
        tokenizer,
        arg_transformer_type,
        arg_classification_type,
        arg_tratto_model_type,
        arg_folds
    )
    return data_processor

@pytest.fixture(scope='function')
def data_processor_single_datapoint(
        arg_data_dir_single_datapoint,
        arg_test_ratio,
        tokenizer,
        arg_transformer_type,
        arg_classification_type,
        arg_tratto_model_type,
        arg_folds
):
    data_processor = DataProcessor(
        arg_data_dir_single_datapoint,
        arg_test_ratio,
        tokenizer,
        arg_transformer_type,
        arg_classification_type,
        arg_tratto_model_type,
        arg_folds
    )
    return data_processor

@pytest.fixture(scope='function')
def data_processor_ten_datapoints(
        arg_data_dir_ten_datapoints,
        arg_test_ratio,
        tokenizer,
        arg_transformer_type,
        arg_classification_type,
        arg_tratto_model_type,
        arg_folds
):
    data_processor = DataProcessor(
        arg_data_dir_ten_datapoints,
        arg_test_ratio,
        tokenizer,
        arg_transformer_type,
        arg_classification_type,
        arg_tratto_model_type,
        arg_folds  # if folds = 1 no cross-validation is performed
    )
    return data_processor

@pytest.fixture(scope='function')
def df_dataset_server(
        arg_data_dir_server
):
    assert len(os.listdir(arg_data_dir_server)) == 1
    file_name = os.listdir(arg_data_dir_server)[0]
    _, dataset = import_json(os.path.join(arg_data_dir_server, file_name))
    df_dataset = pd.DataFrame(dataset["datapoints"])
    return df_dataset

@pytest.fixture(scope='function')
def next_tokenClass_server(
        arg_data_dir_server
):
    assert len(os.listdir(arg_data_dir_server)) == 1
    file_name = os.listdir(arg_data_dir_server)[0]
    _, dataset = import_json(os.path.join(arg_data_dir_server, file_name))
    next_tokenClass = dataset["next_tokenClass"]
    return next_tokenClass


@pytest.fixture(scope='function')
def tokenizer(arg_tokenizer_name):
    if arg_tokenizer_name == 'Salesforce/codet5p-220m':
        tokenizer = AutoTokenizer.from_pretrained(arg_tokenizer_name)
    return tokenizer