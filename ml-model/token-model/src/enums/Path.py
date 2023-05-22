from enum import Enum
import os

# Considering the current position of the file (root-->src-->state)
ROOT_PATH = f"{os.path.join(os.path.dirname(os.path.abspath(__file__)), '..', '..')}"

class Path(str, Enum):
    """
    Helper enums class: it defines the paths of the projects relevant folders
    """
    ROOT_PATH = ROOT_PATH
    INPUT_DATASET = os.path.join(ROOT_PATH, "dataset")
    OUTPUT = os.path.join(ROOT_PATH, "output")