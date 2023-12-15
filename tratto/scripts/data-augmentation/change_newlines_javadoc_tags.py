import re
import json
import sys

def transform_json(input_json):
    output_json = {}

    for key, value in input_json.items():
        if '\n' not in key:
            output_json[key] = value
        else:
            capture = re.findall(r"\n\s*", key)[0]
            output_value = [re.sub(r"\n", capture, item) for item in value]
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
