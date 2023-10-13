#!/bin/bash
# This script setup the jar for the experiments

current_dir=$(realpath "$(dirname "${BASH_SOURCE}")")
# setup global variables
source "${current_dir}/global_variables.sh"

# Generate experiment JAR if not present
if [ ! -f "${EXPERIMENT_JAR}" ]; then
  mvn clean package -DskipTests
  target_jar=$(find "${ROOT_DIR}/target" -type f -name "experiment*-jar-with-dependencies.jar")
    # Check if a file was found
    if [ -z "${target_jar}" ]; then
      echo "experiment.sh: Unable to build jar for experiment module."
      exit 1
    fi
    mv "${target_jar}" "${RESOURCES_DIR}/experiment.jar"
fi