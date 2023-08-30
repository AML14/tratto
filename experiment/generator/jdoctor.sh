# This script generates a list of axiomatic oracles using JDoctor.
# Should output a list of OracleOutput.
RESOURCES_DIR="$(dirname "$0")/resources"

# ----- SETUP -----
# Set this path to the "Home" directory in a local JDK8.
JAVA8_HOME="$RESOURCES_DIR/jdk1.8.0_381.jdk/Contents/Home"

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
OUTPUT_DIR="$RESOURCES_DIR/../../output"
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
java -jar "$RESOURCES_DIR/experiment.jar" "jdoctor" "generate_oracle_outputs" "$OUTPUT_DIR/jdoctor/jdoctor_output.json"
mv "$RESOURCES_DIR/../output/jdoctor/oracle_outputs.json" "$OUTPUT_DIR/jdoctor"
rm -r "$RESOURCES_DIR/../output"
