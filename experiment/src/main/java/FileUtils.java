import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.*;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;


/**
 * This class provides a collection of static methods for a variety of file
 * input and output utilities, such as: creating, copying, moving, writing,
 * reading, etc.
 */
public class FileUtils {
    /**
     * Private constructor to avoid creating an instance of this class.
     */
    private FileUtils() {
        throw new UnsupportedOperationException("This class cannot be instantiated.");
    }

    /**
     * Gets the "experiment" project root directory.
     *
     * @return the path to the project root directory
     * @throws IllegalStateException if unable to resolve the project root
     */
    public static Path getProjectRoot() {
        Path currentPath = Paths.get(Objects.requireNonNull(FileUtils.class.getResource("FileUtils.class")).getPath()).toAbsolutePath();
        while (currentPath != null) {
            if (currentPath.endsWith("experiment")) {
                return currentPath;
            }
            currentPath = currentPath.getParent();
        }
        throw new IllegalStateException("Unable to find project root from current working directory");
    }

    /**
     * Creates an empty directory. Creates parent directories if necessary. If
     * the directory already exists, then this method does nothing.
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
     *               or new file
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
     * Returns the path of the target file when hypothetically moved from the
     * source directory to the destination directory. NOTE: this method does
     * NOT move any files.
     *
     * @param source      the source directory
     * @param destination the destination directory
     * @param target      the target file in the source directory
     * @return the relative path of target in the destination directory. For
     * example, let
     * <pre>
     *      source = [sourcePrefix]/[source]
     *      destination = [destinationPrefix]/[destination]
     *      target = [sourcePrefix]/[source]/[suffix]/[fileName]
     * </pre>
     * then the method outputs,
     * <pre>
     *      relativePath = [destinationPrefix]/[destination]/[suffix]/[fileName]
     * </pre>
     */
    public static Path getRelativePath(Path source, Path destination, Path target) {
        if (source.equals(target)) {
            return destination;
        }
        // remove source prefix
        if (!target.startsWith(source)) {
            throw new IllegalArgumentException(target + " must exist under " + source);
        }
        Path suffix = target.subpath(source.getNameCount(), target.getNameCount());
        // add remaining suffix to destination
        return destination.resolve(suffix);
    }

    /**
     * Generates a relative path to a class file from a fully qualified class name.
     * @param fullyQualifiedClassName the fully qualified class name.
     * @return the relative path corresponding to the fully qualified class name.
     */
    public static Path getRelativePathFromFullyQualifiedClassName(String fullyQualifiedClassName) {
        String[] fullyQualifiedClassNameSplitted = fullyQualifiedClassName.split("\\.");
        if (fullyQualifiedClassNameSplitted.length > 1) {
            int classNameIdx = fullyQualifiedClassNameSplitted.length - 1;
            String className = fullyQualifiedClassNameSplitted[classNameIdx];
            fullyQualifiedClassNameSplitted[classNameIdx] = className + ".java";
            return Paths.get(
                    fullyQualifiedClassNameSplitted[0],
                    Arrays.copyOfRange(
                            fullyQualifiedClassNameSplitted,
                            1,
                            fullyQualifiedClassNameSplitted.length
                    )
            );
        }
        return Paths.get(fullyQualifiedClassName);
    }

    /**
     * Recursively copies all files from the source directory to the
     * destination directory. If a file in the source directory already exists
     * in the destination directory, then the original file will be overridden.
     *
     * @param source      the directory where the files are located
     * @param destination the directory where the files will be copied to
     * @throws Error if the source directory does not exist or an error occurs
     *               while copying a file
     * @see FileUtils#move(Path, Path)
     */
    public static void copy(Path source, Path destination) {
        if (!Files.exists(source)) {
            throw new Error("Directory " + source + " is not found");
        }
        if (!Files.exists(destination)) {
            createDirectories(destination);
        }
        try (Stream<Path> walk = Files.walk(source)) {
            walk
                    .forEach(p -> {
                        Path relativePath = getRelativePath(source, destination, p);
                        try {
                            if (Files.isDirectory(p)) {
                                createDirectories(relativePath);
                            } else {
                                Files.copy(p, relativePath, StandardCopyOption.REPLACE_EXISTING);
                            }
                        } catch (IOException e) {
                            throw new Error("Error when trying to move the file " + p, e);
                        }
                    });
        } catch (IOException e) {
            throw new Error("Error when trying to copy " + source + " to " + destination, e);
        }
    }

