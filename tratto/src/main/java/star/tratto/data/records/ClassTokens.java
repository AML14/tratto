package star.tratto.data.records;

/**
 * This record stores basic information of a Java class. Includes the class
 * name (className) and package name (packageName).
 *
 * @param className the name of a Java class
 * @param packageName the corresponding package of the Java class
 */
public record ClassTokens(String className, String packageName) {
}
