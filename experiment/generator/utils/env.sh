#!/bin/bash
# This script checks and sets environment variables.

if [[ $(uname) == "Darwin" || $(uname) == "Linux" ]]; then
  export SEPARATOR="/"
else
  export SEPARATOR="\\"
fi

if [ ! -d "${JAVA8_HOME}" ] ; then
  echo "JAVA8_HOME environment variable is not set."
  exit 1
fi

export JAVA8_PROGRAM="${JAVA8_HOME}${SEPARATOR}bin${SEPARATOR}java"

if [ ! -f "${JAVA8_PROGRAM}" ]; then
  echo -e "JDK8 java binary \"${JAVA8_PROGRAM}\" does not exist."
  exit 1
fi
