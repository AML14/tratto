#!/bin/bash
# This script generates a list of non-axiomatic oracles using TOGA.
# It should save the output to "output/toga/oracle" as a list of OracleOutput records.

# Set and check environment variables
SCRIPTDIR="$(cd "$(dirname "$0")" && pwd -P)"
. "${SCRIPTDIR}${SEPARATOR}utils${SEPARATOR}env.sh"
