if [[ $(uname) == "Darwin" || $(uname) == "Linux" ]]; then
    SEPARATOR="/"
else
    SEPARATOR="\\"
fi
CHOICE=$(bash ./generator/utils/y_n.sh "Do you want to run toga with the current conda environment? (Y/n): ")