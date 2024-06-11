import os
import sys

def rename_files(root_dir):
    for dirpath, dirnames, filenames in os.walk(root_dir):
        for filename in filenames:
            if filename.startswith("baseline"):
                new_filename = "evosuite" + filename[len("baseline"):]
                os.rename(
                    os.path.join(dirpath, filename),
                    os.path.join(dirpath, new_filename)
                )
                print(f'Renamed: {filename} to {new_filename}')

if __name__ == "__main__":
    root_directory = sys.argv[1]
    rename_files(root_directory)