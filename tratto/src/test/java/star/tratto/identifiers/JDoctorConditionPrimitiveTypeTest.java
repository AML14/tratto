package star.tratto.identifiers;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class JDoctorConditionPrimitiveTypeTest {
    @Test
    public void getValueTest() {
        assertEquals("Z", JDoctorConditionPrimitiveType.CONDITION_BOOLEAN.getValue());
        assertEquals("B", JDoctorConditionPrimitiveType.CONDITION_BYTE.getValue());
        assertEquals("C", JDoctorConditionPrimitiveType.CONDITION_CHAR.getValue());
        assertEquals("D", JDoctorConditionPrimitiveType.CONDITION_DOUBLE.getValue());
        assertEquals("F", JDoctorConditionPrimitiveType.CONDITION_FLOAT.getValue());
        assertEquals("I", JDoctorConditionPrimitiveType.CONDITION_INTEGER.getValue());
        assertEquals("J", JDoctorConditionPrimitiveType.CONDITION_LONG.getValue());
        assertEquals("S", JDoctorConditionPrimitiveType.CONDITION_SHORT.getValue());
        assertEquals("boolean", JDoctorConditionPrimitiveType.JP_BOOLEAN.getValue());
        assertEquals("byte", JDoctorConditionPrimitiveType.JP_BYTE.getValue());
        assertEquals("char", JDoctorConditionPrimitiveType.JP_CHAR.getValue());
        assertEquals("double", JDoctorConditionPrimitiveType.JP_DOUBLE.getValue());
        assertEquals("float", JDoctorConditionPrimitiveType.JP_FLOAT.getValue());
        assertEquals("int", JDoctorConditionPrimitiveType.JP_INTEGER.getValue());
        assertEquals("long", JDoctorConditionPrimitiveType.JP_LONG.getValue());
        assertEquals("short", JDoctorConditionPrimitiveType.JP_SHORT.getValue());
    }

    @Test
    public void condition2JPTest() {
        assertEquals("boolean", JDoctorConditionPrimitiveType.condition2jp(JDoctorConditionPrimitiveType.CONDITION_BOOLEAN).getValue());
        assertEquals("byte", JDoctorConditionPrimitiveType.condition2jp(JDoctorConditionPrimitiveType.CONDITION_BYTE).getValue());
        assertEquals("char", JDoctorConditionPrimitiveType.condition2jp(JDoctorConditionPrimitiveType.CONDITION_CHAR).getValue());
        assertEquals("double", JDoctorConditionPrimitiveType.condition2jp(JDoctorConditionPrimitiveType.CONDITION_DOUBLE).getValue());
        assertEquals("float", JDoctorConditionPrimitiveType.condition2jp(JDoctorConditionPrimitiveType.CONDITION_FLOAT).getValue());
        assertEquals("int", JDoctorConditionPrimitiveType.condition2jp(JDoctorConditionPrimitiveType.CONDITION_INTEGER).getValue());
        assertEquals("long", JDoctorConditionPrimitiveType.condition2jp(JDoctorConditionPrimitiveType.CONDITION_LONG).getValue());
        assertEquals("short", JDoctorConditionPrimitiveType.condition2jp(JDoctorConditionPrimitiveType.CONDITION_SHORT).getValue());
    }

    @Test
    public void condition2JPUnknownTest() {
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> JDoctorConditionPrimitiveType.condition2jp(JDoctorConditionPrimitiveType.JP_BYTE)
        );
        assertEquals("Unknown primitive type: byte", thrown.getMessage());
    }

    @Test
    public void convertValueTest() {
        assertEquals(JDoctorConditionPrimitiveType.CONDITION_BOOLEAN, JDoctorConditionPrimitiveType.convertValue("Z"));
        assertEquals(JDoctorConditionPrimitiveType.CONDITION_BYTE, JDoctorConditionPrimitiveType.convertValue("B"));
        assertEquals(JDoctorConditionPrimitiveType.CONDITION_CHAR, JDoctorConditionPrimitiveType.convertValue("C"));
        assertEquals(JDoctorConditionPrimitiveType.CONDITION_DOUBLE, JDoctorConditionPrimitiveType.convertValue("D"));
        assertEquals(JDoctorConditionPrimitiveType.CONDITION_FLOAT, JDoctorConditionPrimitiveType.convertValue("F"));
        assertEquals(JDoctorConditionPrimitiveType.CONDITION_INTEGER, JDoctorConditionPrimitiveType.convertValue("I"));
        assertEquals(JDoctorConditionPrimitiveType.CONDITION_LONG, JDoctorConditionPrimitiveType.convertValue("J"));
        assertEquals(JDoctorConditionPrimitiveType.CONDITION_SHORT, JDoctorConditionPrimitiveType.convertValue("S"));
        assertEquals(JDoctorConditionPrimitiveType.JP_BOOLEAN, JDoctorConditionPrimitiveType.convertValue("boolean"));
        assertEquals(JDoctorConditionPrimitiveType.JP_BYTE, JDoctorConditionPrimitiveType.convertValue("byte"));
        assertEquals(JDoctorConditionPrimitiveType.JP_CHAR, JDoctorConditionPrimitiveType.convertValue("char"));
        assertEquals(JDoctorConditionPrimitiveType.JP_DOUBLE, JDoctorConditionPrimitiveType.convertValue("double"));
        assertEquals(JDoctorConditionPrimitiveType.JP_FLOAT, JDoctorConditionPrimitiveType.convertValue("float"));
        assertEquals(JDoctorConditionPrimitiveType.JP_INTEGER, JDoctorConditionPrimitiveType.convertValue("integer"));
        assertEquals(JDoctorConditionPrimitiveType.JP_LONG, JDoctorConditionPrimitiveType.convertValue("long"));
        assertEquals(JDoctorConditionPrimitiveType.JP_SHORT, JDoctorConditionPrimitiveType.convertValue("short"));
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
        assertEquals(expected, JDoctorConditionPrimitiveType.getJPValues());
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
        assertEquals(expected, JDoctorConditionPrimitiveType.getConditionValues());
    }

    @Test
    public void getConditionRegExTest() {
        assertEquals("[ZBCDFIJS]", JDoctorConditionPrimitiveType.getConditionRegexValues());
    }

    @Test
    public void jp2ConditionTest() {
        assertEquals("Z", JDoctorConditionPrimitiveType.jp2condition(JDoctorConditionPrimitiveType.JP_BOOLEAN).getValue());
        assertEquals("B", JDoctorConditionPrimitiveType.jp2condition(JDoctorConditionPrimitiveType.JP_BYTE).getValue());
        assertEquals("C", JDoctorConditionPrimitiveType.jp2condition(JDoctorConditionPrimitiveType.JP_CHAR).getValue());
        assertEquals("D", JDoctorConditionPrimitiveType.jp2condition(JDoctorConditionPrimitiveType.JP_DOUBLE).getValue());
        assertEquals("F", JDoctorConditionPrimitiveType.jp2condition(JDoctorConditionPrimitiveType.JP_FLOAT).getValue());
        assertEquals("I", JDoctorConditionPrimitiveType.jp2condition(JDoctorConditionPrimitiveType.JP_INTEGER).getValue());
        assertEquals("J", JDoctorConditionPrimitiveType.jp2condition(JDoctorConditionPrimitiveType.JP_LONG).getValue());
        assertEquals("S", JDoctorConditionPrimitiveType.jp2condition(JDoctorConditionPrimitiveType.JP_SHORT).getValue());
    }

    @Test
    public void jp2ConditionUnknownTest() {
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> JDoctorConditionPrimitiveType.jp2condition(JDoctorConditionPrimitiveType.CONDITION_BYTE)
        );
        assertEquals("Unknown primitive type: B", thrown.getMessage());
    }
}
