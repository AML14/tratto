package star.tratto.util.javaparser;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TypeUtilsTest {
    @Test
    public void removeTypeArgumentsTest() {
        assertEquals("List", TypeUtils.removeTypeArgumentsAndSemicolon("List<? extends Integer>"));
        assertEquals("ArrayList", TypeUtils.removeTypeArgumentsAndSemicolon("ArrayList<? super Number>"));
        assertEquals("Oversuperstition", TypeUtils.removeTypeArgumentsAndSemicolon("Oversuperstition"));
        assertEquals("Foo", TypeUtils.removeTypeArgumentsAndSemicolon("Foo<? super Collection<T>, T>"));
    }

    @Test
    public void getNameSegmentsTest() {
        assertEquals(List.of("normal", "package", "setup", "for", "a", "Class"), TypeUtils.getNameSegments("normal.package.setup.for.a.Class"));
        assertEquals(List.of("spicy", "example", "that", "uses", "Many", "Inner", "Classes"), TypeUtils.getNameSegments("spicy.example.that.uses.Many$Inner$Classes"));
        assertEquals(List.of("even", "spicier", "with", "Inner", "Class", "andMethods"), TypeUtils.getNameSegments("even.spicier.with.Inner$Class.andMethods"));
    }

    @Test
    public void getPackageNameFromNameSegmentsTest() {
        assertEquals("normal.package.setup.for.a", TypeUtils.getPackageNameFromNameSegments(List.of("normal", "package", "setup", "for", "a", "Class")));
    }

    @Test
    public void getClassNameFromNameSegmentsTest() {
        assertEquals("Class", TypeUtils.getClassNameFromNameSegments(List.of("normal", "package", "setup", "for", "a", "Class")));
    }

    @Test
    public void fieldDescriptorNamesToSourceCodeNamesTest() {
        assertEquals(List.of("byte[]", "int"), TypeUtils.fieldDescriptorNamesToSourceCodeNames(List.of("[B", "int")));
        assertEquals(List.of("char[][]"), TypeUtils.fieldDescriptorNamesToSourceCodeNames(List.of("[[C")));
        assertEquals(List.of("SuperCoolClass[][]"), TypeUtils.fieldDescriptorNamesToSourceCodeNames(List.of("[[com.google.SuperCoolClass")));
        assertEquals(List.of("Type"), TypeUtils.fieldDescriptorNamesToSourceCodeNames(List.of("Type<with parameters>")));
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
