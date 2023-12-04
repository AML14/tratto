import pytest
import os

from src.types.ClassificationType import ClassificationType
from src.types.TransformerType import TransformerType
from src.types.TrattoModelType import TrattoModelType


@pytest.fixture(
    scope='session',
    params=[
        os.path.join(os.path.dirname(__file__), '..', 'dataset', 'token-classes-dataset'),
        os.path.join(os.path.dirname(__file__), '..', 'dataset', 'token-values-dataset')
    ]
)
def arg_dataset_path(request):
    return request.param

@pytest.fixture(
    scope='session',
)
def arg_dataset_path_single_datapoint():
    return os.path.join(os.path.dirname(__file__), '..', 'dataset', 'single-datapoint-dataset')

@pytest.fixture(
    scope='session',
)
def arg_dataset_path_ten_datapoint():
    return os.path.join(os.path.dirname(__file__), '..', 'dataset', 'ten-datapoints-dataset')

@pytest.fixture(
    scope='session',
)
def arg_dataset_path_server():
    return os.path.join(os.path.dirname(__file__), '..', 'dataset', 'server-dataset')

@pytest.fixture(
    scope='session',
    params=['Salesforce/codet5p-220m']
)
def arg_tokenizer_name(request):
    return request.param

@pytest.fixture(
    scope='session',
    params=[
        TrattoModelType.TOKEN_CLASSES,
        TrattoModelType.TOKEN_VALUES
    ]
)
def arg_tratto_model_type(request):
    return request.param

@pytest.fixture(
    scope='session',
    params=[
        ClassificationType.CATEGORY_PREDICTION,
        ClassificationType.LABEL_PREDICTION
    ]
)
def arg_classification_type(request):
    return request.param

@pytest.fixture(
    scope='session',
    params=[
        TransformerType.ENCODER,
        TransformerType.DECODER
    ]
)
def arg_transformer_type(request):
    return request.param