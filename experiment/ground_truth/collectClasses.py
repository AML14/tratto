import os
import json
import sys
import shutil

def copy_file(source_path, destination_path):
    try:
        shutil.copy(source_path, destination_path)
        print(f"File copied successfully from {source_path} to {destination_path}")
    except IOError as e:
        print(f"Error copying file: {e}")

# Path to the ground truth data
path_to_project = sys.argv[1]
path_to_classes_file = os.path.join(path_to_project, "class_list.json")
path_to_dest_folder = os.path.join(path_to_project, "classes")

with open(path_to_classes_file) as f:
    class_list = json.load(f)
    for class_path in class_list:
        abs_path_to_class_file = os.path.join(os.path.dirname(__file__), class_path)
        copy_file(abs_path_to_class_file, path_to_dest_folder)