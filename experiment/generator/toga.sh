#!/bin/bash
# This script generates a list of non-axiomatic oracles using TOGA.
# Should output a list of OracleOutput.

# Get current directory
current_dir=$(realpath "$(dirname "$BASH_SOURCE")")
source "${current_dir}/utils/global_variables.sh"

# Define local variables
fully_qualified_name="$1"
src_path="$2"
EVOSUITE_OUTPUT="$3"
TOGA_PROJECT_DIR="${ROOT_DIR}/generator/resources/toga"
TOGA_INPUT_DIR="${ROOT_DIR}/output/toga/input"
TOGA_OUTPUT_DIR="${ROOT_DIR}/output/toga/output"

echo "[2] Setup TOGA project"
bash "./generator/utils/toga_setup.sh"

echo "[3] Generate test prefixes from EvoSuite test suite."
java -jar "generator/resources/experiment.jar" toga remove_oracles "$EVOSUITE_OUTPUT" "$fully_qualified_name"
echo "[4] Generate TOGA input files."
java -jar "generator/resources/experiment.jar" toga generate_tog_inputs "$src_path" "$fully_qualified_name"

echo "[5] Generate oracles with TOGA."
cd "$TOGA_PROJECT_DIR"

python3 toga.py "${TOGA_INPUT_DIR}/toga_input.csv" "${TOGA_INPUT_DIR}/toga_metadata.csv"

cd "$ROOT_DIR"

if [ ! -d "$TOGA_OUTPUT_DIR" ]; then
    # If it doesn't exist, create the folder
    mkdir -p "$TOGA_OUTPUT_DIR"
    echo "Folder created: $TOGA_OUTPUT_DIR"
fi

mv "${TOGA_PROJECT_DIR}/oracle_preds.csv" "${TOGA_OUTPUT_DIR}"

echo "[6] Map oracles generated with TOGA into OracleOutputs."
java -jar "generator/resources/experiment.jar" toga generate_oracle_outputs "$src_path"

echo "[7] Insert oracles on test prefixes."