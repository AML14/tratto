package star.tratto.util.javaparser;

import com.github.javaparser.ast.body.CallableDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.type.TypeParameter;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * This class provides a collection of static methods to convert between the
 * field descriptor and source code formats of Java types. For our purposes,
 * we note that JDoctor uses field descriptors to represent types, whereas
 * JavaParser uses the source code format to represent types.
 */
public class TypeUtils {
    // private constructor to avoid creating an instance of this class.
    private TypeUtils() {
        throw new UnsupportedOperationException("This class cannot be instantiated.");
    }

    /**
     * Removes any type arguments from a parameterized type name.
     *
     * @param parameterizedType a field descriptor or source code
     *                          representation of a type
     * @return the raw type without type arguments
     */
    public static String removeTypeArguments(String parameterizedType) {
        // regex to match type arguments (e.g. <T>).
        String regex = "<[^<>]*>";
        // repeatedly remove all type arguments.
        String previous;
        String current = parameterizedType;
        do {
            previous = current;
            current = previous.replaceAll(regex, "");
        } while (!current.equals(previous));
        return current;
    }

    /**
     * Splits a binary name (of a class or member) into segments. Splits name
     * based on "." (package) and "$" (member).
     *
     * @param name a binary method/class name
     * @return name segments. Includes: all outer packages, all outer classes,
     * the innermost class, and the member name (if {@code name} is a member).
     */
    public static List<String> getNameSegments(
            String name
    ) {
        String regex = "[.$]";
        return Arrays.asList(name.split(regex));
    }

    /**
     * @param nameSegments name segments. Must represent a class.
     * @return name segments joined by ".", representing the package name.
     * NOTE: For inner classes, we represent the package name as:
     *  [outerClass package].[outerClass(es)]
     * for compatibility with the XText grammar.
     * @see TypeUtilsTemp#getNameSegments(String)
     */
    public static String getPackageNameFromNameSegments(
            List<String> nameSegments
    ) {
        return String.join(".", nameSegments.subList(0, nameSegments.size() - 1));
    }

    /**
     * @param nameSegments name segments. Must represent a class.
     * @return innermost class of the name segments
     * @see TypeUtilsTemp#getNameSegments(String)
     */
    public static String getClassNameFromNameSegments(
            List<String> nameSegments
    ) {
        return nameSegments.get(nameSegments.size() - 1);
    }
}
