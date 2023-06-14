import os

import torch
from flask import Flask, jsonify, request
from transformers import RobertaForSequenceClassification, RobertaTokenizer, RobertaConfig

import predictor
import json

from src.types.DeviceType import DeviceType
from src.utils import utils

app = Flask(__name__)

def resume_checkpoint(pt_model, checkpoint_path):
    checkpoint = torch.load(checkpoint_path)
    return pt_model.load_state_dict(checkpoint['model_state_dict'])

def model_setup(checkpoint_classes_path, checkout_values_path, mode):
    print("Setup model")
    return {
        "mode": mode,
        "model_classes": resume_checkpoint(
            RobertaForSequenceClassification.from_pretrained(
                'roberta-base',
                config=RobertaConfig.from_pretrained('roberta-base')
            ),
            checkpoint_classes=torch.load(checkpoint_classes_path)
        ),
        "model_values": resume_checkpoint(
            RobertaForSequenceClassification.from_pretrained(
                'roberta-base',
                config=RobertaConfig.from_pretrained('roberta-base')
            ),
            checkpoint_classes=torch.load(checkout_values_path)
        ),
        "tokenizer": RobertaTokenizer.from_pretrained('roberta-base')
    }

default_checkpoint_classes_path = os.path.join("output_token_classes_plume","checkpoints","lr_1e-05","batch_32","epochs_8","checkpoint_1_2.pt")
default_checkpoint_values_path = os.path.join("output_token_classes_plume","checkpoints","lr_1e-05","batch_32","epochs_8","checkpoint_1_2.pt")

# Connect to device
print("Connect to device")
device = utils.connect_to_device(DeviceType.GPU)

models = model_setup(
    default_checkpoint_classes_path,
    default_checkpoint_values_path,
    "production"
)

@app.route('/api/test', methods=['GET'])
def test():
    global models
    checkpoint_classes_path = request.args.get('checkpoint_classes_path')
    checkpoint_values_path = request.args.get('checkpoint_values_path')
    if checkpoint_classes_path and checkpoint_values_path:
        model_setup(
            checkpoint_classes_path,
            checkpoint_values_path,
            "test"
        )
        return jsonify({"task": "completed"})
    else:
        return 'Missing data parameter', 400  # Return an error if the 'data' parameter is missing

@app.route('/api/next_token', methods=['GET'])
def oracle():
    global models
    filename = request.args.get('filename')
    checkpoint_classes_path = request.args.get('checkpoint_classes') if 'checkpoint_classes' in request.args else None
    checkpoint_values_path = request.args.get('checkpoint_values') if 'checkpoint_values' in request.args else None
    reset = request.args.get('reset') if 'reset' in request.args else False

    if reset or models["mode"] == "test":
        models = model_setup(
            checkpoint_classes_path if checkpoint_classes_path else default_checkpoint_classes_path,
            checkpoint_values_path if checkpoint_values_path else default_checkpoint_values_path,
            "production"
        )

    if filename:
        token = predictor.nextToken(
            device,
            filename,
            models["model_classes"],
            models["model_values"]
        )
    else:
        return 'Missing filename parameter', 400  # Return an error if the 'data' parameter is missing
    return jsonify({'token': token})


if __name__ == '__main__':
    app.run()