import os.path

import pytest

from src.processors.DataProcessorDecoder import DataProcessorDecoder

@pytest.fixture(scope='session')
def data_processor(arg_data_dir):
    return os.path.join(arg_data_dir, "hola")
    """data_processor = DataProcessorDecoder(
        args.data_dir,
        args.test_ratio,
        tokenizer,
        classification_type,
        tratto_model_type,
        args.folds  # if folds = 1 no cross-validation is performed
    )"""