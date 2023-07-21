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
        assertEquals(List.of("normal", "package", "setup", "for", "a", "Class"), TypeUtilsTemp.getNameSegments("normal.package.setup.for.a.Class"));
        assertEquals(List.of("spicy", "example", "that", "uses", "Many", "Inner", "Classes"), TypeUtilsTemp.getNameSegments("spicy.example.that.uses.Many$Inner$Classes"));
        assertEquals(List.of("even", "spicier", "with", "Inner", "Class", "andMethods"), TypeUtilsTemp.getNameSegments("even.spicier.with.Inner$Class$andMethods"));
    }

    @Test
    public void getPackageNameFromNameSegmentsTest() {
        assertEquals("normal.package.setup.for.a", TypeUtilsTemp.getPackageNameFromNameSegments(List.of("normal", "package", "setup", "for", "a", "Class")));
    }

    @Test
    public void getClassNameFromNameSegmentsTest() {
        assertEquals("Class", TypeUtilsTemp.getClassNameFromNameSegments(List.of("normal", "package", "setup", "for", "a", "Class")));
    }
}
