#!/bin/bash
# This script generates a test suite using EvoSuite and saves the output to
# experiment/output/evosuite-test.

# ----- SETUP -----
# After adding the local JDK8 to the generator/resources directory...
# Set this field to the directory name.

# Exit from the program if any error is arose from another bash script or another command executed within this bash script.
set -e

JDK8_NAME="jdk-1.8.jdk"

if [[ $(uname) == "Darwin" || $(uname) == "Linux" ]]; then
    SEPARATOR="/"
else
    SEPARATOR="\\"
fi

# find JDK8 directory
ROOT_DIR=$(dirname "$(dirname "$(realpath "$0")")")
RESOURCES_DIR="${ROOT_DIR}${SEPARATOR}generator${SEPARATOR}resources"
JDK_DEFAULT_PATH=$(find "$RESOURCES_DIR" -type d -name 'jdk-*' -print -quit)
JAVA8_BIN=""
if [ -n "$JDK_DEFAULT_PATH" ]; then
  JDK_PATH=$(dirname "$JDK_DEFAULT_PATH")"${SEPARATOR}${JDK8_NAME}"
  if [ "$JDK_DEFAULT_PATH" != "$JDK_PATH" ]; then
    mv "$JDK_DEFAULT_PATH" "$JDK_PATH"
  fi
  if [ $(uname) == "Linux" ]; then
      JAVA8_HOME="${JDK_PATH}"
  else
      JAVA8_HOME="${JDK_PATH}${SEPARATOR}Contents${SEPARATOR}Home"
  fi
  JAVA8_BIN="${JAVA8_HOME}${SEPARATOR}bin${SEPARATOR}java"
fi

if [ ! -e "$JAVA8_BIN" ] || [ -z "$JAVA8_BIN" ]; then
  echo "(EVOSUITE) Unable to find a jdk directory. Please provide the complete path to the Java 8 JDK binary ([path_to_jdk]${SEPARATOR}Contents${SEPARATOR}Home${SEPARATOR}bin${SEPARATOR}java or [path_to_jdk]${SEPARATOR}bin${SEPARATOR}java):"
  read -r USER_INPUT
  JAVA8_BIN="${USER_INPUT}"
fi

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
