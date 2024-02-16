import argparse
import os

import torch
import random
import numpy as np
from torch.utils.data import DataLoader, RandomSampler
from torch.optim import AdamW
from transformers import get_linear_schedule_with_warmup

from src.model.OracleTrainer import OracleTrainer
from src.types.ClassificationType import ClassificationType
from src.types.DatasetType import DatasetType
from src.types.DeviceType import DeviceType
from src.types.TrattoModelType import TrattoModelType
from src.types.TransformerType import TransformerType
from src.utils import logger
from src.parser.ArgumentParser import ArgumentParser
from src.processors.DataProcessor import DataProcessor
from src.pretrained.ModelClasses import ModelClasses
from src.utils import utils


def set_seed(device: str, seed: int = 42):
    """
    The method sets up seeds for reproducibility.

    Parameters
    ----------
    device: str
        The identifier of the device where to perform the training or the evaluation of the model
    seed: int
        The seed value.
    """
    random.seed(seed)
    np.random.seed(seed)
    torch.manual_seed(seed)
    if not device == "cpu":
        torch.cuda.manual_seed_all(seed)

def main():
    """
    The main method instantiate the model and execute the training and testing phases.
    """
    # Parse arguments
    parser = argparse.ArgumentParser()
    ArgumentParser.add_pre_process_dataset_arguments(parser)
    args = parser.parse_args()
    # Set the RAPIDS cuDF library if available
    if args.rapids_cudf == "True":
        utils.set_rapids_available()
    # Set TRATTO model, classification, and transformer types
    classification_type = ClassificationType(args.classification_type.upper())
    transformer_type = TransformerType(args.transformer_type.upper())
    tratto_model_type = TrattoModelType(args.tratto_model_type.upper())
    # Logging - welcome message
    logger.print_welcome(classification_type, tratto_model_type)
    # Logging - load gpu message
    logger.print_load_gpu()
    # Connect to device
    device = utils.connect_to_device(DeviceType.GPU)
    # Set up seeds for reproducibility
    set_seed(device, args.seed)
    # Get configuration class, model class, and tokenizer class from the corresponding model type
    config_class, model_class, tokenizer_class = ModelClasses.getModelClass(args.model_type)
    # Setup tokenizer
    tokenizer = tokenizer_class.from_pretrained(args.tokenizer_name)
    # Create DataProcessor instance
    data_processor = DataProcessor(
        args.train_path,
        args.validation_path,
        tokenizer,
        transformer_type,
        classification_type,
        tratto_model_type,
        pre_processing=True,
        is_multitask=True if args.is_multitask == "True" else False
    )
    # Pre-processing data
    logger.print_pre_processing()
    data_processor.pre_processing(True)

if __name__ == "__main__":
    main()