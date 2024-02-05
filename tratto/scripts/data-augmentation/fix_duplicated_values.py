import json
import re
import sys

def process_json(json_data):
    new_json_data = {}
    for key, values in json_data.items():
        new_json_data[key] = []
        for value in values:
            if value != key and (values.count(value) == 1 or (values.count(value) > 1 and value not in new_json_data[key])):
                new_json_data[key].append(value)
    json.dump(new_json_data, open(sys.argv[1], "w"), indent=4)

# Load JSON data from first argument
json_data = json.load(open(sys.argv[1]))

# Process JSON data
process_json(json_data)
