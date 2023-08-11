package example.test;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * This class provides a list of example test cases using JUnit. We use this
 * test file to validate the behavior of our test modifiers and analyzers.
 */
public class ExampleTest {
    @Test
    @Disabled
    public void assertionTest() {
        // an example of an assertion oracle
        List<String> nonEmptyList = List.of("example");
        assertEquals("example", nonEmptyList.get(0));
    }

    @Test
    @Disabled
    public void multipleAssertionTest() {
        List<String> nonEmptyList = new ArrayList<>(List.of("example"));
        assertEquals("example", nonEmptyList.get(0));
        assertTrue(nonEmptyList.size() != 0);
        assertFalse(nonEmptyList.size() == 0);
        assertNull(null);
        nonEmptyList.add("Random statement to complicate things");
        assertNotNull(nonEmptyList);
    }

    @Test
    @Disabled
    public void exceptionalTest() {
        // an example of an exceptional oracle
        try {
            List<String> emptyList = new ArrayList<>();
            String element = emptyList.get(0);
            fail();
        } catch (Exception e) {
            assertTrue(e instanceof IndexOutOfBoundsException);
        }
    }

    @Test
    @Disabled
    public void assertionAndExceptionalTest() {
        // assertion prefix.
        List<String> emptyList = new ArrayList<>();
        assertEquals(0, emptyList.size());
        try {
            // exceptional prefix.
            String element = emptyList.get(0);
            fail();
        } catch (Exception e) {
            assertTrue(e instanceof IndexOutOfBoundsException);
        }
    }
}
