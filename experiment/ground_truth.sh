#!/bin/bash
# This script perform the experiments for Defect4J projects

# Get current directory
current_dir=$(realpath "$(dirname "${BASH_SOURCE[@]}")")
# Setup global variables
source "${current_dir}/generator/utils/global_variables.sh"

# Setup sdkman
source "${UTILS_DIR}/init_sdkman.sh"

# Arguments and setup check
if [ ! $# -eq 1 ]; then
  echo -e "defects4j.sh: Incorrect number of arguments. Expected 1 argument, but got ${#}".
  exit 1
fi

# Tog name
tog="${1}"

# Read the CSV file line by line and split into project_id, bug_id, and modified_classes fields
while IFS=, read -r project_name github_link commit_id; do
    cd "$ROOT_DIR"
    sdk use java "$JAVA8"
    # Clean modified_classes string from undesired white-spaces/line-breaks introduced with the CSV parsing
    commit_id="${commit_id//[$'\t\r\n ']/}"
    # Clone project
    echo "Cloning project: ${project_name}."
    git clone "${github_link}" "${GROUND_TRUTH_DIR}/${project_name}/project"
    # Checkout buggy version of the project_id-bug_id project
    echo "Checkout commit version: project ${project_name}-${commit_id}."
    cd "${GROUND_TRUTH_DIR}/${project_name}/project"
    git checkout "${commit_id}"

done < "$GROUND_TRUTH_PROJECTS"