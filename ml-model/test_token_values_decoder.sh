export PYTHONPATH="./"
python3 scripts/test/test_single_project.py \
--model_type codet5+ \
--tokenizer_name Salesforce/codet5p-770m \
--model_name_or_path Salesforce/codet5p-770m \
--checkpoint_path checkpoints/token-values-checkpoint/values_decoder_category_770/pytorch_model.bin \
--input_path dataset/token-values-dataset \
--output_path test_token_values_decoder_label_770 \
--project_name gs-core \
--classification_type LABEL_PREDICTION \
--tratto_model_type token_values