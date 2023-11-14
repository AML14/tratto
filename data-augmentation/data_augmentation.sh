#!/bin/bash
# This script executes the DataAugmentation project on a list of
# input projects.
# The jar reads the json file where are stored the information of
# the projects, and process each of them.
# The output is:
#   1. A list of files within the directory:
#           /src/main/resources/data_entries/[project_name]/classes
#      where each file contains a list of (empty) JDoctor conditions for each method
#      and constructor of the corresponding java file of the project analyzed.
#   2. A file containing the relevant information of the project analyzed,
#      to create a corresponding collection within the database:
#           /src/main/resources/data_entries/[project_name]/repository.json

# Get current directory
current_dir="$(realpath "$(dirname "$BASH_SOURCE")")"

# Setup global variables
source "${current_dir}/generator/utils/global_variables.sh"

# Setup local variables
javadoc_empty="false"

if [ "$1" = "--javadoc-empty" ]; then
  javadoc_empty="true"
fi

# Setup sdkman
source "${UTILS_DIR}/init_sdkman.sh" "${SDKMAN_DIR}"
# Switch to Java 17
sdk use java "${JAVA17}"
# Setup data augmentation
bash "${UTILS_DIR}/data_augmentation_setup.sh"
# Execute data augmentation to generate project conditions
cd "${DATA_AUGMENTATION_PROJECT_DIR}"
java -jar "${DATA_AUGMENTATION_JAR}" "$javadoc_empty"
cd "${ROOT_DIR}" || exit 1
