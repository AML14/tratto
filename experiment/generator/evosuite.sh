# This script generates a test suite using EvoSuite and saves the output to
# experiment/output/evosuite-test.

# ----- SETUP -----
# Set this path to the "Home" directory in the local JDK8.
JAVA8_HOME="$(pwd)/resources/jdk1.8.0_381.jdk/Contents/Home"


# check arguments
if [ ! $# -eq 1 ]; then
  echo -e "Incorrect number of arguments to use EvoSuite (must specify input path)."
  exit 1
fi
if [ ! -d "$1" ]; then
  echo -e "Input path \"$1\" does not exist."
  exit 1
fi

# setup output path
INPUT_PATH=$1
EXPERIMENT_PATH=$(dirname "$(pwd)")
OUTPUT_PATH="$EXPERIMENT_PATH/output/evosuite-test"
mkdir -p "$OUTPUT_PATH"
# check if jdk8 is configured
if [ ! -d "$JAVA8_HOME" ]; then
  echo -e "Error: JDK8 directory \"$JAVA8_HOME\" does not exist.
See \"Setup\" in the experiment README.md for further details."
  exit 1
fi

# setup evosuite commands
EVOSUITE="java -jar $(pwd)/resources/evosuite-1.0.6.jar"
# run evosuite with given source path
#(export JAVA_HOME=$JAVA8_HOME ; $EVOSUITE)
