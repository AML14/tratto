import os
import shutil
from io import BytesIO
import argparse
import subprocess
import zipfile
from src.parser.ArgumentParser import ArgumentParser
from src.utils import utils


def clean_dir(path, file_name):
    path_dirs = os.listdir(path)
    if '.DS_Store' in path_dirs:
        os.remove(os.path.join(path, '.DS_Store'))
    if '__MACOSX' in path_dirs:
        shutil.rmtree(os.path.join(path, '__MACOSX'))
    os.remove(os.path.join(path, file_name))


def download_with_wget(url, output_dir):
    # Extract filename from URL
    file_name = os.path.basename(url)
    output_path = os.path.join(output_dir, file_name)
    subprocess.run(['wget', url, '-O', output_path])
    return file_name


def extract_zip_from_url(resource):
    for resource_name, path_list, url in resource:
        print(f"Downloading resource {resource_name}...")

        path = os.path.join(
            os.path.dirname(os.path.abspath(__file__)),
            '..',
            '..',
            *path_list
        )

        if not os.path.exists(path):
            os.makedirs(path)

        # Specify the output directory for wget
        file_name = download_with_wget(url, path)

        zip_file_path = os.path.join(path, os.path.basename(url))

        # Read the downloaded file into a BytesIO object
        with open(zip_file_path, 'rb') as f:
            zip_data = BytesIO(f.read())

        print(f"Extracting resource {path_list[-1]}...")
        with zipfile.ZipFile(zip_data, 'r') as zip_ref:
            zip_ref.extractall(path)

        clean_dir(path, file_name)


_, datasets = utils.import_json(
    os.path.join(
        os.path.dirname(os.path.abspath(__file__)),
        '..',
        '..',
        'src',
        'resources',
        'datasets.json'
    )
)

_, checkpoints = utils.import_json(
    os.path.join(
        os.path.dirname(os.path.abspath(__file__)),
        '..',
        '..',
        'src',
        'resources',
        'checkpoints.json'
    )
)

if __name__ == '__main__':
    # Parse arguments
    parser = argparse.ArgumentParser()
    ArgumentParser.add_init_arguments(parser)
    args = parser.parse_args()
    if args.datasets:
        extract_zip_from_url(datasets)
    if args.checkpoints:
        extract_zip_from_url(checkpoints)
    print("Initialization completed.")