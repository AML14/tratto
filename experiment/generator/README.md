# Generator

## Setup

### EvoSuite

To use EvoSuite (which is written in Java 8), the user must set a path to a home directory for JDK 8 in [`evosuite.sh`](evosuite.sh). See [Oracle](https://www.oracle.com/java/technologies/downloads/#java8-linux) for JDK downloads.

### JDoctor

Follow the setup instructions on the [ToRaDoCu](https://github.com/albertogoffi/toradocu) GitHub page. After the build is complete (this may take a few minutes), add the `toradocu-1.0-all.jar` file to the `experiment/generator/resources` directory.

## Overview

This module...

 * generates test oracles using a test oracle generator (TOG).  Each TOG script produces oracles as a list of [`OracleOutput`](../src/main/java/data/OracleOutput.java)'s.
    - `jdoctor.sh`: Generates oracles using JDoctor
    - `toga.sh`: Generates oracles using TOGA
    - `tratto.sh`: Generates oracles using Tratto
 * generates a test suite using EvoSuite.  The `evosuite.sh` script produces output in `experiment/output/evosuite-test/`.


### EvoSuite

To use `evosuite.sh`, run

```shell
bash evosuite.sh [fully qualified name] [path to binaries]
```

For example,

```shell
bash evosuite.sh tutorial.Stack ../src/test/resources/project/target/classes
```

For further detail about the EvoSuite calls abstracted by this script, see the [EvoSuite Tutorial](https://www.evosuite.org/documentation/tutorial-part-1/).

### JDoctor

To use `jdoctor.sh`, run

```shell
bash jdoctor.sh [fully qualified name] [path to source] [path to binaries]
```

For example,

```shell
bash jdoctor.sh tutorial.Stack ../src/test/resources/project/src/main/java ../src/test/resources/project/target/classes
```

The JSON output is stored in `output/jdoctor/jdoctor_output.json`.

### Runner

To use `runner.sh`, run

```shell
bash runner.sh [tog] [path to source] [path to binaries]
```

For example,

```shell
bash runner.sh toga ../src/test/resources/project/src/main/java ../src/test/resources/project/target/classes
```
