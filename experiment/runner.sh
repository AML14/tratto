#!/bin/bash

# ----- SETUP -----
# After adding the local JDK8 to the generator/resources directory,
# set this field to the directory name.
JDK8_NAME="jdk-1.8.jdk"

ROOT_DIR="$(dirname "$(realpath "$0")")"
RESOURCES_DIR="$ROOT_DIR/generator/resources"
OUTPUT_DIR="$ROOT_DIR/output"
JAVA8_HOME="$RESOURCES_DIR/$JDK8_NAME/Contents/Home"
JAVA8_BIN="$JAVA8_HOME/bin/java"
JAVA8_C="$JAVA8_HOME/bin/javac"
TOG=$1
SRC_DIR=$(realpath "$2")
BIN_DIR=$(realpath "$3")
TEST_DIR="$OUTPUT_DIR/tog-tests/$TOG"

IFS='/' read -ra SRC_ARR <<< "$SRC_DIR"
IFS='/' read -ra BIN_ARR <<< "$BIN_DIR"
PROJECT_DIR=""
for i in "${!SRC_ARR[@]}" "${!BIN_ARR[@]}"; do
  if [ "${SRC_ARR[i]}" != "${BIN_ARR[i]}" ]; then
    break
  fi
  PROJECT_DIR="${PROJECT_DIR}/${SRC_ARR[i]}"
done
PROJECT_DIR="${PROJECT_DIR#/}"

echo "$PROJECT_DIR"

#cp -r "$TEST_DIR" "$PROJECT_DIR"
#cd "$PROJECT_DIR" || exit 1

#(export JAVA_HOME=$JAVA8_HOME;
#mvn dependency:copy-dependencies
#export CLASSPATH="target/classes:evosuite-standalone-runtime-1.0.6.jar:evosuite-tests:target/dependency/junit-4.12.jar:target/dependency/hamcrest-core-1.3.jar"
#$JAVA8_C "$PROJECT_DIR/evosuite-tests/tutorial/"*".java"
#$JAVA8_BIN org.junit.runner.JUnitCore tutorial.Stack_ESTest
#)
