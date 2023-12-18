import os
import pytest
import copy

from src.processors.DataProcessor import DataProcessor
from src.types.ClassificationType import ClassificationType
from itertools import permutations

from src.types.DatasetType import DatasetType
from src.types.TransformerType import TransformerType
from src.types.TrattoModelType import TrattoModelType
from src.utils import utils
from tests.utils import generate_equivalent_src_input, generate_equivalent_tokenClassesSoFar, generate_equivalent_eligibles, skipTest


def test_load_dataset_as_dataframe_num_rows(
        arg_dataset_path,
        arg_tratto_model_type
):
    if skipTest(arg_dataset_path=arg_dataset_path, tratto_model_type=arg_tratto_model_type):
        pytest.skip(f"Skipping test because of the invalid combination of the arguments: {arg_dataset_path.split('/')[-1]} - {arg_tratto_model_type}")
    for dataset_lenght in ['small', 'ten', 'single']:
        for dataset_type in ['train', 'validation']:
            datapoints_counter = 0
            path_to_dataset = os.path.join(arg_dataset_path, dataset_lenght, dataset_type)
            for file_name in os.listdir(path_to_dataset):
                _, json_content = utils.import_json(os.path.join(path_to_dataset, file_name))
                datapoints_counter += len(json_content)
            df_dataset = DataProcessor.load_dataset_as_dataframe(path_to_dataset, arg_tratto_model_type)
            assert df_dataset.shape[0] == datapoints_counter

def test_compute_weights(
        data_processor
):
    t_df = data_processor.get_train_dataframe()
    v_df = data_processor.get_validation_dataframe()
    for dataset_type in [DatasetType.TRAINING, DatasetType.VALIDATION, None]:
        df_dataset = t_df if dataset_type == DatasetType.TRAINING or dataset_type is None else v_df
        columns = df_dataset.columns.tolist()
        for col in columns:
            if dataset_type is None:
                weights = data_processor.compute_weights(col)
            else:
                weights = data_processor.compute_weights(col, dataset_type, df_dataset)
            assert pytest.approx(sum(weights), 0.01) == 1
            df_dataset = df_dataset.astype(str)
            assert len(weights) == len(df_dataset[col].unique().tolist())

def test_get_encoder_ids_labels(
        data_processor,
        arg_classification_type,
        arg_tratto_model_type
):
    t_df = data_processor.get_train_dataframe()
    v_df = data_processor.get_validation_dataframe()
    for dataset_type in [DatasetType.TRAINING, DatasetType.VALIDATION, None]:
        df_dataset = t_df if dataset_type == DatasetType.TRAINING or dataset_type is None else v_df
        df_dataset = df_dataset.astype(str)
        tgt_column_name = getattr(data_processor, '_tgt_column_name')
        if dataset_type is None:
            ids_tgt_labels = data_processor.get_encoder_ids_labels(tgt_column_name)
        else:
            ids_tgt_labels = data_processor.get_encoder_ids_labels(tgt_column_name, dataset_type, df_dataset)
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
    t_df = data_processor.get_train_dataframe()
    v_df = data_processor.get_validation_dataframe()
    for dataset_type in [DatasetType.TRAINING, DatasetType.VALIDATION, None]:
        df_dataset = t_df if dataset_type == DatasetType.TRAINING or dataset_type is None else v_df
        df_dataset = df_dataset.astype(str)
        tgt_column_name = getattr(data_processor, '_tgt_column_name')
        if dataset_type is None:
            num_labels = data_processor.get_num_labels()
        else:
            num_labels = data_processor.get_num_labels(tgt_column_name, dataset_type, df_dataset)
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
        arg_classification_type
):
    t_df = data_processor_ten_datapoints.get_train_dataframe()
    v_df = data_processor_ten_datapoints.get_validation_dataframe()
    data_processor_ten_datapoints.pre_processing()
    tokenized_train_dataset = getattr(data_processor_ten_datapoints, '_tokenized_train_dataset')
    tokenized_validation_dataset = getattr(data_processor_ten_datapoints, '_tokenized_validation_dataset')
    if arg_classification_type == ClassificationType.CATEGORY_PREDICTION:
        assert len(t_df[t_df['label']] == True) == len(tokenized_train_dataset["src"])
        assert len(v_df[v_df['label']] == True) == len(tokenized_validation_dataset["src"])
    elif arg_classification_type == ClassificationType.LABEL_PREDICTION:
        assert len(t_df) == len(tokenized_train_dataset["src"])
        assert len(v_df) == len(tokenized_validation_dataset["src"])
    else:
        assert False
    assert len(tokenized_train_dataset["src"]) == len(tokenized_train_dataset["tgt"])
    assert len(tokenized_train_dataset["src"]) == len(tokenized_train_dataset["mask"])
    assert len(tokenized_validation_dataset["src"]) == len(tokenized_validation_dataset["tgt"])
    assert len(tokenized_validation_dataset["src"]) == len(tokenized_validation_dataset["mask"])


