from enum import Enum
class TrattoModelType(str, Enum):
    """
    Helper enums class: it defines the types of classification model generate
    """
    TOKEN_VALUES = "TOKEN_VALUES"
    TOKEN_CLASSES = "TOKEN_CLASSES"
    ORACLES = "ORACLES"
    MULTITASK = "MULTITASK"