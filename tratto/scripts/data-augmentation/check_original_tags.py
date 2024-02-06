import json

def check_strings_in_second_file(strings_file, second_file):
    # Load the array of strings from the first file
    with open(strings_file, 'r') as f:
        strings_data = json.load(f)

    # Load the data from the second file
    with open(second_file, 'r') as f:
        second_data = json.load(f)

    # Check each string in the keys of the second file
    counter = 0
    for string_to_check in strings_data:
        if string_to_check not in second_data.keys():
            print('"' + string_to_check.replace('\n', '\\n').replace('"', '\\"') + '"')
            counter += 1

    print(f'Number of strings not found: {counter}')
    print(len(second_data.keys()))

# Example usage
strings_file_path = '../../src/main/resources/data-augmentation/tags.json'  # Replace with your first input JSON file path
second_file_path = '../../src/main/resources/data-augmentation/output_dict.json'  # Replace with your second input JSON file path

check_strings_in_second_file(strings_file_path, second_file_path)
