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

# Clone defects4jprefix project
if [ ! -d "$DEFECTS4J_DIR/defects4jprefix" ]; then
    git clone https://github.com/ezackr/defects4jprefix.git "$DEFECTS4J_DIR/defects4jprefix"
fi

# Read the CSV file line by line and split into project_id, bug_id, and modified_classes fields
while IFS=, read -r project_id bug_id modified_classes; do
    sdk use java "$JAVA17"
    # Clean modified_classes string from undesired white-spaces/line-breaks introduced with the CSV parsing
    modified_classes="${modified_classes//[$'\t\r\n ']/}"
    # Map the modified_classes field from string to list
    IFS=, read -ra modified_classes_list <<< "${modified_classes}"
    # Define the path to the buggy version of the project_id-bug_id project
    buggy_project_bug_dir="${DEFECTS4J_DIR}/temp/${project_id}_${bug_id}b"
    # Define the path to the fixed version of the project_id-bug_id project
    fixed_project_bug_dir="${DEFECTS4J_DIR}/temp/${project_id}_${bug_id}f"
    # Get the directory to the maven/ant configuration file of the project_id
    project_builder_dir="${BUILDERS_DIR}/${project_id}"
    # Get the path to the source directory of the ant project_id-bug_id project
    project_bug_source_path="${buggy_project_bug_dir}/source"
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
        jdoctor_results="${defects4j_output}/${project_id}/${bug_id}/${fqn_path}/jdoctor/"
        toga_results="${defects4j_output}/${project_id}/${bug_id}/${fqn_path}/toga/"
        output_jdoctor="${OUTPUT_DIR}/jdoctor"
        output_toga="${OUTPUT_DIR}/toga"
        if [ ! -d "$output_evosuite_prefix_path" ]; then
            mkdir -p "$output_evosuite_prefix_path"
        fi
        if [ ! -d "$output_evosuite_simple_test_path" ]; then
            mkdir -p "$output_evosuite_simple_test_path"
        fi
        if [ ! -d "$output_evosuite_tests_path" ]; then
            mkdir -p "$output_evosuite_tests_path"
        fi
        if [ ! -d "$output_jdoctor" ]; then
            mkdir -p "$output_jdoctor"
        fi
        if [ ! -d "$output_toga" ]; then
            mkdir -p "$output_toga"
        fi
        cp "$evosuite_prefix_path" "$output_evosuite_prefix_path"
        cp "$evosuite_simple_test_path" "$output_evosuite_simple_test_path"
        cp "$evosuite_tests_path" "$output_evosuite_tests_path"
        cp -r "$jdoctor_results" "$output_jdoctor"
        cp -r "$toga_results" "$output_toga"

        # switch to Java 17
        sdk use java "${JAVA17}"

        java -jar "${EXPERIMENT_JAR}" "insert_oracles" "toga" "${buggy_project_bug_dir}/${binary_path}" "${output_toga}/oracle/oracle_outputs.json"
        #java -jar "${EXPERIMENT_JAR}" "insert_oracles" "jdoctor" "${buggy_project_bug_dir}/${binary_path}" "${output_jdoctor}/oracle/oracle_outputs.json"
        cp -r "${output_jdoctor}/tog-tests" "$jdoctor_results"
        #cp -r "${output_toga}/tog-tests" "$toga_results"
    done
    # TODO: [INTEGRATION WITH RUNNER SCRIPT]
done < "$D4J_PROJECTS_BUGS"

