import re
import json
import sys

def transform_json(input_json):
    output_json = {}

    for key, value in input_json.items():
        if key.startswith("@return"):
            output_json[key] = value
        elif key.startswith("@throws"):
            output_json[key] = value
        elif key.startswith("@param"):
            argument_name = re.findall(r"\S+", key)[1]
            output_value = []
            for item in value:
                if item != "" and not item.startswith("@param " + argument_name):
                    output_value.append("@param " + argument_name + " " + item.replace("@param ", "", 1))
                else:
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
