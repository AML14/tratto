import copy

from scripts.server.predictor_decoder import get_input_model_classes, pre_process_dataset, get_input_model_values
from src.types.ClassificationType import ClassificationType
from src.types.TrattoModelType import TrattoModelType
from tests.utils import generate_equivalent_tokenClassesSoFar, generate_equivalent_eligibles, generate_src_input, \
    generate_tokenClassSoFar_str, generate_eligibles, generate_eligibles_str

def test_pre_processing(
        df_dataset_server,
        tokenizer,
        value_mappings
):
    df_dataset_server_before_pre_process = df_dataset_server.copy()
    df_dataset_server_after_pre_process = pre_process_dataset(df_dataset_server, tokenizer)
    assert len(df_dataset_server_before_pre_process) == len(df_dataset_server_after_pre_process)
    for idx in range(len(df_dataset_server)):
        row_before = df_dataset_server_before_pre_process.loc[idx]
        row_after = df_dataset_server_after_pre_process.loc[idx]
        assert row_after.tokenClass in list(value_mappings.values())
        assert row_after.tokenClassesSoFar == f"[ {' '.join(list(map(lambda x: value_mappings[x], row_before.tokenClassesSoFar)))} ]"
        assert row_after.methodSourceCode == row_before.methodSourceCode.split('{')[0]


def test_get_input(
        df_dataset_server,
        next_tokenClass_server,
        tokenizer,
        arg_tratto_model_type,
        value_mappings
):
    assert arg_tratto_model_type in [TrattoModelType.TOKEN_CLASSES, TrattoModelType.TOKEN_VALUES]
    df_dataset_server_after_pre_process = pre_process_dataset(df_dataset_server, tokenizer)
    if arg_tratto_model_type == TrattoModelType.TOKEN_CLASSES:
        src_predictor, eligible_token_classes = get_input_model_classes(
            df_dataset_server_after_pre_process,
            tokenizer
        )
    elif arg_tratto_model_type == TrattoModelType.TOKEN_VALUES:
        src_predictor, eligible_token_values = get_input_model_values(
            df_dataset_server_after_pre_process,
            value_mappings[next_tokenClass_server],
            tokenizer
        )
    expected_src = []
    for idx in range(len(df_dataset_server)):
        row_copy = df_dataset_server_after_pre_process.loc[idx].copy()
        if arg_tratto_model_type == TrattoModelType.TOKEN_CLASSES:
            eligible_key = 'eligibleTokenClasses'
        else:
            eligible_key = 'eligibleTokens'
        eligibles_list = list(dict.fromkeys(generate_eligibles(
            df_dataset_server_after_pre_process,
            row_copy,
            arg_tratto_model_type,
            value_mappings,
            value_mappings[next_tokenClass_server] if arg_tratto_model_type == TrattoModelType.TOKEN_VALUES else None
        )))
        if arg_tratto_model_type == TrattoModelType.TOKEN_CLASSES:
            assert eligible_token_classes == eligibles_list
        elif arg_tratto_model_type == TrattoModelType.TOKEN_VALUES:
            assert eligible_token_values == eligibles_list
        row_copy[eligible_key] = generate_eligibles_str(eligibles_list)
        row_input = generate_src_input(row_copy, tokenizer, ClassificationType.CATEGORY_PREDICTION, arg_tratto_model_type)
        expected_src.append(row_input)
    expected_src = list(dict.fromkeys(expected_src))
    assert len(expected_src) == len(src_predictor)
    assert len(expected_src) == 1
    assert expected_src[0] == src_predictor[0]