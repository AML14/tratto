import os
import pytest
import copy
from src.types.ClassificationType import ClassificationType
from itertools import permutations
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


def test_pre_processing_token_classes_so_far(
        data_processor_ten_datapoints,
        arg_classification_type,
        arg_tratto_model_type
):
    df_dataset_before_processing = getattr(data_processor_ten_datapoints, '_df_dataset').copy()
    data_processor_ten_datapoints.pre_processing()
    df_dataset_after_processing = getattr(data_processor_ten_datapoints, '_df_dataset')
    expected_tokenClassesSoFar = []
    assert len(df_dataset_before_processing) == len(df_dataset_after_processing)
    if arg_classification_type == ClassificationType.CATEGORY_PREDICTION:
        if arg_tratto_model_type == TrattoModelType.TOKEN_CLASSES:
            for row in df_dataset_before_processing.itertuples(index=False):
                equivalent_array = []
                for perm_tokenClassesSoFar in list(permutations(row.tokenClassesSoFar)):
                    perm_tokenClassesSoFar_str = f"[ {' '.join(list(perm_tokenClassesSoFar))} ]"
                    equivalent_array.append(perm_tokenClassesSoFar_str)
                expected_tokenClassesSoFar.append(equivalent_array)

    expected_tokenClassesSoFar_copy = copy.deepcopy(expected_tokenClassesSoFar)

    for row in df_dataset_after_processing.itertuples(index=False):
        len_before_research = len(expected_tokenClassesSoFar)
        for eq_strings in expected_tokenClassesSoFar_copy:
            if row.eligibleTokenClasses in eq_strings:
                expected_tokenClassesSoFar.remove(eq_strings)
                break
        assert len_before_research == (len(expected_tokenClassesSoFar) + 1)
    assert len(expected_tokenClassesSoFar) == 0


def test_pre_processing_dataset_after(
        data_processor_ten_datapoints,
        arg_classification_type,
        arg_tratto_model_type,
        value_mappings
):
    df_dataset_before_processing = getattr(data_processor_ten_datapoints, '_df_dataset').copy()
    data_processor_ten_datapoints.pre_processing()
    df_dataset_after_processing = getattr(data_processor_ten_datapoints, '_df_dataset')
    if arg_classification_type == ClassificationType.CATEGORY_PREDICTION:
        assert len(df_dataset_before_processing[df_dataset_before_processing['label']] == True) == len(df_dataset_after_processing)
    elif arg_classification_type == ClassificationType.LABEL_PREDICTION:
        assert len(df_dataset_before_processing) == len(df_dataset_after_processing)
    else:
        assert False


def test_pre_processing_tokenClassesSoFar(
        data_processor_ten_datapoints,
        arg_classification_type,
        arg_tratto_model_type,
        value_mappings
):
    df_dataset_before_processing = getattr(data_processor_ten_datapoints, '_df_dataset').copy()
    data_processor_ten_datapoints.pre_processing()
    df_dataset_after_processing = getattr(data_processor_ten_datapoints, '_df_dataset')
    expected_tokenClassesSoFar = []
    if arg_tratto_model_type == TrattoModelType.TOKEN_CLASSES:
        for row in df_dataset_before_processing.itertuples(index=False):
            if (arg_classification_type == ClassificationType.CATEGORY_PREDICTION and row.label == True) or (arg_classification_type == ClassificationType.LABEL_PREDICTION):
                equivalent_array = []
                tokenClassesSoFar_mapped = list(map(lambda t: value_mappings[t], row.tokenClassesSoFar))
                for perm_tokenClassesSoFar in list(permutations(tokenClassesSoFar_mapped)):
                    perm_tokenClassesSoFar_str = f"[ {' '.join(list(perm_tokenClassesSoFar))} ]"
                    equivalent_array.append(perm_tokenClassesSoFar_str)
                expected_tokenClassesSoFar.append(equivalent_array)
        for row in df_dataset_after_processing.itertuples(index=False):
            len_before_research = len(expected_tokenClassesSoFar)
            expected_tokenClassesSoFar_copy = copy.deepcopy(expected_tokenClassesSoFar)
            for eq_strings in expected_tokenClassesSoFar_copy:
                if row.tokenClassesSoFar in eq_strings:
                    expected_tokenClassesSoFar.remove(eq_strings)
                    break
            assert len_before_research == (len(expected_tokenClassesSoFar) + 1)
        assert len(expected_tokenClassesSoFar) == 0
    elif arg_tratto_model_type == TrattoModelType.TOKEN_VALUES:
        assert 'tokenClassesSoFar' not in df_dataset_after_processing.columns.tolist()
    else:
        assert False


