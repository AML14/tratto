#!/bin/bash
# This script perform the experiments for Defect4J projects

# Get current directory
current_dir=$(realpath "$(dirname "${BASH_SOURCE[@]}")")
# Setup global variables
source "${current_dir}/generator/utils/global_variables.sh"

# Setup sdkman
source "${UTILS_DIR}/init_sdkman.sh"

# Scope
scope="${1:-generate_oracle}"

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
    # Get source, binary, and test directories
    src_path=$(defects4j export -p "dir.src.classes" -w "$buggy_project_bug_dir")
    binary_path=$(defects4j export -p "dir.bin.classes" -w "$buggy_project_bug_dir")
    test_path=$(defects4j export -p "dir.src.tests" -w "$buggy_project_bug_dir")
    #classpath=$(defects4j export -p "cp.compile" -w "$buggy_project_bug_dir")
    classpath="/Users/davidemolinelli/Downloads/temptemp/tratto/experiment/generator/resources/defects4j/temp/Closure_1b/build/classes:/Users/davidemolinelli/Downloads/temptemp/tratto/experiment/generator/resources/defects4j/temp/Closure_1b/lib/args4j.jar:/Users/davidemolinelli/Downloads/temptemp/tratto/experiment/generator/resources/defects4j/temp/Closure_1b/lib/guava.jar:/Users/davidemolinelli/Downloads/temptemp/tratto/experiment/generator/resources/defects4j/temp/Closure_1b/lib/json.jar:/Users/davidemolinelli/Downloads/temptemp/tratto/experiment/generator/resources/defects4j/temp/Closure_1b/lib/jsr305.jar:/Users/davidemolinelli/Downloads/temptemp/tratto/experiment/generator/resources/defects4j/temp/Closure_1b/lib/protobuf-java.jar:/Users/davidemolinelli/Downloads/temptemp/tratto/experiment/generator/resources/defects4j/temp/Closure_1b/build/lib/rhino.jar:/Users/davidemolinelli/Downloads/temptemp/tratto/experiment/generator/resources/defects4j/temp/Closure_1b/lib/ant.jar:/Users/davidemolinelli/Downloads/temptemp/tratto/experiment/generator/resources/defects4j/temp/lib2:/Users/davidemolinelli/Downloads/temptemp/tratto/experiment/generator/resources/defects4j/temp/lib2/com/google/common/collect/Multimap.class"

    echo "$classpath"

    cd "$ROOT_DIR"

    # Iterate over the modified classes and generate test cases for each class
    for modified_class in "${modified_classes_list[@]}"; do
        fqn_path=$(echo "$modified_class" | sed 's/\./\//g')
        evosuite_prefix_path="${DEFECTS4J_DIR}/defects4jprefix/src/main/evosuite-prefixes/${project_id}/${bug_id}/${fqn_path}_ESTest.java"
        evosuite_simple_test_path="${DEFECTS4J_DIR}/defects4jprefix/src/main/evosuite-simple-tests/${project_id}/${bug_id}/${fqn_path}_ESTest.java"
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
          bash experiment.sh jdoctor "$modified_class" "${buggy_project_bug_dir}/${src_path}" "${buggy_project_bug_dir}/${binary_path}" "${classpath}"
          cp -r "$OUTPUT_DIR/jdoctor-oracles" "$fqn_output"
          # Generate toga oracles
          #bash experiment.sh toga "$modified_class" "${buggy_project_bug_dir}/${src_path}" "${buggy_project_bug_dir}/${binary_path}" "${buggy_project_bug_dir}/${project_id}.jar"
          #cp -r "$OUTPUT_DIR/toga-oracles" "$fqn_output"
          # Generate tratto oracles
          #bash experiment.sh tratto "$modified_class" "${buggy_project_bug_dir}/${src_path}" "${buggy_project_bug_dir}/${binary_path}" "${buggy_project_bug_dir}/${project_id}.jar" "false"
          #cp -r "$OUTPUT_DIR/tratto-oracles" "$fqn_output"
          #rm -rf "$OUTPUT_DIR"
        elif [ "${scope}" == "run_test" ]; then
          qualifiers=$(echo "${modified_class}" | sed 's/\./\//g')
          # Run jdoctor tests
          cp "${DEFECTS4J_DIR}/output/${project_id}/${bug_id}/${qualifiers}/jdoctor/tog-tests/"* "${OUTPUT_DIR}/jdoctor/tog-tests/"
          bash runner.sh \
            "jdoctor" \
            "${modified_class}" \
            "${buggy_project_bug_dir}/${src_path}" \
            "${buggy_project_bug_dir}/${binary_path}" \
            "${buggy_project_bug_dir}/${test_path}"
          # Run toga tests
          #cp "${DEFECTS4J_DIR}/output/${project_id}/${bug_id}/${qualifiers}/toga/tog-tests/"* "${OUTPUT_DIR}/toga/tog-tests/"
#          bash runner.sh \
#            "toga" \
#            "${modified_class}" \
#            "${buggy_project_bug_dir}/${src_path}" \
#            "${buggy_project_bug_dir}/${binary_path}" \
#            "${buggy_project_bug_dir}/${test_path}"
          # Run tratto tests
          #cp "${DEFECTS4J_DIR}/output/${project_id}/${bug_id}/${qualifiers}/tratto/tog-tests/"* "${OUTPUT_DIR}/tratto/tog-tests/"
#          bash runner.sh \
#            "tratto" \
#            "${modified_class}" \
#            "${buggy_project_bug_dir}/${src_path}" \
#            "${buggy_project_bug_dir}/${binary_path}" \
#            "${buggy_project_bug_dir}/${test_path}"
          # cleanup
#          rm -r "${OUTPUT_DIR}"
        fi
    done
done < "$D4J_PROJECTS_BUGS"

