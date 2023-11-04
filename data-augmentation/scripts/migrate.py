import json
import os

PROJECT_INPUT_FILE_PATH = os.path.join(
    os.path.dirname(os.path.abspath(__file__)), ".." , "src", "main",
    "java", "star", "tratto", "data", "repos", "input_projects.json"
)

with open(PROJECT_INPUT_FILE_PATH, "r") as input_file:
    input_projects = json.load(input_file)
