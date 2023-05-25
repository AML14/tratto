from enum import Enum
class ModelType(str, Enum):
    """
    Helper enums class: it defines the types of classification model generate
    """
    TOKEN = "TOKEN"
    TOKEN_CLASS = "TOKEN_CLASS"