package star.tratto.identifiers;

import org.junit.jupiter.api.Test;
import star.tratto.util.PrimitiveTypeUtils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PrimitiveTypeUtilsTest {
    @Test
    public void convertFieldDescriptorToSourceCodeTest() {
        assertEquals("boolean", PrimitiveTypeUtils.convertFieldDescriptorToSourceCode("Z"));
        assertEquals("byte", PrimitiveTypeUtils.convertFieldDescriptorToSourceCode("B"));
        assertEquals("char", PrimitiveTypeUtils.convertFieldDescriptorToSourceCode("C"));
        assertEquals("short", PrimitiveTypeUtils.convertFieldDescriptorToSourceCode("S"));
        assertEquals("int", PrimitiveTypeUtils.convertFieldDescriptorToSourceCode("I"));
        assertEquals("long", PrimitiveTypeUtils.convertFieldDescriptorToSourceCode("J"));
        assertEquals("float", PrimitiveTypeUtils.convertFieldDescriptorToSourceCode("F"));
        assertEquals("double", PrimitiveTypeUtils.convertFieldDescriptorToSourceCode("D"));
        try {
            PrimitiveTypeUtils.convertSourceCodeToFieldDescriptor("definitely not primitive");
            fail();
        } catch (IllegalArgumentException ignored) {}
    }

    @Test
    public void convertSourceCodeToFieldDescriptorTest() {
        assertEquals("Z", PrimitiveTypeUtils.convertSourceCodeToFieldDescriptor("boolean"));
        assertEquals("B", PrimitiveTypeUtils.convertSourceCodeToFieldDescriptor("byte"));
        assertEquals("C", PrimitiveTypeUtils.convertSourceCodeToFieldDescriptor("char"));
        assertEquals("S", PrimitiveTypeUtils.convertSourceCodeToFieldDescriptor("short"));
        assertEquals("I", PrimitiveTypeUtils.convertSourceCodeToFieldDescriptor("int"));
        assertEquals("J", PrimitiveTypeUtils.convertSourceCodeToFieldDescriptor("long"));
        assertEquals("F", PrimitiveTypeUtils.convertSourceCodeToFieldDescriptor("float"));
        assertEquals("D", PrimitiveTypeUtils.convertSourceCodeToFieldDescriptor("double"));
        try {
            PrimitiveTypeUtils.convertSourceCodeToFieldDescriptor("definitely not primitive");
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
