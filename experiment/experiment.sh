#!/bin/bash
# This script generates a test suite for a class using Evosuite.
# Then, for every supported TOG, it creates a variant of teh test suite that uses the TOG's oracle.
# An invocation follows the format,
#     "bash experiment.sh [target-class] [src-dir] [bin-dir] [jar]"
# This script adds the following sub-directories to the output directory, `experiment/output/`:
#     - "evosuite-tests": a test suite generated by EvoSuite
#     - "evosuite-simple-tests": EvoSuite tests split with one assertion per
#                                test
#     - "evosuite-prefixes": EvoSuite simple tests with all assertions removed
#     - "[tog]/input": preprocessed TOG input (if necessary)
#     - "[tog]/oracle": OracleOutputs generated by TOG
#     - "tog-tests/[tog]": a test suite made by inserting TOG oracles into the
#                          EvoSuite prefixes

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
project_jar=${5:-""}
qualifiers="${target_class%.*}"
#evosuite_output="${ROOT_DIR}/output/evosuite-tests/${qualifiers//.//}"   #!!!


# Check given arguments
if [ ! -d "${src_dir}" ]; then
  echo -e "The project source directory \"${src_dir}\" does not exist."
  exit 1
elif [ ! -d "${bin_dir}" ]; then
  echo -e "The system binaries path \"${bin_dir}\" does not exist."
  exit 1
elif [ "$tog" == "tratto" ] && [ ! -f "${project_jar}" ]; then
  echo -e "The project jar file \"${project_jar}\" does not exist."
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

# Generate EvoSuite tests
echo "[1] Generate EvoSuite tests for class ${target_class}"
#{
bash ./generator/evosuite.sh "${target_class}" "${bin_dir}"
#} > /dev/null 2>&1

# Switch to Java 17
sdk use java "$JAVA17"
# Generate EvoSuite prefixes
java -jar "${EXPERIMENT_JAR}" "remove_oracles" "${target_class}"
# Generate oracles using TOG
if [ "${tog}" == "jdoctor" ]; then
  bash ./generator/jdoctor.sh "${target_class}" "${src_dir}" "${bin_dir}"
  oracle_output="${ROOT_DIR}/output/jdoctor/oracle_outputs.json"
elif [ "${tog}" == "toga" ]; then
  bash ./generator/toga.sh "${target_class}" "${src_dir}" #"${evosuite_output}"
  oracle_output="${ROOT_DIR}/output/toga/oracle/oracle_outputs.json"
elif [ "${tog}" == "tratto" ]; then
  bash ./generator/tratto.sh "${target_class}" "${src_dir}" "${project_jar}"
  oracle_output="${ROOT_DIR}/output/tratto/oracle/oracle_outputs.json"
fi
#cp "${oracle_output}" "${ROOT_DIR}/output/${tog}-oracles.json" #!!!
# insert oracles into EvoSuite prefixes
#echo "[7] Insert oracles in test prefixes"
#java -jar "${EXPERIMENT_JAR}" "insert_oracles" "${tog}" "${bin_dir}" "${oracle_output}"
#echo "[8] Running tests and generating test output"
#bash ./runner.sh "$tog" "$target_class" "$src_dir" "$bin_dir"
#echo "[9] Experiment complete!"
