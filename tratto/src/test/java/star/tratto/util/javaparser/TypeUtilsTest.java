package star.tratto.util.javaparser;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TypeUtilsTest {
    @Test
    public void removeTypeArgumentsTest() {
        assertEquals("List", TypeUtils.removeTypeArguments("List<? extends Integer>"));
        assertEquals("ArrayList", TypeUtils.removeTypeArguments("ArrayList<? super Number>"));
        assertEquals("Oversuperstition", TypeUtils.removeTypeArguments("Oversuperstition"));
        assertEquals("Foo", TypeUtils.removeTypeArguments("Foo<? super Collection<T>, T>"));
    }

    @Test
    public void getTypeSegmentsTest() {
        assertEquals(List.of("path/to"), TypeUtils.getNameSegments("path/to/Class.java"));
    }

    @Test
    public void convertJDoctorTypeNamesToJPTypeNamesTest() {
        assertEquals(List.of("byte[]", "int"), TypeUtils.fieldDescriptorNamesToSourceCodeNames(List.of("[B", "int")));
        assertEquals(List.of("char[][]"), TypeUtils.fieldDescriptorNamesToSourceCodeNames(List.of("[[C")));
    }

    @Test
    public void isStandardTypeTest() {
        assertTrue(TypeUtils.isStandardType("Comparable"));
        assertFalse(TypeUtils.isStandardType("Other"));
    }

    @Test
    public void isStandardTypeArrayTest() {
        assertTrue(TypeUtils.isStandardTypeArray("Object[]"));
        assertFalse(TypeUtils.isStandardTypeArray("Object"));
        assertFalse(TypeUtils.isStandardTypeArray("AnyOtherObject"));
        assertFalse(TypeUtils.isStandardTypeArray("AnyOtherObjectArray[]"));
    }

    @Test
    public void hasJPTypeEllipsisTest() {
        assertTrue(TypeUtils.hasEllipsis("Integer..."));
        assertFalse(TypeUtils.hasEllipsis("Integer"));
    }
}
