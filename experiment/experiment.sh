# This script manages the end-to-end experimental analysis.
# To run the experiment, the user provides a specific TOG and a source file.

# check if the number of arguments is correct
if [ ! $# -eq 4 ]; then
  echo -e "Incorrect number of arguments. Expected 4 arguments, but got $#."
  exit 1
fi

if [[ $(uname) == "Darwin" || $(uname) == "Linux" ]]; then
    SEPARATOR="/"
else
    SEPARATOR="\\"
fi

ROOT_DIR=$(pwd)
TOG=$1
TARGET_CLASS=$2
SRC_DIR=$3
BIN_DIR=$4
QUALIFIERS="${TARGET_CLASS%.*}"
EVOSUITE_OUTPUT="${ROOT_DIR}${SEPARATOR}output${SEPARATOR}evosuite-tests${SEPARATOR}${QUALIFIERS//./$SEPARATOR}"
EXPERIMENT="java -jar ./generator/resources/experiment.jar"

# check if given directories exist
if [ ! -d "$SRC_DIR" ]; then
  echo -e "The project source directory \"$SRC_DIR\" does not exist."
  exit 1
elif [ ! -d "$BIN_DIR" ]; then
  echo -e "The system binaries path \"$BIN_DIR\" does not exist."
  exit 1
fi
# check if given TOG is supported
found=0
VALID_TOG=("jdoctor" "toga" "tratto")
for option in "${VALID_TOG[@]}"; do
  if [ "$option" = "$TOG" ]; then
    found=1
    break
  fi
done
if [ ! $found -eq 1 ]; then
  echo -e "The given TOG \"$1\" is not supported. Must be one of: \"jdoctor\", \"toga\", or \"tratto\"."
  exit 1
fi

# generate EvoSuite tests
bash ./generator/evosuite.sh "${TARGET_CLASS}" "${BIN_DIR}"
# generate EvoSuite prefixes
$EXPERIMENT "$TOG" "remove_oracles" "$EVOSUITE_OUTPUT" "$TARGET_CLASS"
# generate oracles using TOG
if [ "${TOG}" == "jdoctor" ]; then
  bash ./generator/jdoctor.sh "${TARGET_CLASS}" "${SRC_DIR}" "${BIN_DIR}"
  ORACLE_OUTPUT="./output/jdoctor/oracle/oracle_outputs.json"
elif [ "${TOG}" == "toga" ]; then
  bash ./generator/toga.sh "${TARGET_CLASS}" "${SRC_DIR}" "${EVOSUITE_OUTPUT}"
  ORACLE_OUTPUT="./output/toga/oracle/oracle_outputs.json"
elif [ "${TOG}" == "tratto" ]; then
  bash ./generator/tratto.sh "${TARGET_CLASS}" "${SRC_DIR}"
  ORACLE_OUTPUT=""
fi
# insert oracles into EvoSuite prefixes
$EXPERIMENT "$TOG" "insert_oracles" "$ORACLE_OUTPUT"
