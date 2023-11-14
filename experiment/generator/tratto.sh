#!/bin/bash
# This script generates a list of non-axiomatic oracles using Tratto. Saves the
# output to "output/tratto/oracle" as a list of OracleOutput records.

# Arguments and setup check
if [ ! $# -eq 3 ]; then
  echo -e "tratto.sh: Incorrect number of arguments. Expected 3 arguments, but got ${#}".
  exit 1
fi

# Get current directory
# shellcheck disable=SC2128
current_dir=$(realpath "$(dirname "${BASH_SOURCE}")")
# Setup global variables
source "${current_dir}/utils/global_variables.sh"

# Setup local variables
fully_qualified_name="${1}"
src_path="${2}"
project_jar_path="${3}"
resources_dir="${ROOT_DIR}/generator/resources"
utils_dir="${ROOT_DIR}/generator/utils"
tratto_project_dir="${ROOT_DIR}/../tratto"
tratto_output_dir="${ROOT_DIR}/output/tratto/output"

# Setup sdkman
source "${utils_dir}/init_sdkman.sh" "${SDKMAN_DIR}"
# Switch to Java 17
sdk use java "${JAVA17}"
# Setup tratto
bash "${utils_dir}/tratto_setup.sh"
# Execute tratto to generate oracles
cd "${tratto_project_dir}" || exit 1
java -jar "${TRATTO_JAR}" "${fully_qualified_name}" "${src_path}" "${project_jar_path}" "${SERVER_PORT}"
cd "${ROOT_DIR}" || exit 1

if [ ! -d "${tratto_output_dir}" ]; then
    # If it doesn't exist, create the folder
    mkdir -p "${tratto_output_dir}"
    echo "Folder created: ${tratto_output_dir}"
fi

mv "${tratto_project_dir}/src/main/resources/oracle_datapoints.json" "${tratto_output_dir}"

java -jar "${resources_dir}/experiment.jar" "generate_oracle_output" "tratto" "${src_path}"