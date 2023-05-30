from enum import Enum

class FileFormat(str, Enum):
    """
    Helper enums class: it defines the accepted formats of the input dataset.
    """
    CSV = "csv"
    JSON = "json"
    XML = "xml"