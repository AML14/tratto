package star.tratto.identifiers;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JDoctorConditionPrimitiveTypeTest {
    @Test
    public void typeNameToConditionPrimitiveTypeTest() {
        assertEquals(JDoctorConditionPrimitiveType.CONDITION_BOOLEAN, JDoctorConditionPrimitiveType.convertTypeNameToConditionPrimitiveType("Z"));
        assertEquals(JDoctorConditionPrimitiveType.CONDITION_BYTE, JDoctorConditionPrimitiveType.convertTypeNameToConditionPrimitiveType("B"));
        assertEquals(JDoctorConditionPrimitiveType.CONDITION_CHAR, JDoctorConditionPrimitiveType.convertTypeNameToConditionPrimitiveType("C"));
        assertEquals(JDoctorConditionPrimitiveType.CONDITION_DOUBLE, JDoctorConditionPrimitiveType.convertTypeNameToConditionPrimitiveType("D"));
        assertEquals(JDoctorConditionPrimitiveType.CONDITION_FLOAT, JDoctorConditionPrimitiveType.convertTypeNameToConditionPrimitiveType("F"));
        assertEquals(JDoctorConditionPrimitiveType.CONDITION_INTEGER, JDoctorConditionPrimitiveType.convertTypeNameToConditionPrimitiveType("I"));
        assertEquals(JDoctorConditionPrimitiveType.CONDITION_LONG, JDoctorConditionPrimitiveType.convertTypeNameToConditionPrimitiveType("J"));
        assertEquals(JDoctorConditionPrimitiveType.CONDITION_SHORT, JDoctorConditionPrimitiveType.convertTypeNameToConditionPrimitiveType("S"));
        assertEquals(JDoctorConditionPrimitiveType.JP_BOOLEAN, JDoctorConditionPrimitiveType.convertTypeNameToConditionPrimitiveType("boolean"));
        assertEquals(JDoctorConditionPrimitiveType.JP_BYTE, JDoctorConditionPrimitiveType.convertTypeNameToConditionPrimitiveType("byte"));
        assertEquals(JDoctorConditionPrimitiveType.JP_CHAR, JDoctorConditionPrimitiveType.convertTypeNameToConditionPrimitiveType("char"));
        assertEquals(JDoctorConditionPrimitiveType.JP_DOUBLE, JDoctorConditionPrimitiveType.convertTypeNameToConditionPrimitiveType("double"));
        assertEquals(JDoctorConditionPrimitiveType.JP_FLOAT, JDoctorConditionPrimitiveType.convertTypeNameToConditionPrimitiveType("float"));
        assertEquals(JDoctorConditionPrimitiveType.JP_INTEGER, JDoctorConditionPrimitiveType.convertTypeNameToConditionPrimitiveType("integer"));
        assertEquals(JDoctorConditionPrimitiveType.JP_INTEGER, JDoctorConditionPrimitiveType.convertTypeNameToConditionPrimitiveType("int"));
        assertEquals(JDoctorConditionPrimitiveType.JP_LONG, JDoctorConditionPrimitiveType.convertTypeNameToConditionPrimitiveType("long"));
        assertEquals(JDoctorConditionPrimitiveType.JP_SHORT, JDoctorConditionPrimitiveType.convertTypeNameToConditionPrimitiveType("short"));
    }

    @Test
    public void jDoctorToJPTest() {
        assertEquals(JDoctorConditionPrimitiveType.JP_BOOLEAN, JDoctorConditionPrimitiveType.jDoctorToJP(JDoctorConditionPrimitiveType.CONDITION_BOOLEAN));
        assertEquals(JDoctorConditionPrimitiveType.JP_BYTE, JDoctorConditionPrimitiveType.jDoctorToJP(JDoctorConditionPrimitiveType.CONDITION_BYTE));
        assertEquals(JDoctorConditionPrimitiveType.JP_CHAR, JDoctorConditionPrimitiveType.jDoctorToJP(JDoctorConditionPrimitiveType.CONDITION_CHAR));
        assertEquals(JDoctorConditionPrimitiveType.JP_DOUBLE, JDoctorConditionPrimitiveType.jDoctorToJP(JDoctorConditionPrimitiveType.CONDITION_DOUBLE));
        assertEquals(JDoctorConditionPrimitiveType.JP_FLOAT, JDoctorConditionPrimitiveType.jDoctorToJP(JDoctorConditionPrimitiveType.CONDITION_FLOAT));
        assertEquals(JDoctorConditionPrimitiveType.JP_INTEGER, JDoctorConditionPrimitiveType.jDoctorToJP(JDoctorConditionPrimitiveType.CONDITION_INTEGER));
        assertEquals(JDoctorConditionPrimitiveType.JP_LONG, JDoctorConditionPrimitiveType.jDoctorToJP(JDoctorConditionPrimitiveType.CONDITION_LONG));
        assertEquals(JDoctorConditionPrimitiveType.JP_SHORT, JDoctorConditionPrimitiveType.jDoctorToJP(JDoctorConditionPrimitiveType.CONDITION_SHORT));
    }

    @Test
    public void jpToJDoctorTest() {
        assertEquals(JDoctorConditionPrimitiveType.CONDITION_BOOLEAN, JDoctorConditionPrimitiveType.jpToJDoctor(JDoctorConditionPrimitiveType.JP_BOOLEAN));
        assertEquals(JDoctorConditionPrimitiveType.CONDITION_BYTE, JDoctorConditionPrimitiveType.jpToJDoctor(JDoctorConditionPrimitiveType.JP_BYTE));
        assertEquals(JDoctorConditionPrimitiveType.CONDITION_CHAR, JDoctorConditionPrimitiveType.jpToJDoctor(JDoctorConditionPrimitiveType.JP_CHAR));
        assertEquals(JDoctorConditionPrimitiveType.CONDITION_DOUBLE, JDoctorConditionPrimitiveType.jpToJDoctor(JDoctorConditionPrimitiveType.JP_DOUBLE));
        assertEquals(JDoctorConditionPrimitiveType.CONDITION_FLOAT, JDoctorConditionPrimitiveType.jpToJDoctor(JDoctorConditionPrimitiveType.JP_FLOAT));
        assertEquals(JDoctorConditionPrimitiveType.CONDITION_INTEGER, JDoctorConditionPrimitiveType.jpToJDoctor(JDoctorConditionPrimitiveType.JP_INTEGER));
        assertEquals(JDoctorConditionPrimitiveType.CONDITION_LONG, JDoctorConditionPrimitiveType.jpToJDoctor(JDoctorConditionPrimitiveType.JP_LONG));
        assertEquals(JDoctorConditionPrimitiveType.CONDITION_SHORT, JDoctorConditionPrimitiveType.jpToJDoctor(JDoctorConditionPrimitiveType.JP_SHORT));
    }

    @Test
    public void getAllJDoctorPrimitiveTypesTest() {
        List<String> expected = List.of("Z", "B", "C", "D", "F", "I", "J", "S");
        assertEquals(expected, JDoctorConditionPrimitiveType.getAllJDoctorPrimitiveTypeNames());
    }

    @Test
    public void getAllJPPrimitiveTypesTest() {
        List<String> expected = List.of("boolean", "byte", "char", "double", "float", "int", "long", "short");
        assertEquals(expected, JDoctorConditionPrimitiveType.getAllJPPrimitiveTypeNames());
    }

    @Test
    public void getJDoctorRegexValuesTest() {
        String expected = "[ZBCDFIJS]";
        assertEquals(expected, JDoctorConditionPrimitiveType.getJDoctorValuesRegex());
    }

    @Test
    public void getJPRegexValuesTest() {
        String expected = "[booleanbytechardoublefloatintlongshort]";
        assertEquals(expected, JDoctorConditionPrimitiveType.getJPValuesRegex());
    }

    @Test
    public void getValueTest() {
        assertEquals("Z", JDoctorConditionPrimitiveType.CONDITION_BOOLEAN.getTypeName());
        assertEquals("B", JDoctorConditionPrimitiveType.CONDITION_BYTE.getTypeName());
        assertEquals("C", JDoctorConditionPrimitiveType.CONDITION_CHAR.getTypeName());
        assertEquals("D", JDoctorConditionPrimitiveType.CONDITION_DOUBLE.getTypeName());
        assertEquals("F", JDoctorConditionPrimitiveType.CONDITION_FLOAT.getTypeName());
        assertEquals("I", JDoctorConditionPrimitiveType.CONDITION_INTEGER.getTypeName());
        assertEquals("J", JDoctorConditionPrimitiveType.CONDITION_LONG.getTypeName());
        assertEquals("S", JDoctorConditionPrimitiveType.CONDITION_SHORT.getTypeName());
        assertEquals("boolean", JDoctorConditionPrimitiveType.JP_BOOLEAN.getTypeName());
        assertEquals("byte", JDoctorConditionPrimitiveType.JP_BYTE.getTypeName());
        assertEquals("char", JDoctorConditionPrimitiveType.JP_CHAR.getTypeName());
        assertEquals("double", JDoctorConditionPrimitiveType.JP_DOUBLE.getTypeName());
        assertEquals("float", JDoctorConditionPrimitiveType.JP_FLOAT.getTypeName());
        assertEquals("int", JDoctorConditionPrimitiveType.JP_INTEGER.getTypeName());
        assertEquals("long", JDoctorConditionPrimitiveType.JP_LONG.getTypeName());
        assertEquals("short", JDoctorConditionPrimitiveType.JP_SHORT.getTypeName());
    }
}
