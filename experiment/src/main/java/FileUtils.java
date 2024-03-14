import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.FileAttribute;
import java.util.List;
import java.util.stream.Stream;


/**
 * This class provides static methods for file input and output, such as:
 * creating, copying, moving, writing, reading, etc.
 */
public class FileUtils {
    /** Private constructor to avoid creating an instance of this class. */
    private FileUtils() {
        throw new UnsupportedOperationException("This class cannot be instantiated.");
    }

    /**
     * Converts a fully qualified class name into a relative file Path. For
     * example,
     *     {@code com.example.MyClass}    -&gt;
     *     {@code com/example/MyClass.java}
     *
     * @param fullyQualifiedName a fully qualified class name
     * @return the path corresponding to the fully qualified class name
     */
    public static Path getFqnPath(String fullyQualifiedName) {
        return Paths.get(fullyQualifiedName.replaceAll("[.]", File.separator) + ".java");
    }

    /**
     * Checks if a given substring occurs more than once in a given string
     * without overlaps.
     *
     * @param originalString the original string
     * @param substring the substring to find
     * @return true if and only if the substring occurs more than once
     */
    private static boolean isSubstringRepeated(String originalString, String substring) {
        int firstIdx = originalString.indexOf(substring);
        if (firstIdx != -1) {
            int secondIdx = originalString.indexOf(originalString, firstIdx + 1);
            return secondIdx != -1;
        } else {
            return false;
        }
    }

    /**
     * Changes a specified sub-path of a path to another sequence of
     * directories. The original directory does not have to be the immediate
     * parent and may occur at any point in the path. This method will throw
     * an error if the original directory name occurs more than once in the
     * path. This method does NOT modify the original path.
     *
     * @param path a file or directory path
     * @param originalParent the original parent directory
     * @param newParent the new parent directory
     * @return the new path with swapped parent directories
     * @throws Error if the original directory name does not occur or occurs
     * more than once in the given path
     */
    public static Path swapParentDirectory(Path path, String originalParent, String newParent) {
        int idx = path.toString().indexOf(originalParent);
        if (idx != -1) {
            if (isSubstringRepeated(path.toString(), originalParent)) {
                throw new Error("Unable to modify path " + path + " with multiple occurrences of parent directory " + originalParent);
            }
            String newPath = path.toString().substring(0, idx) +
                    newParent +
                    path.toString().substring(idx + originalParent.length());
            return Paths.get(newPath);
        } else {
            throw new Error("Unable to find original parent directory " + originalParent + " in file path " + path);
        }
    }

    /**
     * Gets the ClassGetSimpleName from a fully qualified class name. For
     * example:
     *     {@code com.example.MyClass}    -&gt;    {@code MyClass}
     *
     * @param fullyQualifiedName a fully qualified class name
     * @return the corresponding simple name without packages
     */
    public static String getSimpleNameFromFqn(String fullyQualifiedName) {
        int classNameIdx = fullyQualifiedName.lastIndexOf(".");
        if (classNameIdx != -1) {
            return fullyQualifiedName.substring(classNameIdx + 1);
        }
        return fullyQualifiedName;
    }

    /**
     * Creates an empty directory. Creates parent directories if necessary. If
     * the directory already exists, then this method does nothing. <br> This
     * method is a wrapper method of {@link Files#createDirectories(Path, FileAttribute[])}
     * to substitute {@link IOException} with {@link Error} and avoid
     * superfluous try/catch blocks.
     *
     * @param path a path
     * @throws Error if an error occurs while creating the directory
     */
    public static void createDirectories(Path path) {
        try {
            Files.createDirectories(path);
        } catch (IOException e) {
            throw new Error("Error when creating directory " + path, e);
        }
    }

    /**
     * Creates an empty file. Creates parent directories if necessary. If the
     * file already exists, then this method does nothing.
     *
     * @param path a file
     * @throws Error if an error occurs while creating the parent directories
     * or new file
     */
    public static void createFile(Path path) {
        try {
            createDirectories(path.getParent());
            if (!Files.exists(path)) {
                Files.createFile(path);
            }
        } catch (IOException e) {
            throw new Error("Error when creating file " + path, e);
        }
    }

    /**
     * Recursively deletes a directory and its contents. If the file does not
     * exist, then this method does nothing.
     *
     * @param dirPath a directory
     * @throws Error if an error occurs while traversing or deleting files
     */
    public static void deleteDirectory(Path dirPath) {
        if (!Files.exists(dirPath)) {
            return;
        }
        try (Stream<Path> walk = Files.walk(dirPath)) {
            walk
                    .filter(p -> !p.equals(dirPath))
                    .forEach(p -> {
                        if (Files.isDirectory(p)) {
                            deleteDirectory(p);
                        } else {
                            try {
                                Files.delete(p);
                            } catch (IOException e) {
                                throw new Error("Error when trying to delete the file " + p, e);
                            }
                        }
                    });
            // delete root directory last
            Files.delete(dirPath);
        } catch (IOException e) {
            throw new Error("Error when trying to delete the directory " + dirPath, e);
        }
    }

