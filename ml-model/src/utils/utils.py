import sys
import os
import csv
import json
import gc
import random
import numpy as np
from xml.dom import minidom
import xml.etree.ElementTree as ET
from xml.etree.ElementTree import Element
from typing import Type, Union

import torch.distributed as dist
import torch

from src.enums.DeviceType import DeviceType

sys.path.append(f"{os.path.join(os.path.dirname(os.path.abspath(__file__)), '..')}")
from src.enums.FileFormat import FileFormat


def check_path_exists(path, is_path_file=False):
    """
    The method checks if the given path exists, otherwise it creates it.
    The *is_path_file* determines if the given *path* is a path to a file
    or a directory. If the *path* is a path to a file, only the path to
    the parent directory is considered.

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




def check_cuda_device():
    device_ids = list(range(torch.cuda.device_count()))
    if len(device_ids) > 0:
        os.environ["CUDA_VISIBLE_DEVICES"] = ",".join(str(i) for i in device_ids)
    return device_ids

def import_dataset(file_path: str, file_format: FileFormat):
    """
    The method imports the dataset to use to train and validate the model.
    The methods accept the formats .csv, .json, and .xml.

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
            - A csv reader in the case of a .csv file
            - A dict in the case of a .json file
            - A str in the case of an .xml file
    """
    if file_format == FileFormat.CSV:
        input_file = open(file_path)
        input_obj = csv.reader(input_file, delimiter=',', quoting=csv.QUOTE_NONE)
        return input_file, input_obj
    elif file_format == FileFormat.JSON:
        with open(file_path) as input_file:
            input_obj = json.load(input_file)
            return input_file, input_obj
    elif file_format == FileFormat.XML:
        with open(file_path, 'r') as input_file:
            input_obj = input_file.read()
            return input_file, input_obj


def export_file(file_path: str, content: Union[str, dict, Type[Element]], file_format: FileFormat, mode: str = "w"):
    """
    The method exports a file

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
            - A csv reader in the case of a .csv file
            - A dict in the case of a .json file
            - A str in the case of an .xml file
    """
    check_path_exists(file_path, is_path_file=True)
    if file_format == FileFormat.CSV:
        assert type(content) == str, f"utils.export_file - unknown content type: {type(content)} instead of str"
        with open(file_path, mode) as csv_out_file:
            csv_out_file.write(content)
        return
    elif file_format == FileFormat.JSON:
        assert type(content) == dict or type(
            content) == list, f"utils.export_file - unknown content type: {type(content)} instead of dict"
        with open(file_path, mode) as json_out_file:
            json.dump(content, json_out_file, indent=4)
        return
    elif file_format in [FileFormat.XML, FileFormat.SUMOCFG]:
        assert type(
            content) == Element, f"utils.export_file - unknown content type: {type(content)} instead of xml.ElementTree.Element"
        xml_str = minidom.parseString(ET.tostring(content)).toprettyxml(indent="   ")
        with open(file_path, mode) as xml_out_file:
            xml_out_file.write(xml_str)
        return

def ddp_setup(rank, world_size):
    """
        The method sets up a distributed data parallel instances to exploit multiple gpus

        Parameters
        ----------
        rank: str
            A unique identifier of each process
        world_size: int
            The total number of processes
    """
    os.environ['MASTER_ADDR'] = 'localhost'
    os.environ['MASTER_PORT'] = '12357'
    torch.distributed.init_process_group(backend="nccl", rank=rank, world_size=world_size)

def is_dist_avail_and_initialized():
    if not dist.is_available():
        return False
    if not dist.is_initialized():
        return False
    return True

def get_rank():
    if not is_dist_avail_and_initialized():
        return 0
    return dist.get_rank()

def is_main_process():
    return get_rank() == 0

def cleanup():
    # Destroy data distributed parallel instances
    torch.distributed.destroy_process_group()

def release_memory():
    # Release memory on GPU
    torch.cuda.empty_cache() 
    gc.collect()

def load_config_file(file_path: str):
    with open(file_path, "r") as config_file:
        config_dict = json.load(config_file)
    return config_dict



#############


def connect_to_device(
        local_rank: int,
        no_cuda: bool = False
):
    """
    The method sets the device where to upload the model and perform the training and validation phases.
    """
    # Setup CUDA, GPU & distributed training
    if local_rank == -1 or no_cuda:
        device = torch.device("cuda" if torch.cuda.is_available() and not no_cuda else "cpu")
        n_gpu = torch.cuda.device_count()
    else:  # Initializes the distributed backend which will take care of sychronizing nodes/GPUs
        torch.cuda.set_device(local_rank)
        device = torch.device("cuda", local_rank)
        torch.distributed.init_process_group(backend='nccl')
        n_gpu = 1
    return device, n_gpu
