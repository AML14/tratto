import os
import json
import csv
import sys

def process(tog, root_folder, d4j_bugs_csv_path, tog_stats_path):
    tog_processed_classes_counter = 0
    tog_not_processed_classes_counter = 0
    tog_processed_methods_counter = 0
    tog_stats = {
        "tog": tog,
        "numBugsFound": 0,
        "numTruePositive": 0,
        "numFalsePositive": 0,
        "numFalseNegative": 0,
        "numTrueNegative": 0
    }
    with open(d4j_bugs_csv_path, 'r') as file:
        csv_reader = csv.reader(file)
        for idx, row in enumerate(csv_reader, 1):
            print(f'Processing row {idx}')
            project_name = row[0]
            bug_id = row[1]
            class_name = row[2]
            class_path = class_name.replace('.', '/')
            print(f'Processing: {project_name}, {bug_id}, {class_path}, {tog}')
            path_to_file = os.path.join(root_folder, project_name, bug_id, class_path, f"{tog}_defects4joutput.json")
            if os.path.exists(path_to_file):
                tog_processed_classes_counter += 1
                with open(path_to_file, 'r') as file:
                    json_data = json.load(file)
                    tog_stats['numTruePositive'] += json_data['numTruePositive']
                    tog_stats['numFalsePositive'] += json_data['numFalsePositive']
                    tog_stats['numFalseNegative'] += json_data['numFalseNegative']
                    tog_stats['numTrueNegative'] += json_data['numTrueNegative']
            else:
                print(f'File not found in {tog}: {path_to_file}')
                tog_not_processed_classes_counter += 1
    print(f'Processed bugged classes by {tog}: {tog_processed_classes_counter}/{tog_processed_classes_counter + tog_not_processed_classes_counter}')
    with open(tog_stats_path, 'w') as tog_stats_file:
        json.dump(tog_stats, tog_stats_file, indent=4)

if __name__ == '__main__':
    if len(sys.argv) < 2:
        print("Please provide the tog generator used")
        sys.exit(1)
    tog = sys.argv[1]
    # Replace 'root_folder_path' with the path to your root folder
    root_folder_path = os.path.join(os.path.dirname(__file__), f'output_{tog}')
    d4j_bugs_csv_path = os.path.join(os.path.dirname(__file__), 'modified_classes.csv')
    tog_stats_path = os.path.join(os.path.dirname(__file__), f'{tog}_stats.json')
    process(tog, root_folder_path, d4j_bugs_csv_path, tog_stats_path)