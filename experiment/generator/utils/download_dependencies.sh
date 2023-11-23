#!/bin/bash
# This script perform the experiments for Defect4J projects

# Get current directory
current_dir=$(realpath "$(dirname "${BASH_SOURCE[@]}")")
# Setup global variables
source "${current_dir}/global_variables.sh"

# Setup sdkman
source "${UTILS_DIR}/init_sdkman.sh"

dependency_type="$1"

# Read the CSV file line by line and split into project_id, bug_id, and modified_classes fields
while IFS=, read -r dependency_group dependency_name dependency_version; do
    if [ -d "/home/coder/project/tratto/experiment/generator/resources/_jars" ]; then
        mkdir -p "/home/coder/project/tratto/experiment/generator/resources/${dependency_type}_jars"
    fi
    http_path="https://repo1.maven.org/maven2/${dependency_group}/${dependency_name}/${dependency_version}/${dependency_name}-${dependency_version}.jar"
    wget -P /home/coder/project/tratto/experiment/generator/resources/${dependency_type}_jars "$http_path"
done < "/home/coder/project/tratto/experiment/${dependency_type}_dependencies.csv"

