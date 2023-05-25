from enum import Enum
from src.enums.HyperParameter import HyperParameter


class FileName(str, Enum):
    """
    Helper enums class: it defines the names of the relevant files
    in the project
    """
    LOSS_ACCURACY = f"loss_accuracy_{HyperParameter.BATCH_SIZE.value}_{HyperParameter.LR.value}_{HyperParameter.NUM_EPOCHS.value}"