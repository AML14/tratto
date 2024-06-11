import argparse
import csv
import subprocess
import os
import re
import difflib

class Defects4JUtils:
    @staticmethod
    def generate_csv_modified_classes(d4j_root_path: str):
        """
        Generates a csv file where each row contains information about a modified class of a project, to fix a bug.
        Each row contains three elements:
            <ol>
                <li>the project name</li>
                <li>the bug id</li>
                <li>the fully qualified name of a modified class</li>
            </ol>

        Parameters
        ----------
        d4j_root_path: str
            The path to the root folder of the defects4j project.

        Returns
        -------
        A csv file containing the information about the modified classes within each project, to fix each bug.
        """
        modified_classes_csv_file_path = os.path.join(os.path.dirname(os.path.abspath(__file__)), 'modified_classes.csv')
        result = subprocess.run(["defects4j","pids"], stdout=subprocess.PIPE, text=True, check=True)
        project_ids = result.stdout.split('\n')[:-1]

        if os.path.exists(modified_classes_csv_file_path):
            os.remove(modified_classes_csv_file_path)

        for project_id in project_ids:
            project_path = os.path.join(d4j_root_path, 'framework', 'projects', project_id)
            modified_classes_path = os.path.join(project_path, 'modified_classes')
            result = subprocess.run(['defects4j', 'bids', '-p', project_id], stdout=subprocess.PIPE, text=True, check=True)
            active_bug_ids = result.stdout.split('\n')[:-1]
            if not os.path.exists(project_path):
                raise Exception(f"Unexpected project {project_id} folder not found. Path to folder {project_path} does not exists.")
            for bug_id in active_bug_ids:
                modified_classes = []
                bug_modified_classes_file_path = os.path.join(modified_classes_path, f"{bug_id}.src")
                if not os.path.exists(bug_modified_classes_file_path):
                    raise Exception(f"Unexpected active bug file {bug_id}.src not found. Path to file {bug_modified_classes_file_path} does not exists.")
                with open(bug_modified_classes_file_path, 'r') as bug_modified_classes_file:
                    for fully_qualified_name in bug_modified_classes_file:
                        fully_qualified_name = re.sub(r'["\n]', '', fully_qualified_name)
                        modified_classes.append(fully_qualified_name)
                with open(modified_classes_csv_file_path, mode='a', newline='') as output_csv_file:
                    csv_writer = csv.writer(output_csv_file)
                    csv_writer.writerow([project_id, bug_id, " ".join(modified_classes)])

    @staticmethod
    def check_differences_between_files(reference_file_path, other_file_paths):
        """
        Checks the differences, in terms of content, between a reference file, and other version of the file.
        Generates a textual file (.txt) containing the analysis of the differences. The file is saved in
        the current folder as diff_analysis.txt. The file is overwritten anytime the method is called.

        Parameters
        ----------
        reference_file_path: str
            The path to the reference file.
        other_file_paths: list[str]
            A list of paths to the other files to compare.
        """
        diff_file_path = os.path.join(os.path.dirname(os.path.abspath(__file__)), 'diff_analysis.txt')
        if os.path.exists(diff_file_path):
            os.remove(diff_file_path)
        with open(reference_file_path, 'r') as reference_file:
            reference_file_lines = reference_file.readlines()

        for other_file_path in other_file_paths:
            with open(other_file_path, 'r') as other_file:
                other_file_lines = other_file.readlines()
            differ = difflib.unified_diff(reference_file_lines, other_file_lines, lineterm='', fromfile=reference_file_path, tofile=other_file_path)
            # Convert the diff object to a list of strings
            diff_lines = list(differ)
            with open(diff_file_path, 'a') as diff_file:
                diff_file.write(f"{'*'*20}\n")
                diff_file.write(f"File path: {other_file_path}\n")
                diff_file.writelines(diff_lines)
                diff_file.write(f"{'*'*20}\n")

if __name__ == "__main__":
    parser = argparse.ArgumentParser(description="Choose a static method to call.")
    subparsers = parser.add_subparsers(dest="method", help="Select a method to call.")

    # Subparser for method generate_csv_modified_classes
    generate_csv_modified_classes_parser = subparsers.add_parser("generate_csv_modified_classes", help="Call generate_csv_modified_classes.")
    generate_csv_modified_classes_parser.add_argument("-o", "--options", nargs="+", required=True, help="Options for generate_csv_modified_classes_parser.")

    # Subparser for method check_differences_between_files
    check_differences_between_files_parser = subparsers.add_parser("check_differences_between_files", help="Call check_differences_between_files.")
    check_differences_between_files_parser.add_argument("-o", "--options", nargs="+", required=True, help="Options for check_differences_between_files.")

    args = parser.parse_args()

    if args.method == "generate_csv_modified_classes":
        defects4j_root_path = args.options[0]
        Defects4JUtils.generate_csv_modified_classes(defects4j_root_path)
    if args.method == "check_differences_between_files":
        reference_file_path = args.options[0]
        other_file_paths = [other_file_path for other_file_path in args.options[1:]]
        Defects4JUtils.check_differences_between_files(reference_file_path, other_file_paths)
