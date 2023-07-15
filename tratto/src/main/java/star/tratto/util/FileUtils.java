package star.tratto.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import star.tratto.identifiers.FileFormat;
import star.tratto.identifiers.FileName;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.Objects;

/**
 * Manages all necessary I/O utilities (e.g. creating files, writing to files,
 * retrieving all Java files, etc.).
 */
public class FileUtils {
    private static final Logger logger = LoggerFactory.getLogger(FileUtils.class);

    /**
     * Combines given information into a file path.
     *
     * @param dirPath a base root path
     * @param fileName the file name
     * @param fileFormat the type of file (e.g. ".txt", ".json")
     * @param projectName the corresponding project being analyzed
     * @return a file path which combines the given information into
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
     * Creates an empty file at a given path. Creates parent directories if
     * necessary. If the file already exists, then this method does nothing.
     *
     * @param path a Path representation of a file
     * @throws IOException if an error occurs while creating the parent
     * directories or new file.
     */
    public static void createFile(Path path) throws IOException {
        Files.createDirectories(path.getParent());
        if (!Files.exists(path)) {
            Files.createFile(path);
        }
    }

    /**
     * Writes {@code content} to {@code path}. Creates a new file/directories
     * if necessary. Overwrites any previous content.
     *
     * @param path a Path representation of a file
     * @param content an object to be mapped as JSON content
     * @throws IOException if unable to create files/directories or unable to
     * write content to file.
     */
    public static void write(Path path, Object content) throws IOException {
        createFile(path);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(path.toFile(), content);
    }

    /**
     * Gets all Java files in a given directory (and subdirectories).
     *
     * @param dir a directory
     * @return all Java files (as Path objects) in {@code dir}
     * @throws IOException if unable to collect files from {@code dir}
     */
    public static List<Path> getAllJavaFilesFromDirectory(Path dir) throws IOException {
        try (Stream<Path> walk = Files.walk(dir)) {
            return walk
                    .filter(p -> p.getFileName().toString().endsWith(".java"))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            // catch exception to avoid resource leak.
            throw new IOException(String.format("Error in collecting all files from %s.%n", dir), e);
        }
    }

    /**
     * Returns an absolute file path.
     *
     * @param dirPath the root directory path
     * @param fileName the name of the file in the directory
     * @param fileFormat the file extension
     * @return the complete path to the file
     */
    public static String getAbsolutePath(String dirPath, FileName fileName, FileFormat fileFormat) {
        return Paths.get(dirPath, fileName.getValue()) + fileFormat.getExtension();
    }

    /**
     * Reads a list from a JSON file.
     *
     * @param filePath a path to a JSON file
     * @return the list of values in the JSON file
     * @throws IOException if {@code filePath} does not exist or an error
     * occurs while reading the file
     */
    public static List<?> readJSONList(String filePath) throws IOException {
        // find path.
        Path jsonPath = Paths.get(filePath);
        if (!Files.exists(jsonPath)) throw new IOException(String.format("JSON file %s not found.", filePath));
        // read input from path.
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(
                    jsonPath.toFile(),
                    new TypeReference<>() {}
            );
        } catch (IOException e) {
            throw new IOException(String.format("Error in processing the JSON file %s.%n", filePath), e);
        }
    }

    public static void deleteDirectory(String dirPath) {
        try {
            org.apache.commons.io.FileUtils.deleteDirectory(new File(dirPath));
        } catch (IOException e) {
            logger.warn("There was an error when trying to delete the directory {} ", dirPath);
        }
    }

    /**
     * The method moves all the files contained in a given directory to another directory.
     * If files are contained in subdirectories, these are not copied, i.e., only the files
     * are moved to the destination directory, at the first level.
     *
     * @param orig the path to the directory where the files are located
     * @param dest the path to the directory where the files must be moved
     */
    public static void moveFilesRecursively(String orig, String dest) {
        File origDir = new File(orig);
        File destDir = new File(dest);
        if (!origDir.exists()) {
            String errMsg = String.format("Directory %s not found.", orig);
            logger.error(errMsg);
            return;
        }
        if (!destDir.exists()) {
            boolean success = destDir.mkdirs();
            if (!success) {
                String errMsg = String.format("Unable to create folder %s", dest);
                logger.error(errMsg);
                return;
            }
        }
        for (File file: Objects.requireNonNull(origDir.listFiles())) {
            if (file.isDirectory()) {
                moveFilesRecursively(file.getAbsolutePath(), dest);
            } else {
                file.renameTo(new File(dest + "/" + file.getName()));
            }
        }
    }

    public static String readFile(String filePath) {
        try {
            return Files.readString(Paths.get(filePath));
        } catch (IOException e) {
            logger.error("Error reading file: " + e.getMessage());
            return null;
        }
    }
}
