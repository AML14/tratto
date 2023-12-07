import json
import csv
import os

PROJECT_INPUT_DIR_PATH = os.path.join(
    os.path.dirname(os.path.abspath(__file__)), ".." , "src", "main",
    "java", "star", "tratto", "data", "repos"
)

if __name__ == '__main__':
    prompt_flag = True
    prompt_continue = False
    input_projects = []

    process_new_project = input("Do you want to add new projects to the database? ([Y]/N) ") or "Y"

    if process_new_project.upper() == "Y":
        prompt_flag = True
    elif process_new_project.upper() == "N":
        prompt_flag = False
    else:
        print("Prompt not recognized.")
        prompt_flag = False

    while(prompt_flag):
        project_name = input("Name of the project to analyze: ")
        project_github_link = input("Github link to the project to clone: ")
        project_commit = input("Do you want to clone a specific commit: [last] ") or "last"
        dir_path = [project_name]
        source_input = input("Type the relative path to the src code (for example src/main/java for a standard maven project): [src/main/java] ") or "src/main/java"
        source_path = [dir for dir in source_input.split("/")]
        fqn_input = input("Type the fully qualified class names of the class you want to analyze, separated by a blank space: [all] ")
        if len(fqn_input) > 0 :
            fqn_list = [fqn for fqn in fqn_input.split(" ")]
        else:
            fqn_list = []
        input_projects.append({
            "projectName": project_name,
            "githubLink": project_github_link,
            "commit": project_commit,
            "rootPathList": dir_path,
            "srcPathList": source_path,
            "fullyQualifiedClassNameList": fqn_list
          })

        process_new_project = input("Do you want to analyze another project? ([Y]/N): ") or "Y"
        if process_new_project.upper() == "Y":
            prompt_flag = True
        elif process_new_project.upper() == "N":
            prompt_flag = False
        else:
            print("Prompt not recognized.")
            prompt_flag = False

        if not prompt_flag:
            input_projects_file_json = os.path.join(PROJECT_INPUT_DIR_PATH, "input_projects.json")
            input_projects_file_csv = os.path.join(PROJECT_INPUT_DIR_PATH, "input_projects.csv")

            with open(input_projects_file_json, 'w') as input_projects_file:
                json.dump(input_projects, input_projects_file, indent=4)

            with open(input_projects_file_csv, 'w') as input_projects_file:
                writer = csv.writer(input_projects_file)
                for project in input_projects:
                    writer.writerow([project['projectName'], project['githubLink'], project["commit"]])