#!/bin/bash
# This script generates the batches to run open AI ChatGPT experiments with the ground truth

# Get current directory
current_dir=$(realpath "$(dirname "${BASH_SOURCE[@]}")")
# Setup global variables
source "${current_dir}/../generator/utils/global_variables.sh"
# Setup sdkman
source "${UTILS_DIR}/init_sdkman.sh"

sdk use java "$JAVA17"

# Setup experiments
bash "${UTILS_DIR}/experiment_setup.sh"

output_open_ai="${GROUND_TRUTH_DIR}/open_ai/methods"

if [ ! -d "$output_open_ai" ]; then
  mkdir -p "$output_open_ai"
fi

# Read the CSV file line by line performing the experiments on each project
while IFS=, read -r project_name _ _ _; do
    echo "Processing project: $project_name"
    project_path="${GROUND_TRUTH_DIR}/${project_name}"
    classes_path="${project_path}/classes"
    # Iterate over each .java file in the specified directory
    for class_path in $(find "$classes_path" -type f -name "*.java"); do
      class_file="${class_path##*/}"
      class_name="${class_file%.java}"
      echo "Processing class: $class_file"
      java -jar "$EXPERIMENT_JAR" generate_open_ai_ground_truth project_name "${classes_path}/${class_file}" "${output_open_ai}/${project_name}_${class_name}.json"
    done
done < "$GROUND_TRUTH_PROJECTS"