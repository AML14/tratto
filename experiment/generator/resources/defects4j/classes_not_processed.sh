#!/bin/bash
# This script generates oracles for all bugs in Defects4J for a given TOG.
current_dir=$(realpath "$(dirname "${BASH_SOURCE[0]}")")
# Setup global variables
source "${current_dir}/utils/global_variables.sh"
# setup sdkman
source "${UTILS_DIR}/init_sdkman.sh"
tog=${1}
round=${2}
format=${3}
d4j_classes_file_path=${4}
# Initialize the counter
counter=0

# Read the CSV file line by line and split into project_id, bug_id, and modified_classes fields
while IFS=, read -r project_id bug_id modified_classes; do
  # Map the modified_classes field from string to list
  IFS=, read -ra modified_classes_list <<< "${modified_classes}"
  # Iterate over the modified classes and generate test cases for each class
  for modified_class in "${modified_classes[@]}"; do
      # Fully qualified classname path
      fqn_path=$(echo "$modified_class" | sed 's/\./\//g')
      # D4J output path
      d4j_project_bug_output="${DEFECTS4J_DIR}/output_${tog}/${round}/${project_id}/${bug_id}"
      d4j_fqn_output="${d4j_project_bug_output}/${fqn_path}"
      project_jar_path="${DEFECTS4J_DIR}/defects4jprefix/src/main/project-jars/${project_id}/${bug_id}"
      
      if [ "${tog}" == "baseline" ]; then
        if [ ! -e "${d4j_fqn_output}/evosuite-prefixes/${modified_class##*.}_ESTest.${format}" ]; then
          echo "${project_id},${bug_id},${modified_class}"
          # Increment the counter if the condition is true
          ((counter++))
        fi
      else
        if [ ! -e "${d4j_fqn_output}/${tog}-oracles/${tog}_output.${format}" ]; then
          echo "${project_id},${bug_id},${modified_class}"
          # Increment the counter if the condition is true
          ((counter++))
        fi
      fi
  done
done < "${d4j_classes_file_path}"
echo "Total: ${counter}"
