# Generator

## Overview

This module:
 * generates test oracles using a test oracle generator (TOG).  Each TOG script produces oracles as a list of [`OracleOutput`](../src/main/java/OracleOutput.java)'s.
    - `jdoctor.sh`: Generates oracles using JDoctor
    - `toga.sh`: Generates oracles using TOGA
    - `tratto.sh`: Generates oracles using Tratto
 * generates a test suite using EvoSuite.  The `evosuite.sh` script produces output in `experiment/output/evosuite-test/`.
