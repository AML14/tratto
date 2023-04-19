package star.tratto.util.javaparser;

import com.github.javaparser.ast.body.CallableDeclaration;
import star.tratto.identifiers.JavadocValueType;

import com.github.javaparser.JavaParser;
import org.javatuples.Pair;
import org.javatuples.Triplet;
import org.javatuples.Quartet;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DatasetUtils {
    private JavaParser javaParser;

    /**
     * A helper method that removes all the duplicates from a list.
     * @param list The list from which remove the duplicates.
     * @return A new list that does not contain the duplicates of the list passed to the function.
     * @param <T> The generic type of the list.
     */
    private <T> List<T> removeDuplicates(List<T> list) {
        Set<T> set = new LinkedHashSet<>(list);
        return new ArrayList<>(set);
    }

    /**
     * The method finds all the numeric values present within a Javadoc comment, and it returns them as a list of pairs
     * of strings {@link Pair<String,String>}. The first element of the pair is the numeric value, while the second
     * element of the string identifies the type of the numeric value {@link JavadocValueType} (integer or real).
     *
     * @param javadocComment The javadoc comment, in the form of a string {@link String}
     * @return A list of pairs of strings {@link Pair<String,String>}, where the first element of each pair is the
     * numeric value, while the second element is the type of the numeric value {@link JavadocValueType} (integer or
     * real).
     */
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

    /**
     * The method finds all the string values present within a Javadoc comment, and it returns them as a list of pairs
     * of strings {@link Pair<String,String>}. The first element of the pair is the string value, while the second
     * element of the pair identifies the type of the pair {@link JavadocValueType} (always a string).
     *
     * @param javadocComment The javadoc comment, in the form of a string {@link String}
     * @return A list of pairs of strings {@link Pair<String,String>}, where the first element of each pair is the
     * string value found within the Javadoc comment, while the second element is the type of the value
     * {@link JavadocValueType} (always "string").
     */
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

    /**
     * The method finds all the numeric and string values defined within a string representation of a Javadoc comment,
     * and it returns them as a list of pairs of strings {@link Pair<String,String>}.
     * For each pair:
     * <ul>
     *     <li>If the pair refers to a numeric value, the first element of each pair is the string representation of the
     *     numeric value, while the second element is the type of the numeric value {@link JavadocValueType}
     *     (integer or real).</li>
     *     <li>If the pair refers to a string value, the first element of each pair is the string value found within
     *     the Javadoc comment, while the second element is the type of the value {@link JavadocValueType} (always
     *     "string").</li>
     * </ul>
     * The method returns the list of pairs collected. The list is empty if it does not contain numeric or string values.
     * @param jpCallableJavadoc A string {@link String} representing the Javadoc comment of a JavaParser callable
     *                          declaration {@link CallableDeclaration} (i.e. a method or a constructor).
     * @return A list of pairs of strings {@link Pair<String,String>}, representing the numeric and string values present
     * in the Javadoc comment of the JavaParser callable declaration {@link CallableDeclaration} (i.e. a method or
     * constructor) to which the string representation passed to the function {@code jpCallableJavadoc} refers. For each
     * pair in the list, the first element is the string representation of the value (numeric or string), while
     * the second element represents the type of the value collected ({@link JavadocValueType} - integer, real, or string).
     */
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

    public List<Quartet<String, String, String, String>> getTokensMethodVariablesNonPrivateNonStaticNonVoidMethods(

    ) {
        return null;
    }

    public List<Quartet<String, String, String, String>> getTokensMethodVariablesNonPrivateNonStaticAttributes(

    ) {
        return null;
    }

    public List<Quartet<String, String, String, String>> getTokensOracleVariablesNonPrivateNonStaticNonVoidMethods(

    ) {
        return null;
    }

    public List<Quartet<String, String, String, String>> getTokensOracleVariablesNonPrivateNonStaticAttributes(

    ) {
        return null;
    }
}
