# This script generates a test suite using EvoSuite and saves the output to
# experiment/output/evosuite-test.

# setup output path
EXPERIMENT_PATH=$(dirname "$(pwd)")
TEST_OUTPUT_PATH="$EXPERIMENT_PATH/output/evosuite-test"
mkdir -p "$TEST_OUTPUT_PATH"
