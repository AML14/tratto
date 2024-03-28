# Experiment

----

This module has all scripts necessary to reproduce the results described in the paper "Tratto: A Neuro-Symbolic Approach to Deriving Axiomatic Test Oracles".

[//]: # (Add link to paper when available)

# 1. Setup

---

This section outlines all requirements and corresponding setup instructions necessary to run the experimental pipeline. 

## 1.1. SDKMan

This project uses Sdkman ([link](https://sdkman.io)) to manage switching between different JDKs during the experimental pipeline. Firstly, make sure the `zip` and `unzip` packages are installed. Otherwise, use the commands at the following links: [zip link](https://www.thegeekdiary.com/zip-command-not-found/) and [unzip link](https://www.thegeekdiary.com/unzip-command-not-found/).

Then, execute the command:

  ```shell
  bash ./generator/utils/init.sh
  ```

## 1.2. JDoctor

### 1.2.1. ToRaDoCu

To set up JDoctor for analysis, visit the [ToRaDoCu](https://github.com/albertogoffi/toradocu) GitHub page, and follow the setup
instructions to build the `toradocu-1.0-all.jar` file (this may take a few minutes). Then, move the jar file to the `./generator/resources` directory.

## 1.3. Toga

### 1.3.1. Git Large File Storage

Toga requires Git Large File Storage (Git LFS) to set up its environment. See the [Git LFS homepage](https://git-lfs.com/) for setup instructions.

### 1.3.2. Conda (recommended)

A package management system, such as conda, is recommended (but not required) to create an isolated environment to run the Toga experiments (and debug any potential errors). See the [Miniconda homepage](https://docs.conda.io/projects/miniconda/en/latest/) for setup instructions.

## 1.5. Defects4J

Follow the instructions on the [Defects4J](https://github.com/rjust/defects4j) GitHub page to set up the Defects4J project. Then, run the following command in your local terminal,

```bash
export DEFECTS4J_HOME=/path/to/defects4j
```

## 1.6. Defects4JPrefix

Clone the [Defects4JPrefix](https://github.com/ezackr/defects4jprefix) project into a sibling repository. Then, copy the `defects4jprefix/src/main/evosuite-prefixes` directory into the output directory of this project. The prefixes are pre-generated to expedite the experimental pipeline. 

# 2. Overview

----

This module automates the experimental analysis of a test oracle generator (TOG) for the task of uncovering bugs, using the Defects4J repository. We provide a brief overview of the vocabulary used in this analysis. To begin, a unit test is composed of two parts: **the prefix** and **the oracle**.

```java
public class ExampleTest {
    @Test
    public void sumTest() {
        // prefix
        Integer a = 5;
        Integer b = 1;
        // oracle
        assertTrue(sum(a, b) == (a + b));
    }
}
```

Visit the [Defects4JPrefix](https://github.com/ezackr/defects4jprefix) repository for a description of how the test prefixes are generated. We generate oracles for each test prefix, and add these oracles as assertions to the pre-generated test prefixes. Then, we compile and run these tests on both the buggy and fixed Defects4J program versions, to observe which oracles uncovered the buggy behavior.


## 2.1 Experimental Pipeline

We provide a brief description of the relevant files in the experimental pipeline, alongside their functionality:

- `oracle-generator`: this directory contains bash scripts for generating test oracles and inserting these oracles into a test prefix 
  - `insert_oracles.sh`: a script for inserting oracles into a test prefix
  - `jdoctor.sh`: a script that creates oracles using JDoctor
  - `toga.sh`: a script that creates oracles using TOGA
  - `tratto.sh`: a script that creates oracles using Tratto
- `src/main/java`: this directory contains all Java functionality for the end-to-end experimental pipeline
  - `data`: a package with various enums and records used to represent data
  - `FileUtils.java`: utilities to read, write, and move files
  - `OracleRemover.java`: removing oracles from a test suite (used to generate Defects4J prefixes)
  - `OracleInserter.java`: inserting oracles into a test prefix
  - `Tog.java`: the main file for the `experiment.jar` build
  - `TogUtils.java`: tog utilities, such as preprocessing input and postprocessing output
- `defects4j.sh`: the end-to-end script for reproducing the Tratto experimental results


### 2.1.1. Oracles

After running their corresponding bash scripts to generate test oracles, the new oracles are inserted as assertions into the pre-generated test prefixes. Our method for inserting oracles
varies based on whether the TOG generates [axiomatic](#a-axiomatic) or [non-axiomatic](#b-non-axiomatic) oracles.

The new tests are saved as separate files in `experiment/output/[tog]-tests`, where `[tog]` is the corresponding TOG.

#### A. Axiomatic

If the oracles are axiomatic, then we insert the oracles wherever they are applicable. Consider the following oracles for the
aforementioned `sum` example method: `sum(a, b) != null` and `a != null`. For maximal coverage, we should add these assertions wherever possible, (i.e. before and after each method call). Consider the following toy prefix:

```java
public class ExampleTest {
    @Test
    public void sumTest() {
        Integer a = 2;
        Integer b = 5;
    }

    @Test
    public void sumNegativeTest() {
        Integer a = -1;
        Integer b = 7;
    }
}
```

and the corresponding test suite (after oracle insertion):

```java
public class ExampleTest {
    @Test
    public void sumTest() {
        Integer a = 2;
        assertTrue(a != null);
        Integer b = 5;
        assertTrue(sum(a, b) != null);
    }

    @Test
    public void sumNegativeTest() {
        Integer a = -1;
        assertTrue(a != null);
        Integer b = 7;
        assertTrue(sum(a, b) != null);
    }
}
```

Consequently, each test case may have multiple oracles, and each oracle may be used more than once.

#### B. Non-Axiomatic

If the oracles are non-axiomatic, meaning they correspond to a test prefix, then we simply insert the oracle into its
corresponding test prefix.


### 2.1.3 Output

After running the experiments for a given TOG, the output is saved as `experiment/output/[tog]_defects4joutput.json`. The output follows the format,

```json lines
{
  "tog": [tog-name],
  "numBugsFound": -1,
  "numTruePositive": -1,
  "numFalsePositive": -1,
  "numTrueNegative": -1,
  "numFalseNegative": -1
}
```

The following section provides definitions for each classification, alongside descriptions of how they are computed.

## 2.2. Metrics

To evaluate a TOG, we generate oracles for each bug in the Defects4J project, insert the new oracles into the corresponding test prefixes, and run these tests on both the buggy and fixed program versions. By running the tests on both the buggy and fixed program versions, we are able to determine which tests are "bug-revealing". We provide the following table for clarity:

|                     | Buggy Version                         | Fixed Version                         |
|---------------------|---------------------------------------|---------------------------------------|
| True Positive (TP)  | <span style="color:red">Fail</span>   | <span style="color:green">Pass</span> |
| False Positive (FP) | <span style="color:red">Fail</span>   | <span style="color:red">Fail</span>   |
| True Negative (TN)  | <span style="color:green">Pass</span> | <span style="color:green">Pass</span> |
| False Negative (FN) | <span style="color:green">Pass</span> | <span style="color:red">Fail</span>   |

where a True positive is considered "bug revealing".

Consider the following buggy example:

```java
public class Example {
    /**
     * @param a an integer
     * @param b an integer
     * @return the sum of the two integer values
     */
    public Integer sum(Integer a, Integer b) {
        return a - b;
    }
}
```

Intuitively, the return statement is incorrect (this is our bug), as the code should return `a + b` according to its specification.

We provide an example of each class of oracle below:
- True Positive: `sum(a, b) == (a + b)`
- False Positive: `sum(a, b) == null`
- True Negative: `sum(a, b) != null`
- False Negative: `sum(a, b) == (a - b)`

We hope to maximize True Positive and True Negatives, and minimize False Positive and False Negatives. However, the `numBugsFound` is only incremented if there exists a True Positive in one of the tests in the new test suite (as True Negatives do not reveal any bugs). 
