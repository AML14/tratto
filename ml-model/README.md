# TRATTO Neural Module

TRATTO neural module is a PyTorch model fine-tuned on the task of generating assertion oracles, from the pre-trained 
[CodeBERT](https://huggingface.co/microsoft/codebert-base) model (publicly available on [HuggingFace](https://huggingface.co/)).

# Experiments

1. One experiment could be to try the model with two different type of inputs:
   * Considering the whole set of features
   * Removing the less relevant features to see how it behaves with respect to whole set of features
   These experiments could help us to understand the importance given by the model to the relevant and less relevant features.

2. Moreover, it would be nice if we could mutate slightly some inputs in the test dataset, 
   to check if the model generalize well or not. 

3. We could make individual encoders for separate groups of data (e.g. one for “more relevant” features and one 
   for “less relevant” features) and concatenate/weight their outputs. This could also help overcome the limitations of 
   token size (512) on CodeBert. Using multiple transformers could potentially create better results (although it may 
   just cause overfitting), but it would definitely be more costly.