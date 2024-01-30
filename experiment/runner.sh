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

if [ ! -d "${root_dir}/test-suite" ]; then
  mkdir "${root_dir}/test-suite"
fi
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
  # compress test suite
  cd "${root_dir}/output/${tog}-tests/${project_id}/${bug_id}" || exit 1
  tar -cvjSf "${project_id}-${bug_id}b-evosuite.tar.bz2" .
  mv "${project_id}-${bug_id}b-evosuite.tar.bz2" "${root_dir}/test-suite"
  cd - || exit 1
  # run bug detection
  run_bug_detection.pl -p "${project_id}" -d "${root_dir}/test-suite" -o "${root_dir}/test-suite"
  # cleanup
  rm "${root_dir}/test-suite/${project_id}-${bug_id}b-evosuite.tar.bz2"
#  sdk use java "17.0.8-oracle"
done < "${root_dir}/generator/resources/modified_classes.csv"
