import json
import os
import sys
sys.path.append(os.path.join(os.path.dirname(os.path.abspath(__file__)), "..", ".."))
from src.utils import utils

input_file_path = os.path.join(os.path.dirname(os.path.abspath(__file__)), "resources", "original_javadoctags.json")
output_file_path = os.path.join(os.path.dirname(os.path.abspath(__file__)), "resources", "original_javadoctags_set.json")
batch_size = 15

if __name__ == '__main__':
    _, javadoctags_list = utils.import_json(input_file_path)
    javadoctags_list_batches = [javadoctags_list[i:i + batch_size] for i in range(0, len(javadoctags_list), batch_size)]
    with open(output_file_path, "w") as f:
        json.dump(javadoctags_list_batches, f, indent=4)


