#!/bin/bash
# This script perform the experiments for Defect4J projects

# Get current directory
current_dir=$(realpath "$(dirname "${BASH_SOURCE[@]}")")
# Setup global variables
source "${current_dir}/generator/utils/global_variables.sh"

# Read the CSV file line by line and split into project_id, bug_id, and modified_classes fields
while IFS=, read -r project_name github_link commit_id; do
    cd "$ROOT_DIR"
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