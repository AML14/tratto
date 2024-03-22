#!/bin/bash
# This script generates a list of non-axiomatic oracles using TOGA.
# It should save the output to "output/toga/oracle" as a list of OracleOutput records.

# Get current directory
# shellcheck disable=SC2128
current_dir=$(realpath "$(dirname "${BASH_SOURCE}")")
# setup global variables
source "${current_dir}/utils/global_variables.sh"

# argument and setup check
if [ ! ${#} -eq 4 ]; then
  echo -e "toga.sh: Incorrect number of arguments. Expected 4 arguments, but got ${#}".
  exit 1
fi

# Define local variables
fully_qualified_name="${1}"
fqn_path=$(echo "$fully_qualified_name" | sed 's/\./\//g')
src_path="${2}"
project_id=${3}
bug_id=${4}
buggy_project_bug_dir="${DEFECTS4J_DIR}/temp/${project_id}_${bug_id}b"
evosuite_tests_fqn_file_path="${DEFECTS4J_DIR}/defects4jprefix/src/main/evosuite-simple-tests/${project_id}/${bug_id}/${fqn_path}_ESTest.java"
toga_project_dir="${ROOT_DIR}/generator/resources/toga"
toga_input_dir="${ROOT_DIR}/output/toga-input"
toga_output_dir="${ROOT_DIR}/output/toga-oracles"
toga_output_file="${toga_output_dir}/${fqn_path}_toga_output.csv"
toga_output_fqn_dir="$(dirname "${toga_output_file}")"

# Setup sdkman
source "${UTILS_DIR}/init_sdkman.sh" "${SDKMAN_DIR}"
# Switch to Java 17
sdk use java "$JAVA17"

# Setup experiments
bash "${UTILS_DIR}/experiment_setup.sh"

echo "toga.sh: Generate TOGA input files."
java -jar "generator/resources/experiment.jar" "generate_tog_input" "toga" "${fully_qualified_name}" "${src_path}"

if [ ! -d "${ROOT_DIR}/processed/${project_id}/${bug_id}" ]; then
  echo "toga.sh: Create directory for project ${ROOT_DIR}/processed/${project_id}/${bug_id}"
  mkdir -p "${ROOT_DIR}/processed/${project_id}/${bug_id}"
fi

mv "${ROOT_DIR}/output/"* "${ROOT_DIR}/processed/${project_id}/${bug_id}"

# python3 "$TOGA_INPUT_GENERATOR" "$DEFECTS4J_HOME" "$project_id" "$bug_id" "$fully_qualified_name" "$buggy_project_bug_dir" "$evosuite_tests_fqn_file_path" "$toga_input_dir"