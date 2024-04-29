#!/bin/bash
# This script generates oracles for all bugs in Defects4J for a given TOG.
current_dir=$(realpath "${BASH_SOURCE[0]}")
root_dir=$(dirname "${current_dir}")
# Setup global variables
source "${root_dir}/generator/utils/global_variables.sh"
# setup defects4j and sdkman
export PATH=$PATH:"${DEFECTS4J_HOME}"/framework/bin
source "${root_dir}/generator/utils/init_sdkman.sh"
tog=${1}
project_id=${2}
bug_id=${3}
output_dir=${4-$OUTPUT_DIR}
output_test_suite_path=${5-"${root_dir}/test-suite"}

if [ ! -d "$output_test_suite_path" ]; then
  mkdir "$output_test_suite_path"
fi

sdk use java "$JAVA8"
# compress test suite
cd "${output_dir}/${tog}-tests" || exit 1
tar -cvjSf "$output_test_suite_path/${project_id}-${bug_id}b-evosuite.tar.bz2" .
cd - || exit 1
# run bug detection
run_bug_detection.pl -p "${project_id}" -d "${output_test_suite_path}" -o "${output_test_suite_path}"
# cleanup
#rm "${output_test_suite_path}/tests-evosuite.tar.bz2"

