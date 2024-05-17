#!/bin/bash
# This script generates oracles for all bugs in Defects4J for a given TOG.
current_dir=$(realpath "$(dirname "${BASH_SOURCE[0]}")")
root_dir=$(dirname "${current_dir}")
# setup defects4j and sdkman
export PATH=$PATH:"${DEFECTS4J_HOME}"/framework/bin
source "${root_dir}/generator/utils/init_sdkman.sh"
tog=${1}

sdk use java "17.0.8-oracle"
while IFS=, read -r project_id bug_id modified_class; do
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
  qualifiers=$(echo "${modified_class}" | sed 's/\./\//g')
  oracle_path="${root_dir}/output/${tog}-oracles/${project_id}/${bug_id}/${qualifiers}_tratto_output.json"
  java -jar "${root_dir}/generator/resources/experiment.jar" "generate_oracle_output" "${tog}" "${oracle_path}" ""
done < "${current_dir}/resources/defects4j/modified_classes.csv"
