CUDA_LAUNCH_BLOCKING=1 

python cb_finetuning_new.py \
--model_type roberta \
--task_name tokenClasses_classifier \
--max_seq_length 512 \
--batch_size 8 \
--learning_rate 1e-5 \
--num_epochs 8 \
--save_steps 200 \
--accumulation_steps 1 \
--data_dir ./token-classes-dataset \
--output_dir ./output \
--model_name_or_path microsoft/codebert-base \
--classification_type LABEL_PREDICTION
