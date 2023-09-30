#!/bin/bash
# This script generates a test suite using EvoSuite and saves the output to
# "output/evosuite-tests".

# Argument and setup check
if [ ! $# -eq 2 ]; then
  echo -e "evosuite.sh: Incorrect number of arguments. Expected 2 arguments, but got ${#}".
  exit 1
elif [ ! -d "${2}" ]; then
  echo -e "evosuite.sh: The system binaries path \"${2}\" does not exist."
  exit 1
fi

# Get current directory
# shellcheck disable=SC2128
current_dir=$(realpath "$(dirname "${BASH_SOURCE}")")
# Setup global variables
source "${current_dir}/utils/global_variables.sh"

# Setup local variables
target_class="${1}"  # Fully-qualified name of target class
target_dir="${2}"    # Directory of binary files of the system under test

# Setup sdkman
source "${UTILS_DIR}/init_sdkman.sh" "${SDKMAN_DIR}"

# Generate output dir if it does not exists
if ! [ -d "${OUTPUT_DIR}" ]; then
  mkdir "${OUTPUT_DIR}"
fi

# Switch to Java 8
sdk use java "${JAVA8}"

# Generate tests using EvoSuite
java -jar "${EVOSUITE_JAR}" -class "${target_class}" -projectCP "${target_dir}" -seed 13042023
rm -r "${ROOT_DIR}/evosuite-report"  # delete statistics
# overwrites previous output if necessary
if [ -d "${OUTPUT_DIR}/evosuite-tests" ]; then
  rm - r "${OUTPUT_DIR}/evosuite-tests"
fi
# moves evosuite tests to "output/evosuite-tests"
mkdir -p "${OUTPUT_DIR}/evosuite-tests"
mv "${ROOT_DIR}/evosuite-tests" "${OUTPUT_DIR}"

# switch to Java 17
sdk use java "${JAVA17}"

# convert EvoSuite tests into test prefixes
java -jar "${EXPERIMENT_JAR}" "remove_oracles" "${target_dir}"
