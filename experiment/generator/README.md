# Generator

### JDoctor

Follow the setup instructions on the [ToRaDoCu](https://github.com/albertogoffi/toradocu) GitHub page. After the build is complete, add the `toradocu-1.0-all.jar` file to the `experiment/generator/resources` directory.

## Overview

This module uses shell scripts to generate test oracles using a test oracle generator (TOG), and also generate a full test suite using EvoSuite. Each TOG has an individual shell script, named after the TOG, to generate oracles based on its implementation (e.g. `tratto.sh`). The `evosuite.sh` script generates a full test suite using EvoSuite, and saves the output to `experiment/output/evosuite-test/`.

We provide a brief overview of the relevant scripts:

- `evosuite.sh`: Generates a test suite using EvoSuite 
- `jdoctor.sh`: Generates oracles using JDoctor
- `toga.sh`: Generates oracles using TOGA
- `tratto.sh`: Generates oracles using Tratto

Each TOG script produces oracles as a list of [`OracleOutput`](../src/main/java/OracleOutput.java)'s.
