package star.tratto.data.records;


import com.github.javaparser.ast.body.CallableDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import star.tratto.data.OracleType;

/**
 * This record stores basic information of a JavaDoc method tag. Includes the
 * file where the tag is written (as a string), the type declaration of the
 * declaring class, the corresponding method, the type of tag (e.g. PRE, POST,
 * EXCEPT_POST), the "name" of the tag, and the tag content. For example, in
 * the tag,
 *  "@param fileContent String representation of the source file"
 * "fileContent" is the tag name, and "String representation of the source
 * file" is the tag content.
 */
public record JavadocTag(
        /* String representation of the source file */
        String fileContent,
        /* the declaring class */
        TypeDeclaration<?> jpClass,
        /* the corresponding method */
        CallableDeclaration<?> jpCallable,
        /* the type of JavaDoc tag (e.g. PRE, POST, EXCEPT_POST) */
        OracleType oracleType,
        /* parameter name or exception type ("" if oracleType is POST) */
        String tagName,
        /* the content of the tag */
        String tagBody
) {}
