import argparse
import os

import torch
from transformers import PreTrainedModel
from typing import Type
from src.pretrained.ModelClasses import ModelClasses
from src.processors.DataProcessor import DataProcessor
from src.server.Server import Server
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


def setup_model(
        device: str,
        model_type: str,
        tokenizer_name: str,
        model_name_or_path: str,
        checkpoint_path: str,
        tratto_model_type: Type[TrattoModelType],
        config_name: str = None
):
    config_class, model_class, tokenizer_class = ModelClasses.getModelClass(model_type)
    # Setup tokenizer
    tokenizer = tokenizer_class.from_pretrained(tokenizer_name)
    if tratto_model_type in [TrattoModelType.TOKEN_CLASSES, TrattoModelType.TOKEN_VALUES]:
        # Enrich vocabulary
        DataProcessor.enrich_vocabulary(tokenizer, tratto_model_type)
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
    # Setup oracles model
    model_oracles, tokenizer_oracles = setup_model(
        device,
        args.model_type_oracles,
        args.tokenizer_name_oracles,
        args.model_name_or_path_oracles,
        args.checkpoint_path_oracles,
        TrattoModelType.ORACLES,
        args.config_name_oracles
    )
    # Setup tokenClasses model
    model_token_classes, tokenizer_token_classes = setup_model(
        device,
        args.model_type_token_classes,
        args.tokenizer_name_token_classes,
        args.model_name_or_path_token_classes,
        args.checkpoint_path_token_classes,
        TrattoModelType.TOKEN_CLASSES,
        args.config_name_token_classes
    )
    # Setup tokenValues model
    model_token_values, tokenizer_token_values = setup_model(
        device,
        args.model_type_token_values,
        args.tokenizer_name_token_values,
        args.model_name_or_path_token_values,
        args.checkpoint_path_token_values,
        TrattoModelType.TOKEN_VALUES,
        args.config_name_token_values
    )
    # Get transformer type
    transformer_type_oracles = TransformerType(args.transformer_type_oracles.upper())
    transformer_type_token_classes = TransformerType(args.transformer_type_token_classes.upper())
    transformer_type_token_values = TransformerType(args.transformer_type_token_values.upper())
    # Instantiate server
    server = Server(
        device,
        ClassificationType(args.classification_type_oracles.upper()),
        ClassificationType(args.classification_type_token_classes.upper()),
        ClassificationType(args.classification_type_token_values.upper()),
        transformer_type_oracles,
        transformer_type_token_classes,
        transformer_type_token_values,
        model_oracles,
        model_token_classes,
        model_token_values,
        tokenizer_oracles,
        tokenizer_token_classes,
        tokenizer_token_values
    )
    # Run server
    server.run(args.port)