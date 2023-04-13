package star.tratto.dataset;

import org.javatuples.Tuple;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static star.tratto.dataset.ExcelManager.*;

public class ExcelManagerTest {

    @Test
    public void parseMultiDataCellNullOrEmptyTest() {
        String cell1 = null;
        String cell2 = "";
        assertEquals(0, parseMultiDataCell(cell1).size());
        assertEquals(0, parseMultiDataCell(cell2).size());
    }

    @Test
    public void parseMultiDataCellValidTest() {
        String cell = " [ collectionBag   ;org.apache.commons.collections4.bag; CollectionBag; public static <E> Bag<E> collectionBag(final Bag<E> bag) ]   , [indexOf; org.apache.commons.collections4; ArrayUtils; static int indexOf(final Object\\[\\] array, final Object objectToFind, int startIndex) ] ";

        List<? extends Tuple> cellTuples = parseMultiDataCell(cell);

        assertEquals(2, cellTuples.size());
        assertEquals(4, cellTuples.get(0).getSize());
        assertEquals(4, cellTuples.get(1).getSize());
        assertEquals("collectionBag", cellTuples.get(0).getValue(0));
        assertEquals("org.apache.commons.collections4.bag", cellTuples.get(0).getValue(1));
        assertEquals("CollectionBag", cellTuples.get(0).getValue(2));
        assertEquals("public static <E> Bag<E> collectionBag(final Bag<E> bag)", cellTuples.get(0).getValue(3));
        assertEquals("indexOf", cellTuples.get(1).getValue(0));
        assertEquals("org.apache.commons.collections4", cellTuples.get(1).getValue(1));
        assertEquals("ArrayUtils", cellTuples.get(1).getValue(2));
        assertEquals("static int indexOf(final Object[] array, final Object objectToFind, int startIndex)", cellTuples.get(1).getValue(3));
    }

    @Test
    public void parseMultiDataCellValidSemicolonTest() {
        String cell = "[;], [?]";

        List<? extends Tuple> cellTuples = parseMultiDataCell(cell);

        assertEquals(2, cellTuples.size());
        assertEquals(1, cellTuples.get(0).getSize());
        assertEquals(1, cellTuples.get(1).getSize());
        assertEquals(";", cellTuples.get(0).getValue(0));
        assertEquals("?", cellTuples.get(1).getValue(0));
    }

    @Test
    public void parseMultiDataCellValidStringTest() {
        String cell = "[\"stringValue\"; String], [5; int]";

        List<? extends Tuple> cellTuples = parseMultiDataCell(cell);

        assertEquals(2, cellTuples.size());
        assertEquals(2, cellTuples.get(0).getSize());
        assertEquals(2, cellTuples.get(1).getSize());
        assertEquals("\"stringValue\"", cellTuples.get(0).getValue(0));
        assertEquals("String", cellTuples.get(0).getValue(1));
        assertEquals("5", cellTuples.get(1).getValue(0));
        assertEquals("int", cellTuples.get(1).getValue(1));
    }

    @Test
    public void parseMultiDataCellInvalidNoStartingBracketTest() {
        String cell = "CollectionBag; org.apache.commons.collections4.bag], [ArrayUtils; org.apache.commons.collections4]";

        try {
            parseMultiDataCell(cell);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertEquals(EXCEPTION_STARTING_ENDING_BRACKETS, e.getMessage());
        }
    }

    @Test
    public void parseMultiDataCellInvalidBracketNotEscaped1Test() {
        String cell = "[CollectionBag[]; org.apache.commons.collections4.bag], [ArrayUtils; org.apache.commons.collections4]";

        try {
            parseMultiDataCell(cell);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertEquals(EXCEPTION_BRACKET_NOT_ESCAPED, e.getMessage());
        }
    }

    @Test
    public void parseMultiDataCellInvalidBracketNotEscaped2Test() {
        String cell = "[CollectionBag\\[],[\\]; org.apache.commons.collections4.bag], [ArrayUtils; org.apache.commons.collections4]";

        try {
            parseMultiDataCell(cell);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertEquals(EXCEPTION_BRACKET_NOT_ESCAPED, e.getMessage());
        }
    }

    @Test
    public void parseMultiDataCellInvalidNumberSubElementsTest() {
        String cell = "[CollectionBag; org.apache.commons.collections4.bag], [ArrayUtils; org.apache.commons.collections4], [a;b;c]";

        try {
            parseMultiDataCell(cell);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertEquals(EXCEPTION_NUMBER_SUBELEMENTS, e.getMessage());
        }
    }

    @Test
    public void parseMultiDataCellInvalidTooManySubElementsTest() {
        String cell = "[CollectionBag; org.apache.commons.collections4.bag; a; b; c], [ArrayUtils; org.apache.commons.collections4; d; e; f]";

        try {
            parseMultiDataCell(cell);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertEquals(EXCEPTION_TOO_MANY_SUBELEMENTS, e.getMessage());
        }
    }

    @Test
    public void subElementListToCellNormalTest() {
        List<String> subElementList = List.of("package.name", "ClassName");
        assertEquals("[package.name; ClassName]", subElementListToCell(subElementList));
    }

    @Test
    public void subElementListToCellNullTest() {
        List<String> subElementList1 = null;
        assertEquals("", subElementListToCell(subElementList1));
    }

    @Test
    public void subElementListToCellEmptyTest() {
        List<String> subElementList1 = List.of();
        assertEquals("", subElementListToCell(subElementList1));
    }

    @Test
    public void subElementListToCellSquareBracketsTest() {
        List<String> subElementList = List.of("package.name", "ClassName[]");
        assertEquals("[package.name; ClassName\\[\\]]", subElementListToCell(subElementList));
    }
}
