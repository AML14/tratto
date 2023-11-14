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

# Install Java 17
sdk install java 17.0.8-oracle
# Install maven
sdk install maven 3.9.4
# Install ant
sdk install ant 1.10.13
# Install gradle
sdk install gradle 8.3