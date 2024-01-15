from enum import Enum
class TransformerType(str, Enum):
    """
    Helper enums class: it defines the types of classificator model (encoder or decoder)
    """
    ENCODER = "ENCODER"
    DECODER = "DECODER"