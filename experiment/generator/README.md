# Generator

## Overview

This module uses shell scripts to generate oracles using a test oracle generator (TOG), and also generate a full test suite using EvoSuite. Each TOG has an individual shell script, named accordingly, to generate oracles catered to its specific implementation (e.g. `tratto.sh`). The `evosuite.sh` script generates a full test suite using EvoSuite, and saves the output to `experiment/output/evosuite-tests`.

We provide a brief overview of the relevant scripts:

- `evosuite.sh`: generates a test suite using EvoSuite 
- `jdoctor.sh`: generates oracles using JDoctor
- `toga.sh`: generates oracles using TOGA
- `tratto.sh`: generates oracles using Tratto

Each TOG outputs oracles as a list of [`OracleOutput`](../src/main/java/OracleOutput.java) objects.

## Setup

To use EvoSuite (which is written in Java 8), the user must set a path to a home directory for JDK 8 in [`evosuite.sh`](evosuite.sh). See [Oracle](https://www.oracle.com/java/technologies/downloads/#java8-linux) for JDK downloads.