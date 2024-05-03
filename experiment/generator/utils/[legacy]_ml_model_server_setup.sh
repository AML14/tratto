#!/bin/bash
# This script start the server for the experiments executed with tratto

# Get current directory
current_dir=$(realpath "$(dirname "$BASH_SOURCE")")
# Setup global variables
source "${current_dir}/global_variables.sh"

# Define local variables
oracles_model="${ML_MODEL_DIR}/checkpoints/oracles-checkpoint/pytorch_model.bin"
token_classes_model="${ML_MODEL_DIR}/checkpoints/token-classes-checkpoint/pytorch_model.bin"
token_values_model="${ML_MODEL_DIR}/checkpoints/token-values-checkpoint/pytorch_model.bin"

echo "[1] Install requirements."
# Install python requirements
bash "${UTILS_DIR}/install_python_requirements.sh" "${ML_MODEL_DIR}" "requirements.txt"
bash "${UTILS_DIR}/install_python_requirements.sh" "${ML_MODEL_DIR}" "additional_requirements.txt"

echo "[2] Setup models."
# Download oracles model
if [ ! -e "$oracles_model" ]; then
  echo "[2.1] Oracles model not found."
  echo "Downloading oracles model..."
  oracles_model_dir=$(dirname "$oracles_model")
  mkdir -p "$oracles_model_dir"
  wget "$TRATTO_ORACLES_LINK" -O "$oracles_model"
  echo "Download complete!"
fi
# Download token classes model
if [ ! -e "$token_classes_model" ]; then
  echo "[2.1] Token class model not found."
  echo "Downloading token class model..."
  token_classes_model_dir=$(dirname "$token_classes_model")
  mkdir -p "$token_classes_model_dir"
  wget "$TRATTO_TOKEN_CLASSES_LINK" -O "$token_classes_model"
  echo "Download complete!"
fi
# Download token values model
if [ ! -e "$token_values_model" ]; then
  echo "[2.1] Token values model not found."
  echo "Downloading token values model..."
  token_values_model_dir=$(dirname "$token_values_model")
  mkdir -p "$token_values_model_dir"
  wget "$TRATTO_TOKEN_VALUES_LINK" -O "$token_values_model"
  echo "Download complete!"
fi
# Check that the server port is free
if nc -z -v -w 1 localhost "$SERVER_PORT" 2>&1 | grep -q "succeeded"; then
    echo "Port $SERVER_PORT is occupied. Kill the processes running on port $SERVER_PORT or change the server port in the generator/utils/global_variables.sh bash file."
    exit 1
fi
# Start the server
cd "$ML_MODEL_DIR"
bash "./server.sh" "$SERVER_PORT"
cd "$ROOT_DIR"