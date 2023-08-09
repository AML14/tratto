export PYTHONPATH="./"
accelerate launch --config_file accelerate_config_fsdp.yaml scripts/test/test_single_project_accelerate.py \
--model_type codet5+ \
--tokenizer_name Salesforce/codet5p-770m \
--model_name_or_path Salesforce/codet5p-770m \
--checkpoint_path output_token_classes_decoder_label_700/checkpoints/lr_1e-05/batch_12/epochs_10/state/state_checkpoint_1_10000/pytorch_model.bin \
--input_path dataset/token-classes-dataset-cleaned \
--output_path test_token_classes_decoder \
--project_name gs-core \
--classification_type LABEL_PREDICTION