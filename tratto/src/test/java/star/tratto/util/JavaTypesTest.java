package star.tratto.util;

import org.javatuples.Pair;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static star.tratto.util.JavaTypes.isAssignableToNumeric;

public class JavaTypesTest {

    @Test
    public void isAssignableToNumericTest() {
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

        assertThrows(IllegalArgumentException.class, () -> isAssignableToNumeric(boolean_, int_));
        assertThrows(IllegalArgumentException.class, () -> isAssignableToNumeric(int_, booleanWrapper));
        assertTrue(isAssignableToNumeric(char_, charWrapper));
        assertTrue(isAssignableToNumeric(charWrapper, char_));
        assertTrue(isAssignableToNumeric(char_, int_));
        assertTrue(isAssignableToNumeric(char_, double_));
        assertFalse(isAssignableToNumeric(char_, byte_));
        assertFalse(isAssignableToNumeric(char_, longWrapper));
        assertFalse(isAssignableToNumeric(charWrapper, doubleWrapper));
        assertTrue(isAssignableToNumeric(byte_, byteWrapper));
        assertTrue(isAssignableToNumeric(byte_, int_));
        assertTrue(isAssignableToNumeric(byte_, long_));
        assertTrue(isAssignableToNumeric(byte_, float_));
        assertTrue(isAssignableToNumeric(byte_, double_));
        assertFalse(isAssignableToNumeric(byte_, shortWrapper));
        assertTrue(isAssignableToNumeric(byteWrapper, byte_));
        assertFalse(isAssignableToNumeric(byteWrapper, shortWrapper));
        assertFalse(isAssignableToNumeric(byteWrapper, char_));
        assertTrue(isAssignableToNumeric(short_, shortWrapper));
        assertTrue(isAssignableToNumeric(short_, int_));
        assertTrue(isAssignableToNumeric(short_, long_));
        assertTrue(isAssignableToNumeric(short_, float_));
        assertTrue(isAssignableToNumeric(short_, double_));
        assertFalse(isAssignableToNumeric(short_, byteWrapper));
        assertTrue(isAssignableToNumeric(shortWrapper, short_));
        assertFalse(isAssignableToNumeric(shortWrapper, byteWrapper));
        assertFalse(isAssignableToNumeric(shortWrapper, char_));
        assertTrue(isAssignableToNumeric(int_, intWrapper));
        assertTrue(isAssignableToNumeric(int_, long_));
        assertTrue(isAssignableToNumeric(int_, double_));
        assertFalse(isAssignableToNumeric(int_, longWrapper));
        assertTrue(isAssignableToNumeric(intWrapper, int_));
        assertFalse(isAssignableToNumeric(intWrapper, longWrapper));
        assertFalse(isAssignableToNumeric(intWrapper, short_));
        assertTrue(isAssignableToNumeric(long_, longWrapper));
        assertTrue(isAssignableToNumeric(long_, float_));
        assertTrue(isAssignableToNumeric(long_, double_));
        assertFalse(isAssignableToNumeric(long_, intWrapper));
        assertTrue(isAssignableToNumeric(longWrapper, long_));
        assertFalse(isAssignableToNumeric(longWrapper, intWrapper));
        assertFalse(isAssignableToNumeric(longWrapper, short_));
        assertTrue(isAssignableToNumeric(float_, floatWrapper));
        assertTrue(isAssignableToNumeric(float_, double_));
        assertFalse(isAssignableToNumeric(float_, longWrapper));
        assertTrue(isAssignableToNumeric(floatWrapper, float_));
        assertFalse(isAssignableToNumeric(floatWrapper, longWrapper));
        assertFalse(isAssignableToNumeric(floatWrapper, short_));
        assertTrue(isAssignableToNumeric(double_, doubleWrapper));
        assertFalse(isAssignableToNumeric(double_, floatWrapper));
        assertFalse(isAssignableToNumeric(double_, longWrapper));
        assertTrue(isAssignableToNumeric(doubleWrapper, double_));
        assertFalse(isAssignableToNumeric(doubleWrapper, floatWrapper));
        assertFalse(isAssignableToNumeric(doubleWrapper, longWrapper));
        assertFalse(isAssignableToNumeric(doubleWrapper, short_));
    }
}
