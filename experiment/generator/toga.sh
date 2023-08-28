#!/bin/bash
# This script generates a list of non-axiomatic oracles using TOGA.
# Should output a list of OracleOutput.

# argument and setup check
if [ ! $# -eq 2 ]; then
  echo -e "(TOGA) Incorrect number of arguments. Expected 2 arguments, but got $#".
  exit 1
fi

FULLY_QUALIFIED_NAME="$1"

if [[ $(uname) == "Darwin" || $(uname) == "Linux" ]]; then
    SEPARATOR="/"
    TEST_CLASS_PATH="output${SEPARATOR}evosuite-tests${SEPARATOR}${FULLY_QUALIFIED_NAME//./${SEPARATOR}}_ESTest_Prefix_Assertion.java"
else
    SEPARATOR="\\"
fi

SRC_PATH="$2"
CLASS_PATH="${SRC_PATH}${SEPARATOR}${FULLY_QUALIFIED_NAME//./${SEPARATOR}}.java"

if [ -f "${CLASS_PATH}" ]; then
    echo "Class path exists"
else
    echo "Class path ${CLASS_PATH} not found. Trying common pattern with main/java"
    CLASS_PATH="${SRC_PATH}${SEPARATOR}main${SEPARATOR}java${SEPARATOR}${FULLY_QUALIFIED_NAME//./${SEPARATOR}}.java"
    if [ -f "${CLASS_PATH}" ]; then
      echo "Changed class path to: ${CLASS_PATH}"
    else
      echo "Class path ${CLASS_PATH} not found."
      echo "Please provide the path to full path to the class under test:"
      read CLASS_PATH
      if [ -f "${CLASS_PATH}" ]; then
        echo "Changed class path to: ${CLASS_PATH}"
      else
        echo "Class path ${CLASS_PATH} not found. Terminating program..."
        exit 1
      fi
    fi
fi

ROOT_DIR=$(pwd)
TOGA_PROJECT="${ROOT_DIR}${SEPARATOR}generator${SEPARATOR}resources${SEPARATOR}toga"
TOGA_INPUT="${ROOT_DIR}${SEPARATOR}output${SEPARATOR}toga${SEPARATOR}input"
TOGA_OUTPUT="${ROOT_DIR}${SEPARATOR}output${SEPARATOR}toga${SEPARATOR}output"
TEST_CLASS_PATH="${ROOT_DIR}${SEPARATOR}output${SEPARATOR}evosuite-tests${SEPARATOR}${FULLY_QUALIFIED_NAME//./${SEPARATOR}}_ESTestPrefix.java"

java -jar "generator${SEPARATOR}resources${SEPARATOR}experiment.jar" toga $SRC_PATH $CLASS_PATH $TEST_CLASS_PATH

cd "${TOGA_PROJECT}"

python3 toga.py "${TOGA_INPUT}${SEPARATOR}toga_input.csv" "${TOGA_INPUT}${SEPARATOR}toga_metadata.csv"
if [ ! -d "$TOGA_OUTPUT" ]; then
    # If it doesn't exist, create the folder
    mkdir -p "$TOGA_OUTPUT"
    echo "Folder created: $TOGA_OUTPUT"
fi

mv "${TOGA_PROJECT}${SEPARATOR}oracle_preds.csv" "${TOGA_OUTPUT}${SEPARATOR}oracle_preds.csv"