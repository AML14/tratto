import json
import re
import sys

def process_json(json_data):
    counter = 0
    for key, values in json_data.items():
        if key.startswith("@param") or key.startswith("@throws"):
            # Extract the first two words from the key
            key_words = re.findall(r'\S+', key)[:2]

            # Check if each value in the array starts with the same two words
            for value in values:
                value_words = re.findall(r'\S+', value)[:2]
                if key_words != value_words:
                    print(key)
                    counter += 1
                    break
    print(f"Found {counter} keys with invalid values")

# Load JSON data from first argument
json_data = json.load(open(sys.argv[1]))

# Process JSON data
process_json(json_data)
