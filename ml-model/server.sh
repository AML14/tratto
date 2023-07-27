export PYTHONPATH="./"
python3 scripts/server/server.py \
--port 5000 \
--model_type_token_classes codet5+ \
--model_type_token_values codet5+ \
--classification_type_token_classes CATEGORY_PREDICTION \
--classification_type_token_values CATEGORY_PREDICTION \
--tokenizer_name_token_classes Salesforce/codet5p-220m \
--tokenizer_name_token_values Salesforce/codet5p-220m \
--model_name_or_path_token_classes Salesforce/codet5p-220m \
--model_name_or_path_token_values Salesforce/codet5p-220m \
--checkpoint_path_token_classes checkpoints/token-classes-checkpoint/checkpoint_token_classes.pt \
--checkpoint_path_token_values checkpoints/token-values-checkpoint/checkpoint_token_values.pt