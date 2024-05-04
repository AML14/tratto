export PYTHONPATH="./"

model_type="codet5+"
tokenizer_name="Salesforce/codet5p-770m"
tratto_model_type="token_values"
transformer_type="decoder"
train_path="./dataset/cleaned/token-values-dataset-train/json"
validation_path="./dataset/cleaned/token-values-dataset-validation/json"
classification_type="label_prediction"
pre_processing="True"
rapids_cudf="False"
is_multitask="True"


python3 scripts/setup/pre_process_dataset.py \
  --model_type "$model_type" \
  --tokenizer_name "$tokenizer_name" \
  --tratto_model_type "$tratto_model_type" \
  --transformer_type "$transformer_type" \
  --train_path "$train_path" \
  --validation_path "$validation_path" \
  --classification_type "$classification_type" \
  --pre_processing "$pre_processing" \
  --rapids_cudf "$rapids_cudf" \
  --is_multitask "$is_multitask"