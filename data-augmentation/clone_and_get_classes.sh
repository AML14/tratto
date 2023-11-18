#!/bin/bash

# get parameters (github link & project name & src dir & prefix)
github_link=$1
project_name=$2
src_dir=$3
prefix="projects.${project_name}.$(echo -e "$src_dir" | tr '/' '.')."

if [ ! -d "./projects" ]; then
  mkdir "projects"
fi

if [ ! -d "./projects/${project_name}" ]; then
  # clone project in projects
  git clone "$github_link" "projects/${project_name}"
fi

# get classes to check
classes_str=$(bash get_classes.sh "projects/${project_name}/${src_dir}")
# get final string
final_str=$(python3 convert.py "$classes_str" "$prefix")

echo "$final_str"