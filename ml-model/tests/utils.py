from itertools import permutations

from src.types.ClassificationType import ClassificationType
from src.types.TransformerType import TransformerType
from src.types.TrattoModelType import TrattoModelType


def generate_src_input(
        datapoint,
        tokenizer,
        transformer_type,
        classification_type,
        tratto_model_type
):
    input = ""
    if classification_type == ClassificationType.LABEL_PREDICTION:
        if tratto_model_type == TrattoModelType.TOKEN_CLASSES:
            input += f"{datapoint.tokenClass}{tokenizer.sep_token}"
        elif tratto_model_type == TrattoModelType.TOKEN_VALUES:
            input += f"{datapoint.token}{tokenizer.sep_token}"
    input += f"{datapoint.oracleSoFar}{tokenizer.sep_token}"
    if tratto_model_type == TrattoModelType.TOKEN_CLASSES:
        input += f"{datapoint.tokenClassesSoFar}{tokenizer.sep_token}"
        input += f"{datapoint.eligibleTokenClasses}{tokenizer.sep_token}"
    elif tratto_model_type == TrattoModelType.TOKEN_VALUES:
        input += f"{datapoint.eligibleTokens}{tokenizer.sep_token}"
        if transformer_type == TransformerType.ENCODER or classification_type == ClassificationType.LABEL_PREDICTION:
            input += f"{datapoint.tokenInfo}{tokenizer.sep_token}"
        input += f"{datapoint.tokenClass}{tokenizer.sep_token}"
    input += f"{datapoint.javadocTag}{tokenizer.sep_token}"
    input += f"{datapoint.oracleType}{tokenizer.sep_token}"
    input += f"{datapoint.packageName}{tokenizer.sep_token}"
    input += f"{datapoint.className}{tokenizer.sep_token}"
    input += f"{datapoint.methodSourceCode}{tokenizer.sep_token}"
    input += f"{datapoint.methodJavadoc}"
    return input

def generate_equivalent_src_input(
        df_dataset,
        datapoint,
        tokenizer,
        transformer_type,
        classification_type,
        tratto_model_type,
        value_mappings
):
    equivalent_inputs_array = []
    equivalent_tokenClassesSoFar_array = []
    for perm_tokenClassesSoFar in list(permutations(datapoint.tokenClassesSoFar)):
        perm_tokenClassesSoFar_str = generate_tokenClassSoFar_str(perm_tokenClassesSoFar)
        equivalent_tokenClassesSoFar_array.append(perm_tokenClassesSoFar_str)
    equivalent_eligible_array = generate_eligibles(df_dataset, datapoint, value_mappings)
    for perm_eligible in list(permutations(equivalent_eligible_array)):
        perm_eligible_str = generate_eligibles_str(perm_eligible)
        for perm_tokenClassesSoFar in equivalent_tokenClassesSoFar_array:
            datapoint.tokenClassesSoFar = perm_tokenClassesSoFar
            if tratto_model_type == TrattoModelType.TOKEN_CLASSES:
                datapoint.eligibleTokenClasses = perm_eligible_str
            elif tratto_model_type == TrattoModelType.TOKEN_VALUES:
                datapoint.eligibleTokens = perm_eligible_str
            equivalent_inputs_array.append(generate_src_input(
                datapoint,
                tokenizer,
                transformer_type,
                classification_type,
                tratto_model_type
            ))



def generate_tokenClassSoFar_str(
        tokenClassesSoFar_list
):
    return f"[ {' '.join(list(tokenClassesSoFar_list))} ]"

def generate_eligibles_str(
        eligibles_list
):
    return f"[ {' '.join(list(eligibles_list))} ]"

def generate_equivalent_tokenClassesSoFar(
        datapoint,
        value_mappings
):
    equivalent_array = []
    for perm_tokenClassesSoFar in list(permutations(datapoint.tokenClassesSoFar)):
        perm_tokenClassesSoFar_str = generate_tokenClassSoFar_str(perm_tokenClassesSoFar)
        equivalent_array.append(perm_tokenClassesSoFar_str)
    return equivalent_array

def generate_eligibles(
        df_dataset,
        datapoint,
        tratto_model_type,
        value_mappings,
        next_token_class = None
):
    df_filtered = df_dataset[
        (df_dataset['oracleId'] == datapoint.oracleId) &
        (df_dataset['oracleSoFar'] == datapoint.oracleSoFar)
    ]
    if next_token_class is not None:
        df_filtered = df_filtered[df_filtered['tokenClass'] == next_token_class]
    eligibles = []
    for other_datapoint in df_filtered.itertuples(index=False):
        if tratto_model_type == TrattoModelType.TOKEN_CLASSES:
            if not other_datapoint.tokenClass in value_mappings.values():
                eligibles.append(value_mappings[other_datapoint.tokenClass])
            else:
                eligibles.append(other_datapoint.tokenClass)
        elif tratto_model_type == TrattoModelType.TOKEN_VALUES:
            eligibles.append(other_datapoint.token)
    return eligibles

def generate_equivalent_eligibles(
        df_dataset,
        datapoint,
        tratto_model_type,
        value_mappings
):
    equivalent_array = []
    oracle_eligibles = generate_eligibles(df_dataset, datapoint, tratto_model_type, value_mappings)
    for perm_oracle_eligibles in list(permutations(oracle_eligibles)):
        perm_oracle_eligibles_str = f"[ {' '.join(list(perm_oracle_eligibles))} ]"
        equivalent_array.append(perm_oracle_eligibles_str)
    return equivalent_array