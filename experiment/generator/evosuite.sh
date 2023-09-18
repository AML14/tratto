#!/bin/bash
# This script generates a test suite using EvoSuite and saves the output to
# "output/evosuite-tests".

# Exit from the program if any error is arose from another bash script or another command executed within this bash script.
set -e

TARGET_CLASS="${1}"  # fully-qualified name of target class
TARGET_DIR="${2}"  # directory of binary files of the system under test

if [ ! $# -eq 2 ]; then
  echo -e "evosuite.sh: Incorrect number of arguments. Expected 2 arguments, but got ${#}".
  exit 1
elif [ ! -f "${TARGET_CLASS}" ]; then
  echo -e "evosuite.sh: Class file does not exist: ${TARGET_CLASS}"
  exit 1
elif [ ! -d "${TARGET_DIR}" ]; then
  echo -e "evosuite.sh: The system binaries path does not exist: \"${2}\" "
  exit 1
fi

# Set and check environment variables
SCRIPTDIR="$(cd "$(dirname "$0")" && pwd -P)"
. "${SCRIPTDIR}${SEPARATOR}utils${SEPARATOR}env.sh"

# ROOT_DIR is "experiment/".
ROOT_DIR="$(dirname "${SCRIPTDIR}")"
RESOURCES_DIR="${ROOT_DIR}${SEPARATOR}generator${SEPARATOR}resources"
OUTPUT_DIR="${ROOT_DIR}${SEPARATOR}output"
EVOSUITE="${JAVA8_BIN} -jar ${RESOURCES_DIR}${SEPARATOR}evosuite-1.0.6.jar"

# generate tests using EvoSuite
(
  export JAVA_HOME=${JAVA8_BIN};
  $EVOSUITE -class "${TARGET_CLASS}" -projectCP "${TARGET_DIR}" -seed 13042023
)
CURRENT_DIR=$(realpath .)
rm -r "${CURRENT_DIR}${SEPARATOR}evosuite-report"  # delete statistics
# overwrites previous output if necessary
if [ -d "${OUTPUT_DIR}${SEPARATOR}evosuite-tests" ]; then
  rm -r "${OUTPUT_DIR}${SEPARATOR}evosuite-tests"
fi
# moves evosuite tests to "output/evosuite-tests"
mkdir -p "${OUTPUT_DIR}${SEPARATOR}evosuite-tests"
mv "${CURRENT_DIR}${SEPARATOR}evosuite-tests" "${OUTPUT_DIR}"
