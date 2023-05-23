import math
import timeit
import numpy as np
from typing import Type

import torch
import torch.optim as optim
from torch.nn import Module
from torch.utils.data import DataLoader

from sklearn.metrics import average_precision_score, accuracy_score, precision_score, recall_score
from src.model.OracleClassifier import OracleClassifier


class OracleTrainer:
    """
    The *OracleTrainer* class is an helper class that, given the loss
    function, the optimizer, the model, and the training and validation
    datasets perform the training of the model and computes the loss and
    the auprc of the training and validation phases, saves the statistics
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
    """
    def __init__(
            self,
            model: Type[OracleClassifier],
            loss_fn: Type[Module],
            optimizer: Type[optim.Adam],
            dl_train: Type[DataLoader],
            dl_val: Type[DataLoader],
            dl_test: Type[DataLoader]
    ):
        self._model = model
        self._dl_train = dl_train
        self._dl_val = dl_val
        self._dl_test = dl_test
        self._loss_fn = loss_fn
        self._optimizer = optimizer

    def train(
            self,
            num_epochs: int,
            num_steps: int,
            gpu_id: int,
            best_time: float = math.inf
    ):
        """
        The method perform the training and validation phases of the model.

        Parameters
        ----------
        num_epochs: int
            The number of epochs to train the model
        num_steps: int
            The number of steps after which compute the gradients and upldate the weights
        gpu_id: int
            The identifier of the rank gpu
        best_time:
            Best time for statistics performance
        
        Returns
        -------
        stats: dict
            The statistics of the testing phase
        """
        print("    Start Training...")
        # Dictionary of the statistics
        stats = {
            't_loss': [],
            'v_loss': [],
            't_auprc': [],
            'v_auprc': [],
            't_accuracy': [],
            'v_accuracy': [],
            't_precision': [],
            'v_precision': [],
            't_recall': [],
            'v_recall': [],
        }
        steps = 0
        accumulation_steps = 8
        time_over = False

        # In each epoch the trainer train the model batch by batch,
        # with all the batch of the training dataset. After a given
        # number of *accumulation_steps* the trainer performs the
        # backpropagation and updates the weights accordingly.
        # Moreover, it computes the auprc and the total loss of
        # the training, and performs the validation to understand how
        # well the model generalize on the validation data.
        for epoch in range(1, num_epochs + 1):
            total_loss = 0
            auprc_t = 0
            trained_total = 0
            predicted_correct = 0
            all_predictions = []
            all_labels = []

            start = timeit.default_timer()

            # model in training mode
            self._model.train()
            self._optimizer.zero_grad()

            for step, batch in enumerate(self._dl_train):
                print(f"        Processing step {step+1} of {len(self._dl_train)}")
                steps += 1

                # Extract the inputs, the attention masks and the expected
                # outputs from the batch
                src_input = batch[0].to(gpu_id)
                masks_input = batch[1].to(gpu_id)
                tgt_out = batch[2].to(gpu_id)

                # Train the model
                outputs = self._model(src_input, masks_input)

                # Compute the loss
                loss = self._loss_fn(outputs, tgt_out)
                loss.backward()

                # Exctract the predicted values and the expected output
                with torch.no_grad():
                    _, predicted = outputs.max(1)
                    _, expected_out = tgt_out.max(1)
                # Update the counter of the predictions
                trained_total += tgt_out.size(0)
                predicted_correct += (predicted == expected_out).sum().item()

                # Accumulate predictions and labels
                all_predictions.extend(predicted.detach().cpu().numpy())
                all_labels.extend(expected_out.detach().cpu().numpy())

                if (steps % accumulation_steps) == 0:
                    # Update the weights of the model
                    self._optimizer.step()
                    self._optimizer.zero_grad()
                    # Update the total loss
                    total_loss += loss.item()
                    # Reset counters
                    trained_total = 0
                    predicted_correct = 0

                if (steps % num_steps) == 0:
                    # Compute average statistics for the loss
                    mean_t_loss = total_loss / (num_steps / accumulation_steps)
                    # Compute the auprc of the model within the accumulation
                    # steps
                    predictions_numpy = np.array(all_predictions)
                    labels_numpy = np.array(all_labels)
                    auprc_t = average_precision_score(labels_numpy, predictions_numpy)

                    # Validation phase
                    mean_v_loss, auprc_v, accuracy, precision, recall = self.validation(gpu_id)

                    # Update the statistics
                    stats['t_loss'].append(mean_t_loss)
                    stats['v_loss'].append(mean_v_loss)
                    stats['t_auprc'].append(mean_t_loss)
                    stats['v_auprc'].append(mean_v_loss)
                    stats['t_accuracy'].append(mean_t_loss)
                    stats['v_accuracy'].append(mean_v_loss)
                    stats['t_precision'].append(mean_t_loss)
                    stats['v_precision'].append(mean_v_loss)
                    stats['t_recall'].append(mean_t_loss)
                    stats['v_recall'].append(mean_v_loss)

                    # Print the statistics
                    self.print_stats(
                        epoch,
                        num_epochs,
                        step + 1,
                        len(self._dl_train),
                        mean_t_loss,
                        auprc_t,
                        mean_v_loss,
                        auprc_t
                    )

                    # Reset the total loss
                    total_loss = 0
                    interval = timeit.default_timer()
                    # Clear the predictions and labels lists
                    all_predictions = []
                    all_labels = []

                    # If time is over, stop the training
                    if int(interval - start) > best_time or int(interval - start) > 6000:
                        time_over = True
                        stats['time_100'] = math.inf
                        break
        return stats

    @staticmethod
    def print_stats(
            epoch: int,
            num_epochs: int,
            step: int,
            total_steps: int,
            mean_t_loss: float,
            auprc_t: float,
            mean_v_loss: float,
            auprc_v: float
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
        auprc_t: float
            Area Under the Precision-Recall Curve of the training phase
        accuracy_t: float
            Accuracy of the training phase
        precision_t: float
            Precision of the training phase
        recall_t: float
            Recall of the training phase
        mean_v_loss: float
            Average validation loss
        auprc_v: float
            Area Under the Precision-Recall Curve of the validation phase
        accuracy_v: float
            Accuracy of the validation phase
        precision_v: float
            Precision of the validation phase
        recall_v: float
            Recall of the validation phase
        """
        print("    " + '-'*30)
        print("    " + "STATISTICS")
        print("    " + '-'*30)
        print("    " + f"EPOCH: [{epoch} / {num_epochs}]")
        print("    " + f"STEP: [{step} / {total_steps}]")
        print("    " + f"TRAINING LOSS: {mean_t_loss:.4f}")
        print("    " + f"TRAINING AUPRC: {auprc_t:.2f}%")
        print("    " + f"TRAINING ACCURACY: {accuracy_t:.2f}%")
        print("    " + f"TRAINING PRECISION: {precision_t:.2f}%")
        print("    " + f"TRAINING RECALL: {recall_t:.2f}%")
        print("    " + '-'*30)
        print("    " + f"VALIDATION LOSS: {mean_v_loss:.4f}")
        print("    " + f"VALIDATION AUPRC: {auprc_v:.2f}%")
        print("    " + f"VALIDATION ACCURACY: {accuracy_v:.2f}%")
        print("    " + f"VALIDATION PRECISION: {precision_v:.2f}%")
        print("    " + f"VALIDATION RECALL: {recall_v:.2f}%")
        print("    " + '-'*30)

    @staticmethod
    def print_evaluation_stats(
            auprc_t: float,
            accuracy: float,
            precision_t: float,
            recall: float
    ):
        """
        The method prints the statistics of the testing phases

        Parameters
        ----------
        auprc_t: float
            Area Under the Precision-Recall Curve of the testing phase
        accuracy_t: float
            Accuracy of the testing phase
        precision_t: float
            Precision of the testing phase
        recall_t: float
            Recall of the testing phase
        """
        print("    " + '-'*30)
        print("    " + "TESTING STATISTICS")
        print("    " + '-'*30)
        print("    " + f"TESTING AUPRC: {auprc_t:.2f}%")
        print("    " + '-'*30)
        print("    " + f"TESTING ACCURACY: {accuracy_t:.2f}%")
        print("    " + '-'*30)
        print("    " + f"TESTING PRECISION: {precision_t:.2f}%")
        print("    " + '-'*30)
        print("    " + f"TESTING RECALL: {recall_t:.2f}%")
        print("    " + '-'*30)

    @staticmethod
    def plot_loss_auprc(
            steps: int,
            ax: any,
            stats: dict
    ):
        """
        The method plots the trend of the loss and auprc

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
                title = ('Training ' if j == 0 else 'Validation') + ('Loss' if i == 0 else 'AUPRC')
                dict_label = ('t_' if j == 0 else 'v_') + ('loss' if i == 0 else 'auprc')
                color = 'blue'
                ax[i][j].set_title(title, fontsize=30)
                ax[i][j].set_xlabel("steps", fontsize=30)
                ax[i][j].set_ylabel("loss", fontsize=30)
                ax[i][j].plot(range(0, len(stats['t_loss']) * steps, steps), np.array(stats[dict_label])[:], '-', color=color)
        for i in range(2):
            title = 'Training and Validation ' + ('Loss' if i == 0 else 'AUPRC')
            dict_label = 'loss' if i == 0 else 'auprc'
            ax[2][i].set_title(title, fontsize=30)
            ax[2][i].set_xlabel("steps", fontsize=30)
            ax[2][i].set_ylabel("loss", fontsize=30)
            ax[2][i].plot(range(0, len(stats['t_loss']) * steps, steps), np.array(stats['t_' + dict_label])[:], '-', color='blue')
            ax[2][i].plot(range(0, len(stats['t_loss']) * steps, steps), np.array(stats['v_' + dict_label])[:], '-', color='orange')

    def validation(
            self,
            gpu_id: int
    ):
        """
        The method computes the validation phase.

        Parameters
        ----------
        gpu_id: int
            The identifier of the rank gpu

        Returns
        -------
        mean_v_loss: float
            Average loss of the validation phase
        auprc:
            Area Under the Precision-Recall Curve of the validation phase
        """
        # model in evaluation mode
        self._model.eval()

        total_loss = 0
        # Accumulate predictions and labels
        all_predictions = []
        all_labels = []
        trained_total = 0
        predicted_total = 0
        total_steps = 0
        # The validation phase is performed without accumulating
        # the gradient descent and without updating the weights
        # of the model
        with torch.no_grad():
            for batch in iter(self._dl_val):
                total_steps += 1
                # Extract the inputs, the attention masks and the
                # targets from the batch
                src_input = batch[0].to(gpu_id)
                masks_input = batch[1].to(gpu_id)
                tgt_out = batch[2].to(gpu_id)
                # Feed the model
                outputs = self._model(src_input, masks_input)
                # Compute the loss
                loss = self._loss_fn(outputs, tgt_out)
                total_loss += loss.item()
                # Exctract the predicted values and the expected output
                with torch.no_grad():
                    _, predicted = outputs.max(1)
                    _, expected_out = tgt_out.max(1)
                predicted_total += (predicted == expected_out).sum().item()
                # Accumulate predictions and labels
                all_predictions.extend(predicted.detach().cpu().numpy())
                all_labels.extend(expected_out.detach().cpu().numpy())
        # Compute the average validation loss
        mean_v_loss = total_loss / len(self._dl_val)
        # Compute the auprc of the model within the accumulation
        # steps
        predictions_numpy = np.array(all_predictions)
        labels_numpy = np.array(all_labels)
        auprc = average_precision_score(labels_numpy, predictions_numpy)
        # Compute accuracy
        accuracy = accuracy_score(labels_numpy, predictions_numpy)
        # Compute precision
        precision = precision_score(labels_numpy, predictions_numpy)
        # Compute recall
        recall = recall_score(labels_numpy, predictions_numpy)
        return mean_v_loss, auprc, accuracy, precision, recall

    def evaluation(self):
        """
        The method computes the testing phase.

        Parameters
        ----------
        gpu_id: int
            The identifier of the rank gpu

        Returns
        -------
        stats: dict
            The statistics of the testing phase
        """
        # Dictionary of the statistics
        stats = {
            'test_loss': [],
            'test_auprc': [],
            'test_accuracy': [],
            'test_precision': [],
            'test_recall': []
        }
        # model in evaluation mode
        self._model.eval()

        total_loss = 0
        # Accumulate predictions and labels
        all_predictions = []
        all_labels = []
        trained_total = 0
        predicted_total = 0
        total_steps = 0
        # The validation phase is performed without accumulating
        # the gradient descent and without updating the weights
        # of the model
        with torch.no_grad():
            for batch in iter(self._dl_test):
                total_steps += 1
                # Extract the inputs, the attention masks and the
                # targets from the batch
                src_input = batch[0].to(gpu_id)
                masks_input = batch[1].to(gpu_id)
                tgt_out = batch[2].to(gpu_id)
                # Feed the model
                outputs = self._model(src_input, masks_input)
                # Exctract the predicted values and the expected output
                with torch.no_grad():
                    _, predicted = outputs.max(1)
                    _, expected_out = tgt_out.max(1)
                predicted_total += (predicted == expected_out).sum().item()
                # Accumulate predictions and labels
                all_predictions.extend(predicted.detach().cpu().numpy())
                all_labels.extend(expected_out.detach().cpu().numpy())
        # Compute the auprc of the model within the accumulation
        # steps
        predictions_numpy = np.array(all_predictions)
        labels_numpy = np.array(all_labels)
        auprc = average_precision_score(labels_numpy, predictions_numpy)
        # Compute accuracy
        accuracy = accuracy_score(labels_numpy, predictions_numpy)
        # Compute precision
        precision = precision_score(labels_numpy, predictions_numpy)
        # Compute recall
        recall = recall_score(labels_numpy, predictions_numpy)
        # Perform testing to measure performances on unseen data
        self.print_evaluation_stats(auprc, accuracy, precision, recall)
        return stats
