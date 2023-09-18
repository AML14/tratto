package star.tratto.data.records;


import com.github.javaparser.ast.body.CallableDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import star.tratto.data.OracleType;

/**
 * A Javadoc method tag (e.g. "@throws", "@param", etc.). For reference, in
 * the tag,
 *     "@param box a default container"
 * "box" is the tag name and "a default container" is the tag body.
 */
public record JavadocTag(
        /* String representation of the whole source file (declaring class, imports, etc.). */
        String fileContent,
        /* The declaring class. */
        TypeDeclaration<?> jpClass,
        /* The corresponding method. */
        CallableDeclaration<?> jpCallable,
        /* The type of oracle corresponding to the JavaDoc tag (e.g. @param->PRE, @throws->EXCEPT_POST, etc.). */
        OracleType oracleType,
        /* Parameter name or exception type (empty string if oracleType is NORMAL_POST). */
        String tagName,
        /* The content of the tag. */
        String tagBody
) {}
