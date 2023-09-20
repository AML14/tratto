import subprocess
import json
import os

def import_json(file_path: str):
    """
    Imports a json file as an object. The method accepts only the format .json

    Parameters
    ----------
    file_path: str
        The path to the json file

    Returns
    -------
    input_file: File
        The file opened
    input_obj:
        The content of the file as a python dictionary object
    """
    with open(file_path) as input_file:
        input_obj = json.load(input_file)
        return input_file, input_obj

def get_defects4j_checkout_command(project_name, bug_id, fixed=False):
    """
    Generated a defects4j checkout command as a list (required format by subprocess).

    Parameters
    ----------
    project_name: str
        The name of the defects4j.
    bug_id: int
        The identifier of the bug to checkout.
    fixed: bool
        True, if the command have to checkout the fixed version, False otherwise (default False).

    Returns
    -------
    checkout_command: list[str]
        A list of strings representing the composition of the defects4j checkout command.
    """
    v_label = "f" if fixed else "b"
    checkout_command = [
        "defects4j",
        "checkout",
        "-p",
        project_name,
        "-v",
        f"{bug_id}{v_label}",
        "-w",
        f"/tmp/{project_name.lower()}_{bug_id}_{v_label}"
    ]
    return checkout_command

def main():
    _, defects4j_projects_bugs_list = import_json(os.path.join(os.path.dirname(__file__), 'defects4j_projects.json'))
    for project in defects4j_projects_bugs_list:
        project_name = project['name']
        bug_ids = project['bug_ids']
        for bug_id in bug_ids:
            checkout_buggy_command = get_defects4j_checkout_command(project_name, bug_id)
            checkout_fixed_command = get_defects4j_checkout_command(project_name, bug_id, True)
            try:
                print(f"Downloading project {project_name}-{bug_id}b.")
                checkout_buggy_result = subprocess.run(checkout_buggy_command, check=True, stdout=subprocess.PIPE, stderr=subprocess.PIPE, text=True)
                print(f"Download complete.")
                print(f"Downloading project {project_name}-{bug_id}f.")
                checkout_fixed_result = subprocess.run(checkout_fixed_command, check=True, stdout=subprocess.PIPE, stderr=subprocess.PIPE, text=True)
                print(f"Download complete.")
            except subprocess.CalledProcessError as e:
                print("Error:", e)
                print("Git clone error output:", e.stderr)




if __name__ == "__main__":
    main()