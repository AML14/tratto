#!/bin/bash
# This script generates a list of axiomatic oracles using Tratto.
# Should output a list of OracleOutput.

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
TRATTO_PROJECT="${ROOT_DIR}${SEPARATOR}..${SEPARATOR}tratto"
TRATTO_OUTPUT="${ROOT_DIR}${SEPARATOR}output${SEPARATOR}tratto${SEPARATOR}output"
HOST="127.0.0.1"
PORT=5000

cd ../tratto

java -jar "${ROOT_DIR}${SEPARATOR}generator${SEPARATOR}resources${SEPARATOR}tratto.jar" $FULLY_QUALIFIED_NAME $SRC_PATH $PROJECT_JAR_PATH

if [ ! -d "${TRATTO_OUTPUT}" ]; then
    # If it doesn't exist, create the folder
    mkdir -p "${TRATTO_OUTPUT}"
    echo "Folder created: ${TRATTO_OUTPUT}"
fi

mv "${TRATTO_PROJECT}${SEPARATOR}src${SEPARATOR}main${SEPARATOR}resources${SEPARATOR}oracle_datapoints.json" "${TRATTO_OUTPUT}"

cd ../experiment

java -jar "generator${SEPARATOR}resources${SEPARATOR}experiment.jar" tratto generate_oracle_outputs $SRC_PATH