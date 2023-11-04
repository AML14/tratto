#!/bin/bash
# This script migrates data of projects to the database, for data augmentation.
# Initially, the program asks if the user wants to setup new projects. In this
# phase, the script generates two input files:
#   1. src/main/java/star/tratto/data/repos/input_projects.csv
#   2. src/main/java/star/tratto/data/repos/input_projects.json
# The two files contain the information to clone the projects and generate the
# collections and document to upload to the database.
# The generation of the collections is performed executing the JAR of the
# DataAugmentation project.
# Finally, the script execute a python script to upload the collections and documents
# produced on the database.

# Get current directory
current_dir="$(realpath "$(dirname "$BASH_SOURCE")")"

# Get global variables
source "${current_dir}/generator/utils/global_variables.sh"

# Define local variables
data_aug_resources_dir="${current_dir}/src/main/resources"
projects_source_dir="${data_aug_resources_dir}/projects-source"

python3 "${ROOT_DIR}/scripts/generate_input_projects.py"

# Read the CSV file line by line and split into project_name, github_link, and commit fields
while IFS=, read -r project_name github_link commit; do
  # Clean commit string from undesired white-spaces/line-breaks introduced with the CSV parsing
  commit="${commit//[$'\t\r\n ']/}"
  # Create path if it does not exists
  if [ ! -d "$projects_source_dir" ]; then
      mkdir -p "$projects_source_dir";
  fi
  # Move to the projects_source directory where to clone the current project
  cd "$projects_source_dir"
  # Check if the project has already been cloned
  if [ ! -d "${projects_source_dir}/${project_name}" ]; then
    echo "Cloning project: ${project_name}"
    # Clone the project
    git clone "$github_link" "$project_name"
    # Check if a specific commit must be set
      if [ "$commit" != "last" ]; then
        echo "Move head to commit: ${commit}"
        # Move within the project
        cd "${projects_source_dir}/${project_name}"
        git reset --hard "$commit"
      fi
  fi
done < "$INPUT_PROJECTS_CSV_FILE"
# Move to the root directory
cd "$current_dir"
# Generate collections and documents for each project
bash data_augmentation.sh
# Upload collections and documents on database
python3 "${ROOT_DIR}/scripts/migrate.py"
