# Sdkman Java versions
JAVA8="8.0.382-amzn"
JAVA17="17.0.8-oracle"

# Directories
ROOT_DIR=$(dirname "$(dirname "$(realpath "$(dirname "$BASH_SOURCE")")")")
RESOURCES_DIR="${ROOT_DIR}/generator/resources"
UTILS_DIR="${ROOT_DIR}/generator/utils"
DEFECTS4J_DIR="${RESOURCES_DIR}/defects4j"
BUILDERS_DIR="${DEFECTS4J_DIR}/builders"
SDKMAN_DIR="${RESOURCES_DIR}/sdkman"
OUTPUT_DIR="${ROOT_DIR}/output"

# Files
D4J_PROJECTS_BUGS="${RESOURCES_DIR}/defects4j/d4j_projects_modified_classes.csv"
EVOSUITE_JAR="${RESOURCES_DIR}/evosuite-1.0.6.jar"
