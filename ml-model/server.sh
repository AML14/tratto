python3 server.py \
--model_type_token_classes codet5+ \
--model_type_token_values codet5+ \
--tokenizer_name_token_classes Salesforce/codet5p-220m \
--tokenizer_name_token_values Salesforce/codet5p-220m \
--model_name_or_path_token_classes Salesforce/codet5p-220m \
--model_name_or_path_token_values Salesforce/codet5p-220m \
--checkpoint_path_token_classes checkpoints/token_classes/checkpoint_token_classes.pt \
--checkpoint_path_token_values checkpoints/token_values/checkpoint_token_values.pt