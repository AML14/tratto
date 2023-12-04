from collections import Counter
from typing import List, Tuple, Dict

from src.utils import utils


def print_welcome(classification_type: str, tratto_model_type: str):
    classification_str = f"Performing {classification_type.replace('_', ' ')} classification task"
    model_str = f"Training model in predicting next {tratto_model_type.replace('_', ' ')}"

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


def print_cross_validation(fold):
    print("        " + "-" * 25)
    print(f"        Cross-validation | Fold {fold + 1}")
    print("        " + "-" * 25)


def print_load_dataset_as_dataframe(d_path: str):
    print(f"[2] Load dataset: {d_path}")


def print_load_gpu():
    print("[1] Load GPU")


def print_pre_processing():
    print("[2] Pre-processing phase")


def print_save_model():
    print("[4] Saving model")


def print_training_fold(fold_idx):
    print(f"    [3.{fold_idx}] Training - fold {fold_idx}")



def print_training_phase():
    print("[4] Training phase")


def print_stats(
        epoch: int,
        num_epochs: int,
        step: int,
        total_steps: int,
        stats: dict
):
    """
    The method prints the statistics of the training and validation phases

    Parameters
    ----------
    epoch: int
        The current epoch of the training
    num_epochs: int
        The total number of epochs
    step: int
        The current step of the training phase, in the current epoch
    total_steps: int
        The total number of steps within an epoch
    mean_t_loss: float
        Average training loss
    stats: dict
        t_loss: float
            Average training loss
        v_loss: float
            Average validation loss
        t_f1_score: List[Tuple[str,float]]
            F1 score of the training phase, for each class of the model
        t_accuracy: float
            Accuracy of the training phase
        t_precision: List[Tuple[str,float]]
            Precision of the training phase, for each class of the model
        t_recall: List[Tuple[str,float]]
            Recall of the training phase, for each class of the model
        v_loss: float
            Average validation loss
        v_f1_score: List[Tuple[str,float]]
            F1 score of the validation phase, for each class of the model
        v_accuracy: float
            Accuracy of the validation phase
        v_precision: List[Tuple[str,float]]
            Precision of the validation phase, for each class of the model
        v_recall: List[Tuple[str,float]]
            Recall of the validation phase, for each class of the model
    """
    print("            " + '-' * 30)
    print("            " + "STATISTICS")
    print("            " + '-' * 30)
    print("            " + f"EPOCH: [{epoch} / {num_epochs}]")
    print("            " + f"STEP: [{step} / {total_steps}]")
    print("            " + f"TRAINING LOSS: {stats['t_loss']:.4f}")
    print("            " + f"TRAINING ACCURACY: {stats['t_accuracy']:.2f}")
    print("            " + f"TRAINING F1-SCORE MICRO: {stats['t_f1_score_micro']:.2f}")
    for class_label, score in stats['t_f1_score']:
        print("            " + f"TRAINING F1-SCORE - {class_label}: {score:.2f}")
    for class_label, score in stats['t_precision']:
        print("            " + f"TRAINING PRECISION - {class_label}: {score:.2f}")
    for class_label, score in stats['t_recall']:
        print("            " + f"TRAINING RECALL - {class_label}: {score:.2f}")
    print("            " + '-' * 30)
    if 't_predictions_per_class' in stats:
        for class_label, predictions_stats in stats['t_predictions_per_class']['classes'].items():
            print("            " + f"LABEL {class_label}")
            print("                " + f"Correct: {predictions_stats['correct']} / {predictions_stats['total']}")
            if predictions_stats['correct'] > 0:
                print("                " + f"Correct Classes:")
                correct_class_counter = Counter(predictions_stats["correct_class"])
                for class_label, occurrences in correct_class_counter.items():
                    print("                    " + f"{class_label}: {occurrences}")
            print("                " + f"Wrong: {predictions_stats['wrong']} / {predictions_stats['total']}")
            if predictions_stats['wrong'] > 0:
                print("                " + f"Wrong Classes:")
                wrong_class_counter = Counter(predictions_stats["wrong_class"])
                for class_label, occurrences in wrong_class_counter.items():
                    print("                    " + f"{class_label}: {occurrences}")
        print("            " + '-' * 30)
    print("            " + f"VALIDATION LOSS: {stats['v_loss']:.4f}")
    print("            " + f"VALIDATION ACCURACY: {stats['v_accuracy']:.2f}")
    print("            " + f"VALIDATION F1-SCORE MICRO: {stats['v_f1_score_micro']:.2f}")
    for class_label, score in stats['v_f1_score']:
        print("            " + f"VALIDATION F1-SCORE - {class_label}: {score:.2f}")
    for class_label, score in stats['v_precision']:
        print("            " + f"VALIDATION PRECISION - {class_label}: {score:.2f}")
    for class_label, score in stats['v_recall']:
        print("            " + f"VALIDATION RECALL - {class_label}: {score:.2f}")
    print("            " + '-' * 30)
    if 'v_predictions_per_class'in stats:
        for class_label, predictions_stats in stats['v_predictions_per_class']['classes'].items():
            print("            " + f"LABEL {class_label}")
            print("                " + f"Correct: {predictions_stats['correct']} / {predictions_stats['total']}")
            if predictions_stats['correct'] > 0:
                print("                " + f"Correct Classes:")
                correct_class_counter = Counter(predictions_stats["correct_class"])
                for class_label, occurrences in correct_class_counter.items():
                    print("                    " + f"{class_label}: {occurrences}")
            print("                " + f"Wrong: {predictions_stats['wrong']} / {predictions_stats['total']}")
            if predictions_stats['wrong'] > 0:
                print("                " + f"Wrong Classes:")
                wrong_class_counter = Counter(predictions_stats["wrong_class"])
                for class_label, occurrences in wrong_class_counter.items():
                    print("                    " + f"{class_label}: {occurrences}")
        print("            " + '-' * 30)


