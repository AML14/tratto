#!/bin/bash
# This script setup the experiments with jdoctor

# Get current directory
current_dir=$(realpath "$(dirname "$BASH_SOURCE")")

# Setup global variables
if ! [ -v ROOT_DIR ]; then
    source "${current_dir}/global_variables.sh"
else
    echo "Global variables already defined."
fi

# Generate JAR
if [ ! -e "${JDOCTOR_JAR}" ]; then
  cd "$RESOURCES_DIR"
  echo "JDoctor project not found."
  echo "Downloading JDoctor project into './generator/resources'..."
  wget "$JDOCTOR_JAR_LINK" -O "$JDOCTOR_JAR"
  #git clone "$JDOCTOR_GITHUB_REPO" jdoctor
  #cd "$JDOCTOR_PROJECT_DIR"
  #echo "Generate jdoctor jar from java project."
  #./gradlew shadowJar
  #mv "${JDOCTOR_PROJECT_DIR}/build/libs/toradocu-1.0-all.jar" "$JDOCTOR_JAR"
fi
rm -rf "$JDOCTOR_PROJECT_DIR"