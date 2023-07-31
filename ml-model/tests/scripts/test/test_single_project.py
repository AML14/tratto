import copy

from scripts.test.test_single_project import pre_processing
from src.types.ClassificationType import ClassificationType
from src.types.TransformerType import TransformerType
from src.types.TrattoModelType import TrattoModelType
from tests.utils import generate_src_input, generate_equivalent_eligibles, generate_equivalent_tokenClassesSoFar


def test_pre_processing(
        df_projects,
        tokenizer,
        arg_classification_type,
        arg_transformer_type,
        arg_tratto_model_type,
        value_mappings
):
    assert arg_tratto_model_type in [TrattoModelType.TOKEN_CLASSES, TrattoModelType.TOKEN_VALUES]
    df_projects_before = df_projects.copy()
    df_projects_before["tokenClass"] = df_projects_before["tokenClass"].apply(lambda x: value_mappings[x])
    datasets, _ = pre_processing(
        df_projects,
        tokenizer,
        arg_classification_type,
        arg_tratto_model_type,
        arg_transformer_type
    )
    expected_src_tgt = []
    for idx in range(len(df_projects_before)):
        row_copy = df_projects_before.loc[idx].copy()
        if arg_classification_type == ClassificationType.CATEGORY_PREDICTION:
            if row_copy.label == False:
                continue
        row_copy.methodSourceCode = row_copy.methodSourceCode.split('{')[0]
        #row_copy.tokenClass = value_mappings[row_copy.tokenClass]
        equivalent_tokenClassesSoFar_str_array = generate_equivalent_tokenClassesSoFar(row_copy, value_mappings)
        if arg_tratto_model_type == TrattoModelType.TOKEN_CLASSES:
            eligible_key = 'eligibleTokenClasses'
        else:
            eligible_key = 'eligibleTokens'
        equivalent_eligibles_str_array = generate_equivalent_eligibles(
            df_projects_before,
            row_copy,
            arg_tratto_model_type,
            value_mappings
        )
        equivalent_src_inputs = []
        for eligible_str in equivalent_eligibles_str_array:
            for tokenClassesSoFar_str in equivalent_tokenClassesSoFar_str_array:
                row_copy.tokenClassesSoFar = tokenClassesSoFar_str
                row_copy[eligible_key] = eligible_str
                row_input = generate_src_input(
                    row_copy,
                    tokenizer,
                    arg_transformer_type,
                    arg_classification_type,
                    arg_tratto_model_type
                )
                equivalent_src_inputs.append(row_input)
        if arg_classification_type == ClassificationType.CATEGORY_PREDICTION:
            if arg_tratto_model_type == TrattoModelType.TOKEN_CLASSES:
                expected_src_tgt.append((equivalent_src_inputs,row_copy.tokenClass))
            else:
                expected_src_tgt.append((equivalent_src_inputs,row_copy.token))
        else:
            expected_src_tgt.append((equivalent_src_inputs,str(row_copy.label)))

    if arg_tratto_model_type == TrattoModelType.TOKEN_CLASSES:
        ids_classes_dict = {i: k for i, k in enumerate(sorted(list(df_projects_before["tokenClass"].unique())))}
    elif arg_tratto_model_type == TrattoModelType.TOKEN_VALUES:
        ids_classes_dict = {i: k for i, k in enumerate(sorted(list(df_projects_before["token"].unique())))}
    ids_labels_dict = {i: str(k) for i, k in enumerate(sorted(list(df_projects_before["label"].unique())))}

    for _, src, tgt, _, _ in datasets:
        for input, output in [*zip(src,tgt)]:
            # Get the list of target values from the dataframe
            if arg_transformer_type == TransformerType.ENCODER:
                if arg_classification_type == ClassificationType.CATEGORY_PREDICTION:
                    output = ids_classes_dict[output]
                elif arg_classification_type == ClassificationType.LABEL_PREDICTION:
                    output = ids_labels_dict[output]
            len_before_processing = len(expected_src_tgt)
            expected_src_tgt_copy = copy.deepcopy(expected_src_tgt)
            for equivalent_inputs, expected_tgt in expected_src_tgt_copy:
                if input in equivalent_inputs:
                    assert output == expected_tgt
                    expected_src_tgt.remove((equivalent_inputs,expected_tgt))
                    break
            assert len_before_processing == (len(expected_src_tgt) + 1)
    assert len(expected_src_tgt) == 0

