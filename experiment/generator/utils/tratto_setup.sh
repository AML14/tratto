#!/bin/bash
# This script setup the experiments with tratto

# Get current directory
current_dir=$(realpath "$(dirname "$BASH_SOURCE")")

# Setup global variables
if ! [ -v ROOT_DIR ]; then
    source "${current_dir}/global_variables.sh"
else
    echo "Global variables already defined."
fi

# Generate JAR
if [ ! -e "${TRATTO_JAR}" ]; then
  echo "Generate tratto jar from java project."
  cd "${TRATTO_PROJECT_DIR}"
  bash scripts/install_dependencies.sh
  mvn clean package -DskipTests
  cd "${ROOT_DIR}"
  target_jar=$(find "${TRATTO_PROJECT_DIR}/target" -type f -name "tratto*-jar-with-dependencies.jar")
  # Check if a file was found
  if [ -z "$target_jar" ]; then
    echo "Unexpected error: tratto jar not found."
    exit 1
  fi
  # Move jar to the resources folder and rename it as tratto.jar
  mv "$target_jar" "${TRATTO_JAR}"
fi