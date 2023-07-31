import json
import os
import timeit
import numpy as np
from typing import Type, Union, Tuple, Dict, List
from collections import Counter
import torch
from torch.optim import AdamW
from transformers import PreTrainedModel, PreTrainedTokenizer
from torch.nn import Module
from torch.utils.data import DataLoader
from sklearn.metrics import f1_score, accuracy_score, precision_score, recall_score
from src.types.ClassificationType import ClassificationType
from src.types.TransformerType import TransformerType
from src.utils import utils, logger


class OracleTrainer:
    """
    The {@code OracleTrainer} class is a helper class that, given the loss function, the optimizer, the model,
    and the training and validation datasets perform the training of the model, computes the loss and
    the f1 score of the training and validation phases and saves the statistics of the training, over the epochs.

    Parameters
    ----------
    model: Type[PreTrainedModel]
    optimizer:
        The optimizer used to perform the backpropagation and updates the weights of the model
    dl_train: DataLoader
        The training dataloader which contains the batches of datapoints for the training phase
    dl_val: DataLoader
        The validation dataloader which contains the batches of datapoints for the validation phase
    dl_test: DataLoader
        The test dataloader which contains the batches of datapoints for the testing phase
    classifier_ids_labels: Dict[int,str]
        The dictionary of labels. The keys are numerical identifiers (int), while the values are strings representing
        the name of the corresponding target label. The dictionary is empty if the dataset has not been processed yet.
    classification_type: Type[ClassificationType]
        Category prediction or label prediction
    transformerType: Type[TransformerType]
        The classificator type (encoder or decoder)
    checkpoint_path: str
        The path where to save model checkpoints
    scheduler: Type[torch.optim.lr_scheduler.LambdaLR]
        Scheduler to update the learning rate
    max_grad_norm: float
        Maximum value for gradient normalization
    tokenizer  : Type[PreTrainedTokenizer]
        The instance of tokenizer used to tokenize the input dataset.

    Attributes
    ----------
    _model: Type[PreTrainedModel]
        The model to train
    _optimizer: Type[AdamW]
        The optimizer used to perform the backpropagation and updates the weights of the model
    _dl_train: Type[DataLoader]
        The training dataloader which contains the batches of datapoints for the training phase
    _dl_val: Type[DataLoader]
        The validation dataloader which contains the batches of datapoints for the validation phase
    _dl_test: Type[DataLoader]
        The test dataloader which contains the batches of datapoints for the testing phase
    _classifier_ids_labels: Dict[int,str]
        The dictionary of labels of the classification model, where the value is the name of a target label, while the
        key element is a numerical identifier representing the index of the one-shot vector representing the target label,
        with value equals to 1.0.
    _classification_type: Type[ClassificationType]
        Category prediction or label prediction
    _transformer_type: Type[TransformerType]
        The classificatorType (encoder or decoder).
    _checkpoint_path: str
        The path where to save model checkpoints
    _scheduler: Type[torch.optim.lr_scheduler.LambdaLR]
        Scheduler to update the learning rate
    _max_grad_norm: float
        Maximum value for gradient normalization
    _tokenizer  : Type[PreTrainedTokenizer]
        The instance of tokenizer used to tokenize the input dataset.
    """

    def __init__(
            self,
            model: Type[PreTrainedModel],
            optimizer: Type[AdamW],
            dl_train: Type[DataLoader],
            dl_val: Type[DataLoader],
            dl_test: Type[DataLoader],
            classifier_ids_labels: Dict[int, str],
            classification_type: Type[ClassificationType],
            transformer_type: Type[TransformerType],
            checkpoint_path: str,
            scheduler: Type[torch.optim.lr_scheduler.LambdaLR],
            tokenizer: PreTrainedTokenizer,
            max_grad_norm: float = 1.0
    ):
        self._model = model
        self._dl_train = dl_train
        self._dl_val = dl_val
        self._dl_test = dl_test
        self._optimizer = optimizer
        self._classifier_ids_labels = classifier_ids_labels
        self._classification_type = classification_type
        self._transformer_type = transformer_type
        self._checkpoint_path = checkpoint_path
        self._max_grad_norm = max_grad_norm
        self._scheduler = scheduler
        self._tokenizer = tokenizer

    def _save_checkpoint(
            self,
            epoch: int,
            step: int,
            stats: Dict[str, any]
    ):
        if not os.path.exists(self._checkpoint_path):
            os.makedirs(self._checkpoint_path)
        filename = os.path.join(self._checkpoint_path, f"checkpoint_{str(epoch)}_{str(step)}.pt")
        torch.save({
            "stats": stats,
            "epoch": epoch,
            "model_state_dict": self._model.state_dict(),
            "optimizer_state_dict": self._optimizer.state_dict(),
            "scheduler_state_dict": self._scheduler.state_dict()
        }, filename)

    def train(
            self,
            num_epochs: int,
            num_steps: int,
            device: Union[int, str],
            accumulation_steps,
            max_grad_norm: float
    ):
        """
        The method performs the training and validation phases of the model.

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
            't_f1_score_micro': [],
            'v_f1_score': [],
            'v_f1_score_micro': [],
            't_accuracy': [],
            'v_accuracy': [],
            't_precision': [],
            'v_precision': [],
            't_recall': [],
            'v_recall': []
        }

        # Steps counter
        steps = 0

        # In each epoch the trainer train the model batch by batch,
        # with all the batch of the training dataset. After a given
        # number of *accumulation_steps* the trainer performs the
        # backpropagation and updates the weights accordingly.
        # Moreover, it computes the f1 score and the total loss of
        # the training, and performs the validation to understand how
        # well the model generalize on the validation data.
        for epoch in range(1, num_epochs + 1):
            print(f"        Start training - Epoch {epoch} of {num_epochs}")
            total_loss = 0
            all_predictions = []
            all_labels = []
            # Define early stopping criteria
            patience = 3  # Number of epochs to wait for improvement
            best_f1_score_micro = 0
            counter = 0

            # model in training mode
            self._model.train()
            self._optimizer.zero_grad()

            for step, batch in enumerate(self._dl_train, 1):
                print(f"            Processing step {step} of {len(self._dl_train)}")
                steps += 1
                # Extract the inputs, the attention masks and the expected
                # outputs from the batch
                src_input = batch[0].to(device)
                src_masks = batch[1].to(device)
                tgt_out = batch[2].to(device)

                # Train the model
                print(f"                Model predictions...")
                outputs = self._model(
                    input_ids=src_input,
                    attention_mask=src_masks,
                    labels=tgt_out
                )
                # Compute the loss
                print(f"                Computing loss...")


                #tgt_out_loss = tgt_out.reshape(-1)

                #outputs_loss = outputs.logits.reshape(-1, outputs.logits.shape[2])
                #tgt_out_loss = tgt_out[:, 1:].reshape(-1)
                loss =  outputs.loss
                loss = loss / accumulation_steps
                loss.backward()
                # Gradient clipping. It is used to mitigate the problem of exploding gradients
                torch.nn.utils.clip_grad_norm_(self._model.parameters(), max_grad_norm)

                # Decode outputs and expected values
                if self._transformer_type == TransformerType.DECODER:
                    predicted = np.array(
                        self._tokenizer.batch_decode(
                            torch.argmax(torch.softmax(outputs.logits, dim=-1), dim=-1),
                            skip_special_tokens=True
                        )
                    )
                    expected_out = np.array(
                        self._tokenizer.batch_decode(
                            tgt_out,
                            skip_special_tokens=True
                        )
                    )
                else:
                    predicted_ids = torch.argmax(torch.softmax(outputs.logits, dim=-1), dim=-1)
                    predicted = np.array(list(map(lambda x: self._classifier_ids_labels[x], predicted_ids.tolist())))
                    expected_out = np.array(list(map(lambda x: self._classifier_ids_labels[x], tgt_out.tolist())))

                # Accumulate predictions and labels
                all_predictions.extend(predicted)
                all_labels.extend(expected_out)

                if (steps % accumulation_steps) == 0:
                    print(f"                Weigths update...")
                    # Update the weights of the model
                    self._optimizer.step()
                    self._optimizer.zero_grad()
                    self._scheduler.step()
                    # Update the total loss
                    total_loss += loss.item()

                if (steps % num_steps) == 0:
                    # Compute average statistics for the loss
                    mean_t_loss = total_loss / (num_steps / accumulation_steps)
                    # Compute the f1 score of the model within the accumulation steps
                    predictions_numpy = np.array(all_predictions)
                    labels_numpy = np.array(all_labels)
                    t_f1 = f1_score(
                        labels_numpy,
                        predictions_numpy,
                        average=None,
                        zero_division=0,
                        labels=list(self._classifier_ids_labels.values())
                    )
                    t_f1 = [[self._classifier_ids_labels[i], score] for i, score in enumerate(t_f1)]
                    t_f1_micro = f1_score(
                        labels_numpy,
                        predictions_numpy,
                        average='micro',
                        zero_division=0,
                        labels=list(self._classifier_ids_labels.values())
                    )
                    # Compute accuracy
                    t_accuracy = accuracy_score(labels_numpy, predictions_numpy)
                    # Compute precision
                    t_precision = precision_score(
                        labels_numpy,
                        predictions_numpy,
                        average=None,
                        zero_division=0,
                        labels=list(self._classifier_ids_labels.values())
                    )
                    t_precision = [[self._classifier_ids_labels[i], score] for i, score in enumerate(t_precision)]
                    # Compute recall
                    t_recall = recall_score(
                        labels_numpy,
                        predictions_numpy,
                        average=None,
                        zero_division=0,
                        labels=list(self._classifier_ids_labels.values())
                    )
                    t_recall = [[self._classifier_ids_labels[i], score] for i, score in enumerate(t_recall)]

                    # Validation phase
                    mean_v_loss, v_f1, v_f1_micro, v_accuracy, v_precision, v_recall = self.validation(device)

                    # Update the statistics
                    stats['t_loss'].append(mean_t_loss)
                    stats['v_loss'].append(mean_v_loss)
                    stats['t_f1_score'].append(t_f1)
                    stats['t_f1_score_micro'].append(t_f1_micro)
                    stats['v_f1_score'].append(v_f1)
                    stats['v_f1_score_micro'].append(v_f1_micro)
                    stats['t_accuracy'].append(t_accuracy)
                    stats['v_accuracy'].append(v_accuracy)
                    stats['t_precision'].append(t_precision)
                    stats['v_precision'].append(v_precision)
                    stats['t_recall'].append(t_recall)
                    stats['v_recall'].append(v_recall)

                    # Print the statistics
                    logger.print_stats(
                        epoch,
                        num_epochs,
                        step + 1,
                        len(self._dl_train),
                        {
                            't_loss': mean_t_loss,
                            'v_loss': mean_v_loss,
                            't_f1_score': t_f1,
                            't_f1_score_micro': t_f1_micro,
                            'v_f1_score': v_f1,
                            'v_f1_score_micro': v_f1_micro,
                            't_accuracy': t_accuracy,
                            'v_accuracy': v_accuracy,
                            't_precision': t_precision,
                            'v_precision': v_precision,
                            't_recall': t_recall,
                            'v_recall': v_recall
                        }
                    )

                    # Reset the total loss
                    total_loss = 0
                    interval = timeit.default_timer()
                    # Clear the predictions and labels lists
                    all_predictions = []
                    all_labels = []

                    # Save checkpoints
                    self._save_checkpoint(epoch, step, stats)

                    # Round F1-Score to discard minor improvments and speed-up convergence
                    v_f1_micro = round(v_f1_micro, 2)
                    # Check if validation loss has improved
                    if v_f1_micro > best_f1_score_micro:
                        counter = 0
                        best_f1_score_micro = v_f1_micro
                    else:
                        counter += 1
                        if counter > patience and epoch > 1:
                            print("                Early stopping triggered. Training stopped.")
                            return stats
        return stats

    def validation(
            self,
            device: Union[int, str]
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
        # The validation phase is performed without accumulating
        # the gradient descent and without updating the weights
        # of the model

        print("        Performing validation step...")
        with torch.no_grad():
            for batch_id, batch in enumerate(self._dl_val, 1):
                print(f"            Processing batch {batch_id} of {len(self._dl_val)}")
                # Extract the inputs, the attention masks and the
                # targets from the batch
                src_input = batch[0].to(device)
                src_masks = batch[1].to(device)
                tgt_out = batch[2].to(device)

                # Train the model
                print(f"                Model predictions...")
                outputs = self._model(
                    input_ids=src_input,
                    attention_mask=src_masks,
                    labels=tgt_out
                )
                print(f"                Computing loss...")
                # Compute the loss
                loss = outputs.loss
                total_loss += loss.item()
                # Exctract the predicted values and the expected output
                # Decode outputs and expected values
                if self._transformer_type == TransformerType.DECODER:
                    predicted = np.array(
                        self._tokenizer.batch_decode(
                            torch.argmax(torch.softmax(outputs.logits, dim=-1), dim=-1),
                            skip_special_tokens=True
                        )
                    )
                    expected_out = np.array(
                        self._tokenizer.batch_decode(
                            tgt_out,
                            skip_special_tokens=True
                        )
                    )
                else:
                    predicted_ids = torch.argmax(torch.softmax(outputs.logits, dim=-1), dim=-1)
                    predicted = np.array(list(map(lambda x: self._classifier_ids_labels[x], predicted_ids.tolist())))
                    expected_out = np.array(list(map(lambda x: self._classifier_ids_labels[x], tgt_out.tolist())))
                # Accumulate predictions and labels
                all_predictions.extend(predicted)
                all_labels.extend(expected_out)
        # Compute the average validation loss
        mean_v_loss = total_loss / len(self._dl_val)
        # Compute the f1_score of the model within the accumulation
        # steps
        predictions_numpy = np.array(all_predictions)
        labels_numpy = np.array(all_labels)
        print(f"                Computing statistics...")
        v_f1 = f1_score(
            labels_numpy,
            predictions_numpy,
            average=None,
            zero_division=0,
            labels=list(self._classifier_ids_labels.values())
        )
        v_f1 = [[self._classifier_ids_labels[i], score] for i, score in enumerate(v_f1)]
        v_f1_micro = f1_score(
            labels_numpy,
            predictions_numpy,
            average="micro",
            zero_division=0,
            labels=list(self._classifier_ids_labels.values())
        )
        # Compute accuracy
        v_accuracy = accuracy_score(labels_numpy, predictions_numpy)
        # Compute precision
        v_precision = precision_score(
            labels_numpy,
            predictions_numpy,
            average=None,
            zero_division=0,
            labels=list(self._classifier_ids_labels.values())
        )
        v_precision = [[self._classifier_ids_labels[i], score] for i, score in enumerate(v_precision)]
        # Compute recall
        v_recall = recall_score(
            labels_numpy,
            predictions_numpy,
            average=None,
            zero_division=0,
            labels=list(self._classifier_ids_labels.values())
        )
        v_recall = [[self._classifier_ids_labels[i], score] for i, score in enumerate(v_recall)]
        return mean_v_loss, v_f1, v_f1_micro, v_accuracy, v_precision, v_recall

    def evaluation(
            self,
            device: Union[int, str]
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
        # model in evaluation mode
        self._model.eval()
        # Accumulate predictions and labels
        all_predictions = []
        all_labels = []

        # The validation phase is performed without accumulating
        # the gradient descent and without updating the weights
        # of the model
        print("        Performing testing evaluation...")
        with torch.no_grad():
            for batch_id, batch in enumerate(self._dl_test, 1):
                print(f"            Processing batch {batch_id} of {len(self._dl_test)}")
                # Extract the inputs, the attention masks and the
                # targets from the batch
                src_input = batch[0].to(device)
                src_masks = batch[1].to(device)
                tgt_out = batch[2].to(device)

                # Train the model
                print(f"                Model predictions...")
                outputs = self._model(
                    input_ids=src_input,
                    attention_mask=src_masks,
                    labels=tgt_out
                )
                # Compute the loss
                print(f"                Computing loss...")
                # Exctract the predicted values and the expected output
                # Decode outputs and expected values
                if self._transformer_type == TransformerType.DECODER:
                    predicted = np.array(
                        self._tokenizer.batch_decode(
                            torch.argmax(torch.softmax(outputs.logits, dim=-1), dim=-1),
                            skip_special_tokens=True
                        )
                    )
                    expected_out = np.array(
                        self._tokenizer.batch_decode(
                            tgt_out,
                            skip_special_tokens=True
                        )
                    )
                else:
                    predicted_ids = torch.argmax(torch.softmax(outputs.logits, dim=-1), dim=-1)
                    predicted = np.array(list(map(lambda x: self._classifier_ids_labels[x], predicted_ids.tolist())))
                    expected_out = np.array(list(map(lambda x: self._classifier_ids_labels[x], tgt_out.tolist())))
                # Accumulate predictions and labels
                all_predictions.extend(predicted)
                all_labels.extend(expected_out)
        print(f"                Computing statistics...")
        # Compute the f1 score of the model within the accumulation
        # steps
        predictions_numpy = np.array(all_predictions)
        labels_numpy = np.array(all_labels)
        test_f1 = f1_score(
            labels_numpy,
            predictions_numpy,
            average=None,
            labels=list(self._classifier_ids_labels.values()),
            zero_division=0
        )
        test_f1 = [[self._classifier_ids_labels[i], score] for i, score in enumerate(test_f1)]
        test_f1_micro = f1_score(
            labels_numpy,
            predictions_numpy,
            average='micro',
            labels=list(self._classifier_ids_labels.values()),
            zero_division=0
        )
        # Compute accuracy
        test_accuracy = accuracy_score(labels_numpy, predictions_numpy)
        # Compute precision
        test_precision = precision_score(
            labels_numpy,
            predictions_numpy,
            average=None,
            zero_division=0,
            labels=list(self._classifier_ids_labels.values())
        )
        test_precision = [[self._classifier_ids_labels[i], score] for i, score in enumerate(test_precision)]
        # Compute recall
        test_recall = recall_score(labels_numpy, predictions_numpy, average=None, zero_division=0,labels=list(self._classifier_ids_labels.values()))
        test_recall = [[self._classifier_ids_labels[i], score] for i, score in enumerate(test_recall)]
        # Perform testing to measure performances on unseen data
        logger.print_evaluation_stats(test_f1, test_f1_micro, test_accuracy, test_precision, test_recall)
        stats = {}
        stats["test_f1"] = test_f1
        stats["test_f1_micro"] = test_f1_micro
        stats["test_accuracy"] = test_accuracy
        stats["test_precision"] = test_precision
        stats["test_recall"] = test_recall
        return stats
