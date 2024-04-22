#!/bin/bash
# This script perform the experiments for Defect4J projects

# Get current directory
current_dir=$(realpath "$(dirname "${BASH_SOURCE[@]}")")
# Setup global variables
source "${current_dir}/generator/utils/global_variables.sh"

# Perform checkout
checkout=false

# Check if any parameter is provided
if [ $# -gt 0 ]; then
    # Check if the first parameter is "checkout"
    if [ "$1" = "checkout" ]; then
      echo "Checkout parameter provided."
      checkout=true
    fi
fi

# Setup sdkman
source "${UTILS_DIR}/init_sdkman.sh"

# Clone defects4jprefix project
if [ "$checkout" = true ]; then
  if [ ! -d "$DEFECTS4J_DIR/defects4jprefix" ]; then
      git clone "$D4J_EVOSUITE_PREFIX_GITHUB_REPO" "$DEFECTS4J_DIR/defects4jprefix"
  fi
fi

# Read the CSV file line by line and split into project_id, bug_id, and modified_classes fields
while IFS=, read -r project_id bug_id modified_classes; do
    # Switch to Java 8
    sdk use java "$JAVA8"
    # Clean modified_classes string from undesired white-spaces/line-breaks introduced with the CSV parsing
    modified_classes="${modified_classes//[$'\t\r\n ']/}"
    # Map the modified_classes field from string to list
    IFS=, read -ra modified_classes_list <<< "${modified_classes}"
    # Define the path to the buggy version of the project_id-bug_id project
    buggy_project_bug_dir="${DEFECTS4J_DIR}/temp/${project_id}_${bug_id}b"
    # Perform checkout if required
    if [ "$checkout" = true ]; then
      # Checkout buggy version of the project_id-bug_id project
      echo "Checkout buggy version: project ${project_id}-${bug_id}b."
      defects4j checkout -p "${project_id}" -v "${bug_id}b" -w "$buggy_project_bug_dir"
      echo "Checkout complete."
      # Get the directory to the maven/ant configuration file of the project_id
      project_builder_dir="${BUILDERS_DIR}/${project_id}"
      # Get the path to the source directory of the ant project_id-bug_id project
      project_bug_source_path="${buggy_project_bug_dir}/source"
      echo "Compiling buggy version: project ${project_id}-${bug_id}b."
      defects4j compile -w "$buggy_project_bug_dir"
      echo "Compilation complete."
    fi
    # Get source and jars directories
    #src_path=$(defects4j export -p "dir.src.classes" -w "$buggy_project_bug_dir")
    jars_path="${DEFECTS4J_DIR}/defects4jprefix/src/main/project-jars/${project_id}/${bug_id}"
    # Move to the root directory
    cd "$ROOT_DIR"
    # Iterate over the modified classes and count the tests for each class
    for modified_class in "${modified_classes_list[@]}"; do
        fqn_path=$(echo "$modified_class" | sed 's/\./\//g')
        evosuite_prefix_fqn_file_path="${DEFECTS4J_DIR}/defects4jprefix/src/main/evosuite-prefixes/${project_id}/${bug_id}/${fqn_path}_ESTest.java"
        output_evosuite_prefix_path="${OUTPUT_DIR}/evosuite-prefixes"
        output_evosuite_prefix_fqn_path="${OUTPUT_DIR}/evosuite-prefixes/$(dirname "$fqn_path")"
        if [ ! -d "$output_evosuite_prefix_fqn_path" ]; then
          mkdir -p "$output_evosuite_prefix_fqn_path"
        fi
        cp "$evosuite_prefix_fqn_file_path" "$output_evosuite_prefix_fqn_path"
        # Switch to Java 17
        sdk use java "$JAVA17"
        # Setup experiments
        bash "${UTILS_DIR}/experiment_setup.sh"
        # Count EvoSuite test methods
        echo "Counting EvoSuite test methods for class ${modified_class} - ${project_id}_${bug_id}."
        # Run the count
        java -jar "${EXPERIMENT_JAR}" "count_test_methods" ${jars_path} ${project_id} ${bug_id} ${modified_class}
        # Cleanup
        rm -r "${output_evosuite_prefix_path}"
    done
done < "$D4J_PROJECTS_BUGS"

