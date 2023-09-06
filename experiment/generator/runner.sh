#!/bin/bash

JDK8_NAME="jdk-8.jdk"
ROOT_DIR=$(dirname "$(dirname "$(realpath "$0")")")
RESOURCES_DIR="$ROOT_DIR/src/main/resources"
JAVA8_HOME="$RESOURCES_DIR/$JDK8_NAME/Contents/Home"
JAVA8_BIN="$JAVA8_HOME/bin/java"
JAVA8_C="$JAVA8_HOME/bin/javac"

(export JAVA_HOME=$JAVA8_HOME;
mvn dependency:copy-dependencies
export CLASSPATH="target/classes:evosuite-standalone-runtime-1.0.6.jar:evosuite-tests:target/dependency/junit-4.12.jar:target/dependency/hamcrest-core-1.3.jar"
$JAVA8_C "$ROOT_DIR/evosuite-tests/tutorial/"*".java"
echo "$ROOT_DIR/evosuite-tests/tutorial/"*".java"
$JAVA8_BIN org.junit.runner.JUnitCore tutorial.Stack_ESTest
)
