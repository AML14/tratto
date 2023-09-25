#!/bin/bash
# This script generates a test suite for a class using Evosuite.
# Then, for every supported TOG, it creates a variant of teh test suite that uses the TOG's oracle.
# An invocation follows the format,
#     "bash experiment.sh [target-class] [src-dir] [bin-dir] [jar]"
# This script adds the following sub-directories to the output directory, `experiment/output/`:
#     - "evosuite-tests": a test suite generated by EvoSuite
#     - "evosuite-simple-tests": EvoSuite tests split with one assertion per
#                                test
#     - "evosuite-prefixes": EvoSuite simple tests with all assertions removed
#     - "[tog]/input": preprocessed TOG input (if necessary)
#     - "[tog]/oracle": OracleOutputs generated by TOG
#     - "tog-tests/[tog]": a test suite made by inserting TOG oracles into the
#                          EvoSuite prefixes
