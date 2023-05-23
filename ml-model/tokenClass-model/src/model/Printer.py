
class Printer:
    @staticmethod
    def print_welcome():
        print("")
        print("**********************************")
        print("* TRATTO NEURAL MODEL - TRAINING *")
        print("*      CodeBert Finetuning       *")
        print("**********************************")
        print("")

    @staticmethod
    def print_dataset_generation():
        print("[5] Training and validation dataset generation phase")

    @staticmethod
    def print_load_dataset():
        print("[2] Load dataset-tokens phase")

    @staticmethod
    def print_load_gpu():
        print("[1] Load GPU")

    @staticmethod
    def print_pre_processing():
        print("[3] Pre-processing phase")

    @staticmethod
    def print_save_model():
        print("[6] Saving model")

    @staticmethod
    def print_training_fold(fold_idx):
        print(f"    [5.{fold_idx}] Training - fold {fold_idx}")

    @staticmethod
    def print_training_phase():
        print("[4] Training phase")
