#!/bin/bash
# This script generates a list of axiomatic oracles using JDoctor. Saves the
# output to "output/jdoctor-oracles" as a list of OracleOutput records.

# Get current directory
current_dir=$(realpath "$(dirname "${BASH_SOURCE}")")
# Setup global variables
source "${current_dir}/utils/global_variables.sh"
root_dir=$(dirname "${current_dir}")
# Setup sdkman
source "${current_dir}/utils/init_sdkman.sh"
sdk use java "$JAVA8"
# Check arguments
if [ ! $# -eq 4 ]; then
  echo -e "jdoctor.sh: Expected 4 arguments (target_class, src_dir, classpath, and output directory), but got $#"
  exit 1
fi
# Setup local variables
target_class=${1}
src_dir=${2}
classpath=${3}
output_dir=${4-${OUTPUT_DIR}}
jdoctor_output_dir="${output_dir}/jdoctor-oracles"
jdoctor_output_file="${jdoctor_output_dir}/jdoctor_output.json"
# Generate path to toga output directory, if it does not exists
if [ ! -d "$jdoctor_output_dir" ]; then
  mkdir -p "$jdoctor_output_dir"
fi
# Setup jdoctor to perform the experiment
echo "jdoctor.sh: Setup JDoctor project"
bash "${ROOT_DIR}/generator/utils/jdoctor_setup.sh"
start_task=$(date +%s%3N)
# Execute experiments with jdoctor
java -jar "$JDOCTOR_JAR" \
  --target-class "${target_class}" \
  --source-dir "${src_dir}" \
  --class-dir "${classpath}" \
  --condition-translator-output "${jdoctor_output_file}"
end_task=$(date +%s%3N)
duration_task=$(( end_task - start_task ))
echo "jdoctor.sh: Jdoctor task completed in: $duration_task"
sdk use java "17.0.8-oracle"
# Map jdoctor output to OracleOutput datapoints
echo "jdoctor.sh: Map oracles generated with Jdoctor into OracleOutputs."
java -jar "${EXPERIMENT_JAR}" "generate_oracle_output" "jdoctor" "${jdoctor_output_file}"
# Cleanup
rm -r "${root_dir}/glove-txt"
rm -r "${root_dir}/wmd-glove-distances.csv"
