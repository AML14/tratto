#!/bin/bash
# This script read a Yes/No choice from a prompted input and return the choice.
# If the input contains a value different from Yes/No, it repeats the question.

# Local variables
choice="Y"

# Prompt the input and capture the user choice
while true; do
  read -rp "To proceed it is necessary to install the python packages from the requirements.txt of the repository. Proceed? (Y/n):" USER_INPUT
  # Check if the user input is empty (just Enter) and set it to 'Y'
  [ -z "$USER_INPUT" ] && USER_INPUT="Y"
  # Convert the user input to lowercase for case-insensitive comparisons
  USER_INPUT=$(echo "$USER_INPUT" | tr '[:upper:]' '[:lower:]')
  # Check if the user input is 'Y' or 'n' (case-insensitive)
  if [[ "$USER_INPUT" == "Y" || "$USER_INPUT" == "y" || "$USER_INPUT" == "Yes" || "$USER_INPUT" == "yes" ]]; then
    choice="Y"
    break
  elif [[ "$USER_INPUT" == "N" || "$USER_INPUT" == "n" ]]; then
    choice="N"
    break
  else
    echo "Invalid input. Please enter 'Y' or 'n'." >&2
  fi
done
echo "pluto"
# Return the Yes/No choice
echo "${choice}"