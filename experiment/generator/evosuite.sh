# This script generates a test suite using EvoSuite and saves the output to
# experiment/output/evosuite-test.

# setup output path
EXPERIMENT_PATH=$(dirname "$(pwd)")
TEST_OUTPUT_PATH="$EXPERIMENT_PATH/output/evosuite-test"
mkdir -p "$TEST_OUTPUT_PATH"
# check if jdk8 is configured
JAVA8_HOME="$(pwd)/resources/jdk1.8.0_3281.jdk/Contents/Home"
if [ ! -d "$JAVA8_HOME" ]; then
  echo -e "Error: JDK8 directory does not exist. See \"Setup\" in."
  exit 1
fi

# setup evosuite commands
EVOSUITE="java -jar $(pwd)/resources/evosuite-1.0.6.jar"
(export JAVA_HOME=$JAVA8_HOME ; $EVOSUITE)
