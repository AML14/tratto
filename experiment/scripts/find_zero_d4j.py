import os
import json
import sys

def check_all_zeroes(data, stats_type):
    keys = ["numBugsFound", "numTruePositive", "numFalsePositive", "numTrueNegative", "numFalseNegative", "numInvalid"]
    return all(data[stats_type].get(key, None) == 0 for key in keys)

def find_files_with_all_zeroes(tog, stats_type, directory):
    zero_files = []
    for root, _, files in os.walk(directory):
        for file in files:
            if file == f"{tog}_defects4joutput.json":
                file_path = os.path.join(root, file)
                try:
                    with open(file_path, 'r') as f:
                        data = json.load(f)
                        if check_all_zeroes(data, stats_type):
                            zero_files.append(os.path.abspath(file_path))
                except (json.JSONDecodeError, IOError) as e:
                    print(f"Error reading {file_path}: {e}")
    return zero_files

if __name__ == "__main__":
    tog = sys.argv[1]
    stats_type = sys.argv[2]
    directory = sys.argv[3]
    zero_files = find_files_with_all_zeroes(tog, stats_type, directory)
    for file in zero_files:
        file = file.replace(directory + "/", "")
        file_splitted = file.split("/")
        project_id = file_splitted[0]
        bug_id = file_splitted[1]
        fqn_class = ".".join(file_splitted[2:-1])
        print(f"{project_id},{bug_id},{fqn_class}")
    print(f"Total: {len(zero_files)}")