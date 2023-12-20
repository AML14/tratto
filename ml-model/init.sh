export PYTHONPATH="./"

# Check the number of parameters
if [ "$#" -gt 2 ]; then
    echo "Error: Too many parameters provided. Maximum allowed is 2 (--datasets, --checkpoints)."
    exit 1
fi

# Initialize variables
datasets=""
checkpoints=""

# Loop through the parameters
while [ "$#" -gt 0 ]; do
    case "$1" in
        --datasets)
            datasets="--datasets"
            ;;
        --checkpoints)
            checkpoints="--checkpoints"
            ;;
        *)
            echo "Error: Unknown parameter '$1'."
            exit 1
            ;;
    esac
    shift
done

if [ -n "$datasets" ]; then
    if [ -n "$checkpoints" ]; then
      python3 scripts/setup/init.py \
      --datasets \
      --checkpoints
    else
      python3 scripts/setup/init.py \
      --datasets
    fi
elif [ -n "$checkpoints" ]; then
    python3 scripts/setup/init.py \
    --checkpoints
else
    python3 scripts/setup/init.py
fi
