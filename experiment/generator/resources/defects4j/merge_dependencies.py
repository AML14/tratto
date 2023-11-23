import sys
import re
import os

if __name__ == "__main__":
    dependency_type = sys.argv[1]
    path_to_dependency_file = sys.argv[2]
    path_to_merge_file = sys.argv[3]
    if dependency_type == "mvn":
        pattern=r"\[INFO\]\s*(\s*\|\s+)?[+\\]-\s([^\n]+):jar:([^\n]+):"
    elif dependency_type == "gradle":
        pattern=r"(\S*\|\s+)?[+\\]---\s([^\n]+):([A-Za-z0-9.\-]*)"

    dependencies=""
    reader_content = []

    if os.path.exists(path_to_merge_file):
        with open(path_to_merge_file, 'r') as reader_file:
            for line in reader_file:
                reader_content.append(line)

    # Open the file in read mode
    with open(path_to_dependency_file, 'r') as input_file:
        # Iterate over each line in the file
        for line in input_file:
            line_content = line.strip()
            # Using re.match to check if the pattern matches the input string
            match = re.match(pattern, line_content)
            if match:
                dependency = match.group(2)
                version = match.group(3)
                if os.path.exists(path_to_merge_file):
                    dependency_str = f"{dependency.replace('.','/').replace(':',',')},{version}\n"
                    if dependency_str not in reader_content:
                        with open(path_to_merge_file, 'a') as writer_file:
                            writer_file.write(f"{dependency_str}")
                else:
                    with open(path_to_merge_file, 'w') as writer_file:
                        dependency_str = f"{dependency.replace('.','/').replace(':',',')},{version}\n"
                        writer_file.write(f"{dependency_str}")
