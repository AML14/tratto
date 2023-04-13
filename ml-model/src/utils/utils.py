import sys
import os
import csv
import json
import torch
from xml.dom import minidom
import xml.etree.ElementTree as ET
from xml.etree.ElementTree import Element
from typing import Type, Union

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
    if not device_type in [DeviceType.GPU, DeviceType.CPU]:
        raise Exception(f"Unrecognized device type: {device_type}")
    if device_type == DeviceType.GPU and torch.cuda.is_available():
        # Set the gpu as device to perform the training
        device = torch.device("cuda:0")
        print(f'There are {torch.cuda.device_count()} GPU(s) available.')
        print(f'We will use the GPU: torch.cuda.get_device_name(0)')
    else:
        # Set the cpu as device to perform the training
        device = torch.device("cpu")
        print('No GPU available, using the CPU instead.')
    return device


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