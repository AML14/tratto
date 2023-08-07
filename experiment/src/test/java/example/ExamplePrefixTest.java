package example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * This class provides a list of example test prefixes. This class represents
 * our expected prefixes after removing oracles from ExampleTest.java.
 */
public class ExamplePrefixTest {
    @Test
    public void assertionTest() {  // an example of an assertion oracle
        List<String> nonEmptyList = List.of("example");
    }

    @Test
    public void multipleAssertionTest() {
        List<String> nonEmptyList = new ArrayList<>(List.of("example"));
        nonEmptyList.add("Random statement to complicate things");
    }

    @Test
    public void exceptionalTest() {  // an example of an exceptional oracle
        List<String> emptyList = new ArrayList<>();
        String element = emptyList.get(0);
    }

    @Test
    public void assertionAndExceptionalTest() {
        // assertion prefix.
        List<String> emptyList = new ArrayList<>();
        // exceptional prefix.
        String element = emptyList.get(0);
    }
}
