import re
import sys
import csv

def extract_test_case(file_path, test_case_name):
    with open(file_path, 'r') as file:
        content = file.read()

    # Define a regex pattern to capture the specified test case
    pattern = re.compile(r'public void ' + re.escape(test_case_name) + r'\(\) throws Throwable \{(?!@Test[\s\S]*?\})([\s\S]*?)\}(?:\n\}|[\s\S]*@Test)', re.DOTALL)

    # Extract the specified test case
    match = pattern.search(content)

    if match:
        return "".join(match.group(0).split("@Test")[0]).replace("}\n}", "}").strip()
        return None

def print_test_case(test_case):
    if test_case:
        print(f"Test Case:\n")
        print(test_case)
        print("\n" + "="*80 + "\n")
    else:
        print("Test case not found.")


if __name__ == '__main__':
    tog = sys.argv[1]
    base_path = sys.argv[2]
    tests_list_file_path = sys.argv[3]
    processed_data = []

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
            test_cases = row[4:]
            classname = fqn_class.split('.')[-1]
            package_path = '/'.join(fqn_class.split('.')[:-1])
            fqn_class_path = fqn_class.replace('.', '/')

            test_class_file_name = f"{base_path}/{project_id}/{bug_id}/{fqn_class_path}/{tog}-tests/{package_path}/{classname}_ESTest.java"

            for test_case_name in test_cases:
                # Extract and print the test case
                test_case = extract_test_case(test_class_file_name, test_case_name)
                processed_data.append([project_id, bug_id, fqn_class, test_case_name, test_case])

    # Write the processed data to the output CSV file
    with open(f'{tog}_reveal_test.csv', mode='w', newline='') as outfile:
        csv_writer = csv.writer(outfile)
        
        # Write each processed row to the output file
        for row in processed_data:
            csv_writer.writerow(row)