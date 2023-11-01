package star.tratto.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.FileAttribute;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * This class provides file input and output
 * utilities, such as: creating, copying, moving, writing, reading, etc.
 */
public class FileUtils {
    /** Private constructor to avoid creating an instance of this class. */
    private FileUtils() {
        throw new UnsupportedOperationException("This class cannot be instantiated.");
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
     * @param target the target file in the source directory
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
    private static Path getRelativePath(Path sourceDir, Path destination, Path target) {
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
                            try {
                                Files.copy(p, relativePath, StandardCopyOption.REPLACE_EXISTING);
                            } catch (IOException e) {
                                throw new Error("Error when trying to copy the file " + p + " to " + relativePath, e);
                            }
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
     * Reads a list of objects from a JSON file. If the given type is null,
     * then returns a list of unknown type (wildcard).
     *
     * @param jsonPath a JSON file
     * @param type the class type of the elements to which the JSON data will
     *             be deserialized
     * @return a list of objects
     * @param <T> the type of the list elements,
     *             or null (in which case the list element type is arbitrary)
     */
    public static <T> List<T> readJSONList(Path jsonPath, Class<T> type) {
        if (!Files.exists(jsonPath)) {
          throw new Error("JSON file " + jsonPath + " not found");
        }
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            if (type == null) {
                return objectMapper.readValue(jsonPath.toFile(), new TypeReference<>() {});
            } else {
                return objectMapper.readValue(
                        jsonPath.toFile(),
                        objectMapper.getTypeFactory().constructCollectionType(List.class, type)
                );
            }
        } catch (IOException e) {
            throw new Error("Error in processing the JSON file " + jsonPath, e);
        }
    }

    /**
     * Reads a list of objects from a JSON file. This method is used instead
     * of {@link FileUtils#readJSONList(Path, Class)} for parameterized types,
     * where it is not possible to retrieve the corresponding class, such as
     * lists of List&lt;Integer&gt;.
     *
     * @param jsonPath a JSON file
     * @return a list of objects
     */
    public static List<?> readJSONList(Path jsonPath) {
        return readJSONList(jsonPath, null);
    }

    /**
     * Gets all Java files under a given directory (including in
     * subdirectories).
     *
     * @param dir a directory
     * @return all Java files (as Path objects) in {@code dir}
     * @throws Error if unable to collect files from {@code dir}
     */
    public static List<Path> getAllJavaFilesUnderDirectory(Path dir) {
        try (Stream<Path> walk = Files.walk(dir)) {
            return walk
                    .filter(p -> p.getFileName().toString().endsWith(".java"))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new Error("Error when collecting Java files from " + dir, e);
        }
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
     * Generates a relative path to a class file from a fully qualified class name.
     * @param fullyQualifiedClassName the fully qualified class name.
     * @return the relative path corresponding to the fully qualified class name.
     */
    public static Path getRelativePathFromFullyQualifiedClassName(String fullyQualifiedClassName) {
        String[] fullyQualifiedClassNameSplitted = fullyQualifiedClassName.split("\\.");
        if (fullyQualifiedClassName.equals("")) {
            return Paths.get(fullyQualifiedClassName);
        }
        if (fullyQualifiedClassNameSplitted.length > 1) {
            int classNameIdx = fullyQualifiedClassNameSplitted.length - 1;
            String className = fullyQualifiedClassNameSplitted[classNameIdx];
            fullyQualifiedClassNameSplitted[classNameIdx] = className + ".java";
            Path relativePath = Paths.get(
                    fullyQualifiedClassNameSplitted[0],
                    Arrays.copyOfRange(
                            fullyQualifiedClassNameSplitted,
                            1,
                            fullyQualifiedClassNameSplitted.length
                    )
            );
            return relativePath;
        }
        return Paths.get(fullyQualifiedClassName + ".java");
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
}
