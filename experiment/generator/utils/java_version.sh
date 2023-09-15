#!/bin/bash
# This script check the presence of the JDK to use in a script

# ----- SETUP -----
# After adding the local JDK to the generator/resources directory...
# Set this field to the directory name.
JDK_NAME="${1}"


# Exit from the program if any error is arose from another bash script or another command executed within this bash script.
set -e
if [[ $(uname) == "Darwin" || $(uname) == "Linux" ]]; then
    SEPARATOR="/"
else
    SEPARATOR="\\"
fi

# find JDK directory
ROOT_DIR=$(dirname "$(dirname "$(dirname "$(realpath "${0}")")")")
RESOURCES_DIR="${ROOT_DIR}${SEPARATOR}generator${SEPARATOR}resources"
JDK_DEFAULT_PATH=$(find "${RESOURCES_DIR}" -type d -name 'jdk-*' -print -quit)
JAVA_BIN=""
if [ -n "${JDK_DEFAULT_PATH}" ]; then
  JDK_PATH=$(dirname "${JDK_DEFAULT_PATH}")"${SEPARATOR}${JDK_NAME}"
  if [ "${JDK_DEFAULT_PATH}" != "${JDK_PATH}" ]; then
    mv "${JDK_DEFAULT_PATH}" "${JDK_PATH}"
  fi
  if [ "$(uname)" == "Linux" ]; then
      JAVA_HOME="${JDK_PATH}"
  else
      JAVA_HOME="${JDK_PATH}${SEPARATOR}Contents${SEPARATOR}Home"
  fi
  JAVA_BIN="${JAVA_HOME}${SEPARATOR}bin${SEPARATOR}java"
fi

if [ ! -e "${JAVA_BIN}" ] || [ -z "${JAVA_BIN}" ]; then
  echo "(JAVA SETUP) Unable to find a jdk directory. Please provide the complete path to the Java JDK binary ([path_to_jdk]${SEPARATOR}Contents${SEPARATOR}Home${SEPARATOR}bin${SEPARATOR}java or [path_to_jdk]${SEPARATOR}bin${SEPARATOR}java):"
  read -r USER_INPUT
  JAVA_BIN="${USER_INPUT}"
fi
echo "${JAVA_BIN}"
