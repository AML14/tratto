export PYTHONPATH="./"
python3 scripts/train/run_classifier.py \
--model_type codet5+ \
--tokenizer_name Salesforce/codet5p-770m \
--model_name_or_path Salesforce/codet5p-770m \
--tratto_model_type oracles \
--task_name oraclesModel_classifier_decoder \
--max_seq_length 512 \
--batch_size 16 \
--learning_rate 1e-5 \
--num_epochs 5 \
--save_steps 500 \
--accumulation_steps 1 \
--train_path ./dataset/cleaned/oracles-dataset-train/csv \
--validation_path ./dataset/cleaned/oracles-dataset-validation/csv \
--output_dir ./output_oracles_model_decoder_label_770 \
--classification_type label_prediction