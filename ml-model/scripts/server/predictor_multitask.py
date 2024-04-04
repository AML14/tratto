import os.path

import torch
import numpy as np
import pandas as pd
import re
from datasets import load_dataset, Dataset
from torch.utils.data import DataLoader, TensorDataset
from transformers import PreTrainedModel, PreTrainedTokenizer, DataCollatorForSeq2Seq
from typing import Type
from src.types.TransformerType import TransformerType
from src.types.TrattoModelType import TrattoModelType

# Oracle evaluator eligible tokens
oracle_evaluator_eligible_tokens = ["assertTrue(", "// No assertion possible"]

def predict_next(
        device,
        model,
        dl_src,
        tokenizer,
        eligible_tokens,
        transformer_type,
        max_tgt_length
):
    # Model in evaluation mode
    model.eval().to(device)
    gen_kwargs = {
        "max_new_tokens": max_tgt_length,
        "num_beams": 1,
        "do_sample": False,
        "pad_token_id": tokenizer.eos_token_id,
        "output_scores": True,
        "return_dict_in_generate": True
    }
    predictions = []
    # Inference time: The prediction is performed without accumulating the gradient descent and without updating
    # the weights of the model
    with torch.no_grad():
        for batch_id, batch in enumerate(dl_src, 1):
            # Extract the inputs, the attention masks and the targets from the batch
            src_input = batch[0].to(device)
            src_masks = batch[1].to(device)

            if transformer_type == TransformerType.DECODER:
                # Model predictions with beam-search
                outputs = model.generate(
                    input_ids=src_input,
                    attention_mask=src_masks,
                    **gen_kwargs
                )
                tokens = np.array(
                    tokenizer.batch_decode(
                        outputs['sequences'],
                        skip_special_tokens=True
                    )
                )
                # Beam search (return first token that matches the eligible tokens)
                for token in tokens:
                    if token in eligible_tokens:
                        return token
                raise Exception(f"Predicted token not in eligible tokens: {token}")
            else:
                raise Exception("Encoder predictions not implemented yet")


def process_dataset(
        df_dataset: pd.DataFrame,
        pre_processing: bool = True
):
    def generate_src(
            row
    ):
        # Generate input string
        return f"// {row['oracleType']}: \"{row['javadocTag']}\"\n// Next possible tokens: {row['eligibleTokens'] if not row['oracleSoFar'] == '' else oracle_evaluator_eligible_tokens}\n// Assertion:\n{'assertTrue(' + row['oracleSoFar'] if not row['oracleSoFar'] == '' else ''}<FILL_ME>'\n\n// Method under test:{row['methodJavadoc']}\n{row['methodSourceCode']}{row['additionalInfo']}"

    if pre_processing:
        # Mapping for oracle types
        oracle_type_mapping = {
            "EXCEPT_POST": "Exceptional postcondition",
            "NORMAL_POST": "Postcondition",
            "PRE": "Precondition"
        }
        # Map empty cells to empty strings
        df_dataset.fillna('', inplace=True)
        # Delete spurious columns for predicting the next token class
        df_dataset = df_dataset.drop(['id', 'oracleId', 'projectName', 'packageName', 'className', 'classJavadoc', 'classSourceCode', 'token'], axis=1)
        # Drop duplicates
        df_dataset.drop_duplicates(inplace=True)
        # Pre-process dataset
        df_dataset['oracleType'] = df_dataset['oracleType'].map(oracle_type_mapping)
        df_dataset['javadocTag'] = df_dataset['javaDocTag'].apply(lambda tag: re.sub("\n *", " ", tag))
        df_dataset['methodJavadoc'] = df_dataset['methodJavadoc'].apply(lambda javadoc: "\n" + re.sub("\n +", "\n ", javadoc.strip()) if re.sub("\n +", "\n ", javadoc.strip()) != "" else "")
        df_dataset['eligibleTokens'] = df_dataset['eligibleTokens'].apply(lambda tokenInfo: [t['token'] for t in tokenInfo if t["token"] != ";"])
        df_dataset['additionalInfo'] = df_dataset['eligibleTokens'].apply(lambda tokenInfo: "\n\n// Additional context:\n" + [re.sub("\n +", "\n ", t["tokenInfo"][2].strip()) for t in tokenInfo].join("\n") if any(t["tokenClass"] in ["MethodName", "ClassField"] for t in tokenInfo) else "")
        #df_dataset['token'] = df_dataset['token'].str.replace(";", ");")
    assert len(df_dataset) == 1
    df_dataset['src'] = df_dataset.apply(generate_src, axis=1)
    return df_dataset


def tokenize_input(
        src,
        tokenizer,
        max_src_length: int = 2048
):
    def preprocess_single(example):
        bos_token = [tokenizer.bos_token_id]
        input_ids = bos_token + tokenizer(example["src"], max_length=max_src_length, truncation=True)["input_ids"]
        attention_mask = [1] * len(input_ids)
        return {
            "input_ids": input_ids,
            "attention_mask": attention_mask,
        }

    src_dataset = Dataset.from_list(src)

    predict_dataset = src_dataset.map(
        preprocess_single,
        desc="Running tokenizer on prediction dataset"
    )
    predict_dataset.set_format(type='torch', columns=['input_ids', 'attention_mask'])
    return predict_dataset


def next_token(
        device,
        filename: str,
        transformer_type: Type[TransformerType],
        model: Type[PreTrainedModel],
        tokenizer: PreTrainedTokenizer,
        max_src_length,
        max_tgt_length,
        pre_processing: bool = True,
):
    # Read json file
    print("Predict next token")
    df_dataset = pd.read_json(filename)
    # Pre-process dataset
    print("Processing dataset")
    df_dataset = process_dataset(df_dataset, pre_processing)

    # Get input and eligible tokens
    src = df_dataset['src'].tolist()
    eligible_tokens = df_dataset['eligibleTokens'].tolist() if not df_dataset['oracleSoFar'] == '' else oracle_evaluator_eligible_tokens

    # Tokenize input
    print("Tokenize model input")
    predict_dataset = tokenize_input(
        src,
        tokenizer,
        max_src_length
    )
    # Generate data loader
    dl_src = DataLoader(
        predict_dataset,
        collate_fn=DataCollatorForSeq2Seq(tokenizer, pad_to_multiple_of=8, padding=True)
    )
    # Predict next token
    print("Predict next token")

    next_token = predict_next(
        device,
        model,
        dl_src,
        tokenizer,
        eligible_tokens,
        transformer_type,
        max_tgt_length
    )

    if df_dataset['oracleSoFar'] == '':
        if next_token == "// No assertion possible":
            return ";"
        elif next_token == "assertTrue(":
            df_dataset.loc[:, 'oracleSoFar'] = "assertTrue("
            df_dataset.to_json(filename, orient='records')
            return next_token(
                device,
                filename,
                transformer_type,
                model,
                tokenizer,
                max_src_length,
                max_tgt_length,
                False
            )
        else:
            raise Exception(f"Predicted token not in eligible tokens: {next_token}")
