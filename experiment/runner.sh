#!/bin/bash

# Set and check environment variables
SCRIPTDIR="$(cd "$(dirname "$0")" && pwd -P)"
. "${SCRIPTDIR}${SEPARATOR}generator${SEPARATOR}utils${SEPARATOR}env.sh"

# get global path variables
ROOT_DIR="$(dirname "$(realpath "${0}")")"
RESOURCES_DIR="${ROOT_DIR}/generator/resources"
OUTPUT_DIR="${ROOT_DIR}/output"
JAVA8_PROGRAM="${JAVA8_HOME}/bin/java"
JAVA8_JAVAC="${JAVA8_HOME}/bin/javac"
# get given variables
TOG="jdoctor"
TARGET_CLASS=${2}
SRC_DIR=$(realpath "${3}")
BIN_DIR=$(realpath "${4}")
TEST_DIR="${OUTPUT_DIR}/tog-tests/${TOG}"
# get project root directory
IFS='/' read -ra SRC_ARR <<< "${SRC_DIR}"
IFS='/' read -ra BIN_ARR <<< "${BIN_DIR}"
PROJECT_DIR=""
for i in "${!SRC_ARR[@]}" "${!BIN_ARR[@]}"; do
  if [ "${SRC_ARR[i]}" != "${BIN_ARR[i]}" ]; then
    break
  fi
  PROJECT_DIR="${PROJECT_DIR}/${SRC_ARR[i]}"
done
PROJECT_DIR="${PROJECT_DIR#/}"

# copy tests and utilities into target project
cp -r "${TEST_DIR}" "${PROJECT_DIR}/evosuite-tests"
cp "${RESOURCES_DIR}/evosuite-1.0.6.jar" "${PROJECT_DIR}"
cp "${RESOURCES_DIR}/evosuite-standalone-runtime-1.0.6.jar" "${PROJECT_DIR}"
cd "${PROJECT_DIR}" || exit 1

# run tests
(export JAVA_HOME=${JAVA8_HOME};
mvn dependency:copy-dependencies
export CLASSPATH=target/classes:evosuite-standalone-runtime-1.0.6.jar:evosuite-tests:target/dependency/junit-4.12.jar:target/dependency/hamcrest-core-1.3.jar
# compile all tests
find "${PROJECT_DIR}/evosuite-tests" -type f -name "*.java" > java_tests.txt
while read -r java_test; do
  $JAVA8_JAVAC "${java_test}"
done < java_tests.txt
# run JUnit
TEST_FAILS="test_fails.txt"
while read -r java_test; do
  test_class="${java_test#${PROJECT_DIR}/evosuite-tests/}"  # remove project prefix
  test_class="${test_class%.java}"  # remove java suffix
  test_class="${test_class//\//.}"  # convert to package name
  # do not run scaffolding files
  if [[ "${test_class}" == *ESTest ]]; then
    junit_output=$($JAVA8_PROGRAM org.junit.runner.JUnitCore "${test_class}")
    if [[ ${junit_output} == *"FAILURES!!!"* ]]; then
      # get names of all failing tests
      while IFS= read -r line; do
        regex="^[0-9]+\) test[0-9]+\(.*\)$"
        if [[ "${line}" =~ ${regex} ]]; then
          echo "${line}" >> "${TEST_FAILS}"
        fi
      done <<< "${junit_output}"
    fi
  fi
done < java_tests.txt;
mv "${TEST_FAILS}" "${OUTPUT_DIR}/tog-tests/${TOG}"
)

# generate TestOutput record
EXPERIMENT="java -jar ${RESOURCES_DIR}/experiment.jar"
$EXPERIMENT "${TOG}" "generate_test_output" "${TARGET_CLASS}" "${SRC_DIR}" "${BIN_DIR}" "${ROOT_DIR}"

# cleanup
rm -f "java_tests.txt"
rm -r "evosuite-tests"
rm -f "evosuite-1.0.6.jar"
rm -f "evosuite-standalone-runtime-1.0.6.jar"
