#!/bin/bash
# This script generates a list of axiomatic oracles using JDoctor. Saves the
# output to "output/jdoctor/oracle" as a list of OracleOutput records.

# arguments check
if [ ! $# -eq 3 ]; then
  echo -e "jdoctor.sh: Expected 3 arguments, but got ${#}".
  exit 1
elif [ ! -d "${2}" ]; then
  echo -e "jdoctor.sh: The source directory \"${2}\" does not exist."
  exit 1
elif [ ! -f "${3}" ]; then
  echo -e "jdoctor.sh: The project jar \"${3}\" does not exist."
  exit 1
fi

# get current directory
current_dir=$(realpath "$(dirname "${BASH_SOURCE[@]}")")
# setup global variables
source "${current_dir}/utils/global_variables.sh"

# define local variables
target_class="${1}"  # fully-qualified name of target class
src_dir="${2}"  # project source directory
jar_path="${3}"  # path to jar of the project under test

# setup sdkman
source "${UTILS_DIR}/init_sdkman.sh" "${SDKMAN_DIR}"

# switch to Java 8
sdk use java "${JAVA8}"

echo "jdoctor.sh: Setup JDoctor project"
bash "${ROOT_DIR}/generator/utils/jdoctor_setup.sh"

# generate output dir if it does not exists
if ! [ -d "${OUTPUT_DIR}/jdoctor" ]; then
  mkdir -p "${OUTPUT_DIR}/jdoctor"
fi

# execute JDoctor to generate oracles
mkdir -p "${OUTPUT_DIR}/jdoctor/output"
java -jar "${JDOCTOR_JAR}" \
    --target-class "${target_class}" \
    --source-dir "${src_dir}" \
    --class-dir "${jar_path}:${dependencies_path}" \
    --condition-translator-output "${OUTPUT_DIR}/jdoctor/output/jdoctor_output.json"

# switch to Java 17
sdk use java "${JAVA17}"

# convert JDoctor JSON to OracleOutput
java -jar "${EXPERIMENT_JAR}" "generate_oracle_output" "jdoctor" ""   # empty argument for compatibility
