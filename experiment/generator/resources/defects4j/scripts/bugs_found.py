import os
import json
import csv
import sys

def must_be_processed(tog_not_processed_data, project_name, bug_id, class_name):
    for data in tog_not_processed_data:
        if data['projectName'] == project_name and data['projectBugID'] == bug_id and data['className'] == class_name:
            return False
    return True

def process(tog, root_folder, d4j_bugs_csv_path, tog_stats_path, stats_type, tog_not_processed_paths=[]):
    tog_processed_classes_counter = 0
    tog_not_processed_classes_counter = 0
    tog_processed_methods_counter = 0
    discard_classes = []
    tog_stats = {
        "tog": tog,
        "numBugsFound": 0,
        "numTruePositive": 0,
        "numFalsePositive": 0,
        "numFalseNegative": 0,
        "numTrueNegative": 0,
        "numInvalid": 0
    }

    for tog_not_processed_path in tog_not_processed_paths:
        with open(tog_not_processed_path, 'r') as file:
            tog_not_processed_data = json.load(file)
            discard_classes.extend(tog_not_processed_data)

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
            if os.path.exists(path_to_file) and must_be_processed(discard_classes, project_name, bug_id, class_name):
                tog_processed_classes_counter += 1
                with open(path_to_file, 'r') as file:
                    json_data = json.load(file)
                    tog_stats['numBugsFound'] += json_data[stats_type]['numBugsFound']
                    tog_stats['numTruePositive'] += json_data[stats_type]['numTruePositive']
                    tog_stats['numFalsePositive'] += json_data[stats_type]['numFalsePositive']
                    tog_stats['numFalseNegative'] += json_data[stats_type]['numFalseNegative']
                    tog_stats['numTrueNegative'] += json_data[stats_type]['numTrueNegative']
                    tog_stats['numInvalid'] += json_data[stats_type]['numInvalid']
            else:
                print(f'File not found in {tog}: {path_to_file}')
                tog_not_processed_classes_counter += 1
    print(f'Processed bugged classes by {tog}: {tog_processed_classes_counter}/{tog_processed_classes_counter + tog_not_processed_classes_counter}')
    with open(tog_stats_path, 'w') as tog_stats_file:
        json.dump(tog_stats, tog_stats_file, indent=4)

if __name__ == '__main__':
    if len(sys.argv) < 3:
        print("Incorrect number of arguments. Please provide the tog generator used, and the round of test suite")
        sys.exit(1)
    tog = sys.argv[1]
    evosuite_round = sys.argv[2]
    stats_type = sys.argv[3]
    not_processed_flag = True if len(sys.argv) > 4 and sys.argv[4] == 'not_processed' else False
    intersection_flag = True if len(sys.argv) > 4 and sys.argv[4] == 'intersection' else False
    root_folder_path = os.path.join(os.path.dirname(__file__), f'output', f"{tog}", evosuite_round)
    d4j_bugs_csv_path = os.path.join(os.path.dirname(__file__), 'modified_classes.csv')
    tog_stats_path = os.path.join(os.path.dirname(__file__), f'{tog}_stats.json')

    if tog == "baseline" and not_processed_flag:
        other_tog = sys.argv[3]
        tog_not_processed_path = [os.path.join(os.path.dirname(__file__), f'{other_tog}_not_processed_methods_count.json')]
    elif intersection_flag:
        files = os.listdir(os.path.dirname(__file__))
        matching_files = [filename for filename in files if filename.endswith("_not_processed_methods_count.json")]
        tog_not_processed_path = [os.path.join(os.path.dirname(__file__), filename) for filename in matching_files]
    else:
        tog_not_processed_path = []
    process(tog, root_folder_path, d4j_bugs_csv_path, tog_stats_path, stats_type, tog_not_processed_path)