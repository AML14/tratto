# Generator

## Overview

This module uses shell scripts to generate test oracles using a test oracle generator (TOG), and also generate a full test suite using EvoSuite. Each TOG has an individual shell script, named after the TOG, to generate oracles catered to its implementation (e.g. `tratto.sh`). The `evosuite.sh` script generates a full test suite using EvoSuite, and saves the output to `experiment/output/evosuite-test/`.

We provide a brief overview of the relevant files:

- `evosuite.sh`: Generates a test suite using EvoSuite 
- `jdoctor.sh`: Generates a list of oracles using JDoctor
- `toga.sh`: Generates a list of oracles using TOGA
- `tratto.sh`: Generates a list of oracles using Tratto
