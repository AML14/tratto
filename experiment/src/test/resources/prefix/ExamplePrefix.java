package example;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;
public class ExamplePrefix {
    @Test
    @Disabled
    public void assertionTest() throws Throwable {
        int primitiveInt = 5;
        Integer objectInt = Integer.valueOf(primitiveInt);
    }

    @Test
    @Disabled
    public void assertionNonStaticTest() throws Throwable {
        Integer objectInt = 5;
        objectInt.intValue();
    }

    @Test
    @Disabled
    public void exceptionalTest() throws Throwable {
        String integerToParse = null;
        int correspondingInteger = Integer.parseInt(integerToParse);
    }

    @Test
    @Disabled
    public void everythingTest() throws Throwable {
        Class<?> clazz = Integer.class;
        String methodName = "compare";
        Class<?>[] parameters = { int.class, int.class };
        Method method = clazz.getMethod(methodName, parameters);
    }
}
