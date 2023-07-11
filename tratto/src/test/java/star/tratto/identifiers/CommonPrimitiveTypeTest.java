package star.tratto.identifiers;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CommonPrimitiveTypeTest {
    @Test
    public void typeNameToConditionPrimitiveTypeTest() {
        assertEquals(CommonPrimitiveType.CONDITION_BOOLEAN, CommonPrimitiveType.convertTypeNameToConditionPrimitiveType("Z"));
        assertEquals(CommonPrimitiveType.CONDITION_BYTE, CommonPrimitiveType.convertTypeNameToConditionPrimitiveType("B"));
        assertEquals(CommonPrimitiveType.CONDITION_CHAR, CommonPrimitiveType.convertTypeNameToConditionPrimitiveType("C"));
        assertEquals(CommonPrimitiveType.CONDITION_DOUBLE, CommonPrimitiveType.convertTypeNameToConditionPrimitiveType("D"));
        assertEquals(CommonPrimitiveType.CONDITION_FLOAT, CommonPrimitiveType.convertTypeNameToConditionPrimitiveType("F"));
        assertEquals(CommonPrimitiveType.CONDITION_INTEGER, CommonPrimitiveType.convertTypeNameToConditionPrimitiveType("I"));
        assertEquals(CommonPrimitiveType.CONDITION_LONG, CommonPrimitiveType.convertTypeNameToConditionPrimitiveType("J"));
        assertEquals(CommonPrimitiveType.CONDITION_SHORT, CommonPrimitiveType.convertTypeNameToConditionPrimitiveType("S"));
        assertEquals(CommonPrimitiveType.JP_BOOLEAN, CommonPrimitiveType.convertTypeNameToConditionPrimitiveType("boolean"));
        assertEquals(CommonPrimitiveType.JP_BYTE, CommonPrimitiveType.convertTypeNameToConditionPrimitiveType("byte"));
        assertEquals(CommonPrimitiveType.JP_CHAR, CommonPrimitiveType.convertTypeNameToConditionPrimitiveType("char"));
        assertEquals(CommonPrimitiveType.JP_DOUBLE, CommonPrimitiveType.convertTypeNameToConditionPrimitiveType("double"));
        assertEquals(CommonPrimitiveType.JP_FLOAT, CommonPrimitiveType.convertTypeNameToConditionPrimitiveType("float"));
        assertEquals(CommonPrimitiveType.JP_INTEGER, CommonPrimitiveType.convertTypeNameToConditionPrimitiveType("integer"));
        assertEquals(CommonPrimitiveType.JP_INTEGER, CommonPrimitiveType.convertTypeNameToConditionPrimitiveType("int"));
        assertEquals(CommonPrimitiveType.JP_LONG, CommonPrimitiveType.convertTypeNameToConditionPrimitiveType("long"));
        assertEquals(CommonPrimitiveType.JP_SHORT, CommonPrimitiveType.convertTypeNameToConditionPrimitiveType("short"));
    }

    @Test
    public void jDoctorToJPTest() {
        assertEquals(CommonPrimitiveType.JP_BOOLEAN, CommonPrimitiveType.jDoctorToJP(CommonPrimitiveType.CONDITION_BOOLEAN));
        assertEquals(CommonPrimitiveType.JP_BYTE, CommonPrimitiveType.jDoctorToJP(CommonPrimitiveType.CONDITION_BYTE));
        assertEquals(CommonPrimitiveType.JP_CHAR, CommonPrimitiveType.jDoctorToJP(CommonPrimitiveType.CONDITION_CHAR));
        assertEquals(CommonPrimitiveType.JP_DOUBLE, CommonPrimitiveType.jDoctorToJP(CommonPrimitiveType.CONDITION_DOUBLE));
        assertEquals(CommonPrimitiveType.JP_FLOAT, CommonPrimitiveType.jDoctorToJP(CommonPrimitiveType.CONDITION_FLOAT));
        assertEquals(CommonPrimitiveType.JP_INTEGER, CommonPrimitiveType.jDoctorToJP(CommonPrimitiveType.CONDITION_INTEGER));
        assertEquals(CommonPrimitiveType.JP_LONG, CommonPrimitiveType.jDoctorToJP(CommonPrimitiveType.CONDITION_LONG));
        assertEquals(CommonPrimitiveType.JP_SHORT, CommonPrimitiveType.jDoctorToJP(CommonPrimitiveType.CONDITION_SHORT));
    }

    @Test
    public void jpToJDoctorTest() {
        assertEquals(CommonPrimitiveType.CONDITION_BOOLEAN, CommonPrimitiveType.jpToJDoctor(CommonPrimitiveType.JP_BOOLEAN));
        assertEquals(CommonPrimitiveType.CONDITION_BYTE, CommonPrimitiveType.jpToJDoctor(CommonPrimitiveType.JP_BYTE));
        assertEquals(CommonPrimitiveType.CONDITION_CHAR, CommonPrimitiveType.jpToJDoctor(CommonPrimitiveType.JP_CHAR));
        assertEquals(CommonPrimitiveType.CONDITION_DOUBLE, CommonPrimitiveType.jpToJDoctor(CommonPrimitiveType.JP_DOUBLE));
        assertEquals(CommonPrimitiveType.CONDITION_FLOAT, CommonPrimitiveType.jpToJDoctor(CommonPrimitiveType.JP_FLOAT));
        assertEquals(CommonPrimitiveType.CONDITION_INTEGER, CommonPrimitiveType.jpToJDoctor(CommonPrimitiveType.JP_INTEGER));
        assertEquals(CommonPrimitiveType.CONDITION_LONG, CommonPrimitiveType.jpToJDoctor(CommonPrimitiveType.JP_LONG));
        assertEquals(CommonPrimitiveType.CONDITION_SHORT, CommonPrimitiveType.jpToJDoctor(CommonPrimitiveType.JP_SHORT));
    }

    @Test
    public void getAllJDoctorPrimitiveTypesTest() {
        List<String> expected = List.of("Z", "B", "C", "D", "F", "I", "J", "S");
        assertEquals(expected, CommonPrimitiveType.getAllJDoctorPrimitiveTypeNames());
    }

    @Test
    public void getAllJPPrimitiveTypesTest() {
        List<String> expected = List.of("boolean", "byte", "char", "double", "float", "int", "long", "short");
        assertEquals(expected, CommonPrimitiveType.getAllJPPrimitiveTypeNames());
    }

    @Test
    public void getJDoctorRegexValuesTest() {
        String expected = "[ZBCDFIJS]";
        assertEquals(expected, CommonPrimitiveType.getJDoctorValuesRegex());
    }

    @Test
    public void getJPRegexValuesTest() {
        String expected = "[booleanbytechardoublefloatintlongshort]";
        assertEquals(expected, CommonPrimitiveType.getJPValuesRegex());
    }

    @Test
    public void getValueTest() {
        assertEquals("Z", CommonPrimitiveType.CONDITION_BOOLEAN.getTypeName());
        assertEquals("B", CommonPrimitiveType.CONDITION_BYTE.getTypeName());
        assertEquals("C", CommonPrimitiveType.CONDITION_CHAR.getTypeName());
        assertEquals("D", CommonPrimitiveType.CONDITION_DOUBLE.getTypeName());
        assertEquals("F", CommonPrimitiveType.CONDITION_FLOAT.getTypeName());
        assertEquals("I", CommonPrimitiveType.CONDITION_INTEGER.getTypeName());
        assertEquals("J", CommonPrimitiveType.CONDITION_LONG.getTypeName());
        assertEquals("S", CommonPrimitiveType.CONDITION_SHORT.getTypeName());
        assertEquals("boolean", CommonPrimitiveType.JP_BOOLEAN.getTypeName());
        assertEquals("byte", CommonPrimitiveType.JP_BYTE.getTypeName());
        assertEquals("char", CommonPrimitiveType.JP_CHAR.getTypeName());
        assertEquals("double", CommonPrimitiveType.JP_DOUBLE.getTypeName());
        assertEquals("float", CommonPrimitiveType.JP_FLOAT.getTypeName());
        assertEquals("int", CommonPrimitiveType.JP_INTEGER.getTypeName());
        assertEquals("long", CommonPrimitiveType.JP_LONG.getTypeName());
        assertEquals("short", CommonPrimitiveType.JP_SHORT.getTypeName());
    }
}
