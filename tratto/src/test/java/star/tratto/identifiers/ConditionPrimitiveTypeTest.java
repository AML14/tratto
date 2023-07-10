package star.tratto.identifiers;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ConditionPrimitiveTypeTest {
    @Test
    public void typeNameToConditionPrimitiveTypeTest() {
        assertEquals(ConditionPrimitiveType.CONDITION_BOOLEAN, ConditionPrimitiveType.convertTypeNameToConditionPrimitiveType("Z"));
        assertEquals(ConditionPrimitiveType.CONDITION_BYTE, ConditionPrimitiveType.convertTypeNameToConditionPrimitiveType("B"));
        assertEquals(ConditionPrimitiveType.CONDITION_CHAR, ConditionPrimitiveType.convertTypeNameToConditionPrimitiveType("C"));
        assertEquals(ConditionPrimitiveType.CONDITION_DOUBLE, ConditionPrimitiveType.convertTypeNameToConditionPrimitiveType("D"));
        assertEquals(ConditionPrimitiveType.CONDITION_FLOAT, ConditionPrimitiveType.convertTypeNameToConditionPrimitiveType("F"));
        assertEquals(ConditionPrimitiveType.CONDITION_INTEGER, ConditionPrimitiveType.convertTypeNameToConditionPrimitiveType("I"));
        assertEquals(ConditionPrimitiveType.CONDITION_LONG, ConditionPrimitiveType.convertTypeNameToConditionPrimitiveType("J"));
        assertEquals(ConditionPrimitiveType.CONDITION_SHORT, ConditionPrimitiveType.convertTypeNameToConditionPrimitiveType("S"));
        assertEquals(ConditionPrimitiveType.JP_BOOLEAN, ConditionPrimitiveType.convertTypeNameToConditionPrimitiveType("boolean"));
        assertEquals(ConditionPrimitiveType.JP_BYTE, ConditionPrimitiveType.convertTypeNameToConditionPrimitiveType("byte"));
        assertEquals(ConditionPrimitiveType.JP_CHAR, ConditionPrimitiveType.convertTypeNameToConditionPrimitiveType("char"));
        assertEquals(ConditionPrimitiveType.JP_DOUBLE, ConditionPrimitiveType.convertTypeNameToConditionPrimitiveType("double"));
        assertEquals(ConditionPrimitiveType.JP_FLOAT, ConditionPrimitiveType.convertTypeNameToConditionPrimitiveType("float"));
        assertEquals(ConditionPrimitiveType.JP_INTEGER, ConditionPrimitiveType.convertTypeNameToConditionPrimitiveType("integer"));
        assertEquals(ConditionPrimitiveType.JP_INTEGER, ConditionPrimitiveType.convertTypeNameToConditionPrimitiveType("int"));
        assertEquals(ConditionPrimitiveType.JP_LONG, ConditionPrimitiveType.convertTypeNameToConditionPrimitiveType("long"));
        assertEquals(ConditionPrimitiveType.JP_SHORT, ConditionPrimitiveType.convertTypeNameToConditionPrimitiveType("short"));
    }

    @Test
    public void jDoctorToJPTest() {
        assertEquals(ConditionPrimitiveType.JP_BOOLEAN, ConditionPrimitiveType.jDoctorToJP(ConditionPrimitiveType.CONDITION_BOOLEAN));
        assertEquals(ConditionPrimitiveType.JP_BYTE, ConditionPrimitiveType.jDoctorToJP(ConditionPrimitiveType.CONDITION_BYTE));
        assertEquals(ConditionPrimitiveType.JP_CHAR, ConditionPrimitiveType.jDoctorToJP(ConditionPrimitiveType.CONDITION_CHAR));
        assertEquals(ConditionPrimitiveType.JP_DOUBLE, ConditionPrimitiveType.jDoctorToJP(ConditionPrimitiveType.CONDITION_DOUBLE));
        assertEquals(ConditionPrimitiveType.JP_FLOAT, ConditionPrimitiveType.jDoctorToJP(ConditionPrimitiveType.CONDITION_FLOAT));
        assertEquals(ConditionPrimitiveType.JP_INTEGER, ConditionPrimitiveType.jDoctorToJP(ConditionPrimitiveType.CONDITION_INTEGER));
        assertEquals(ConditionPrimitiveType.JP_LONG, ConditionPrimitiveType.jDoctorToJP(ConditionPrimitiveType.CONDITION_LONG));
        assertEquals(ConditionPrimitiveType.JP_SHORT, ConditionPrimitiveType.jDoctorToJP(ConditionPrimitiveType.CONDITION_SHORT));
    }

    @Test
    public void jpToJDoctorTest() {
        assertEquals(ConditionPrimitiveType.CONDITION_BOOLEAN, ConditionPrimitiveType.jpToJDoctor(ConditionPrimitiveType.JP_BOOLEAN));
        assertEquals(ConditionPrimitiveType.CONDITION_BYTE, ConditionPrimitiveType.jpToJDoctor(ConditionPrimitiveType.JP_BYTE));
        assertEquals(ConditionPrimitiveType.CONDITION_CHAR, ConditionPrimitiveType.jpToJDoctor(ConditionPrimitiveType.JP_CHAR));
        assertEquals(ConditionPrimitiveType.CONDITION_DOUBLE, ConditionPrimitiveType.jpToJDoctor(ConditionPrimitiveType.JP_DOUBLE));
        assertEquals(ConditionPrimitiveType.CONDITION_FLOAT, ConditionPrimitiveType.jpToJDoctor(ConditionPrimitiveType.JP_FLOAT));
        assertEquals(ConditionPrimitiveType.CONDITION_INTEGER, ConditionPrimitiveType.jpToJDoctor(ConditionPrimitiveType.JP_INTEGER));
        assertEquals(ConditionPrimitiveType.CONDITION_LONG, ConditionPrimitiveType.jpToJDoctor(ConditionPrimitiveType.JP_LONG));
        assertEquals(ConditionPrimitiveType.CONDITION_SHORT, ConditionPrimitiveType.jpToJDoctor(ConditionPrimitiveType.JP_SHORT));
    }

    @Test
    public void getAllJDoctorPrimitiveTypesTest() {
        List<String> expected = List.of("Z", "B", "C", "D", "F", "I", "J", "S");
        assertEquals(expected, ConditionPrimitiveType.getAllJDoctorPrimitiveTypeNames());
    }

    @Test
    public void getAllJPPrimitiveTypesTest() {
        List<String> expected = List.of("boolean", "byte", "char", "double", "float", "int", "long", "short");
        assertEquals(expected, ConditionPrimitiveType.getAllJPPrimitiveTypeNames());
    }

    @Test
    public void getJDoctorRegexValuesTest() {
        String expected = "[ZBCDFIJS]";
        assertEquals(expected, ConditionPrimitiveType.getJDoctorValuesRegex());
    }

    @Test
    public void getJPRegexValuesTest() {
        String expected = "[booleanbytechardoublefloatintlongshort]";
        assertEquals(expected, ConditionPrimitiveType.getJPValuesRegex());
    }

    @Test
    public void getValueTest() {
        assertEquals("Z", ConditionPrimitiveType.CONDITION_BOOLEAN.getTypeName());
        assertEquals("B", ConditionPrimitiveType.CONDITION_BYTE.getTypeName());
        assertEquals("C", ConditionPrimitiveType.CONDITION_CHAR.getTypeName());
        assertEquals("D", ConditionPrimitiveType.CONDITION_DOUBLE.getTypeName());
        assertEquals("F", ConditionPrimitiveType.CONDITION_FLOAT.getTypeName());
        assertEquals("I", ConditionPrimitiveType.CONDITION_INTEGER.getTypeName());
        assertEquals("J", ConditionPrimitiveType.CONDITION_LONG.getTypeName());
        assertEquals("S", ConditionPrimitiveType.CONDITION_SHORT.getTypeName());
        assertEquals("boolean", ConditionPrimitiveType.JP_BOOLEAN.getTypeName());
        assertEquals("byte", ConditionPrimitiveType.JP_BYTE.getTypeName());
        assertEquals("char", ConditionPrimitiveType.JP_CHAR.getTypeName());
        assertEquals("double", ConditionPrimitiveType.JP_DOUBLE.getTypeName());
        assertEquals("float", ConditionPrimitiveType.JP_FLOAT.getTypeName());
        assertEquals("int", ConditionPrimitiveType.JP_INTEGER.getTypeName());
        assertEquals("long", ConditionPrimitiveType.JP_LONG.getTypeName());
        assertEquals("short", ConditionPrimitiveType.JP_SHORT.getTypeName());
    }
}
