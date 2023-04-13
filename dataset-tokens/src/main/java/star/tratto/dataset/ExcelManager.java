package star.tratto.dataset;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.javatuples.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.apache.poi.ss.usermodel.Row.MissingCellPolicy.CREATE_NULL_AS_BLANK;

/**
 * Functionalities to read from and write to the datasets stored as Excel files
 */
public class ExcelManager {

    static final String EXCEPTION_STARTING_ENDING_BRACKETS = "The cell does not match the expected format. Values must be enclosed in square brackets";
    static final String EXCEPTION_BRACKET_NOT_ESCAPED = "The cell does not match the expected format. Some '[' or ']' character is not escaped";
    static final String EXCEPTION_NUMBER_SUBELEMENTS = "The cell does not match the expected format. All elements must have the same number of sub-elements";
    static final String EXCEPTION_TOO_MANY_SUBELEMENTS = "The cell does not match the expected format. The number of sub-elements must be between 1 and 4";

    /**
     * Returns the first sheet of the Excel file at the given path.
     */
    public static Sheet getFirstSheet(String path) {
        try {
            FileInputStream file = new FileInputStream(path);
            Workbook datasetWorkbook = new XSSFWorkbook(file);
            file.close();
            return datasetWorkbook.getSheetAt(0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * The format of multi-data cells in the dataset is as follows:
     * <code>[foo1; bar1], [foo2; bar2], ...</code>
     * This method parses the cell and returns a List of Tuples of Strings. The
     * type of Tuple varies according to the number of elements contained between
     * square brackets.
     */
    public static List<? extends Tuple> parseMultiDataCell(String cell) {
        if (cell == null || cell.isEmpty()) {
            return List.of();
        }

        if (!cell.matches("^ *\\[[\\s\\S]*\\] *$")) { // Check that the cell starts and ends with a square bracket
            throw new IllegalArgumentException(EXCEPTION_STARTING_ENDING_BRACKETS);
        }

        Integer elementSize = null; // Number of elements (enclosed in square brackets and separated by commas) in the cell
        List<Tuple> tuples = new ArrayList<>();

        String[] elements = cell.replaceAll("(^ *\\[ *)|( *\\] *$)", "").split(" *\\] *, *\\[ *", -1);
        for (String element : elements) {
            if (element.matches("[\\s\\S]*[^\\\\]([\\[\\]])[\\s\\S]*") || element.chars().filter(ch -> ch == '[').count() != element.chars().filter(ch -> ch == ']').count()) {
                throw new IllegalArgumentException(EXCEPTION_BRACKET_NOT_ESCAPED);
            }

            String[] subElements = element.split(" *; *", -1); // ';' is not allowed within subElements, so we can safely split by it

            // CAREFUL! There's a special case that we need to handle separately: when the element is actually the ";" token
            if (element.matches(" *; *")) {
                subElements = new String[]{";"};
                elementSize = 1;
            } else if (elementSize == null) {
                elementSize = subElements.length;
            } else if (elementSize != subElements.length) {
                throw new IllegalArgumentException(EXCEPTION_NUMBER_SUBELEMENTS);
            }

            // Only characters that need to be unescaped are '\[' and '\]':
            List<String> subElementsUnescaped = Arrays.stream(subElements).map(subElement -> subElement.replaceAll("\\\\([\\[\\]])", "$1")).collect(Collectors.toList());
            switch (elementSize) {
                case 1:
                    tuples.add(Unit.fromCollection(subElementsUnescaped));
                    break;
                case 2:
                    tuples.add(Pair.fromCollection(subElementsUnescaped));
                    break;
                case 3:
                    tuples.add(Triplet.fromCollection(subElementsUnescaped));
                    break;
                case 4:
                    tuples.add(Quartet.fromCollection(subElementsUnescaped));
                    break;
                default:
                    throw new IllegalArgumentException(EXCEPTION_TOO_MANY_SUBELEMENTS);
            }
        }

        return tuples;
    }

    public static List<? extends Tuple> parseMultiDataCell(Row row, int cellIndex) {
        return parseMultiDataCell(parseStringCell(row, cellIndex));
    }

    public static String parseStringCell(Row row, int cellIndex) {
        return row.getCell(cellIndex, CREATE_NULL_AS_BLANK).getStringCellValue();
    }

    public static double parseNumericCell(Row row, int cellIndex) {
        return row.getCell(cellIndex, CREATE_NULL_AS_BLANK).getNumericCellValue();
    }

    /**
     * A list of subElement should be reflected in a cell as: [subElement1; subElement2; ...]
     */
    public static String subElementListToCell(List<String> subElementList) {
        if (subElementList == null || subElementList.isEmpty()) {
            return "";
        }
        return "[" + subElementList.stream().map(se -> se.replaceAll("([\\[\\]])", "\\\\$1")).collect(Collectors.joining("; ")) + "]";
    }

}
