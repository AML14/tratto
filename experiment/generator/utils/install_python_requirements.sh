#!/bin/bash
# This script installs all the requirements of a given python project

# Get current directory
current_dir=$(realpath "$(dirname "$BASH_SOURCE")")
# Setup global variables
source "${current_dir}/global_variables.sh"

project_dir="$1"
requirements_file="${project_dir}/requirements.txt"
packages=($(grep -Eo '^[^=#]+' "$requirements_file"))
# Get a list of installed packages using pip freeze
installed_packages=$(pip freeze)
install_packages="N"

# Loop through each package and check if it's installed
for PACKAGE in "${packages[@]}"; do
    found=$(echo "$installed_packages" | grep "^${PACKAGE}==")
    if [ -z "$found" ]; then
        echo "Detected python packages not installed."
        install_packages="Y"
        break
    fi
done
# Install the packages not installed
if [ "$install_packages" == "Y" ]; then
  choice=$(bash "${UTILS_DIR}/y_n.sh" "To proceed it is necessary to install the python packages from the requirements.txt of the ${2} repository. Proceed? (Y/n): ")
  if [ ! "$choice" == "Y" ]; then
    echo "[ERROR] - Impossible to proceed without authorization to install python packages."
    exit 1
  fi
  echo "Installing packages..."
  cd "$project_dir"
  #pip install -q -r requirements.txt
  pip install -r requirements.txt
  cd "$ROOT_DIR"
fi
echo "Installation complete!"