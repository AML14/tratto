#!/bin/bash
# This script runs test suite using EvoSuite and check the tests work

# Get current directory
CURRENT_DIR=$(realpath "$(dirname "$BASH_SOURCE")")

# Setup global variables
if ! [ -v ROOT_DIR ]; then
    source "${current_dir}/utils/global_variables.sh"
else
    echo "Global variables already defined."
fi

tog="$1"
project_dir="$2"
target_class="$3"  # Fully-qualified name of target class
target_dir="$4"    # Directory of binary files of the system under test

# setup classpath
export "CLASSPATH=${target_dir}:${project_dir}/lib/*:${project_dir}/togTests/${tog}"
# Run tests
java org.junit.runner.JUnitCore "${target_class}"