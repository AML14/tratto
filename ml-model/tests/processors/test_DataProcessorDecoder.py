import os
import pytest
from src.types.ClassificationType import ClassificationType
from src.types.TrattoModelType import TrattoModelType
from src.utils import utils


def test_load_dataset_num_rows(
        data_processor,
        arg_data_dir
):
    datapoints_counter = 0
    for file_name in os.listdir(arg_data_dir):
        _, json_content = utils.import_json(os.path.join(arg_data_dir, file_name))
        datapoints_counter += len(json_content)
    df_dataset = getattr(data_processor, '_df_dataset')
    assert df_dataset.shape[0] == datapoints_counter

def test_compute_weights(
        data_processor
):
    data_processor.pre_processing()
    df_dataset = getattr(data_processor, '_df_dataset')
    columns = df_dataset.columns.tolist()
    for col in columns:
        weights = data_processor.compute_weights(col)
        assert pytest.approx(sum(weights), 0.01) == 1
        assert len(weights) == len(df_dataset[col].unique().tolist())

def test_get_ids_tgt_labels(
        data_processor,
        arg_classification_type,
        arg_tratto_model_type
):
    data_processor.pre_processing()
    ids_tgt_labels = data_processor.get_ids_tgt_labels()
    df_dataset = getattr(data_processor, '_df_dataset')
    if arg_classification_type == ClassificationType.CATEGORY_PREDICTION:
        if arg_tratto_model_type == TrattoModelType.TOKEN_CLASSES:
            dataset_tgt_labels = df_dataset['tokenClass'].unique().tolist()
        elif arg_tratto_model_type == TrattoModelType.TOKEN_VALUES:
            dataset_tgt_labels = df_dataset['token'].unique().tolist()
        else:
            assert False
        assert len(ids_tgt_labels.values()) == len(dataset_tgt_labels)
        for tgt_label in dataset_tgt_labels:
            assert tgt_label in list(ids_tgt_labels.values())
        for idx in range(len(dataset_tgt_labels)):
            assert idx in list(ids_tgt_labels.keys())

def test_get_ids_tgt_labels(
        data_processor,
        arg_classification_type,
        arg_tratto_model_type
):
    data_processor.pre_processing()
    ids_tgt_labels = data_processor.get_ids_tgt_labels()
    df_dataset = getattr(data_processor, '_df_dataset')
    if arg_classification_type == ClassificationType.CATEGORY_PREDICTION:
        if arg_tratto_model_type == TrattoModelType.TOKEN_CLASSES:
            dataset_tgt_labels = df_dataset['tokenClass'].unique().tolist()
        elif arg_tratto_model_type == TrattoModelType.TOKEN_VALUES:
            dataset_tgt_labels = df_dataset['token'].unique().tolist()
        else:
            assert False
    elif arg_classification_type == ClassificationType.LABEL_PREDICTION:
        dataset_tgt_labels = df_dataset['label'].unique().tolist()
    else:
        assert False
    assert len(ids_tgt_labels.values()) == len(dataset_tgt_labels)
    for tgt_label in dataset_tgt_labels:
        assert tgt_label in list(ids_tgt_labels.values())
    for idx in range(len(dataset_tgt_labels)):
        assert idx in list(ids_tgt_labels.keys())


def test_num_labels(
        data_processor,
        arg_classification_type,
        arg_tratto_model_type
):
    data_processor.pre_processing()
    num_labels = data_processor.get_num_labels()
    df_dataset = getattr(data_processor, '_df_dataset')
    if arg_classification_type == ClassificationType.CATEGORY_PREDICTION:
        if arg_tratto_model_type == TrattoModelType.TOKEN_CLASSES:
            dataset_tgt_labels = df_dataset['tokenClass'].unique().tolist()
        elif arg_tratto_model_type == TrattoModelType.TOKEN_VALUES:
            dataset_tgt_labels = df_dataset['token'].unique().tolist()
        else:
            assert False
    elif arg_classification_type == ClassificationType.LABEL_PREDICTION:
        dataset_tgt_labels = df_dataset['label'].unique().tolist()
    else:
        assert False
    assert num_labels == len(dataset_tgt_labels)
