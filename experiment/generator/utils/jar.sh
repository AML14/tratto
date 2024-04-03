#!/bin/bash
# This script tests the Defects4J export command for future reference.
current_dir=$(realpath "$(dirname "${BASH_SOURCE[0]}")")
echo $current_dir
# Setup global variables
source "${current_dir}/global_variables.sh"
# Setup sdkman
source "${UTILS_DIR}/init_sdkman.sh"

while IFS=, read -r project_id bug_id _; do
  # Define the path to the jar file
  jars_path="${DEFECTS4J_DIR}/defects4jprefix/src/main/project-jars/${project_id}/${bug_id}"
  # Define the path to the buggy version of the project_id-bug_id project
  buggy_project_bug_dir="${DEFECTS4J_DIR}/temp/${project_id}_${bug_id}b"
  # Check for a given project or bug id
  if [ ${#} -gt 0 ]; then
    if [ "${project_id}" != "${1}" ]; then
      echo "Skipping ${project_id}-${bug_id}b. Project ID does not match."
      continue
    fi
    if [ ${#} -gt 1 ]; then
      if [ "${bug_id}" != "${2}" ]; then
        echo "Skipping ${project_id}-${bug_id}b. Bug ID does not match."
        continue
      fi
    fi
  fi
  if [ -f "${jars_path}" ]; then
    echo "Project jar already generated for ${project_id}-${bug_id}b."
    continue
  fi
  if [ ! -d "$jars_path" ]; then
    mkdir -p "$jars_path"
  fi
  if [ ! -d "${buggy_project_bug_dir}" ]; then
    # Checkout project
    sdk use java "$JAVA8"
    defects4j checkout -p "${project_id}" -v "${bug_id}b" -w "${buggy_project_bug_dir}"
  fi
  binary_path=$(defects4j export -p "dir.bin.classes" -w "$buggy_project_bug_dir")
  classpath=$(defects4j export -p "cp.compile" -w "${buggy_project_bug_dir}")
  # Initialize jar
  IFS=':' read -ra cp_elts <<< "${classpath}"
  num_elts=${#cp_elts[@]}
  # Copy files to output folder
  for ((i = 0; i < num_elts; i++)); do
      path=${cp_elts[i]}
      if [ -d "${path}" ]; then
          echo "$path"
          echo "${buggy_project_bug_dir}/${binary_path}"
          if [ ! "$path" == "${buggy_project_bug_dir}/${binary_path}" ]; then
              echo "Unexpected directory in classpath: ${path} - ${binary_path}."
          else
              jar cf "${jars_path}/${project_id}_${bug_id}b.jar" -C "${path}" .
          fi
      else
          # Use the file command to check the file's type
          if [[ $path == *".jar" ]]; then
            cp "${path}" "${jars_path}"
          else
            echo "Unexpected file not corresponding to a jar file, in classpath: ${path}."
          fi
      fi
  done
done < "$D4J_PROJECTS_BUGS"