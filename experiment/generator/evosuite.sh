#!/bin/bash
# This script generates a test suite using EvoSuite and saves the output to
# "output/evosuite-tests".

# Set and check environment variables
SCRIPTDIR="$(cd "$(dirname "$0")" && pwd -P)"
. "${SCRIPTDIR}${SEPARATOR}utils${SEPARATOR}env.sh"
