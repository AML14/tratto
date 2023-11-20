#!/bin/bash
# This script setup the data augmentation project jar

# Get current directory
current_dir="$(realpath "$(dirname "$BASH_SOURCE")")"

# setup global variables
source "${current_dir}/global_variables.sh"

# Generate JAR
if [ ! -e "${DATA_AUGMENTATION_JAR}" ]; then
  echo "Generate DataAugmentation jar from java project."
  mvn clean package -DskipTests
  cd "${current_dir}"
  target_jar=$(find "${DATA_AUGMENTATION_PROJECT_DIR}/target" -type f -name "data-augmentation*-jar-with-dependencies.jar")
  # Check if a file was found
  if [ -z "$target_jar" ]; then
    echo "Unexpected error: dataAugmentation jar not found."
    exit 1
  fi
  # Generate path to resources if it does not exists
  if [ ! -d "${RESOURCES_DIR}" ]; then
    mkdir -p "${RESOURCES_DIR}"
  fi
  # Move jar to the resources folder and rename it as tratto.jar
  mv "$target_jar" "${DATA_AUGMENTATION_JAR}"
fi