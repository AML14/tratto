# Sdkman Java versions
JAVA8="8.0.392-amzn"
JAVA11="11.0.21-amzn"
JAVA17="17.0.8-oracle"
# Sdkman Maven version
MAVEN_VERSION="3.9.4"
# Sdkman Ant version
ANT_VERSION="1.10.13"
# Sdkman Gradle version
GRADLE_VERSION="8.3"

# Directories
ROOT_DIR=$(dirname "$(dirname "$(realpath "$(dirname "$BASH_SOURCE")")")")
GENERATOR_DIR="${ROOT_DIR}/generator"
RESOURCES_DIR="${GENERATOR_DIR}/resources"
UTILS_DIR="${GENERATOR_DIR}/utils"
ML_MODEL_DIR="${ROOT_DIR}/../ml-model"
JDOCTOR_PROJECT_DIR="${RESOURCES_DIR}/jdoctor"
TRATTO_PROJECT_DIR="${ROOT_DIR}/../tratto"
TOGA_PROJECT_DIR="${RESOURCES_DIR}/toga"
DEFECTS4J_DIR="${RESOURCES_DIR}/defects4j"
SDKMAN_DIR="${RESOURCES_DIR}/sdkman"
OUTPUT_DIR="${ROOT_DIR}/output"

# JARs & Files
D4J_PROJECTS_BUGS="${RESOURCES_DIR}/defects4j/modified_classes.csv"
EXPERIMENT_JAR="${ROOT_DIR}/generator/resources/experiment.jar"
EVOSUITE_JAR="${RESOURCES_DIR}/evosuite-1.0.6.jar"
JDOCTOR_JAR="${RESOURCES_DIR}/jdoctor.jar"
TRATTO_JAR="${RESOURCES_DIR}/tratto.jar"

# Server
SERVER_PORT=5050

# Github repositories
JDOCTOR_GITHUB_REPO="https://github.com/albertogoffi/toradocu.git"
TOGA_GITHUB_REPO="https://github.com/microsoft/toga.git"

# Links to resources
TOGA_ASSERTION_MODEL_LINK="https://drive.google.com/u/0/uc?id=1TvZMlpXeN3DQUwwgOhlCRkn5-v1l_ZSK&export=download"
TOGA_EXCEPTION_MODEL_LINK="https://drive.google.com/u/0/uc?id=1JeRod7jtR8CdWTB_wn-HRNMgtgoRFpc7&export=download"
TRATTO_TOKEN_CLASSES_LINK="https://drive.switch.ch/index.php/s/vkuzseJ7YeiO1Vh/download"
TRATTO_TOKEN_VALUES_LINK="https://drive.switch.ch/index.php/s/IXNa0fMFTfzVp7V/download"
JDOCTOR_JAR_LINK="https://drive.switch.ch/index.php/s/dAkxslN83PvXhLo/download"
