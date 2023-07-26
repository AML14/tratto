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
        assertEquals(List.of("even", "spicier", "with", "Inner", "Class", "andMethods"), TypeUtils.getNameSegments("even.spicier.with.Inner$Class$andMethods"));
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
    public void fieldDescriptorsToSourceFormatsTest() {
        assertEquals(List.of("boolean", "byte", "char", "short", "int", "long", "float", "double"), TypeUtils.fieldDescriptorsToSourceFormats(List.of("Z", "B", "C", "S", "I", "J", "F", "D")));
        assertEquals(List.of("RandomObject"), TypeUtils.fieldDescriptorsToSourceFormats(List.of("some.weird.RandomObject")));
        assertEquals(List.of("byte[]", "int"), TypeUtils.fieldDescriptorsToSourceFormats(List.of("[B", "I")));
        assertEquals(List.of("char[][]"), TypeUtils.fieldDescriptorsToSourceFormats(List.of("[[C")));
        assertEquals(List.of("SuperCoolClass[][]"), TypeUtils.fieldDescriptorsToSourceFormats(List.of("[[com.google.SuperCoolClass")));
        assertEquals(List.of("Type"), TypeUtils.fieldDescriptorsToSourceFormats(List.of("Type<with parameters>")));
        assertEquals(List.of("SuperCoolParameterizedType[]", "double"), TypeUtils.fieldDescriptorsToSourceFormats(List.of("[com.amazon.coretta.SuperCoolParameterizedType<with parameters<T>, Integer>", "D")));
    }

    @Test
    public void hasEllipsisTest() {
        String normalType = "int";
        String almost = "How..";
        String varArg = "int...";
        assertFalse(TypeUtils.hasEllipsis(normalType));
        assertFalse(TypeUtils.hasEllipsis(almost));
        assertTrue(TypeUtils.hasEllipsis(varArg));
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
}
