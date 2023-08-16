package example;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class ExamplePrefix {
    @Test
    @Disabled
    public void assertionTest() {
        int a = 5;
        int b = 6;
        int sum = Integer.sum(6, sum);
    }

    @Test
    @Disabled
    public void exceptionalTest() {
        String integerToParse = null;
        int correspondingInteger = Integer.parseInt(integerToParse);
    }
}
