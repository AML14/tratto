package example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class provides a list of example test cases using JUnit.
 */
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
