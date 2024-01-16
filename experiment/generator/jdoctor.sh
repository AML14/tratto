#!/bin/bash
# This script generates a list of axiomatic oracles using JDoctor. Saves the
# output to "output/jdoctor-oracles" as a list of OracleOutput records.
current_dir=$(realpath "$(dirname "${BASH_SOURCE[0]}")")
root_dir=$(dirname "${current_dir}")
# setup sdkman
source "${current_dir}/utils/init_sdkman.sh"
sdk use java "8.0.382-amzn"
# check arguments
if [ ! $# -eq 3 ]; then
  echo -e "jdoctor.sh: Expected 3 arguments (target_class, src_dir, classpath), but got $#"
  exit 1
fi
target_class="${1}"
src_dir="${2}"
classpath="${3}"
qualifiers=$(echo "${target_class}" | sed 's/\./\//g')

# generate OracleOutput with JDoctor
jdoctor_output_file="${root_dir}/output/jdoctor-oracles/${qualifiers}_jdoctor_output.json"
if [ ! -d "$(dirname "${jdoctor_output_file}")" ]; then
  mkdir -p "$(dirname "${jdoctor_output_file}")"
fi
touch "${jdoctor_output_file}"
java -jar "${current_dir}/resources/toradocu-1.0-all.jar" \
  --target-class "${target_class}" \
  --source-dir "${src_dir}" \
  --class-dir "${classpath}" \
  --condition-translator-output "${jdoctor_output_file}"
# cleanup
rm -r "${root_dir}/glove-txt"
rm -r "${root_dir}/wmd-glove-distances.csv"
