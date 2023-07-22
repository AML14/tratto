import os.path
import pandas as pd
import pytest
from transformers import AutoTokenizer

from src.processors.DataProcessorDecoder import DataProcessorDecoder

@pytest.fixture(scope='function')
def data_processor(
        arg_data_dir,
        arg_test_ratio,
        tokenizer,
        arg_classification_type,
        arg_tratto_model_type,
        arg_folds
):
    data_processor = DataProcessorDecoder(
        arg_data_dir,
        arg_test_ratio,
        tokenizer,
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
        arg_classification_type,
        arg_tratto_model_type,
        arg_folds
):
    data_processor = DataProcessorDecoder(
        arg_data_dir_single_datapoint,
        arg_test_ratio,
        tokenizer,
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
        arg_classification_type,
        arg_tratto_model_type,
        arg_folds
):
    data_processor = DataProcessorDecoder(
        arg_data_dir_single_datapoint,
        arg_test_ratio,
        tokenizer,
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
        arg_classification_type,
        arg_tratto_model_type,
        arg_folds
):
    data_processor = DataProcessorDecoder(
        arg_data_dir_ten_datapoints,
        arg_test_ratio,
        tokenizer,
        arg_classification_type,
        arg_tratto_model_type,
        arg_folds  # if folds = 1 no cross-validation is performed
    )
    return data_processor

@pytest.fixture(scope='function')
def tokenizer(arg_tokenizer_name):
    if arg_tokenizer_name == 'Salesforce/codet5p-220m':
        tokenizer = AutoTokenizer.from_pretrained(arg_tokenizer_name)
    return tokenizer