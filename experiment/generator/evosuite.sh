# This script generates a test suite using EvoSuite and saves the output to
# experiment/output/evosuite-test.

# SETUP
# Set this path to the "Home" directory in the local JDK8.
JAVA8_HOME="$(pwd)/resources/jdk1.8.0_381.jdk/Contents/Home"

# setup output path
EXPERIMENT_PATH=$(dirname "$(pwd)")
TEST_OUTPUT_PATH="$EXPERIMENT_PATH/output/evosuite-test"
mkdir -p "$TEST_OUTPUT_PATH"
# check if jdk8 is configured
if [ ! -d "$JAVA8_HOME" ]; then
  echo -e "Error: JDK8 directory \"$JAVA8_HOME\" does not exist.
See \"Setup\" in the experiment README.md for further details."
  exit 1
fi

# setup evosuite commands
EVOSUITE="java -jar $(pwd)/resources/evosuite-1.0.6.jar"
(export JAVA_HOME=$JAVA8_HOME ; $EVOSUITE)
