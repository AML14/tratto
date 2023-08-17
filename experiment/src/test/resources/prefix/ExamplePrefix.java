package example;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class ExamplePrefix {
    @Test
    @Disabled
    public void assertionTest() {
        Integer a = 5;
        Integer b = 6;
    }

    @Test
    @Disabled
    public void exceptionalTest() {
        String integerToParse = null;
        Integer.parseInt(integerToParse);
    }
}
