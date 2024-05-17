#!/bin/bash
# This script generates oracles for all bugs in Defects4J for a given TOG.
current_dir=$(realpath "$(dirname "${BASH_SOURCE[0]}")")
root_dir=$(dirname "${current_dir}")
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
  if [ ! -d "${root_dir}/output/${tog}-oracles/${project_id}/${bug_id}" ]; then
    echo -e "Unable to find oracles generated for ${tog} ${project_id} ${bug_id}"
    continue
  fi
  if [ -d "${root_dir}/output/${tog}-tests/${project_id}/${bug_id}" ]; then
    echo -e "Already generated tests for ${tog} ${project_id} ${bug_id}"
    continue
  fi
  sdk use java "8.0.392-amzn"
  # checkout Defects4J project and export classpath
  project_dir="${root_dir}/temp/${project_id}_${bug_id}"
  defects4j checkout -p "${project_id}" -v "${bug_id}b" -w "${project_dir}"
  external_classpath="${root_dir}/generator/resources/evosuite-runtime.jar:${root_dir}/generator/resources/junit-4.13.2.jar:${root_dir}/generator/resources/sdkman/candidates/java/8.0.392-amzn/src"
  classpath=$(defects4j export -p "cp.compile" -w "${project_dir}")
  src_rel_path=$(defects4j export -p "dir.src.classes" -w "${project_dir}")
  echo "classpath: ${classpath}"
  # insert oracles
  prefix_path="${root_dir}/output/evosuite-prefixes/${project_id}/${bug_id}"
  oracle_path="${root_dir}/output/${tog}-oracles/${project_id}/${bug_id}"
  src_path="${project_dir}/${src_rel_path}"
  echo "source path: ${src_path}"
  sdk use java "17.0.8-oracle"
  java -cp "${classpath}" -jar "${root_dir}/generator/resources/experiment.jar" "insert_oracles" "${prefix_path}" "${oracle_path}" "${src_path}" "${classpath}:${external_classpath}"
#  rm -r "${root_dir}/temp"
done < "${current_dir}/resources/defects4j/modified_classes.csv"
