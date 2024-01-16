#!/bin/bash
tog=${1}
# This script generates oracles for all bugs in Defects4J for a given TOG.
current_dir=$(realpath "$(dirname "${BASH_SOURCE[0]}")")
root_dir=$(dirname "${current_dir}")
# setup defects4j and sdkman
export PATH=$PATH:"${DEFECTS4J_HOME}"/framework/bin
source "${current_dir}/utils/init_sdkman.sh"

sdk use java "8.0.382-amzn"
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
  # checkout Defects4J project and export features
  project_dir="${root_dir}/temp/${project_id}_${bug_id}"
  defects4j checkout -p "${project_id}" -v "${bug_id}b" -w "${project_dir}"
  classpath=$(defects4j export -p "cp.compile" -w "${project_dir}")
  src_dir="${project_dir}/$(defects4j export -p "dir.src.classes" -w "${project_dir}")"
  # generate oracles using TOG
  if [ "${tog}" == "jdoctor" ]; then
#    ./jdoctor.sh "${modified_class}" "${src_dir}" "${classpath}"
    echo "${src_dir}"
  elif [ "${tog}" == "toga" ]; then
    ./toga.sh "${modified_class}"
  elif [ "${tog}" == "tratto" ]; then
    ./tratto.sh
  else
    echo -e "tog.sh: Invalid TOG name, ${tog}"
    exit 1
  fi
  rm -r "${root_dir}/temp"
done < "${current_dir}/resources/modified_classes.csv"
