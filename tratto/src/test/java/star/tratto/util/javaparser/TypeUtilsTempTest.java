package star.tratto.util.javaparser;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TypeUtilsTempTest {
    @Test
    public void removeTypeArgumentsTest() {
        assertEquals("List", TypeUtilsTemp.removeTypeArguments("List<? extends Integer>"));
        assertEquals("ArrayList", TypeUtilsTemp.removeTypeArguments("ArrayList<? super Number>"));
        assertEquals("Oversuperstition", TypeUtilsTemp.removeTypeArguments("Oversuperstition"));
        assertEquals("Foo", TypeUtilsTemp.removeTypeArguments("Foo<? super Collection<T>, T>"));
    }

    @Test
    public void getNameSegmentsTest() {
        assertEquals(List.of("normal", "package", "setup", "for", "a", "Class"), TypeUtilsTemp.getNameSegments("normal.package.setup.for.a.Class"));
        assertEquals(List.of("spicy", "example", "that", "uses", "Many", "Inner", "Classes"), TypeUtilsTemp.getNameSegments("spicy.example.that.uses.Many$Inner$Classes"));
        assertEquals(List.of("even", "spicier", "with", "Inner", "Class", "andMethods"), TypeUtilsTemp.getNameSegments("even.spicier.with.Inner$Class.andMethods"));
    }

    @Test
    public void getPackageNameFromNameSegmentsTest() {
        assertEquals("normal.package.setup.for.a", TypeUtilsTemp.getPackageNameFromNameSegments(List.of("normal", "package", "setup", "for", "a", "Class")));
    }

    @Test
    public void getClassNameFromNameSegmentsTest() {
        assertEquals("Class", TypeUtilsTemp.getClassNameFromNameSegments(List.of("normal", "package", "setup", "for", "a", "Class")));
    }

    @Test
    public void fieldDescriptorNamesToSourceCodeNamesTest() {
        assertEquals(List.of("byte[]", "int"), TypeUtilsTemp.fieldDescriptorNamesToSourceCodeNames(List.of("[B", "int")));
        assertEquals(List.of("char[][]"), TypeUtilsTemp.fieldDescriptorNamesToSourceCodeNames(List.of("[[C")));
        assertEquals(List.of("SuperCoolClass[][]"), TypeUtilsTemp.fieldDescriptorNamesToSourceCodeNames(List.of("[[com.google.SuperCoolClass")));
        assertEquals(List.of("Type"), TypeUtilsTemp.fieldDescriptorNamesToSourceCodeNames(List.of("Type<with parameters>")));
    }

    @Test
    public void isStandardTypeTest() {
        assertTrue(TypeUtilsTemp.isStandardType("Comparable"));
        assertFalse(TypeUtilsTemp.isStandardType("Other"));
    }

    @Test
    public void isStandardTypeArrayTest() {
        assertTrue(TypeUtilsTemp.isStandardTypeArray("Object[]"));
        assertFalse(TypeUtilsTemp.isStandardTypeArray("Object"));
        assertFalse(TypeUtilsTemp.isStandardTypeArray("AnyOtherObject"));
        assertFalse(TypeUtilsTemp.isStandardTypeArray("AnyOtherObjectArray[]"));
    }

    @Test
    public void hasJPTypeEllipsisTest() {
        assertTrue(TypeUtilsTemp.hasEllipsis("Integer..."));
        assertFalse(TypeUtilsTemp.hasEllipsis("Integer"));
    }
}