def print_evaluation_stats(
        test_f1_score: List[Tuple[str,float]],
        test_f1_score_micro: List[Tuple[str,float]],
        test_accuracy: List[Tuple[str,float]],
        test_precision: List[Tuple[str,float]],
        test_recall: List[Tuple[str,float]],
        test_predictions_per_class: Dict[str,Dict] = {}
):
    """
    The method prints the statistics of the testing phases

    Parameters
    ----------
    test_f1_scoret: List[Tuple[str,float]]
        F1 score of the testing phase, for each class of the model
    test_accuracy: float
        Accuracy of the testing phase
    test_precision: List[Tuple[str,float]]
        Precision of the testing phase, for each class of the model
    test_recall: List[Tuple[str,float]]
        Recall of the testing phase, for each class of the model
    """
    print("            " + '-'*30)
    print("            " + "TESTING STATISTICS")
    print("            " + '-'*30)
    print("            " + f"TESTING ACCURACY: {test_accuracy:.2f}")
    print("            " + '-'*30)
    print("            " + f"TESTING F1-SCORE MICRO: {test_f1_score_micro:.2f}")
    print("            " + '-' * 30)
    for class_label, score in test_f1_score:
        print("            " + f"TESTING F1-SCORE - {class_label}: {score:.2f}")
    print("            " + '-'*30)
    for class_label, score in test_precision:
        print("            " + f"TESTING PRECISION - {class_label}: {score:.2f}")
    print("            " + '-'*30)
    for class_label, score in test_recall:
        print("            " + f"TESTING RECALL - {class_label}: {score:.2f}")
    print("            " + '-'*30)
    if bool(test_predictions_per_class):
        for class_label, predictions_stats in test_predictions_per_class['classes'].items():
            print("            " + f"LABEL {class_label}")
            print("                " + f"Correct: {predictions_stats['correct']} / {predictions_stats['total']}")
            if predictions_stats['correct'] > 0:
                print("                " + f"Correct Classes:")
                correct_class_counter = Counter(predictions_stats["correct_class"])
                for class_label, occurrences in correct_class_counter.items():
                    print("                    " + f"{class_label}: {occurrences}")
            print("                " + f"Wrong: {predictions_stats['wrong']} / {predictions_stats['total']}")
            if predictions_stats['wrong'] > 0:
                print("                " + f"Wrong Classes:")
                wrong_class_counter = Counter(predictions_stats["wrong_class"])
                for class_label, occurrences in wrong_class_counter.items():
                    print("                    " + f"{class_label}: {occurrences}")
        print("            " + '-' * 30)