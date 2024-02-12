from src.pretrained.ModelClasses import ModelClasses
from argparse import ArgumentParser
from typing import Type

from src.types.ClassificationType import ClassificationType


class ArgumentParser:
    @staticmethod
    def add_server_arguments(parser: Type[ArgumentParser]):
        parser.add_argument(
            "--port",
            default=5000,
            type=str,
            required=True,
            help="The server port."
        )
        parser.add_argument(
            "--checkpoint_path_multitask",
            default=None,
            type=str,
            help="The path to the checkpoint of the multitask model to load."
        )
        parser.add_argument(
            "--checkpoint_path_oracles",
            default=None,
            type=str,
            help="The path to the checkpoint of the oracles model to load."
        )
        parser.add_argument(
            "--checkpoint_path_token_classes",
            default=None,
            type=str,
            help="The path to the checkpoint of the tokenClasses model to load."
        )
        parser.add_argument(
            "--checkpoint_path_token_values",
            default=None,
            type=str,
            help="The path to the checkpoint of the tokenValues model to load."
        )
        parser.add_argument(
            "--model_type_multitask",
            default=None,
            type=str,
            help="TokenValues model type for the selected in the list: " + ", ".join(
                ModelClasses.get_available_model_classes()
            )
        )
        parser.add_argument(
            "--model_type_oracles",
            default=None,
            type=str,
            help="Oracles model type for the selected in the list: " + ", ".join(
                ModelClasses.get_available_model_classes()
            )
        )
        parser.add_argument(
            "--model_type_token_classes",
            default=None,
            type=str,
            help="TokenClasses model type for the selected in the list: " + ", ".join(
                ModelClasses.get_available_model_classes()
            )
        )
        parser.add_argument(
            "--model_type_token_values",
            default=None,
            type=str,
            help="TokenValues model type for the selected in the list: " + ", ".join(
                ModelClasses.get_available_model_classes()
            )
        )
        parser.add_argument(
            "--transformer_type_multitask",
            default="decoder",
            type=str,
            help="Trasformer type: encoder or decoder. Default decoder."
        )
        parser.add_argument(
            "--transformer_type_oracles",
            default="decoder",
            type=str,
            help="Trasformer type: encoder or decoder. Default decoder."
        )
        parser.add_argument(
            "--transformer_type_token_classes",
            default="decoder",
            type=str,
            help="Trasformer type: encoder or decoder. Default decoder."
        )
        parser.add_argument(
            "--transformer_type_token_values",
            default="decoder",
            type=str,
            help="Trasformer type: encoder or decoder. Default decoder."
        )
        parser.add_argument(
            "--classification_type_multitask",
            default=None,
            type=str,
            help="Multitask classification type for the selected in the list: " + ", ".join([
                ClassificationType.CATEGORY_PREDICTION,
                ClassificationType.LABEL_PREDICTION
            ])
        )
        parser.add_argument(
            "--classification_type_oracles",
            default=None,
            type=str,
            help="TokenClasses classification type for the selected in the list: " + ", ".join([
                ClassificationType.CATEGORY_PREDICTION,
                ClassificationType.LABEL_PREDICTION
            ])
        )
        parser.add_argument(
            "--classification_type_token_classes",
            default=None,
            type=str,
            help="TokenClasses classification type for the selected in the list: " + ", ".join([
                ClassificationType.CATEGORY_PREDICTION,
                ClassificationType.LABEL_PREDICTION
            ])
        )
        parser.add_argument(
            "--classification_type_token_values",
            default=None,
            type=str,
            help="TokenValues classification type for the selected in the list: " + ", ".join([
                ClassificationType.CATEGORY_PREDICTION,
                ClassificationType.LABEL_PREDICTION
            ])
        )
        parser.add_argument(
            "--tokenizer_name_multitask",
            default=None,
            type=str,
            help="Pretrained tokenizer name or path if not the same as model_name for the multitask model."
        )
        parser.add_argument(
            "--tokenizer_name_oracles",
            default=None,
            type=str,
            help="Pretrained tokenizer name or path if not the same as model_name for the oracles model."
        )
        parser.add_argument(
            "--tokenizer_name_token_classes",
            default=None,
            type=str,
            help="Pretrained tokenizer name or path if not the same as model_name for the tokenClasses model."
        )
        parser.add_argument(
            "--tokenizer_name_token_values",
            default=None,
            type=str,
            help="Pretrained tokenizer name or path if not the same as model_name for the tokenValues model."
        )
        parser.add_argument(
            "--model_name_or_path_multitask",
            default=None,
            type=str,
            help="Path to pre-trained model or shortcut name for the multitask model."
        )
        parser.add_argument(
            "--model_name_or_path_oracles",
            default=None,
            type=str,
            help="Path to pre-trained model or shortcut name for the oracles model."
        )
        parser.add_argument(
            "--model_name_or_path_token_classes",
            default=None,
            type=str,
            help="Path to pre-trained model or shortcut name for the tokenClasses model."
        )
        parser.add_argument(
            "--model_name_or_path_token_values",
            default=None,
            type=str,
            help="Path to pre-trained model or shortcut name for the tokenValues model."
        )
        parser.add_argument(
            "--config_name_multitask",
            default=None,
            type=str,
            help="Pretrained config name or path if not the same as model_name for the multitask model."
        )
        parser.add_argument(
            "--config_name_oracles",
            default=None,
            type=str,
            help="Pretrained config name or path if not the same as model_name for the oracles model."
        )
        parser.add_argument(
            "--config_name_token_classes",
            default=None,
            type=str,
            help="Pretrained config name or path if not the same as model_name for the tokenClasses model."
        )
        parser.add_argument(
            "--config_name_token_values",
            default=None,
            type=str,
            help="Pretrained config name or path if not the same as model_name for the tokenValues model."
        )

    @staticmethod
    def add_pre_process_dataset_arguments(parser: Type[ArgumentParser]):
        """
        Set up the arguments to parse from the command line, to pre-process the dataset.

        Parameters
        ----------
        parser: Type[ArgumentParser]
            The parser that extract the values of the parameters from the command line
        """
        # Required parameters
        parser.add_argument(
            "--train_path",
            default=None,
            type=str,
            required=True,
            help="The directory to the train dataset."
        )
        parser.add_argument(
            "--validation_path",
            default=None,
            type=str,
            required=True,
            help="The directory to the validation dataset."
        )
        parser.add_argument(
            "--model_type",
            default=None,
            type=str,
            required=True,
            help="Model type selected in the list: " + ", ".join(ModelClasses.get_available_model_classes())
        )
        parser.add_argument(
            "--tokenizer_name",
            default=None,
            type=str,
            help="Pretrained tokenizer name or path if not the same as model_name"
        )
        # Optional parameters
        parser.add_argument(
            "--classification_type",
            default="label_prediction",
            type=str,
            help="Classification type: category prediction (category_prediction) or label prediction (label_prediction)."
        )
        parser.add_argument(
            "--tratto_model_type",
            default="token_classes",
            type=str,
            help="Tratto model type: token classes (token_classes) or token values (token_values)."
        )
        parser.add_argument(
            "--transformer_type",
            default="decoder",
            type=str,
            help="Trasformer type: encoder or decoder. Default decoder."
        )
        parser.add_argument(
            "--no_cuda",
            action='store_true',
            help="Avoid using CUDA when available"
        )
        parser.add_argument(
            "--pre_processing",
            default="True",
            type=str,
            help="Perform pre-processing of the dataset"
        )
        parser.add_argument(
            "--rapids_cudf",
            default="False",
            type=str,
            help="Use RAPIDS cuDF library for faster data loading"
        )
        parser.add_argument(
            "--overwrite_output_dir",
            action='store_true',
            help="Overwrite the content of the output directory"
        )
        parser.add_argument(
            "--seed",
            type=int,
            default=42,
            help="random seed for initialization"
        )

    @staticmethod
    def add_init_arguments(parser: Type[ArgumentParser]):
        """
        Set up the arguments to parse from the command line.

        Parameters
        ----------
        parser: Type[ArgumentParser]
            The parser that extract the values of the parameters from the command line
        """
        parser.add_argument(
            "--datasets",
            action='store_true',
            help="If provided as argument, the script will download the training datasets."
        )
        parser.add_argument(
            "--checkpoints",
            action='store_true',
            help="If provided as argument, the script will download the checkpoints of the finetuned models."
        )

    @staticmethod
    def add_test_arguments(parser: Type[ArgumentParser]):
        """
        Set up the arguments to parse from the command line.
        Parameters
        ----------
        parser: Type[ArgumentParser]
            The parser that extract the values of the parameters from the command line
        """
        parser.add_argument(
            "--project_name",
            default=None,
            type=str,
            required=True,
            help="The name of the project to test, among ['gs-core','plume-lib','jgrapht','commons-math','commons-collections','guava-19']."
        )
        parser.add_argument(
            "--checkpoint_path",
            default=None,
            type=str,
            required=True,
            help="The path to the checkpoint to load."
        )
        parser.add_argument(
            "--input_path",
            default=None,
            type=str,
            required=True,
            help="The path to the dataset."
        )
        parser.add_argument(
            "--output_path",
            default=None,
            type=str,
            required=True,
            help="The path where to save the statistics."
        )
        parser.add_argument(
            "--model_type",
            default=None,
            type=str,
            required=True,
            help="Model type selected in the list: " + ", ".join(ModelClasses.get_available_model_classes()))
        parser.add_argument(
            "--tokenizer_name",
            default=None,
            type=str,
            help="Pretrained tokenizer name or path if not the same as model_name"
        )
        parser.add_argument(
            "--config_name",
            default=None,
            type=str,
            help="Pretrained config name or path if not the same as model_name"
        )
        parser.add_argument(
            "--model_name_or_path",
            default=None,
            type=str,
            required=True,
            help="Path to pre-trained model or shortcut name.")
        parser.add_argument(
            "--classification_type",
            default="label_prediction",
            type=str,
            help="Classification type: category prediction (category_prediction) or label prediction (label_prediction)."
        )
        parser.add_argument(
            "--tratto_model_type",
            default="token_classes",
            type=str,
            help="Tratto model type: token classes (token_classes) or token values (token_values)."
        )
        parser.add_argument(
            "--transformer_type",
            default="decoder",
            type=str,
            help="Trasformer type: encoder or decoder. Default decoder."
        )
        parser.add_argument(
            "--pre_processing",
            default="True",
            type=str,
            help="Perform pre-processing of the dataset"
        )
        parser.add_argument(
            "--rapids_cudf",
            default="False",
            type=str,
            help="Use RAPIDS cuDF library for faster data loading"
        )

    @staticmethod
    def add_training_arguments(parser: Type[ArgumentParser]):
        """
        Set up the arguments to parse from the command line.

        Parameters
        ----------
        parser: Type[ArgumentParser]
            The parser that extract the values of the parameters from the command line
        """
        # Required parameters
        parser.add_argument(
            "--train_path",
            default=None,
            type=str,
            required=True,
            help="The directory to the train dataset."
        )
        parser.add_argument(
            "--validation_path",
            default=None,
            type=str,
            required=True,
            help="The directory to the validation dataset."
        )
        parser.add_argument(
            "--model_type",
            default=None,
            type=str,
            required=True,
            help="Model type selected in the list: " + ", ".join(ModelClasses.get_available_model_classes())
        )
        parser.add_argument(
            "--tokenizer_name",
            default=None,
            type=str,
            help="Pretrained tokenizer name or path if not the same as model_name"
        )
        parser.add_argument(
            "--config_name",
            default=None,
            type=str,
            help="Pretrained config name or path if not the same as model_name"
        )
        parser.add_argument(
            "--model_name_or_path",
            default=None,
            type=str,
            required=True,
            help="Path to pre-trained model or shortcut name."
        )
        parser.add_argument(
            "--task_name",
            default='tokenClasses',
            type=str,
            required=True,
            help="The name of the task to train the model: tokenClasses or tokenValues."
        )
        parser.add_argument(
            "--output_dir",
            default=None,
            type=str,
            required=True,
            help="The output directory where the model predictions and checkpoints will be written."
        )
        # Optional parameters
        parser.add_argument(
            "--classification_type",
            default="label_prediction",
            type=str,
            help="Classification type: category prediction (category_prediction) or label prediction (label_prediction)."
        )
        parser.add_argument(
            "--tratto_model_type",
            default="token_classes",
            type=str,
            help="Tratto model type: token classes (token_classes) or token values (token_values)."
        )
        parser.add_argument(
            "--transformer_type",
            default="decoder",
            type=str,
            help="Trasformer type: encoder or decoder. Default decoder."
        )
        parser.add_argument(
            "--max_seq_length",
            default=512,
            type=int,
            help="The maximum total input sequence length after tokenization. " \
                 "Sequences longer than this will be truncated, sequences shorter will be padded."
        )
        parser.add_argument(
            "--do_train",
            action='store_true',
            help="Whether to run training."
        )
        parser.add_argument(
            "--do_eval",
            action='store_true',
            help="Whether to run eval on the dev set."
        )
        parser.add_argument(
            "--do_predict",
            action='store_true',
            help="Whether to run predict on the test set."
        )
        parser.add_argument(
            "--evaluate_during_training",
            action='store_true',
            help="Run evaluation during training at each logging step."
        )
        parser.add_argument(
            "--resume_checkpoint_filename",
            default="",
            type=str,
            help="The whole path to the checkpoint to resume."
        )
        parser.add_argument(
            "--folds",
            default=1,
            type=int,
            help="The number of folds for cross-validation (if folds=1 no-cross-validation is performed)."
        )
        parser.add_argument(
            "--batch_size",
            default=8,
            type=int,
            help="Batch size per GPU/CPU for training."
        )
        parser.add_argument(
            "--accumulation_steps",
            type=int,
            default=1,
            help="Number of updates steps to accumulate before performing a backward/update pass."
        )
        parser.add_argument(
            "--test_ratio",
            default=0.1,
            type=float,
            help="The ratio of the original dataset, reserved for testing."
        )
        parser.add_argument(
            "--validation_ratio",
            default=0.1,
            type=float,
            help="The ratio of the original dataset, reserved for validation."
        )
        parser.add_argument(
            "--learning_rate",
            default=5e-5,
            type=float,
            help="The initial learning rate for Adam."
        )
        parser.add_argument(
            "--weight_decay",
            default=0.0,
            type=float,
            help="Weight decay, if applied."
        )
        parser.add_argument(
            "--warmup_steps",
            default=0,
            type=int,
            help="Linear warmup over warmup_steps."
        )
        parser.add_argument(
            "--adam_epsilon",
            default=1e-8,
            type=float,
            help="Epsilon for Adam optimizer."
        )
        parser.add_argument(
            "--max_grad_norm",
            default=1.0,
            type=float,
            help="Max gradient norm."
        )
        parser.add_argument(
            "--num_epochs",
            default=3,
            type=int,
            help="Total number of training epochs to perform."
        )
        parser.add_argument(
            "--checkpoint_steps",
            type=int,
            default=50,
            help="Save checkpoint every X updates steps."
        )
        parser.add_argument(
            "--eval_all_checkpoints",
            action='store_true',
            help="Evaluate all checkpoints starting with the same prefix as model_name ending and ending with step number"
        )
        parser.add_argument(
            "--no_cuda",
            action='store_true',
            help="Avoid using CUDA when available"
        )
        parser.add_argument(
            "--pre_processing",
            default="True",
            type=str,
            help="Perform pre-processing of the dataset"
        )
        parser.add_argument(
            "--rapids_cudf",
            default="False",
            type=str,
            help="Use RAPIDS cuDF library for faster data loading"
        )
        parser.add_argument(
            "--save_pre_processing",
            default="False",
            type=str,
            help="Perform pre-processing of the dataset"
        )
        parser.add_argument(
            "--overwrite_output_dir",
            action='store_true',
            help="Overwrite the content of the output directory"
        )
        parser.add_argument(
            "--seed",
            type=int,
            default=42,
            help="random seed for initialization"
        )