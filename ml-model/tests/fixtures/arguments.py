import pytest
import os

from src.types.ClassificationType import ClassificationType
from src.types.TransformerType import TransformerType
from src.types.TrattoModelType import TrattoModelType


@pytest.fixture(
    scope='session',
    params=[
        os.path.join(os.path.dirname(__file__), '..', 'dataset', 'token-classes-dataset'),
        os.path.join(os.path.dirname(__file__), '..', 'dataset', 'token-values-dataset'),
        os.path.join(os.path.dirname(__file__), '..', 'dataset', 'token-oracles-dataset')
    ]
)
def arg_dataset_path(request):
    return request.param

@pytest.fixture(
    scope='session',
    params=[
        os.path.join(os.path.dirname(__file__), '..', 'dataset', 'server-dataset', 'not-empty')
    ]
)
def arg_dataset_path_server(request):
    return request.param

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
        TrattoModelType.TOKEN_VALUES,
        TrattoModelType.ORACLES
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

@pytest.fixture(
    scope='session',
    params=[
        True
    ]
)
def arg_pre_processing(request):
    return request.param