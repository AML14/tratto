# Token-Class Neural module

Token-Class neural module is a PyTorch model fine-tuned on the task of predicting the next token-class, given a partial oracle. 
The model is fine-tuned from the pre-trained [CodeBERT](https://huggingface.co/microsoft/codebert-base) model (publicly 
available on [HuggingFace](https://huggingface.co/)).

## Installation

In order to set up the environment is not strictly necessary, but recommended to create a [_venv_](https://docs.python.org/3/library/venv.html) 
or [_conda_](https://docs.conda.io/en/latest/) environment. 
In the following section we will provide the general information to configure the project on a _conda_ environment.

### Install Conda

The reader can find all the instructions to properly install and setup _conda_, on the official [user guide](https://docs.conda.io/projects/conda/en/stable/user-guide/install/index.html).

We provide an example on how to install conda on Linux operating systems. The installation is relatively similar for
macOS and Windows systems.

1. Open a terminal and type the following command to download the installer:
    ```bash
    wget -P [path_to_destination_directory] [conda_download_http_link]
    ```
    * **path_to_destination_directory** - represents the destination path where the installer will be downloaded.
    * **conda_download_http_link** - is the link of the installer. You can choose the installer [here](https://docs.conda.io/en/latest/miniconda.html#linux-installers), copying the corresponding url.

2. Execute the installer:
    ```bash
    bash [fullname_of_the_installer]
    ```
   * **fullname_of_the_installer** - is the whole name of the downloaded installer (it is a _.sh_ file, like for example
     _Miniconda3-py39_23.3.1-0-Linux-aarch64.sh_)

3. Close and re-open the terminal at the end of the installation. Check _conda_ has been successfully installated typing the command:
    ```bash
    conda --version
    ```

### Create and activate _conda_ environment

4. Create a new conda environment for the **TRATTO** project
   ```bash
    conda create --name [name_of_the_environment]
    ```
   * **name_of_the_environment** - the name of the environment (for example _tratto_)

5. Activate the conda environment
    ```bash
    conda activate [name_of_the_environment]
    ```
    Note tha in some Linux distro, the command must be executed without the prefix `conda`:
    ```bash
    activate [name_of_the_environment]
    ```

6. Install [_pip_](https://pip.pypa.io/en/stable/) if not already installed within _conda_
    ```bash
    conda install pip
    ```

### Install the requirements
  
7. Move to the `ml-model` folder of the `tratto` project
    ```bash
    cd [path_to_the_ml-model_folder]
    ```
    
8. Install all the required dependencies
    ```bash
    pip install -r requirements.txt
    ```

## Training

Run the following command to start training the model on a `CPU` or on a single `GPU`
    
```bash
python3 cb_finetuning.py
```

Run the following command to start training the model on multiple `GPUs`, instead.

```bash
python3 cb_finetuning_gpu_distributed.py
```

If you want to run the training as a background process:

```bash
nohup python3 cb_finetuning.py > log.out 2>&1 & echo $! > run.pid
```
```bash
nohup python3 cb_finetuning_gpu_distributed.py > log.out 2>&1 & echo $! > run.pid
```

The command will run the training in background and will create and save the logs in the `log.out` file and the pid of the process 
in the `run.pid` file.