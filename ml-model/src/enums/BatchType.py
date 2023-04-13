from enum import Enum
class BatchType(str, Enum):
    """
    Helper enums class: it defines the types of sorting algorithm for the whole dataset.
    """
    HETEROGENEOUS = "HETEROGENEOUS"
    OMOGENEOUS = "OMOGENEOUS"
    RANDOM = "RANDOM"
    SORTED_BY_LENGTH = "SORTED_BY_LENGTH"