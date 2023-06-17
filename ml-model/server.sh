python3 server.py \
--model_type_token_classes codet5 \
--model_type_token_values codet5 \
--tokenizer_name_token_classes Salesforce/codet5-base \
--tokenizer_name_token_values Salesforce/codet5-base \
--model_name_or_path_token_classes Salesforce/codet5-base \
--model_name_or_path_token_values Salesforce/codet5-base \
--checkpoint_path_token_classes output_token_classes/checkpoints/lr_1e-05/batch_16/epochs_8/checkpoint_1_1.pt \
--checkpoint_path_token_values output_token_values/checkpoints/lr_1e-05/batch_16/epochs_8/checkpoint_1_1.pt