#!/bin/bash
# This script generates a list of non-axiomatic oracles using TOGA.
# It should save the output to "output/toga/oracle" as a list of OracleOutput records.

# Get current directory
# shellcheck disable=SC2128
current_dir=$(realpath "$(dirname "${BASH_SOURCE}")")
# setup global variables
source "${current_dir}/utils/global_variables.sh"

# argument and setup check
if [ ! ${#} -eq 5 ]; then
  echo -e "toga.sh: Incorrect number of arguments. Expected 4 arguments, but got ${#}".
  exit 1
fi

# Define local variables
fully_qualified_name="${1}"
fqn_path=$(echo "$fully_qualified_name" | sed 's/\./\//g')
src_path="${2}"
project_jar_path="${3}"
output_dir=${4-${OUTPUT_DIR}}
project_id=${5}
bug_id=${6}
buggy_project_bug_dir="${DEFECTS4J_DIR}/temp/${project_id}_${bug_id}b"
#evosuite_tests_fqn_file_path="${DEFECTS4J_DIR}/defects4jprefix/src/main/evosuite-simple-tests/${project_id}/${bug_id}/${fqn_path}_ESTest.java"
toga_project_dir="${ROOT_DIR}/generator/resources/toga"
toga_input_dir="${output_dir}/toga-input"
toga_output_file="${output_dir}/toga-oracles/${fqn_path}_toga_output.csv"
toga_output_fqn_dir="$(dirname "${toga_output_file}")"

echo "toga.sh: Setup TOGA project"
bash "${ROOT_DIR}/generator/utils/toga_setup.sh"

# Setup sdkman
source "${UTILS_DIR}/init_sdkman.sh" "${SDKMAN_DIR}"
# Switch to Java 17
sdk use java "$JAVA17"

# Setup experiments
bash "${UTILS_DIR}/experiment_setup.sh"

echo "toga.sh: Generate TOGA input files."
java -jar "generator/resources/experiment.jar" "generate_tog_input" "toga" "${fully_qualified_name}" "${output_dir}" "${src_path}" ${project_jar_path}
# python3 "$TOGA_INPUT_GENERATOR" "$DEFECTS4J_HOME" "$project_id" "$bug_id" "$fully_qualified_name" "$buggy_project_bug_dir" "$evosuite_tests_fqn_file_path" "$toga_input_dir"

echo "toga.sh: Generate oracles with TOGA."
cd "${toga_project_dir}" || exit 1

python3 toga.py "${toga_input_dir}/toga_input.csv" "${toga_input_dir}/toga_metadata.csv"

cd "${ROOT_DIR}" || exit 1

if [ ! -d "${toga_output_fqn_dir}" ]; then
    # If it doesn't exist, create the folder
    mkdir -p "${toga_output_fqn_dir}"
    echo "Folder created: ${toga_output_fqn_dir}"
fi

mv "${toga_project_dir}/oracle_preds.csv" "${toga_output_file}"

echo "toga.sh: Map oracles generated with TOGA into OracleOutputs."
java -jar "${EXPERIMENT_JAR}" "generate_oracle_output" "toga" "${toga_output_file}" "${output_dir}"
