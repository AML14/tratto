import os
import json

all_data = []

base_dir = os.path.join(os.path.dirname(__file__), "gpt_output")

requests = [ f"request-{idx}" for idx in range(2212) ]

for root, dirs, files in os.walk(base_dir):
    for file in files:
        if file.endswith('.json'):
            file_path = os.path.join(root, file)
            with open(file_path, 'r') as f:
                data = json.load(f)
                all_data.extend(data)
                for response in data:
                    if response['custom_id'] in requests:
                        requests.remove(response['custom_id'])

with open(os.path.join(base_dir, "all_oracles.json"), 'w') as output_file:
    json.dump(all_data, output_file, indent=4)

print(requests)