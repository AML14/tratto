import argparse
import os
import torch
from accelerate import init_empty_weights, load_checkpoint_and_dispatch
from safetensors.torch import load_model
from transformers import PreTrainedModel
from typing import Type
from src.pretrained.ModelClasses import ModelClasses
from src.server.ServerMultitask import ServerMultitask
from src.parser.ArgumentParser import ArgumentParser
from src.types.DeviceType import DeviceType
from src.types.TransformerType import TransformerType
from src.utils import utils


def setup_model(
        model_type: str,
        tokenizer_name: str,
        model_name_or_path: str,
        config_name_or_path: str,
        checkpoint_path: str
):
    config_class, model_class, tokenizer_class = ModelClasses.getModelClass(model_type)
    config = config_class.from_pretrained(model_name_or_path)
    print(config)
    # Setup tokenizer
    tokenizer = tokenizer_class.from_pretrained(tokenizer_name)
    tokenizer.add_bos_token = False
    tokenizer.add_eos_token = False
    tokenizer.add_special_tokens({"pad_token": "<pad>"})
    # Setup model
    # Creates a model with uninitialized weights, minimizing memory usage
    with init_empty_weights():
        pt_model = model_class(config)
    abs_checkpoint_path = os.path.join(
        os.path.dirname(os.path.abspath(__file__)),
        '..',
        '..',
        checkpoint_path
    )
    pt_model = load_checkpoint_and_dispatch(pt_model, abs_checkpoint_path, device_map='auto')
    pt_model.resize_token_embeddings(len(tokenizer))
    # pt_model.to(device)
    return pt_model, tokenizer


if __name__ == '__main__':
    # Parse arguments
    parser = argparse.ArgumentParser()
    ArgumentParser.add_server_arguments(parser)
    args = parser.parse_args()
    # Connect to device
    device = utils.connect_to_device(DeviceType.GPU)
    # Setup model
    model, tokenizer = setup_model(
        args.model_type_multitask,
        args.tokenizer_name_multitask,
        args.model_name_or_path_multitask,
        args.config_name_or_path_multitask,
        args.checkpoint_path_multitask
    )
    # Get transformer type
    transformer_type = TransformerType(args.transformer_type_multitask.upper())
    # Instantiate server
    server = ServerMultitask(
        device,
        transformer_type,
        model,
        tokenizer,
        args.max_src_length,
        args.max_tgt_length
    )
    # Run server
    server.run(args.port)