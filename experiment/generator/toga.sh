#!/bin/bash
# This script generates a list of non-axiomatic oracles using TOGA.
# Should output a list of OracleOutput.

# argument and setup check
if [ ! $# -eq 2 ]; then
  echo -e "(TOGA) Incorrect number of arguments. Expected 2 arguments, but got $#".
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
TOGA_PROJECT="${ROOT_DIR}${SEPARATOR}generator${SEPARATOR}resources${SEPARATOR}toga"
TOGA_INPUT="${ROOT_DIR}${SEPARATOR}output${SEPARATOR}toga${SEPARATOR}input"
TOGA_OUTPUT="${ROOT_DIR}${SEPARATOR}output${SEPARATOR}toga${SEPARATOR}output"



java -jar "generator${SEPARATOR}resources${SEPARATOR}experiment.jar" toga generate_oracle_outputs $SRC_PATH