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
TOGA_PROJECT_DIR="${ROOT_DIR}/generator/resources/toga"
TOGA_INPUT_DIR="${ROOT_DIR}/output/toga/input"
TOGA_OUTPUT_DIR="${ROOT_DIR}/output/toga/output"

echo "toga.sh: Setup TOGA project"
bash "${ROOT_DIR}/generator/utils/toga_setup.sh"

echo "toga.sh: Generate TOGA input files."
java -jar "generator/resources/experiment.jar" "generate_tog_input" "toga" "${fully_qualified_name}" "${src_path}"

echo "toga.sh: Generate oracles with TOGA."
cd "${TOGA_PROJECT_DIR}" || exit 1

python3 toga.py "${TOGA_INPUT_DIR}/toga_input.csv" "${TOGA_INPUT_DIR}/toga_metadata.csv"

cd "${ROOT_DIR}" || exit 1

if [ ! -d "${TOGA_OUTPUT_DIR}" ]; then
    # If it doesn't exist, create the folder
    mkdir -p "${TOGA_OUTPUT_DIR}"
    echo "Folder created: ${TOGA_OUTPUT_DIR}"
fi

mv "${TOGA_PROJECT_DIR}/oracle_preds.csv" "${TOGA_OUTPUT_DIR}"

echo "toga.sh: Map oracles generated with TOGA into OracleOutputs."
java -jar "generator/resources/experiment.jar" "generate_oracle_output" "toga" "${src_path}"
