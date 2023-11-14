#!/bin/bash

# Check if a directory path argument is provided
if [ $# -ne 1 ]; then
  echo "Usage: $0 /path/to/your/directory"
  exit 1
fi

directory_path="$1"

# List all ".java" files recursively, exclude "package-info.java" and "module-info.java", and sort by size
file_list=$(find "$directory_path" -type f -name "*.java" ! -name "package-info.java" ! -name "module-info.java" -exec wc -c {} + | sort -h)

# Remove the last row since it is the total size
list_size=$(echo "$file_list" | wc -l)
file_list=$(echo "$file_list" | head -n $((list_size - 1)))

echo "All files:"
echo -e "$file_list"

# Remove top and bottom 5% of the list if possible
list_size=$(echo "$file_list" | wc -l)
if [ "$list_size" -eq 11 ]; then
  # If the list has 11 elements, discard the last one
  file_list=$(echo "$file_list" | head -n $((list_size - 1)))
elif [ "$list_size" -ge 12 ]; then
  # If the list has 12 or more elements, remove 5% at the beginning and 5% at the end
  num_to_remove=$((list_size / 20))
  file_list=$(echo "$file_list" | head -n $((list_size - num_to_remove)) | tail -n $((list_size - 2 * num_to_remove)))
fi
list_size=$(echo "$file_list" | wc -l)

# Select 10 elements evenly spaced
selected_list=""
selected_list+="$(echo "$file_list" | head -n 1)"  # Include the first element
# Calculate the indices for the remaining 8 elements
for ((i = 1; i <= 8; i++)); do
  index=$(printf "%.0f\n" $(echo "($list_size - 1) / 9 * $i + 1" | bc -l))
  selected_list+="\n$(echo "$file_list" | sed -n "${index}p")"
done
selected_list+="\n$(echo "$file_list" | tail -n 1)"  # Include the last element

# Print the selected list
echo "Selected files:"
echo -e "$selected_list"
