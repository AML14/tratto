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
PROJECT_DIR="$1"
UTILS_DIR="${ROOT_DIR}${SEPARATOR}generator${SEPARATOR}utils"
REQUIREMENTS_FILE="${PROJECT_DIR}${SEPARATOR}requirements.txt"
PACKAGES=($(grep -Eo '^[^=#]+' "$REQUIREMENTS_FILE"))
# Get a list of installed packages using pip freeze
INSTALLED_PACKAGES=$(pip freeze)
INSTALL_PACKAGES="N"

# Loop through each package and check if it's installed
for PACKAGE in "${PACKAGES[@]}"; do
    FOUND=$(echo "$INSTALLED_PACKAGES" | grep "^${PACKAGE}==")
    if [ -z "$FOUND" ]; then
        echo "                Detected python packages not installed."
        INSTALL_PACKAGES="Y"
        break
    fi
done

if [ "$INSTALL_PACKAGES" == "Y" ]; then
  CHOICE=$(bash "${UTILS_DIR}${SEPARATOR}y_n.sh" "                To proceed it is necessary to install the python packages from the requirements.txt of the ${2} repository. Proceed? (Y/n): ")
  if [ ! "$CHOICE" == "Y" ]; then
    echo "                ERROR - Impossible to proceed without authorization to install python packages."
    exit 1
  fi
  echo "                Installing packages..."
  cd "$PROJECT_DIR"
  #pip install -q -r requirements.txt
  pip install -r requirements.txt
  cd "$ROOT_DIR"
fi
echo "                Installation complete!"