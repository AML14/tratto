#!/bin/bash
# This script perform the experiments for Defect4J projects

# Get current directory
current_dir=$(realpath "$(dirname "$BASH_SOURCE")")

# Load global variables
source "${current_dir}/generator/utils/global_variables.sh"

# Setup sdkman
source "${UTILS_DIR}/sdkman_init.sh"

# Read the CSV file line by line and split into project_id, bug_id, and modified_classes fields
while IFS=, read -r project_id bug_id modified_classes; do
  sdk use java "$JAVA8"
  # Clean modified_classes string from undesired white-spaces/line-breaks introduced with the CSV parsing
  modified_classes="${modified_classes//[$'\t\r\n ']/}"
  # Map the modified_classes field from string to list
  IFS=, read -ra modified_classes_list <<< "${modified_classes}"
  # Define the path to the buggy version of the project_id-bug_id project
  buggy_project_bug_dir="${DEFECTS4J_DIR}/temp/${project_id}_${bug_id}b"
  # Define the path to the fixed version of the project_id-bug_id project
  fixed_project_bug_dir="${DEFECTS4J_DIR}/temp/${project_id}_${bug_id}f"
  # Checkout buggy version of the project_id-bug_id project
  echo "Checkout buggy version: project ${project_id}-${bug_id}b."
  #defects4j checkout -p "${project_id}" -v "${bug_id}b" -w "$buggy_project_bug_dir"
  echo "Checkout complete."
  # Checkout fixed version of the project_id-bug_id project
  echo "Checkout fixed version: project ${project_id}-${bug_id}f."
  #defects4j checkout -p "${project_id}" -v "${bug_id}f" -w "$fixed_project_bug_dir"
  echo "Checkout complete."
  # Get the directory to the maven/ant configuration file of the project_id
  project_builder_dir="${BUILDERS_DIR}/${project_id}"
  # Check if the project uses ant to build the project, otherwise use maven
  if [ -e "${project_builder_dir}/build.xml" ]; then
    # Get the path to the source directory of the ant project_id-bug_id project
    project_bug_source_path="${buggy_project_bug_dir}/source"
    # Copy the configuration file within the buggy version of the project_id-bug_id project
    cp "${project_builder_dir}/build.xml" "${buggy_project_bug_dir}/build.xml"
    # Copy the configuration file within the fixed version of the project_id-bug_id project
    cp "${project_builder_dir}/build.xml" "${fixed_project_bug_dir}/build.xml"
    # Compile the buggy version of the ant project_id-bug_id project
    echo "Compiling buggy version: project ${project_id}-${bug_id}b."
    cd "$buggy_project_bug_dir"
    #ant compile
    # Compile the fixed version of the ant project_id-bug_id project
    echo "Compiling fixed version: project ${project_id}-${bug_id}f."
    cd "$fixed_project_bug_dir"
    #ant compile
  elif [ -e "${project_builder_dir}/pom.xml" ]; then
    # Get the path to the source directory of the maven project_id-bug_id project
    project_bug_source_path="${buggy_project_bug_dir}/src"
    # Copy the configuration file within the buggy version of the project_id-bug_id project
    cp "${project_builder_dir}/pom.xml" "${buggy_project_bug_dir}/pom.xml"
    # Copy the configuration file within the fixed version of the project_id-bug_id project
    cp "${project_builder_dir}/pom.xml" "${fixed_project_bug_dir}/pom.xml"
    echo "Compiling buggy version: project ${project_id}-${bug_id}b."
    # Compile the buggy version of the maven project_id-bug_id project
    cd "$buggy_project_bug_dir"
    mvn clean package -DskipTests
    echo "Compiling fixed version: project ${project_id}-${bug_id}f."
    # Compile the fixed version of the maven project_id-bug_id project
    cd "$fixed_project_bug_dir"
    mvn clean package -DskipTests
  else
    echo "Project builder not found for project ${project_id}."
    exit 1
  fi
  # Move to the root directory to start the experiments
  cd "$ROOT_DIR"
  # Define the path to the binary classes
  project_bug_binary_path="${buggy_project_bug_dir}/target/classes"
  # Define the path to the jar file
  project_bug_jar_path="${buggy_project_bug_dir}/target/${project_id}.jar"
  # Iterate over the modified classes and generate test cases for each class
  for modified_class in "${modified_classes_list[@]}"; do
    # Run the experiment on the modified class
    bash experiment_test.sh "$modified_class" "$project_bug_binary_path"
  done
done < "$D4J_PROJECTS_BUGS"
