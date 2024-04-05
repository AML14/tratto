import json
import torch
import numpy as np
import re
from datasets import Dataset
from torch.utils.data import DataLoader
from transformers import PreTrainedModel, PreTrainedTokenizer, DataCollatorForSeq2Seq
from typing import Type
from src.exceptions.TokenNotFoundException import TokenNotFoundException
from src.types.TransformerType import TransformerType

# Oracle evaluator eligible tokens
assert_token = "assertTrue("
no_assertion_token = "// No assertion possible"
oracle_evaluator_eligible_tokens = [assert_token, no_assertion_token]

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
    model.eval()
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
                raise TokenNotFoundException(f"Predicted token not in eligible tokens: {token}", token)
            else:
                raise Exception("Encoder predictions not implemented yet")


def process_dataset(
        tokens_datapoint: dict
):
    # Mapping for oracle types
    oracle_type_mapping = {
        "EXCEPT_POST": "Exceptional postcondition",
        "NORMAL_POST": "Postcondition",
        "PRE": "Precondition"
    }
    # Map the oracle type to the corresponding description
    oracle_type = oracle_type_mapping.get(tokens_datapoint['oracleType'], "")
    # Process the javadoc tag (flat it to a single line)
    javadoc_tag = re.sub("\n *", " ", tokens_datapoint['javadocTag'])
    # Process the method javadoc
    method_javadoc = tokens_datapoint['methodJavadoc'].strip()
    if not method_javadoc == "":
        method_javadoc = "\n" + re.sub("\n +", "\n ", method_javadoc)
    # Get the method source code
    method_source_code = tokens_datapoint["methodSourceCode"]
    # Get the oracle so far
    oracle_so_far = tokens_datapoint["oracleSoFar"]
    # Process additional information
    additional_info = ""
    # Check if among the eligible tokens at least one refers to a "MethodName" or "ClassField"
    if any(t["tokenClass"] in ["MethodName", "ClassField"] for t in tokens_datapoint['eligibleTokens']):
        # Add additional information
        additional_info += "\n\n// Additional context:\n"
        for t in tokens_datapoint['eligibleTokens']:
            if t["tokenClass"] in ["MethodName", "ClassField"]:
                additional_info += re.sub("\n +", "\n ", t["tokenInfo"][2].strip()) + "\n"
    # Map eligible tokens to a list of raw tokens (removing additional information from them)
    eligible_tokens = [t['token'] for t in tokens_datapoint['eligibleTokens']]
    # If the oracle is at the beginning, the ';' is not an eligible token ("assertTrue(;" is not a valid assertion)
    if oracle_so_far == assert_token:
        eligible_tokens.remove(";")
    # If the oracle is empty, the only eligible tokens are 'assertTrue(' and '// No assertion possible'
    # The model is used as an oracle evaluator
    elif oracle_so_far == "":
        eligible_tokens = oracle_evaluator_eligible_tokens
    # If the oracle is not empty, the eligible token ';' is replaced with ');'
    else:
        oracle_so_far = assert_token + oracle_so_far
        eligible_tokens = [t.replace(";", ");") for t in eligible_tokens]
    # Generate input string
    src = f'// {oracle_type}: "{javadoc_tag}"\n// Next possible tokens: {eligible_tokens}\n// Assertion:\n{oracle_so_far}<FILL_ME>\n\n// Method under test:{method_javadoc}\n{method_source_code}{additional_info}'
    # Return the input and the eligible tokens
    return src, eligible_tokens


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
        max_tgt_length
):
    # Read json file
    print("Predict next token")
    with open(filename, 'r') as json_file:
        tokens_datapoint = json.load(json_file)

    # Pre-process dataset
    print("Processing dataset")
    src, eligible_tokens = process_dataset(tokens_datapoint)

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

    if tokens_datapoint['oracleSoFar'] == '':
        if next_token == no_assertion_token:
            return ";"
        elif next_token == assert_token:
            tokens_datapoint['oracleSoFar'] = "assertTrue("
            with open(filename, 'w') as json_file:
                json.dump(tokens_datapoint, json_file, indent=4)
            return next_token(
                device,
                filename,
                transformer_type,
                model,
                tokenizer,
                max_src_length,
                max_tgt_length
            )
        else:
            raise Exception(f"Predicted token not in eligible tokens: {next_token}")
    else:
        return next_token if next_token != ";)" else ";"