#!/bin/bash
# This script install the requirements to run the experiments
# using an isolated version of sdkman. Sdkman let to easily
# manage different versions of Java JDK required by the tools
# used for the experiments. The script assumes zip package
# is installed in your machine.

# Set experiment as root directory
ROOT_DIR=$(dirname "$(dirname "$(dirname "$(realpath "$0")")")")
RESOURCES_DIR="${ROOT_DIR}/generator/resources"
UTILS_DIR="${ROOT_DIR}/generator/utils"
SDKMAN_DIR="${RESOURCES_DIR}/sdkman"

# Download sdkman
bash "${UTILS_DIR}/sdkman_install.sh"
source "${UTILS_DIR}/sdkman_init.sh"

# Install Java 8
sdk install java 8.0.382-amzn
# Install Java 17
sdk install java 17.0.8-oracle
# Install maven
sdk install maven 3.9.4
# Install ant
sdk install ant 1.10.13