import json
import math
import os
import timeit
import numpy as np
from typing import Type, Union, Tuple, Dict
from collections import Counter


import torch
import torch.optim as optim
from torch.nn import Module
from torch.utils.data import DataLoader

from sklearn.metrics import f1_score, accuracy_score, precision_score, recall_score

from src.enums.FileFormat import FileFormat
from src.enums.HyperParameter import HyperParameter
from src.enums.Path import Path
from src.model.OracleClassifier import OracleClassifier
from src.enums.ClassificationType import ClassificationType
from src.utils import utils


class OracleTrainer:
    """
    The *OracleTrainer* class is an helper class that, given the loss
    function, the optimizer, the model, and the training and validation
    datasets perform the training of the model and computes the loss and
    the f1 score of the training and validation phases, saves the statistics
    of the training, and have auxiliary methods that let to visualize the
    trend of the training and the validation, over the epochs.

    Parameters
    ----------
    model: OracleClassifier
        The model to train
    loss_fn:
        The loss function to compute the loss, during the training and
        validation phases
    optimizer:
        The optimizer used to perform the backpropagation and updates
        the weights of the model
    dl_train: DataLoader
        The training dataloader which contains the batches of datapoints
        for the training phase
    dl_val: DataLoader
        The validation dataloader which contains the batches of datapoints
        for the validation phase
    dl_test: DataLoader
        The test dataloader which contains the batches of datapoints
        for the testing phase
    classifier_ids_labels:
        The method compute the list of labels of the classification model, in the form of a list of tuples where the first
        element is an incremental index, while the second element is the name of the class
    classification_type: Type[ClassificationType]
        Category prediction or label prediction
    checkpoint_path: str
        The path where to save model checkpoints

    Attributes
    ----------
    model: OracleClassifier
        The model to train
    loss_fn:
        The loss function to compute the loss, during the training and
        validation phases
    optimizer:
        The optimizer used to perform the backpropagation and updates
        the weights of the model
    dl_train: DataLoader
        The training dataloader which contains the batches of datapoints
        for the training phase
    dl_val: DataLoader
        The validation dataloader which contains the batches of datapoints
        for the validation phase
    dl_test: DataLoader
        The test dataloader which contains the batches of datapoints
        for the testing phase
    classifier_ids_labels:
        The dictionary of labels of the classification model, where the value is the name of a target label, while the key element
        is a numerical identifier representing the index of the one-shot vector representing the target label, with value equals
        to 1.0.
    classifier_ids_classes:
        The dictionary of classes of the classification model, where the value is the name of a class, while the key element
        is a numerical identifier representing the index of the one-shot vector representing the class, with value equals
        to 1.0
    classification_type: Type[ClassificationType]
        Category prediction or label prediction
    checkpoint_path: str
        The path where to save model checkpoints
    """
    def __init__(
            self,
            model: Type[OracleClassifier],
            loss_fn: Type[Module],
            optimizer: Type[optim.Adam],
            dl_train: Type[DataLoader],
            dl_val: Type[DataLoader],
            dl_test: Type[DataLoader],
            classifier_ids_labels: Dict[int,str],
            classifier_ids_classes: Dict[int,str],
            classification_type: ClassificationType,
            checkpoint_path
    ):
        self._model = model
        self._dl_train = dl_train
        self._dl_val = dl_val
        self._dl_test = dl_test
        self._loss_fn = loss_fn
        self._optimizer = optimizer
        self._classifier_ids_labels = classifier_ids_labels
        self._classifier_ids_classes = classifier_ids_classes
        self._classification_type = classification_type
        self._checkpoint_path = checkpoint_path

    def _checkpoint(self, epoch: int, step: int):
        if not os.path.exists(self._checkpoint_path):
            os.makedirs(self._checkpoint_path)
        filename = os.path.join(self._checkpoint_path, f"checkpoint_{str(epoch)}_{str(step)}")
        torch.save(self._model.state_dict(), f"{filename}.pt")

    def _resume(self, epoch: int, step: int):
        if not os.path.exists(self._checkpoint_path):
            os.makedirs(self._checkpoint_path)
        filename = os.path.join(self._checkpoint_path, str(epoch), str(step))
        self._model.load_state_dict(torch.load(f"{filename}.pt"))

    def train(
            self,
            num_epochs: int,
            num_steps: int,
            device: Union[int,str]
    ):
        """
        The method perform the training and validation phases of the model.

        Parameters
        ----------
        num_epochs: int
            The number of epochs to train the model
        num_steps: int
            The number of steps after which compute the gradients and upldate the weights
        device: Union[int,str]
            The identifier of the rank gpu or the cpu
        best_time:
            Best time for statistics performance
        
        Returns
        -------
        stats: dict
            The statistics of the testing phase
        """
        # Dictionary of the statistics
        stats = {
            't_loss': [],
            'v_loss': [],
            't_f1_score': [],
            'v_f1_score': [],
            't_accuracy': [],
            'v_accuracy': [],
            't_precision': [],
            'v_precision': [],
            't_recall': [],
            'v_recall': [],
            't_predictions_per_class': [],
            'v_predictions_per_class': []
        }
        steps = 0
        accumulation_steps = HyperParameter.ACCUMULATION_STEPS.value

        # In each epoch the trainer train the model batch by batch,
        # with all the batch of the training dataset. After a given
        # number of *accumulation_steps* the trainer performs the
        # backpropagation and updates the weights accordingly.
        # Moreover, it computes the f1 score and the total loss of
        # the training, and performs the validation to understand how
        # well the model generalize on the validation data.
        for epoch in range(1, num_epochs + 1):
            if utils.is_main_process():
                print(f"        Start training - Epoch {epoch} of {num_epochs}")
            total_loss = 0
            all_predictions = []
            all_labels = []

            t_predictions_per_class = {'classes': {k: {"correct": 0, "wrong": 0, "predicted": [], "wrong_class": [], "correct_class": [], "total": 0} for k in self._classifier_ids_labels.values()}, 'total': 0}

            # model in training mode
            self._model.train()
            self._optimizer.zero_grad()

            for step, batch in enumerate(self._dl_train,1):
                print(f"            Processing step {step} of {len(self._dl_train)}")
                steps += 1

                # Extract the inputs, the attention masks and the expected
                # outputs from the batch
                src_input = batch[0].to(device)
                masks_input = batch[1].to(device)
                tgt_out = batch[2].to(device)
                tgt_classes = list(map(lambda i: self._classifier_ids_classes[i], batch[3].numpy()))

                print(f"                Model predictions...")
                # Train the model
                outputs = self._model(src_input, masks_input)

                print(f"                Computing loss...")
                # Compute the loss
                loss = self._loss_fn(outputs, tgt_out)
                loss.backward()

                # Exctract the predicted values and the expected output
                with torch.no_grad():
                    _, predicted = outputs.max(1)
                    _, expected_out = tgt_out.max(1)
                # Update the counter of the predictions
                t_predictions_per_class['total'] = tgt_out.size(0)
                prediction_results = (predicted == expected_out)
                expected_values_per_class_ids = Counter(expected_out.detach().cpu().numpy())

                for expected_class_id, occurrences in expected_values_per_class_ids.items():
                    expected_class_label = self._classifier_ids_labels[expected_class_id]
                    t_predictions_per_class['classes'][expected_class_label]['total'] += occurrences

                for idx, result in enumerate(prediction_results):
                    expected_class_id = expected_out.detach().cpu().numpy()[idx]
                    expected_class_label = self._classifier_ids_labels[expected_class_id]
                    if result == False:
                        predicted_class_id = predicted.detach().cpu().numpy()[idx]
                        predicted_class_label = self._classifier_ids_labels[predicted_class_id]
                        t_predictions_per_class['classes'][expected_class_label]["wrong"] += 1
                        t_predictions_per_class['classes'][expected_class_label]["predicted"].append(predicted_class_label)
                        t_predictions_per_class['classes'][expected_class_label]["wrong_class"].append(tgt_classes[idx])
                    else:
                        t_predictions_per_class['classes'][expected_class_label]["correct"] += 1
                        t_predictions_per_class['classes'][expected_class_label]["correct_class"].append(tgt_classes[idx])

                # Accumulate predictions and labels
                all_predictions.extend(predicted.detach().cpu().numpy())
                all_labels.extend(expected_out.detach().cpu().numpy())

                if (steps % accumulation_steps) == 0:
                    print(f"                Weigths update...")
                    # Update the weights of the model
                    self._optimizer.step()
                    self._optimizer.zero_grad()
                    # Update the total loss
                    total_loss += loss.item()

                if (steps % num_steps) == 0:
                    # Compute average statistics for the loss
                    mean_t_loss = total_loss / (num_steps / accumulation_steps)
                    # Compute the f1 score of the model within the accumulation
                    # steps
                    predictions_numpy = np.array(all_predictions)
                    labels_numpy = np.array(all_labels)

                    t_f1 = f1_score(labels_numpy, predictions_numpy, average=None, zero_division=0, labels=list(self._classifier_ids_labels.keys()))
                    t_f1 = [[self._classifier_ids_labels[i], score] for i, score in enumerate(t_f1)]
                    # Compute accuracy
                    t_accuracy = accuracy_score(labels_numpy, predictions_numpy)
                    # Compute precision
                    t_precision = precision_score(labels_numpy, predictions_numpy, average=None, zero_division=0, labels=list(self._classifier_ids_labels.keys()))
                    t_precision = [[self._classifier_ids_labels[i], score] for i, score in enumerate(t_precision)]
                    # Compute recall
                    t_recall = recall_score(labels_numpy, predictions_numpy, average=None, zero_division=0, labels=list(self._classifier_ids_labels.keys()))
                    t_recall = [[self._classifier_ids_labels[i], score] for i, score in enumerate(t_recall)]

                    # Validation phase
                    mean_v_loss, v_f1, v_accuracy, v_precision, v_recall, v_predictions_per_class = self.validation(device)

                    # save past v_loss
                    last_v_loss = stats['v_loss'][-1] if len(stats['v_loss']) > 0 else mean_v_loss

                    # Update the statistics
                    stats['t_loss'].append(mean_t_loss)
                    stats['v_loss'].append(mean_v_loss)
                    stats['t_f1_score'].append(t_f1)
                    stats['v_f1_score'].append(v_f1)
                    stats['t_accuracy'].append(t_accuracy)
                    stats['v_accuracy'].append(v_accuracy)
                    stats['t_precision'].append(t_precision)
                    stats['v_precision'].append(v_precision)
                    stats['t_recall'].append(t_recall)
                    stats['v_recall'].append(v_recall)
                    stats['t_predictions_per_class'].append(t_predictions_per_class)
                    stats['v_predictions_per_class'].append(v_predictions_per_class)

                    # Print the statistics
                    self.print_stats(
                        epoch,
                        num_epochs,
                        step + 1,
                        len(self._dl_train),
                        {
                            't_loss': mean_t_loss,
                            'v_loss': mean_v_loss,
                            't_f1_score': t_f1,
                            'v_f1_score': v_f1,
                            't_accuracy': t_accuracy,
                            'v_accuracy': v_accuracy,
                            't_precision': t_precision,
                            'v_precision': v_precision,
                            't_recall': t_recall,
                            'v_recall': v_recall,
                            't_predictions_per_class': t_predictions_per_class,
                            'v_predictions_per_class': v_predictions_per_class
                        }
                    )

                    # Save checkpoints
                    if utils.is_main_process():
                        self._checkpoint(epoch, step)
                    # Reset the total loss
                    total_loss = 0
                    interval = timeit.default_timer()
                    # Clear the predictions and labels lists
                    all_predictions = []
                    all_labels = []
                    t_predictions_per_class = {'classes': {k: {"correct": 0, "wrong": 0, "predicted": [], "wrong_class": [], "correct_class": [], "total": 0} for k in self._classifier_ids_labels.values()}, 'total': 0}

                    # Stop if the model starts to overfit
                    if mean_v_loss > last_v_loss + last_v_loss * 0.3:
                        if utils.is_main_process():
                            print("Early stopping...")
                        break
            # Check if the directory exists, to save the statistics of the training
            if not os.path.exists(Path.OUTPUT.value):
                # If the path does not exists, create it
                os.makedirs(Path.OUTPUT.value)
            # Save the statistics in json format
            with open(
                os.path.join(
                    Path.OUTPUT.value,
                    f"stats_after_epoch_{epoch}.{FileFormat.JSON.value}"
                ),
                "w"
            ) as stats_file:
                json.dump(stats, stats_file)
        return stats

    @staticmethod
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
            t_f1_score: list[Tuple[str,float]]
                F1 score of the training phase, for each class of the model
            t_accuracy: float
                Accuracy of the training phase
            t_precision: list[Tuple[str,float]]
                Precision of the training phase, for each class of the model
            t_recall: list[Tuple[str,float]]
                Recall of the training phase, for each class of the model
            v_loss: float
                Average validation loss
            v_f1_score: list[Tuple[str,float]]
                F1 score of the validation phase, for each class of the model
            v_accuracy: float
                Accuracy of the validation phase
            v_precision: list[Tuple[str,float]]
                Precision of the validation phase, for each class of the model
            v_recall: list[Tuple[str,float]]
                Recall of the validation phase, for each class of the model
        """
        if utils.is_main_process():
            print("            " + '-' * 30)
            print("            " + "STATISTICS")
            print("            " + '-' * 30)
            print("            " + f"EPOCH: [{epoch} / {num_epochs}]")
            print("            " + f"STEP: [{step} / {total_steps}]")
            print("            " + f"TRAINING LOSS: {stats['t_loss']:.4f}")
            print("            " + f"TRAINING ACCURACY: {stats['t_accuracy']:.2f}")
            for class_label, score in stats['t_f1_score']:
                print("            " + f"TRAINING F1 SCORE - {class_label}: {score:.2f}")
            for class_label, score in stats['t_precision']:
                print("            " + f"TRAINING PRECISION - {class_label}: {score:.2f}")
            for class_label, score in stats['t_recall']:
                print("            " + f"TRAINING RECALL - {class_label}: {score:.2f}")
            print("            " + '-' * 30)
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
            for class_label, score in stats['v_f1_score']:
                print("            " + f"VALIDATION F1 SCORE - {class_label}: {score:.2f}")
            for class_label, score in stats['v_precision']:
                print("            " + f"VALIDATION PRECISION - {class_label}: {score:.2f}")
            for class_label, score in stats['v_recall']:
                print("            " + f"VALIDATION RECALL - {class_label}: {score:.2f}")
            print("            " + '-' * 30)
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

    @staticmethod
    def print_evaluation_stats(
            test_f1_score: list[Tuple[str,float]],
            test_accuracy: list[Tuple[str,float]],
            test_precision: list[Tuple[str,float]],
            test_recall: list[Tuple[str,float]],
            test_predictions_per_class: Dict[str,Dict]
    ):
        """
        The method prints the statistics of the testing phases

        Parameters
        ----------
        test_f1_scoret: list[Tuple[str,float]]
            F1 score of the testing phase, for each class of the model
        test_accuracy: float
            Accuracy of the testing phase
        test_precision: list[Tuple[str,float]]
            Precision of the testing phase, for each class of the model
        test_recall: list[Tuple[str,float]]
            Recall of the testing phase, for each class of the model
        """
        if utils.is_main_process():
            print("            " + '-'*30)
            print("            " + "TESTING STATISTICS")
            print("            " + '-'*30)
            print("            " + f"TESTING ACCURACY: {test_accuracy:.2f}")
            print("            " + '-'*30)
            for class_label, score in test_f1_score:
                print("            " + f"TESTING F1 SCORE - {class_label}: {score:.2f}")
            print("            " + '-'*30)
            for class_label, score in test_precision:
                print("            " + f"TESTING PRECISION - {class_label}: {score:.2f}")
            print("            " + '-'*30)
            for class_label, score in test_recall:
                print("            " + f"TESTING RECALL - {class_label}: {score:.2f}")
            print("            " + '-'*30)
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


    @staticmethod
    def plot_loss_f1_score(
            steps: int,
            ax: any,
            stats: dict
    ):
        """
        The method plots the trend of the loss and f1 score

        Parameters
        ----------
        steps: int
            The number of steps
        ax: any
            The axes of the matplotlib figure
        stats: dict
            The dictionary of the statistics
        """
        for i in range(2):
            for j in range(2):
                title = ('Training ' if j == 0 else 'Validation') + ('Loss' if i == 0 else 'F1-score')
                dict_label = ('t_' if j == 0 else 'v_') + ('loss' if i == 0 else 'f1-score')
                color = 'blue'
                ax[i][j].set_title(title, fontsize=30)
                ax[i][j].set_xlabel("steps", fontsize=30)
                ax[i][j].set_ylabel("loss", fontsize=30)
                ax[i][j].plot(range(0, len(stats['t_loss']) * steps, steps), np.array(stats[dict_label])[:], '-', color=color)
        for i in range(2):
            title = 'Training and Validation ' + ('Loss' if i == 0 else 'F1-score')
            dict_label = 'loss' if i == 0 else 'f1-score'
            ax[2][i].set_title(title, fontsize=30)
            ax[2][i].set_xlabel("steps", fontsize=30)
            ax[2][i].set_ylabel("loss", fontsize=30)
            ax[2][i].plot(range(0, len(stats['t_loss']) * steps, steps), np.array(stats['t_' + dict_label])[:], '-', color='blue')
            ax[2][i].plot(range(0, len(stats['t_loss']) * steps, steps), np.array(stats['v_' + dict_label])[:], '-', color='orange')

    def validation(
            self,
            device: Union[int,str]
    ):
        """
        The method computes the validation phase.

        Parameters
        ----------
        device: int
            The identifier of the rank gpu or the cpu

        Returns
        -------
        mean_v_loss: float
            Average loss of the validation phase
        v_f1:
            F1 score of the validation phase
        """
        # model in evaluation mode
        self._model.eval()

        total_loss = 0
        # Accumulate predictions and labels
        all_predictions = []
        all_labels = []
        v_predictions_per_class = {'classes': {k: {"correct": 0, "wrong": 0, "predicted": [], "wrong_class": [], "correct_class": [], "total": 0} for k in self._classifier_ids_labels.values()}, 'total': 0}
        # The validation phase is performed without accumulating
        # the gradient descent and without updating the weights
        # of the model

        if utils.is_main_process():
            print("        Performing validation step...")
        with torch.no_grad():
            for batch_id, batch in enumerate(self._dl_val,1):
                print(f"            Processing batch {batch_id} of {len(self._dl_val)}")
                # Extract the inputs, the attention masks and the
                # targets from the batch
                src_input = batch[0].to(device)
                masks_input = batch[1].to(device)
                tgt_out = batch[2].to(device)
                tgt_classes = list(map(lambda i: self._classifier_ids_classes[i], batch[3].numpy()))
                print(f"                Model predictions...")
                # Feed the model
                outputs = self._model(src_input, masks_input)
                print(f"                Computing loss...")
                # Compute the loss
                loss = self._loss_fn(outputs, tgt_out)
                total_loss += loss.item()
                # Exctract the predicted values and the expected output
                with torch.no_grad():
                    _, predicted = outputs.max(1)
                    _, expected_out = tgt_out.max(1)
                prediction_results = (predicted == expected_out)
                v_predictions_per_class["total"] += tgt_out.size(0)
                expected_values_per_class_ids = Counter(expected_out.detach().cpu().numpy())
                for expected_class_id, occurrences in expected_values_per_class_ids.items():
                    expected_class_label = self._classifier_ids_labels[expected_class_id]
                    v_predictions_per_class['classes'][expected_class_label]['total'] += occurrences

                for idx, result in enumerate(prediction_results):
                    expected_class_id = expected_out.detach().cpu().numpy()[idx]
                    expected_class_label = self._classifier_ids_labels[expected_class_id]
                    if result == False:
                        predicted_class_id = predicted.detach().cpu().numpy()[idx]
                        predicted_class_label = self._classifier_ids_labels[predicted_class_id]
                        v_predictions_per_class['classes'][expected_class_label]["wrong"] += 1
                        v_predictions_per_class['classes'][expected_class_label]["predicted"].append(predicted_class_label)
                        v_predictions_per_class['classes'][expected_class_label]["wrong_class"].append(tgt_classes[idx])
                    else:
                        v_predictions_per_class['classes'][expected_class_label]["correct"] += 1
                        v_predictions_per_class['classes'][expected_class_label]["correct_class"].append(tgt_classes[idx])
                # Accumulate predictions and labels
                all_predictions.extend(predicted.detach().cpu().numpy())
                all_labels.extend(expected_out.detach().cpu().numpy())
        # Compute the average validation loss
        mean_v_loss = total_loss / len(self._dl_val)
        # Compute the f1_score of the model within the accumulation
        # steps
        predictions_numpy = np.array(all_predictions)
        labels_numpy = np.array(all_labels)
        if utils.is_main_process():
            print(f"                Computing statistics...")
        v_f1 = f1_score(labels_numpy, predictions_numpy, average=None, zero_division=0, labels=list(self._classifier_ids_labels.keys()))
        v_f1 = [[self._classifier_ids_labels[i], score] for i, score in enumerate(v_f1)]
        # Compute accuracy
        v_accuracy = accuracy_score(labels_numpy, predictions_numpy)
        # Compute precision
        v_precision = precision_score(labels_numpy, predictions_numpy, average=None, zero_division=0, labels=list(self._classifier_ids_labels.keys()))
        v_precision = [[self._classifier_ids_labels[i], score] for i, score in enumerate(v_precision)]
        # Compute recall
        v_recall = recall_score(labels_numpy, predictions_numpy, average=None, zero_division=0, labels=list(self._classifier_ids_labels.keys()))
        v_recall = [[self._classifier_ids_labels[i], score] for i, score in enumerate(v_recall)]
        return mean_v_loss, v_f1, v_accuracy, v_precision, v_recall, v_predictions_per_class

    def evaluation(
        self,
        device: Union[int,str]
    ):
        """
        The method computes the testing phase.

        Parameters
        ----------
        device: int
            The identifier of the rank gpu or the cpu

        Returns
        -------
        stats: dict
            The statistics of the testing phase
        """
        # Dictionary of the statistics
        stats = {
            'test_loss': [],
            'test_f1_score': [],
            'test_accuracy': [],
            'test_precision': [],
            'test_recall': [],
            'test_predictions_per_class': {}
        }
        # model in evaluation mode
        self._model.eval()
        # Accumulate predictions and labels
        all_predictions = []
        all_labels = []
        test_predictions_per_class = {'classes': {k: {"correct": 0, "wrong": 0, "predicted": [], "wrong_class": [], "correct_class": [], "total": 0} for k in self._classifier_ids_labels.values()}, 'total': 0}

        # The validation phase is performed without accumulating
        # the gradient descent and without updating the weights
        # of the model
        if utils.is_main_process():
            print("        Performing testing evaluation...")
        with torch.no_grad():
            for batch_id, batch in enumerate(self._dl_test,1):
                print(f"            Processing batch {batch_id} of {len(self._dl_test)}")
                # Extract the inputs, the attention masks and the
                # targets from the batch
                src_input = batch[0].to(device)
                masks_input = batch[1].to(device)
                tgt_out = batch[2].to(device)
                tgt_classes = list(map(lambda i: self._classifier_ids_classes[i], batch[3].numpy()))
                print(f"                Model predictions...")
                # Feed the model
                outputs = self._model(src_input, masks_input)
                # Exctract the predicted values and the expected output
                with torch.no_grad():
                    _, predicted = outputs.max(1)
                    _, expected_out = tgt_out.max(1)
                prediction_results = (predicted == expected_out)
                test_predictions_per_class['total'] += tgt_out.size(0)
                expected_values_per_class_ids = Counter(expected_out.detach().cpu().numpy())

                for expected_class_id, occurrences in expected_values_per_class_ids.items():
                    expected_class_label = self._classifier_ids_labels[expected_class_id]
                    test_predictions_per_class['classes'][expected_class_label]['total'] += occurrences

                for idx, result in enumerate(prediction_results):
                    expected_class_id = expected_out.detach().cpu().numpy()[idx]
                    expected_class_label = self._classifier_ids_labels[expected_class_id]
                    if result == False:
                        predicted_class_id = predicted.detach().cpu().numpy()[idx]
                        predicted_class_label = self._classifier_ids_labels[predicted_class_id]
                        test_predictions_per_class['classes'][expected_class_label]["wrong"] += 1
                        test_predictions_per_class['classes'][expected_class_label]["predicted"].append(predicted_class_label)
                        test_predictions_per_class['classes'][expected_class_label]["wrong_class"].append(tgt_classes[idx])
                    else:
                        test_predictions_per_class['classes'][expected_class_label]["correct"] += 1
                        test_predictions_per_class['classes'][expected_class_label]["correct_class"].append(tgt_classes[idx])
                # Accumulate predictions and labels
                all_predictions.extend(predicted.detach().cpu().numpy())
                all_labels.extend(expected_out.detach().cpu().numpy())
        if utils.is_main_process():
            print(f"                Computing statistics...")
        # Compute the f1 score of the model within the accumulation
        # steps
        predictions_numpy = np.array(all_predictions)
        labels_numpy = np.array(all_labels)
        test_f1 = f1_score(labels_numpy, predictions_numpy, average=None, labels=list(self._classifier_ids_labels.keys()), zero_division=0)
        test_f1 = [[self._classifier_ids_labels[i], score] for i, score in enumerate(test_f1)]
        # Compute accuracy
        test_accuracy = accuracy_score(labels_numpy, predictions_numpy)
        # Compute precision
        test_precision = precision_score(labels_numpy, predictions_numpy, average=None, zero_division=0, labels=list(self._classifier_ids_labels.keys()))
        test_precision = [[self._classifier_ids_labels[i], score] for i, score in enumerate(test_precision)]
        # Compute recall
        test_recall = recall_score(labels_numpy, predictions_numpy, average=None, zero_division=0, labels=list(self._classifier_ids_labels.keys()))
        test_recall = [[self._classifier_ids_labels[i], score] for i, score in enumerate(test_recall)]
        # Perform testing to measure performances on unseen data
        self.print_evaluation_stats(test_f1, test_accuracy, test_precision, test_recall, test_predictions_per_class)
        stats["test_f1"] = test_f1
        stats["test_accuracy"] = test_accuracy
        stats["test_precision"] = test_precision
        stats["test_recall"] = test_recall
        stats["test_predictions_per_class"] = test_predictions_per_class
        return stats
