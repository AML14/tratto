package star.tratto.identifiers;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ConditionPrimitiveTypeTest {
    @Test
    public void getValueTest() {
        assertEquals("Z", ConditionPrimitiveType.CONDITION_BOOLEAN.getValue());
        assertEquals("B", ConditionPrimitiveType.CONDITION_BYTE.getValue());
        assertEquals("C", ConditionPrimitiveType.CONDITION_CHAR.getValue());
        assertEquals("D", ConditionPrimitiveType.CONDITION_DOUBLE.getValue());
        assertEquals("F", ConditionPrimitiveType.CONDITION_FLOAT.getValue());
        assertEquals("I", ConditionPrimitiveType.CONDITION_INTEGER.getValue());
        assertEquals("J", ConditionPrimitiveType.CONDITION_LONG.getValue());
        assertEquals("S", ConditionPrimitiveType.CONDITION_SHORT.getValue());
        assertEquals("boolean", ConditionPrimitiveType.JP_BOOLEAN.getValue());
        assertEquals("byte", ConditionPrimitiveType.JP_BYTE.getValue());
        assertEquals("char", ConditionPrimitiveType.JP_CHAR.getValue());
        assertEquals("double", ConditionPrimitiveType.JP_DOUBLE.getValue());
        assertEquals("float", ConditionPrimitiveType.JP_FLOAT.getValue());
        assertEquals("int", ConditionPrimitiveType.JP_INTEGER.getValue());
        assertEquals("long", ConditionPrimitiveType.JP_LONG.getValue());
        assertEquals("short", ConditionPrimitiveType.JP_SHORT.getValue());
    }

    @Test
    public void condition2JPTest() {
        assertEquals("boolean", ConditionPrimitiveType.condition2jp(ConditionPrimitiveType.CONDITION_BOOLEAN).getValue());
        assertEquals("byte", ConditionPrimitiveType.condition2jp(ConditionPrimitiveType.CONDITION_BYTE).getValue());
        assertEquals("char", ConditionPrimitiveType.condition2jp(ConditionPrimitiveType.CONDITION_CHAR).getValue());
        assertEquals("double", ConditionPrimitiveType.condition2jp(ConditionPrimitiveType.CONDITION_DOUBLE).getValue());
        assertEquals("float", ConditionPrimitiveType.condition2jp(ConditionPrimitiveType.CONDITION_FLOAT).getValue());
        assertEquals("int", ConditionPrimitiveType.condition2jp(ConditionPrimitiveType.CONDITION_INTEGER).getValue());
        assertEquals("long", ConditionPrimitiveType.condition2jp(ConditionPrimitiveType.CONDITION_LONG).getValue());
        assertEquals("short", ConditionPrimitiveType.condition2jp(ConditionPrimitiveType.CONDITION_SHORT).getValue());
    }

    @Test
    public void condition2JPUnknownTest() {
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> ConditionPrimitiveType.condition2jp(ConditionPrimitiveType.JP_BYTE)
        );
        assertEquals("Unknown primitive type: byte", thrown.getMessage());
    }

    @Test
    public void convertValueTest() {
        assertEquals(ConditionPrimitiveType.CONDITION_BOOLEAN, ConditionPrimitiveType.convertValue("Z"));
        assertEquals(ConditionPrimitiveType.CONDITION_BYTE, ConditionPrimitiveType.convertValue("B"));
        assertEquals(ConditionPrimitiveType.CONDITION_CHAR, ConditionPrimitiveType.convertValue("C"));
        assertEquals(ConditionPrimitiveType.CONDITION_DOUBLE, ConditionPrimitiveType.convertValue("D"));
        assertEquals(ConditionPrimitiveType.CONDITION_FLOAT, ConditionPrimitiveType.convertValue("F"));
        assertEquals(ConditionPrimitiveType.CONDITION_INTEGER, ConditionPrimitiveType.convertValue("I"));
        assertEquals(ConditionPrimitiveType.CONDITION_LONG, ConditionPrimitiveType.convertValue("J"));
        assertEquals(ConditionPrimitiveType.CONDITION_SHORT, ConditionPrimitiveType.convertValue("S"));
        assertEquals(ConditionPrimitiveType.JP_BOOLEAN, ConditionPrimitiveType.convertValue("boolean"));
        assertEquals(ConditionPrimitiveType.JP_BYTE, ConditionPrimitiveType.convertValue("byte"));
        assertEquals(ConditionPrimitiveType.JP_CHAR, ConditionPrimitiveType.convertValue("char"));
        assertEquals(ConditionPrimitiveType.JP_DOUBLE, ConditionPrimitiveType.convertValue("double"));
        assertEquals(ConditionPrimitiveType.JP_FLOAT, ConditionPrimitiveType.convertValue("float"));
        assertEquals(ConditionPrimitiveType.JP_INTEGER, ConditionPrimitiveType.convertValue("integer"));
        assertEquals(ConditionPrimitiveType.JP_LONG, ConditionPrimitiveType.convertValue("long"));
        assertEquals(ConditionPrimitiveType.JP_SHORT, ConditionPrimitiveType.convertValue("short"));
    }

    @Test
    public void getJPValuesTest() {
        List<String> expected = new ArrayList<>();
        expected.add("boolean");
        expected.add("byte");
        expected.add("char");
        expected.add("double");
        expected.add("float");
        expected.add("int");
        expected.add("long");
        expected.add("short");
        assertEquals(expected, ConditionPrimitiveType.getJPValues());
    }

    @Test
    public void getConditionValuesTest() {
        List<String> expected = new ArrayList<>();
        expected.add("Z");
        expected.add("B");
        expected.add("C");
        expected.add("D");
        expected.add("F");
        expected.add("I");
        expected.add("J");
        expected.add("S");
        assertEquals(expected, ConditionPrimitiveType.getConditionValues());
    }

    @Test
    public void getConditionRegExTest() {
        assertEquals("[ZBCDFIJS]", ConditionPrimitiveType.getConditionRegexValues());
    }

    @Test
    public void jp2ConditionTest() {
        assertEquals("Z", ConditionPrimitiveType.jp2condition(ConditionPrimitiveType.JP_BOOLEAN).getValue());
        assertEquals("B", ConditionPrimitiveType.jp2condition(ConditionPrimitiveType.JP_BYTE).getValue());
        assertEquals("C", ConditionPrimitiveType.jp2condition(ConditionPrimitiveType.JP_CHAR).getValue());
        assertEquals("D", ConditionPrimitiveType.jp2condition(ConditionPrimitiveType.JP_DOUBLE).getValue());
        assertEquals("F", ConditionPrimitiveType.jp2condition(ConditionPrimitiveType.JP_FLOAT).getValue());
        assertEquals("I", ConditionPrimitiveType.jp2condition(ConditionPrimitiveType.JP_INTEGER).getValue());
        assertEquals("J", ConditionPrimitiveType.jp2condition(ConditionPrimitiveType.JP_LONG).getValue());
        assertEquals("S", ConditionPrimitiveType.jp2condition(ConditionPrimitiveType.JP_SHORT).getValue());
    }

    @Test
    public void jp2ConditionUnknownTest() {
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> ConditionPrimitiveType.jp2condition(ConditionPrimitiveType.CONDITION_BYTE)
        );
        assertEquals("Unknown primitive type: B", thrown.getMessage());
    }
}
