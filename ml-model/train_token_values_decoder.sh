export PYTHONPATH="./"
python scripts/train/run_classifier.py \
--model_type codet5+ \
--tokenizer_name Salesforce/codet5p-220m \
--model_name_or_path Salesforce/codet5p-220m \
--tratto_model_type token_values \
--task_name tokenValues_classifier_decoder \
--max_seq_length 512 \
--batch_size 24 \
--learning_rate 1e-5 \
--num_epochs 20 \
--save_steps 9000 \
--accumulation_steps 1 \
--train_path ./dataset/token-values-dataset \
--validation_path ./dataset/token-values-dataset \
--output_dir ./output_token_values_decoder \
--classification_type category_prediction