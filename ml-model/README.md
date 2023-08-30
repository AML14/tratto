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

4. Create a new conda environment named "_tratto_" for the **TRATTO** project with Python version 3.9
   ```bash
    conda create --name tratto python=3.9.17
    ```

5. Activate the conda environment
    ```bash
    conda activate tratto
    ```

6. Install [_pip_](https://pip.pypa.io/en/stable/) if not already installed within _conda_
    ```shell
    conda install pip
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

### 1.4 Grant permissions to run bash scripts

9. Execute the following command to grant the permissions to run all the bash scripts
   ```shell
   chmod a+x *.sh
   ```

## 2. Models Checkpoints & Datasets

Execute the following command to download the checkpoints of the final trained models.
   ```shell
   ./init.sh
   ```
The script will run a python script (`scripts/setup/init.py`) to which two optional parameters can be provided:
* `--datasets`: if provided, the script will download the datasets to train the token classes and token values models.
* `--checkpoints`: if provided, the script will download the checkpoints of the finetuned models.

**Important note**:: The datasets can be also generated from the main **Tratto** program. This script will only speed up
the process. The downloaded datasets are exactly the same the main **Tratto** program would generate.

**Important note**: if you skipped the instructions to configure the enviroment (`section 1`), remember to execute the command described
at `section 1.4` to grant the permissions to run the bash scripts.

The datasets and checkpoints will be saved within the `ml-model` root directory, with the homonym names 
(`ml-model/datasets` and `ml-model/checkpoints`).

# Training

The model can be trained both on a CPU (highly not recommended), on a single GPU or multi-GPUs. 
The training process requires a lot of **RAM** memory. In the current implementation, the more the number of GPUs used, 
the more the **RAM** required to upload all the data on the memory.

Run one of the following commands to replicate the training of the `token-classes` or the `token-values` model on a `CPU` or on a single `GPU`
   
   ```shell
   ./train_token_classes_decoder.sh
   ```
   ```shell
   ./train_token_values_decoder.sh
   ```

Run one of the following commands to replicate the training of the `token-classes` model on multi GPUs with 
[accelerate](https://huggingface.co/docs/accelerate/index) and [Fully-Sharded Data Parallel](https://huggingface.co/blog/pytorch-fsdp).

   ```shell
   ./train_token_classes_decoder_accelerate.sh
   ```
   ```shell
   ./train_token_values_decoder_accelerate.sh
   ```

**Important Note**: The accelerate script is highly recommended to finetune the **CodeT5+** `700m` version, due to the huge dimension of the pre-trained model.
The script reads the `accelerate_config_fsdp.yaml` configuration file to split the model on the GPUs available. We provide
the configuration file that we set up to finetune the model on Google Cloud Platform instance equipped with **340GB** of 
system RAM and 4 GPUs **Tesla A-100** with **40GB**. The configuration file can be generated running the accelerate plugin.
The reader can find more information on the FSDP accelerate documentation webpage ([link](https://huggingface.co/docs/accelerate/usage_guides/fsdp)).
If the system fail to run the script with accelerate try to upgrade the torch and torchvision versions with the following
command ([reference](https://github.com/huggingface/accelerate/issues/1473)):

```shell
pip install light-the-torch; ltt install torch torchvision -U
```


**Important Note**: The bash scripts contain a list of different arguments that can be changed to fine-tune the model using
different values of the hyperparameters or classification types (**category** or **label**).

If you want to run the training as a background process execute the following command:
   ```shell
   nohup [script_name] > [log_filename].out 2>&1 & echo $! > [process_number_filename].pid
   ```

where:

* `script_name` is the name of the script to run (for example, `./train_token_classes_decoder.sh`)
* `log_filename` is the name of the textual file where the logs will be saved
* `process_number_filename` is the name of the textual file where the pid number of the generated process will be saved.


## Server

To run the server and perform predictions on new data (for example, in combination with the main tratto program), execute the following
command to start the server:

   ```shell
   ./server.sh
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