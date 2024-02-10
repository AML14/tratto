#!/bin/bash
# This script setup the experiments woth toga

# Get current directory
current_dir=$(realpath "$(dirname "$BASH_SOURCE")")
# Setup global variables
source "${current_dir}/global_variables.sh"

# Define local variables
toga_assertions_dir="${TOGA_PROJECT_DIR}/model/assertions/pretrained"
toga_exceptions_dir="${TOGA_PROJECT_DIR}/model/exceptions/pretrained"

# Check the script has been from root of the experiment repository directory
if [ ! -d "$RESOURCES_DIR" ]; then
  echo "Unknown directory. The script expect to be executed from root of the experiment repository directory, and that the path ./generator/resources exists."
  exit 1
fi

echo "[2.1] Check TOGA project exists."
# Check if toga project has already been downloaded, otherwise download and setup the project
if [ ! -d "${RESOURCES_DIR}/toga" ]; then
  cd "$RESOURCES_DIR"
  echo "TOGA project not found."
  echo "Downloading TOGA project into './generator/resources'..."
  #{
  git clone "$TOGA_GITHUB_REPO" toga
  #} > /dev/null 2>&1
  cd "$ROOT_DIR"
fi

echo "Checking python packages."
rm "${TOGA_PROJECT_DIR}/requirements.txt"
cp "${RESOURCES_DIR}/toga_requirements.txt" "${TOGA_PROJECT_DIR}/requirements.txt"
bash "${UTILS_DIR}/install_python_requirements.sh" "$TOGA_PROJECT_DIR" "requirements.txt"

# Download models
if [ ! -e "${toga_assertions_dir}/pytorch_model.bin" ]; then
  echo "Assertion model not found."
  echo "Downloading toga assertions model..."
  if ! pip show "gdown" > /dev/null 2>&1; then
    #CHOICE=$(bash "${UTILS_DIR}/y_n.sh" "To proceed it is necessary to install gdown python package to download the model from a google drive link. Proceed? (Y/n): ")
    #if [ ! "$CHOICE" == "Y" ]; then
    #  echo "ERROR - Impossible to proceed without authorization to install gdown package. Terminate program."
    #  exit 1
    #fi
    sudo pip install gdown
    # Check if a Conda environment is activated
    if [ -z "$CONDA_DEFAULT_ENV" ]; then
      # Determine the location where gdown is installed
      GDOWN_PATH=$(which gdown)
      # Add the directory containing gdown to PATH
      GDOWN_DIR=$(dirname "$GDOWN_PATH")
      if [[ ":$PATH:" != *":$GDOWN_DIR:"* ]]; then
         export PATH="$PATH:$GDOWN_DIR"
      fi
    fi
  fi
  #{
  gdown "$TOGA_ASSERTION_MODEL_LINK" -O "${toga_assertions_dir}/pytorch_model.bin"
  #} > /dev/null 2>&1
  echo "Download of TOGA assertion model complete!"
fi
if [ ! -e "${toga_exceptions_dir}/pytorch_model.bin" ]; then
  echo "Exceptions model not found."
  echo "Downloading toga exceptions model..."
  if ! pip show "gdown" > /dev/null 2>&1; then
    #CHOICE=$(bash "${UTILS_DIR}/y_n.sh" "To proceed it is necessary to install gdown python package to download the model from a google drive link. Proceed? (Y/n): ")
    #if [ ! "$CHOICE" == "Y" ]; then
    #  echo "ERROR - Impossible to proceed without authorization to install gdown package. Terminate program."
    #  exit 1
    #fi
    sudo pip install gdown
    # Check if a Conda environment is activated
    if [ -z "$CONDA_DEFAULT_ENV" ]; then
      # Determine the location where gdown is installed
      GDOWN_PATH=$(which gdown)
      # Add the directory containing gdown to PATH
      GDOWN_DIR=$(dirname "$GDOWN_PATH")
      if [[ ":$PATH:" != *":$GDOWN_DIR:"* ]]; then
        export PATH="$PATH:$GDOWN_DIR"
      fi
    fi
  fi
  #{
  gdown "$TOGA_EXCEPTION_MODEL_LINK" -O "${toga_exceptions_dir}/pytorch_model.bin"
  #} > /dev/null 2>&1
  echo "Download of TOGA exceptions model complete!"
fi