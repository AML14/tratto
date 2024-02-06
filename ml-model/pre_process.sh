export PYTHONPATH="./"

model_type="codet5+"
tokenizer_name="Salesforce/codet5p-770m"
tratto_model_type="oracles"
transformer_type="decoder"
train_path="./dataset/cleaned/oracles-dataset-train/json"
validation_path="./dataset/cleaned/oracles-dataset-validation/json"
classification_type="label_prediction"
pre_processing="True"
rapids_cudf="False"

if [ "$#" -eq 1 ] && [ "$1" == "--accelerate" ]; then
  echo "Running with accelerate"
  accelerate launch --config_file accelerate_config_fsdp.yaml scripts/setup/pre_process_dataset.py \
    --model_type "$model_type" \
    --tokenizer_name "$tokenizer_name" \
    --tratto_model_type "$tratto_model_type" \
    --transformer_type "$transformer_type" \
    --train_path "$train_path" \
    --validation_path "$validation_path" \
    --classification_type "$classification_type" \
    --pre_processing "$pre_processing" \
    --rapids_cudf "$rapids_cudf"
else
  echo "Running without accelerate"
  python3 scripts/setup/pre_process_dataset.py \
    --model_type "$model_type" \
    --tokenizer_name "$tokenizer_name" \
    --tratto_model_type "$tratto_model_type" \
    --transformer_type "$transformer_type" \
    --train_path "$train_path" \
    --validation_path "$validation_path" \
    --classification_type "$classification_type" \
    --pre_processing "$pre_processing" \
    --rapids_cudf "$rapids_cudf"
fi