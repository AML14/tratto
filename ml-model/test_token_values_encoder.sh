export PYTHONPATH="./"
python3 scripts/test/test_single_project.py \
--model_type roberta \
--tokenizer_name roberta-base \
--model_name_or_path microsoft/codebert-base \
--tratto_model_type token_values \
--checkpoint_path checkpoints/token-values-checkpoint/checkpoint_token_values.pt \
--input_path dataset/token-values-dataset \
--output_path test_token_values_encoder \
--project_name gs-core \
--classification_type LABEL_PREDICTION