export PYTHONPATH="./"
python3 scripts/test/test_single_project.py \
--model_type codet5+ \
--tokenizer_name Salesforce/codet5p-770m \
--model_name_or_path Salesforce/codet5p-770m \
--tratto_model_type token_classes \
--transformer_type decoder \
--checkpoint_path checkpoints/token-classes-checkpoint/pytorch_model.bin \
--input_path ./dataset/cleaned/token-classes-dataset-train/json \
--output_path test_token_classes_decoder \
--project_name gs-core \
--classification_type LABEL_PREDICTION \
--pre_processing "True" \
--rapids_cudf "False"