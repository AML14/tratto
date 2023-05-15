import math
import timeit
import numpy as np
from typing import Type

import torch
import torch.optim as optim
from torch.nn import Module
from torch.utils.data import DataLoader

from src.model.OracleClassifier import OracleClassifier


class OracleTrainer:
    """
    The *OracleTrainer* class is an helper class that, given the loss
    function, the optimizer, the model, and the training and validation
    datasets perform the training of the model and computes the loss and
    the accuracy of the training and validation phases, saves the statistics
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
    """
    def __init__(
            self,
            model: Type[OracleClassifier],
            loss_fn: Type[Module],
            optimizer: Type[optim.Adam],
            dl_train: Type[DataLoader],
            dl_val: Type[DataLoader]
    ):
        self.model = model
        self.dl_train = dl_train
        self.dl_val = dl_val
        self.loss_fn = loss_fn
        self.optimizer = optimizer

    def train(
            self,
            num_epochs: int,
            num_steps: int,
            device: Type[torch.device],
            break_end: float = 99.9,
            best_time: float = math.inf,
    ):
        """
        The method perform the training and validation phases of the model.

        Parameters
        ----------
        num_epochs: int
            The number of epochs to train the model
        num_steps: int
            The number of steps after which compute the gradients and upldate the weights
        device: torch.device
            The device where to perform the training and the validation of the
            model (CPU or GPU)
        break_end:
            The accuracy threshold to stop the training
        best_time:
            Best time for statistics performance
        """
        print("    Start Training...")

        # Dictionary of the statistics
        stats = {
            't_loss': [],
            'v_loss': [],
            't_accuracy': [],
            'v_accuracy': []
        }
        steps = 0
        accumulation_steps = 8
        flag_90 = False
        flag_end = False
        time_over = False

        # In each epoch the trainer train the model batch by batch,
        # with all the batch of the training dataset. After a given
        # number of *accumulation_steps* the trainer performs the
        # backpropagation and updates the weights accordingly.
        # Moreover, it computes the accuracy and the total loss of
        # the training, and performs the validation to understand how
        # well the model generalize on the validation data.
        for epoch in range(1, num_epochs + 1):
            total_loss = 0
            total_accuracy = 0
            trained_total = 0
            predicted_correct = 0

            start = timeit.default_timer()

            # model in training mode
            self.model.train()
            self.optimizer.zero_grad()

            for step, batch in enumerate(self.dl_train):
                print(f"    Processing step {step+1} of {len(self.dl_train)}")
                steps += 1

                # Extract the inputs, the attention masks and the expected
                # outputs from the batch
                src_input = batch[0].to(device)
                masks_input = batch[1].to(device)
                tgt_out = batch[2].to(device)

                # Train the model
                outputs = self.model(src_input, masks_input)

                # Compute the loss
                loss = self.loss_fn(outputs, tgt_out)
                loss.backward()

                # Exctract the predicted values and the expected output
                with torch.no_grad():
                    _, predicted = outputs.max(1)
                    _, expected_out = tgt_out.max(1)
                # Update the accuracy of the model, given the predictions
                # of the batch
                trained_total += tgt_out.size(0)
                predicted_correct += (predicted == expected_out).sum().item()

                if (steps % accumulation_steps) == 0:
                    # Update the weights of the model
                    self.optimizer.step()
                    self.optimizer.zero_grad()
                    # Update the total loss
                    total_loss += loss.item()
                    # Compute the accuracy of the model within the accumulation
                    # steps
                    total_accuracy = 100 * predicted_correct/trained_total
                    # Reset the counter for the accuracy
                    trained_total = 0
                    predicted_correct = 0

                if (steps % num_steps) == 0:
                    # Compute average statistics for the loss and the accuracy
                    mean_t_loss = total_loss / (num_steps / accumulation_steps)
                    mean_t_accuracy = total_accuracy / (num_steps / accumulation_steps)

                    # Validation phase
                    mean_v_loss, mean_v_accuracy = self.validation(device)

                    # Update the statistics
                    stats['t_loss'].append(mean_t_loss)
                    stats['t_accuracy'].append(mean_t_accuracy)
                    stats['v_loss'].append(mean_v_loss)
                    stats['v_accuracy'].append(mean_v_accuracy)

                    # Print the statistics
                    self.print_stats(
                        epoch,
                        num_epochs,
                        step + 1,
                        len(self.dl_train),
                        mean_t_loss,
                        mean_t_accuracy,
                        mean_v_loss,
                        mean_v_accuracy
                    )

                    # Reset the total loss and accuracy
                    total_loss = 0
                    total_accuracy = 0
                    interval = timeit.default_timer()

                    # Breakpoint validation accuracy
                    if mean_v_accuracy > 90 and (not flag_90):
                        flag_90 = not flag_90
                        print("    " + '-'*30)
                        print("    " + "BREAKPOINT 90% VALIDATION ACCURACY")
                        print("    " + '-'*30)
                        print("    " + f"TIME: {int(interval - start)}seconds")
                        print("    " + '-'*30)
                        stats['time_90'] = int(interval - start)

                    # Breakpoint validation accuracy - stop the
                    # training to avoid overfitting
                    if mean_v_accuracy > break_end:
                        flag_end = not flag_end
                        interval = timeit.default_timer()
                        print("    " + '-'*30)
                        print("    " + "BREAKPOINT 100% VALIDATION ACCURACY")
                        print("    " + '-'*30)
                        print("    " + f"TIME: {int(interval - start)} seconds")
                        print("    " + '-'*30)
                        print("    " + '-'*30)
                        print("    " + f"FINAL SAMPLES")
                        print("    " + '-'*30)
                        stats['time_100'] = int(interval - start)
                        break

                    # If time is over, stop the training
                    if int(interval - start) > best_time or int(interval - start) > 6000:
                        time_over = True
                        stats['time_100'] = math.inf
                        break
            if flag_end or time_over:
                break
        return stats

    @staticmethod
    def print_stats(
            epoch: int,
            num_epochs: int,
            step: int,
            total_steps: int,
            mean_t_loss: float,
            mean_t_accuracy: float,
            mean_v_loss: float,
            mean_v_accuracy: float
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
        mean_t_accuracy: float
            Average training accuracy
        mean_v_loss: float
            Average validation loss
        mean_v_accuracy: float
            Average validation accuracy
        """
        print("    " + '-'*30)
        print("    " + "STATISTICS")
        print("    " + '-'*30)
        print("    " + f"EPOCH: [{epoch} / {num_epochs}]")
        print("    " + f"STEP: [{step} / {total_steps}]")
        print("    " + f"TRAINING LOSS: {mean_t_loss:.4f}")
        print("    " + f"TRAINING ACCURACY: {mean_t_accuracy:.2f}%")
        print("    " + '-'*30)
        print("    " + f"VALIDATION LOSS: {mean_v_loss:.4f}")
        print("    " + f"VALIDATION ACCURACY: {mean_v_accuracy:.2f}%")
        print("    " + '-'*30)

    @staticmethod
    def plot_loss_accuracy(
            steps: int,
            ax: any,
            stats: dict
    ):
        """
        The method plots the trend of the loss and the accuracy over the epochs

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
                title = ('Training ' if j == 0 else 'Validation') + ('Loss' if i == 0 else 'Accuracy')
                dict_label = ('t_' if j == 0 else 'v_') + ('loss' if i == 0 else 'accuracy')
                color = 'blue'
                ax[i][j].set_title(title, fontsize=30)
                ax[i][j].set_xlabel("steps", fontsize=30)
                ax[i][j].set_ylabel("loss", fontsize=30)
                ax[i][j].plot(range(0, len(stats['t_loss']) * steps, steps), np.array(stats[dict_label])[:], '-', color=color)
        for i in range(2):
            title = 'Training and Validation ' + ('Loss' if i == 0 else 'Accuracy')
            dict_label = 'loss' if i == 0 else 'accuracy'
            ax[2][i].set_title(title, fontsize=30)
            ax[2][i].set_xlabel("steps", fontsize=30)
            ax[2][i].set_ylabel("loss", fontsize=30)
            ax[2][i].plot(range(0, len(stats['t_loss']) * steps, steps), np.array(stats['t_' + dict_label])[:], '-', color='blue')
            ax[2][i].plot(range(0, len(stats['t_loss']) * steps, steps), np.array(stats['v_' + dict_label])[:], '-', color='orange')

    def validation(
            self,
            device: Type[torch.device]
    ):
        """
        The method computes the validation phase.

        Parameters
        ----------
        device: torch.device
            The device where to perform the validation of the model (CPU or GPU)

        Returns
        -------
        mean_v_loss: float
            Average loss of the validation phase
        mean_v_accuracy:
            Average accuracy of the validation phase
        """
        # model in evaluation mode
        self.model.eval()

        total_loss = 0
        total_accuracy = 0
        trained_total = 0
        predicted_total = 0
        total_steps = 0
        # The validation phase is performed without accumulating
        # the gradient descent and without updating the weights
        # of the model
        with torch.no_grad():
            for batch in iter(self.dl_val):
                total_steps += 1
                # Extract the inputs, the attention masks and the
                # targets from the batch
                src_input = batch[0].to(device)
                masks_input = batch[1].to(device)
                tgt_out = batch[2].to(device)
                # Feed the model
                outputs = self.model(src_input, masks_input)
                # Compute the loss
                loss = self.loss_fn(outputs, tgt_out)
                total_loss += loss.item()
                # Exctract the predicted values and the expected output
                with torch.no_grad():
                    _, predicted = outputs.max(1)
                    _, expected_out = tgt_out.max(1)
                # Update the accuracy of the model, given the predictions
                # of the batch
                trained_total += tgt_out.size(0)
                predicted_total += (predicted == expected_out).sum().item()
                # Update the accuracy
                total_accuracy += 100 * predicted_total/trained_total
        # Compute the average validation loss
        mean_v_loss = total_loss / len(self.dl_val)
        # Compute the average validation accuracy
        mean_v_accuracy = total_accuracy / len(self.dl_val)
        return mean_v_loss, mean_v_accuracy
