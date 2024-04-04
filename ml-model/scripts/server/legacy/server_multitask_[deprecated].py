import argparse
import os

import torch
from safetensors.torch import load_model
from transformers import PreTrainedModel
from typing import Type
from src.pretrained.ModelClasses import ModelClasses
from src.processors.DataProcessor import DataProcessor
from src.server.ServerMultitask import ServerMultitask
from src.parser.ArgumentParser import ArgumentParser
from src.types.ClassificationType import ClassificationType
from src.types.DeviceType import DeviceType
from src.types.TrattoModelType import TrattoModelType
from src.types.TransformerType import TransformerType
from src.utils import utils


def resume_checkpoint(
        pt_model: Type[PreTrainedModel],
        checkpoint_path: str,
        device
):
    if checkpoint_path.endswith(".bin"):
        pt_model.load_state_dict(torch.load(checkpoint_path, map_location=torch.device(device)))
    elif checkpoint_path.endswith(".pt"):
        checkpoint = torch.load(checkpoint_path, map_location=torch.device(device))
        pt_model.load_state_dict(checkpoint['model_state_dict'])
    elif checkpoint_path.endswith(".safetensors"):
        load_model(pt_model, checkpoint_path)

def setup_model(
        device: str,
        model_type: str,
        tokenizer_name: str,
        model_name_or_path: str,
        checkpoint_path: str,
        config_name: str = None
):
    config_class, model_class, tokenizer_class = ModelClasses.getModelClass(model_type)
    # Setup tokenizer
    tokenizer = tokenizer_class.from_pretrained(tokenizer_name)
    # Setup model
    config = config_class.from_pretrained(config_name if config_name else model_name_or_path)
    pt_model = model_class.from_pretrained(model_name_or_path, config=config)
    pt_model.resize_token_embeddings(len(tokenizer))
    pt_model.to(device)
    abs_checkpoint_path = os.path.join(
        os.path.dirname(os.path.abspath(__file__)),
        '..',
        '..',
        checkpoint_path
    )
    resume_checkpoint(pt_model, abs_checkpoint_path, device)
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
        device,
        args.model_type_multitask,
        args.tokenizer_name_multitask,
        args.model_name_or_path_multitask,
        args.checkpoint_path_multitask,
        args.config_name_multitask
    )
    # Get transformer type
    transformer_type = TransformerType(args.transformer_type_multitask.upper())
    # Instantiate server
    server = ServerMultitask(
        device,
        ClassificationType(args.classification_type_oracles.upper()),
        ClassificationType(args.classification_type_token_classes.upper()),
        ClassificationType(args.classification_type_token_values.upper()),
        transformer_type,
        model,
        tokenizer,
    )
    # Run server
    server.run(args.port)