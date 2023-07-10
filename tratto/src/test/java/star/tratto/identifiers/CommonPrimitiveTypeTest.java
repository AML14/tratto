package star.tratto.identifiers;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CommonPrimitiveTypeTest {
    @Test
    public void getValueTest() {
        assertEquals("Z", CommonPrimitiveType.CONDITION_BOOLEAN.getValue());
        assertEquals("B", CommonPrimitiveType.CONDITION_BYTE.getValue());
        assertEquals("C", CommonPrimitiveType.CONDITION_CHAR.getValue());
        assertEquals("D", CommonPrimitiveType.CONDITION_DOUBLE.getValue());
        assertEquals("F", CommonPrimitiveType.CONDITION_FLOAT.getValue());
        assertEquals("I", CommonPrimitiveType.CONDITION_INTEGER.getValue());
        assertEquals("J", CommonPrimitiveType.CONDITION_LONG.getValue());
        assertEquals("S", CommonPrimitiveType.CONDITION_SHORT.getValue());
        assertEquals("boolean", CommonPrimitiveType.JP_BOOLEAN.getValue());
        assertEquals("byte", CommonPrimitiveType.JP_BYTE.getValue());
        assertEquals("char", CommonPrimitiveType.JP_CHAR.getValue());
        assertEquals("double", CommonPrimitiveType.JP_DOUBLE.getValue());
        assertEquals("float", CommonPrimitiveType.JP_FLOAT.getValue());
        assertEquals("int", CommonPrimitiveType.JP_INTEGER.getValue());
        assertEquals("long", CommonPrimitiveType.JP_LONG.getValue());
        assertEquals("short", CommonPrimitiveType.JP_SHORT.getValue());
    }

    @Test
    public void condition2JPTest() {
        assertEquals("boolean", CommonPrimitiveType.condition2jp(CommonPrimitiveType.CONDITION_BOOLEAN).getValue());
        assertEquals("byte", CommonPrimitiveType.condition2jp(CommonPrimitiveType.CONDITION_BYTE).getValue());
        assertEquals("char", CommonPrimitiveType.condition2jp(CommonPrimitiveType.CONDITION_CHAR).getValue());
        assertEquals("double", CommonPrimitiveType.condition2jp(CommonPrimitiveType.CONDITION_DOUBLE).getValue());
        assertEquals("float", CommonPrimitiveType.condition2jp(CommonPrimitiveType.CONDITION_FLOAT).getValue());
        assertEquals("int", CommonPrimitiveType.condition2jp(CommonPrimitiveType.CONDITION_INTEGER).getValue());
        assertEquals("long", CommonPrimitiveType.condition2jp(CommonPrimitiveType.CONDITION_LONG).getValue());
        assertEquals("short", CommonPrimitiveType.condition2jp(CommonPrimitiveType.CONDITION_SHORT).getValue());
    }

    @Test
    public void condition2JPUnknownTest() {
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> CommonPrimitiveType.condition2jp(CommonPrimitiveType.JP_BYTE)
        );
        assertEquals("Unknown primitive type: byte", thrown.getMessage());
    }

    @Test
    public void convertValueTest() {
        assertEquals(CommonPrimitiveType.CONDITION_BOOLEAN, CommonPrimitiveType.convertValue("Z"));
        assertEquals(CommonPrimitiveType.CONDITION_BYTE, CommonPrimitiveType.convertValue("B"));
        assertEquals(CommonPrimitiveType.CONDITION_CHAR, CommonPrimitiveType.convertValue("C"));
        assertEquals(CommonPrimitiveType.CONDITION_DOUBLE, CommonPrimitiveType.convertValue("D"));
        assertEquals(CommonPrimitiveType.CONDITION_FLOAT, CommonPrimitiveType.convertValue("F"));
        assertEquals(CommonPrimitiveType.CONDITION_INTEGER, CommonPrimitiveType.convertValue("I"));
        assertEquals(CommonPrimitiveType.CONDITION_LONG, CommonPrimitiveType.convertValue("J"));
        assertEquals(CommonPrimitiveType.CONDITION_SHORT, CommonPrimitiveType.convertValue("S"));
        assertEquals(CommonPrimitiveType.JP_BOOLEAN, CommonPrimitiveType.convertValue("boolean"));
        assertEquals(CommonPrimitiveType.JP_BYTE, CommonPrimitiveType.convertValue("byte"));
        assertEquals(CommonPrimitiveType.JP_CHAR, CommonPrimitiveType.convertValue("char"));
        assertEquals(CommonPrimitiveType.JP_DOUBLE, CommonPrimitiveType.convertValue("double"));
        assertEquals(CommonPrimitiveType.JP_FLOAT, CommonPrimitiveType.convertValue("float"));
        assertEquals(CommonPrimitiveType.JP_INTEGER, CommonPrimitiveType.convertValue("integer"));
        assertEquals(CommonPrimitiveType.JP_LONG, CommonPrimitiveType.convertValue("long"));
        assertEquals(CommonPrimitiveType.JP_SHORT, CommonPrimitiveType.convertValue("short"));
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
        assertEquals(expected, CommonPrimitiveType.getJPValues());
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
        assertEquals(expected, CommonPrimitiveType.getConditionValues());
    }

    @Test
    public void getConditionRegExTest() {
        assertEquals("[ZBCDFIJS]", CommonPrimitiveType.getConditionRegexValues());
    }

    @Test
    public void jp2ConditionTest() {
        assertEquals("Z", CommonPrimitiveType.jp2condition(CommonPrimitiveType.JP_BOOLEAN).getValue());
        assertEquals("B", CommonPrimitiveType.jp2condition(CommonPrimitiveType.JP_BYTE).getValue());
        assertEquals("C", CommonPrimitiveType.jp2condition(CommonPrimitiveType.JP_CHAR).getValue());
        assertEquals("D", CommonPrimitiveType.jp2condition(CommonPrimitiveType.JP_DOUBLE).getValue());
        assertEquals("F", CommonPrimitiveType.jp2condition(CommonPrimitiveType.JP_FLOAT).getValue());
        assertEquals("I", CommonPrimitiveType.jp2condition(CommonPrimitiveType.JP_INTEGER).getValue());
        assertEquals("J", CommonPrimitiveType.jp2condition(CommonPrimitiveType.JP_LONG).getValue());
        assertEquals("S", CommonPrimitiveType.jp2condition(CommonPrimitiveType.JP_SHORT).getValue());
    }

    @Test
    public void jp2ConditionUnknownTest() {
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> CommonPrimitiveType.jp2condition(CommonPrimitiveType.CONDITION_BYTE)
        );
        assertEquals("Unknown primitive type: B", thrown.getMessage());
    }
}
