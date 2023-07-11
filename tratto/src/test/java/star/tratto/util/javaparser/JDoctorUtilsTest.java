package star.tratto.util.javaparser;

import star.tratto.util.javaparser.JDoctorUtils;
import org.junit.jupiter.api.Test;

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
        assertTrue(JDoctorUtils.hasJPTypeEllipsis("Integer..."));
        assertFalse(JDoctorUtils.hasJPTypeEllipsis("Integer"));
    }

    @Test
    public void hasJPTypeExtendsTest() {

    }

    @Test
    public void removeSpuriousCharactersTest() {
        assertEquals("List", JDoctorUtils.removeSpuriousCharacters("List<? extends Integer>"));
        assertEquals("ArrayList", JDoctorUtils.removeSpuriousCharacters("ArrayList<? super Number>"));
        assertEquals("Oversuperstition", JDoctorUtils.removeSpuriousCharacters("Oversuperstition"));
    }
}
