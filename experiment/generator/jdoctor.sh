#!/bin/bash
# This script generates a list of axiomatic oracles using JDoctor. Saves the
# output to "output/jdoctor/oracle" as a list of OracleOutput records.

# Set and check environment variables
SCRIPTDIR="$(cd "$(dirname "$0")" && pwd -P)"
. "${SCRIPTDIR}${SEPARATOR}utils${SEPARATOR}env.sh"
