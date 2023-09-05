#!/bin/bash
# Set separator depending on the operating system
# '/' for linux-based operating systems
# '\' for windows users
if [[ $(uname) == "Darwin" || $(uname) == "Linux" ]]; then
    SEPARATOR="/"
else
    SEPARATOR="\\"
fi

ROOT_DIR=$(pwd)
SERVER_PORT="${1:-5050}"
ML_MODEL_DIR="${ROOT_DIR}${SEPARATOR}..${SEPARATOR}ml-model"
TOKEN_CLASSES_MODEL="${ML_MODEL_DIR}${SEPARATOR}checkpoints${SEPARATOR}token-classes-checkpoint${SEPARATOR}pytorch_model.bin"
TOKEN_VALUES_MODEL="${ML_MODEL_DIR}${SEPARATOR}checkpoints${SEPARATOR}token-values-checkpoint${SEPARATOR}pytorch_model.bin"
RESOURCES_DIR="${ROOT_DIR}${SEPARATOR}generator${SEPARATOR}resources"
UTILS_DIR="${ROOT_DIR}${SEPARATOR}generator${SEPARATOR}utils"

#CONDA_ENV_NAME=$(bash ".${SEPARATOR}generator${SEPARATOR}utils${SEPARATOR}conda_setup.sh" "ml-model")
#if [ ! "$CONDA_ENV_NAME" == "" ]; then
#  if [[ $(uname) == "Darwin" || $(uname) == "Linux" ]]; then
#    source activate "$CONDA_ENV_NAME"
#  else
#    activate "$CONDA_ENV_NAME"
#  fi
#fi

echo "[1] Install requirements."
bash "${UTILS_DIR}${SEPARATOR}install_requirements.sh" "${ML_MODEL_DIR}" "ml-model"

echo "[2] Setup models."
if [ ! -e "$TOKEN_CLASSES_MODEL" ]; then
  echo "[2.1] Token class model not found."
  echo "      Downloading token class model..."
  TOKEN_CLASSES_MODEL_DIR=$(dirname "$TOKEN_CLASSES_MODEL")
  mkdir -p "$TOKEN_CLASSES_MODEL_DIR"
  curl -o "$TOKEN_CLASSES_MODEL" "https://drive.switch.ch/index.php/s/ClE8ttjDtWzSJ13/download"
  echo "      Download complete!"
fi

if [ ! -e "$TOKEN_VALUES_MODEL" ]; then
  echo "[2.1] Token values model not found."
  echo "      Downloading token values model..."
  TOKEN_VALUES_MODEL_DIR=$(dirname "$TOKEN_VALUES_MODEL")
  mkdir -p "$TOKEN_VALUES_MODEL_DIR"
  curl -o "$TOKEN_VALUES_MODEL" "https://drive.switch.ch/index.php/s/tYTmJtdZ0sgBa6Z/download"
  echo "      Download complete!"
fi

while true; do
  if nc -z -v -w 1 localhost "$SERVER_PORT" 2>&1 | grep -q "succeeded"; then
      echo "Port $SERVER_PORT is occupied."
      CHOICE=$(bash "${UTILS_DIR}${SEPARATOR}y_n.sh" "The port is occupied. Do you want to run the processes on another port? (Y/n): ")
      if [ "$CHOICE" == "Y" ]; then
        read -rp "Provide the number of the port you want to use: " USER_INPUT
        SERVER_PORT="$USER_INPUT"
      else
        echo "Program stopped."
        exit 1
      fi
  else
      echo "Starting server on port ${SERVER_PORT}..."
      break
  fi
done

cd "$ML_MODEL_DIR"
bash ".${SEPARATOR}server.sh" $SERVER_PORT
cd "$ROOT_DIR"