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
    ArgumentParser.add_training_arguments(parser)
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
        True if args.pre_processing == "True" else False
    )
    # Pre-processing data
    logger.print_pre_processing()
    data_processor.pre_processing(True if args.save_pre_processing == "True" else False)
    # Initialize statistics
    stats = {}
    # Get the train, validation, and test sorted datasets
    train_dataset = data_processor.get_tokenized_dataset(DatasetType.TRAINING)
    val_dataset = data_processor.get_tokenized_dataset(DatasetType.VALIDATION)
    # Create instance of training, validation, and test dataloaders
    dl_train = DataLoader(
        train_dataset,
        sampler=RandomSampler(train_dataset),
        batch_size=args.batch_size
    )
    dl_val = DataLoader(
        val_dataset,
        sampler=RandomSampler(val_dataset),
        batch_size=args.batch_size
    )
    # Setup model
    config = config_class.from_pretrained(
        args.config_name if args.config_name else args.model_name_or_path,
        num_labels=data_processor.get_num_labels(),
        finetuning_task=args.task_name
    )
    model = model_class.from_pretrained(
        args.model_name_or_path,
        config=config
    )
    # Resize tokenizer after vocabulary enrichment
    model.resize_token_embeddings(len(tokenizer))
    model.to(device)
    # Prepare optimizer and schedule (linear warmup and decay)
    no_decay = ['bias', 'LayerNorm.weight']
    optimizer_grouped_parameters = [
        {'params': [p for n, p in model.named_parameters() if not any(nd in n for nd in no_decay)],
         'weight_decay': args.weight_decay},
        {'params': [p for n, p in model.named_parameters() if any(nd in n for nd in no_decay)],
         'weight_decay': 0.0}
    ]
    optimizer = AdamW(optimizer_grouped_parameters, lr=args.learning_rate, eps=args.adam_epsilon)
    # Instantiate trainer
    classifier_ids_labels = data_processor.get_encoder_ids_labels()
    training_steps = len(dl_train) // args.accumulation_steps * args.num_epochs
    scheduler = get_linear_schedule_with_warmup(optimizer, args.warmup_steps, training_steps)
    checkpoint_path = os.path.join(
        args.output_dir,
        "checkpoints",
        f"lr_{args.learning_rate}",
        f"batch_{args.batch_size}",
        f"epochs_{args.num_epochs}"
    )
    oracle_trainer = OracleTrainer(
        model,
        optimizer,
        dl_train,
        dl_val,
        classifier_ids_labels,
        classification_type,
        transformer_type,
        checkpoint_path,
        scheduler,
        tokenizer
    )
    logger.print_training_phase()
    try:
        # Train the model
        stats[f"training"] = oracle_trainer.train(
            args.num_epochs,
            args.checkpoint_steps,
            device,
            args.accumulation_steps,
            args.max_grad_norm
        )
        # Save the model, optimizer, scheduler and statistics
        logger.print_save_model()
        utils.export_stats(
            os.path.join(args.output_dir,
                         f"training_stats_{args.batch_size}_{args.learning_rate}_{args.num_epochs}.json"),
            {
                **stats[f"training"],
                "batch_size": args.batch_size,
                "lr": args.learning_rate,
                "num_epochs": args.num_epochs
            }
        )
    except RuntimeError as e:
        print("Runtime Exception...")
        if device.type == "cuda":
            # Release memory
            del model
            utils.release_memory()
        raise e
    # Release memory
    del model
    print("        " + "-" * 18)
    print("Training completed")
    utils.release_memory()

if __name__ == "__main__":
    main()