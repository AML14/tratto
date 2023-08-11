# This script generates a test suite using EvoSuite and saves the output to
# experiment/output/evosuite-test.

# ----- SETUP -----
# Set this path to the "Home" directory in a local JDK8.
JAVA8_HOME="$(pwd)/resources/jdk1.8.0_381.jdk/Contents/Home"


# argument checker
if [ ! $# -eq 2 ]; then
  echo -e "Incorrect number of arguments. Expected 2 arguments, but got $#".
  exit 1
fi
if [ ! -d "$2" ]; then
  echo -e "The system binaries path \"$2\" does not exist."
  exit 1
fi
# jdk8 checker
if [ ! -d "$JAVA8_HOME" ]; then
  echo -e "Error: JDK8 Home directory \"$JAVA8_HOME\" does not exist."
  exit 1
fi


# setup variables
TARGET_CLASS="$1"  # fully-qualified name of target class
TARGET_DIR="$2"  # directory of binary files of the system under test
OUTPUT_DIR="$(pwd)/../output/evosuite-test"
EVOSUITE="java -jar $(pwd)/evosuite-1.0.6.jar"
mkdir -p "$OUTPUT_DIR"
# use EvoSuite to generate tests
(export JAVA_HOME=$JAVA8_HOME ; $EVOSUITE -class "$TARGET_CLASS" -projectCP "$TARGET_DIR")
