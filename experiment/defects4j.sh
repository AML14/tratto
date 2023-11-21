#!/bin/bash
# This script perform the experiments for Defect4J projects

# Get current directory
current_dir=$(realpath "$(dirname "${BASH_SOURCE[@]}")")
# Setup global variables
source "${current_dir}/generator/utils/global_variables.sh"

# Setup sdkman
source "${UTILS_DIR}/init_sdkman.sh"

# Scope
scope="${1:-experiments}"

# Clone defects4jprefix project
if [ ! -d "$DEFECTS4J_DIR/defects4jprefix" ]; then
    git clone https://github.com/ezackr/defects4jprefix.git "$DEFECTS4J_DIR/defects4jprefix"
fi

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
    elif [ -d "$buggy_project_bug_dir/gson/src/main/java" ]; then
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
        fqn_path=$(echo "$modified_class" | sed 's/\./\//g')
        evosuite_prefix_path="${DEFECTS4J_DIR}/defects4jprefix/src/main/evosuite-prefixes/${project_id}/${bug_id}/${fqn_path}Test.java"
        evosuite_simple_test_path="${DEFECTS4J_DIR}/defects4jprefix/src/main/evosuite-simple-tests/${project_id}/${bug_id}/${fqn_path}Test.java"
        evosuite_tests_path="${DEFECTS4J_DIR}/defects4jprefix/src/main/evosuite-tests/${project_id}/${bug_id}/${fqn_path}_ESTest.java"
        output_evosuite_prefix_path="${OUTPUT_DIR}/evosuite-prefixes/$(dirname "$fqn_path")"
        output_evosuite_simple_test_path="${OUTPUT_DIR}/evosuite-simple-tests/$(dirname "$fqn_path")"
        output_evosuite_tests_path="${OUTPUT_DIR}/evosuite-tests/$(dirname "$fqn_path")"
        defects4j_output="${DEFECTS4J_DIR}/output"
        fqn_output="${defects4j_output}/${project_id}/${bug_id}/${fqn_path}"
        if [ ! -d "$output_evosuite_prefix_path" ]; then
            mkdir -p "$output_evosuite_prefix_path"
        fi
        if [ ! -d "$output_evosuite_simple_test_path" ]; then
            mkdir -p "$output_evosuite_simple_test_path"
        fi
        if [ ! -d "$output_evosuite_tests_path" ]; then
            mkdir -p "$output_evosuite_tests_path"
        fi
        if [ ! -d "$fqn_output" ]; then
            mkdir -p "$fqn_output"
        fi
        cp "$evosuite_prefix_path" "$output_evosuite_prefix_path"
        cp "$evosuite_simple_test_path" "$output_evosuite_simple_test_path"
        cp "$evosuite_tests_path" "$output_evosuite_tests_path"
        if [ "${scope}" == "generate_oracle" ]; then
          # Generate jdoctor oracles
          bash experiment.sh jdoctor "$modified_class" "${buggy_project_bug_dir}/${src_path}" "${buggy_project_bug_dir}/${binary_path}" "false"
          cp -r "$OUTPUT_DIR/jdoctor" "$fqn_output"
          # Generate toga oracles
          bash experiment.sh toga "$modified_class" "${buggy_project_bug_dir}/${src_path}" "${buggy_project_bug_dir}/${binary_path}" "false"
          cp -r "$OUTPUT_DIR/toga" "$fqn_output"
          # Generate tratto oracles
          bash experiment.sh tratto "$modified_class" "${buggy_project_bug_dir}/${src_path}" "${buggy_project_bug_dir}/${binary_path}" "false" "${buggy_project_bug_dir}/${project_id}.jar"
          cp -r "$OUTPUT_DIR/tratto" "$fqn_output"
          rm -rf "$OUTPUT_DIR"
        elif [ "${scope}" == "run_test" ]; then
          # Run jdoctor tests
          bash runner.sh \
            "jdoctor" \
            "${modified_class}" \
            "${buggy_project_bug_dir}/${src_path}" \
            "${buggy_project_bug_dir}/${binary_path}" \
            "${OUTPUT_DIR}/tog-tests/jdoctor"
          # Run toga tests

          # Run tratto tests

        fi

    done
    # TODO: [INTEGRATION WITH RUNNER SCRIPT]
done < "$D4J_PROJECTS_BUGS"

