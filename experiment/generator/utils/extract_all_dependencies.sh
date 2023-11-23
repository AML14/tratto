#!/bin/bash
# This script perform the experiments for Defect4J projects

# Get current directory
current_dir=$(realpath "$(dirname "${BASH_SOURCE[@]}")")
# Setup global variables
source "${current_dir}/global_variables.sh"

# Setup sdkman
source "${UTILS_DIR}/init_sdkman.sh"

# Scope
scope="${1:-generate_oracle}"

# Clone defects4jprefix project
if [ ! -d "$DEFECTS4J_DIR/defects4jprefix" ]; then
    git clone https://github.com/ezackr/defects4jprefix.git "$DEFECTS4J_DIR/defects4jprefix"
fi

# Read the CSV file line by line and split into project_id, bug_id, and modified_classes fields
while IFS=, read -r project_id bug_id modified_classes; do
    echo "Processing project ${project_id} - ${bug_id} - ${modified_classes}"
    # Clean modified_classes string from undesired white-spaces/line-breaks introduced with the CSV parsing
    modified_classes="${modified_classes//[$'\t\r\n ']/}"
    # Map the modified_classes field from string to list
    IFS=, read -ra modified_classes_list <<< "${modified_classes}"
    # Define the path to the buggy version of the project_id-bug_id project
    buggy_project_bug_dir="${DEFECTS4J_DIR}/temp/${project_id}_${bug_id}b"
    # Define the path to the fixed version of the project_id-bug_id project
    fixed_project_bug_dir="${DEFECTS4J_DIR}/temp/${project_id}_${bug_id}f"
    # Get the directory to the maven/ant configuration file of the project_id
    project_builder_dir="${BUILDERS_DIR}/${project_id}"
    # Get the path to the source directory of the ant project_id-bug_id project
    project_bug_source_path="${buggy_project_bug_dir}/source"
    # Set path path to binary files
    if [ -d "$buggy_project_bug_dir/build/classes" ]; then
        binary_path="build/classes"
    elif [ -d "$buggy_project_bug_dir/build" ]; then
        binary_path="build"
    elif [ -d "${buggy_project_bug_dir}/target/classes" ]; then
        binary_path="target/classes"
    else
        echo "Binary path for project $project_id not found."
        exit 1
    fi
    if [ -d "$buggy_project_bug_dir/source" ]; then
        src_path="source"
    elif [ -d "$buggy_project_bug_dir/src/main/java" ]; then
        src_path="src/main/java"
    elif [ -d "$buggy_project_bug_dir/src/java" ]; then
        src_path="src/java"
    elif [ -d "${buggy_project_bug_dir}/src" ]; then
        src_path="src"
    elif [ -d "$buggy_project_bug_dir/gson/src/main/java" ]; then
        src_path="gson/src/main/java"
    else
        echo "Binary path for project $project_id not found."
        exit 1
    fi

    # Generate jars folder
    if [ ! -d "${buggy_project_bug_dir}/${project_id}/d4j_jars" ]; then
      mkdir -p "${buggy_project_bug_dir}/${project_id}/d4j_jars"
    fi
    if [ ! -d "${fixed_project_bug_dir}/${project_id}/d4j_jars" ]; then
      mkdir -p "${fixed_project_bug_dir}/${project_id}/d4j_jars"
    fi

    if [ -e "${buggy_project_bug_dir}/pom.xml" ]; then
        echo "mvn-dependencies"
        cd "$buggy_project_bug_dir"
        mvn dependency:tree > "${buggy_project_bug_dir}/dependencies.txt"
        cd "$ROOT_DIR"
        python3 "${DEFECTS4J_DIR}/merge_dependencies.py" mvn "${buggy_project_bug_dir}/mvn_dependencies.txt" "${ROOT_DIR}/mvn_dependencies.csv"
    elif [ -e "${buggy_project_bug_dir}/build.gradle" ]; then
        echo "gradle-dependencies"
        java_version=$(grep 'sourceCompatibility' "${buggy_project_bug_dir}/build.gradle" | awk -F= '{print $2}' | tr -d ' ')

        if [ -z "$java_version" ]; then
            java_version=$(grep 'def version' "${buggy_project_bug_dir}/build.gradle" | awk -F\' '{print $2}')
        fi

        if [[ ! "$java_version" =~ ^(1\.9|1_9) ]]; then
            if [[ "$java_version" =~ ^(1\.5|1\.6|1\.7|1\.8|1_5|1_6|1_7|1_8) ]]; then
                sdk use java "$JAVA8"
            elif [[ "$java_version" =~ ^(1\.11|1_11) ]]; then
                sdk use java "$JAVA11"
            else
                sdk use java "$JAVA17"
            fi
            cd "${buggy_project_bug_dir}"
            if [ -e "./gradlew" ]; then
                ./gradlew dependencies > "${buggy_project_bug_dir}/gradle_dependencies.txt"
                cd "${ROOT_DIR}"
                python3 "${DEFECTS4J_DIR}/merge_dependencies.py" gradle "${buggy_project_bug_dir}/gradle_dependencies.txt" "${ROOT_DIR}/gradle_dependencies.csv"
            fi
        else
            echo "skipped"
        fi
    else
        echo "ant-dependencies"
    fi
done < "$D4J_PROJECTS_BUGS"