package star.tratto.util.javaparser;

import org.junit.jupiter.api.Test;
import star.tratto.util.javaparser.PrimitiveTypeUtils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PrimitiveTypeUtilsTest {
    @Test
    public void convertFieldDescriptorToSourceCodeTest() {
        assertEquals("boolean", PrimitiveTypeUtils.fieldDescriptorToPrimitiveType("Z"));
        assertEquals("byte", PrimitiveTypeUtils.fieldDescriptorToPrimitiveType("B"));
        assertEquals("char", PrimitiveTypeUtils.fieldDescriptorToPrimitiveType("C"));
        assertEquals("short", PrimitiveTypeUtils.fieldDescriptorToPrimitiveType("S"));
        assertEquals("int", PrimitiveTypeUtils.fieldDescriptorToPrimitiveType("I"));
        assertEquals("long", PrimitiveTypeUtils.fieldDescriptorToPrimitiveType("J"));
        assertEquals("float", PrimitiveTypeUtils.fieldDescriptorToPrimitiveType("F"));
        assertEquals("double", PrimitiveTypeUtils.fieldDescriptorToPrimitiveType("D"));
        try {
            PrimitiveTypeUtils.primitiveTypeToFieldDescriptor("definitely not primitive");
            fail();
        } catch (IllegalArgumentException ignored) {}
    }

    @Test
    public void convertSourceCodeToFieldDescriptorTest() {
        assertEquals("Z", PrimitiveTypeUtils.primitiveTypeToFieldDescriptor("boolean"));
        assertEquals("B", PrimitiveTypeUtils.primitiveTypeToFieldDescriptor("byte"));
        assertEquals("C", PrimitiveTypeUtils.primitiveTypeToFieldDescriptor("char"));
        assertEquals("S", PrimitiveTypeUtils.primitiveTypeToFieldDescriptor("short"));
        assertEquals("I", PrimitiveTypeUtils.primitiveTypeToFieldDescriptor("int"));
        assertEquals("J", PrimitiveTypeUtils.primitiveTypeToFieldDescriptor("long"));
        assertEquals("F", PrimitiveTypeUtils.primitiveTypeToFieldDescriptor("float"));
        assertEquals("D", PrimitiveTypeUtils.primitiveTypeToFieldDescriptor("double"));
        try {
            PrimitiveTypeUtils.primitiveTypeToFieldDescriptor("definitely not primitive");
            fail();
        } catch (IllegalArgumentException ignored) {}
    }

    @Test
    public void getAllFieldDescriptorsTest() {
        List<String> expected = List.of(
                "Z",
                "B",
                "C",
                "S",
                "I",
                "J",
                "F",
                "D"
        );
        assertEquals(expected, PrimitiveTypeUtils.getAllPrimitiveFieldDescriptors());
    }

    @Test
    public void getAllFieldDescriptorsRegexTest() {
        String expected = "[ZBCSIJFD]";
        assertEquals(expected, PrimitiveTypeUtils.getAllPrimitiveFieldDescriptorsRegex());
    }
}
