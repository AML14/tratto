#!/bin/bash
# This script generates a list of axiomatic oracles using JDoctor. Saves the
# output to "output/jdoctor/oracle" as a list of OracleOutput records.

# Set and check environment variables
SCRIPT_DIR="$(cd "$(dirname "${0}")" && pwd -P)"
# shellcheck source=generator/utils/env.sh
. "${SCRIPT_DIR}${SEPARATOR}utils${SEPARATOR}env.sh"
