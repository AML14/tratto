#!/bin/bash
# This script generates a test suite using EvoSuite and saves the output to
# "output/evosuite-tests".

# argument and setup check
if [ ! $# -eq 2 ]; then
  echo -e "evosuite.sh: Incorrect number of arguments. Expected 2 arguments, but got ${#}".
  exit 1
elif [ ! -d "${2}" ]; then
  echo -e "evosuite.sh: The system binaries path \"${2}\" does not exist."
  exit 1
fi

# get current directory
# shellcheck disable=SC2128
current_dir=$(realpath "$(dirname "${BASH_SOURCE}")")
# setup global variables
source "${current_dir}/utils/global_variables.sh"

# setup local variables
target_class="${1}"  # Fully-qualified name of target class
target_dir="${2}"    # Directory of binary files of the system under test
target_class_path=$(echo "$target_class" | sed 's/\./\//g')

# setup sdkman
source "${UTILS_DIR}/init_sdkman.sh" "${SDKMAN_DIR}"

# generate output dir if it does not exists
if ! [ -d "${OUTPUT_DIR}" ]; then
  mkdir "${OUTPUT_DIR}"
fi

# switch to Java 8
sdk use java "${JAVA8}"

# Generate tests using EvoSuite, if the tests does not exist
if [ ! -f "${OUTPUT_DIR}/evosuite-tests/${target_class_path}_ESTest.java" ]; then
  echo "${OUTPUT_DIR}/evosuite-tests/${target_class_path}.java"
  java -jar "${EVOSUITE_JAR}" -class "${target_class}" -projectCP "${target_dir}" -seed 13042023 -base_dir "${OUTPUT_DIR}"
  #rm -r "${OUTPUT_DIR}/evosuite-report"  # delete statistics
fi


# switch to Java 17
sdk use java "${JAVA17}"

# convert EvoSuite tests into test prefixes
java -jar "${EXPERIMENT_JAR}" "remove_oracles" "${target_class}"
