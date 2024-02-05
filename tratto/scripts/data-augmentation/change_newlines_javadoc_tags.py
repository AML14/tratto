import re
import json
import sys

def transform_json(input_json):
    """
    Takes as input a JSON file with the following format:
    {
        "key 1 without newline characters": [
            "value 1 without newline characters",
            "value 2 without newline characters"
        ],
        "key2 with\nnewline characters": [
            "value 3 without newline characters",
            "value 4 without newline characters"
        ],
        "key3 with\n    newline characters and spaces": [
            "value 5 without newline characters",
            "value 6 without newline characters"
        ]
    }
    and modifies each value to make it contain newline characters followed by
    the same number of spaces as the number of spaces in the key. These characters
    are introduced more or less at the same position as the newline characters
    in the key.
    """

    output_json = {}

    for key, value in input_json.items():
        if '\n' not in key:
            output_json[key] = value
        else:
            nl_spaces = re.findall(r"\n\s*", key)[0]
            output_value = []
            for item in value:
                line_length = len(key.split('\n')[0])
                while item.find(' ', line_length) != -1:
                    first_space_index = item.find(' ', line_length)
                    item = item[:first_space_index] + nl_spaces + item[first_space_index + 1:]
                    line_length = first_space_index + len(nl_spaces) + line_length + 1
                output_value.append(item)
            output_json[key] = output_value

    return output_json

def main():
    if len(sys.argv) != 2:
        print("Please provide the input file as the first argument.")
        return

    input_file = sys.argv[1]
    output_file = input_file.replace(".json", "_transformed.json")

    try:
        with open(input_file, 'r') as file:
            input_json = json.load(file)
            output_json = transform_json(input_json)
            with open(output_file, 'w') as file:
                json.dump(output_json, file, indent=4)
    except FileNotFoundError:
        print("File not found:", input_file)
    except json.JSONDecodeError:
        print("Invalid JSON format in the input file.")

if __name__ == "__main__":
    main()
