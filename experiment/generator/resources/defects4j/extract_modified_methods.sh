#!/bin/bash
# This script perform the experiments for Defect4J projects

# Get current directory
current_dir=$(realpath "$(dirname "${BASH_SOURCE[@]}")")
# Setup global variables
source "${current_dir}/../../utils/global_variables.sh"
# Setup sdkman
source "${UTILS_DIR}/init_sdkman.sh"

# Arguments and setup check
if [ $# -lt 2 ] || [ $# -gt 4 ]; then
  echo -e "defects4j.sh: Incorrect number of arguments. Expected 2 or 4 argument, but got ${#}".
  exit 1
fi

# Tog name
project_id="${1}"
# Round number
bug_id="${2}"
# Context
context="${3-20}"


sdk use java "$JAVA8"
# Define the path to the buggy version of the project_id-bug_id project
buggy_project_bug_dir="${DEFECTS4J_DIR}/temp/${project_id}_${bug_id}b"
# Define the path to the fixed version of the project_id-bug_id project
fixed_project_bug_dir="${DEFECTS4J_DIR}/temp/${project_id}_${bug_id}f"

if [ ! -d "$buggy_project_bug_dir" ]; then
    echo "Checkout buggy version: project ${project_id}-${bug_id}b."
    defects4j checkout -p "${project_id}" -v "${bug_id}b" -w "$buggy_project_bug_dir"
    echo "Checkout complete."
fi

if [ ! -d "$fixed_project_bug_dir" ]; then
    echo "Checkout fixed version: project ${project_id}-${bug_id}f."
    defects4j checkout -p "${project_id}" -v "${bug_id}f" -w "$fixed_project_bug_dir"
    echo "Checkout complete."
fi

cd "$buggy_project_bug_dir"

git diff -u -b -U"$context" HEAD HEAD^ > "${ROOT_DIR}/diff_output.txt"

#git diff --no-index -u -b -U100 "$buggy_project_bug_dir" "$fixed_project_bug_dir" > diff_output.txt