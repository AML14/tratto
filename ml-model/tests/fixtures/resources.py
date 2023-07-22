import os.path

import pytest

from src.utils import utils


@pytest.fixture(scope="session")
def value_mappings():
    _, value_mappings = utils.import_json(
        os.path.join(
            os.path.dirname(__file__),
            "..",
            "..",
            "src",
            "resources",
            "tokenClassesValuesMapping.json"
        )
    )
    return value_mappings