# This script generates a test suite using EvoSuite and saves the output to
# experiment/output/evosuite-test.
RESOURCES_DIR="$(dirname "$0")/resources"

JDK_OLD_PATH=$(find $RESOURCES_DIR -type d -name 'jdk*.jdk' -print -quit)
echo $JDK_OLD_PATH
if [ -n "$JDK_OLD_PATH" ]; then
    # Extract the directory name from the path
    JDK_OLD_NAME=$(basename "$JDK_OLD_PATH")

    # Rename the folder
    JDK_NEW_NAME="jdk-1.8.jdk"
    JDK_NEW_PATH=$(dirname "$JDK_OLD_PATH")"/$JDK_NEW_NAME"

    echo $JDK_OLD_PATH
    echo $JDK_NEW_PATH

    if [ $JDK_OLD_PATH != $JDK_NEW_PATH ]; then
      mv "$JDK_OLD_PATH" "$JDK_NEW_PATH"
    fi
    JAVA8_HOME="$JDK_NEW_PATH/Contents/Home"
else
    echo "No matching folder found. Please provide the complete path to the Java 1.8 JDK folder:"
    read JAVA8_FOLDER
    JAVA8_HOME="${JAVA8_FOLDER}/Contents/Home"
fi

# argument and setup check
if [ ! $# -eq 2 ]; then
  echo -e "(EVOSUITE) Incorrect number of arguments. Expected 2 arguments, but got $#".
  exit 1
elif [ ! -d "$2" ]; then
  echo -e "(EVOSUITE) The system binaries path \"$2\" does not exist."
  exit 1
elif [ ! -d "$JAVA8_HOME" ]; then
  echo -e "(EVOSUITE) Error: JDK8 home directory \"$JAVA8_HOME\" does not exist."
  exit 1
fi

# setup variables
TARGET_CLASS="$1"  # fully-qualified name of target class
TARGET_DIR="$2"  # directory of binary files of the system under test
OUTPUT_DIR="$RESOURCES_DIR/../../output"
EVOSUITE="${JAVA8_HOME}/bin/java -jar $RESOURCES_DIR/evosuite-1.0.6.jar"
mkdir -p "$OUTPUT_DIR"
# use EvoSuite to generate tests
(export JAVA_HOME=$JAVA8_HOME; $EVOSUITE -class "$TARGET_CLASS" -projectCP "$TARGET_DIR")

#mv "./evosuite-tests" "$OUTPUT_DIR"
#rm -r "./evosuite-report"