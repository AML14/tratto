# Sdkman Java versions
JAVA8="8.0.382-amzn"
JAVA17="17.0.8-oracle"

# Directories
ROOT_DIR=$(dirname "$(dirname "$(realpath "$(dirname "$BASH_SOURCE")")")")
RESOURCES_DIR="${ROOT_DIR}/generator/resources"
UTILS_DIR="${ROOT_DIR}/generator/utils"
ML_MODEL_DIR="${ROOT_DIR}/../ml-model"
TRATTO_PROJECT_DIR="${ROOT_DIR}/../tratto"
TOGA_PROJECT_DIR="${RESOURCES_DIR}/toga"
DEFECTS4J_DIR="${RESOURCES_DIR}/defects4j"
SDKMAN_DIR="${RESOURCES_DIR}/sdkman"
OUTPUT_DIR="${ROOT_DIR}/output"

# JARs & Files
D4J_PROJECTS_BUGS="${RESOURCES_DIR}/defects4j/modified_classes.csv"
EXPERIMENT_JAR="${ROOT_DIR}/generator/resources/experiment.jar"
EVOSUITE_JAR="${RESOURCES_DIR}/evosuite-1.0.6.jar"
JDOCTOR_JAR="${RESOURCES_DIR}/toradocu-1.0-all.jar"
TRATTO_JAR="${RESOURCES_DIR}/tratto.jar"

# Server
SERVER_PORT=5050