    /**
     * Returns the path of the target file or directory when (hypothetically)
     * moved from the source directory to the destination directory.
     * NOTE: This method does NOT perform the move. This method is different
     * from {@link Path#relativize(Path)}, which gives a path to the
     * destination, relative to the source.
     *
     * @param sourceDir the source directory
     * @param destination the destination directory
     * @param target the absolute target file path
     * @return the path of the target file if hypothetically moved from the
     * source directory to the destination directory. For example, let
     * <pre>
     *     sourceDir = [sourcePath]
     *     destination = [destinationPath]
     *     target = [sourcePath]/[internalDirectories]/[fileName]
     * </pre>
     * then the method outputs,
     * <pre>
     *     relativePath = [destinationPath]/[internalDirectories]/[fileName]
     * </pre>
     */
    public static Path getRelativePath(Path sourceDir, Path destination, Path target) {
        if (sourceDir.equals(target)) {
            return destination;
        }
        // remove source prefix
        if (!target.startsWith(sourceDir)) {
            throw new IllegalArgumentException(target + " must exist under " + sourceDir);
        }
        Path suffix = target.subpath(sourceDir.getNameCount(), target.getNameCount());
        // add remaining suffix to destination
        return destination.resolve(suffix);
    }

    /**
     * Copies a source file to a target file. If the target file already
     * exists, then it is overwritten. If target does not exist, then it will
     * be created, along with necessary parent directories. <br> This method
     * is a wrapper method of {@link Files#copy(Path, Path, CopyOption...)}
     * to substitute {@link IOException} with {@link Error} and avoid
     * superfluous try/catch blocks.
     *
     * @param source the source file
     * @param target the target file
     */
    public static void copyFile(Path source, Path target) {
        if (!Files.exists(target)) {
            createFile(target);
        }
        try {
            Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new Error("Error when trying to copy the file " + source + " to " + target, e);
        }
    }

    /**
     * Recursively copies all files and directories from the source directory
     * to the destination directory. If a file in the source directory already
     * exists in the destination directory, then the original file will be
     * overwritten.
     *
     * @param sourceDir the directory where the files are located
     * @param destinationDir the directory where the files will be copied to
     * @throws Error if the source directory does not exist or an error occurs
     * while copying a file
     * @see FileUtils#move(Path, Path)
     */
    public static void copy(Path sourceDir, Path destinationDir) {
        if (!Files.exists(sourceDir)) {
            throw new Error("Directory " + sourceDir + " is not found");
        }
        if (!Files.exists(destinationDir)) {
            createDirectories(destinationDir);
        }
        // walk is used to iterate over files in subdirectories
        try (Stream<Path> walk = Files.walk(sourceDir)) {
            walk
                    .forEach(p -> {
                        Path relativePath = getRelativePath(sourceDir, destinationDir, p);
                        if (Files.isDirectory(p)) {
                            createDirectories(relativePath);
                        } else {
                            copyFile(p, relativePath);
                        }
                    });
        } catch (IOException e) {
            throw new Error("Error when trying to copy " + sourceDir + " to " + destinationDir, e);
        }
    }

    /**
     * Recursively moves all files from the source directory to the
     * destination directory. If a file in the source directory already exists
     * in the destination directory, then the original file will be
     * overwritten.
     *
     * @param sourceDir the directory where the files are located
     * @param destinationDir the directory where the files will be moved to
     * @throws Error if the source directory does not exist or an error occurs
     * while moving a file
     * @see FileUtils#copy(Path, Path)
     */
    public static void move(Path sourceDir, Path destinationDir) {
        if (!Files.exists(sourceDir)) {
            throw new Error("Directory " + sourceDir + " is not found");
        }
        if (!Files.exists(destinationDir)) {
            createDirectories(destinationDir);
        }
        copy(sourceDir, destinationDir);
        deleteDirectory(sourceDir);
    }

