# Configurations
## Training
### Small models & Single GPU
```bash
python3 scripts/train/run_classifier.py
```
#### Big Models & Multiple GPUs

```bash
accelerate launch --config_file accelerate_config_fsdp.yaml scripts/train/run_classifier_accelerate.py
```
The scripts for the training of the model requires the following arguments:
```bash
--model_type [ The model type, defined and configured in the `src/pretrained/ModelClasses.py` file - e.g. codet5+ ] \
--tokenizer_name [ The name of the tokenizer, from HuggingFace - e.g. Salesforce/codet5p-770m ] \
--model_name_or_path [ The name of the pre-trained model, from HuggingFace - e.g. Salesforce/codet5p-770m ] \
--tratto_model_type [ oracles, token_classes, or token_values ] \
--task_name [ Custom name - e.g. oraclesModel_classifier_decoder ] \
--max_src_length [ Max number of input tokens to the model - e.g. 512 ] \
--batch_size [ Batch size to train the model - e.g. 16] \
--learning_rate [ The learning rate during the training phase - e.g. 1e-5 ] \
--num_epochs [ Number of epochs to train the model - e.g. 20 ] \
--checkpoint_steps [ Checkpoint interval before to prform validation and save current model state - e.g. 500 ] \
--accumulation_steps [ Number of steps before to perform backpropagation - e.g. 1 ] \
--train_path [ Path to the training dataset - e.g. ./dataset/cleaned/oracles-dataset-train/csv ] \
--validation_path [ Path to the validation dataset - e.g. ./dataset/cleaned/oracles-dataset-validation/csv ] \
--output_dir [ Path to the output directory where to save the checkpoints - e.g. ./output_oracles_model_decoder_label_770 ] \
--classification_type [ category_prediction, or label_prediction ]
```
The model name and the tokenizer name are taken from the HuggingFace models collection, and must be in compliance with 
the model type defined and configured in the `src/pretrained/ModelClasses.py` file.

Remember to export the python path if not already sets:
```bash
export PYTHONPATH="./"
```
### Test the model (Inference)
```bash
python3 scripts/test/test_single_project.py
```
The script for the inference of the model requires the following arguments:
```bash
--model_type [ The model type, defined and configured in the `src/pretrained/ModelClasses.py` file - e.g. codet5+ ] \
--tokenizer_name [ The name of the tokenizer, from HuggingFace - e.g. Salesforce/codet5p-770m ] \
--model_name_or_path [ The name of the pre-trained model, from HuggingFace - e.g. Salesforce/codet5p-770m ] \
--tratto_model_type [ oracles, token_classes, or token_values ] \
--checkpoint_path [ Path to the checkpoint to load and use for inference - e.g. checkpoints/token-classes-checkpoint/classes_decoder_category_770/pytorch_model.bin ] \
--input_path [ Path to the test dataset - e.g. dataset/token-classes-dataset] \
--output_path [ Path to the output directory where to save the results of the test - test_token_classes_decoder_category_770 ] \
--project_name [ Name of the project to analyze. It must match the name on the dataset to load - e.g. gs-core ] \
--classification_type [ category_prediction, or label_prediction ]
```
The script supposes that the test dataset is a folder containing single/multiple files in json or csv format.
The name of each file must contain the name of the project to analyze, e.g.:
```bash
dataset_dir
├── project_gs-core_file_1.json
├── project_gs-core_file_2.json
├── project_gs-core_file_3.json
├── ...
```
or
```bash
dataset_dir
├── project_gs-core_file_1.csv
├── project_gs-core_file_2.csv
├── project_gs-core_file_3.csv
├── ...
```
if the project name is `gs-core`.

Remember to export the python path if not already sets:
```bash
export PYTHONPATH="./"
```
### Server
```bash
export PYTHONPATH="./"
python3 scripts/server/server.py \
--port [ server port, e.g. 5050 ] \
--model_type_token_classes [ The model type of the TokenClasses predictor, defined and configured in the `src/pretrained/ModelClasses.py` file - e.g. codet5+ ] \
--model_type_token_values [ The model type of the TokenValues predictor, defined and configured in the `src/pretrained/ModelClasses.py` file - e.g. codet5+ ] \
--classification_type_token_classes [ category_prediction, or label_prediction ] \
--classification_type_token_values [ category_prediction, or label_prediction ] \
--tokenizer_name_token_classes [ The name of the tokenizer of the TokenClass predictor, from HuggingFace - e.g. Salesforce/codet5p-770m ] \
--tokenizer_name_token_values [ The name of the tokenizer of the TokenValues predictor, from HuggingFace - e.g. Salesforce/codet5p-770m ] \
--model_name_or_path_token_classes [ The name of the pre-trained model of the TokenClass predictor, from HuggingFace - e.g. Salesforce/codet5p-770m ] \
--model_name_or_path_token_values [ The name of the pre-trained model of the TokenValues predictor, from HuggingFace - e.g. Salesforce/codet5p-770m ] \
--checkpoint_path_token_classes [ Path to the checkpoint to load and use for the TokenClasses predictor - e.g. checkpoints/token-classes-checkpoint/pytorch_model.bin ] \
--checkpoint_path_token_values [ Path to the checkpoint to load and use for the TokenValues predictor - e.g. checkpoints/token-values-checkpoint/pytorch_model.bin ]
```
