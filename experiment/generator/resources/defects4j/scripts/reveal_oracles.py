import re
import sys
import csv
import json

def extract_oracles(file_path, method_name):
    with open(file_path, 'r') as file:
        all_oracles = json.load(file)
        return list(filter(lambda o: method_name in o['methodSignature'], all_oracles))


if __name__ == '__main__':
    tog = sys.argv[1]
    base_path = sys.argv[2]
    tests_list_file_path = sys.argv[3]
    oracles = []

    with open(tests_list_file_path, mode='r') as file:
        # Create a CSV reader object
        csv_reader = csv.reader(file)

        # Iterate over the rows in the CSV file
        for row in csv_reader:
            # Assuming the CSV has three columns
            project_id = row[0]
            bug_id = row[1]
            fqn_class = row[2]
            bugs_found = row[3]
            method_name = row[4]
            fqn_class_path = fqn_class.replace('.', '/')

            oracles_file_name = f"{base_path}/{project_id}/{bug_id}/{fqn_class_path}/{tog}-oracles/{tog}_Oracle.json"

            oracles = extract_oracles(oracles_file_name, method_name)

    # Write the processed data to the output CSV file
    with open(f'{tog}_reveal_oracles.json', mode='w', newline='') as outfile:
        json.dump(oracles, outfile, indent=4)