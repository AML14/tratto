import argparse
import os

import torch
from transformers import PreTrainedModel
from typing import Type
from src.pretrained.ModelClasses import ModelClasses
from src.server.Server import Server
from src.parser.ArgumentParser import ArgumentParser
from src.types.DeviceType import DeviceType
from src.utils import utils


def resume_checkpoint(
        pt_model: Type[PreTrainedModel],
        checkpoint_path: str
):
    checkpoint = torch.load(checkpoint_path)
    return pt_model.load_state_dict(checkpoint['model_state_dict'])


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
    # Add tokens to tokenizer vocabulary
    tokenizer.add_tokens([
        'multiple', 'single', 'variable', 'punctuation', 'value', 'arrays', 'operator', 'Class', 'Name', 'Semicolon',
        'Method', 'Argument', 'Open', 'Parenthesis', 'This', 'True', 'Arraysclass', 'Logical', 'Equals', 'Not',
        'Period', 'Instanceof', 'Null', 'Integer', 'Close', 'Greater', 'Less', 'Result', 'Float', 'Bit', 'Negate',
        'False', 'Colon', 'Question', 'Arithmetical', 'Field', 'Logic', 'Shift', 'Boolean', 'Streammethod',
        'Matchmethodvariable', 'Matchmethod', 'Rightarrow', 'String', 'Comma', 'Modifier'
    ])
    # Setup model
    config = config_class.from_pretrained(config_name if config_name else model_name_or_path)
    pt_model = model_class.from_pretrained(args.model_name_or_path, config=config)
    pt_model.resize_token_embeddings(len(tokenizer))
    pt_model.to(device)
    model = resume_checkpoint(pt_model, checkpoint_path)
    return model, tokenizer


if __name__ == '__main__':
    # Parse arguments
    parser = argparse.ArgumentParser()
    ArgumentParser.add_training_arguments(parser)
    args = parser.parse_args()
    # Connect to device
    device = utils.connect_to_device(DeviceType.GPU)
    # Setup tokenClasses model
    model_token_classes, tokenizer_token_classes = setup_model(
        device,
        args.model_type_token_classes,
        args.tokenizer_name_token_classes,
        args.model_name_or_path_token_classes,
        args.checkpoint_path_token_classes,
        args.config_name_token_classes
    )
    # Setup tokenValues model
    model_token_values, tokenizer_token_values = setup_model(
        device,
        args.model_type_token_values,
        args.tokenizer_name_token_values,
        args.model_name_or_path_token_values,
        args.checkpoint_path_token_values,
        args.config_name_token_values
    )
    # Instantiate server
    server = Server(
        device,
        model_token_classes,
        model_token_values,
        tokenizer_token_classes,
        tokenizer_token_values
    )
    # Run server
    server.run(args.port)