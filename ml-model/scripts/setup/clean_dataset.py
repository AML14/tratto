import os
import numpy as np
import torch
import pandas as pd

d_paths = [
    os.path.join(os.path.dirname(os.path.abspath(__file__)), "..", "..", "dataset", "token-classes-dataset"),
    os.path.join(os.path.dirname(os.path.abspath(__file__)), "..", "..", "dataset", "token-values-dataset")
]
c_paths = [
    os.path.join(os.path.dirname(os.path.abspath(__file__)), "..", "..", "dataset", "token-classes-dataset-cleaned"),
    os.path.join(os.path.dirname(os.path.abspath(__file__)), "..", "..", "dataset", "token-values-dataset-cleaned")
]

for c_path in c_paths:
    if not os.path.exists(c_path):
        os.makedirs(c_path)

# list of partial dataframes
dfs = []
# collects partial dataframes from oracles
for d_path, c_path in zip(d_paths, c_paths):
    for file_name in os.listdir(d_path):
        print(file_name)
        df_dataset = pd.read_json(os.path.join(d_path,  file_name))
        if len(df_dataset) > 0:
            # drop column id (it is not relevant for training the model)
            df_dataset = df_dataset.drop(['id'], axis=1)
            # map empty cells to empty strings
            df_dataset.fillna('', inplace=True)
            # Remove method source code (keep only signature)
            #df_dataset['methodSourceCode'] = df_dataset['methodSourceCode'].str.split('{').str[0]
            # Delete the tgt labels from the input dataset, and others less relevant columns
            df_dataset = df_dataset.drop(['projectName', 'classJavadoc', 'classSourceCode'], axis=1)
            df_dataset.to_json(os.path.join(c_path, file_name))
