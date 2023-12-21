from itertools import permutations
import pandas as pd

from src.types.ClassificationType import ClassificationType
from src.types.TransformerType import TransformerType
from src.types.TrattoModelType import TrattoModelType
from src.utils.utils import is_valid_combination


def generate_src_input(
        datapoint,
        tokenizer,
        transformer_type,
        classification_type,
        tratto_model_type
):
    input = ""
    if tratto_model_type in [TrattoModelType.TOKEN_CLASSES, TrattoModelType.TOKEN_VALUES]:
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
        input += f"{datapoint.javadocTag}{tokenizer.sep_token}"
        input += f"{datapoint.oracleType}{tokenizer.sep_token}"
        input += f"{datapoint.packageName}{tokenizer.sep_token}"
        input += f"{datapoint.className}{tokenizer.sep_token}"
        input += f"{datapoint.methodSourceCode}{tokenizer.sep_token}"
        input += f"{datapoint.methodJavadoc}"
        if tratto_model_type == TrattoModelType.TOKEN_VALUES:
            # TODO: Check if tokenInfo must be removed
            # if transformer_type == TransformerType.ENCODER or classification_type == ClassificationType.LABEL_PREDICTION:
            # input += f"{datapoint.tokenInfo}{tokenizer.sep_token}"
            input += f"{tokenizer.sep_token}{datapoint.tokenInfo}"
    elif tratto_model_type == TrattoModelType.ORACLES:
        input += f"{datapoint.javadocTag}{tokenizer.sep_token}"
        input += f"{datapoint.oracleType}{tokenizer.sep_token}"
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
    if tratto_model_type in [TrattoModelType.TOKEN_CLASSES, TrattoModelType.TOKEN_VALUES]:
        equivalent_tokenClassesSoFar_array = generate_equivalent_tokenClassesSoFar(datapoint, value_mappings)
        equivalent_eligible_array = generate_equivalent_eligibles(df_dataset, datapoint, tratto_model_type, value_mappings)
        temp_df = pd.DataFrame([datapoint], columns=df_dataset.columns)
        for input_eligible in equivalent_eligible_array:
            for input_tokenClassSoFar in equivalent_tokenClassesSoFar_array:
                temp_df['tokenClassesSoFar'] = None
                temp_df['tokenClassesSoFar'] = temp_df['tokenClassesSoFar'].astype(str)
                temp_df.at[0, 'tokenClassesSoFar'] = str(input_tokenClassSoFar)
                if tratto_model_type == TrattoModelType.TOKEN_CLASSES:
                    temp_df['eligibleTokenClasses'] = None
                    temp_df['eligibleTokenClasses'] = temp_df['eligibleTokenClasses'].astype(str)
                    temp_df.at[0, 'eligibleTokenClasses'] = input_eligible
                    temp_df.astype({'eligibleTokenClasses': 'str'})
                elif tratto_model_type == TrattoModelType.TOKEN_VALUES:
                    temp_df['eligibleTokens'] = None
                    temp_df['eligibleTokens'] = temp_df['eligibleTokens'].astype(str)
                    temp_df.at[0, 'eligibleTokens'] = input_eligible
                equivalent_inputs_array.append(generate_src_input(
                    temp_df.iloc[0],
                    tokenizer,
                    transformer_type,
                    classification_type,
                    tratto_model_type
                ))
    else:
        equivalent_inputs_array.append(generate_src_input(
            datapoint,
            tokenizer,
            transformer_type,
            classification_type,
            tratto_model_type
        ))
    return equivalent_inputs_array



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

def skipTest(
        arg_dataset_path = None,
        transformer_type = None,
        tratto_model_type = None,
        classification_type = None,
):
    if 'token-classes-dataset' in arg_dataset_path:
        if tratto_model_type == TrattoModelType.TOKEN_VALUES:
            return True
        if tratto_model_type == TrattoModelType.ORACLES:
            return True
    if 'token-values-dataset' in arg_dataset_path:
        if tratto_model_type == TrattoModelType.TOKEN_CLASSES:
            return True
        if tratto_model_type == TrattoModelType.ORACLES:
            return True
    if 'token-oracles-dataset' in arg_dataset_path:
        if tratto_model_type == TrattoModelType.TOKEN_CLASSES:
            return True
        if tratto_model_type == TrattoModelType.TOKEN_VALUES:
            return True
        if classification_type == ClassificationType.CATEGORY_PREDICTION:
            return True
    if is_valid_combination(tratto_model_type, transformer_type, classification_type):
        return False
    return True

