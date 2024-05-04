#!/bin/bash
# This script installs all the requirements of a given python project

# Get current directory
current_dir=$(realpath "$(dirname "$BASH_SOURCE")")
# Setup global variables
source "${current_dir}/global_variables.sh"

project_dir="$1"
requirements_filename="${2:-requirements.txt}"
requirements_file_path="${project_dir}/${requirements_filename}"
packages=($(grep -Eo '^[^=#]+' "$requirements_file_path"))
# Get a list of installed packages using pip freeze
installed_packages=$(pip freeze)
install_packages="N"

# Loop through each package and check if it's installed
for package_name in "${packages[@]}"; do
    dashed_package_name=$(echo "$package_name" | sed 's/_/-/g')
    found=$(echo "$installed_packages" | grep "^${package_name}\|^${dashed_package_name}")
    if [ -z "$found" ]; then
        echo "Detected python packages not installed."
        install_packages="Y"
        break
    fi
done
# Install the packages not installed
if [ "$install_packages" == "Y" ]; then
  echo "Installing packages..."
  cd "$project_dir"
  pip install -r "$requirements_filename"
  cd "$ROOT_DIR"
  echo "Installation complete!"
fi