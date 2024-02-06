import json
import re
import sys
import os

def import_json(file_path: str):
    with open(file_path) as input_file:
        input_obj = json.load(input_file)
        return input_obj

def check_path_exists(path, is_path_file=False):
    if is_path_file:
        path = path[:path.rindex(os.sep)]
    if not os.path.exists(path):
        os.makedirs(path)

def export_json(file_path: str, content: dict, mode: str = "w"):
    check_path_exists(file_path, is_path_file=True)
    with open(file_path, mode) as json_out_file:
        json.dump(content, json_out_file, indent=4)
    return

def add_parameter_to_javadoc_tag(javadoc_tag, param):
    # Split the value javadoc tag into words
    words = javadoc_tag.split()
    # Insert the param at the second position (index 1)
    words.insert(1, param)
    # Join the words back into a javadoctag
    new_javadoctag = ' '.join(words)
    # Return the new javadoc tag
    return new_javadoctag

def check_missing_tags(output_dict, original_list):
  missing_tags = []
  for tag in output_dict.keys():
    before_len = len(original_list)
    original_list = [item for item in original_list if item != tag]
    after_len = len(original_list)
    if after_len < (before_len - 1):
      raise Exception(f"Removed more than one occurence of tag: {tag}")
    if before_len == after_len:
      missing_tags.append(tag)
    #if before_len == after_len + 1:
    #    print(f"Found tag - remaining tags: {after_len}")
  return missing_tags, original_list

def process_json(json_data):
    counter = 0
    invalid = []
    clean_json_data = {}
    for key, values in json_data.items():
        clean_json_data[key] = []
        if key.startswith("@param") or key.startswith("@throws"):
            # Extract the first two words from the key
            match = re.match(r'@(param|throws)\s+(\w+)', key)
            # Check if the key match the pattern
            if match:
                # Extract the tag and the parameter from the original javadoc tag
                key_tag = match.group(1)
                key_param = match.group(2)

                # Analyze the alternatives
                for value in values:
                    if len(value) > 0:
                        # Extract the first two words from the current alternative javadoc tag
                        match = re.match(r'@(param|throws)\s+(\w+)', value)
                        # Check if the alternative match the pattern
                        if match:
                            # Extract the tag and the parameter from the alternative javadoc tag
                            value_tag = match.group(1)
                            value_param = match.group(2)
                        else:
                            raise Exception(f"Invalid value: {value}")
                        if not key_tag == value_tag:
                            # The alternative must have the same tag as the original javadoc tag
                            raise Exception(f"Inconsistent value tag: {value}")
                        if not key_param == value_param:
                            # The alternative must have the same parameter as the original javadoc tag
                            # Add the key and the alternative to the list of invalid combination of original and alternative javadoc tags
                            invalid.append([key, value])
                            # Add parameter to alternative javadoc tag
                            new_javadoctag = add_parameter_to_javadoc_tag(value, key_param)
                            # Add the new alternative to the list of alternatives
                            clean_json_data[key].append(new_javadoctag)
                            counter += 1
                        else:
                            # Add the alternative to the list of alternatives
                            clean_json_data[key].append(value)
                    else:
                        # Add the empty string to the alternatives
                        clean_json_data[key].append(value)
        else:
            # Add the alternatives to the list of alternatives
            clean_json_data[key] = values
    return counter, invalid, clean_json_data

# Load JSON data from first argument
json_data = json.load(open(sys.argv[1]))
clean_json_data = {}

# Process JSON data
process_json(json_data)


if __name__ == '__main__':
    output_path = os.path.join(os.path.dirname(os.path.abspath(__file__)))
    # Dictionary for the clean output dictionary
    clean_json_data = {}
    # Load the dictionary with all the alternatives, for each javadoc tag
    output_dict = import_json(sys.argv[1])
    # Load the original list of javadoc tags
    original_javadoctags = import_json(sys.argv[2])
    # Process the output dictionary
    counter, invalid, clean_json_data = process_json(output_dict)
    # Check the missing tags
    missing_tags, remaining_tags = check_missing_tags(clean_json_data, original_javadoctags)
    # Export results, for future analysis
    export_json(os.path.join(output_path, f"invalid_keys.json"), invalid)
    export_json(os.path.join(output_path, f"clean_output_dict.json"), clean_json_data)
    export_json(os.path.join(output_path, f"missing_tags.json"), missing_tags)
    export_json(os.path.join(output_path, f"remaining_tags.json"), remaining_tags)
    print(f"Found and corrected {counter} keys with invalid values")
    print(f"Missing tags: {len(missing_tags)}")
    print(f"Remaining tags: {len(remaining_tags)}")