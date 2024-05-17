#!/bin/bash
# This script generates a list of non-axiomatic oracles using TOGA.
# It should save the output to "output/toga/oracle" as a list of OracleOutput records.

# Get current directory
current_dir=$(realpath "$(dirname "${BASH_SOURCE}")")
# Setup global variables
source "${current_dir}/utils/global_variables.sh"
# Setup sdkman
source "${UTILS_DIR}/init_sdkman.sh" "${SDKMAN_DIR}"
# Argument and setup check
if [ ! ${#} -eq 6 ]; then
  echo -e "toga.sh: Incorrect number of arguments. Expected 6 arguments, but got ${#}".
  exit 1
fi
# Define local variables
fully_qualified_name=${1}
src_path=${2}
project_jar_path=${3}
output_dir=${4-${OUTPUT_DIR}}
toga_project_dir="${ROOT_DIR}/generator/resources/toga"
toga_input_dir="${output_dir}/toga-input"
toga_output_dir="${output_dir}/toga-oracles"
toga_output_file="${toga_output_dir}/toga_output.csv"
# Generate path to toga output directory, if it does not exists
if [ ! -d "${toga_output_dir}" ]; then
    mkdir -p "${toga_output_dir}"
    echo "Folder created: ${toga_output_dir}"
fi
# Setup toga to perform the experiment
echo "toga.sh: Setup Toga project"
bash "${UTILS_DIR}/toga_setup.sh"
# Switch to Java 17
sdk use java "$JAVA17"
# Setup experiment jar, used to generate toga inputs
bash "${UTILS_DIR}/experiment_setup.sh"
# Generate toga inputs
echo "toga.sh: Generate Toga input files."
java -jar "generator/resources/experiment.jar" "generate_tog_input" "toga" "${fully_qualified_name}" "${output_dir}" "${src_path}" ${project_jar_path}
# Execute experiments with toga
echo "toga.sh: Generate oracles with Toga."
cd "${toga_project_dir}" || exit 1
python3 toga.py "${toga_input_dir}/toga_input.csv" "${toga_input_dir}/toga_metadata.csv"
mv "${toga_project_dir}/oracle_preds.csv" "${toga_output_file}"
# Map toga output to OracleOutput datapoints
echo "toga.sh: Map oracles generated with Toga into OracleOutputs."
java -jar "${EXPERIMENT_JAR}" "generate_oracle_output" "toga" "${toga_output_file}" "${output_dir}"
