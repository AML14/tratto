package star.tratto.util.javaparser;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TypeUtilsTest {
    @Test
    public void convertJDoctorTypeNamesToJPTypeNamesTest() {
        assertEquals(List.of("byte[]", "int"), TypeUtils.convertJDoctorTypeNamesToJPTypeNames(List.of("[B", "int")));
        assertEquals(List.of("char[][]"), TypeUtils.convertJDoctorTypeNamesToJPTypeNames(List.of("[[C")));
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

    @Test
    public void removeSpuriousCharactersTest() {
        assertEquals("List", TypeUtils.removeSpuriousCharacters("List<? extends Integer>"));
        assertEquals("ArrayList", TypeUtils.removeSpuriousCharacters("ArrayList<? super Number>"));
        assertEquals("Oversuperstition", TypeUtils.removeSpuriousCharacters("Oversuperstition"));
    }
}
