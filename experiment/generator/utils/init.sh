#!/bin/bash
# This script install the requirements to run the experiments
# using an isolated version of sdkman. Sdkman let to easily
# manage different versions of Java JDK required by the tools
# used for the experiments. The script assumes zip and unzip
# packages are installed in the machine.

# Get current directory
current_dir=$(realpath "$(dirname "$BASH_SOURCE")")
# Setup global variables
source "${current_dir}/global_variables.sh"

# Download sdkman
bash "${UTILS_DIR}/install_sdkman.sh"
source "${UTILS_DIR}/init_sdkman.sh"

# Install Java 8
sdk install java "$JAVA8"
# Install Java 11
sdk install java "$JAVA11"
# Install Java 17
sdk install java "$JAVA17"
# Install maven
sdk install maven "$MAVEN_VERSION"
# Install ant
sdk install ant "$ANT_VERSION"
# Install gradle
sdk install gradle "$GRADLE_VERSION"

unzip "${SDKMAN_DIR}/candidates/java/${JAVA8}/src.zip" -d "${SDKMAN_DIR}/candidates/java/${JAVA8}/src"