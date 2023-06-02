from src.pretrained.ModelClasses import ModelClasses


class ArgumentParser:
    @staticmethod
    def add_arguments(parser):
        ## Required parameters
        parser.add_argument(
            "--data_dir",
            default=None,
            type=str,
            required=True,
            help="The directory of the dataset."
        )
        parser.add_argument(
            "--model_type",
            default=None,
            type=str,
            required=True,
            help="Model type selected in the list: " + ", ".join(ModelClasses.get_available_model_classes()))
        parser.add_argument(
            "--model_name_or_path",
            default=None,
            type=str,
            required=True,
            help="Path to pre-trained model or shortcut name.")
        parser.add_argument(
            "--task_name",
            default='tokenClasses',
            type=str,
            required=True,
            help="The name of the task to train the model: tokenClasses or tokenValues")
        parser.add_argument(
            "--output_dir",
            default=None,
            type=str,
            required=True,
            help="The output directory where the model predictions and checkpoints will be written.")

        ## Other parameters
        parser.add_argument(
            "--classification_type",
            default="label_prediction",
            type=str,
            help="Classification type: CATEGORY_PREDICTION or LABEL_PREDICTION.")
        parser.add_argument(
            "--tratto_model_type",
            default="token_classes",
            type=str,
            help="Classification type: TOKEN_CLASSES or TOKEN_VALUES.")
        parser.add_argument(
            "--config_name",
            default="",
            type=str,
            help="Pretrained config name or path if not the same as model_name")
        parser.add_argument(
            "--tokenizer_name",
            default="",
            type=str,
            help="Pretrained tokenizer name or path if not the same as model_name")
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
            "--cross_validation",
            action='store_true',
            help="Perform cross-validation."
        )
        parser.add_argument(
            "--folds",
            default=1,
            type=int,
            help="The number of folds for cross-validation."
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
            help="Weight deay if we apply some."
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
            "--max_steps",
            default=-1,
            type=int,
            help="If > 0: set total number of training steps to perform. Override num_epochs."
        )
        parser.add_argument(
            "--warmup_steps",
            default=0,
            type=int,
            help="Linear warmup over warmup_steps."
        )
        parser.add_argument(
            "--save_steps",
            type=int,
            default=50,
            help="Save checkpoint every X updates steps."
        )
        parser.add_argument(
            "--eval_all_checkpoints",
            action='store_true',
            help="Evaluate all checkpoints starting with the same prefix as model_name ending and ending with step number")
        parser.add_argument(
            "--no_cuda",
            action='store_true',
            help="Avoid using CUDA when available")
        parser.add_argument(
            "--overwrite_output_dir",
            action='store_true',
            help="Overwrite the content of the output directory")
        parser.add_argument(
            "--overwrite_cache",
            action='store_true',
            help="Overwrite the cached training and evaluation sets"
        )
        parser.add_argument(
            "--seed",
            type=int,
            default=42,
            help="random seed for initialization"
        )
        parser.add_argument(
            "--fp16",
            action='store_true',
           help="Whether to use 16-bit (mixed) precision (through NVIDIA apex) instead of 32-bit"
        )
        parser.add_argument(
            "--fp16_opt_level",
            type=str,
            default='O1',
            help="For fp16: Apex AMP optimization level selected in ['O0', 'O1', 'O2', and 'O3']." \
                 "See details at https://nvidia.github.io/apex/amp.html"
        )
        parser.add_argument(
            "--local_rank",
            type=int,
            default=-1,
             help="For distributed training: local_rank"
        )
        return parser