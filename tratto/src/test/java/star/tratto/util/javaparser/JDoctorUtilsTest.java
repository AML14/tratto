package star.tratto.util.javaparser;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Parameter;

import static org.junit.jupiter.api.Assertions.*;

public class JDoctorUtilsTest {

    @Test
    public void isStandardTypeTest() {
        assertTrue(JDoctorUtils.isStandardType("Comparable"));
        assertFalse(JDoctorUtils.isStandardType("Other"));
    }

    @Test
    public void isStandardTypeArrayTest() {
        assertTrue(JDoctorUtils.isStandardTypeArray("Object[]"));
        assertFalse(JDoctorUtils.isStandardTypeArray("Object"));
        assertFalse(JDoctorUtils.isStandardTypeArray("AnyOtherObject"));
        assertFalse(JDoctorUtils.isStandardTypeArray("AnyOtherObjectArray[]"));
    }

    @Test
    public void hasJPTypeEllipsisTest() {
        assertTrue(JDoctorUtils.hasEllipsis("Integer..."));
        assertFalse(JDoctorUtils.hasEllipsis("Integer"));
    }

    @Test
    public void removeSpuriousCharactersTest() {
        assertEquals("List", JDoctorUtils.removeSpuriousCharacters("List<? extends Integer>"));
        assertEquals("ArrayList", JDoctorUtils.removeSpuriousCharacters("ArrayList<? super Number>"));
        assertEquals("Oversuperstition", JDoctorUtils.removeSpuriousCharacters("Oversuperstition"));
    }
}
