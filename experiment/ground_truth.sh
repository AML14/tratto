#!/bin/bash
# This script perform the experiments for Defect4J projects

# Get current directory
current_dir=$(realpath "$(dirname "${BASH_SOURCE[@]}")")
# Setup global variables
source "${current_dir}/generator/utils/global_variables.sh"

# Tog name
tog=${1}
# Server port
server_port="${4-${SERVER_PORT}}"
# Setup ground truth projects folder
bash "${UTILS_DIR}/ground_truth_setup.sh"
# Read the CSV file line by line performing the experiments on each project
while IFS=, read -r project_name src_path _ _; do
    project_path="${GROUND_TRUTH_DIR}/${project_name}"
    src_path="${project_path}/project/${src_path}"
    binary_path="${project_path}/bin/unzip"
    jars_path="${project_path}/jars"
    classes_list_file="${project_path}/class_list.csv"
    classpath=""
    if [ ! -d "$binary_path" ]; then
        mkdir -p "$binary_path"
        cd "${project_path}/bin"
        unzip unzip bin.zip -d "$binary_path"
        cd "$ROOT_DIR"
    fi
    # Iterate over each file in the directory
    for file in "$jars_path"/*; do
      # Append the absolute path of the file to the classpath variable
      classpath="${classpath}:${file}"
    done
    # Remove the leading colon from the classpath
    classpath=${classpath:1}
    echo "Performing experiment with ${tog} on project ${project_name}."
    # Iterate over the modified classes and generate test cases for each class
    while IFS=, read -r project_class; do
        project_class="${project_class//[$'\t\r\n ']/}"
        # Fully qualified classname path
        fqn_path=$(echo "$project_class" | sed 's/\./\//g')
        # output paths
        project_class_output="${OUTPUT_DIR}/${tog}/ground_truth/${project_name}"
        tog_fqn_output="${project_class_output}/${fqn_path}"
        gt_project_output="${GROUND_TRUTH_DIR}/output_${tog}/${project_name}"
        gt_fqn_output="${gt_project_output}/${fqn_path}"
        if [ ! -d "$tog_fqn_output" ]; then
            mkdir -p "$tog_fqn_output"
        fi
        if [ ! -d "$gt_fqn_output" ]; then
            mkdir -p "$gt_fqn_output"
        fi
        # Generate jdoctor oracles
        if [ "${tog}" == "jdoctor" ]; then
          bash experiment.sh jdoctor "$project_class" "${src_path}" "${binary_path}" "${classpath}" "${tog_fqn_output}" "false"
        # Generate toga oracles
        elif [ "${tog}" == "toga" ]; then
          bash experiment.sh toga "$project_class" "${src_path}" "${binary_path}" "${jars_path}" "${tog_fqn_output}" "false"
        # Generate tratto oracles
        elif [ "${tog}" == "tratto" ]; then
          bash experiment.sh tratto "$project_class" "${src_path}" "${binary_path}" "${jars_path}" "${tog_fqn_output}" "false" "${server_port}"
        # Execute baseline
        elif [ "${tog}" == "baseline" ]; then
          bash experiment.sh baseline "$project_class" "" "" "" ${tog_fqn_output} "false"
        else
          echo -e "tog.sh: Invalid TOG name, ${tog}"
          exit 1
        fi
        cp -r "${tog_fqn_output}" "$(dirname ${gt_fqn_output})"
    done < "$classes_list_file"
done < "$GROUND_TRUTH_PROJECTS"