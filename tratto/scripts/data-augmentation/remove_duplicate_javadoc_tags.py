import json
import sys

def remove_duplicates(input_json):
    output_json = {}

    for key, value in input_json.items():
        unique_values = []
        for item in value:
            if item not in unique_values and item != key:
                unique_values.append(item)
        output_json[key] = unique_values

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
            output_json = remove_duplicates(input_json)
            with open(output_file, 'w') as file:
                json.dump(output_json, file, indent=4)
    except FileNotFoundError:
        print("File not found:", input_file)
    except json.JSONDecodeError:
        print("Invalid JSON format in the input file.")

if __name__ == "__main__":
    main()
