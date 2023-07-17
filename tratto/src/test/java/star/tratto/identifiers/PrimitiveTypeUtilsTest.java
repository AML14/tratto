package star.tratto.identifiers;

import org.junit.jupiter.api.Test;
import star.tratto.util.javaparser.PrimitiveTypeUtils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PrimitiveTypeUtilsTest {
    @Test
    public void convertFieldDescriptorToSourceCodeTest() {
        assertEquals("boolean", PrimitiveTypeUtils.convertFieldDescriptorToPrimitiveType("Z"));
        assertEquals("byte", PrimitiveTypeUtils.convertFieldDescriptorToPrimitiveType("B"));
        assertEquals("char", PrimitiveTypeUtils.convertFieldDescriptorToPrimitiveType("C"));
        assertEquals("short", PrimitiveTypeUtils.convertFieldDescriptorToPrimitiveType("S"));
        assertEquals("int", PrimitiveTypeUtils.convertFieldDescriptorToPrimitiveType("I"));
        assertEquals("long", PrimitiveTypeUtils.convertFieldDescriptorToPrimitiveType("J"));
        assertEquals("float", PrimitiveTypeUtils.convertFieldDescriptorToPrimitiveType("F"));
        assertEquals("double", PrimitiveTypeUtils.convertFieldDescriptorToPrimitiveType("D"));
        try {
            PrimitiveTypeUtils.convertPrimitiveTypeToFieldDescriptor("definitely not primitive");
            fail();
        } catch (IllegalArgumentException ignored) {}
    }

    @Test
    public void convertSourceCodeToFieldDescriptorTest() {
        assertEquals("Z", PrimitiveTypeUtils.convertPrimitiveTypeToFieldDescriptor("boolean"));
        assertEquals("B", PrimitiveTypeUtils.convertPrimitiveTypeToFieldDescriptor("byte"));
        assertEquals("C", PrimitiveTypeUtils.convertPrimitiveTypeToFieldDescriptor("char"));
        assertEquals("S", PrimitiveTypeUtils.convertPrimitiveTypeToFieldDescriptor("short"));
        assertEquals("I", PrimitiveTypeUtils.convertPrimitiveTypeToFieldDescriptor("int"));
        assertEquals("J", PrimitiveTypeUtils.convertPrimitiveTypeToFieldDescriptor("long"));
        assertEquals("F", PrimitiveTypeUtils.convertPrimitiveTypeToFieldDescriptor("float"));
        assertEquals("D", PrimitiveTypeUtils.convertPrimitiveTypeToFieldDescriptor("double"));
        try {
            PrimitiveTypeUtils.convertPrimitiveTypeToFieldDescriptor("definitely not primitive");
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
        assertEquals(expected, PrimitiveTypeUtils.getAllFieldDescriptors());
    }

    @Test
    public void getAllFieldDescriptorsRegexTest() {
        String expected = "[ZBCSIJFD]";
        assertEquals(expected, PrimitiveTypeUtils.getAllFieldDescriptorsRegex());
    }
}
