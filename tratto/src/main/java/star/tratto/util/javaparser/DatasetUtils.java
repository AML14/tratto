package star.tratto.util.javaparser;

import com.github.javaparser.ast.CompilationUnit;
import star.tratto.dataset.oracles.JDoctorCondition.*;
import star.tratto.exceptions.PrimaryTypeNotFoundException;
import star.tratto.identifiers.file.*;
import star.tratto.identifiers.JavadocValueType;
import static star.tratto.util.javaparser.JavaParserUtils.*;

import org.javatuples.Pair;

import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DatasetUtils {

    private <T> List<T> removeDuplicates(List<T> list) {
        Set<T> set = new LinkedHashSet<>(list);
        return new ArrayList<>(set);
    }

    public static List<Pair<String, String>> findAllNumericValuesInJavadocComment(
        String javadocComment
    ) {
        // Define regex pattern that matches integer and real values within a string
        Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?"); // matches integers and real numbers
        // Instantiate matcher
        Matcher matcher = pattern.matcher(javadocComment);
        // Define collection of pairs of numeric values [numericType, numericValue]
        List<Pair<String, String>> numericValues = new ArrayList<>();
        // Collect the matches
        while (matcher.find()) {
            // Extract the value
            String match = matcher.group();
            // Check if the value is integer or real
            if (match.contains(".")) {
                // The number is real
                try {
                    float realValue = Float.parseFloat(match);
                    numericValues.add(new Pair<>(Float.toString(realValue), JavadocValueType.REAL.getValue()));
                } catch (Exception e) {
                    String errMsg = String.format("Number exceed maximum real value: %s", match);
                    System.err.println(errMsg);
                }
            } else {
                // The number is integer
                try {
                    long longIntValue = Long.parseLong(match);
                    numericValues.add(new Pair<>(Long.toString(longIntValue), JavadocValueType.INTEGER.getValue()));
                } catch (NumberFormatException e) {
                    String errMsg = String.format("Number exceed maximum integer value: %s", match);
                    System.err.println(errMsg);
                }
            }
        }
        // Return the list of the collected integer values
        return numericValues;
    }

    public static List<Pair<String, String>> findAllStringValuesInJavadocComment(
            String javadocComment
    ) {
        // Define regex pattern that matches a string value within a string
        Pattern pattern = Pattern.compile("\"([^\"]*)\"");
        // Instantiate matcher
        Matcher matcher = pattern.matcher(javadocComment);
        // Define collection of pairs of string values [stringValue, "String"]
        List<Pair<String, String>> stringValues = new ArrayList<>();
        // Collect the matches
        while (matcher.find()) {
            // Extract the value
            String match = matcher.group(1);
            stringValues.add(new Pair<>(match, "String"));
        }
        // Return the list of the collected string values
        return stringValues;
    }

    public static List<Pair<String, String>> getJavadocValuesFromCallableJavadocString(
            String jpCallableJavadoc
    ) {
        // Define the list of pair values of type [valueName, valueType]
        List<Pair<String, String>> pairList = new ArrayList<>();
        // Collect the numeric values present in the javadoc comment
        pairList.addAll(findAllNumericValuesInJavadocComment(jpCallableJavadoc));
        // Collect the string values present in the javadoc comment
        pairList.addAll(findAllStringValuesInJavadocComment(jpCallableJavadoc));
        return pairList;
    }

    public static String getSourceCode(
            Operation operation,
            String sourcePath
    ) {
        String source = "";
        // get class of compilation unit.
        Optional<CompilationUnit> cu = getClassCompilationUnit(operation, sourcePath);
        if (cu.isPresent()) {
            try {
                source = JavaParserUtils.getSourceFromCompilationUnit(cu.get());
            } catch (PrimaryTypeNotFoundException e) {
                // return empty source code if class is not found.
                e.printStackTrace();
                return source;
            }
        }
        return source;
    }

    public static Optional<CompilationUnit> getClassCompilationUnit(
            Operation operation,
            String sourcePath
    ) {
        List<String> pathList = Arrays.asList(operation.getClassName().split("\\."));
        String classPath = Paths.get(sourcePath, pathList.toArray(String[]::new)) + FileFormat.JAVA.getValue();
        return getCompilationUnitFromFilePath(classPath);
    }
}
