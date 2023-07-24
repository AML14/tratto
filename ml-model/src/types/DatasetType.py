from enum import Enum

class DatasetType(str, Enum):
    """
    Helper enums class: it efines the types of dataset to create (training and validation)
    """
    TRAINING = "TRAINING"
    VALIDATION = "VALIDATION"
    TEST = "TEST"