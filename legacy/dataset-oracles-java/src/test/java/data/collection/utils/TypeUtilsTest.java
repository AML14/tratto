package data.collection.utils;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TypeUtilsTest {
    @Test
    public void testRemoveSpuriousCharacters() {
        assertEquals("int", TypeUtils.removeSpuriousCharacters("int"));
        assertEquals("int", TypeUtils.removeSpuriousCharacters("int  "));
        assertEquals("int", TypeUtils.removeSpuriousCharacters("int super"));
        assertEquals("int", TypeUtils.removeSpuriousCharacters("int;"));
        assertEquals("int", TypeUtils.removeSpuriousCharacters("int??"));
        assertEquals("ints", TypeUtils.removeSpuriousCharacters("int super s??;"));
    }

    @Test
    public void testConvertToArrayType() {
        assertEquals("double", TypeUtils.convertToArrayType("double", 0));
        assertEquals("double[]", TypeUtils.convertToArrayType("double", 1));
        assertEquals("double[][]", TypeUtils.convertToArrayType("double", 2));
    }

    @Test
    public void testConvertConditionToArrayType() {
        assertEquals("boolean", TypeUtils.convertConditionToArrayType("boolean"));
        assertEquals("boolean[]", TypeUtils.convertConditionToArrayType("[boolean"));
        assertEquals("boolean[][]", TypeUtils.convertConditionToArrayType("[[boolean"));
    }
}
