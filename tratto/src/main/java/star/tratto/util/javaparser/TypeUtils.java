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
}
