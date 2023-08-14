# This script generates a test suite using EvoSuite and saves the output to
# experiment/output/evosuite-test.
RESOURCES_DIR="$(dirname "$0")/resources"

# ----- SETUP -----
# Set this path to the "Home" directory in a local JDK8.
JAVA8_HOME="$RESOURCES_DIR/jdk1.8.0_381.jdk/Contents/Home"


# argument and setup check
if [ ! $# -eq 2 ]; then
  echo -e "(EVOSUITE) Incorrect number of arguments. Expected 2 arguments, but got $#".
  exit 1
elif [ ! -d "$2" ]; then
  echo -e "(EVOSUITE) The system binaries path \"$2\" does not exist."
  exit 1
elif [ ! -d "$JAVA8_HOME" ]; then
  echo -e "(EVOSUITE) Error: JDK8 Home directory \"$JAVA8_HOME\" does not exist."
  exit 1
fi


# setup variables
TARGET_CLASS="$1"  # fully-qualified name of target class
TARGET_DIR="$2"  # directory of binary files of the system under test
OUTPUT_DIR="$RESOURCES_DIR/../../output"
EVOSUITE="java -jar $RESOURCES_DIR/evosuite-1.0.6.jar"
mkdir -p "$OUTPUT_DIR"
# use EvoSuite to generate tests
(export JAVA_HOME=$JAVA8_HOME ; $EVOSUITE -class "$TARGET_CLASS" -projectCP "$TARGET_DIR")
mv "./evosuite-tests" "$OUTPUT_DIR"
rm -r "./evosuite-report"
