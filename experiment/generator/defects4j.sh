# This script perform the experiments for Defect4J projects

# ----- SETUP -----
# After adding the local JDK8 to the generator/resources directory...
# Set this field to the directory name.
JDK8_NAME="jdk-1.8.jdk"

# Exit from the program if any error is arose from another bash script or another command executed within this bash script.
set -e

if [[ $(uname) == "Darwin" || $(uname) == "Linux" ]]; then
    SEPARATOR="/"
else
    SEPARATOR="\\"
fi

# find JDK8 directory
ROOT_DIR=$(dirname "$(dirname "$(realpath "$0")")")
RESOURCES_DIR="$ROOT_DIR${SEPARATOR}generator${SEPARATOR}resources"
JDK_DEFAULT_PATH=$(find "$RESOURCES_DIR" -type d -name 'jdk*.jdk' -print -quit)

defects4j checkout -p Lang -v 1b -w /tmp/lang_1_buggy
