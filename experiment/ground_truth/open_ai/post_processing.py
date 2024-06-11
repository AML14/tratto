import json
import os
from pathlib import Path

import pandas as pd
from tqdm import tqdm

current_file_path = Path(os.path.realpath(__file__))
open_ai_path = current_file_path.parent.absolute()
methods_path = os.path.join(open_ai_path, "methods")
output_batches_path = os.path.join(open_ai_path, "output_batches")

convert_method_source_to_method_signature: bool = True

def jsonl_to_dataframe(dir_name: str):
    all_data = pd.DataFrame()
    for filename in tqdm(os.listdir(dir_name)):
        if filename.endswith(".jsonl"):
            file_path = os.path.join(dir_name, filename)
            with open(file_path, "r") as file:
                data = [json.loads(line) for line in file]
                df = pd.DataFrame(data)
                all_data = pd.concat([all_data, df], ignore_index=True)
    return all_data


def file_name_to_project_name(file_name: str):
    project_idx = file_name.index("_")
    return file_name[:project_idx]


def methods_to_dataframe(dir_name: str):
    all_method_data = pd.DataFrame()
    for filename in tqdm(os.listdir(dir_name)):
        project_name = file_name_to_project_name(filename)
        if filename.endswith(".json"):
            file_path = os.path.join(dir_name, filename)
            df = pd.read_json(file_path)
            df = df.assign(projectName=project_name)
            all_method_data = pd.concat([all_method_data, df], ignore_index=True)
    return all_method_data


def remove_annotations(method_source: str):
    lines = method_source.strip().split("\n")
    cleaned_lines = [line for line in lines if not line.strip().startswith("@")]
    return "\n".join(cleaned_lines)


def remove_javadoc(method_source: str):
    lines = method_source.strip().split("\n")
    cleaned_lines = []
    in_javadoc = False
    for line in lines:
        line = line.strip()
        if line.startswith("/**"):
            in_javadoc = True
            continue
        if line.endswith("*/"):
            in_javadoc = False
            continue
        if in_javadoc or line.startswith("*"):
            continue
        cleaned_lines.append(line)
    return '\n'.join(cleaned_lines)


def method_source_to_method_signature(method_source: str):
    method_source = remove_javadoc(method_source)
    method_source = remove_annotations(method_source)
    start_params_idx = method_source.find('(')
    end_params_idx = method_source.find(')')
    if start_params_idx == -1 or end_params_idx == -1:
        raise ValueError(f"Unable to find parameters in method source code: {method_source}")
    before_params = method_source[:start_params_idx]
    before_params_parts = before_params.split()
    method_name = before_params_parts[-1]
    parameters = method_source[start_params_idx + 1:end_params_idx].strip()
    return f"{method_name}({parameters})"


def response_to_text(response: dict):
    return response["body"]["choices"][0]["message"]["content"]


def estimate_oracles(response: str):
    start_idx = response.find("```json")
    end_idx = response.find("```", start_idx + len("```json"))
    if start_idx == -1 or end_idx == -1:
        return ""
    else:
        return response[start_idx + len("```json"):end_idx]


def reformat_gpt_response():
    output_df = jsonl_to_dataframe(output_batches_path).drop(columns=["id", "error"])
    method_df = methods_to_dataframe(methods_path)
    combined_df = pd.merge(output_df, method_df, on="custom_id", how="left")
    if convert_method_source_to_method_signature:
        combined_df["methodSignature"] = combined_df["methodSourceCode"].apply(method_source_to_method_signature)
        # combined_df = combined_df.drop(columns="methodSourceCode")
    combined_df["response"] = combined_df["response"].apply(response_to_text)
    combined_df["estimated_oracles"] = combined_df["response"].apply(estimate_oracles)
    return combined_df


def write_gpt_response(df: pd.DataFrame):
    grouped_df = df.groupby(["projectName", "className"])
    for (project_name, class_name), group in grouped_df:
        project_dir = os.path.join(open_ai_path, "gpt_output", project_name)
        if not os.path.exists(project_dir):
            os.makedirs(project_dir)
        file_path = os.path.join(project_dir, f"{class_name}.json")
        group.to_json(file_path, orient="records", indent=2)


def main():
    gpt_response = reformat_gpt_response()
    write_gpt_response(gpt_response)


if __name__ == "__main__":
    main()
