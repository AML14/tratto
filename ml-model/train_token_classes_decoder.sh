export PYTHONPATH="./"
python scripts/train/run_classifier.py \
--model_type codet5+ \
--tokenizer_name Salesforce/codet5p-220m \
--model_name_or_path Salesforce/codet5p-220m \
--tratto_model_type token_classes \
--task_name tokenClasses_classifier_decoder \
--max_seq_length 512 \
--batch_size 24 \
--learning_rate 1e-5 \
--num_epochs 20 \
--save_steps 9000 \
--accumulation_steps 1 \
--train_path ./dataset/token-classes-dataset/train \
--validation_path ./dataset/token-classes-dataset/validation \
--output_dir ./output_token_classes_decoder \
--classification_type label_prediction