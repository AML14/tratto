from enum import Enum

class HyperParameter(Enum):
    """
    Helper enums class: it defines the values of the hyperparameters of the model.
    """
    # percentage of the dataset that will be used for training (the remaining percentage will be for validation)
    TRAINING_RATIO = 0.8
    # number of sequence in each batch
    BATCH_SIZE = 16
    # number of epochs to train the model
    NUM_EPOCHS = 1
    # number of steps after which computes the validation loss and accuracy
    NUM_STEPS = 8
    # learning rate for the gradient descent
    LR = 0.0001