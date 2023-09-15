#!/bin/bash
# This script generates a list of axiomatic oracles using JDoctor. Saves the
# output to "output/jdoctor/oracle" as a list of OracleOutput records.

# ----- SETUP -----
# After adding the local JDK8 to the generator/resources directory...
# Set this field to the directory name.
JDK8_NAME="jdk-1.8.jdk"


# Exit from the program if any error is arose from another bash script or another command executed within this bash script.
set -e

if [[ $(uname) == "Darwin" || $(uname) == "Linux" ]]; then
    SEPARATOR="/"
else
    SEPARATOR="\\"
fi

# find JDK8 directory
ROOT_DIR=$(dirname "$(dirname "$(realpath "${0}")")")
RESOURCES_DIR="${ROOT_DIR}${SEPARATOR}generator${SEPARATOR}resources"
JAVA8_BIN=$(bash "${ROOT_DIR}${SEPARATOR}generator${SEPARATOR}utils${SEPARATOR}java_version.sh" "${JDK8_NAME}" "JDOCTOR")

# argument check
if [ ! $# -eq 3 ]; then
  echo -e "(JDOCTOR) Incorrect number of arguments. Expected 3 arguments, but got ${#}".
  exit 1
elif [ ! -d "${2}" ]; then
  echo -e "(JDOCTOR) The source directory \"${2}\" does not exist."
  exit 1
elif [ ! -d "${3}" ]; then
  echo -e "(JDOCTOR) The system binaries path \"${3}\" does not exist."
  exit 1
elif [ ! -f "${JAVA8_BIN}" ]; then
  echo -e "(JDOCTOR) Error: JDK8 java binary \"${JAVA8_BIN}\" does not exist."
  exit 1
fi

TARGET_CLASS="${1}"  # fully-qualified name of target class
SRC_DIR="${2}"  # project source directory
CLASS_DIR="${3}"  # path to binary files of the system under test
OUTPUT_DIR="${ROOT_DIR}${SEPARATOR}output"
JDOCTOR="${JAVA8_BIN} -jar ${RESOURCES_DIR}${SEPARATOR}toradocu-1.0-all.jar"
mkdir -p "${OUTPUT_DIR}"
mkdir -p "${OUTPUT_DIR}${SEPARATOR}jdoctor"
# use JDoctor to generate oracles
(
  export JAVA_HOME=${JAVA8_BIN};
  $JDOCTOR \
  --target-class "${TARGET_CLASS}" \
  --source-dir "${SRC_DIR}" \
  --class-dir "${CLASS_DIR}" \
  --condition-translator-output "${OUTPUT_DIR}${SEPARATOR}jdoctor${SEPARATOR}output${SEPARATOR}jdoctor_output.json"  # location of JDoctor output in JSON format
)

# convert JDoctor JSON to OracleOutput
java -jar "${RESOURCES_DIR}${SEPARATOR}experiment.jar" "jdoctor" "generate_oracle_outputs" ""  # empty argument for compatibility
