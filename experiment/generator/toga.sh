#!/bin/bash
# This script generates a list of non-axiomatic oracles using TOGA.
# It should save the output to "output/toga/oracle" as a list of OracleOutput records.

# Exit from the program if any error is arose from another bash script or another command executed within this bash script.
set -e
if [[ $(uname) == "Darwin" || $(uname) == "Linux" ]]; then
  export SEPARATOR="/"
else
  export SEPARATOR="\\"
fi

FULLY_QUALIFIED_NAME="${1}"
SRC_PATH="${2}"

# argument and setup check
if [ ! $# -eq 2 ]; then
  echo -e "toga.sh: Incorrect number of arguments. Expected 2 arguments, but got ${#}".
  exit 1
fi

# Set and check environment variables
SCRIPT_DIR="$(cd "$(dirname "${0}")" && pwd -P)"
# shellcheck source=generator/utils/env.sh
. "${SCRIPT_DIR}${SEPARATOR}utils${SEPARATOR}env.sh"

ROOT_DIR="$(dirname "${SCRIPT_DIR}")"
RESOURCES_DIR="${ROOT_DIR}${SEPARATOR}generator${SEPARATOR}resources"
TOGA_PROJECT_DIR="${ROOT_DIR}${SEPARATOR}generator${SEPARATOR}resources${SEPARATOR}toga"
TOGA_INPUT_DIR="${ROOT_DIR}${SEPARATOR}output${SEPARATOR}toga${SEPARATOR}input"
TOGA_OUTPUT_DIR="${ROOT_DIR}${SEPARATOR}output${SEPARATOR}toga${SEPARATOR}output"

echo "toga.sh: Setup TOGA project"
bash "${ROOT_DIR}${SEPARATOR}generator${SEPARATOR}utils${SEPARATOR}toga_setup.sh"

echo "toga.sh: Generate TOGA input files."
java -jar "${RESOURCES_DIR}${SEPARATOR}experiment.jar" "generate_tog_input" "toga" "${FULLY_QUALIFIED_NAME}" "${SRC_PATH}"

echo "toga.sh: Generate oracles with TOGA."
cd "${TOGA_PROJECT_DIR}"

python3 toga.py "${TOGA_INPUT_DIR}${SEPARATOR}toga_input.csv" "${TOGA_INPUT_DIR}${SEPARATOR}toga_metadata.csv"

cd "${ROOT_DIR}"

if [ ! -d "${TOGA_OUTPUT_DIR}" ]; then
    # If it doesn't exist, create the folder
    mkdir -p "${TOGA_OUTPUT_DIR}"
    echo "Folder created: ${TOGA_OUTPUT_DIR}"
fi

mv "${TOGA_PROJECT_DIR}${SEPARATOR}oracle_preds.csv" "${TOGA_OUTPUT_DIR}"

echo "toga.sh: Map oracles generated with TOGA into OracleOutputs."
java -jar "${RESOURCES_DIR}${SEPARATOR}experiment.jar" "generate_oracle_output" "toga" "${SRC_PATH}"
