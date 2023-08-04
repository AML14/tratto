package star.tratto.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * This class provides a collection of static methods for a variety of file
 * input and output utilities, such as: creating, copying, moving, writing,
 * reading, etc.
 */
public class FileUtils {
    /** Private constructor to avoid creating an instance of this class. */
    private FileUtils() {
        throw new UnsupportedOperationException("This class cannot be instantiated.");
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
     * @param root a root directory
     * @param extension a file/subdirectory in the given root directory
     * @return the path of {@code extension}, with {@code root} removed
     * @throws IllegalArgumentException if {@code extension} is equal to
     * {@code root} or if {@code extension} is not a child of {@code root}
     */
    private static Path getPathSuffix(Path root, Path extension) {
        if (!extension.startsWith(root)) {
            throw new IllegalArgumentException(extension + " must be an extension of " + root);
        }
        return extension.subpath(root.getNameCount(), extension.getNameCount());
    }

    /**
     * Returns the path of the target file when moved from the source
     * directory to the destination directory.
     *
     * @param source the source directory
     * @param destination the destination directory
     * @param target the target file in the source directory
     * @return the relative path of target in the destination directory. For
     * example, let
     *      source = [sourcePrefix]/[source]
     *      destination = [destinationPrefix]/[destination]
     *      target = [sourcePrefix]/[source]/[suffix]/[fileName]
     * then the method outputs,
     *      relativePath = [destinationPrefix]/[destination]/[suffix]/[fileName]
     */
    private static Path getRelativePath(Path source, Path destination, Path target) {
        if (source.equals(target)) return destination;
        Path suffix = getPathSuffix(source, target);
        return destination.resolve(suffix);
    }

    /**
     * Recursively copies all files from the source directory to the
     * destination directory. If a file in the source directory already exists
     * in the destination directory, then the original file will be overridden.
     *
     * @param source the directory where the files are located
     * @param destination the directory where the files will be copied to
     * @throws Error if the source directory does not exist or an error occurs
     * while copying a file
     */
    public static void copy(Path source, Path destination) {
        if (!Files.exists(source)) throw new Error("Directory " + source + " is not found");
        if (!Files.exists(destination)) createDirectories(destination);
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
     * @param source the directory where the files are located
     * @param destination the directory where the files will be moved to
     * @throws Error if the source directory does not exist or an error occurs
     * while moving a file
     */
    public static void move(Path source, Path destination) {
        if (!Files.exists(source)) throw new Error("Directory " + source + " is not found");
        if (!Files.exists(destination)) createDirectories(destination);
        copy(source, destination);
        deleteDirectory(source);
    }

    /**
     * Writes {@code content} to {@code path} in JSON format. Creates a new
     * file and parent directories if necessary. If file already exists,
     * then this method overrides any previous content.
     *
     * @param path a file
     * @param content an object to be written in JSON content
     * @throws Error if unable to create files/directories or unable to write
     * content to file
     */
    public static void write(Path path, Object content) {
        try {
            createFile(path);
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(path.toFile(), content);
        } catch (IOException e) {
            throw new Error("Error when writing " + content + " to file " + path, e);
        }
    }

    /**
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
     * Reads a list of objects from a JSON file. If the given type is null,
     * then returns a list of unknown type (wildcard).
     *
     * @param jsonPath a JSON file
     * @param type the class type of the elements to which the JSON data will
     *             be deserialized
     * @return a list of objects of the specified class type. If type is null,
     * then returns a list of a wildcard type.
     * @param <T> the generic type parameter representing the class of the
     *            elements in the list
     */
    public static <T> List<T> readJSONList(Path jsonPath, Class<T> type) {
        if (!Files.exists(jsonPath)) throw new Error("JSON file " + jsonPath + " not found");
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return (type == null)
                    // if type is null, return List<?>
                    ? objectMapper.readValue(jsonPath.toFile(), new TypeReference<>() {})
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
     * Gets all Java files in a given directory (and subdirectories).
     *
     * @param dir a directory
     * @return all Java files (as Path objects) in {@code dir}
     * @throws Error if unable to collect files from {@code dir}
     */
    public static List<Path> getAllJavaFilesFromDirectory(Path dir) {
        try (Stream<Path> walk = Files.walk(dir)) {
            return walk
                    .filter(p -> p.getFileName().toString().endsWith(".java"))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            // catch exception to avoid resource leak.
            throw new Error("Error in collecting all files from " + dir, e);
        }
    }
}
