#!/bin/bash
# This script generates a test suite using EvoSuite and saves the output to
# experiment/output/evosuite-test.

# Argument and setup check
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

# Get current directory
CURRENT_DIR=$(realpath "$(dirname "$BASH_SOURCE")")

# Setup global variables
if ! [ -v ROOT_DIR ]; then
    source "${CURRENT_DIR}/utils/global_variables.sh"
else
    echo "Global variables already defined."
fi

# Setup local variables
TARGET_CLASS="$1"  # Fully-qualified name of target class
TARGET_DIR="$2"    # Directory of binary files of the system under test

# Setup sdkman
source "${UTILS_DIR}/sdkman_init.sh" "$SDKMAN_DIR"

# Generate tests using EvoSuite
java -jar "$EVOSUITE_JAR" -class "$TARGET_CLASS" -projectCP "$TARGET_DIR"

# Move to output directory
CURRENT_DIR=$(realpath .)
# Delete statistics
rm -r "${CURRENT_DIR}/evosuite-report"
# Overwrites previous output
if [ -d "${OUTPUT_DIR}/evosuite-tests" ]; then
  rm -r "${OUTPUT_DIR}/evosuite-tests"
fi
# Move evosuite tests into output directory
mkdir -p "$OUTPUT_DIR/evosuite-tests"
mv -f "${CURRENT_DIR}/evosuite-tests" "${OUTPUT_DIR}"
