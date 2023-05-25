from enum import Enum

class HyperParameter(Enum):
    """
    Helper enums class: it defines the values of the hyperparameters of the model.
    """
    # number of sequence in each batch
    BATCH_SIZE = 256
    # learning rate for the gradient descent
    LR = 0.001
    # number of epochs to train the model
    NUM_EPOCHS = 3
    # Specify the number of folds for cross-validation
    NUM_SPLITS = 10
    # number of steps after which computes the validation loss and accuracy
    NUM_STEPS = 50
    # percentage of the dataset that will be used for test (the remaining percentage will be for training and validation)
    TEST_RATIO = 0.1
