import os
import json
import sys


def find_files_with_bugs(tog, stats_type, directory):
    bug_files = []
    for root, _, files in os.walk(directory):
        for file in files:
            if file == f"{tog}_defects4joutput.json":
                file_path = os.path.join(root, file)
                try:
                    with open(file_path, 'r') as f:
                        data = json.load(f)
                        if data[stats_type]["numInvalid"] >= 1:
                            bug_files.append((os.path.abspath(file_path), data[stats_type]["numInvalid"]))
                except (json.JSONDecodeError, IOError) as e:
                    print(f"Error reading {file_path}: {e}")
    return bug_files

if __name__ == "__main__":
    tog = sys.argv[1]
    stats_type = sys.argv[2]
    directory = sys.argv[3]
    bug_files = find_files_with_bugs(tog, stats_type, directory)
    invalid = 0 
    for file, num_bugs in bug_files:
        file = file.replace(directory + "/", "")
        file_splitted = file.split("/")
        project_id = file_splitted[0]
        bug_id = file_splitted[1]
        fqn_class = ".".join(file_splitted[2:-1])
        invalid += num_bugs
        print(f"{project_id},{bug_id},{fqn_class} - Invalid found: {num_bugs}")
    print(f"Total: {len(bug_files)}")
    print(f"Total invalid: {invalid}")