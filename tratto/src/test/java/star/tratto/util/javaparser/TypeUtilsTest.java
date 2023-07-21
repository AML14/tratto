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
}
