from src.utils import utils


class Printer:
    @staticmethod
    def print_welcome(classification_type: str, model_type: str):
        if utils.is_main_process():
            classification_str = f"Performing {classification_type.replace('_', ' ')} classification task"
            model_str = f"Training model in predicting next {model_type.replace('_', ' ')}"

            print("")
            print("**********************************")
            print("* TRATTO NEURAL MODEL - TRAINING *")
            print("*      CodeBert Finetuning       *")
            print("**********************************")
            print("-"*len(classification_str))
            print(classification_str)
            print(f"-"*len(classification_str))
            print(model_str)
            print(f"-" * len(model_str))
            print("")

    @staticmethod
    def print_dataset_generation():
        if utils.is_main_process():
            print("[5] Training and validation dataset generation phase")

    @staticmethod
    def print_load_dataset(d_path: str):
        if utils.is_main_process():
            print(f"[2] Load {'token-values-dataset' if d_path.endswith('token-values-dataset') else 'token-classes-dataset'} phase")

    @staticmethod
    def print_load_gpu():
        if utils.is_main_process():
            print("[1] Load GPU")

    @staticmethod
    def print_pre_processing():
        if utils.is_main_process():
            print("[3] Pre-processing phase")

    @staticmethod
    def print_save_model():
        if utils.is_main_process():
            print("[6] Saving model")

    @staticmethod
    def print_training_fold(fold_idx):
        if utils.is_main_process():
            print(f"    [5.{fold_idx}] Training - fold {fold_idx}")

    @staticmethod
    def print_training_phase():
        if utils.is_main_process():
            print("[4] Training phase")
