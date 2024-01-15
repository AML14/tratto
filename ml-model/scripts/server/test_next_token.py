import os
import shutil
import requests

dataset_dir = os.path.join(
    os.path.dirname(os.path.abspath(__file__)),
    "..",
    "..",
    "dataset"
)

next_token_dir = os.path.join(
    dataset_dir,
    "test_next_token"
)

oracle = ""

for counter in range(len(os.listdir(next_token_dir))):
    shutil.copy(
        os.path.join(next_token_dir, f"next_token_{counter}.json"),
        os.path.join(dataset_dir, f"next_token.json")
    )
    url = f'http://127.0.0.1:5000/api/next_token?filename={os.path.join(dataset_dir, f"next_token.json")}'
    response = requests.get(url,verify=False)

    if response.status_code == 200:
        print(response.text)
        # Request was successful
        oracle+=response.text

print(oracle)
