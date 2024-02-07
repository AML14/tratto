export PYTHONPATH="./"

model_type="codet5+"
tokenizer_name="Salesforce/codet5p-770m"
model_name_or_path="Salesforce/codet5p-770m"
tratto_model_type="oracles"
transformer_type="decoder"
task_name="oraclesModel_classifier_decoder"
max_seq_length=512
batch_size=16
learning_rate=1e-5
num_epochs=5
checkpoint_steps=500
accumulation_steps=1
train_path="./dataset/cleaned/oracles-dataset-train/json"
validation_path="./dataset/cleaned/oracles-dataset-validation/json"
output_dir="./output_oracles_model_decoder_label_770"
classification_type="label_prediction"
pre_processing="True"
save_pre_processing="True"
rapids_cudf="False"

if [ "$#" -eq 1 ] && [ "$1" == "--accelerate" ]; then
  echo "Running with accelerate"
  accelerate launch --config_file accelerate_config_fsdp.yaml scripts/train/run_classifier_accelerate.py \
    --model_type "$model_type" \
    --tokenizer_name "$tokenizer_name" \
    --model_name_or_path "$model_name_or_path" \
    --tratto_model_type "$tratto_model_type" \
    --transformer_type "$transformer_type" \
    --task_name "$task_name" \
    --max_seq_length "$max_seq_length" \
    --batch_size "$batch_size" \
    --learning_rate "$learning_rate" \
    --num_epochs "$num_epochs" \
    --checkpoint_steps "$checkpoint_steps" \
    --accumulation_steps "$accumulation_steps" \
    --train_path "$train_path" \
    --validation_path "$validation_path" \
    --output_dir "$output_dir" \
    --classification_type "$classification_type" \
    --pre_processing "$pre_processing" \
    --rapids_cudf "$rapids_cudf" \
    --save_pre_processing "$save_pre_processing"
else
  echo "Running without accelerate"
  python3 scripts/train/run_classifier.py \
    --model_type "$model_type" \
    --tokenizer_name "$tokenizer_name" \
    --model_name_or_path "$model_name_or_path" \
    --tratto_model_type "$tratto_model_type" \
    --transformer_type "$transformer_type" \
    --task_name "$task_name" \
    --max_seq_length "$max_seq_length" \
    --batch_size "$batch_size" \
    --learning_rate "$learning_rate" \
    --num_epochs "$num_epochs" \
    --checkpoint_steps "$checkpoint_steps" \
    --accumulation_steps "$accumulation_steps" \
    --train_path "$train_path" \
    --validation_path "$validation_path" \
    --output_dir "$output_dir" \
    --classification_type "$classification_type" \
    --pre_processing "$pre_processing" \
    --rapids_cudf "$rapids_cudf" \
    --save_pre_processing "$save_pre_processing"
fi