export PYTHONPATH="./"
python scripts/train/run_classifier.py \
--model_type roberta \
--tokenizer_name roberta-base \
--model_name_or_path microsoft/codebert-base \
--tratto_model_type token_classes \
--task_name tokenClasses_classifier_encoder \
--max_seq_length 512 \
--batch_size 24 \
--learning_rate 1e-5 \
--num_epochs 20 \
--save_steps 9000 \
--accumulation_steps 1 \
--train_path ./dataset/token-classes-dataset/train \
--validation_path ./dataset/token-classes-dataset/validation \
--output_dir ./output_token_classes_encoder \
--classification_type label_prediction