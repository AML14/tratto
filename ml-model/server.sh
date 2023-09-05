export PYTHONPATH="./"
PORT="${1:-5000}"
python3 scripts/server/server.py \
--port "$PORT" \
--model_type_token_classes codet5+ \
--model_type_token_values codet5+ \
--classification_type_token_classes LABEL_PREDICTION \
--classification_type_token_values LABEL_PREDICTION \
--tokenizer_name_token_classes Salesforce/codet5p-770m \
--tokenizer_name_token_values Salesforce/codet5p-770m \
--model_name_or_path_token_classes Salesforce/codet5p-770m \
--model_name_or_path_token_values Salesforce/codet5p-770m \
--checkpoint_path_token_classes checkpoints/token-classes-checkpoint/pytorch_model.bin \
--checkpoint_path_token_values checkpoints/token-values-checkpoint/pytorch_model.bin