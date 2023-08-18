import os
import json
import sys

def extract_properties_from_json(json_file):
    with open(json_file, 'r') as file:
        data = json.load(file)
        extracted_data = []
        for obj in data:
            extracted_obj = {
                'oracle': obj.get('oracle'),
                'javadocTag': obj.get('javadocTag'),
                'packageName': obj.get('packageName'),
                'className': obj.get('className'),
                'methodSignature': obj.get('methodSourceCode').split('{')[0].strip(),
            }
            extracted_data.append(extracted_obj)
        return extracted_data

def process_folder(folder_path):
    extracted_data = []
    for filename in os.listdir(folder_path):
        if filename.endswith('.json'):
            file_path = os.path.join(folder_path, filename)
            extracted_data.extend(extract_properties_from_json(file_path))

    output_file = 'oracles.json'
    with open(output_file, 'w') as file:
        json.dump(extracted_data, file, indent=4)

    print(f"Unique JSON file created: {output_file}")

def main():
    if len(sys.argv) != 2:
        print("Usage: python script.py <folder_path>")
        return

    folder_path = sys.argv[1]
    if not os.path.isdir(folder_path):
        print("Invalid folder path.")
        return

    process_folder(folder_path)

if __name__ == '__main__':
    main()
