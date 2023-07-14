package star.tratto.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import star.tratto.exceptions.FileNotCreatedException;
import star.tratto.exceptions.FolderCreationFailedException;
import star.tratto.identifiers.FileFormat;
import star.tratto.identifiers.FileName;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * This class manages file I/O utilities.
 */
public class FileUtils {
    private static final Logger logger = LoggerFactory.getLogger(FileUtils.class);

    public static void appendToFile(
            String dirPath,
            String fileName,
            FileFormat fileFormat,
            String projectName,
            Object content
    ) {
        try {
            // create a new file
            File file = createFile(Paths.get(dirPath, projectName).toString(), fileName, fileFormat);
            // depending on the file format, save the content, accordingly
            switch (fileFormat) {
                case TXT, CSV -> {
                    // Convert content to string
                    String fileContent = (String) content;
                    // Create a FileWriter object with append flag set to true
                    FileWriter writer = new FileWriter(file, true);
                    // Write content to the file
                    writer.write(fileContent + "\n");
                    // Close the FileWriter object
                    writer.close();
                }
                case JSON -> {
                    ObjectMapper objectMapper = new ObjectMapper();
                    objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, content);
                }
                default -> {
                    String errMsg = String.format("File format %s not yet supported to save the content of a file.", fileFormat.getExtension());
                    logger.error(errMsg);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * The method creates a file within a given directory. If the file already
     * exists, then this method does nothing.
     *
     * @param dirPath the path to the directory where the file must be saved
     * @param fileName the name of the file where to write the content
     * @param fileFormat the format of the file
     * @return the file created
     * @throws IOException if the file cannot be created
     */
    public static File createFile(String dirPath, String fileName, FileFormat fileFormat) throws IOException {
        String filePath = Paths.get(dirPath, fileName + fileFormat.getExtension()).toString();
        File dir = new File(dirPath);
        File file = new File(filePath);
        // create directory.
        if (!dir.exists()) {
            boolean success = dir.mkdirs();
            if (!success) {
                String errMsg = String.format("Unable to create folder %s", dirPath);
                logger.error(errMsg);
                throw new FolderCreationFailedException();
            }
        }
        // create file.
        if (!file.exists()) {
            boolean fileCreated = file.createNewFile();
            if (!fileCreated) {
                String errMsg = String.format("Unable to create file %s to path %s.", fileName, dirPath);
                logger.error(errMsg);
                throw new FileNotCreatedException();
            }
        }
        return file;
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
        // get path.
        Path jsonPath = Paths.get(filePath);
        if (!Files.exists(jsonPath)) {
            throw new IOException(String.format("JSON file %s not found.", filePath));
        }
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
}
