# ----- SETUP -----
# Clone the Defects4J repository and follow the setup instructions.
# Then, set this field to the relative path of Defects4J from the working directory.
DEFECTS4J_PATH="$(pwd)/../../defects4j"
# Add a local JDK8 to the "./generator/resources" directory.
# Then, set this field to the directory name.
JDK8_NAME="jdk-1.8.jdk"

# configure Defects4J and setup variables
# we provide the following example invocation:
#     "bash defects4j.sh Csv 1"
DEFECTS4J_PATH=$(realpath "${DEFECTS4J_PATH}")
export PATH=${PATH}:${DEFECTS4J_PATH}"/framework/bin"
PROJECT="${1}"
BUG_ID="${2}"
ROOT_DIR="$(dirname "$(realpath "$0")")"
RESOURCES_DIR="${ROOT_DIR}/generator/resources"
JAVA8_HOME="${RESOURCES_DIR}/${JDK8_NAME}/Contents/Home"

echo "[1] Gathering project info for bug #${BUG_ID} in ${PROJECT}"
defects4j info -p "${PROJECT}" -b "${BUG_ID}"

echo "[2] Cloning buggy and fixed project versions"
BUGGY_PATH="${DEFECTS4J_PATH}/tmp/${PROJECT}_${BUG_ID}_buggy"
FIXED_PATH="${DEFECTS4J_PATH}/tmp/${PROJECT}_${BUG_ID}_fixed"
defects4j checkout -p "${PROJECT}" -v "${BUG_ID}b" -w "${BUGGY_PATH}"
defects4j checkout -p "${PROJECT}" -v "${BUG_ID}f" -w "${FIXED_PATH}"
echo "Saved buggy version to ${BUGGY_PATH}"
echo "Saved fixed version to ${FIXED_PATH}"

echo "[3] Compiling both project versions"
(export JAVA_HOME=$JAVA8_HOME;
cd "${BUGGY_PATH}" || exit 1
defects4j compile
cd "${FIXED_PATH}" || exit 1
defects4j compile
# get experiment.sh and runner.sh analysis commands
MODIFIED_CLASSES_PATH="${DEFECTS4J_PATH}/framework/projects/${PROJECT}/modified_classes/${BUG_ID}.src"
echo "To generate tests using experiment.sh, run the following commands:"
while IFS= read -r fqn; do
  echo "experiment.sh [tog] ${fqn} ${BUGGY_PATH}/src/main/java ${BUGGY_PATH}/target/classes"
done < "${MODIFIED_CLASSES_PATH}"
#echo "To compare the tests against the fixed versions, run the following commands:"
#while IFS= read -r fqn; do
#  echo "${fqn}"
#done < "${MODIFIED_CLASSES_PATH}"
)

echo "[4] Setup complete"
