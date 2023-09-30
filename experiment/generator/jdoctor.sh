#!/bin/bash
# This script generates a list of axiomatic oracles using JDoctor. Saves the
# output to "output/jdoctor/oracle" as a list of OracleOutput records.

# Arguments check
if [ ! $# -eq 3 ]; then
  echo -e "jdoctor.sh: Expected 3 arguments, but got ${#}".
  exit 1
elif [ ! -d "${2}" ]; then
  echo -e "jdoctor.sh: The source directory \"${2}\" does not exist."
  exit 1
elif [ ! -d "${3}" ]; then
  echo -e "jdoctor.sh: The system binaries path \"${3}\" does not exist."
  exit 1
fi

# Get current directory
# shellcheck disable=SC2128
current_dir=$(realpath "$(dirname "$BASH_SOURCE")")
# Setup global variables
source "${current_dir}/utils/global_variables.sh"

# Define local variables
target_class="$1"  # fully-qualified name of target class
src_dir="$2"  # project source directory
class_dir="$3"  # path to binary files of the system under test

# Setup sdkman
source "${UTILS_DIR}/init_sdkman.sh" "$SDKMAN_DIR"

# Generate output dir if it does not exists
if ! [ -d "$OUTPUT_DIR/jdoctor" ]; then
  mkdir -p "$OUTPUT_DIR/jdoctor"
fi

# Switch to Java 8
sdk use java "$JAVA8"

# Execute JDoctor to generate oracles
(
  java -jar "$JDOCTOR_JAR" \
    --target-class "$target_class" \
    --source-dir "$src_dir" \
    --class-dir "$class_dir" \
    --condition-translator-output "${OUTPUT_DIR}/jdoctor/jdoctor_output.json"  # location of JDoctor output in JSON format
)

# Switch to Java 17
sdk use java "$JAVA17"

# Convert JDoctor JSON to OracleOutput
java -jar "$EXPERIMENT_JAR" "jdoctor" "generate_oracle_outputs" "${OUTPUT_DIR}/jdoctor/jdoctor_output.json"
