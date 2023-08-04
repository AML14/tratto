# Java Source

## Overview

This module manages all relevant Java functionality in the analysis. The necessary functionality includes removing oracles from a test suite, adding oracles to test prefixes, calculating the mutation score of a test suite, and classifying test cases based on whether they pass or fail using the current implementation. The functionality for modifying a test suite (e.g. removing/inserting oracles) is in `TestUtils.java`, and the functionality for analyzing a test suite (e.g. mutation score and classification) is in `TestAnalyzer.java`.

## `TestUtils.java`

### `removeOracles`

As a precursor, we define two types of oracles: **assertion oracles** and **exceptional oracles**. Consider the following examples,

```java
public class ExampleTest {
    @Test
    public void assertionTest() {  // an example of an assertion oracle
        List<String> nonEmptyList = List.of("example");
        assertEquals("example", nonEmptyList.get(0));
    }
    
    @Test
    public void exceptionalTest() {  // an example of an exceptional oracle
        List<String> emptyList = new ArrayList<>();
        try {
            String element = emptyList.get(0);
            fail();
        } catch (Exception e) {
            assertTrue(e instanceof IndexOutOfBoundsException);
        }
    }
}
```

The structure of an assertion oracle is very different from an exceptional oracle. In `TestUtils.java`, the method `removeOracles` handles these cases separately, and would return,

```java
public class ExamplePrefix {
    @Test
    public void assertionTest() {  // an example of an assertion oracle
        List<String> nonEmptyList = List.of("example");
    }
    
    @Test
    public void exceptionalTest() {  // an example of an exceptional oracle
        List<String> emptyList = new ArrayList<>();
    }
}
```

We note that `removeOracles` saves the prefixes as a separate file `ExamplePrefix.java` in `experiment/output/evosuite-prefix`.

### `insertOracles`

Our approach for inserting oracles into test prefixes varies based on whether the oracles are [**axiomatic**](../README.md#axiomatic) or [**non-axiomatic**](../README.md#non-axiomatic). See their respective sections in the [top-level README](../README.md) for further detail.

## `TestAnalyzer.java`

Description


