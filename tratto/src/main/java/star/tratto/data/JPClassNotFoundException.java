package star.tratto.data;

import com.github.javaparser.ast.body.TypeDeclaration;
import star.tratto.util.javaparser.JavaParserUtils;

import java.io.IOException;

/**
 * An exception that indicates if a class was either undefined or unresolvable
 * (two possible errors that may occur when attempting to get all available
 * methods/fields) using JavaParser.
 *
 * @see JavaParserUtils#getAllFields(TypeDeclaration)
 * @see JavaParserUtils#getAllMethods(TypeDeclaration)
 */
public class JPClassNotFoundException extends IOException {
    public JPClassNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
