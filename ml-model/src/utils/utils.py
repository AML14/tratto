import sys
import os
import csv
import json
import gc
from xml.dom import minidom
import xml.etree.ElementTree as ET
from xml.etree.ElementTree import Element
from typing import Type, Union
import torch.distributed as dist
import torch
from accelerate import accelerator

from src.types.DeviceType import DeviceType

def check_cuda_device():
    device_ids = list(range(torch.cuda.device_count()))
    if len(device_ids) > 0:
        os.environ["CUDA_VISIBLE_DEVICES"] = ",".join(str(i) for i in device_ids)
    return device_ids


def check_path_exists(path, is_path_file=False):
    """
    The method checks if the given path exists, otherwise it creates it. The {@code is_path_file} determines
    if the given {@code path} is a path to a file or a directory. If the {@code path} is a path to a file, only
    the path to the parent directory is considered.

    Parameters
    ----------
    path: str
        The path to the directory or the file to check

    Returns
    -------
    is_path_to_file: bool
        If the give path is a path to a file the value is True (False otherwise)
    """
    if is_path_file:
        path = path[:path.rindex(os.sep)]
    if not os.path.exists(path):
        os.makedirs(path)


def connect_to_device(device_type: Type[DeviceType] = DeviceType.GPU):
    """
    The method sets the device where to upload the model and perform the training and validation phases.
    If available, the GPU is used to improve the performance. Otherwise, the CPU is used.

    Parameters
    ----------
    device_type: DeviceType
        The preferred device type to use to train and validate the model

    Returns
    -------
    device: torch.Device
        the Pytorch device to use to train and validate the model
    """
    torch.cuda.empty_cache()
    if not device_type in [DeviceType.GPU, DeviceType.CPU, DeviceType.ACCELERATOR]:
        raise Exception(f"Unrecognized device type: {device_type}")
    if device_type == DeviceType.ACCELERATOR:
        print(f'        There are {torch.cuda.device_count()} GPU(s) available.')
        print(f'        Training distributed on all the GPUs available, with accelerator')
        device = accelerator.device
    elif device_type == DeviceType.GPU and torch.cuda.is_available():
        print(f'        There are {torch.cuda.device_count()} GPU(s) available.')
        # Set the gpu as device to perform the training
        device = torch.device("cuda:0")
        print(f'        Selected GPU: {torch.cuda.get_device_name(0)}')
    else:
        # Set the cpu as device to perform the training
        device = torch.device("cpu")
        print('        No GPU available, using the CPU instead.')
    return device


def export_stats(file_path: str, content: dict, mode: str = "w"):
    """
    The method exports the statistics of a model in a json format

    Parameters
    ----------
    file_path: str
        The path to the dataset file
    content:
        The content of the model

    Returns
    -------
    input_file: File
        The file opened
    input_obj:
        The content of the file:
            - A csv reader in the case of a .csv file
            - A dict in the case of a .json file
            - A str in the case of an .xml file
    """
    check_path_exists(file_path, is_path_file=True)
    with open(file_path, mode) as json_out_file:
        json.dump(content, json_out_file, indent=4)
    return


def import_json(file_path: str):
    """
    The method imports the dataset to use to train and validate the model.
    The methods accept only the format .json

    Parameters
    ----------
    file_path: str
        The path to the dataset file
    file_format: FileFormat
        The extension of the file

    Returns
    -------
    input_file: File
        The file opened
    input_obj:
        The content of the file:
    """
    with open(file_path) as input_file:
        input_obj = json.load(input_file)
        return input_file, input_obj


def release_memory():
    # Release memory on GPU
    torch.cuda.empty_cache() 
    gc.collect()

def resume_checkpoint(checkpoint_filename: str, device: str):
    return torch.load(checkpoint_filename, map_location=torch.device(device))