#!/bin/bash
# This script generates a list of axiomatic oracles using JDoctor. Saves the
# output to "output/jdoctor/oracle" as a list of OracleOutput records.

# ----- Check environment variables -----

if [ ! -d "${JAVA8_HOME}" ] ; then
  echo "JAVA8_HOME is not set."
  exit 1
fi

if [ ! -d "${JAVA8_HOME}" ] ; then
  echo "JAVA8_HOME is not set to an existing directory: ${JAVA8_HOME}"
  exit 1
fi
