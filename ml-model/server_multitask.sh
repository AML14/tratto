export PYTHONPATH="./"

# Common parameters
port="${1:-5000}"

# Parameters for server with multitask model
model_type_multitask="codellama"
tokenizer_name_multitask="codellama/CodeLlama-7b-hf"
model_name_or_path_multitask="codellama/CodeLlama-7b-hf"
checkpoint_path_multitask="path_to_checkpoints"
config_name_or_path_multitask="path_to_config"
transformer_type_multitask="decoder"

python3 scripts/server/server_multitask.py \
  --port "$port" \
  --model_type_multitask "$model_type_multitask" \
  --tokenizer_name_multitask "$tokenizer_name_multitask" \
  --model_name_or_path_multitask "$model_name_or_path_multitask" \
  --config_name_or_path_multitask "$config_name_or_path_multitask" \
  --checkpoint_path_multitask "$checkpoint_path_multitask" \
  --transformer_type_multitask "$transformer_type_multitask" \
  --max_src_length 2015 \
  --max_tgt_length 30