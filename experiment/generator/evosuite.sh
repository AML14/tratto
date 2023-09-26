#!/bin/bash
# This script generates a test suite using EvoSuite and saves the output to
# "output/evosuite-tests".

# Set and check environment variables
SCRIPT_DIR="$(cd "$(dirname "${0}")" && pwd -P)"
# shellcheck source=generator/utils/env.sh
. "${SCRIPT_DIR}${SEPARATOR}utils${SEPARATOR}env.sh"
