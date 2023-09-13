# This script manages the end-to-end experimental analysis.
# To run the experiment, the user provides a specific test oracle generator (TOG) and a source file.

# To analyze a TOG, run
#   experiment.sh [tog] [target-class] [source-dir] [bin-dir]
# where
# - `[tog]` is a test oracle generator
# - `[target-class]` is the fully qualified name of the class under test
# - `[source-dir]` is the path to the project source directory 
# - `[bin-dir]` is the path to the project binary files
# 
# For example,
#   experiment.sh tratto example.Stack ../path/to/src ../path/to/target/classes
