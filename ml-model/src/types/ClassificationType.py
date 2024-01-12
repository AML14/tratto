from enum import Enum
class ClassificationType(str, Enum):
    """
    Helper enums class: it defines the types of classification model generate
    """
    LABEL_PREDICTION = "LABEL_PREDICTION"
    CATEGORY_PREDICTION = "CATEGORY_PREDICTION"