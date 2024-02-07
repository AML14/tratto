import json
import re
import sys

def process_json(json_data):
    counter = 0
    for key, values in json_data.items():
        for value in values:
            if value == key or values.count(value) > 1:
                print(value)
                counter += 1
    print(f"Found {counter} duplicated values")

# Load JSON data from first argument
json_data = json.load(open(sys.argv[1]))

# Process JSON data
process_json(json_data)
