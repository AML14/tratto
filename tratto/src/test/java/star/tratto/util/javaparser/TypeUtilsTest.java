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
        assertEquals(List.of("byte[]", "int"), TypeUtils.fieldDescriptorsToSourceFormats(List.of("[B", "I")));
        assertEquals(List.of("char[][]"), TypeUtils.fieldDescriptorsToSourceFormats(List.of("[[C")));
        assertEquals(List.of("SuperCoolClass[][]"), TypeUtils.fieldDescriptorsToSourceFormats(List.of("[[com.google.SuperCoolClass")));
        assertEquals(List.of("Type"), TypeUtils.fieldDescriptorsToSourceFormats(List.of("Type<with parameters>")));
    }
}
