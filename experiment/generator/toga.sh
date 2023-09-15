#!/bin/bash
# This script generates a list of non-axiomatic oracles using TOGA.
# Should output a list of OracleOutput.

# Exit from the program if any error is arose from another bash script or another command executed within this bash script.
set -e

# argument and setup check
if [ ! $# -eq 3 ]; then
  echo -e "(TOGA) Incorrect number of arguments. Expected 3 arguments, but got ${#}".
  exit 1
fi

if [[ $(uname) == "Darwin" || $(uname) == "Linux" ]]; then
    SEPARATOR="/"
else
    SEPARATOR="\\"
fi

ROOT_DIR=$(pwd)
FULLY_QUALIFIED_NAME="${1}"
SRC_PATH="${2}"
TOGA_PROJECT_DIR="${ROOT_DIR}${SEPARATOR}generator${SEPARATOR}resources${SEPARATOR}toga"
TOGA_INPUT_DIR="${ROOT_DIR}${SEPARATOR}output${SEPARATOR}toga${SEPARATOR}input"
TOGA_OUTPUT_DIR="${ROOT_DIR}${SEPARATOR}output${SEPARATOR}toga${SEPARATOR}output"

echo "(TOGA) Setup TOGA project"
bash ".${SEPARATOR}generator${SEPARATOR}utils${SEPARATOR}toga_setup.sh"

echo "(TOGA) Generate TOGA input files."
java -jar "generator${SEPARATOR}resources${SEPARATOR}experiment.jar" "generate_tog_input" "toga" "${FULLY_QUALIFIED_NAME}" "${SRC_PATH}"

echo "(TOGA) Generate oracles with TOGA."
cd "${TOGA_PROJECT_DIR}"

python3 toga.py "${TOGA_INPUT_DIR}${SEPARATOR}toga_input.csv" "${TOGA_INPUT_DIR}${SEPARATOR}toga_metadata.csv"

cd "${ROOT_DIR}"

if [ ! -d "${TOGA_OUTPUT_DIR}" ]; then
    # If it doesn't exist, create the folder
    mkdir -p "${TOGA_OUTPUT_DIR}"
    echo "Folder created: ${TOGA_OUTPUT_DIR}"
fi

mv "${TOGA_PROJECT_DIR}${SEPARATOR}oracle_preds.csv" "${TOGA_OUTPUT_DIR}"

echo "(TOGA) Map oracles generated with TOGA into OracleOutputs."
java -jar "generator${SEPARATOR}resources${SEPARATOR}experiment.jar" "generate_oracle_output" "toga" "${SRC_PATH}"
