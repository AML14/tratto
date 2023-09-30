#!/bin/bash
# This script generates oracles using a given TOG and inserts them into
# pre-generated EvoSuite prefixes.

# Get current directory
# shellcheck disable=SC2128
current_dir=$(realpath "$(dirname "${BASH_SOURCE}")")
# Setup global variables
source "${current_dir}/generator/utils/global_variables.sh"

# Define local variables
tog=${1}
target_class=${2}
src_dir=${3}
bin_dir=${4}
project_jar=${5}
qualifiers="${target_class%.*}"
evosuite_prefixes="${ROOT_DIR}/output/evosuite-prefixes/${qualifiers//.//}"

# Check if project files exist
if [ ! -d "${src_dir}" ]; then
  echo -e "The project source directory \"${src_dir}\" does not exist."
  exit 1
elif [ ! -d "${bin_dir}" ]; then
  echo -e "The system binaries path \"${bin_dir}\" does not exist."
  exit 1
elif [ ! -f "${project_jar}" ]; then
  echo -e "The project jar file \"${project_jar}\" does not exist."
  exit 1
fi
# Check if EvoSuite prefixes are generated.
if [ ! -d "${evosuite_prefixes}" ]; then
  echo -e "The EvoSuite prefixes for the target class have not been generated."
  exit 1
fi
# Check if given TOG is supported
found=0
valid_tog=("jdoctor" "toga" "tratto")
for option in "${valid_tog[@]}"; do
  if [ "${option}" = "${tog}" ]; then
    found=1
    break
  fi
done
if [ ! $found -eq 1 ]; then
  echo -e "The given TOG \"${1}\" is not supported. Must be one of: \"jdoctor\", \"toga\", or \"tratto\"."
  exit 1
fi

echo "${evosuite_prefixes}"
exit 1

# Generate experiment JAR if not present
if [ ! -f "${EXPERIMENT_JAR}" ]; then
  mvn clean package -DskipTests
  target_jar=$(find "${ROOT_DIR}/target" -type f -name "experiment*-jar-with-dependencies.jar")
    # Check if a file was found
    if [ -z "${target_jar}" ]; then
      echo "experiment.sh: Unable to build jar for experiment module."
      exit 1
    fi
    sudo mv "${target_jar}" "${RESOURCES_DIR}/experiment.jar"
fi

# Setup sdkman
source "${UTILS_DIR}/init_sdkman.sh" "${SDKMAN_DIR}"

# Switch to Java 17
sdk use java "${JAVA17}"
# Generate oracles using TOG
if [ "${tog}" == "jdoctor" ]; then
  bash ./generator/jdoctor.sh "${target_class}" "${src_dir}" "${bin_dir}"
  oracle_output="${ROOT_DIR}/output/jdoctor/oracle_outputs.json"
elif [ "${tog}" == "toga" ]; then
  bash ./generator/toga.sh "${target_class}" "${src_dir}" "${evosuite_output}"
  oracle_output="${ROOT_DIR}/output/toga/oracle/oracle_outputs.json"
elif [ "${tog}" == "tratto" ]; then
  bash ./generator/tratto.sh "${target_class}" "${src_dir}" "${project_jar}"
  oracle_output="${ROOT_DIR}/output/tratto/oracle/oracle_outputs.json"
fi
cp "${oracle_output}" "${ROOT_DIR}/output/${tog}-oracles.json"
# insert oracles into EvoSuite prefixes
java -jar "${EXPERIMENT_JAR}" "${tog}" "insert_oracles" "${bin_dir}" "${oracle_output}"
