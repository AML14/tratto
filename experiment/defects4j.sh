#!/bin/bash
# This script perform the experiments for Defect4J projects

# Get current directory
current_dir=$(realpath "$(dirname "$BASH_SOURCE")")
# Setup global variables
source "${current_dir}/generator/utils/global_variables.sh"

# Setup sdkman
source "${UTILS_DIR}/init_sdkman.sh"

# Scope
scope="${1:-experiments}"

# Read the CSV file line by line and split into project_id, bug_id, and modified_classes fields
while IFS=, read -r project_id bug_id modified_classes; do
  sdk use java "$JAVA8"
  # Clean modified_classes string from undesired white-spaces/line-breaks introduced with the CSV parsing
  modified_classes="${modified_classes//[$'\t\r\n ']/}"
  # Map the modified_classes field from string to list
  IFS=, read -ra modified_classes_list <<< "${modified_classes}"
  # Define the path to the buggy version of the project_id-bug_id project
  buggy_project_bug_dir="${DEFECTS4J_DIR}/temp/${project_id}_${bug_id}b"
  # Define the path to the fixed version of the project_id-bug_id project
  fixed_project_bug_dir="${DEFECTS4J_DIR}/temp/${project_id}_${bug_id}f"
  # Checkout buggy version of the project_id-bug_id project
  echo "Checkout buggy version: project ${project_id}-${bug_id}b."
  defects4j checkout -p "${project_id}" -v "${bug_id}b" -w "$buggy_project_bug_dir"
  echo "Checkout complete."
  # Checkout fixed version of the project_id-bug_id project
  echo "Checkout fixed version: project ${project_id}-${bug_id}f."
  defects4j checkout -p "${project_id}" -v "${bug_id}f" -w "$fixed_project_bug_dir"
  echo "Checkout complete."
  # Get the directory to the maven/ant configuration file of the project_id
  project_builder_dir="${BUILDERS_DIR}/${project_id}"
  # Get the path to the source directory of the ant project_id-bug_id project
  project_bug_source_path="${buggy_project_bug_dir}/source"
  echo "Compiling buggy version: project ${project_id}-${bug_id}b."
  defects4j compile -w "$buggy_project_bug_dir"
  echo "Compilation complete."
  # Compile the fixed version of the ant project_id-bug_id project
  echo "Compiling fixed version: project ${project_id}-${bug_id}f."
  defects4j compile -w "$fixed_project_bug_dir"
  echo "Compilation complete."
  # Set path path to binary files
    if [ -d "$buggy_project_bug_dir/build/classes" ]; then
      binary_path="build/classes"
    elif [ -d "$buggy_project_bug_dir/build" ]; then
      binary_path="build"
    elif [ -d "${buggy_project_bug_dir}/target/classes" ]; then
      binary_path="target/classes"
    else
      echo "Binary path for project $project_id not found."
      exit 1
    fi
    if [ -d "$buggy_project_bug_dir/source" ]; then
      src_path="source"
    elif [ -d "$buggy_project_bug_dir/src/main/java" ]; then
      src_path="src/main/java"
    elif [ -d "$buggy_project_bug_dir/src/java" ]; then
      src_path="src/java"
    elif [ -d "${buggy_project_bug_dir}/src" ]; then
      src_path="src"
    elif [ -d "$buggy_project_bug_dir/src/main/java" ]; then
      src_path="gson/src/main/java"
    else
      echo "Binary path for project $project_id not found."
      exit 1
    fi
    # Generate JAR from binary path
    jar cf "${buggy_project_bug_dir}/${project_id}".jar -C "${buggy_project_bug_dir}/${binary_path}" .
    jar cf "${buggy_project_bug_dir}/$project_id".jar -C "${fixed_project_bug_dir}/${binary_path}" .

    # Iterate over the modified classes and generate test cases for each class
    for modified_class in "${modified_classes_list[@]}"; do
      if [ "$scope" == "experiments" ]; then
        #bash /Users/davidemolinelli/Documents/phd/repositories/tratto/experiment/generator/evosuite.sh "$modified_class" "${buggy_project_bug_dir}/${binary_path}"
        bash "${ROOT_DIR}/generator/evosuite.sh" "$modified_class" "${buggy_project_bug_dir}/${binary_path}"
        # Generate jdoctor oracles
        bash experiment.sh jdoctor "$modified_class" "${buggy_project_bug_dir}/${src_path}" "${buggy_project_bug_dir}/${binary_path}"
        # Generate toga oracles
        bash experiment.sh toga "$modified_class" "${buggy_project_bug_dir}/${src_path}" "${buggy_project_bug_dir}/${binary_path}"
        # Generate tratto oracles
        bash experiment.sh tratto "$modified_class" "${buggy_project_bug_dir}/${src_path}" "${buggy_project_bug_dir}/${binary_path}" "${buggy_project_bug_dir}/${project_id}.jar"
      elif [ "$scope" == "evosuite" ]; then
        bash "${GENERATOR_DIR}/evosuite.sh" "$modified_class" "${buggy_project_bug_dir}/${binary_path}"
      fi
    done
    # TODO: [INTEGRATION WITH RUNNER SCRIPT]
done < "$D4J_PROJECTS_BUGS"

