from enum import Enum

class HyperParameter(Enum):
    """
    Helper enums class: it defines the values of the hyperparameters of the model.
    """
    # steps of the training after which compute the backpropagation
    ACCUMULATION_STEPS = 1
    # number of sequence in each batch
    BATCH_SIZE = 32
    # learning rate for the gradient descent
    LR = 1e-5
    # adam epsilon
    ADAM_EPSILON = 1e-8
    # weight decay
    WEIGHT_DECAY = 0.0
    # number of epochs to train the model
    NUM_EPOCHS = 8
    # specify the number of folds for cross-validation
    NUM_SPLITS = 1
    # number of steps after which computes the validation loss and accuracy
    NUM_STEPS = 250
    # percentage of the dataset that will be used for test (the remaining percentage will be for training and validation)
    TEST_RATIO = 0.1
    # scheduler warmup steps
    WARMUP_STEPS = 0
