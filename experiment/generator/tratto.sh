#!/bin/bash
# This script generates a list of axiomatic oracles using Tratto.
# Should output a list of OracleOutput.

# Exit from the program if any error is arose from another bash script or another command executed within this bash script.
set -e

# argument and setup check
if [ ! $# -eq 3 ]; then
  echo -e "(TRATTO) Incorrect number of arguments. Expected 3 arguments, but got $#".
  exit 1
fi

if [[ $(uname) == "Darwin" || $(uname) == "Linux" ]]; then
    SEPARATOR="/"
else
    SEPARATOR="\\"
fi

ROOT_DIR=$(pwd)
FULLY_QUALIFIED_NAME="$1"
SRC_PATH="$2"
PROJECT_JAR_PATH="$3"
RESOURCES_DIR="${ROOT_DIR}${SEPARATOR}generator${SEPARATOR}resources"
UTILS_DIR="${ROOT_DIR}${SEPARATOR}generator${SEPARATOR}utils"
TRATTO_PROJECT_DIR="${ROOT_DIR}${SEPARATOR}..${SEPARATOR}tratto"
TRATTO_OUTPUT_DIR="${ROOT_DIR}${SEPARATOR}output${SEPARATOR}tratto${SEPARATOR}output"

bash "${UTILS_DIR}${SEPARATOR}tratto_setup.sh"

read -rp "In which port is running the server? (default: 5050): " SERVER_PORT
[ -z "$SERVER_PORT" ] && SERVER_PORT="5050"
cd "$TRATTO_PROJECT_DIR"
java -jar "${RESOURCES_DIR}${SEPARATOR}tratto.jar" "$FULLY_QUALIFIED_NAME" "$SRC_PATH" "$PROJECT_JAR_PATH" "$SERVER_PORT"
cd "$ROOT_DIR"

if [ ! -d "${TRATTO_OUTPUT_DIR}" ]; then
    # If it doesn't exist, create the folder
    mkdir -p "${TRATTO_OUTPUT_DIR}"
    echo "Folder created: ${TRATTO_OUTPUT_DIR}"
fi

mv "${TRATTO_PROJECT_DIR}${SEPARATOR}src${SEPARATOR}main${SEPARATOR}resources${SEPARATOR}oracle_datapoints.json" "${TRATTO_OUTPUT_DIR}"

java -jar "${RESOURCES_DIR}${SEPARATOR}experiment.jar" tratto generate_oracle_outputs "${TRATTO_OUTPUT_DIR}${SEPARATOR}oracle_datapoints.json"