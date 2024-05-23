import os
import json
import csv
import sys

def process_json_file(file_path):
    with open(file_path, 'r') as file:
        counter = 0
        json_data = json.load(file)
        counter += json_data['numTruePositive']
        counter += json_data['numFalsePositive']
        counter += json_data['numFalseNegative']
        counter += json_data['numTrueNegative']
    return counter

def search_and_process(tog, root_folder, d4j_bugs_csv_path, evosuite_methods_count_path, tog_methods_count_path):
    evosuite_processed_classes_counter = 0
    evosuite_not_processed_classes_counter = 0
    evosuite_processed_methods_counter = 0
    tog_processed_classes_counter = 0
    tog_not_processed_classes_counter = 0
    tog_processed_methods_counter = 0
    tog_not_processed_methods_counter = 0
    tog_class_processed = []
    tog_methods_not_processed = []
    sum_percentage = 0
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
                tog_class_methods_counter = process_json_file(path_to_file)
                with open(evosuite_methods_count_path, 'r') as evosuite_methods_file:
                    evosuite_methods_list = json.load(evosuite_methods_file)
                    flag = False
                    for evosuite_class in evosuite_methods_list:
                        if evosuite_class['projectName'] == project_name and evosuite_class['projectBugID'] == bug_id and evosuite_class['className'] == class_name:
                            tog_class = evosuite_class.copy()
                            tog_class[f'{tog}MethodCount'] = tog_class_methods_counter
                            tog_class[f'{tog}Difference'] = evosuite_class['methodCount'] - tog_class_methods_counter
                            tog_class[f'{tog}Percentage'] = (tog_class_methods_counter / evosuite_class['methodCount']) * 100 if evosuite_class['methodCount'] > 0 else 100
                            sum_percentage += tog_class[f'{tog}Percentage']
                            evosuite_processed_classes_counter += 1
                            evosuite_processed_methods_counter += evosuite_class['methodCount']
                            tog_processed_methods_counter += tog_class_methods_counter
                            tog_class_processed.append(tog_class)
                            flag = True
                            break
                    if not flag:
                        evosuite_not_processed_classes_counter += 1
                        print(f'Not found in Evosuite: {project_name}, {bug_id}, {class_name}')
            else:
                print(f'File not found in {tog}: {path_to_file}')
                tog_not_processed_classes_counter += 1
                with open(evosuite_methods_count_path, 'r') as evosuite_methods_file:
                    evosuite_methods_list = json.load(evosuite_methods_file)
                    flag = False
                    for evosuite_class in evosuite_methods_list:
                        if evosuite_class['projectName'] == project_name and evosuite_class['projectBugID'] == bug_id and evosuite_class['className'] == class_name:
                            evosuite_processed_classes_counter += 1
                            evosuite_processed_methods_counter += evosuite_class['methodCount']
                            tog_not_processed_methods_counter += evosuite_class['methodCount']
                            tog_methods_not_processed.append(evosuite_class)
                            flag = True
                            break
                    if not flag:
                        evosuite_not_processed_classes_counter += 1
                        print(f'Not found in Evosuite: {project_name}, {bug_id}, {class_name}')
    print(f'Processed bugged classes by {tog}: {tog_processed_classes_counter} ({tog_processed_methods_counter} methods).')
    print(f'Processed bugged classes by Evosuite: {evosuite_processed_classes_counter} ({evosuite_processed_methods_counter} methods).')
    print(f'Not processed bugged classes by {tog}: {tog_not_processed_classes_counter} ({tog_not_processed_methods_counter} methods).')
    print(f'Not processed bugged classes by Evosuite: {evosuite_not_processed_classes_counter}.')
    print(f'Average percentage of methods generated by {tog} in relation to the methods generated by Evosuite: {sum_percentage / tog_processed_classes_counter}%')
    with open(tog_methods_count_path, 'w') as tog_methods_processed_file:
        json.dump(tog_class_processed, tog_methods_processed_file, indent=4)
    with open(os.path.join(os.path.dirname(__file__), f'{tog}_not_processed_methods_count.json'), 'w') as tog_methods_not_processed_file:
        json.dump(tog_methods_not_processed, tog_methods_not_processed_file, indent=4)

if __name__ == '__main__':
    if len(sys.argv) < 4:
        print("Incorrect number of arguments. Please provide the tog generator used, the round of test suite, and the path to the test_methods_count.json file")
        sys.exit(1)
    tog = sys.argv[1]
    evosuite_round = sys.argv[2]
    evosuite_methods_count_path = sys.argv[3]
    root_folder_path = os.path.join(os.path.dirname(__file__), f'output_{tog}', evosuite_round)
    d4j_bugs_csv_path = os.path.join(os.path.dirname(__file__), 'modified_classes.csv')
    tog_methods_count_path = os.path.join(os.path.dirname(__file__), f'{tog}_methods_count.json')
    search_and_process(tog, root_folder_path, d4j_bugs_csv_path, evosuite_methods_count_path, tog_methods_count_path)