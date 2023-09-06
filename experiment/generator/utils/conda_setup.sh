#!/bin/bash
# Set separator depending on the operating system
# '/' for linux-based operating systems
# '\' for windows users
if [[ $(uname) == "Darwin" || $(uname) == "Linux" ]]; then
    SEPARATOR="/"
else
    SEPARATOR="\\"
fi

CURRENT_DIR=$(pwd)
UTILS_DIR="${CURRENT_DIR}${SEPARATOR}generator${SEPARATOR}utils"
CONDA_CHOICE="Y"
PROJECT_NAME="${1}"
CONDA_DEFAULT_PROJECT_ENV_NAME="${PROJECT_NAME}_project_conda_env"
CONDA_ENV_NAME="$CONDA_DEFAULT_PROJECT_ENV_NAME"

# Check if a Conda environment is activated
if [ -n "$CONDA_DEFAULT_ENV" ]; then
    echo "Conda environment '$CONDA_DEFAULT_ENV' is activated." >&2
    # Check if user want to run the experiment using the current conda environment
    CHOICE=$(bash "${UTILS_DIR}${SEPARATOR}y_n.sh" "Do you want to run ${PROJECT_NAME} with the current conda environment? (Y/n): ")
    if [ "$CHOICE" == "Y" ]; then
      CONDA_ENV_NAME="$CONDA_DEFAULT_ENV"
    else
      CHOICE=$(bash "${UTILS_DIR}${SEPARATOR}y_n.sh" "Do you want to run ${PROJECT_NAME} with another conda environment? (Y/n): ")
      if [ "$CHOICE" == "N" ]; then
        conda deactivate
      fi
    fi
else
    CHOICE=$(bash "${UTILS_DIR}${SEPARATOR}y_n.sh" "Do you want to run ${PROJECT_NAME} with a conda environment? (Y/n): ")
fi

if [ "$CONDA_CHOICE" == "Y" ]; then
  # Check conda is installed in the machine, otherwise exit the script
  if command -v conda &> /dev/null; then
      CONDA_ENV_LIST=$(conda env list)
  else
      echo "Conda is not installed. Please install conda before to run the script with conda option." >&2
      exit 1
  fi
  if [ "$CONDA_ENV_NAME" == "$CONDA_DEFAULT_PROJECT_ENV_NAME" ]; then
    read -rp "Prompt the name of the conda environment you want to use (if the environment does not exist it will be created. Default ${CONDA_DEFAULT_PROJECT_ENV_NAME}): " USER_INPUT
    # Check if the user input is empty (just Enter) and set it to 'Y'
    if [ -z "$USER_INPUT" ]; then
      echo "Conda environment not provided. Using default conda environment name: ${CONDA_ENV_NAME}" >&2
    else
      CONDA_ENV_NAME=$USER_INPUT
    fi
  fi
  # Check if conda environment already exists, otherwise generate it
  if [[ $CONDA_ENV_LIST =~ $CONDA_ENV_NAME ]]; then
    echo "Conda environment '${CONDA_ENV_NAME}' found." >&2
  else
    echo "Conda environment '${CONDA_ENV_NAME}' does not exist." >&2
    echo "Creating conda environment..." >&2
    conda create --name "$CONDA_ENV_NAME"
  fi
  echo "${CONDA_ENV_NAME}"
else
  echo ""
fi