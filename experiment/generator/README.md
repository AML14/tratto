# Generator

## Overview

This module generates test oracles using a test oracle generator (TOG), and also generate a full test suite using EvoSuite. Each TOG has an individual shell script, named after the TOG, to generate oracles.  Each TOG script produces oracles as a list of [`OracleOutput`](../src/main/java/OracleOutput.java)'s:
- `jdoctor.sh`: Generates oracles using JDoctor
- `toga.sh`: Generates oracles using TOGA
- `tratto.sh`: Generates oracles using Tratto

The `evosuite.sh` script generates a full test suite using EvoSuite, and saves the output to `experiment/output/evosuite-test/`.
