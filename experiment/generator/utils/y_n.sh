#!/bin/bash
CHOICE="Y"

while true; do
  read -rp "$1" USER_INPUT

  # Check if the user input is empty (just Enter) and set it to 'Y'
  [ -z "$USER_INPUT" ] && USER_INPUT="Y"

  # Convert the user input to lowercase for case-insensitive comparisons
  USER_INPUT=$(echo "$USER_INPUT" | tr '[:upper:]' '[:lower:]')

  # Check if the user input is 'Y' or 'n' (case-insensitive)
  if [[ "$USER_INPUT" == "Y" || "$USER_INPUT" == "y" || "$USER_INPUT" == "Yes" || "$USER_INPUT" == "yes" ]]; then
    CHOICE="Y"
    break
  elif [[ "$USER_INPUT" == "N" || "$USER_INPUT" == "n" ]]; then
    CHOICE="N"
    break
  else
    echo "Invalid input. Please enter 'Y' or 'n'." >&2
  fi
done
echo "${CHOICE}"