import sys
import re

if __name__ == "__main__":
    dependency_type = sys.argv[1]
    path_to_dependency_file = sys.argv[2]
    path_to_merge_file = sys.argv[3]
    if dependency_type == "maven":
        pattern=r"\[INFO\]\s*(\s*\|\s+)?[+\\]-\s([^\n]+):jar:([^\n]+):"
    elif dependency_type == "gradle":
        pattern=r"(\S*\|\s+)?[+\\]---\s([^\n]+):([A-Za-z0-9.\-]*)"
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
                dependencies += f"{dependency.replace('.','/').replace(':',',')},{version}\n"

        with open(path_to_output_file, 'w') as output_file:
            output_file.write(dependencies)