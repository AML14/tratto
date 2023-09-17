#!/bin/bash
# This script generates a list of axiomatic oracles using JDoctor. Saves the
# output to "output/jdoctor/oracle" as a list of OracleOutput records.


# Exit from the program if any error is arose from another bash script or another command executed within this bash script.
set -e

TARGET_CLASS="${1}"  # fully-qualified name of target class
SRC_DIR="${2}"  # project source directory
CLASS_DIR="${3}"  # path to binary files of the system under test

# argument check
if [ ! $# -eq 3 ]; then
  echo -e "jdoctor.sh: Expected 3 arguments, but got ${#}".
  exit 1
elif [ ! -d "${SRC_DIR}" ]; then
  echo -e "jdoctor.sh: The source directory \"${SRC_DIR}\" does not exist."
  exit 1
elif [ ! -d "${CLASS_DIR}" ]; then
  echo -e "jdoctor.sh: The system binaries path \"${CLASS_DIR}\" does not exist."
  exit 1
fi

SCRIPTDIR="$(cd "$(dirname "$0")" && pwd -P)"
. "${SCRIPTDIR}${SEPARATOR}utils${SEPARATOR}env.sh"

OUTPUT_DIR="${ROOT_DIR}${SEPARATOR}output"
JDOCTOR="${JAVA8_PROGRAM} -jar ${RESOURCES_DIR}${SEPARATOR}toradocu-1.0-all.jar"
mkdir -p "${OUTPUT_DIR}${SEPARATOR}jdoctor${SEPARATOR}output"
# use JDoctor to generate oracles
(
  # shellcheck disable=SC2153 # JAVA8_HOME, not a misspelling of JAVA_HOME.
  export JAVA_HOME=${JAVA8_HOME};
  $JDOCTOR \
  --target-class "${TARGET_CLASS}" \
  --source-dir "${SRC_DIR}" \
  --class-dir "${CLASS_DIR}" \
  --condition-translator-output "${OUTPUT_DIR}${SEPARATOR}jdoctor${SEPARATOR}output${SEPARATOR}jdoctor_output.json"  # location of JDoctor output in JSON format
)

# convert JDoctor JSON to OracleOutput
java -jar "${RESOURCES_DIR}${SEPARATOR}experiment.jar" "generate_oracle_output" "jdoctor" ""  # empty argument for compatibility
