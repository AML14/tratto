import argparse
import os

import torch
from transformers import PreTrainedModel
from typing import Type
from src.pretrained.ModelClasses import ModelClasses
from src.server.Server import Server
from src.parser.ArgumentParser import ArgumentParser
from src.types.DeviceType import DeviceType
from src.types.TrattoModelType import TrattoModelType
from src.utils import utils


def resume_checkpoint(
        pt_model: Type[PreTrainedModel],
        checkpoint_path: str,
        device
):
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

    if tratto_model_type == TrattoModelType.TOKEN_CLASSES:
        # Map token class names
        _, value_mappings = utils.import_json(
            os.path.join(
                os.path.dirname(os.path.abspath(__file__)),
                '..',
                '..',
                'src',
                'resources',
                'tokenClassesValuesMapping.json'
            )
        )
        vocab = tokenizer.get_vocab()
        for new_word in value_mappings.values():
            for new_sub_word in new_word.split("_"):
                if not new_sub_word in vocab.keys():
                    tokenizer.add_tokens([new_sub_word])
    # Setup model
    config = config_class.from_pretrained(config_name if config_name else model_name_or_path)
    pt_model = model_class.from_pretrained(model_name_or_path, config=config)
    if tratto_model_type == TrattoModelType.TOKEN_CLASSES:
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