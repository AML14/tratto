#!/bin/bash
# This script perform the experiments for Defect4J projects

# Get current directory
current_dir=$(realpath "$(dirname "${BASH_SOURCE[@]}")")
# Setup global variables
source "${current_dir}/generator/utils/global_variables.sh"

# Setup sdkman
source "${UTILS_DIR}/init_sdkman.sh"

# Arguments and setup check
if [ ! $# -eq 1 ]; then
  echo -e "defects4j.sh: Incorrect number of arguments. Expected 1 argument, but got ${#}".
  exit 1
fi

# Tog name
tog="${1}"

# Clone defects4jprefix project
if [ ! -d "$DEFECTS4J_DIR/defects4jprefix" ]; then
    git clone "$D4J_EVOSUITE_PREFIX_GITHUB_REPO" "$DEFECTS4J_DIR/defects4jprefix"
fi

# Read the CSV file line by line and split into project_id, bug_id, and modified_classes fields
while IFS=, read -r project_id bug_id modified_classes; do
    src_path=""
    binary_path=""
    test_path=""
    classpath=""
    sdk use java "$JAVA8"
    # Clean modified_classes string from undesired white-spaces/line-breaks introduced with the CSV parsing
    modified_classes="${modified_classes//[$'\t\r\n ']/}"
    # Map the modified_classes field from string to list
    IFS=, read -ra modified_classes_list <<< "${modified_classes}"
    # Define the path to the buggy version of the project_id-bug_id project
    buggy_project_bug_dir="${DEFECTS4J_DIR}/temp/${project_id}_${bug_id}b"
    # Define the path to the fixed version of the project_id-bug_id project
    fixed_project_bug_dir="${DEFECTS4J_DIR}/temp/${project_id}_${bug_id}f"
    if [ "$tog" != "baseline" ]; then
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
        classpath=$(defects4j export -p "cp.compile" -w "$buggy_project_bug_dir")
    fi

    cd "$ROOT_DIR"

    # Iterate over the modified classes and generate test cases for each class
    for modified_class in "${modified_classes_list[@]}"; do
        fqn_path=$(echo "$modified_class" | sed 's/\./\//g')
        evosuite_prefix_fqn_file_path="${DEFECTS4J_DIR}/defects4jprefix/src/main/evosuite-prefixes/${project_id}/${bug_id}/${fqn_path}_ESTest.java"
        evosuite_prefix_scaffolding_fqn_file_path="${DEFECTS4J_DIR}/defects4jprefix/src/main/evosuite-prefixes/${project_id}/${bug_id}/${fqn_path}_ESTest_scaffolding.java"
        evosuite_simple_test_fqn_file_path="${DEFECTS4J_DIR}/defects4jprefix/src/main/evosuite-simple-tests/${project_id}/${bug_id}/${fqn_path}_ESTest.java"
        evosuite_tests_fqn_file_path="${DEFECTS4J_DIR}/defects4jprefix/src/main/evosuite-tests/${project_id}/${bug_id}/${fqn_path}_ESTest.java"
        fat_jar_path="${DEFECTS4J_DIR}/defects4jprefix/src/main/project-jars/${project_id}/${bug_id}/${project_id}_${bug_id}b.jar"
        output_evosuite_prefix_path="${OUTPUT_DIR}/evosuite-prefixes"
        output_evosuite_prefix_fqn_path="${OUTPUT_DIR}/evosuite-prefixes/$(dirname "$fqn_path")"
        output_evosuite_simple_test_fqn_path="${OUTPUT_DIR}/evosuite-simple-tests/$(dirname "$fqn_path")"
        output_evosuite_tests_fqn_path="${OUTPUT_DIR}/evosuite-tests/$(dirname "$fqn_path")"
        defects4j_output="${DEFECTS4J_DIR}/output_${tog}"
        output_fqn_path="${defects4j_output}/${project_id}/${bug_id}/${fqn_path}"
        if [ ! -d "$output_evosuite_prefix_fqn_path" ]; then
            mkdir -p "$output_evosuite_prefix_fqn_path"
        fi
        if [ ! -d "$output_evosuite_simple_test_fqn_path" ]; then
            mkdir -p "$output_evosuite_simple_test_fqn_path"
        fi
        if [ ! -d "$output_evosuite_tests_fqn_path" ]; then
            mkdir -p "$output_evosuite_tests_fqn_path"
        fi
        if [ ! -d "$output_fqn_path" ]; then
            mkdir -p "$output_fqn_path"
        fi
        cp "$evosuite_prefix_fqn_file_path" "$output_evosuite_prefix_fqn_path"
        cp "$evosuite_prefix_scaffolding_fqn_file_path" "$output_evosuite_prefix_fqn_path"
        cp "$evosuite_simple_test_fqn_file_path" "$output_evosuite_simple_test_fqn_path"
        cp "$evosuite_tests_fqn_file_path" "$output_evosuite_tests_fqn_path"

        # Generate jdoctor oracles
        if [ "${tog}" == "jdoctor" ]; then
          bash experiment.sh jdoctor "$modified_class" "${buggy_project_bug_dir}/${src_path}" "${buggy_project_bug_dir}/${binary_path}" "${classpath}" "false" "${project_id}" ${bug_id}
        # Generate toga oracles
        elif [ "${tog}" == "toga" ]; then
          bash experiment.sh toga "$modified_class" "${buggy_project_bug_dir}/${src_path}" "${buggy_project_bug_dir}/${binary_path}" "${classpath}" "false" "${project_id}" ${bug_id}
        # Generate tratto oracles
        elif [ "${tog}" == "tratto" ]; then
          bash experiment.sh tratto "$modified_class" "${buggy_project_bug_dir}/${src_path}" "${buggy_project_bug_dir}/${binary_path}" "${fat_jar_path}" "false" "${project_id}" "${bug_id}"
        # Execute baseline
        elif [ "${tog}" == "baseline" ]; then
          bash experiment.sh baseline "$modified_class" "" "" "" "false" "${project_id}" ${bug_id}
        else
          echo -e "tog.sh: Invalid TOG name, ${tog}"
          exit 1
        fi
        if [ "$tog" != "baseline" ]; then
          cp -r "$OUTPUT_DIR/${tog}-oracles" "$output_fqn_path"
        fi
        if [ "$tog" == "toga" ]; then
          cp -r "$OUTPUT_DIR/${tog}-input" "$output_fqn_path"
          cp -r "$OUTPUT_DIR/${tog}-err" "$output_fqn_path"
        fi
        cp -r "$OUTPUT_DIR/${tog}-tests" "$output_fqn_path"
        cp -r "$OUTPUT_DIR/${tog}-test-suite" "$output_fqn_path"
        cp -r "$OUTPUT_DIR/${tog}_defects4joutput.json" "$output_fqn_path"
        # Cleanup
        rm -r "${OUTPUT_DIR}"
    done
done < "$D4J_PROJECTS_BUGS"

