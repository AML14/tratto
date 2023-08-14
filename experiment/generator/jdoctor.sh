# This script generates a list of axiomatic oracles using JDoctor.
# Should output a list of OracleOutput.

# argument check
if [ ! $# -eq 3 ]; then
  echo -e "Incorrect number of arguments. Expected 3 arguments, but got $#".
  exit 1
elif [ ! -d "$2" ]; then
  echo -e "The source directory \"$2\" does not exist."
  exit 1
elif [ ! -d "$3" ]; then
  echo -e "The system binaries path \"$3\" does not exist."
  exit 1
fi
