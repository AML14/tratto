#!/bin/bash
# This script runs a test suite generated by a TOG in a given context. Saves
# the output to "output/[tog]/test".

current_dir=$(realpath "$(dirname "${BASH_SOURCE[@]}")")
source "${current_dir}/generator/utils/global_variables.sh"
source "${UTILS_DIR}/init_sdkman.sh"
sdk use "${JAVA8}"

# get local variables
tog="${1}"
target_class="${2}"
src_dir="$(realpath "${3}")"
bin_dir="$(realpath "${4}")"
test_dir="$(realpath "${5}")"
qualifiers=$(echo "${target_class}" | sed 's/\./\//g' | xargs dirname)
# get project root path
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
# copy tests from tog-tests to test directory
test_dir="${test_dir}/${qualifiers}"
if [ ! -d "${test_dir}" ]; then
  mkdir -p "${test_dir}"
fi
cp "${ROOT_DIR}/output/${tog}/tog-tests/"* "${test_dir}/"
cd "${project_dir}" || exit 1

test_output="${ROOT_DIR}/test_output.txt"
simple_output="${ROOT_DIR}/simple_output.txt"
touch "${test_output}"
touch "${simple_output}"
# run tests
defects4j compile
defects4j test > "${test_output}"
tail -n +2 "${test_output}" > "${test_output}.tmp" && mv "${test_output}.tmp" "${test_output}"
while IFS= read -r line; do
  echo "${line}" | awk -F '::' '{print $NF}' | tr -d ' ' >> "${simple_output}"
done < "${test_output}"
# cleanup
rm "${test_output}"
mv "${simple_output}" "${ROOT_DIR}/output/${tog}/tog-tests/test_fails.txt"

# generate TestOutput record
sdk use java "${JAVA17}"
java -jar "${EXPERIMENT_JAR}" "generate_test_output" "${tog}" "${target_class}" "${src_dir}" "${bin_dir}"
