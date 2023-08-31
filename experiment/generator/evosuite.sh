# This script generates a test suite using EvoSuite and saves the output to
# experiment/output/evosuite-test.

# ----- SETUP -----
# After adding the local JDK8 to the generator/resources directory...
# Set this field to the directory name.
JDK8_NAME="jdk-1.8.jdk"

# find JDK8 directory
ROOT_DIR=$(dirname "$(dirname "$(realpath "$0")")")
RESOURCES_DIR="$ROOT_DIR/generator/resources"
JDK_DEFAULT_PATH=$(find "$RESOURCES_DIR" -type d -name 'jdk*.jdk' -print -quit)
if [ -n "$JDK_DEFAULT_PATH" ]; then
  JDK_PATH=$(dirname "$JDK_DEFAULT_PATH")"/$JDK8_NAME"
  if [ "$JDK_DEFAULT_PATH" != "$JDK_PATH" ]; then
    mv "$JDK_DEFAULT_PATH" "$JDK_PATH"
  fi
  JAVA8_HOME="$JDK_PATH/Contents/Home"
else
  echo "(EVOSUITE) Unable to find a jdk directory. Please provide the complete path to the Java 8 JDK directory:"
  read -r JAVA8_FOLDER
  JAVA8_HOME="$JAVA8_FOLDER/Contents/Home"
fi

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
OUTPUT_DIR="$ROOT_DIR/output"
EVOSUITE="$JAVA8_HOME/bin/java -jar $RESOURCES_DIR/evosuite-1.0.6.jar"

# generate tests using EvoSuite
(export JAVA_HOME=$JAVA8_HOME; $EVOSUITE -class "$TARGET_CLASS" -projectCP "$TARGET_DIR")
# move to output directory
CURRENT_DIR=$(realpath .)
rm -r "$CURRENT_DIR/evosuite-report"  # delete statistics
rm -r "$OUTPUT_DIR/evosuite-tests"  # overwrites previous output
mkdir -p "$OUTPUT_DIR"
mkdir -p "$OUTPUT_DIR/evosuite-tests"
mv -f "$CURRENT_DIR/evosuite-tests" "$OUTPUT_DIR"
