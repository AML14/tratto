#!/bin/bash
# This script generates a test suite using EvoSuite and saves the output to
# experiment/output/evosuite-test.

# ----- SETUP -----
# After adding the local JDK8 to the generator/resources directory...
# Set this field to the directory name.
JDK8_NAME="jdk-1.8.jdk"

# Exit from the program if any error is arose from another bash script or another command executed within this bash script.
set -e

if [[ $(uname) == "Darwin" || $(uname) == "Linux" ]]; then
    SEPARATOR="/"
else
    SEPARATOR="\\"
fi

# find JDK8 directory
ROOT_DIR=$(dirname "$(dirname "$(realpath "$0")")")
RESOURCES_DIR="${ROOT_DIR}${SEPARATOR}generator${SEPARATOR}resources"
JDK_DEFAULT_PATH=$(find "$RESOURCES_DIR" -type d -name 'jdk-*' -print -quit)
JAVA8_BIN=$(bash "${ROOT_DIR}${SEPARATOR}generator${SEPARATOR}utils${SEPARATOR}java_version.sh" "$JDK8_NAME" "EVOSUITE")

# argument and setup check
if [ ! $# -eq 2 ]; then
  echo -e "(EVOSUITE) Incorrect number of arguments. Expected 2 arguments, but got $#".
  exit 1
elif [ ! -d "$2" ]; then
  echo -e "(EVOSUITE) The system binaries path \"$2\" does not exist."
  exit 1
elif [ ! -f "$JAVA8_BIN" ]; then
  echo -e "(EVOSUITE) Error: JDK8 java binary \"$JAVA8_BIN\" does not exist."
  exit 1
fi

# setup variables
TARGET_CLASS="$1"  # fully-qualified name of target class
TARGET_DIR="$2"  # directory of binary files of the system under test
OUTPUT_DIR="${ROOT_DIR}${SEPARATOR}output"
EVOSUITE="${JAVA8_BIN} -jar ${RESOURCES_DIR}${SEPARATOR}evosuite-1.0.6.jar"

# generate tests using EvoSuite
(export JAVA_HOME=$JAVA8_BIN; $EVOSUITE -class "$TARGET_CLASS" -projectCP "$TARGET_DIR")
# move to output directory
CURRENT_DIR=$(realpath .)

rm -r "${CURRENT_DIR}${SEPARATOR}evosuite-report"  # delete statistics

if [ -d "${OUTPUT_DIR}${SEPARATOR}evosuite-tests" ]; then
  rm -r "${OUTPUT_DIR}${SEPARATOR}evosuite-tests"  # overwrites previous output
fi

mkdir -p "$OUTPUT_DIR${SEPARATOR}evosuite-tests"
mv -f "${CURRENT_DIR}${SEPARATOR}evosuite-tests" "${OUTPUT_DIR}"
