import json
import csv
import re
import os

# Mapping for oracle types
oracle_type_mapping = {
    "EXCEPT_POST": "Exceptional postcondition",
    "NORMAL_POST": "Postcondition",
    "PRE": "Precondition"
}

# Get the path of the folder containing the JSON files
folder_path = 'non-aug-tokens-dataset'

# Constants
first_token = "assertTrue("
empty_token = "// No assertion possible"

n_samples = 0

# Open the CSV file in write mode
with open('non-aug-dataset.csv', 'w', newline='') as csv_file:
    writer = csv.writer(csv_file)
    writer.writerow(["src", "tgt", "project", "package", "class"])  # Write the header

    # Process each JSON file in the folder
    for filename in os.listdir(folder_path):
        if filename.endswith('.json'):
            print(f'Processing file {filename}')
            file_path = os.path.join(folder_path, filename)

            # Open the JSON file and load the data
            with open(file_path, 'r') as json_file:
                data = json.load(json_file)

            # Process each object in the JSON array
            for obj in data:
                oracle_type = oracle_type_mapping.get(obj["oracleType"], "")
                javadoc_tag = re.sub("\n *", " ", obj["javadocTag"])
                method_javadoc = "\n" + re.sub("\n +", "\n ", obj["methodJavadoc"].strip())
                if method_javadoc == "\n":
                    method_javadoc = ""
                method_source_code = obj["methodSourceCode"]
                oracle_so_far = obj["oracleSoFar"]
                token = obj["token"]
                eligible_tokens_info = obj["eligibleTokens"]
                eligible_tokens = [t["token"] for t in eligible_tokens_info]
                if oracle_so_far == "" and token == ";":
                    eligible_tokens = [first_token, empty_token]
                    token = empty_token
                else:
                    oracle_so_far = first_token + oracle_so_far
                    if oracle_so_far == first_token:
                        eligible_tokens.remove(";")
                    else:
                        eligible_tokens = [t.replace(";", ");") for t in eligible_tokens]
                        token = token.replace(";", ");")

                # Construct the additional information string
                # Check if eligible_tokens_info contains at least one token whose class is "MethodName" or "ClassField":
                additional_info = ""
                if any(t["tokenClass"] in ["MethodName", "ClassField"] for t in eligible_tokens_info):
                    additional_info = "\n\n// Additional context:\n"
                    for t in eligible_tokens_info:
                        if t["tokenClass"] in ["MethodName", "ClassField"]:
                            additional_info += re.sub("\n +", "\n ", t["tokenInfo"][2].strip()) + "\n"

                # Construct the "src" string
                src = f'// {oracle_type}: "{javadoc_tag}"\n// Next possible tokens: {eligible_tokens}\n// Assertion:\n{oracle_so_far}<FILL_ME>\n\n// Method under test:{method_javadoc}\n{method_source_code}{additional_info}'
                # src = re.sub("(\r\n|\n)", "\\\\n", src)

                project_name = obj["projectName"]
                package_name = obj["packageName"]
                class_name = obj["className"]

                # Write the row to the CSV file
                writer.writerow([src, token, project_name, package_name, class_name])
                n_samples += 1

                # If this was the first token of a positive oracle, need to add one more data point related to the oracle itself
                if oracle_so_far == first_token:
                    oracle_so_far = ""
                    eligible_tokens = [first_token, empty_token]
                    token = first_token
                    src = f'// {oracle_type}: "{javadoc_tag}"\n// Next possible tokens: {eligible_tokens}\n// Assertion:\n{oracle_so_far}<FILL_ME>\n\n// Method under test:{method_javadoc}\n{method_source_code}{additional_info}'
                    writer.writerow([src, token, project_name, package_name, class_name])
                    n_samples += 1

print('Done')
print(f'Number of samples: {n_samples}')