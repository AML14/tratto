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
current_dir=$(realpath "$(dirname "$BASH_SOURCE")")

# Setup global variables
if ! [ -v ROOT_DIR ]; then
    source "${current_dir}/utils/global_variables.sh"
else
    echo "Global variables already defined."
fi

# Setup local variables
target_class="$1"  # Fully-qualified name of target class
target_dir="$2"    # Directory of binary files of the system under test

# Setup sdkman
source "${UTILS_DIR}/sdkman_init.sh" "$SDKMAN_DIR"

# Generate output dir if it does not exists
if ! [ -d "$OUTPUT_DIR" ]; then
  mkdir "$OUTPUT_DIR"
fi

# Generate tests using EvoSuite
java -jar "$EVOSUITE_JAR" -class "$target_class" -projectCP "$target_dir" -base_dir="${OUTPUT_DIR}" -seed=42
