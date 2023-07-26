import os
import pytest
import copy
from src.types.ClassificationType import ClassificationType
from itertools import permutations
from src.types.TrattoModelType import TrattoModelType
from src.utils import utils
from tests.utils import generate_src_input, generate_equivalent_tokenClassesSoFar, generate_equivalent_eligibles


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
                equivalent_array = generate_equivalent_tokenClassesSoFar(row, value_mappings)
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


def test_pre_processing_eligibles(
        data_processor_ten_datapoints,
        arg_classification_type,
        arg_tratto_model_type,
        value_mappings
):
    if not arg_tratto_model_type in [TrattoModelType.TOKEN_CLASSES, TrattoModelType.TOKEN_VALUES]:
        assert False
    df_dataset_before_processing = getattr(data_processor_ten_datapoints, '_df_dataset').copy()
    data_processor_ten_datapoints.pre_processing()
    df_dataset_after_processing = getattr(data_processor_ten_datapoints, '_df_dataset')
    expected_eligibles = []
    for row in df_dataset_before_processing.itertuples(index=False):
        if (arg_classification_type == ClassificationType.CATEGORY_PREDICTION and row.label == True or arg_classification_type == ClassificationType.LABEL_PREDICTION):
            equivalent_array = generate_equivalent_eligibles(
                df_dataset_before_processing,
                row,
                arg_tratto_model_type,
                value_mappings
            )
            expected_eligibles.append(equivalent_array)
    for row in df_dataset_after_processing.itertuples(index=False):
        len_before_research = len(expected_eligibles)
        expected_eligibles_copy = copy.deepcopy(expected_eligibles)
        for eq_strings in expected_eligibles_copy:
            if arg_tratto_model_type == TrattoModelType.TOKEN_CLASSES:
                if row.eligibleTokenClasses in eq_strings:
                    expected_eligibles.remove(eq_strings)
                    break
            elif arg_tratto_model_type == TrattoModelType.TOKEN_VALUES:
                if row.eligibleTokens in eq_strings:
                    expected_eligibles.remove(eq_strings)
                    break
        assert len_before_research == (len(expected_eligibles) + 1)
    assert len(expected_eligibles) == 0


def test_pre_processing_src_input(
        data_processor_ten_datapoints,
        arg_classification_type,
        arg_tratto_model_type
):
    data_processor_ten_datapoints.pre_processing()
    df_dataset = getattr(data_processor_ten_datapoints, '_df_dataset')
    tokenizer = getattr(data_processor_ten_datapoints, '_tokenizer')
    src = getattr(data_processor_ten_datapoints, '_src')
    src_test = getattr(data_processor_ten_datapoints, '_src_test')
    tgt = getattr(data_processor_ten_datapoints, '_tgt')
    tgt_test = getattr(data_processor_ten_datapoints, '_tgt_test')
    test_ratio = getattr(data_processor_ten_datapoints, '_test_ratio')

    assert pytest.approx(len(src),1) == (1 - test_ratio) * df_dataset.shape[0]
    assert pytest.approx(len(src_test),1) == test_ratio * df_dataset.shape[0]
    assert len(src) == len(tgt)
    assert len(src_test) == len(tgt_test)

    src_tgt_src_test_tgt_test_expected = []

    for row in df_dataset.itertuples(index=False):
        input = generate_src_input(row, tokenizer, arg_classification_type, arg_tratto_model_type)
        if arg_classification_type == ClassificationType.CATEGORY_PREDICTION:
            if arg_tratto_model_type == TrattoModelType.TOKEN_CLASSES:
                src_tgt_src_test_tgt_test_expected.append((input, row.tokenClass))
            elif arg_tratto_model_type == TrattoModelType.TOKEN_VALUES:
                src_tgt_src_test_tgt_test_expected.append((input, row.token))
        elif arg_classification_type == ClassificationType.LABEL_PREDICTION:
            src_tgt_src_test_tgt_test_expected.append((input, row.label))

    assert (len(src) + len(src_test)) == len(src_tgt_src_test_tgt_test_expected)

    for input, target in [*zip(src,tgt),(*src_test,*tgt_test)]:
        len_before_processing = len(src_tgt_src_test_tgt_test_expected)
        if (input, target) in src_tgt_src_test_tgt_test_expected:
            src_tgt_src_test_tgt_test_expected.remove((input, target))
        assert len_before_processing == (len(src_tgt_src_test_tgt_test_expected) + 1)
    assert len(src_tgt_src_test_tgt_test_expected) == 0


