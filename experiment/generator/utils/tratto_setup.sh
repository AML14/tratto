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
SERVER_PORT="${1:-5000}"
ML_MODEL_DIR="${ROOT_DIR}${SEPARATOR}..${SEPARATOR}ml-model"
RESOURCES_DIR="${ROOT_DIR}${SEPARATOR}generator${SEPARATOR}resources"
UTILS_DIR="${ROOT_DIR}${SEPARATOR}generator${SEPARATOR}utils"
TRATTO_JAR_FILE="${RESOURCES_DIR}${SEPARATOR}tratto.jar"
TRATTO_PROJECT_DIR="${ROOT_DIR}${SEPARATOR}..${SEPARATOR}tratto"

if [ ! -e "${TRATTO_JAR_FILE}" ]; then
  echo "[0] Generate experiment jar from java project."
  cd "${TRATTO_PROJECT_DIR}"
  bash scripts/install_dependencies.sh
  mvn clean package -DskipTests
  cd "${ROOT_DIR}"
  TARGET_JAR=$(find "${TRATTO_PROJECT_DIR}${SEPARATOR}target" -type f -name "tratto*-jar-with-dependencies.jar")

  # Check if a file was found
  if [ -z "$TARGET_JAR" ]; then
    echo "Unexpected error: tratto jar not found."
    exit 1
  fi
  mv "$TARGET_JAR" "$RESOURCES_DIR/tratto.jar"
fi


