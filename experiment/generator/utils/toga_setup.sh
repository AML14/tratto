#!/bin/bash
# Set separator depending on the operating system
# '/' for linux-based operating systems
# '\' for windows users
if [[ $(uname) == "Darwin" || $(uname) == "Linux" ]]; then
    SEPARATOR="/"
else
    SEPARATOR="\\"
fi

#CONDA_ENV_NAME=$(bash ".${SEPARATOR}generator${SEPARATOR}utils${SEPARATOR}conda_setup.sh" "toga")
#if [ ! "$CONDA_ENV_NAME" == "" ]; then
#  if [[ $(uname) == "Darwin" || $(uname) == "Linux" ]]; then
#    source activate "$CONDA_ENV_NAME"
#  else
#    activate "$CONDA_ENV_NAME"
#  fi
#fi

ROOT_DIR=$(pwd)
RESOURCES_DIR="${ROOT_DIR}${SEPARATOR}generator${SEPARATOR}resources"
TOGA_ASSERTIONS_DIR="${RESOURCES_DIR}${SEPARATOR}toga${SEPARATOR}model${SEPARATOR}assertions${SEPARATOR}pretrained"
TOGA_EXCEPTIONS_DIR="${RESOURCES_DIR}${SEPARATOR}toga${SEPARATOR}model${SEPARATOR}exceptions${SEPARATOR}pretrained"
UTILS_DIR="${ROOT_DIR}${SEPARATOR}generator${SEPARATOR}utils"

# Check the script has been from root of the experiment repository directory
if [ ! -d $RESOURCES_DIR ]; then
  echo "Unknown directory. The script expect to be executed from root of the experiment repository directory, and that the path .${SEPARATOR}generator${SEPARATOR}resources exists."
  exit 1
fi

echo "    [2.1] Check TOGA project exists."
# Check if toga project has already been downloaded, otherwise download and setup the project
if [ ! -d "${RESOURCES_DIR}${SEPARATOR}toga" ]; then
  cd "$RESOURCES_DIR"
  echo "          TOGA project not found."
  echo "          Downloading TOGA project into '.${SEPARATOR}generator${SEPARATOR}resources'..."
  #{
  git clone https://github.com/microsoft/toga.git
  #} > /dev/null 2>&1
  cd "$ROOT_DIR"

  STARTING_NAME="toga"
  # Find all the folders starting with name toga and sort by date of last modification
  MATCHING_DIRECTORIES=()
  while IFS= read -r -d '' entry; do
      MATCHING_DIRECTORIES=("$entry")
  done < <(find "${RESOURCES_DIR}" -type d -name "${STARTING_NAME}*" -print0 | sort -z -n -r)
  # Check the number of matching directories found
  NUM_MATCHING_DIRECTORIES="${#MATCHING_DIRECTORIES[@]}"
  if [ "$NUM_MATCHING_DIRECTORIES" -eq 0 ]; then
      echo "          ERROR - TOGA project not found after cloning. Terminate program."
      exit 1
  fi

  TOGA_PROJECT_NAME="${MATCHING_DIRECTORIES[0]}"
  TOGA_PROJECT_DIR="${TOGA_PROJECT_NAME#* }"

  # Rename downloaded project with default name 'toga'
  if [ ! "${TOGA_PROJECT_DIR}" == "${RESOURCES_DIR}${SEPARATOR}toga" ]; then
    mv "${TOGA_PROJECT_DIR}" "${RESOURCES_DIR}${SEPARATOR}toga"
  fi
fi

echo "          Checking python packages."
rm "${RESOURCES_DIR}${SEPARATOR}toga${SEPARATOR}requirements.txt"
cp "${RESOURCES_DIR}${SEPARATOR}toga_requirements.txt" "${RESOURCES_DIR}${SEPARATOR}toga${SEPARATOR}requirements.txt"
bash "${UTILS_DIR}${SEPARATOR}install_requirements.sh" "${RESOURCES_DIR}${SEPARATOR}toga" "toga"

# Download models
if [ ! -e "${TOGA_ASSERTIONS_DIR}${SEPARATOR}pytorch_model.bin" ]; then
  echo "          Assertion model not found."
  echo "          Downloading toga assertions model..."
  if ! pip show "gdown" > /dev/null 2>&1; then
    CHOICE=$(bash "${UTILS_DIR}${SEPARATOR}y_n.sh" "          To proceed it is necessary to install gdown python package to download the model from a google drive link. Proceed? (Y/n): ")
    if [ ! "$CHOICE" == "Y" ]; then
      echo "          ERROR - Impossible to proceed without authorization to install gdown package. Terminate program."
      exit 1
    fi
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
  gdown "https://drive.google.com/u/0/uc?id=1TvZMlpXeN3DQUwwgOhlCRkn5-v1l_ZSK&export=download" -O "${TOGA_ASSERTIONS_DIR}${SEPARATOR}pytorch_model.bin"
  #} > /dev/null 2>&1
  echo "          Download of TOGA assertion model complete!"
fi
if [ ! -e "${TOGA_EXCEPTIONS_DIR}${SEPARATOR}pytorch_model.bin" ]; then
  echo "Exceptions model not found."
  echo "Downloading toga exceptions model..."
  if ! pip show "gdown" > /dev/null 2>&1; then
    CHOICE=$(bash "${UTILS_DIR}${SEPARATOR}y_n.sh" "          To proceed it is necessary to install gdown python package to download the model from a google drive link. Proceed? (Y/n): ")
    if [ ! "$CHOICE" == "Y" ]; then
      echo "          ERROR - Impossible to proceed without authorization to install gdown package. Terminate program."
      exit 1
    fi
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
  gdown "https://drive.google.com/u/0/uc?id=1JeRod7jtR8CdWTB_wn-HRNMgtgoRFpc7&export=download" -O "${TOGA_EXCEPTIONS_DIR}${SEPARATOR}pytorch_model.bin"
  #} > /dev/null 2>&1
  echo "          Download of TOGA exceptions model complete!"
fi