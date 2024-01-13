#!/bin/bash
# This script generates oracles for all bugs in Defects4J for a given TOG.
current_dir=$(realpath "$(dirname "${BASH_SOURCE[0]}")")
source "${current_dir}/utils/global_variables.sh"
source "${UTILS_DIR}/init_sdkman.sh"
sdk use java "${JAVA8}"

# Switch to Java 17
#sdk use java "${JAVA8}"
#
## Generate oracles using TOG
#if [ "${tog}" == "jdoctor" ]; then
#  bash ./generator/jdoctor.sh "${target_class}" "${src_dir}" "${bin_dir}"
#  oracle_output="${ROOT_DIR}/output/jdoctor/oracle_outputs.json"
#elif [ "${tog}" == "toga" ]; then
#  bash ./generator/toga.sh "${target_class}" "${src_dir}" "${evosuite_output}"
#  oracle_output="${ROOT_DIR}/output/toga/oracle/oracle_outputs.json"
#elif [ "${tog}" == "tratto" ]; then
#  bash ./generator/tratto.sh "${target_class}" "${src_dir}" "${project_jar}"
#  oracle_output="${ROOT_DIR}/output/tratto/oracle/oracle_outputs.json"
#fi
#cp "${oracle_output}" "${ROOT_DIR}/output/${tog}-oracles.json"
## insert oracles into EvoSuite prefixes
#java -jar "${EXPERIMENT_JAR}" "insert_oracles" "${tog}" "${bin_dir}" "${oracle_output}"
