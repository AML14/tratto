from enum import Enum

class DeviceType(str, Enum):
    """
    Helper enums class: it classifies the types of device where the model can be uploaded to perform the training
    and validation.
    """
    CPU = "cpu"
    GPU = "gpu"
    ACCELERATOR = "accelerator"