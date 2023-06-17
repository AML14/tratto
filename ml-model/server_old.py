import argparse
import os

import torch
from flask import Flask, jsonify, request
from transformers import RobertaForSequenceClassification, RobertaTokenizer, RobertaConfig

import predictor
import json

from src.pretrained.ModelClasses import ModelClasses
from src.types.DeviceType import DeviceType
from src.utils import utils


# Global variables
app = Flask(__name__)
device = None
checkpoint_token_classes_path = None #os.path.join("output_token_classes_plume","checkpoints","lr_1e-05","batch_32","epochs_8","checkpoint_1_2.pt")
checkpoint_token_values_path = None #os.path.join("output_token_classes_plume","checkpoints","lr_1e-05","batch_32","epochs_8","checkpoint_1_2.pt")
model_type = None
model_name_or_path = None
config_name = None
models = None


def resume_checkpoint(pt_model, checkpoint_path):
    checkpoint = torch.load(checkpoint_path)
    return pt_model.load_state_dict(checkpoint['model_state_dict'])


def model_setup(checkpoint_classes_path, checkout_values_path, mode):
    print("Setup model")
    config_class, model_class, tokenizer_class = ModelClasses.getModelClass(args.model_type)
    return {
        "mode": mode,
        "model_classes": resume_checkpoint(
            model_class.from_pretrained(
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
    global device, models, checkpoint_token_classes_path, checkpoint_token_values_path, model_type, model_name_or_path, config_name
    # Parse arguments
    parser = argparse.ArgumentParser()
    parser.add_argument(
        "--checkpoint_token_classes_path",
        default=None,
        type=str,
        required=True,
        help="The path to the checkpoint of the tokenClass model to load."
    )
    parser.add_argument(
        "--checkpoint_token_values_path",
        default=None,
        type=str,
        required=True,
        help="The path to the checkpoint of the tokenValues model to load."
    )
    parser.add_argument(
        "--model_type",
        default=None,
        type=str,
        required=True,
        help="Model type selected in the list: " + ", ".join(ModelClasses.get_available_model_classes())
    )
    parser.add_argument(
        "--tokenizer_name",
        default=None,
        type=str,
        help="Pretrained tokenizer name or path if not the same as model_name"
    )
    parser.add_argument(
        "--config_name",
        default=None,
        type=str,
        help="Pretrained config name or path if not the same as model_name"
    )
    parser.add_argument(
        "--model_name_or_path",
        default=None,
        type=str,
        required=True,
        help="Path to pre-trained model or shortcut name."
    )
    parser.add_argument(
        "--config_name",
        default=None,
        type=str,
        help="Pretrained config name or path if not the same as model_name"
    )
    args = parser.parse_args()
    device = utils.connect_to_device(DeviceType.GPU)
    checkpoint_token_classes_path = args.checkpoint_token_classes_path
    checkpoint_token_values_path = args.checkpoint_token_values_path
    model_type = args.model_type
    model_name_or_path = args.model_name_or_path
    config_name = args.config_name
    models = model_setup(
        model_type,
        checkpoint_token_classes_path,
        checkpoint_token_values_path,
        "production"
    )
    app.run()
