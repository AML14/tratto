#!/bin/bash
# This script generates a test suite using EvoSuite and saves the output to
# experiment/output/evosuite-test.

# Get current directory
CURRENT_DIR=$(realpath "$(dirname "$BASH_SOURCE")")

# Setup global variables
if ! [ -v ROOT_DIR ]; then
    source "${CURRENT_DIR}/generator/utils/global_variables.sh"
else
    echo "Global variables already defined."
fi

# Setup local variables
TARGET_CLASS="$1"  # Fully-qualified name of target class
TARGET_DIR="$2"    # Directory of binary files of the system under test

# Setup sdkman
source "${UTILS_DIR}/init_sdkman.sh"

sdk use java "$JAVA8"

if ! [ -d "$OUTPUT_DIR" ]; then
  mkdir "$OUTPUT_DIR"
fi

# Generate tests using EvoSuite
#java -jar "$EVOSUITE_JAR" -class "$TARGET_CLASS" -projectCP "$TARGET_DIR" -base_dir="${OUTPUT_DIR}" -seed=42