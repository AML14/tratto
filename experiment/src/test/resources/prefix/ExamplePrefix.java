package example;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class ExamplePrefix {
    @Test
    @Disabled
    public void assertionTest() {
        int primitiveInt = 5;
        Integer objectInt = Integer.valueOf(primitiveInt);
    }

    @Test
    @Disabled
    public void assertionNonStaticTest() {
        Integer objectInt = 5;
        objectInt.intValue();
    }

    @Test
    @Disabled
    public void exceptionalTest() {
        String integerToParse = null;
        int correspondingInteger = Integer.parseInt(integerToParse);
    }
}
