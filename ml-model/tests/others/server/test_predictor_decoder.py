from scripts.server.predictor import get_input_model_classes, pre_process_dataset, get_input_model_values, get_input_model_oracles
from src.types.ClassificationType import ClassificationType
from src.types.TransformerType import TransformerType
from src.types.TrattoModelType import TrattoModelType
from tests.utils import generate_src_input, generate_eligibles, generate_eligibles_str

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
        assert row_after.methodSourceCode == row_before.methodSourceCode


def test_get_input(
        df_dataset_server,
        next_tokenClass_server,
        tokenizer,
        arg_transformer_type,
        arg_classification_type,
        arg_tratto_model_type,
        value_mappings
):
    assert arg_tratto_model_type in [TrattoModelType.TOKEN_CLASSES, TrattoModelType.TOKEN_VALUES, TrattoModelType.ORACLES]
    next_tokenClass_server = value_mappings[next_tokenClass_server]
    df_dataset_server_after_pre_process = pre_process_dataset(df_dataset_server, tokenizer)
    if arg_tratto_model_type == TrattoModelType.TOKEN_CLASSES:
        src_predictor, tgt_predictor, eligible_token_classes = get_input_model_classes(
            df_dataset_server_after_pre_process,
            tokenizer,
            arg_classification_type,
            arg_transformer_type
        )
    elif arg_tratto_model_type == TrattoModelType.TOKEN_VALUES:
        src_predictor, tgt_predictor, eligible_token_values, next_token_value = get_input_model_values(
            df_dataset_server_after_pre_process,
            next_tokenClass_server,
            tokenizer,
            arg_classification_type,
            arg_transformer_type
        )
    elif arg_tratto_model_type == TrattoModelType.ORACLES:
        src_predictor, tgt_predictor = get_input_model_oracles(
            df_dataset_server_after_pre_process,
            tokenizer,
            arg_transformer_type
        )
    expected_src = []
    for idx in range(len(df_dataset_server)):
        row_copy = df_dataset_server_after_pre_process.loc[idx].copy()
        if row_copy.tokenClass == next_tokenClass_server or arg_tratto_model_type == TrattoModelType.TOKEN_CLASSES:
            if arg_tratto_model_type == TrattoModelType.TOKEN_CLASSES:
                eligible_key = 'eligibleTokenClasses'
            else:
                eligible_key = 'eligibleTokens'
            eligibles_list = list(dict.fromkeys(generate_eligibles(
                df_dataset_server_after_pre_process,
                row_copy,
                arg_tratto_model_type,
                value_mappings,
                next_tokenClass_server if arg_tratto_model_type == TrattoModelType.TOKEN_VALUES else None
            )))
            if arg_tratto_model_type == TrattoModelType.TOKEN_CLASSES:
                assert eligible_token_classes == eligibles_list
            elif arg_tratto_model_type == TrattoModelType.TOKEN_VALUES:
                if not row_copy.oracleSoFar == '':
                    assert eligible_token_values == None #eligibles_list
            row_copy[eligible_key] = generate_eligibles_str(eligibles_list)
            row_input = generate_src_input(
                row_copy,
                tokenizer,
                arg_transformer_type,
                arg_classification_type,
                arg_tratto_model_type
            )
            expected_src.append(row_input)
        elif arg_tratto_model_type == TrattoModelType.ORACLES:
            row_input = generate_src_input(
                row_copy,
                tokenizer,
                arg_transformer_type,
                arg_classification_type,
                arg_tratto_model_type
            )
            expected_src.append(row_input)
    if arg_transformer_type == TransformerType.DECODER or arg_tratto_model_type == TrattoModelType.ORACLES:
        expected_src = list(set(expected_src))
    if arg_transformer_type == TransformerType.DECODER and arg_classification_type == ClassificationType.CATEGORY_PREDICTION:
        expected_src = list(dict.fromkeys(expected_src))
        assert len(expected_src) == 1
        if not arg_tratto_model_type == TrattoModelType.TOKEN_VALUES or next_token_value == None:
            assert expected_src[0] == src_predictor[0]
    else:
        if not arg_tratto_model_type == TrattoModelType.TOKEN_VALUES or next_token_value == None:
            assert len(expected_src) == len(src_predictor)
            for input in src_predictor:
                if input in expected_src:
                    expected_src.remove(input)
                    continue
                assert False
            assert len(expected_src) == 0
        else:
            if next_token_value == None:
                assert src_predictor == None
