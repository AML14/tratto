package star.tratto.util;

import org.javatuples.Pair;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static star.tratto.util.JavaTypes.isNumeric1AssignableToNumeric2;

public class JavaTypesTest {

    @Test
    public void isNumeric1AssignableToNumeric2Test() {
        Pair<String, String> boolean_ = Pair.with("", "boolean");
        Pair<String, String> char_ = Pair.with("", "char");
        Pair<String, String> int_ = Pair.with("", "int");
        Pair<String, String> long_ = Pair.with("", "long");
        Pair<String, String> float_ = Pair.with("", "float");
        Pair<String, String> double_ = Pair.with("", "double");
        Pair<String, String> byte_ = Pair.with("", "byte");
        Pair<String, String> short_ = Pair.with("", "short");
        Pair<String, String> booleanWrapper = Pair.with("java.lang", "Boolean");
        Pair<String, String> charWrapper = Pair.with("java.lang", "Character");
        Pair<String, String> intWrapper = Pair.with("java.lang", "Integer");
        Pair<String, String> longWrapper = Pair.with("java.lang", "Long");
        Pair<String, String> floatWrapper = Pair.with("java.lang", "Float");
        Pair<String, String> doubleWrapper = Pair.with("java.lang", "Double");
        Pair<String, String> byteWrapper = Pair.with("java.lang", "Byte");
        Pair<String, String> shortWrapper = Pair.with("java.lang", "Short");

        assertThrows(IllegalArgumentException.class, () -> isNumeric1AssignableToNumeric2(boolean_, int_));
        assertThrows(IllegalArgumentException.class, () -> isNumeric1AssignableToNumeric2(int_, booleanWrapper));
        assertTrue(isNumeric1AssignableToNumeric2(char_, charWrapper));
        assertTrue(isNumeric1AssignableToNumeric2(charWrapper, char_));
        assertTrue(isNumeric1AssignableToNumeric2(char_, int_));
        assertTrue(isNumeric1AssignableToNumeric2(char_, double_));
        assertFalse(isNumeric1AssignableToNumeric2(char_, byte_));
        assertFalse(isNumeric1AssignableToNumeric2(char_, longWrapper));
        assertFalse(isNumeric1AssignableToNumeric2(charWrapper, doubleWrapper));
        assertTrue(isNumeric1AssignableToNumeric2(byte_, byteWrapper));
        assertTrue(isNumeric1AssignableToNumeric2(byte_, int_));
        assertTrue(isNumeric1AssignableToNumeric2(byte_, long_));
        assertTrue(isNumeric1AssignableToNumeric2(byte_, float_));
        assertTrue(isNumeric1AssignableToNumeric2(byte_, double_));
        assertFalse(isNumeric1AssignableToNumeric2(byte_, shortWrapper));
        assertTrue(isNumeric1AssignableToNumeric2(byteWrapper, byte_));
        assertFalse(isNumeric1AssignableToNumeric2(byteWrapper, shortWrapper));
        assertFalse(isNumeric1AssignableToNumeric2(byteWrapper, char_));
        assertTrue(isNumeric1AssignableToNumeric2(short_, shortWrapper));
        assertTrue(isNumeric1AssignableToNumeric2(short_, int_));
        assertTrue(isNumeric1AssignableToNumeric2(short_, long_));
        assertTrue(isNumeric1AssignableToNumeric2(short_, float_));
        assertTrue(isNumeric1AssignableToNumeric2(short_, double_));
        assertFalse(isNumeric1AssignableToNumeric2(short_, byteWrapper));
        assertTrue(isNumeric1AssignableToNumeric2(shortWrapper, short_));
        assertFalse(isNumeric1AssignableToNumeric2(shortWrapper, byteWrapper));
        assertFalse(isNumeric1AssignableToNumeric2(shortWrapper, char_));
        assertTrue(isNumeric1AssignableToNumeric2(int_, intWrapper));
        assertTrue(isNumeric1AssignableToNumeric2(int_, long_));
        assertTrue(isNumeric1AssignableToNumeric2(int_, double_));
        assertFalse(isNumeric1AssignableToNumeric2(int_, longWrapper));
        assertTrue(isNumeric1AssignableToNumeric2(intWrapper, int_));
        assertFalse(isNumeric1AssignableToNumeric2(intWrapper, longWrapper));
        assertFalse(isNumeric1AssignableToNumeric2(intWrapper, short_));
        assertTrue(isNumeric1AssignableToNumeric2(long_, longWrapper));
        assertTrue(isNumeric1AssignableToNumeric2(long_, float_));
        assertTrue(isNumeric1AssignableToNumeric2(long_, double_));
        assertFalse(isNumeric1AssignableToNumeric2(long_, intWrapper));
        assertTrue(isNumeric1AssignableToNumeric2(longWrapper, long_));
        assertFalse(isNumeric1AssignableToNumeric2(longWrapper, intWrapper));
        assertFalse(isNumeric1AssignableToNumeric2(longWrapper, short_));
        assertTrue(isNumeric1AssignableToNumeric2(float_, floatWrapper));
        assertTrue(isNumeric1AssignableToNumeric2(float_, double_));
        assertFalse(isNumeric1AssignableToNumeric2(float_, longWrapper));
        assertTrue(isNumeric1AssignableToNumeric2(floatWrapper, float_));
        assertFalse(isNumeric1AssignableToNumeric2(floatWrapper, longWrapper));
        assertFalse(isNumeric1AssignableToNumeric2(floatWrapper, short_));
        assertTrue(isNumeric1AssignableToNumeric2(double_, doubleWrapper));
        assertFalse(isNumeric1AssignableToNumeric2(double_, floatWrapper));
        assertFalse(isNumeric1AssignableToNumeric2(double_, longWrapper));
        assertTrue(isNumeric1AssignableToNumeric2(doubleWrapper, double_));
        assertFalse(isNumeric1AssignableToNumeric2(doubleWrapper, floatWrapper));
        assertFalse(isNumeric1AssignableToNumeric2(doubleWrapper, longWrapper));
        assertFalse(isNumeric1AssignableToNumeric2(doubleWrapper, short_));
    }
}
