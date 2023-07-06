# TRATTO Neural module

TRATTO neural module is a PyTorch model fine-tuned on the task of predicting the next token-class and the next token, given a partial oracle. 
The model is fine-tuned from the pre-trained [CodeBERT](https://huggingface.co/microsoft/codebert-base) model (publicly 
available on [HuggingFace](https://huggingface.co/)).

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

4. Create a new conda environment named "_tratto_" for the **TRATTO** project
   ```bash
    conda create --name tratto
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

9. Execute the following command to grant the premission to run all the bash scripts
   ```shell
   chmod a+x *.sh
   ```

## 2. Models Checkpoints

Execute the following command to download the checkpoints of the final trained models.
   ```shell
   ./init.sh
   ```

**Important note**: if you skipped the instructions to configure the enviroment, remember to execute the command described
at `section 1.4` to grant the permissions to run the bash scripts.

The checkpoints will be saved in the `ml-model` root directory, with the homonym name.

# Training

Run the following command to replicate the training of the `token-classes` model on a `CPU` or on a single `GPU`
   ```shell
   ./train_token_classes_decoder.sh
   ```

Run the following command to replicate the training of the `token-values` model on a `CPU` or on a single `GPU`
   ```shell
   ./train_token_classes_decoder.sh
   ```


If you want to run the training as a background process execute one of the corresponding command:
   ```shell
   nohup ./train_token_classes.sh > log_token_classes.out 2>&1 & echo $! > run_token_classes.pid
   ```
   
   ```shell
   nohup ./train_token_values.sh > log_token_values.out 2>&1 & echo $! > run_token_values.pid
   ```

The command will run the training in background and will create and save the logs in the `log_token_classes.out`  (or `log_token_values.out`)
file and the pid of the process in the `run_token_classes.pid` (or `run_token_values.pid`) file.

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
   http://127.0.0.1:5000/api/next_token?[path_to_file]/[filename].json
   ```
   * **path_to_file** - the absolute path to the directory where the dataset is located.
   * **filename** - the name of the json file containing the dataset
The response to the request contains a string representing the next token of the oracle.
