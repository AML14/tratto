#!/bin/bash
# This script runs a test suite generated by a TOG in a given context. Saves
# the output to "output/[tog]/test".

# Get current directory
current_dir=$(realpath "$(dirname "${BASH_SOURCE[0]}")")
# Setup global variables
source "${current_dir}/generator/utils/global_variables.sh"

tog=${1}
target_class=${2}
src_dir=$(realpath "${3}")
bin_dir=$(realpath "${4}")
test_dir="${OUTPUT_DIR}/tog-tests/${tog}"

# setup sdkman
source "${UTILS_DIR}/init_sdkman.sh" "${SDKMAN_DIR}"

# Get project root directory
IFS='/' read -ra src_arr <<< "${src_dir}"
IFS='/' read -ra bin_arr <<< "${bin_dir}"
project_dir=""
for i in "${!src_arr[@]}" "${!bin_arr[@]}"; do
  if [ "${src_arr[i]}" != "${bin_arr[i]}" ]; then
    break
  fi
  project_dir="${project_dir}/${src_arr[i]}"
done
project_dir="${project_dir#/}"

# Copy tests and utilities into target project
cp -r "${test_dir}" "${project_dir}/evosuite-tests"
cp "${RESOURCES_DIR}/evosuite-1.0.6.jar" "${project_dir}"
cp "${RESOURCES_DIR}/evosuite-standalone-runtime-1.0.6.jar" "${project_dir}"
cd "${project_dir}" || exit 1

find "${project_dir}/evosuite-tests" -type f -name "*.java" > java_tests.txt
while read -r java_test; do
  javac "${java_test}"
done < java_tests.txt