import os
import shutil
from io import BytesIO

import requests
import zipfile
from src.utils import utils

def clean_dir(path):
    path_dirs = os.listdir(path)
    if '.DS_Store' in path_dirs:
        os.remove(os.path.join(path,'.DS_Store'))
    if '__MACOSX' in path_dirs:
        shutil.rmtree(os.path.join(path,'__MACOSX'))

def extract_zip_from_url(resource):
    for path_list, url in resource:
        print(f"Downloading resource {path_list[-1]}...")
        zip = zipfile.ZipFile(BytesIO(requests.get(url, allow_redirects=True).content))
        path = os.path.join(
            os.path.dirname(os.path.abspath(__file__)),
            '..',
            '..',
            *path_list
        )
        if not os.path.exists(path):
            os.makedirs(path)
        print(f"Extracting resource {path_list[-1]}...")
        for zip_file in zip.infolist():
            zip.extract(
                zip_file,
                path
            )
        clean_dir(path)


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

#extract_zip_from_url(datasets)
extract_zip_from_url(checkpoints)

