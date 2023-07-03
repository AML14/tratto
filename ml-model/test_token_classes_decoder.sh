python3 test_token_classes_decoder.py \
--model_type codet5 \
--tokenizer_name Salesforce/codet5-base \
--model_name_or_path Salesforce/codet5-base \
--checkpoint_path output_token_classes/checkpoints/lr_1e-05/batch_16/epochs_8/checkpoint_1_1.pt \
--input_path dataset/token-classes-dataset \
--output_path test_token_classes_decoder \
--project_name gs_core \
--classification_type CATEGORY_PREDICTION