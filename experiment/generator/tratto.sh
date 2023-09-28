#!/bin/bash
# This script generates a list of axiomatic oracles using Tratto.
# The output is a list of OracleOutput.

# Arguments and setup check
if [ ! $# -eq 3 ]; then
  echo -e "(TRATTO) Incorrect number of arguments. Expected 3 arguments, but got $#".
  exit 1
fi

# Get current directory
current_dir=$(realpath "$(dirname "$BASH_SOURCE")")
source "${current_dir}/utils/global_variables.sh"


FULLY_QUALIFIED_NAME="$1"
SRC_PATH="$2"
PROJECT_JAR_PATH="$3"
RESOURCES_DIR="${ROOT_DIR}/generator/resources"
UTILS_DIR="${ROOT_DIR}/generator/utils"
TRATTO_PROJECT_DIR="${ROOT_DIR}/../tratto"
TRATTO_OUTPUT_DIR="${ROOT_DIR}/output/tratto/output"

# Setup sdkman
source "${UTILS_DIR}/init_sdkman.sh" "$SDKMAN_DIR"
# Switch to Java 17
sdk use java "$JAVA17"
# Setup tratto
bash "${UTILS_DIR}/tratto_setup.sh"
# Execute tratto to generate oracles
cd "$TRATTO_PROJECT_DIR"
java -jar "${RESOURCES_DIR}/tratto.jar" "$FULLY_QUALIFIED_NAME" "$SRC_PATH" "$PROJECT_JAR_PATH" "$SERVER_PORT"
cd "$ROOT_DIR"

if [ ! -d "${TRATTO_OUTPUT_DIR}" ]; then
    # If it doesn't exist, create the folder
    mkdir -p "${TRATTO_OUTPUT_DIR}"
    echo "Folder created: ${TRATTO_OUTPUT_DIR}"
fi

mv "${TRATTO_PROJECT_DIR}/src/main/resources/oracle_datapoints.json" "${TRATTO_OUTPUT_DIR}"

java -jar "${RESOURCES_DIR}/experiment.jar" tratto generate_oracle_outputs "$SRC_PATH"