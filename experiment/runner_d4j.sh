#!/bin/bash
# This script run evosuite tests on a Defects4J project, using a script
# provided by the authors of the Defects4J project

# Get current directory and root directory
current_dir=$(realpath "${BASH_SOURCE[0]}")
root_dir=$(dirname "${current_dir}")
# Setup global variables
source "${root_dir}/generator/utils/global_variables.sh"
source "${root_dir}/generator/utils/init_sdkman.sh"
tog=${1}
project_id=${2}
bug_id=${3}
output_dir=${4-$OUTPUT_DIR}
output_test_suite_path=${5-"${root_dir}/test-suite"}
# Generate path to test-suite output directory, if it does not exists
if [ ! -d "$output_test_suite_path" ]; then
  mkdir "$output_test_suite_path"
fi
# Switch to Java 8
sdk use java "$JAVA8"
# Compress test suite
cd "${output_dir}/${tog}-tests" || exit 1
tar -cvjSf "$output_test_suite_path/${project_id}-${bug_id}b-evosuite.tar.bz2" .
cd - || exit 1
# Run bug detection
run_bug_detection.pl -p "${project_id}" -d "${output_test_suite_path}" -o "${output_test_suite_path}"

