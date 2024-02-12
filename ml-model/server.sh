export PYTHONPATH="./"

# Common parameters
port="${1:-5000}"
classification_type_oracles="LABEL_PREDICTION"
classification_type_token_classes="LABEL_PREDICTION"
classification_type_token_values="LABEL_PREDICTION"

# Parameters for server with 3 separate models
model_type_oracles="codet5+"
model_type_token_classes="codet5+"
model_type_token_values="codet5+"
tokenizer_name_oracles="Salesforce/codet5p-770m"
tokenizer_name_token_classes="Salesforce/codet5p-770m"
tokenizer_name_token_values="Salesforce/codet5p-770m"
model_name_or_path_oracles="Salesforce/codet5p-770m"
model_name_or_path_token_classes="Salesforce/codet5p-770m"
model_name_or_path_token_values="Salesforce/codet5p-770m"
checkpoint_path_oracles="checkpoints/oracles-checkpoint/pytorch_model.bin"
checkpoint_path_token_classes="checkpoints/token-classes-checkpoint/pytorch_model.bin"
checkpoint_path_token_values="checkpoints/token-values-checkpoint/pytorch_model.bin"

# Parameters for server with multitask model
model_type_multitask="codet5+"
tokenizer_name_multitask="Salesforce/codet5p-220m"
model_name_or_path_multitask="Salesforce/codet5p-220m"
checkpoint_path_multitask="/Users/davidemolinelli/tratto/ml-model/checkpoints/multitask-checkpoint/model.safetensors"

if [ "$#" -eq 2 ] && [ "$2" == "--multitask" ]; then
  echo "Starting server with multitask model"
  python3 scripts/server/server_multitask.py \
    --port "$port" \
    --model_type_multitask "$model_type_multitask" \
    --classification_type_oracles "$classification_type_oracles" \
    --classification_type_token_classes "$classification_type_token_classes" \
    --classification_type_token_values "$classification_type_token_values" \
    --tokenizer_name_multitask "$tokenizer_name_multitask" \
    --model_name_or_path_multitask "$model_name_or_path_multitask" \
    --checkpoint_path_multitask "$checkpoint_path_multitask"
else
  echo "Starting server with oracles, token_classes, and token values models"
  python3 scripts/server/server.py \
    --port "$port" \
    --model_type_oracles "$model_type_oracles" \
    --model_type_token_classes "$model_type_token_classes" \
    --model_type_token_values "$model_type_token_values" \
    --classification_type_oracles "$classification_type_oracles" \
    --classification_type_token_classes "$classification_type_token_classes" \
    --classification_type_token_values "$classification_type_token_values" \
    --tokenizer_name_oracles "$tokenizer_name_oracles" \
    --tokenizer_name_token_classes "$tokenizer_name_token_classes" \
    --tokenizer_name_token_values "$tokenizer_name_token_values" \
    --model_name_or_path_oracles "$model_name_or_path_oracles" \
    --model_name_or_path_token_classes "$model_name_or_path_token_classes" \
    --model_name_or_path_token_values "$model_name_or_path_token_values" \
    --checkpoint_path_oracles "$checkpoint_path_oracles" \
    --checkpoint_path_token_classes "$checkpoint_path_token_classes" \
    --checkpoint_path_token_values "$checkpoint_path_token_values"
fi