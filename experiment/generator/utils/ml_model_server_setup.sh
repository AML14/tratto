#!/bin/bash
# This script start the server for the experiments executed with tratto

# Get current directory
current_dir=$(realpath "$(dirname "$BASH_SOURCE")")
# Setup global variables
source "${current_dir}/global_variables.sh"

# Define local variables
token_classes_model="${ML_MODEL_DIR}/checkpoints/token-classes-checkpoint/pytorch_model.bin"
token_values_model="${ML_MODEL_DIR}/checkpoints/token-values-checkpoint/pytorch_model.bin"

echo "[1] Install requirements."
# Install python requirements
bash "${UTILS_DIR}/install_python_requirements.sh" "${ML_MODEL_DIR}" "ml-model"

echo "[2] Setup models."
# Download token classes model
if [ ! -e "$token_classes_model" ]; then
  echo "[2.1] Token class model not found."
  echo "Downloading token class model..."
  token_classes_model_dir=$(dirname "$token_classes_model")
  mkdir -p "$token_classes_model_dir"
  #curl -o "$token_classes_model" "https://drive.switch.ch/index.php/s/ClE8ttjDtWzSJ13/download"
  curl -o "$token_classes_model" "https://drive.switch.ch/index.php/s/vkuzseJ7YeiO1Vh/download"
  echo "Download complete!"
fi
# Download token values model
if [ ! -e "$token_values_model" ]; then
  echo "[2.1] Token values model not found."
  echo "Downloading token values model..."
  token_values_model_dir=$(dirname "$token_values_model")
  mkdir -p "$token_values_model_dir"
  #curl -o "$token_values_model" "https://drive.switch.ch/index.php/s/tYTmJtdZ0sgBa6Z/download"
  curl -o "$token_values_model" "https://drive.switch.ch/index.php/s/IXNa0fMFTfzVp7V/download"
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