    /**
     * Writes {@code content} to {@code path} in JSON format. Creates a new
     * file and parent directories if necessary. If file already exists,
     * then this method overrides any previous content.
     *
     * @param path a file
     * @param content an object to be written in JSON content
     * @throws Error if unable to create files/directories or unable to write
     *               content to file
     */
    public static void writeJSON(Path path, Object content) {
        createFile(path);
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(path.toFile(), content);
        } catch (IOException e) {
            throw new Error("Error when writing " + content + " to file " + path, e);
        }
    }

    /**
     * This method is a wrapper for {@link Files#writeString(Path, CharSequence, OpenOption...)}
     * to avoid unnecessary try/catch blocks. Creates a new file and parent
     * directories if needed. If file already exists, then this method
     * overrides any previous content.
     *
     * @param path    a file
     * @param content a string to be written to a file
     * @throws Error if unable to create files/directories or unable to write
     *               content to file
     */
    public static void writeString(Path path, String content) {
        createFile(path);
        try {
            Files.writeString(path, content);
        } catch (IOException e) {
            throw new Error("Error when writing " + content + " to file " + path, e);
        }
    }

    /**
     * Reads the JSON contents of a file as a standard Java Object.
     *
     * @param path a file
     * @param typeReference the file JSON contents as an Object
     * @return if unable to process the file
     */
    public static Object readJSON(Path path, TypeReference<?> typeReference) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            File jsonFile = new File(path.toString());
            return objectMapper.readValue(jsonFile, typeReference);
        } catch (IOException e) {
            throw new Error("Error in processing file " + path, e);
        }
    }

    /**
     * Returns the file contents as a String.
     *
     * @param path a file
     * @return the file contents as a String
     * @throws Error if unable to process the file
     */
    public static String readString(Path path) {
        try {
            return Files.readString(path);
        } catch (IOException e) {
            throw new Error("Error in processing file " + path, e);
        }
    }

    /**
     * Returns the contents of a file as a {@link CSVParser}.
     *
     * @param path a file
     * @return the CSV parser of the file
     * @throws Error if unable to process the file
     */
    public static CSVParser readCSV(Path path) {
        try {
            return new CSVParser(new FileReader(path.toString()), CSVFormat.DEFAULT);
        } catch (IOException e) {
            throw new Error("Error in processing file " + path, e);
        }
    }

    /**
     * Reads a list of objects from a JSON file. If the given type is null,
     * then returns a list of unknown type (wildcard).
     *
     * @param jsonPath a JSON file
     * @param type     the class type of the elements to which the JSON data will
     *                 be deserialized
     * @param <T>      the generic type parameter representing the class of the
     *                 elements in the list
     * @return a list of objects of the specified class type. If type is null,
     * then returns a list of a wildcard type.
     */
    public static <T> List<T> readJSONList(Path jsonPath, Class<T> type) {
        if (!Files.exists(jsonPath)) {
            throw new Error("JSON file " + jsonPath + " not found");
        }
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(
                    jsonPath.toFile(),
                    objectMapper.getTypeFactory().constructCollectionType(List.class, type)
            );
        } catch (IOException e) {
            throw new Error("Error in processing the JSON file " + jsonPath, e);
        }
    }

    /**
     * Searches for a class file in a given directory and its
     * subdirectories. Returns the path to the class from the root directory
     * if found, otherwise, returns null.
     *
     * @param dir the root directory
     * @param fqnPath the fully qualified name of the class as a Path
     * @return the path to the class in the given root directory. Returns null
     * if no such class is found.
     * @see FileUtils#getFqnPath(String)
     */
    public static Path findClassPath(Path dir, Path fqnPath) {
        try (Stream<Path> walk = Files.walk(dir)) {
            return walk
                    .filter(p -> p.endsWith(fqnPath))
                    .findFirst()
                    .orElse(null);
        } catch (IOException e) {
            throw new Error("Unable to parse files in directory " + dir);
        }
    }

    /**
     * Checks if a given path corresponds to a Java file
     *
     * @param path a file
     * @return true iff the given file represents a Java file
     */
    public static boolean isJavaFile(Path path) {
        return path.getFileName().toString().endsWith(".java");
    }

    /**
     * Checks if a given path corresponds to a scaffolding file generated by
     * EvoSuite. All scaffolding files end with "scaffolding.java".
     *
     * @param path a file
     * @return true iff the given file represents an EvoSuite scaffolding file
     */
    public static boolean isScaffolding(Path path) {
        return path.getFileName().toString().endsWith("scaffolding.java");
    }

    /**
     * Returns the JavaParser {@link CompilationUnit} corresponding to a given
     * Java file. Uses StaticJavaParser to avoid configuring a symbol solver.
     * <br> This method is a wrapper method of
     * {@link StaticJavaParser#parse(Path)} to substitute {@link IOException}
     * with {@link Error} and avoid superfluous try/catch blocks.
     *
     * @param path a Java file
     * @return the CompilationUnit corresponding to the given file
     */
    public static CompilationUnit getCompilationUnit(Path path) {
        try {
            return StaticJavaParser.parse(path);
        } catch (IOException e) {
            throw new Error("Unable to parse the file " + path + " using JavaParser");
        }
    }
}
