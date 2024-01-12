package star.tratto.data.records;


import com.github.javaparser.ast.body.CallableDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import star.tratto.data.OracleType;

/**
 * All information regarding a Javadoc method tag (e.g. "@throws", "@param",
 * etc.). In the tag,
 *     "@param box a default container",
 * "box" is the tag name and "a default container" is the tag body.
 */
public record TagAndText(
        /* String representation of the whole source file (declaring class, imports, etc.). */
        String fileContent,
        /* The declaring class. */
        TypeDeclaration<?> jpClass,
        /* The corresponding method. */
        CallableDeclaration<?> jpCallable,
        /* The type of oracle tag (e.g. @param->PRE, @throws->EXCEPT_POST, etc.). */
        OracleType oracleType,
        /* Parameter name or exception type (empty string if oracleType is NORMAL_POST). */
        String tagName,
        /* The tag description. */
        String tagBody
) {
    /**
     * Gets the Javadoc tag corresponding to a given oracle type. For
     * reference,
     * <ul>
     *     <li>PRE &rarr; @param</li>
     *     <li>NORMAL_POST &rarr; @return</li>
     *     <li>EXCEPT_POST &rarr; @throws</li>
     * </ul>
     * This method is included in this record to avoid incompatibility issues
     * with the Jackson JSON reader.
     *
     * @param oracleType a type of oracle
     * @return an equivalent Javadoc tag corresponding to the oracle type
     */
    private static String oracleTypeToJavadocTag(
            OracleType oracleType
    ) {
        switch (oracleType) {
            case PRE -> {
                return "@param ";
            }
            case NORMAL_POST -> {
                return "@return ";
            }
            case EXCEPT_POST -> {
                return "@throws ";
            }
            default -> throw new IllegalArgumentException("Unknown oracle type " + oracleType);
        }
    }

    /**
     * Gets a String representation of a Javadoc tag and description
     * equivalent to as it would appear in source code. However, this method
     * is not lossless. For example, if a tag is originally "@exception", this
     * method will return "@throws". The type of tag is preserved (i.e. PRE,
     * EXCEPT_POST, NORMAL_POST).
     *
     * @return a String representation of the Javadoc tag and its text
     */
    public String getTagAsString() {
        StringBuilder sb = new StringBuilder();
        sb.append(oracleTypeToJavadocTag(this.oracleType()));
        if (!this.tagName().equals("")) {
            sb.append(this.tagName()).append(" ");
        }
        sb.append(this.tagBody());
        return sb.toString();
    }
}
