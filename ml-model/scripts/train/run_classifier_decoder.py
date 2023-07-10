import argparse
import os

import torch
import random
import numpy as np
from torch.utils.data import DataLoader, RandomSampler
from transformers import AdamW, get_linear_schedule_with_warmup

from src.types.ClassificationType import ClassificationType
from src.types.DatasetType import DatasetType
from src.types.DeviceType import DeviceType
from src.types.TrattoModelType import TrattoModelType
from src.model.OracleTrainerDecoder import OracleTrainerDecoder
from src.utils import logger
from src.parser.ArgumentParser import ArgumentParser
from src.processors.DataProcessorDecoder import DataProcessorDecoder
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

    # Set TRATTO model and classification types
    tratto_model_type = TrattoModelType.TOKEN_CLASSES
    classification_type = ClassificationType.LABEL_PREDICTION
    # Set cross-validation
    cv = True if args.folds > 1 else False

    if args.classification_type is not None:
        try:
            classification_type = ClassificationType(args.classification_type.upper())
        except:
            print(
                f"Classification type {args.classification_type} not recognized. Classification type {ClassificationType.CATEGORY_PREDICTION} used.")
    if args.model_type is not None:
        try:
            tratto_model_type = TrattoModelType(args.tratto_model_type.upper())
        except:
            print(f"Model type {args.tratto_model_type} not recognized. Classification type {TrattoModelType.TOKEN_CLASSES} used.")

    logger.print_welcome(classification_type, tratto_model_type)
    # Logging - load gpu
    logger.print_load_gpu()
    # Connect to device
    device = utils.connect_to_device(DeviceType.GPU)
    # Set up seeds for reproducibility
    set_seed(device, args.seed)

    # Get configuration class, model class, and tokenizer class from the corresponding model type
    config_class, model_class, tokenizer_class = ModelClasses.getModelClass(args.model_type)
    # Setup tokenizer
    tokenizer = tokenizer_class.from_pretrained(args.tokenizer_name)

    logger.print_load_dataset(args.data_dir)
    # Create DataProcessor instance
    data_processor = DataProcessorDecoder(
        args.data_dir,
        args.test_ratio,
        tokenizer,
        classification_type,
        tratto_model_type,
        args.folds              # if folds = 1 no cross-validation is performed
    )
    # Pre-processing data
    logger.print_pre_processing()
    data_processor.pre_processing()
    # Process the data
    data_processor.processing()

    # Initialize statistics
    stats = {}
    # Training (stratified cross-validation training, if folds > 1)
    for fold in range(args.folds):
        if cv:
            logger.print_cross_validation(fold)
        # Get the train, validation, and test sorted datasets
        train_dataset = data_processor.get_tokenized_dataset(DatasetType.TRAINING, fold)
        val_dataset = data_processor.get_tokenized_dataset(DatasetType.VALIDATION, fold)
        test_dataset = data_processor.get_tokenized_dataset(DatasetType.TEST)

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
        dl_test = DataLoader(
            test_dataset,
            sampler=RandomSampler(test_dataset),
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
        classifier_ids_labels = data_processor.get_ids_labels()
        classifier_ids_classes = data_processor.get_ids_classes()
        training_steps = len(dl_train) // args.accumulation_steps * args.num_epochs
        scheduler = get_linear_schedule_with_warmup(optimizer, args.warmup_steps, training_steps)
        checkpoint_path = os.path.join(
            args.output_dir,
            f"checkpoints_{fold}" if cv else "checkpoints",
            f"lr_{args.learning_rate}",
            f"batch_{args.batch_size}",
            f"epochs_{args.num_epochs}"
        )
        oracle_trainer = OracleTrainerDecoder(
            model,
            optimizer,
            dl_train,
            dl_val,
            dl_test,
            classifier_ids_labels,
            classifier_ids_classes,
            classification_type,
            checkpoint_path,
            scheduler,
            tokenizer
        )

        if args.do_train:
            logger.print_training_phase()
            try:
                # Train the model
                stats[f"fold_{fold}"] = oracle_trainer.train(
                    args.num_epochs,
                    args.save_steps,
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
                        **stats[f"fold_{fold}"],
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
        elif args.do_predict:
            if not os.path.exists(args.resume_checkpoint_filename):
                raise ValueError(f"Impossible to resume checkpoint - File not found {args.resume_checkpoint_filename} (or argument --resume_checkpoint_filename not provided).")
            best_checkpoint = utils.resume_checkpoint(args.resume_checkpoint_filename, device)
            # Load the model state dict
            model.load_state_dict(best_checkpoint['model_state_dict'])
            # Load the optimizer state dict
            optimizer.load_state_dict(best_checkpoint['optimizer_state_dict'])
            # Load the scheduler state dict
            scheduler.load_state_dict(best_checkpoint['scheduler_state_dict'])
        # Perform testing phase
        if args.do_predict:
            stats_test = oracle_trainer.evaluation(device)
            # Check if the directory exists, to save the statistics of the training
            if not os.path.exists(args.output_dir):
                # If the path does not exist, create it
                os.makedirs(args.output_dir)
            utils.export_stats(os.path.join(args.output_dir, "stats_test.json"), stats_test)
        # Release memory
        del model
        print("        " + "-" * 18)
        print("Training completed")
    utils.release_memory()

if __name__ == "__main__":
    main()