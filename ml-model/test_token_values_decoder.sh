python3 test_token_values_decoder.py \
--model_type codet5+ \
--tokenizer_name Salesforce/codet5p-220m \
--model_name_or_path Salesforce/codet5p-220m \
--checkpoint_path checkpoints/token_classes/checkpointtoken_classes.pt \
--input_path dataset/token-values-dataset \
--output_path test_token_values_decoder_t5_plus_eligible \
--project_name gs-core \
--classification_type CATEGORY_PREDICTION