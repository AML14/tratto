package star.tratto.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import star.tratto.identifiers.FileFormat;
import star.tratto.identifiers.FileName;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * I/O utilities: creating files, writing to files, retrieving Java files, etc.
 */
public class FileUtils {
    /**
     * Returns an absolute file path.
     *
     * @param dirPath the root directory path
     * @param fileName the name of the file in the directory
     * @param fileFormat the file extension
     * @return the complete path to the file
     */
    public static Path getAbsolutePath(String dirPath, FileName fileName, FileFormat fileFormat) {
        return Paths.get(dirPath, fileName.getValue() + fileFormat.getExtension()).toAbsolutePath();
    }

    /**
     * Combines given information into a file path.
     *
     * @param dirPath a base root path
     * @param fileName a file name
     * @param fileFormat a file extension (e.g. ".txt", ".json")
     * @param projectName the corresponding project under analysis
     * @return a file path which combines the given information into the
     * format:
     *  "[dirPath]/[projectName]/[fileName].[fileFormat.getExtension()]"
     */
    public static Path getPath(
            String dirPath,
            String fileName,
            FileFormat fileFormat,
            String projectName
    ) {
        return Paths.get(dirPath, projectName, fileName + fileFormat.getExtension());
    }

    /**
     * Creates an empty directory at a given path. Creates parent directories
     * if necessary. If the directories already exists, then this method does
     * nothing.
     *
     * @param path a path
     */
    public static void createDirectories(Path path) {
        try {
            Files.createDirectories(path);
        } catch (IOException e) {
            throw new Error("Error when creating directory " + path, e);
        }
    }

    /**
     * Creates an empty file at a given path. Creates parent directories if
     * necessary. If the file already exists, then this method does nothing.
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
     * Recursively deletes all files and subdirectories in a given path.
     *
     * @param dirPath the root directory
     */
    public static void deleteDirectory(Path dirPath) {
        try (Stream<Path> walk = Files.walk(dirPath)) {
            walk
                    .filter(Files::isDirectory)
                    .forEach(p -> {
                        try {
                            Files.delete(p);
                        } catch (IOException e) {
                            throw new Error(
                                    "Error when deleting the file " + p + " in the directory " + dirPath, e
                            );
                        }
                    });
        } catch (IOException e) {
            throw new Error("Error when trying to delete the directory " + dirPath, e);
        }
    }

    /**
     * Writes {@code content} to {@code path} in JSON format. Creates a new
     * file and parent directories if necessary. If file already exists,
     * overrides any original content.
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
     */
    public static String readString(Path path) {
        try {
            return Files.readString(path);
        } catch (IOException e) {
            throw new Error("Error in processing file " + path, e);
        }
    }

    /**
     * Reads a list of objects from a JSON file.
     *
     * @param jsonPath a JSON file
     * @return the list of objects in the JSON file
     * @throws Error if unable to process JSON file
     */
    public static List<?> readJSONList(Path jsonPath) {
        if (!Files.exists(jsonPath)) throw new Error("JSON file " + jsonPath + " not found");
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(
                    jsonPath.toFile(),
                    new TypeReference<>() {}
            );
        } catch (IOException e) {
            throw new Error("Error in processing the JSON file " + jsonPath, e);
        }
    }

    /**
     * Recursively moves all files in a given directory to another directory.
     *
     * @param source the directory where the files are located
     * @param destination the directory where the files will be moved to
     */
    public static void move(Path source, Path destination) {
        if (!Files.exists(source)) throw new Error("Directory" + source + " is not found");
        if (!Files.exists(destination)) createDirectories(destination);
        try (Stream<Path> walk = Files.walk(source)) {
            walk
                    .filter(Files::isDirectory)
                    .forEach(p -> {
                        try {
                            Files.move(p, destination);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
        } catch (IOException e) {
            throw new Error("Error when moving files from " + source + " to " + destination, e);
        }

//        try (Stream<Path> paths = Files.list(source)) {
//            for (Path path : paths.toList()) {
//                if (Files.isDirectory(path)) {
//                    move(path.toAbsolutePath(), destination);
//                } else {
//                    Files.move(source, destination);
//                }
//            }
//        } catch (IOException e) {
//            throw new Error("Error when moving files from " + source + " to " + destination, e);
//        }
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
