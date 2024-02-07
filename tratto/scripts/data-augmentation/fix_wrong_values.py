import json
import re
import sys

def process_json(json_data):
    new_json_data = {}
    for key, values in json_data.items():
        if key.startswith("@param") or key.startswith("@throws"):
            new_json_data[key] = []
            # Extract the first two words from the key
            key_words = re.findall(r'\S+', key)[:2]

            # Check if each value in the array starts with the same two words
            for value in values:
                value_words = re.findall(r'\S+', value)[:2]
                if key_words != value_words:
                    print(f"Previous value: {value}")
                    # Replace the first two words in the value with the first two words from the key:
                    value = re.sub(r'^\S+\s+\S+', ' '.join(key_words), value)
                    print(f"New value: {value}")
                new_json_data[key].append(value)
        else:
            new_json_data[key] = values
    json.dump(new_json_data, open(sys.argv[1], "w"), indent=4)

# Load JSON data from first argument
json_data = json.load(open(sys.argv[1]))

# Process JSON data
process_json(json_data)
