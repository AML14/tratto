package star.tratto.utils.javaparser;

import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.comments.JavadocComment;
import com.github.javaparser.ast.stmt.BlockStmt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import star.tratto.data.DataAgumentationPath;
import star.tratto.utils.FileUtils;

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * This class provides static methods for generating features of the oracles
 * dataset, and the conversion of JavaParser objects into interpretable
 * outputs.
 */
public class DatasetUtils {
    private static final Logger logger = LoggerFactory.getLogger(DatasetUtils.class);

    /** Private constructor to avoid creating an instance of this class. */
    private DatasetUtils() {
        throw new UnsupportedOperationException("This class cannot be instantiated.");
    }

    /**
     * Gets the Javadoc comment of a body declaration using regex patterns.
     * Use ONLY IF Javadoc comment is not recoverable using JavaParser API,
     * such as {@link DatasetUtils#getCallableJavadoc(CallableDeclaration)}.
     *
     * @param jpBody a member in a Java class
     * @return the matched Javadoc comment (empty string if not found)
     */
    private static String getJavadocByPattern(BodyDeclaration<?> jpBody) {
        String input = jpBody.toString();
        Pattern pattern = Pattern.compile("/\\*\\*(.*?)\\*/", Pattern.DOTALL);
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            String content = matcher.group(1);
            if (jpBody instanceof TypeDeclaration<?>) {
                // class javadoc format
                return "/**" + content + "*/";
            } else {
                // method javadoc format
                return "    /**" + content + "*/";
            }
        }
        return "";
    }

    /**
     * Gets the Javadoc comment of a given function.
     *
     * @param jpCallable a JavaParser function
     * @return the method/constructor Javadoc comment
     */
    public static String getCallableJavadoc(
            CallableDeclaration<?> jpCallable
    ) {
        Optional<JavadocComment> optionalJavadocComment = jpCallable.getJavadocComment();
        return optionalJavadocComment
                .map(javadocComment -> "    /**" + javadocComment.getContent().replaceAll("@exception ", "@throws ") + "*/")
                .orElseGet(() -> getJavadocByPattern(jpCallable));
    }

    /**
     * Gets the source code of a given function.
     *
     * @param jpCallable a method or constructor
     * @return a string representation of the source code
     */
    public static String getCallableSourceCode(
            CallableDeclaration<?> jpCallable
    ) {
        String jpSignature = JavaParserUtils.getCallableSignature(jpCallable);
        Optional<BlockStmt> jpBody = jpCallable instanceof MethodDeclaration
                ? ((MethodDeclaration) jpCallable).getBody()
                : Optional.ofNullable(((ConstructorDeclaration) jpCallable).getBody());
        return jpSignature + (jpBody.isEmpty() ? ";" : jpBody.get().toString());
    }

    /**
     * Finds all ".java" files in a given directory. Files are filtered by an
     * ad-hoc list of files to ignore (see dataset/repos/ignore_file.json).
     *
     * @param sourceDir the path to the project root directory
     * @return a list of all valid files
     */
    public static List<Path> getValidJavaFiles(Path sourceDir) {
        List<Path> allJavaFiles = FileUtils.getAllJavaFilesUnderDirectory(sourceDir);
        // Get list of files to ignore.
        Path ignoreFilePath = DataAgumentationPath.IGNORE_FILE.getPath();
        List<String> ignoreFileList = FileUtils.readJSONList(ignoreFilePath, String.class);
        // filter files.
        return allJavaFiles
                .stream()
                .filter(f -> !ignoreFileList.contains(f.getFileName().toString()))
                .collect(Collectors.toList());
    }

    /**
     * Finds a selection of ".java" files in a given directory.
     *
     * @param sourceDir the path to the project root directory
     * @param fullyQualifiedNameClassList the list of selected classes within the project
     * @return a list of all valid files
     */
    public static List<Path> getSelectionOfValidJavaFiles(Path sourceDir, List<String> fullyQualifiedNameClassList) {
        List<Path> allValidJavaFiles = getValidJavaFiles(sourceDir);
        if (fullyQualifiedNameClassList.size() == 0) {
            return allValidJavaFiles;
        }
        List<String> fullyQualifiedNameClassFileList = fullyQualifiedNameClassList
                .stream()
                .map(fqnClassName -> getClassPath(sourceDir, fqnClassName).toString())
                .collect(Collectors.toList());
        // filter files.
        return allValidJavaFiles
                .stream()
                .filter(f -> fullyQualifiedNameClassFileList.contains(f.toString()))
                .collect(Collectors.toList());
    }

    /**
     * Gets the path to the class of a JDoctor condition from the given source
     * path.
     *
     * @param sourceDir the source path of the relevant project
     * @param fullyQualifiedClassName the fully qualified name of a class within the project
     * @return the path of the class in the JDoctor condition
     */
    private static Path getClassPath(
            Path sourceDir,
            String fullyQualifiedClassName
    ) {
        return sourceDir.resolve(fullyQualifiedClassName.replace(".", "/") + ".java");
    }

}
