# This script generates a list of axiomatic oracles using JDoctor.
# Should output a list of OracleOutput.
RESOURCES_DIR="$(dirname "$0")/resources"

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

# argument check
if [ ! $# -eq 3 ]; then
  echo -e "(JDOCTOR) Incorrect number of arguments. Expected 3 arguments, but got $#".
  exit 1
elif [ ! -d "$2" ]; then
  echo -e "(JDOCTOR) The source directory \"$2\" does not exist."
  exit 1
elif [ ! -d "$3" ]; then
  echo -e "(JDOCTOR) The system binaries path \"$3\" does not exist."
  exit 1
elif [ ! -d "$JAVA8_HOME" ]; then
  echo -e "(JDOCTOR) Error: JDK8 Home directory \"$JAVA8_HOME\" does not exist."
  exit 1
fi

TARGET_CLASS="$1"  # fully-qualified name of target class
SRC_DIR="$2"  # project source directory
CLASS_DIR="$3"  # path to binary files of the system under test
OUTPUT_DIR="$ROOT_DIR/output"
JDOCTOR="$JAVA8_HOME/bin/java -jar $RESOURCES_DIR/toradocu-1.0-all.jar"
mkdir -p "$OUTPUT_DIR"
mkdir -p "$OUTPUT_DIR/jdoctor"
# use JDoctor to generate oracles
(export JAVA_HOME=$JAVA8_HOME ;
$JDOCTOR \
--target-class "$TARGET_CLASS" \
--source-dir "$SRC_DIR" \
--class-dir "$CLASS_DIR" \
--condition-translator-output "$OUTPUT_DIR/jdoctor/jdoctor_output.json"  # location of JDoctor output in JSON format
)
# convert JDoctor JSON to OracleOutput
java -jar "$RESOURCES_DIR/experiment.jar" "jdoctor" "generate_oracle_outputs" "$OUTPUT_DIR/jdoctor/jdoctor_output.json"
