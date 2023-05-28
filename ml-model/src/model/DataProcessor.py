import os
from typing import Type, Dict, Union, Tuple

#sys.path.append(f"{os.path.join(os.path.dirname(os.path.abspath(__file__)), '..', '..')}")

import pandas as pd
import numpy as np
import torch
from torch.utils.data import TensorDataset, ConcatDataset
from sklearn.model_selection import train_test_split, StratifiedKFold

from src.enums.ClassificationType import ClassificationType
from src.enums.DatasetType import DatasetType
from src.enums.ModelType import ModelType


class DataProcessor:
    """
    The *DataProcessor* class takes care of pre-processing the raw datasets to create
    the final datasets, efficiently sorted (with customizable criteria) and tokenized.
    The datasets generated by the class can be feed to the *Dataset* and *DataLoader*
    PyTorch classes to easily load the data and prepare the batches to feed the model.

    Parameters
    ----------
    tokenizer : RobertaTokenizerFast
        The instance of tokenizer used to tokenize the input dataset
    batch_size: int
        The length of each batch of the training and validation dataset

    Attributes
    ----------
    src : list[str]
        The list of input datapoints (strings concatenated).
        This source list is used to train and validate the model.
        Initially set to None
    tgt : list[int]
        The list of expected class values (in one-shot vector format) for each datapoint in input to the model.
        This target list is used to train and validate the model.
        Initially set to None
    tgt_map : Dict[str,list[int]]
        The dictionary is composed of all the unique target values, in string format (as key), and the corresponding
        one-shot vector representation (as value)
    src_test : list[str]
        The list of input datapoints (strings concatenated).
        This source list is used to test the model.
        Initially set to None
    tgt_test : list[int]
        The list of expected values (in one-shot vector format) for a subset of datapoint in input to the model.
        This target list is used to test the model.
        Initially set to None
    processed_dataset: Dict[str,list[list[Union[str,int]]]]
        Dictionary of the processed dataset:
            - b_train_tokenized: contains a list (each element representing a fold) of lists of batches for the training
              dataset, after the original dataset has been splitted (according to *training_ratio* value), grouped in
              batches, and tokenized
            - b_val_tokenized: contains a list (each element representing a fold) of lists of batches for the validation
              dataset, after the original dataset has been splitted (according to *training_ratio* value), grouped in
              batches, and tokenized
            - b_test_tokenized: contains a list of batches for the validation dataset, after the testing dataset has been 
              grouped in batches, and tokenized
            - b_train: contains a list (each element representing a fold) of lists of batches for the training dataset,
              after the original dataset has been splitted (according to *training_ratio* value) and grouped in batches,
              but not tokenized yet
            - b_val: contains a list (each element representing a fold) of lists of batches for the training dataset,
              after the original dataset has been splitted (according to *training_ratio* value) and grouped in batches,
              but not tokenized yet
            - b_test: contains a list of batches for the testing dataset, after the testing dataset has been grouped in batches,
              but not tokenized yet
    """
    def __init__(
            self,
            d_path: str,
            batch_size: int,
            test_ratio: float,
            tokenizer: any,
            n_split: int,
            classification_type: ClassificationType,
            model_type: Type[ModelType]
    ):
        self._d_path = d_path
        self._tokenizer = tokenizer
        self._batch_size = batch_size
        self._test_ratio = test_ratio
        self._df_dataset = self._load_dataset(d_path)
        self._src = None
        self._tgt = None
        self._tgt_map = {}
        self._src_test = None
        self._tgt_test = None
        self._n_split = n_split
        self._classification_type = classification_type
        self._model_type = model_type
        self._processed_dataset = {
            "b_train_tokenized": [],
            "b_val_tokenized": [],
            "b_test_tokenized": [],
            "b_train": [],
            "b_val": [],
            "b_test": []
        }

    def compute_weights(self, column_name: str):
        """
        The method computes the weights to assign a each value of the list passed to the function.
        The weights are used to assign a different importance to each value of the list, in the computation
        of the loss of the classification task, when the dataset is unbalanced.

        Parameters
        ----------
        column_name: str
            The name of the column of the dataset to process
        
        Returns
        -------
        weights : list[float]
            A list containing the values of the weights. The length of the list is equal to the number
            of unique values in the column processed. Each index corresponds to a value
        """
        # Get the list of unique labels and the count the occurrences of each class
        unique_classes, class_counts = np.unique(self._df_dataset[column_name], return_counts=True)
        # Calculate the inverse class frequencies
        total_samples = np.sum(class_counts)
        class_frequencies = class_counts / total_samples
        class_weights = 1.0 / class_frequencies
        # Normalize the class weights
        class_weights /= np.sum(class_weights)
        # Return the computed weights
        return class_weights

    def get_ids_labels(self):
        """
        The method computes the dictionary of labels of the classification model, where the value is the name of a target label,
        while the key element is a numerical identifier representing the index of the one-shot vector representing the class,
        with value equals to 1.0.

        Returns
        -------
        The dictionary of labels. The keys are numerical identifiers (int), while the values are strings representing the
        name of the corresponding target label.
        """
        return { i:k for i,k in enumerate(self._tgt_map.keys()) }

    def get_classes_ids(self):
        """
        The method computes the dictionary of classes of the classification model, where the key is the name of a class,
        while the value element is a numerical identifier representing the codification of a corresponding class.

        Returns
        -------
        The dictionary of classes. The keys are strings representing the name of a class (int), while the values are the
        corresponding numeric codification.
        """
        if self._model_type == ModelType.TOKEN_CLASSES:
            return {k: i for i, k in enumerate(self._df_dataset["tokenClass"].unique())}
        else:
            return {k: i for i, k in enumerate(self._df_dataset["token"].unique())}

    def get_ids_classes(self):
        """
        The method computes the dictionary of classes of the classification model, a numerical identifier representing the
        index of the one-shot vector representing the class while the value element is the name of the corresponding target class.

        Returns
        -------
        The dictionary of classes. The keys are numeric identifiers (integer) representing the codification value of a class (int),
        while the values are the name of the corresponding target class.
        """
        return { i:k for k,i in self.get_classes_ids().items()}

    def get_tgt_classes_size(self):
        """
        The method computes the number of classes of the classification model.

        Returns
        -------
        The number of classes of the classification model.
        """
        classes_size = len(self._tgt_map.keys())
        if classes_size == 0:
            print("[Warn] - classes size is 0")
        return classes_size

    def get_src(self):
        """
        The method returns a copy of the list of input datapoints for training and validation

        Returns
        -------
        src : list[str]
            A copy of the list of input datapoints for training and validation
        """
        src = [*self._src]
        return src

    def get_tgt(self):
        """
        The method returns a copy of the list of target associated to the input datapoints for training and validation

        Returns
        -------
        src : list[str]
            A copy of the list of input datapoints for training and validation
        """
        src = [*self._src]
        return src

    def get_src_test(self):
        """
        The method returns a copy of the list of input datapoints for testing

        Returns
        -------
        src : list[str]
            A copy of the list of input datapoints for testing
        """
        src_test = [*self._src_test]
        return src_test

    def get_tgt_test(self):
        """
        The method returns a copy of the list of the targets associated to the input datapoints for testing

        Returns
        -------
        tgt : list[str]
            A copy of the list of targets associated to the input datapoints for testing
        """
        tgt_test = [*self._tgt_test]
        return tgt_test

    def get_tokenized_dataset(self, d_type: Type[DatasetType], fold_idx: int = 0):
        """
        The method returns the processed tokenized (training or validation) dataset of the requested fold.

        Parameters
        ----------
        d_type: DatasetType
            The dataset type that the method must return
                - DatasetType.TRAINING for the training dataset
                - DatasetType.VALIDATION for the validation dataset
                - DatasetType.TEST for the test dataset
        fold_idx: int
            The index of the fold (0 by default)

        Returns
        -------
        t_dataset : TensorDataset
            A PyTorch TensorDataset composed of three tensors stack:
                - the first tensor stack representing the stack of tokenized input
                  datapoints of the whole sorted dataset
                - the second tensor stack representing the stack of attention masks
                  (each index corresponding to the index of the input datapoints in
                  the first tensor)
                - the third tensor stack representing the list of expected target
                  outputs (each index corresponding to the index of the input
                  datapoints in the first tensor)
        """
        # The list of batches of the tokenized (training or validation) datapoints
        if d_type == DatasetType.TRAINING:
            b_tokenized = self._processed_dataset["b_train_tokenized"][fold_idx]
        elif d_type == DatasetType.VALIDATION:
            b_tokenized = self._processed_dataset["b_val_tokenized"][fold_idx]
        elif d_type == DatasetType.TEST:
            b_tokenized = self._processed_dataset["b_test_tokenized"]
        else:
            raise Exception(f"Unrecognized DataType value: {d_type}")

        t_dataset_list = []
        # The batches are composed of tuples of (inputs, attention_masks, targets) s
        # tacks, where each element of the tuple is a stack of n datapoints, with
        # 1<=n<=*BATCH_SIZE*
        #
        #       t_batch = (
        #           [
        #               [t_i_1_1,...,t_i_1_n],
        #                        ...
        #               [t_i_k_1,...,t_i_k_n]
        #           ],
        #           [
        #               [m_1_1,...,m_1_n],
        #                        ...
        #               [m_k_1,...,m_k_n]
        #           ],
        #           [
        #               [t_1],
        #                ...
        #               [t_n]
        #           ]
        #       )
        #
        for t_batch in b_tokenized:
            # The list of inputs of the current batch
            t_src_batch = t_batch[0]
            # The list of attention masks of the current batch
            t_mask_batch = t_batch[1]
            # The list of targets of the current batch
            t_tgt_batch = t_batch[2]
            # The list of target labels of the current batch
            t_tgt_labels_batch = t_batch[3]
            # Generate a dataset of the batch
            dataset_batch = TensorDataset(
                t_src_batch,
                t_mask_batch,
                t_tgt_batch,
                t_tgt_labels_batch
            )
            # Add the datasets of batches
            t_dataset_list.append(dataset_batch)
        # Concatenates the datasets of batches in a single dataset
        t_dataset = ConcatDataset(t_dataset_list)
        # return the dataset
        return t_dataset
        
    def map_column_values_to_one_shot_vectors(self, column_name: str):
        """
        The method create a dictionary that maps the unique values of a column of the dataset, to the corresponding one-shot
        vectors representations.

        Parameters
        ----------
        column_name: str
            The name of the column of the dataset from which to map the values into the one-shot vector representation
        
        Returns
        -------
        mapping: Dict[obj,list[int]]
            The dictionary that maps the unique values of the column of the dataset, to the corresponding one-shot
            vectors representations.
        """
        # Get unique values
        unique_values = np.unique(self._df_dataset[column_name])
        # Create a dictionary to map string values to their corresponding vector
        mapping = {}
        for i, value in enumerate(unique_values):
            vector = np.zeros(len(unique_values))
            vector[i] = 1.0
            mapping[value] = list(vector)
        return mapping


    def processing(self):
        """
        This represents the core method of the class. Firstly, it generates folds of training and validation datasets,
        stratifying the original dataset to ensure the samples are equally distributed in the training and validation
        datasets. The cross-validation helps to manage the imbalanced dataset. Then, the method generates the training
        and validation batches, for each fold. Finally, it tokenizes the input datapoints of the training and validation
        datasets of each fold.
        """
        # Create the cross-validation splitter
        cross_validation = StratifiedKFold(n_splits=self._n_split, shuffle=True, random_state=42)
        print(f"        Generating {self._n_split} folds for cross-validation.")
        for fold, (t_fold_indices, v_fold_indices) in enumerate(cross_validation.split(self._src, np.array([np.array(dp) for dp in self._tgt]))):
            print(f"            Processing fold {fold + 1}.")
            # Split the dataset into training and validation sets for the current fold
            t_src_fold_data = [self._src[i] for i in t_fold_indices]
            t_tgt_fold_data = [self._tgt[i] for i in t_fold_indices]
            v_src_fold_data = [self._src[i] for i in v_fold_indices]
            v_tgt_fold_data = [self._tgt[i] for i in v_fold_indices]
            # The training and validation datasets are grouped in batches
            t_batches = self._generate_batches(t_src_fold_data, t_tgt_fold_data, self._batch_size)
            v_batches = self._generate_batches(v_src_fold_data, v_tgt_fold_data, self._batch_size)
            self._processed_dataset["b_train"].append(t_batches)
            self._processed_dataset["b_val"].append(v_batches)
            # The batches of datapoints in the training and validation datasets are tokenized
            self._processed_dataset["b_train_tokenized"].append(self._tokenize_batches(t_batches))
            self._processed_dataset["b_val_tokenized"].append(self._tokenize_batches(v_batches))
        # Group test set in batches
        test_batches = self._generate_batches(self._src_test, self._tgt_test, self._batch_size)
        self._processed_dataset["b_test"] = test_batches
        # The batches of datapoints in the test set are tokenized
        self._processed_dataset["b_test_tokenized"] = self._tokenize_batches(test_batches)
        

    def pre_processing(self):
        # drop column id (it is not relevant for training the model)
        self._df_dataset = self._df_dataset.drop(['id'], axis=1)
        # replicate single occurrences
        if self._model_type == ModelType.TOKEN_CLASSES:
            single_occurrence_rows = self._df_dataset[self._df_dataset['tokenClass'].map(self._df_dataset['tokenClass'].value_counts()) <= self._n_split]
        else:
            single_occurrence_rows = self._df_dataset[self._df_dataset['token'].map(self._df_dataset['token'].value_counts()) <= self._n_split]
        for i in range(self._n_split):
            self._df_dataset = pd.concat([self._df_dataset, single_occurrence_rows], ignore_index=True)
        # map empty cells to empty strings
        self._df_dataset.fillna('', inplace=True)
        # specify the type of each column in the dataset
        self._df_dataset = self._df_dataset.astype({
            'label': 'str',
            'oracleId': 'int64',
            'oracleType': 'string',
            'projectName': 'string',
            'packageName': 'string',
            'className': 'string',
            'javadocTag': 'string',
            'methodJavadoc': 'string',
            'methodSourceCode': 'string',
            'classJavadoc': 'string',
            'classSourceCode': 'string',
            'oracleSoFar': 'string',
            'token': 'string',
            'tokenClass': 'string',
            'tokenInfo': 'string'
        })
        # delete the oracle ids and the tgt labels from the input dataset
        df_src = self._df_dataset.drop(['label','oracleId','projectName','classJavadoc','classSourceCode'], axis=1)
        # if the model predicts classes, remove the label from the input
        if self._model_type == ModelType.TOKEN_CLASSES:
            df_src = df_src.drop(['token','tokenInfo'], axis=1)
            if self._classification_type == ClassificationType.CATEGORY_PREDICTION:
                df_src = df_src.drop(['tokenClass'], axis=1)
        else:
            df_src = df_src.drop(['tokenClass'], axis=1)
            if self._classification_type == ClassificationType.CATEGORY_PREDICTION:
                df_src = df_src.drop(['token'], axis=1)

        # The apply function maps each row of the src dataset with multiple columns, to
        # a row with a single column containing the concatenation of the strings of each
        # original column, using a token as a separator.
        #
        # For example:
        #
        #   1. Given the first row of the dataset:
        #
        #         projectName                                 "commons-collections4-4.1"
        #         packageName                          "org.apache.commons.collections4"
        #         className                                                    "Equator"
        #         javadocTag                "@return whether the two objects are equal."
        #         methodJavadoc      "/**\n     * Evaluates the two arguments for th..."
        #         methodSourceCode                         "boolean equate(T o1, T o2);"
        #         classJavadoc       "/**\n * An equation function, which determines..."
        #         oracleSoFar                                                         ""
        #         token                                                              "("
        #         tokenClass                                        "OpeningParenthesis"
        #         tokenInfo                                                           ""
        #         notes                                                               ""
        #
        #   2. The statement gets the values of each column in an array (row.values)
        #
        #         ["commons-collections4-4.1","org.apache.commons.collections4",...,"",""]
        #
        #   3. The join method concatenates all the values in the array into a string,
        #      using a special token (the *cls_token*) as separator.
        #
        #         commons-collections4-4.1<s>org.apache.commons.collections4<s>...<s>OpeningParenthesis<s><s>
        #
        # The result of step (3) represents the content of the unique column of the new
        # map row. The process is repeated for each row in the src dataset.
        df_src_concat = df_src.apply(lambda row: self._tokenizer.cls_token.join(row.values), axis=1)
        # The pandas dataframe is transformed in a list of strings: each string is a input
        # to the model
        src = df_src_concat.to_numpy().tolist()
        # Get the list of target values from the dataframe
        if self._model_type == ModelType.TOKEN_CLASSES:
            tgt = self._df_dataset["tokenClass"].values if self._classification_type == ClassificationType.CATEGORY_PREDICTION else self._df_dataset["label"].values
        else:
            tgt = self._df_dataset["token"].values if self._classification_type == ClassificationType.CATEGORY_PREDICTION else self._df_dataset["label"].values
        # Split the dataset into training and test sets with stratified sampling based on target classes
        self._src, self._src_test, self._tgt, self._tgt_test = train_test_split(src, tgt, test_size=self._test_ratio, stratify=tgt)
        # Generate the mapping of the target column unique values to the corresponding one-shot representations
        if self._model_type == ModelType.TOKEN_CLASSES:
            self._tgt_map = self.map_column_values_to_one_shot_vectors("tokenClass" if self._classification_type == ClassificationType.CATEGORY_PREDICTION else "label")
        else:
            self._tgt_map = self.map_column_values_to_one_shot_vectors("token" if self._classification_type == ClassificationType.CATEGORY_PREDICTION else "label")


    def _generate_batches(self, src_data: list[str], tgt_data: list[list[float]], batch_size: int):
        """
            The method splits the datapoints of the dataset passed to the function, and generate a list of batches. Each
            batch will have length equals to {@code batch_size} (the second parameter passed to the function). The last
            batch contains the rest of the division between the length of the dataset and the batch size (if the rest of
            the division is different from 0).

            Parameters
            ----------
            src_data: list
                The list of input datapoints of the dataset passed to the function
            tgt_data: list
                The list of target datapoints of the dataset passed to the function
            batch_size: int
                The length of each batch

            Returns
            -------
            d_batches : list[list[list[str],list[list[float]]]
                A list of tuples (batches), where the first element of the tuple is the list of string representing
                the input datapoints within the batch, while the second element of the tuple is the list of vectors
                representing the corresponding target datapoints (one-shot vectors) of the input within the same batch
        """
        # check the src and tgt list have the same length
        if len(src_data) != len(tgt_data):
            raise ValueError("Both lists must have the same length.")
        # group datapoints in groups of {@code batch_size} elements
        d_batches = [(src_data[i:i+batch_size],tgt_data[i:i+batch_size]) for i in range(0, len(src_data), batch_size)]
        # return the list of batches
        return d_batches

    def _load_dataset(
            self,
            d_path: str
    ):
        """
            The method applies the dataset preprocessing. It loads dataset from
            specified `d_path`. Drops empty `label` column and fills null values
            with empty strings.

            Parameters
            ----------
            d_path: str
                The path to the dataset

            Returns
            -------
            df_dataset: pandas.DataFrame
                A pandas DataFrame representation of the dataset
        """
        # list of partial dataframes
        dfs = []

        # datasets path
        oracles_dataset = os.path.join(d_path)
        # collects partial dataframes from oracles
        for file_name in os.listdir(oracles_dataset):
            df = pd.read_json(os.path.join(oracles_dataset,  file_name))
            dfs.append(df)
        df_dataset = pd.concat(dfs)
        return df_dataset

    def _tokenize_batches(
            self,
            batches: list[[list[str],list[list[float]]]]
    ):
        """
        The method tokenizes the input and target datapoints of the list of batches passed to the function

        Parameters
        ----------
        batches: list[Tuple[list[str],list[list[float]]]]
            The batches of datapoints and targets. Each element of the list is a batch, in the form of a tuple: the
            first element of the batch contains the inputs, while the second contains the targets within the batch.

        Returns
        -------
        tokenized_batches : list[list[list[list[int]],list[list[float]]]
            A list of tuples (batches), where the first element of the tuple is the list of tokenized strings representing
            the input datapoints within the batch, while the second element of the tuple is the list of vectors
            representing the corresponding target datapoints (one-shot vectors) of the input within the same batch
        """
        # istantiate list of tokenized batches
        tokenized_batches = []
        for batch in batches:
            # Extracts the inputs datapoints from the batch
            b_inputs = batch[0]
            # Extracts the corresponding targets datapoints from the batch
            b_targets = batch[1]
            # Computes the length of the longest input datapoint within the batch
            max_len = 512 #reduce(lambda max_len, s: len(s) if len(s) > max_len else max_len, b_inputs,0)
            # Tokenize the inputs datapoints of the batch
            # The method generate a dictionary with two keys:
            #
            #   t_src_dict = {
            #       "input_ids": [[t_i_1_1,...,t_i_1_n],...,[t_i_k_1,...,t_i_k_n]],
            #       "attention_mask": [[m_1_1,...,m_k_n],...,[m_k_1,...,m_k_n]]
            #   }
            #
            # where each element in the *input_ids* list is a list of tokenized words
            # (the words of an input datapoint), while each element in the *attention
            # masks* is the corresponding mask vector to distinguish the real tokens
            # from the padding tokens. In the example, t_i_x_y is the y tokenized word
            # of the input datapoint x, and m_x_y is a boolean value that states if
            # the token y is a real word or a padding token
            #
            t_src_dict = self._tokenizer(
              b_inputs,
              max_length=max_len,
              padding='max_length',
              truncation=True
            )
            # Transform the list into a tensor stack
            #
            #   t_src_dict['input_ids'] = [[t_i_1_1,...,t_i_1_n],...,[t_i_k_1,...,t_i_k_n]]
            #
            #   t_inputs = tensor([
            #           [t_i_1_1,...,t_i_1_n],
            #                   ...
            #           [t_i_k_1,...,t_i_k_n]
            #   ])
            #
            # this is the structure accepted by the DataLoader, to process the dataset
            t_inputs = torch.stack([torch.tensor(ids) for ids in t_src_dict['input_ids']])
            # Transform the list into a tensor stack
            t_attention_masks = torch.stack([torch.tensor(mask) for mask in t_src_dict['attention_mask']])
            # map targets value into one-shot vectors
            b_targets_one_shot = list(map(lambda t: self._tgt_map[t],b_targets))
            # Transform the targets into a tensor list
            targets_tensor = torch.tensor(b_targets_one_shot)
            # Keep track of labels
            targets_labels = b_targets if self._classification_type == ClassificationType.CATEGORY_PREDICTION else list(map(lambda i: i.split(self._tokenizer.cls_token)[-1], b_inputs))
            classifier_classes_ids = self.get_classes_ids()
            targets_labels_tensor = torch.tensor(list(map(lambda l: classifier_classes_ids[l], targets_labels)))
            # Append triplet to list of tokenized batches
            tokenized_batches.append((t_inputs, t_attention_masks, targets_tensor, targets_labels_tensor))
        # return list of tokenized batches
        return tokenized_batches