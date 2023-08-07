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
    public void exceptionalTest() {  // an example of an exceptional oracle
        try {
            List<String> emptyList = new ArrayList<>();
            String element = emptyList.get(0);
            fail();
        } catch (Exception e) {
            assertTrue(e instanceof IndexOutOfBoundsException);
        }
    }

    @Test
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
