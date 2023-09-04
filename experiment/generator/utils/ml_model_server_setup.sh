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

bash "${UTILS_DIR}${SEPARATOR}install_requirements.sh" "${ML_MODEL_DIR}" "ml-model"

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