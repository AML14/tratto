#!/bin/bash
# This script generates oracles for all bugs in Defects4J for a given TOG.
current_dir=$(realpath "$(dirname "${BASH_SOURCE[0]}")")
root_dir=$(dirname "${current_dir}")
# Setup global variables
source "${root_dir}/generator/utils/global_variables.sh"
# setup defects4j and sdkman
export PATH=$PATH:"${DEFECTS4J_HOME}"/framework/bin
source "${root_dir}/generator/utils/init_sdkman.sh"
tog=${1}

while IFS=, read -r project_id bug_id _; do
  # check for a given project or bug id
  if [ ${#} -gt 1 ]; then
    if [ "${project_id}" != "${2}" ]; then
      continue
    fi
    if [ ${#} -gt 2 ]; then
      if [ "${bug_id}" != "${3}" ]; then
        continue
      fi
    fi
  fi
  sdk use java "$JAVA8"
  # checkout Defects4J project and export classpath
  project_dir="${root_dir}/temp/${project_id}_${bug_id}"
  defects4j checkout -p "${project_id}" -v "${bug_id}b" -w "${project_dir}"
  classpath=$(defects4j export -p "cp.compile" -w "${project_dir}")
  # insert oracles
  prefix_path="${root_dir}/output/evosuite-prefixes/${project_id}/${bug_id}"
  oracle_path="${root_dir}/output/${tog}-oracles/${project_id}/${bug_id}"
  sdk use java "$JAVA17"
  java -jar "${root_dir}/generator/resources/experiment.jar" "insert_oracles" "${prefix_path}" "${oracle_path}" "${classpath}"
  rm -r "${root_dir}/temp"
done < "${current_dir}/resources/modified_classes.csv"
