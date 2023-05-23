from typing import Type

import torch
from torch.nn import Module, Linear
from transformers import AutoModel


class OracleClassifier(Module):
    """
    The model is composed of:
        1.  The pretrained codeBERT model
        2.  A fully connected layer that takes in input the output from the
            codebert model (which represents our hidden state) and maps this vector
            to a a vector of two elements (representing our 0 and 1 scores).
    N.B.: The vector in output to our model is not normalized, therefore each
          element does not represent the probability that the token is 0 or 1
          by itself. The normalization is performed by the cross entropy loss
          function, which performs the softmax.

    Parameters
    ----------
    linear_size: int
        The output of the last feed-forward layer (the size represents the number of classes)
    max_input_len: int
        The length of the longest input in the whole dataset
    """
    def __init__(
            self,
            linear_size: int,
            max_input_len: int = 512,
    ):
        super(OracleClassifier, self).__init__()
        # First layer of the model: the pre-trained codeBERT model
        self.codebert_transformer = AutoModel.from_pretrained("microsoft/codebert-base")
        # Setup of the pre-trained model layer
        """
        self.codebert_transformer.config.max_position_embeddings = max_input_len
        self.codebert_transformer.base_model.embeddings.position_ids = torch.arange(max_input_len).expand((1, -1))
        self.codebert_transformer.base_model.embeddings.token_type_ids = torch.zeros(max_input_len).expand((1, -1)).int()
        orig_pos_emb = self.codebert_transformer.base_model.embeddings.position_embeddings.weight
        self.codebert_transformer.base_model.embeddings.position_embeddings.weight = torch.nn.Parameter(
            torch.cat((orig_pos_emb, orig_pos_emb))
        )
        """

        # The last layer of the pre-trained codeBERT model. It is necessary to
        # get the size of the output layer and understand the size of the next
        # fully-connected layer (the output vector of the pre-trained codeBERT
        # model will represent the input vector of the fully-connected layer)
        hidden_size = self.codebert_transformer.config.to_dict()['hidden_size']
        # Second layer of the model: the fully-connected layer.
        # The size of the input is equal to the dimension of the output vector
        # of the pre-trained codeBERT model, while the size of the output is
        # a vector of {@code linear_size} elements (the classes of our classifier)
        self.linear = Linear(hidden_size, linear_size)

    def forward(
            self,
            input_ids: Type[torch.Tensor],
            input_masks: Type[torch.Tensor]
    ):
        """
        The method feed the model with the stack of inputs and the attention masks.
        It returns the stack of output vectors of the last fully-connected layer.
        Each output vector of the stack represents the non-normalized vector of
        the probabilities that the corresponding input belongs to each class of
        the classification task.

        For example:

            input_ids = tensor([
                            [t_i_1_1,...,t_i_1_n],
                                     ...
                            [t_i_k_1,...,t_i_k_n]
                        ])

        where each row of the stack is a tokenized input (t_i_1_1 is the first
        token of the first word of the first input of the batch).

            input_masks = tensor([
                            [m_1_1,...,m_1_n],
                                     ...
                            [m_k_1,...,m_k_n]
                          ])

        where each row of the stack is the corresponding attention mask of the
        input with the same index in the input_ids stack.

        The output is a stack of the form:

            output = tensor([
                        [p_1,p_2],
                           ...
                        [p_1,p_2]
                     ])

        where each row of the stack is the corresponding output of the input with
        the same index in the input_ids stack. The row is a vector composed of two
        elements (in our case) and p_1 and p_2 represents the non-normalized
        probabilities that the input belongs respectively to the first and the
        second class of the classificator (the softmax computed in the loss function,
        during the training phase transforms p_1 and p_2 into normalized values,
        i.e. real probability values whose sum is 1).

        Parameters
        ----------
        input_ids: Tensor
            The tensor stack of the inputs within the batch passed to the model
            in the training or validation phase
        input_masks: Tensor
            The tensor stack of the attention masks within the batch passed to
            the model in the training or validation phase

        Returns
        -------
        output: Tensor
            The tensor stack of the outputs of the model
        """
        output = self.codebert_transformer(input_ids, input_masks)
        output = output.pooler_output
        output = self.linear(output)
        return output
