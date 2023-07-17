package star.tratto.identifiers;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CommonPrimitiveTypeTest {
    @Test
    public void convertFieldDescriptorToSourceCodeTest() {
        assertEquals("boolean", CommonPrimitiveType.convertFieldDescriptorToSourceCode("Z"));
        assertEquals("byte", CommonPrimitiveType.convertFieldDescriptorToSourceCode("B"));
        assertEquals("char", CommonPrimitiveType.convertFieldDescriptorToSourceCode("C"));
        assertEquals("short", CommonPrimitiveType.convertFieldDescriptorToSourceCode("S"));
        assertEquals("int", CommonPrimitiveType.convertFieldDescriptorToSourceCode("I"));
        assertEquals("long", CommonPrimitiveType.convertFieldDescriptorToSourceCode("J"));
        assertEquals("float", CommonPrimitiveType.convertFieldDescriptorToSourceCode("F"));
        assertEquals("double", CommonPrimitiveType.convertFieldDescriptorToSourceCode("D"));
        try {
            CommonPrimitiveType.convertSourceCodeToFieldDescriptor("definitely not primitive");
            fail();
        } catch (IllegalArgumentException ignored) {}
    }

    @Test
    public void convertSourceCodeToFieldDescriptorTest() {
        assertEquals("Z", CommonPrimitiveType.convertSourceCodeToFieldDescriptor("boolean"));
        assertEquals("B", CommonPrimitiveType.convertSourceCodeToFieldDescriptor("byte"));
        assertEquals("C", CommonPrimitiveType.convertSourceCodeToFieldDescriptor("char"));
        assertEquals("S", CommonPrimitiveType.convertSourceCodeToFieldDescriptor("short"));
        assertEquals("I", CommonPrimitiveType.convertSourceCodeToFieldDescriptor("int"));
        assertEquals("J", CommonPrimitiveType.convertSourceCodeToFieldDescriptor("long"));
        assertEquals("F", CommonPrimitiveType.convertSourceCodeToFieldDescriptor("float"));
        assertEquals("D", CommonPrimitiveType.convertSourceCodeToFieldDescriptor("double"));
        try {
            CommonPrimitiveType.convertSourceCodeToFieldDescriptor("definitely not primitive");
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
        assertEquals(expected, CommonPrimitiveType.getAllFieldDescriptors());
    }

    @Test
    public void getAllFieldDescriptorsRegexTest() {
        String expected = "[ZBCSIJFD]";
        assertEquals(expected, CommonPrimitiveType.getAllFieldDescriptorsRegex());
    }
}
