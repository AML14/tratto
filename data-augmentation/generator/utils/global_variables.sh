# Sdkman Java versions
JAVA8="8.0.382-amzn"
JAVA17="17.0.8-oracle"

# Directories
ROOT_DIR=$(dirname "$(dirname "$(realpath "$(dirname "$BASH_SOURCE")")")")
GENERATOR_DIR="${ROOT_DIR}/generator"
RESOURCES_DIR="${GENERATOR_DIR}/resources"
UTILS_DIR="${GENERATOR_DIR}/utils"
SDKMAN_DIR="${RESOURCES_DIR}/sdkman"
DATA_AUGMENTATION_PROJECT_DIR="${ROOT_DIR}"

# JARs & Files
DATA_AUGMENTATION_JAR="${RESOURCES_DIR}/data_augmentation.jar"
INPUT_PROJECTS_CSV_FILE="${ROOT_DIR}/src/main/java/star/tratto/data/repos/input_projects.csv"
INPUT_PROJECTS_JSON_FILE="${ROOT_DIR}/src/main/java/star/tratto/data/repos/input_projects.json"