import os.path
import pandas as pd
import pytest
from transformers import AutoTokenizer

from src.processors.DataProcessor import DataProcessor
from src.types.TrattoModelType import TrattoModelType
from src.utils.utils import import_json
from tests import utils


@pytest.fixture(scope='session')
def data_processor(
        arg_dataset_path,
        tokenizer,
        arg_transformer_type,
        arg_classification_type,
        arg_tratto_model_type,
        arg_pre_processing
):
    if utils.skipTest(arg_dataset_path, arg_transformer_type, arg_tratto_model_type, arg_classification_type):
        pytest.skip(f"Skipping test because of the invalid combination of the arguments: {arg_dataset_path.split('/')[-1]} - {arg_tratto_model_type}")
    data_processor = DataProcessor(
        os.path.join(arg_dataset_path, "small", "train"),
        os.path.join(arg_dataset_path, "small", "validation"),
        tokenizer,
        arg_transformer_type,
        arg_classification_type,
        arg_tratto_model_type,
        arg_pre_processing
    )
    return data_processor


@pytest.fixture(scope='function')
def df_projects(
        arg_dataset_path,
        arg_transformer_type,
        arg_classification_type,
        arg_tratto_model_type
):
    if utils.skipTest(arg_dataset_path, arg_transformer_type, arg_tratto_model_type, arg_classification_type):
        pytest.skip(
            f"Skipping test because of the invalid combination of the arguments: {arg_dataset_path.split('/')[-1]} - {arg_tratto_model_type}")
    # list of partial dataframes
    dfs = []
    # Collects partial dataframes
    for dataset_type in ["train", "validation"]:
        oracles_dataset = os.path.join(arg_dataset_path, 'ten', dataset_type)
        for file_name in os.listdir(oracles_dataset):
            df = pd.read_json(os.path.join(oracles_dataset, file_name))
            if arg_tratto_model_type == TrattoModelType.ORACLES:
                df["label"] = df["oracle"].apply(lambda o: "False" if o == ";" else "True")
                if "oracleId" not in df.columns:
                    if "id" in df.columns:
                        df.rename(columns={'id': 'oracleId'}, inplace=True)
            dfs.append(df)
    df_dataset = pd.concat(dfs)
    df_dataset.reset_index(drop=True, inplace=True)
    return df_dataset


@pytest.fixture(scope='session')
def data_processor_single_datapoint(
        arg_dataset_path,
        tokenizer,
        arg_transformer_type,
        arg_classification_type,
        arg_tratto_model_type,
        arg_pre_processing
):
    data_processor = DataProcessor(
        os.path.join(arg_dataset_path, "single", "train"),
        os.path.join(arg_dataset_path, "single", "validation"),
        tokenizer,
        arg_transformer_type,
        arg_classification_type,
        arg_tratto_model_type,
        arg_pre_processing
    )
    return data_processor

@pytest.fixture(scope='session')
def data_processor_ten_datapoints(
        arg_dataset_path,
        tokenizer,
        arg_transformer_type,
        arg_classification_type,
        arg_tratto_model_type,
        arg_pre_processing
):
    if utils.skipTest(arg_dataset_path, arg_transformer_type, arg_tratto_model_type, arg_classification_type):
        pytest.skip(f"Skipping test because of the invalid combination of the arguments: {arg_dataset_path.split('/')[-1]} - {arg_tratto_model_type}")
    data_processor = DataProcessor(
        os.path.join(arg_dataset_path, "ten", "train"),
        os.path.join(arg_dataset_path, "ten", "validation"),
        tokenizer,
        arg_transformer_type,
        arg_classification_type,
        arg_tratto_model_type,
        arg_pre_processing
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