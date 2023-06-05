import argparse
import json
import logging
import os

import torch
import random
import numpy as np
from torch.nn import CrossEntropyLoss
from torch.utils.data import DataLoader, SequentialSampler, WeightedRandomSampler
from transformers import RobertaConfig, RobertaForSequenceClassification, RobertaTokenizer, AdamW, get_linear_schedule_with_warmup

from src.enums.ClassificationType import ClassificationType
from src.enums.DatasetType import DatasetType
from src.enums.DeviceType import DeviceType
from src.enums.FileFormat import FileFormat
from src.enums.FileName import FileName
from src.enums.ModelType import ModelType
from src.enums.Path import Path
from src.model.OracleClassifier import OracleClassifier
from src.model.OracleTrainer import OracleTrainer
from src.model.Printer import Printer
from src.parser.ArgumentParser import ArgumentParser
from src.precessors.DataProcessor import DataProcessor
from src.pretrained.ModelClasses import ModelClasses
from src.utils import utils

logger = logging.getLogger(__name__)

def set_seed(n_gpu: int, seed: int = 42):
    random.seed(seed)
    np.random.seed(seed)
    torch.manual_seed(seed)
    if n_gpu > 0:
        torch.cuda.manual_seed_all(seed)

def main():
    # parse bash arguments
    parser = argparse.ArgumentParser()
    ArgumentParser.add_arguments(parser)
    args = parser.parse_args()

    # Set TRATTO model and classification types
    model_type = ModelType.TOKEN_CLASSES
    classification_type = ClassificationType.CATEGORY_PREDICTION
    cv = False
    if args.classification_type is not None:
        try:
            classification_type = ClassificationType(args.classification_type.upper())
        except:
            print(
                f"Classification type {args.classification_type} not recognized. Classification type {ClassificationType.CATEGORY_PREDICTION} used.")
    if args.model_type is not None:
        try:
            model_type = ModelType(args.tratto_model_type.upper())
        except:
            print(
                f"Model type {args.model_type} not recognized. Classification type {ModelType.TOKEN_CLASSES} used.")
    if args.cross_validation:
        if args.folds <= 1:
            raise ValueError("Folds parameter must be > 1 to perform cross-validation.")
        cv = True

    Printer.print_welcome(classification_type, model_type)

    Printer.print_load_gpu()
    # connect to device
    device, n_gpu = utils.connect_to_device(DeviceType.GPU)

    # Set seeds for reproducibility
    set_seed(n_gpu, args.seed)

    # Load pretrained model and tokenizer
    if args.local_rank not in [-1, 0]:
        # Make sure only the first process in distributed training will download model & vocab
        torch.distributed.barrier()

    # Get configuration class, model class, and tokenizer class from the corresponding model type
    config_class, model_class, tokenizer_class = ModelClasses.getModelClass(args.model_type)

    # Setup tokenizer
    if args.tokenizer_name:
        tokenizer_name = args.tokenizer_name
    elif args.model_name_or_path:
        tokenizer_name = 'roberta-base'
    tokenizer = tokenizer_class.from_pretrained(tokenizer_name)

    Printer.print_load_dataset(args.data_dir)
    # Create DataProcessor instance
    data_processor = DataProcessor(
        args.data_dir,
        args.batch_size,
        args.test_ratio,
        tokenizer,
        args.folds,         # if folds = 1 no cross-validation is performed
        classification_type,
        model_type
    )
    # Pre-processing data
    Printer.print_pre_processing()
    data_processor.pre_processing()
    # Process the data
    data_processor.processing(cv)

    # Initialize statistics
    stats = {}

    # Stratified cross-validation training
    for fold in range(args.folds):
        if cv:
            print("        " + "-" * 25)
            print(f"        Cross-validation | Fold {fold + 1}")
            print("        " + "-" * 25)
        # Get the train, validation, and test sorted datasets
        # Printer.print_dataset_generation()
        train_dataset = data_processor.get_tokenized_dataset(DatasetType.TRAINING, fold)
        val_dataset = data_processor.get_tokenized_dataset(DatasetType.VALIDATION, fold)
        test_dataset = data_processor.get_tokenized_dataset(DatasetType.TEST)

        # Compute weights
        if classification_type == ClassificationType.CATEGORY_PREDICTION:
            if model_type == ModelType.TOKEN_CLASSES:
                label_weights = "tokenClass"
            else:
                label_weights = "token"
        else:
            label_weights = "label"

        class_weights = data_processor.compute_weights(label_weights)
        train_dataset_weights = [(class_weights * np.array(data_point[2])).sum() for data_point in train_dataset]
        val_dataset_weights = [(class_weights * np.array(data_point[2])).sum() for data_point in val_dataset]
        test_dataset_weights = [(class_weights * np.array(data_point[2])).sum() for data_point in test_dataset]

        # The cross-entropy loss function is commonly used for classification tasks
        loss_fn = CrossEntropyLoss(weight=torch.tensor(class_weights).to(device))
        # loss_fn = CrossEntropyLoss()

        # Create instance of training, validation, and test dataloaders
        dl_train = DataLoader(
            train_dataset,
            sampler=WeightedRandomSampler(train_dataset_weights, len(train_dataset)),
            batch_size=args.batch_size
        )
        dl_val = DataLoader(
            val_dataset,
            sampler=WeightedRandomSampler(val_dataset_weights, len(val_dataset)),
            batch_size=args.batch_size
        )
        dl_test = DataLoader(
            test_dataset,
            sampler=WeightedRandomSampler(test_dataset_weights, len(test_dataset)),
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

        if n_gpu > 1:
            model = torch.nn.DataParallel(model)
        if args.local_rank != -1:
            model = torch.nn.parallel.DistributedDataParallel(
                model,
                device_ids=[args.local_rank],
                output_device=args.local_rank,
                find_unused_parameters=True
            )

        # Prepare optimizer and schedule (linear warmup and decay)
        no_decay = ['bias', 'LayerNorm.weight']
        optimizer_grouped_parameters = [
            {'params': [p for n, p in model.named_parameters() if not any(nd in n for nd in no_decay)],
             'weight_decay': args.weight_decay},
            {'params': [p for n, p in model.named_parameters() if any(nd in n for nd in no_decay)],
             'weight_decay': 0.0}
        ]
        optimizer = AdamW(optimizer_grouped_parameters, lr=args.learning_rate, eps=args.adam_epsilon)

        # Instantiation of the trainer
        classifier_ids_labels = data_processor.get_ids_labels()
        classifier_ids_classes = data_processor.get_ids_classes()
        # Define checkpoint path
        checkpoint_path = os.path.join(
            Path.OUTPUT,
            f"checkpoints_{fold}" if cv else "checkpoints",
            f"lr_{args.learning_rate}",
            f"batch_{args.batch_size}",
            f"epochs_{args.num_epochs}"
        )

        training_steps = len(dl_train) // args.accumulation_steps * args.num_epochs
        scheduler = get_linear_schedule_with_warmup(optimizer, args.warmup_steps, training_steps)

        oracle_trainer = OracleTrainer(
            model,
            loss_fn,
            optimizer,
            dl_train,
            dl_val,
            dl_test,
            classifier_ids_labels,
            classifier_ids_classes,
            classification_type,
            checkpoint_path,
            scheduler
        )


        try:
            # Train the model
            stats[f"fold_{fold}"] = oracle_trainer.train(
                args.num_epochs,
                args.save_steps,
                device,
                n_gpu,
                args.accumulation_steps,
                args.max_grad_norm
            )
        except RuntimeError as e:
            print("Runtime Exception...")
            if device.type == "cuda":
                # Release memory
                del model
                utils.release_memory()
            raise e
        # Perform testing phase
        stats_test = oracle_trainer.evaluation(device)
        stats[f"fold_{fold}"] = {
            **stats[f"fold_{fold}"],
            **stats_test
        }
        # Check if the directory exists, to save the statistics of the training
        if not os.path.exists(Path.OUTPUT.value):
            # If the path does not exists, create it
            os.makedirs(Path.OUTPUT.value)
        # Save the statistics in json format
        with open(
                os.path.join(
                    Path.OUTPUT.value,
                    f"{FileName.LOSS_ACCURACY.value}_fold_{fold}.{FileFormat.JSON.value}"
                ),
                "w"
        ) as loss_file:
            data = {
                **stats[f"fold_{fold}"],
                "batch_size": args.batch_size,
                "lr": args.learning_rate,
                "num_epochs": args.num_epochs
            }
            json.dump(data, loss_file)
        # Close the file
        loss_file.close()
        # ## Save the statistics and the trained model
        #
        # Saves the statistics for future analysis, and the trained model for future use or improvements.
        # Saving the model we save the values of all the weights. In other words, we create a snapshot of
        # the state of the model, after the training.
        Printer.print_save_model()
        torch.save(model, os.path.join(Path.OUTPUT.value, f"tratto_model_fold{fold}.pt"))
        torch.save(model.state_dict(), os.path.join(Path.OUTPUT.value, f"tratto_model_state_dict_fold_{fold}.pt"))
        # Release memory
        del model
        print("        " + "-" * 18)
        print("Training completed")
    utils.release_memory()

if __name__ == "__main__":
    main()