export PYTHONPATH="./"
accelerate launch --config_file accelerate_config_fsdp.yaml scripts/test/test_single_project_accelerate.py \
--model_type codet5+ \
--tokenizer_name Salesforce/codet5p-770m \
--model_name_or_path Salesforce/codet5p-770m \
--checkpoint_path checkpoints/token-classes-checkpoint/classes_decoder_category_770/pytorch_model.bin \
--input_path dataset/token-classes-dataset \
--output_path test_token_classes_decoder_label_770 \
--project_name gs-core \
--classification_type LABEL_PREDICTION