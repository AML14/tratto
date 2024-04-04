# TRATTO Neural module

TRATTO neural module is a PyTorch model fine-tuned on the task of predicting the next token-class and the next token, given a partial oracle.
The model is fine-tuned from the pre-trained [CodeT5+](https://github.com/salesforce/CodeT5/tree/main/CodeT5+). Different
pre-trained models of the Codet5+ family are available (`200m`, `700m`, `2b`, `6b`, `16b`). Given our hardware resources,
we operated the finetuning  on the `200m` and `700m`, achieving the best results on the `700m` version.

# Setup

## 1. Environment

In order to set up the environment is not strictly necessary, but recommended to create a [_venv_](https://docs.python.org/3/library/venv.html)
or [_conda_](https://docs.conda.io/en/latest/) environment.
In the following section we will provide the general information to configure the project on a _conda_ environment.

### 1.1 Install Conda

The reader can find all the instructions to properly install and setup _conda_, on the official [user guide](https://docs.conda.io/projects/conda/en/stable/user-guide/install/index.html).

We provide an example on how to install conda on Linux operating systems. The installation is relatively similar for
macOS and Windows systems.

1. Open a terminal and type the following command to download the installer:
    ```shell
    wget -P [path_to_destination_directory] [conda_download_http_link]
    ```
    * **path_to_destination_directory** - represents the destination path where the installer will be downloaded.
    * **conda_download_http_link** - is the link of the installer. You can choose the installer [here](https://docs.conda.io/en/latest/miniconda.html#linux-installers), copying the corresponding url.

2. Execute the installer:
    ```shell
    bash [fullname_of_the_installer]
    ```
   * **fullname_of_the_installer** - is the whole name of the downloaded installer (it is a _.sh_ file, like for example
     _Miniconda3-py39_23.3.1-0-Linux-aarch64.sh_)

3. Close and re-open the terminal at the end of the installation. Check _conda_ has been successfully installated typing the command:
    ```shell
    conda --version
    ```

### 1.2 Create and activate _conda_ environment

4. Create a new conda environment named "_tratto_" for the **TRATTO** project with Python version 3.9 and pip.
   ```bash
    conda create --name tratto python=3.9 pip
    ```

5. Activate the conda environment
    ```bash
    conda activate tratto
    ```

### 1.3 Install the requirements

7. Move to the `ml-model` folder of the `tratto` project
    ```bash
    cd [path_to_the_tratto_folder]/ml-model
    ```

8. Install all the required dependencies
    ```shell
    pip install -r requirements.txt
    ```

9. If you have the GPU available, and you want to use the RAPIDS library to perform pandas operations on the GPU, install the `cudf` library and its dependencies, following the instructions on the official documentation ([link](https://docs.rapids.ai/install?_gl=1*1awki0p*_ga*MTQ2NjU1MDc5MS4xNzAyNjQzMzM4*_ga_RKXFW6CM42*MTcwNDg3OTE3My40LjEuMTcwNDg3OTE4OS40NC4wLjA.)).

   Otherwise, install the additional requirements with the following command:
    ```shell
    pip install -r additional_requirements.txt
    ```

10. If you plan to use HuggingFace [accelerate](https://huggingface.co/docs/accelerate/index) to train big models on multi-GPUs, install the following requirements:
    ```shell
    pip install light-the-torch; ltt install torch torchvision -U
    ```

## 2. Models Checkpoints & Datasets

Execute the following command to download the datasets (and/or the checkpoints of the final trained models).
   ```shell
   bash init.sh [--datasets] [--checkpoints]
   ```
The script will run a python script (`scripts/setup/init.py`) to which two optional parameters can be provided:
* `--datasets`: if provided, the script will download the datasets to train the token classes and token values models.
* `--checkpoints`: if provided, the script will download the checkpoints of the finetuned models.

Execute the following command to prepare the datasets for the training of the models.
   ```shell
   bash prepare_datasets.sh
   ```
The script generate both a json and a csv version of the prepared datasets. The .csv format is necessary to train the model
using the CUDF library (the `read_json` method of the `CUDF` library is not able to exploit the GPU yet).

**Important note**:: The datasets can be also generated from the main **Tratto** program. This script will only speed up
the process. The downloaded datasets are exactly the same the main **Tratto** program would generate.

The datasets and checkpoints will be saved within the `ml-model` root directory, with the homonym names
(`ml-model/datasets` and `ml-model/checkpoints`).


# 3. Training

The model can be trained both on a CPU (highly not recommended), on a single GPU or multi-GPUs.

Run the following command to replicate the training of the `oracles`, `token-classes` or the `token-values` model.
   ```shell
   bash ./train_model.sh [--accelerate]
   ```

The command accepts the following optional parameter:
* `--accelerate`: if provided, the script execute the training on multi GPUs with [accelerate](https://huggingface.co/docs/accelerate/index) and [Fully-Sharded Data Parallel](https://huggingface.co/blog/pytorch-fsdp).

If you want to run the training as a background process execute the following command:
   ```shell
   nohup [script_name] > [log_filename].out 2>&1 & echo $! > [process_number_filename].pid
   ```
where:

* `script_name` is the name of the script to run (for example, `./train_token_classes_decoder.sh`)
* `log_filename` is the name of the textual file where the logs will be saved
* `process_number_filename` is the name of the textual file where the pid number of the generated process will be saved.

The bash scripts contain a list of different arguments that can be changed to fine-tune the model using
different values of the hyperparameters or classification types (**category** or **label**). The list of the arguments and the
relative description is reported below.

```bash
--model_type [ The model type, defined and configured in the `src/pretrained/ModelClasses.py` file - e.g. codet5+ ] \
--tokenizer_name [ The name of the tokenizer, from HuggingFace - e.g. Salesforce/codet5p-770m ] \
--model_name_or_path [ The name of the pre-trained model, from HuggingFace - e.g. Salesforce/codet5p-770m ] \
--tratto_model_type [ oracles, token_classes, or token_values ] \
--task_name [ Custom name - e.g. oraclesModel_classifier_decoder ] \
--max_src_length [ Max number of input tokens to the model - e.g. 512 ] \
--batch_size [ Batch size to train the model - e.g. 16] \
--learning_rate [ The learning rate during the training phase - e.g. 1e-5 ] \
--num_epochs [ Number of epochs to train the model - e.g. 20 ] \
--checkpoint_steps [ Checkpoint interval before to prform validation and save current model state - e.g. 500 ] \
--accumulation_steps [ Number of steps before to perform backpropagation - e.g. 1 ] \
--train_path [ Path to the training dataset - e.g. ./dataset/cleaned/oracles-dataset-train/csv ] \
--validation_path [ Path to the validation dataset - e.g. ./dataset/cleaned/oracles-dataset-validation/csv ] \
--output_dir [ Path to the output directory where to save the checkpoints - e.g. ./output_oracles_model_decoder_label_770 ] \
--classification_type [ category_prediction, or label_prediction ]
```

The model can be trained both as an `Encoder`, `Decoder` or `Encoder-Decoder`.
To train the model as an encoder, set the parameter `--transformer_type` to `encoder`, Otherwise, set it to `decoder`.
(The value should be consistent with the HuggingFace pre-trained model type selected).

The type of model to train can be set with the parameter `--tratto_model_type`: set it to `oracles` to train the model 
that predict if an oracle must be generated or not, `token_classes` to train the model for the `token classes` prediction, 
or `token_values` to train the model for the token values prediction.

Finally, the type of classification can be set with the parameter `--classification_type`: set it to `category_prediction`
to train the model for the category prediction (the list of `token classes`), or `label_prediction` to train the model 
for the label prediction (`True` or `False`). The only models that can be trained with the `category_prediction` are the
`token-classes` and `token-values` models. However, is highly recommend to train the model in this configuration only for
the `token-classes` model, since the list of classes is fixed and known in advance. 
Both the classification types are suitable for the training with an `Encoder`, `Decoder`, or `Encoder-Decoder` model.

**Important Note**: The accelerate script is highly recommended to finetune the **CodeT5+** `770m` version, due to the huge dimension of the pre-trained model.
The script reads the `accelerate_config_fsdp.yaml` configuration file to split the model on the GPUs available. We provide
the configuration file that we set up to finetune the model on Google Cloud Platform instance equipped with **340GB** of
system RAM and 4 GPUs **Tesla A-100** with **40GB**. The configuration file can be generated running the accelerate plugin.
The reader can find more information on the FSDP accelerate documentation webpage ([link](https://huggingface.co/docs/accelerate/usage_guides/fsdp)).
If the system fail to run the script with accelerate try to upgrade the torch and torchvision versions with the following
command ([reference](https://github.com/huggingface/accelerate/issues/1473)):

```shell
pip install light-the-torch; ltt install torch torchvision -U
```

**Important note**: The model name and the tokenizer name are taken from the HuggingFace models collection, and must be 
consistent with the model type defined and configured in the `src/pretrained/ModelClasses.py` file.

**Important note**: Remember to set the path to `csv` version of the datasets if you want to train the model using the `CUDF` library (otherwise use the `json` version).


## 4. Test the model (Inference)

To test the model on a given test dataset of a project, execute the following command:
   ```shell
   bash test_model.sh
   ```

The script for the inference of the model requires the following arguments:

```bash
--model_type [ The model type, defined and configured in the `src/pretrained/ModelClasses.py` file - e.g. codet5+ ] \
--tokenizer_name [ The name of the tokenizer, from HuggingFace - e.g. Salesforce/codet5p-770m ] \
--model_name_or_path [ The name of the pre-trained model, from HuggingFace - e.g. Salesforce/codet5p-770m ] \
--tratto_model_type [ oracles, token_classes, or token_values ] \
--checkpoint_path [ Path to the checkpoint to load and use for inference - e.g. checkpoints/token-classes-checkpoint/classes_decoder_category_770/pytorch_model.bin ] \
--input_path [ Path to the test dataset - e.g. dataset/token-classes-dataset] \
--output_path [ Path to the output directory where to save the results of the test - test_token_classes_decoder_category_770 ] \
--project_name [ Name of the project to analyze. It must match the name on the dataset to load - e.g. gs-core ] \
--classification_type [ category_prediction, or label_prediction ]
```

The script supposes that the test dataset is a folder containing single/multiple files in json or csv format.
The name of each file must contain the name of the project to analyze, e.g.:

```bash
dataset_dir
├── project_gs-core_file_1.json
├── project_gs-core_file_2.json
├── project_gs-core_file_3.json
├── ...
```

or

```bash
dataset_dir
├── project_gs-core_file_1.csv
├── project_gs-core_file_2.csv
├── project_gs-core_file_3.csv
├── ...
```

if the project name is `gs-core`.


## 5. Server

To run the server and perform predictions on new data (for example, in combination with the main tratto program), execute the following
command to start the server:

   ```shell
   bash server.sh
   ```

The command will instantiate the server, listening on the port printed within the terminal (`http://127.0.0.1:5000` by default).
The server implements a single REST API endpoint (`/api/next_token`) that receive as a single parameter the path to the file
where the dataset for the next token to compute is located (the file must be in `json` format):

   ```http request
   http://127.0.0.1:5000/api/next_token?filename=[path_to_file]/[filename].json
   ```
   * **path_to_file** - the absolute path to the directory where the dataset is located.
   * **filename** - the name of the json file containing the dataset.

The response to the request contains a string representing the next token class and token value of the oracle.

**Important Note**: The bash scripts contain a list of different arguments that can be changed to use different models
for the **token classes** and the **token values**.

