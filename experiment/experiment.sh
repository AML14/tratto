#!/bin/bash
# This script generates a test suite for a class using Evosuite.
# Then, for every supported TOG, it creates a variant of teh test suite that uses the TOG's oracle.
# An invocation follows the format,
#     "bash experiment.sh [target-class] [src-dir] [bin-dir] [jar]"
# This script adds the following sub-directories to the output directory, `experiment/output/`:
#     - "evosuite-tests": a test suite generated by EvoSuite
#     - "evosuite-simple-tests": EvoSuite tests split with one assertion per
#                                test
#     - "evosuite-prefixes": EvoSuite simple tests with all assertions removed
#     - "[tog]/input": preprocessed TOG input (if necessary)
#     - "[tog]/oracle": OracleOutputs generated by TOG
#     - "tog-tests/[tog]": a test suite made by inserting TOG oracles into the
#                          EvoSuite prefixes

# Exit from the program if any error is arose from another bash script or another command executed within this bash script.
set -e
if [[ $(uname) == "Darwin" || $(uname) == "Linux" ]]; then
    SEPARATOR="/"
else
    SEPARATOR="\\"
fi

# get arguments
TARGET_CLASS=${1}
SRC_DIR=${2}
BIN_DIR=${3}
PROJECT_JAR=${4}
QUALIFIERS="${TARGET_CLASS%.*}"

# get useful directories
ROOT_DIR="$(dirname "$(realpath "${0}")")"
OUTPUT_DIR="${ROOT_DIR}${SEPARATOR}output"
EVOSUITE_OUTPUT="${OUTPUT_DIR}${SEPARATOR}evosuite-tests${SEPARATOR}${QUALIFIERS//./${SEPARATOR}}"
RESOURCES_DIR="${ROOT_DIR}${SEPARATOR}generator${SEPARATOR}resources"
# get experiment jar
EXPERIMENT_JAR="${RESOURCES_DIR}${SEPARATOR}experiment.jar"
if [ ! -f "${EXPERIMENT_JAR}" ]; then
  mvn clean package -DskipTests
  TEMP_JAR=$(find "${ROOT_DIR}${SEPARATOR}target" -type f -name 'experiment*-jar-with-dependencies.jar')
  if [ -z "${TEMP_JAR}" ]; then
    echo "experiment.sh: Unexpected error: experiment jar not found."
    exit 1
  fi
  sudo mv "${TEMP_JAR}" "${RESOURCES_DIR}${SEPARATOR}experiment.jar"
fi

# check if given directories exist
if [ ! -d "${SRC_DIR}" ]; then
  echo -e "The project source directory \"${SRC_DIR}\" does not exist."
  exit 1
elif [ ! -d "${BIN_DIR}" ]; then
  echo -e "The system binaries path \"${BIN_DIR}\" does not exist."
  exit 1
fi

# define useful macros
TOGS=("jdoctor" "toga" "tratto")
EXPERIMENT="java -jar ${EXPERIMENT_JAR}"

echo "[1] Generating EvoSuite tests for class ${TARGET_CLASS}"
bash ./generator/evosuite.sh "${TARGET_CLASS}" "${BIN_DIR}"

echo "[2] Removing oracles from EvoSuite tests"
$EXPERIMENT "remove_oracles" "${TARGET_CLASS}"

echo "[3] Generating new test suite using TOGs"
for TOG in "${TOGS[@]}"; do
  # generate oracles
  if [ "${TOG}" == "jdoctor" ]; then
    bash ./generator/jdoctor.sh "${TARGET_CLASS}" "${SRC_DIR}" "${BIN_DIR}"
  elif [ "${TOG}" == "toga" ]; then
    bash ./generator/toga.sh "${TARGET_CLASS}" "${SRC_DIR}" "${EVOSUITE_OUTPUT}"
  elif [ "${TOG}" == "tratto" ]; then
#    bash ./generator/tratto.sh "${TARGET_CLASS}" "${SRC_DIR}" "${PROJECT_JAR}"
    exit 1
  fi
  # insert oracles into test prefixes
  $EXPERIMENT "insert_oracles" "${TOG}" "${PROJECT_JAR}"
done

echo "[4] Successfully created test suite in \"output/tog-tests\""
