package star.tratto.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * I/O utilities: creating files, writing to files, retrieving Java files, etc.
 */
public class FileUtils {
    /**
     * Creates an empty directory at a given path. Creates parent directories
     * if necessary. If the directories already exists, then this method does
     * nothing.
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
     * @param dirPath a root directory
     * @throws Error if an error occurs while traversing or deleting files
     */
    public static void deleteDirectory(Path dirPath) {
        try (Stream<Path> walk = Files.walk(dirPath)) {
            walk
                    .filter(p -> !p.equals(dirPath))
                    .forEach(p -> {
                        try {
                            if (Files.isDirectory(p)) {
                                // empty directory before deleting
                                deleteDirectory(p);
                            } else {
                                Files.delete(p);
                            }
                        } catch (Exception e) {
                            throw new Error("Error when trying to delete the file " + p, e);
                        }
                    });
            // delete root directory last
            Files.delete(dirPath);
        } catch (IOException e) {
            throw new Error("Error when trying to delete the directory " + dirPath, e);
        }
    }

    /**
     * Writes {@code content} to {@code path} in JSON format. Creates a new
     * file and parent directories if necessary. If file already exists,
     * overrides any previous content.
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
     * Writes {@code contentChunks} to individual files in JSON format.
     * Creates new files and parent directories if necessary. If files already
     * exists, overrides any previous content.
     *
     * @param path base path for all chunks. The i-th chunk will be written to
     *             the path "[path]_i.json".
     * @param contentChunks objects to write
     * @param <T> the type of each chunk
     * @throws Error if unable to create files/directories or unable to write
     * content to file
     * @see FileUtils#write
     */
    public static <T> void writeChunks(Path path, List<T> contentChunks) {
        for (int i = 0; i < contentChunks.size(); i++) {
            T chunk = contentChunks.get(i);
            String chunkFileName = String.format("%s_%d.json", path.getFileName().toString().replace(".json", ""), i);
            Path chunkPath = path.getParent().resolve(chunkFileName);
            FileUtils.write(chunkPath, chunk);
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
