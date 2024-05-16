#!/bin/bash
# This script generates oracles for all bugs in Defects4J for a given TOG.
current_dir=$(realpath "$(dirname "${BASH_SOURCE[0]}")")
# Setup global variables
source "${current_dir}/generator/utils/global_variables.sh"
# setup sdkman
source "${UTILS_DIR}/init_sdkman.sh"
tog=${1}
round=${2}
d4j_classes_file_path=${3}
run=${4-false}

# Read the CSV file line by line and split into project_id, bug_id, and modified_classes fields
while IFS=, read -r project_id bug_id modified_classes; do
  # Clean modified_classes string from undesired white-spaces/line-breaks introduced with the CSV parsing
  modified_classes="${modified_classes//[$'\t\r\n ']/}"
  # Map the modified_classes field from string to list
  IFS=, read -ra modified_classes_list <<< "${modified_classes}"
  # Check for a given project or bug id
  sdk use java "$JAVA8"
  # Checkout Defects4J project and export classpath
  project_dir="${DEFECTS4J_DIR}/temp/${project_id}_${bug_id}"
  if [ ! -d "$project_dir"]; then
    defects4j checkout -p "${project_id}" -v "${bug_id}b" -w "${project_dir}"
  fi
  external_classpath="${RESOURCES_DIR}/evosuite-runtime.jar:${RESOURCES_DIR}/junit-4.13.2.jar:${SDKMAN_DIR}/candidates/java/${JAVA8}/src"
  classpath=$(defects4j export -p "cp.compile" -w "${project_dir}")
  src_rel_path=$(defects4j export -p "dir.src.classes" -w "${project_dir}")
  # Iterate over the modified classes and generate test cases for each class
  for modified_class in "${modified_classes_list[@]}"; do
      # Fully qualified classname path
      fqn_path=$(echo "$modified_class" | sed 's/\./\//g')
      # D4J output path
      d4j_project_bug_output="${DEFECTS4J_DIR}/output_${tog}/${round}/${project_id}/${bug_id}"
      d4j_fqn_output="${d4j_project_bug_output}/${fqn_path}"
      # output path
      tog_project_bug_output="${OUTPUT_DIR}/${tog}/${round}/${project_id}/${bug_id}"
      tog_fqn_output="${tog_project_bug_output}/${fqn_path}"
      # Insert oracles
      if [ ! -d "$tog_fqn_output" ]; then
        mkdir -p "$tog_fqn_output"
      fi
      cp -r "$d4j_fqn_output"/* "$tog_fqn_output"
      prefix_path="${tog_fqn_output}/evosuite-prefixes/${project_id}/${bug_id}"
      oracle_path="${tog_fqn_output}/${tog}-oracles/${project_id}/${bug_id}"
      src_path="${project_dir}/${src_rel_path}"
  echo "source path: ${src_path}"
  sdk use java "17.0.8-oracle"
  java -cp "${classpath}" -jar "${EXPERIMENT_JAR}" "insert_oracles" "${prefix_path}" "${oracle_path}" "${src_path}" "${classpath}:${external_classpath}"
  # Run tests and generate output
  if [ "$run" == "true" ]; then
    echo "Running tests and generating test output"
    # Execute tests with evosuite
    bash "${ROOT_DIR}/runner_d4j.sh" "$tog" "$project_id" "$bug_id" "${tog_fqn_output}" "${tog_fqn_output}/${tog}-test-suite"
    # Generate tests stats
    java -jar "$EXPERIMENT_JAR" generate_defects4j_output "$tog" "$target_class" "$project_id" "$bug_id" "${tog_fqn_output}/${tog}-tests" "${tog_fqn_output}/${tog}-test-suite"
  fi
done < "${d4j_classes_file_path}"
echo "Experiment complete!"
