#!/bin/bash
# This script generates a test suite using EvoSuite and saves the output to
# "output/evosuite-tests".

# ----- Check environment variables -----

if [ ! -d "${JAVA8_HOME}" ] ; then
  echo "JAVA8_HOME is not set."
  exit 1
fi

if [ ! -d "${JAVA8_HOME}" ] ; then
  echo "JAVA8_HOME is not set to an existing directory: ${JAVA8_HOME}"
  exit 1
fi