def test_compute_tokenClassesSoFar(
        data_processor_ten_datapoints,
        arg_classification_type,
        arg_tratto_model_type,
        value_mappings
):
    t_df = data_processor_ten_datapoints.get_train_dataframe()
    v_df = data_processor_ten_datapoints.get_validation_dataframe()
    expected_tokenClassesSoFar = []
    if arg_tratto_model_type == TrattoModelType.TOKEN_CLASSES:
        for row in [*t_df.itertuples(index=False), *v_df.itertuples(index=False)]:
            if (arg_classification_type == ClassificationType.CATEGORY_PREDICTION and row.label == True) or (arg_classification_type == ClassificationType.LABEL_PREDICTION):
                equivalent_array = generate_equivalent_tokenClassesSoFar(row, value_mappings)
                expected_tokenClassesSoFar.append(equivalent_array)
        if (arg_classification_type == ClassificationType.CATEGORY_PREDICTION):
            t_df = data_processor_ten_datapoints._compute_tokenClassesSoFar(t_df[t_df['label'] == True])
            v_df = data_processor_ten_datapoints._compute_tokenClassesSoFar(v_df[v_df['label'] == True])
        else:
            t_df = data_processor_ten_datapoints._compute_tokenClassesSoFar(t_df)
            v_df = data_processor_ten_datapoints._compute_tokenClassesSoFar(v_df)
        for row in [*list(t_df.itertuples(index=False)), *list(v_df.itertuples(index=False))]:
            len_before_research = len(expected_tokenClassesSoFar)
            expected_tokenClassesSoFar_copy = copy.deepcopy(expected_tokenClassesSoFar)
            for eq_strings in expected_tokenClassesSoFar_copy:
                if row.tokenClassesSoFar in eq_strings:
                    expected_tokenClassesSoFar.remove(eq_strings)
                    break
            assert len_before_research == (len(expected_tokenClassesSoFar) + 1)
        assert len(expected_tokenClassesSoFar) == 0
    else:
        if not arg_tratto_model_type in [TrattoModelType.ORACLES, TrattoModelType.TOKEN_VALUES]:
            assert False


def test_pre_processing_eligibles(
        data_processor_ten_datapoints,
        arg_classification_type,
        arg_tratto_model_type,
        value_mappings
):
    t_df = data_processor_ten_datapoints.get_train_dataframe()
    v_df = data_processor_ten_datapoints.get_validation_dataframe()
    expected_eligibles = []

    if not arg_tratto_model_type == TrattoModelType.ORACLES:
        for row in [*t_df.itertuples(index=False), *v_df.itertuples(index=False)]:
            df = t_df if row in t_df.itertuples(index=False) else v_df
            if (arg_classification_type == ClassificationType.CATEGORY_PREDICTION and row.label == True or arg_classification_type == ClassificationType.LABEL_PREDICTION):
                equivalent_array = generate_equivalent_eligibles(
                    df,
                    row,
                    arg_tratto_model_type,
                    value_mappings
                )
                expected_eligibles.append(equivalent_array)
        if arg_tratto_model_type == TrattoModelType.TOKEN_CLASSES:
            t_df = data_processor_ten_datapoints._compute_eligible_token_classes(t_df)
            v_df = data_processor_ten_datapoints._compute_eligible_token_classes(v_df)
            if arg_classification_type == ClassificationType.CATEGORY_PREDICTION:
                t_df = t_df[t_df['label'] == True]
                v_df = v_df[v_df['label'] == True]
        elif arg_tratto_model_type == TrattoModelType.TOKEN_VALUES:
            t_df = data_processor_ten_datapoints._compute_eligible_token_values(t_df)
            v_df = data_processor_ten_datapoints._compute_eligible_token_values(v_df)
            if arg_classification_type == ClassificationType.CATEGORY_PREDICTION:
                t_df = t_df[t_df['label'] == True]
                v_df = v_df[v_df['label'] == True]
        for row in [*t_df.itertuples(index=False), *v_df.itertuples(index=False)]:
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
    else:
        assert True


