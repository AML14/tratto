#!/bin/bash
# This script generates a list of axiomatic oracles using JDoctor. Saves the
# output to "output/jdoctor/oracle" as a list of OracleOutput records.

# ----- SETUP -----
# After adding the local JDK8 to the generator/resources directory...
# Set this field to the directory name.
JDK8_NAME="jdk-1.8.jdk"


# Exit from the program if any error is arose from another bash script or another command executed within this bash script.
set -e

TARGET_CLASS="${1}"  # fully-qualified name of target class
SRC_DIR="${2}"  # project source directory
CLASS_DIR="${3}"  # path to binary files of the system under test
# argument check
if [ ! $# -eq 3 ]; then
  echo -e "jdoctor.sh: Incorrect number of arguments. Expected 3 arguments, but got ${#}".
  exit 1
elif [ ! -d "${2}" ]; then
  echo -e "jdoctor.sh: The source directory does not exist: ${2}"
  exit 1
elif [ ! -d "${3}" ]; then
  echo -e "jdoctor.sh: The system binaries path does not exist: ${3}"
  exit 1
fi

SCRIPTDIR="$(cd "$(dirname "$0")" && pwd -P)"
. "${SCRIPTDIR}${SEPARATOR}utils${SEPARATOR}env.sh"

# ROOT_DIR is "experiment/".
ROOT_DIR=$(dirname "$(dirname "$(realpath "${0}")")")
RESOURCES_DIR="${ROOT_DIR}${SEPARATOR}generator${SEPARATOR}resources"

OUTPUT_DIR="${ROOT_DIR}${SEPARATOR}output"
JDOCTOR="${JAVA8_BIN} -jar ${RESOURCES_DIR}${SEPARATOR}toradocu-1.0-all.jar"
mkdir -p "${OUTPUT_DIR}${SEPARATOR}jdoctor${SEPARATOR}output"
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
java -jar "${RESOURCES_DIR}${SEPARATOR}experiment.jar" "generate_oracle_output" "jdoctor" ""  # empty argument for compatibility