    /**
     * Recursively moves all files from the source directory to the
     * destination directory. If a file in the source directory already exists
     * in the destination directory, then the original file will be overridden.
     *
     * @param source      the directory where the files are located
     * @param destination the directory where the files will be moved to
     * @throws Error if the source directory does not exist or an error occurs
     *               while moving a file
     * @see FileUtils#copy
     */
    public static void move(Path source, Path destination) {
        if (!Files.exists(source)) {
            throw new Error("Directory " + source + " is not found");
        }
        if (!Files.exists(destination)) {
            createDirectories(destination);
        }
        copy(source, destination);
        deleteDirectory(source);
    }

    /**
     * Writes {@code content} to {@code path} in JSON format. Creates a new
     * file and parent directories if necessary. If file already exists,
     * then this method overrides any previous content.
     *
     * @param path    a file
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
     * Reads the JSON file.
     * The method only works with standard Java classes. To read custom
     * objects a parser must be implemented.
     *
     * @param path a file
     * @return the file contents as a Object
     * @throws Error if unable to process the file
     */
    public static Object readJSON(Path path, TypeReference typeReference) {
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
     * Returns the file contents as a CSV parser.
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
            return (type == null)
                    // if type is null, return List<?>
                    ? objectMapper.readValue(jsonPath.toFile(), new TypeReference<>() {
            })
                    // otherwise, return List<T> of the given type
                    : objectMapper.readValue(
                    jsonPath.toFile(),
                    objectMapper.getTypeFactory().constructCollectionType(List.class, type)
            );
        } catch (IOException e) {
            throw new Error("Error in processing the JSON file " + jsonPath, e);
        }
    }

    /**
     * Reads a list of objects from a JSON file. We use this method rather
     * than {@link FileUtils#readJSONList(Path, Class)} for parameterized
     * types, where we cannot retrieve the corresponding class.
     *
     * @param jsonPath a JSON file
     * @return a list of objects without a specified type
     * @see FileUtils#readJSONList(Path, Class)
     */
    public static List<?> readJSONList(Path jsonPath) {
        return readJSONList(jsonPath, null);
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
     * EvoSuite.
     *
     * @param path a file
     * @return true iff the given file represents an EvoSuite scaffolding file
     */
    public static boolean isScaffolding(Path path) {
        return path.getFileName().toString().endsWith("scaffolding.java");
    }

    /**
     * Searches a file within all the subdirectories of a given directory.
     * @param dir the root directory.
     * @param fullyQualifiedClassFilePath the fully qualified name of the class file.
     * @return the full path to the file. Returns null if the path is not found.
     */
    public static Path searchClassFile(Path dir, Path fullyQualifiedClassFilePath) {
        File dirFile = new File(dir.toString());
        int classNameIdx = fullyQualifiedClassFilePath.getNameCount() - 1;
        Path className = fullyQualifiedClassFilePath.getName(classNameIdx);
        File[] files = dirFile.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    Path path = searchClassFile(file.toPath(), fullyQualifiedClassFilePath);
                    if (!(path == null)) {
                        return path;
                    }
                } else if (file.getName().equals(className.toString())) {
                    if (file.toPath().endsWith(fullyQualifiedClassFilePath)) {
                        return file.toPath();
                    }
                }
            }
        }
        return null;
    }

    /**
     * Extracts the class name from a fully qualified class name and returns it as a string.
     * @param fullyQualifiedClassName the fully qualified class name.
     * @return the class name.
     */
    public static String getClassNameFromFullyQualifiedName(String fullyQualifiedClassName) {
        String[] fullyQualifiedClassNameSplitted = fullyQualifiedClassName.split("\\.");
        if (fullyQualifiedClassNameSplitted.length > 1) {
            int classNameIdx = fullyQualifiedClassNameSplitted.length - 1;
            return fullyQualifiedClassNameSplitted[classNameIdx];
        }
        return fullyQualifiedClassName;
    }
}
