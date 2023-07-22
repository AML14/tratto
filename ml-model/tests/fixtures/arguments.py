import pytest
import os

from src.types.ClassificationType import ClassificationType
from src.types.TrattoModelType import TrattoModelType
from tests.fixtures.factory import fixture_factory


@pytest.fixture(
    scope='session',
    params=[
        os.path.join(os.path.dirname(__file__), '..', 'dataset', 'token-classes-dataset'),
        os.path.join(os.path.dirname(__file__), '..', 'dataset', 'token-values-dataset')
    ]
)
def arg_data_dir(request):
    return request.param

@pytest.fixture(
    scope='session',
)
def arg_data_dir_single_datapoint():
    return os.path.join(os.path.dirname(__file__), '..', 'dataset', 'single-datapoint-dataset')

@pytest.fixture(
    scope='session',
)
def arg_data_dir_ten_datapoints():
    return os.path.join(os.path.dirname(__file__), '..', 'dataset', 'ten-datapoints-dataset')

@pytest.fixture(
    scope='session'
)
def arg_test_ratio():
    return 0.1

@pytest.fixture(
    scope='session',
    params=['Salesforce/codet5p-220m']
)
def arg_tokenizer_name(request):
    return request.param

@pytest.fixture(
    scope='session',
    params=[1]
)
def arg_folds(request):
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