def test_pre_processing_src_input(
        data_processor_ten_datapoints,
        arg_transformer_type,
        arg_classification_type,
        arg_tratto_model_type,
        value_mappings
):
    t_df = data_processor_ten_datapoints.get_train_dataframe()
    v_df = data_processor_ten_datapoints.get_validation_dataframe()
    tokenizer = getattr(data_processor_ten_datapoints, '_tokenizer')

    src_tgt_src_test_tgt_test_expected = []

    for row in [*t_df.itertuples(index=False), *v_df.itertuples(index=False)]:
        df = t_df if row in t_df.itertuples(index=False) else v_df
        if (arg_classification_type == ClassificationType.CATEGORY_PREDICTION and row.label == True or arg_classification_type == ClassificationType.LABEL_PREDICTION):
            equivalent_inputs = generate_equivalent_src_input(
                df, row, tokenizer, arg_transformer_type, arg_classification_type,
                arg_tratto_model_type, value_mappings
            )
            if arg_transformer_type == TransformerType.DECODER:
                if arg_classification_type == ClassificationType.CATEGORY_PREDICTION:
                    if arg_tratto_model_type == TrattoModelType.TOKEN_CLASSES:
                        src_tgt_src_test_tgt_test_expected.append((equivalent_inputs, row.tokenClass))
                    elif arg_tratto_model_type == TrattoModelType.TOKEN_VALUES:
                        src_tgt_src_test_tgt_test_expected.append((equivalent_inputs, row.token))
                elif arg_classification_type == ClassificationType.LABEL_PREDICTION:
                    src_tgt_src_test_tgt_test_expected.append((equivalent_inputs, str(row.label)))
            else:
                if arg_classification_type == ClassificationType.CATEGORY_PREDICTION:
                    if arg_tratto_model_type == TrattoModelType.TOKEN_CLASSES:
                        classes_ids_dict = data_processor_ten_datapoints.get_encoder_labels_ids("tokenClass")
                        row_tgt = classes_ids_dict[row.tokenClass]
                        src_tgt_src_test_tgt_test_expected.append((equivalent_inputs, row_tgt))
                    elif arg_tratto_model_type == TrattoModelType.TOKEN_VALUES:
                        classes_ids_dict = data_processor_ten_datapoints.get_encoder_labels_ids("token")
                        row_tgt = classes_ids_dict[row.token]
                        src_tgt_src_test_tgt_test_expected.append((equivalent_inputs, row_tgt))
                elif arg_classification_type == ClassificationType.LABEL_PREDICTION:
                    labels_ids_dict = data_processor_ten_datapoints.get_encoder_labels_ids()
                    row_tgt = labels_ids_dict[str(row.label)]
                    src_tgt_src_test_tgt_test_expected.append((equivalent_inputs, row_tgt))

    data_processor_ten_datapoints.pre_processing()
    tokenized_train_dataset = getattr(data_processor_ten_datapoints, '_tokenized_train_dataset')
    tokenized_validation_dataset = getattr(data_processor_ten_datapoints, '_tokenized_validation_dataset')

    t_t_src = tokenized_train_dataset['src']
    t_t_mask = tokenized_train_dataset['mask']
    t_t_tgt = tokenized_train_dataset['tgt']
    v_t_src = tokenized_validation_dataset['src']
    v_t_mask = tokenized_validation_dataset['mask']
    v_t_tgt = tokenized_validation_dataset['tgt']

    assert (len(t_t_src) + len(v_t_src)) == len(src_tgt_src_test_tgt_test_expected)
    assert (len(t_t_mask) + len(v_t_mask)) == len(src_tgt_src_test_tgt_test_expected)
    assert (len(t_t_tgt) + len(v_t_tgt)) == len(src_tgt_src_test_tgt_test_expected)

    for input, target in [*zip(t_t_src,t_t_tgt),*zip(v_t_src,v_t_tgt)]:
        decoded_input = tokenizer.decode(input)
        decoded_input = decoded_input[len(tokenizer.special_tokens_map['cls_token']):]
        decoded_input = decoded_input[:decoded_input.rindex(tokenizer.special_tokens_map['sep_token'])]
        if arg_transformer_type == TransformerType.DECODER:
            decoded_target = tokenizer.decode(target)
            decoded_target = decoded_target[len(tokenizer.special_tokens_map['cls_token']):]
            decoded_target = decoded_target[:decoded_target.rindex(tokenizer.special_tokens_map['sep_token'])]
        else:
            decoded_target = target.item()
        len_before_research = len(src_tgt_src_test_tgt_test_expected)
        src_tgt_src_test_tgt_test_expected_copy = copy.deepcopy(src_tgt_src_test_tgt_test_expected)
        for equivalent_inputs, expected_tgt in src_tgt_src_test_tgt_test_expected_copy:
            if decoded_input in equivalent_inputs:
                assert decoded_target == expected_tgt
                src_tgt_src_test_tgt_test_expected.remove((equivalent_inputs, expected_tgt))
                break
        assert len_before_research == (len(src_tgt_src_test_tgt_test_expected) + 1)
    assert len(src_tgt_src_test_tgt_test_expected) == 0


