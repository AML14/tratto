# Experiment

----

This module has all scripts necessary to reproduce the experimental results described in the paper "Tratto: A Neuro-Symbolic Approach to Deriving Axiomatic Test Oracles".

[//]: # (Add link to paper when available)

# 1. Setup

---

This section outlines all requirements and corresponding setup instructions for each tool used in the experimental pipeline. After the setup is complete, the user should be able to run each example command in the [Run the experiments](#3-run-the-experiments) section.

## 1.1. Evosuite

### 1.1.1. Java 8

We use EvoSuite to generate test prefixes, which is written in Java 8. However, the experiment module uses Java 17. To run EvoSuite (and similarly, JDoctor), the user must configure a home directory for a local JDK 8 version. See [Oracle](https://www.oracle.com/java/technologies/downloads/#java8-linux) for JDK downloads (you may need an Oracle account to download older versions). Then, add the JDK to the `./generator/resources` directory. In `./generator/evosuite.sh`, modify the field at the top of the script, `JDK8_NAME`, to the name of the local JDK directory in `./generator/resources`. By default, the script searches for `jdk-1.8.jdk` (alternatively, you may rename your local JDK directory to match this name).

## 1.2. JDoctor

### 1.2.1. ToRaDoCu

To set up JDoctor for analysis, visit the [ToRaDoCu](https://github.com/albertogoffi/toradocu) GitHub page, and follow the setup
instructions to build the `toradocu-1.0-all.jar` file (may take a few minutes). Then, move the jar file to the `./generator/resources` directory.

### 1.2.2. Java 8

JDoctor is written in Java 8. Please complete the above [EvoSuite Java 8 setup](#111-java-8) instructions before continuing. Similar to the EvoSuite setup, modify the field at the top of the `./generator/jdoctor.sh` script, `JDK8_NAME`, to the name of the local JDK 8 directory in `./generator/resources`.

## 1.3. Toga

### 1.3.1. Git Large File Storage

Toga requires Git Large File Storage (Git LFS) to set up its environment. See the [Git LFS homepage](https://git-lfs.com/) for setup instructions.

### 1.3.2. Conda (recommended)

Toga is written in `Python 3.8` and requires the user to install various python packages (automated by `./generator/toga.sh` script). A package management system, such as conda, is recommended (but not required) to create an isolated environment to run the Toga experiments (and debug any potential errors). See the [Miniconda homepage](https://docs.conda.io/projects/miniconda/en/latest/) for setup instructions.

## 1.4. Tratto

### 1.4.1 Conda (recommended)

Similar to Toga, a package management system, such as conda, is recommended (but not required) to create an isolated environment to run the Tratto experiments (and debug any potential errors). See the [Miniconda homepage](https://docs.conda.io/projects/miniconda/en/latest/) for setup instructions. Otherwise, the setup is fully automated by `./generator/tratto.sh`.

## 1.5 Runner

### 1.5.1 Java 8

Please complete the above [EvoSuite Java 8 setup](#111-java-8) instructions before continuing. Similar to the EvoSuite and JDoctor setup, modify the field at the top of the `./runner.sh` script, `JDK8_NAME`, to the name of the local JDK 8 directory in `./generator/resources`.

# 2. Overview

----

This module automates the experimental analysis of a test oracle generator (TOG) for the task of automated test generation. A unit test is composed of two parts: **the prefix** and **the oracle**.

```java
public class Example {
    public void exampleTest() {
        // prefix
        int a = 5;
        int b = 1;
        // oracle
        assertTrue(sum(a, b) == (a + b));
    }
}
```

To generate test prefixes, we use [EvoSuite](https://www.evosuite.org/), which generates complete unit tests (including oracles), and removes the generated oracles (assertions) using [JavaParser](https://javaparser.org/). Then, we generate new oracles using an arbitrary TOG, and add these assertions to the test prefixes. Finally, we run the tests using EvoSuite and record the number of passing/failing tests. Additionally, we use [Defects4J](https://github.com/rjust/defects4j) to compute the precision and FPR of a TOG. 

As a running example, we consider the following toy method:
```java
public class Example {
    /**
     * @param a an integer
     * @param b an integer
     * @return the sum of the two integer values
     */
    int sum(int a, int b) {
        return a - b;
    }
}
```

## 2.1 Experimental Pipeline

For reference, we provide a simplified graphic of the experimental pipeline:

![experiment pipeline](./doc/experiment-pipeline.png)

and a brief description of the relevant files:

- `defects4j`: this directory contains python scripts for running the Defects4J analysis
  - `defects4j.py`: a script that analyzes all bugs in all projects of Defects4J
  - `setup.py`: a script that sets up a Defects4J project bug for analysis 
- `generator`: this directory contains scripts for generating test prefixes and test oracles
  - `evosuite.sh`: a script that creates a test suite using EvoSuite for a given class
  - `jdoctor.sh`: a script that creates oracles using JDoctor
  - `toga.sh`: a script that creates oracles using TOGA
  - `tratto.sh`: a script that creates oracles using Tratto
- `src/main/java`: this directory contains all Java functionality for the end-to-end experimental pipeline
  - `data`: a package with records for representing input and output
  - `FileUtils.java`: a class with all necessary utilities to read, write, and move files
  - `TestUtils.java`: a class for test suite utilities, such as removing/inserting oracles
  - `Tog.java`: the main file for the `experiment.jar` build
  - `TogUtils.java`: a class for tog utilities, such as preprocessing input and postprocessing output
- `experiment.sh`: the end-to-end script which performs the experiment
- `runner.sh`: a script that compiles and runs a test suite

Note that in the previous diagram, the script `tog.sh` is a placeholder for the user-specified tog (e.g. `jdoctor.sh`, `toga.sh`, `tratto.sh`).

### 2.1.1. Prefix

We run `evosuite.sh` to generate a test suite using EvoSuite. These full test cases include both the test prefix and the test oracle, and are saved in `experiment/output/evosuite-tests/`. The EvoSuite tests are split such that each test contains exactly one assertion per test. These single-assertion tests are saved in `experiment/output/evosuite-simple-tests`. Then, we remove the oracles (assertions) and save the test prefixes in `experiment/output/evosuite-prefixes`. If an original EvoSuite test has 4 assertions, then we create 4 corresponding simple tests, and 4 test prefixes.

### 2.1.2. Oracle

After creating test prefixes, we generate oracles using each supported TOG: `jdoctor`, `toga`, and `tratto`. Each TOG has a corresponding script invoking the TOG (as a JAR or python script). Then, the new oracles are inserted into the test prefixes as assertions. The new tests are saved as separate files in `experiment/output/tog-tests/[tog]`.

Our method for inserting oracles varies based on whether the TOG generates [axiomatic](#a-axiomatic) or [non-axiomatic](#b-non-axiomatic) oracles.

#### A. Axiomatic

If the oracles are axiomatic, then we insert the oracles wherever they are applicable. Consider the following oracles for the
aforementioned `sum` example method: `sum(a, b) != null` and `a != null`. We may interpret these oracles as "method
output must not be null" and "first method argument must not be null". Consequently, we should add the assertions after
every appearance of the method output or first method argument, respectively. 

Consider the following test prefixes
generated by EvoSuite.

```java
public class ExampleTest {
    void sumTest() {
        int a = 2;
        int b = 5;
    }

    void sumNegativeTest() {
        int a = -1;
        int b = 7;
    }
}
```

After inserting the example axiomatic oracles, we get the following test suite,

```java
public class ExampleTest {
    void sumTest() {
        int a = 2;
        // first method argument must not be null
        assertTrue(a != null);
        int b = 5;
        // method output must not be null
        assertTrue(sum(a, b) != null);
    }

    void sumNegativeTest() {
        int a = -1;
        // first method argument must not be null
        assertTrue(a != null);
        int b = 7;
        // method output must not be null
        assertTrue(sum(a, b) != null);
    }
}
```

For simplicity, all axiomatic oracles are inserted as `assertTrue` method calls.

Consequently, each test case may have multiple oracles, and each oracle may be used more than once. Therefore, the number
of oracles may not equal the number of prefixes.

#### B. Non-Axiomatic

If the oracles are non-axiomatic, meaning that they correspond to a test prefix, then we simply insert the oracle into its
corresponding test prefix. In contrast to the axiomatic oracles, each test case contains at most one oracle (the TOG may be unable to generate an oracle for a given prefix), and each
oracle is used exactly once.

## 2.2. Research Questions

As a precursor, we define an "axiomatic" oracle, as a self-evident and unquestionable oracle (e.g. `methodResult != null`). Axiomatic oracles are very general and not specific to individual test prefixes. An example of a non-axiomatic oracle is `sum(5, 1) == 6`.

In our experimental analysis, we seek to answer the following research questions:

1. What is the effectiveness (precision and FPR) of Tratto for generating axiomatic oracles?
2. How does Tratto enhance test suites when combined with tools such as EvoSuite or Randoop in terms of bug-finding ability and mutation score?
3. Is Tratto able to recreate all (or more) axiomatic oracles generated by JDoctor? Is Tratto able to synthesize all oracles (and fix incorrect oracles) generated by TOGA?

[comment]: <> (4. How does Tratto compare with ChatGPT for axiomatic oracle generation?)


## 2.3. Metrics

To evaluate 

We say an oracle <span style="color:red">"fails"</span> the code if its corresponding test assertion fails using the current implementation. We say an oracle <span style="color:green">"passes"</span> the specification if the assertion *should* pass according to the specification.

|                     | Code                                  | Specification                         |
|---------------------|---------------------------------------|---------------------------------------|
| True Positive (TP)  | <span style="color:red">Fail</span>   | <span style="color:green">Pass</span> |
| False Positive (FP) | <span style="color:red">Fail</span>   | <span style="color:red">Fail</span>   |
| True Negative (TN)  | <span style="color:green">Pass</span> | <span style="color:green">Pass</span> |
| False Negative (FN) | <span style="color:green">Pass</span> | <span style="color:red">Fail</span>   |

For clarification, consider the following (buggy) code snippet:

We provide an example of each class of oracle below:
- True Positive: `sum(a, b) == (a + b)`
- False Positive: `sum(a, b) == null`
- True Negative: `sum(a, b) != null`
- False Negative: `sum(a, b) == (a - b)`

Intuitively, we hope to maximize True Positives and True Negatives, and minimize False Positives and False Negatives.
This corresponds to a high precision and a low FPR.


## 2.4. Output

### 2.4.1. `TestOutput`

We

### 2.4.2. `Defects4JOutput`

We save the output as a JSON file. The output follows the format,

```json lines

{

  "tog": "tratto",

  "source": "path/to/source/File.java",

  "positive": 10,

  "negative": 24,

  "mutation-score": 85.42,

  "tests": [

    {

      "class": "File",

      "methodSignature": "sum(int a, int b)",

      "isPositive": false,

      "test": "int a = 2;\nassert a != null; ..."

    },

    ...,

    {

      "class": "File",

      "methodSignature": "sum(int a, int b)",

      "isPositive": false,

      "test": "int a = -1;\nassert != null; ..."

    }

  ]

}

```

We report the TOG, the source path, the number of failing tests (positive), the number of passing test (negative),
the mutation score, and information for each test case. For each test case, we report the class under test, the method
under test, whether the test passes or fails (`isPositive` is `true` if the test fails), and the test case as a String
(see `data.data.TestOutput.java` for further detail).

# 3. Run the experiments

----

**NOTE:** Check that all steps in [Section 1](#1-setup) have been complete before running an experiment.

## 3.1. Experiment

To run an experiment on an arbitrary project, run:

```shell
bash experiment.sh [fully-qualified-name] [source-dir] [bin-dir] [project-jar]
```

providing...
1. ...the fully qualified name of the class under test
2. ...the path to the source directory of the project under test
3. ...the path to the system binaries of the project under test
4. ...the path to the project jar

We provide a toy example of a project with a single class (`Stack.java`) in the directory `experiment/src/test/resources/project`. To invoke `experiment.sh` using this project, run,
```shell
bash experiment.sh \
  tutorial.Stack \
  ./src/test/resources/project/src/main/java \
  ./src/test/resources/project/target/classes \
  ./src/test/resources/project/target/Tutorial_Stack-1.0-SNAPSHOT.jar
```

## 3.2. Defects4J

To recreate the Defects4J experiment, run:

```shell
python ./defects4j/defects4j.py
```
