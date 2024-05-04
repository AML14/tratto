#!/bin/bash
# This script generates a list of non-axiomatic oracles using Tratto. Saves the
# output to "output/tratto/oracle" as a list of OracleOutput records.

# Arguments check
if [ ! $# -eq 5 ]; then
  echo -e "tratto.sh: Incorrect number of arguments. Expected 5 arguments, but got ${#}".
  exit 1
fi
# Get current directory
current_dir=$(realpath "$(dirname "${BASH_SOURCE}")")
# Setup global variables
source "${current_dir}/utils/global_variables.sh"
# Setup sdkman
source "${current_dir}/utils/init_sdkman.sh"
# Setup local variables
fully_qualified_name=${1}
src_path=${2}
project_jar_path=${3}
output_dir=${4-${OUTPUT_DIR}}
server_port=${5-${SERVER_PORT}}
resources_dir="${ROOT_DIR}/generator/resources"
utils_dir="${ROOT_DIR}/generator/utils"
tratto_project_dir="${ROOT_DIR}/../tratto"
tratto_output_dir="${output_dir}/tratto-oracles"
tratto_output_file="${tratto_output_dir}/tratto_output.json"
# Generate path to tratto output directory, if it does not exists
if [ ! -d "$tratto_output_dir" ]; then
    # If it doesn't exist, create the folder
    mkdir -p "$tratto_output_dir"
    echo "Folder created: ${tratto_output_dir}"
fi
# Switch to Java 17
sdk use java "${JAVA17}"
# Setup Tratto to perform the experiment
bash "${utils_dir}/tratto_setup.sh"
# Execute Tratto to generate oracles
java -jar "${TRATTO_JAR}" "${fully_qualified_name}" "${src_path}" "${project_jar_path}" "${SERVER_PORT}"
mv "${tratto_project_dir}/src/main/resources/oracle_datapoints.json" "${tratto_output_file}"
# Map Tratto output to OracleOutput datapoints
echo "tratto.sh: Map oracles generated with Tratto into OracleOutputs."
java -jar "$EXPERIMENT_JAR" "generate_oracle_output" "tratto" "${tratto_output_file}" "${project_jar_path}"