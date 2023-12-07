import sys

fqn_classes = sys.argv[1]
prefix = sys.argv[2]

def remove_java_extension(input_string):
    input_list = input_string.split(" ")
    new_input_list = []
    for input in input_list:
        # Check if the string ends with ".java"
        if input.endswith(".java"):
            # Remove the ".java" extension
            new_input_list.append(input[: -len(".java")])
        else:
            # If the string does not end with ".java", return it unchanged
            new_input_list.append(input)
    return " ".join(new_input_list)

print(remove_java_extension(fqn_classes.replace(prefix,"")))