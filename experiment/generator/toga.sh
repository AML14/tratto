#!/bin/bash
# This script generates a list of non-axiomatic oracles using TOGA.
# It should save the output to "output/toga/oracle" as a list of OracleOutput records.

# Get current directory
# shellcheck disable=SC2128
current_dir=$(realpath "$(dirname "${BASH_SOURCE}")")
source "${current_dir}/utils/global_variables.sh"

# argument and setup check
if [ ! ${#} -eq 2 ]; then
  echo -e "toga.sh: Incorrect number of arguments. Expected 2 arguments, but got ${#}".
  exit 1
fi

# Define local variables
fully_qualified_name="${1}"
src_path="${2}"
toga_project_dir="${ROOT_DIR}/generator/resources/toga"
toga_input_dir="${ROOT_DIR}/output/toga/input"
toga_output_dir="${ROOT_DIR}/output/toga/output"

echo "toga.sh: Setup TOGA project"
bash "${ROOT_DIR}/generator/utils/toga_setup.sh"

echo "toga.sh: Generate TOGA input files."
java -jar "generator/resources/experiment.jar" "generate_tog_input" "toga" "${fully_qualified_name}" "${src_path}"

echo "toga.sh: Generate oracles with TOGA."
cd "${toga_project_dir}" || exit 1

python3 toga.py "${toga_input_dir}/toga_input.csv" "${toga_input_dir}/toga_metadata.csv"

cd "${ROOT_DIR}" || exit 1

if [ ! -d "${toga_output_dir}" ]; then
    # If it doesn't exist, create the folder
    mkdir -p "${toga_output_dir}"
    echo "Folder created: ${toga_output_dir}"
fi

mv "${toga_project_dir}/oracle_preds.csv" "${toga_output_dir}"

echo "toga.sh: Map oracles generated with TOGA into OracleOutputs."
java -jar "${EXPERIMENT_JAR}" "generate_oracle_output" "toga" "${src_path}"
