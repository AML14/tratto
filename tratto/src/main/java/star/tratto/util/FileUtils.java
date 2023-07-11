package star.tratto.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import star.tratto.exceptions.FileNotCreatedException;
import star.tratto.exceptions.FolderCreationFailedException;
import star.tratto.identifiers.file.FileFormat;
import star.tratto.identifiers.file.FileName;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


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
                    String errMsg = String.format("File format %s not yet supported to save the content of a file.", fileFormat.getValue());
                    logger.error(errMsg);
                }
            }
        } catch (IOException | FolderCreationFailedException e) {
            e.printStackTrace();
        }
    }

    public static void appendToFileExclusive(
            String dirPath,
            String fileName,
            FileFormat fileFormat,
            String projectName,
            String content
    ) {
        String filePath = Paths.get(dirPath, projectName, fileName + fileFormat.getValue()).toString();
        File file = new File(filePath);
        boolean found = false;
        if (file.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = br.readLine()) != null) {
                    if (line.contains(content)) {
                        found = true;
                        break;
                    }
                }
            } catch (IOException e) {
                logger.error("Error reading file: " + e.getMessage());
            }
        }
        if (!found) {
            appendToFile(
                    dirPath,
                    fileName,
                    fileFormat,
                    projectName,
                    content
            );
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
    public static File createFile(String dirPath, String fileName, FileFormat fileFormat) throws FolderCreationFailedException, IOException, FileNotCreatedException {
        String filePath = Paths.get(dirPath, fileName + fileFormat.getValue()).toString();
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
     * Recursively gets a list of all java files in a given directory.
     *
     * @param dir the directory from which to extract all the files contained
     * @return the list of all the files existing within the given directory
     */
    public static List<File> getAllJavaFilesFromDirectory(File dir) {
        List<File> javaFileList = new ArrayList<>();
        for (File file: Objects.requireNonNull(dir.listFiles())) {
            if (file.isDirectory()) {
                javaFileList.addAll(getAllJavaFilesFromDirectory(file));
            }
            if (isJavaFile(file)) {
                javaFileList.add(file);
            }
        }
        return javaFileList;
    }

    /**
     * The method builds a complete path to a file.
     *
     * @param dirPath the path to the directory where the file must be saved
     * @param fileName the name of the file where to write the content
     * @param fileFormat the format of the file
     * @return the complete path to a file
     */
    public static String getAbsolutePathToFile(String dirPath, FileName fileName, FileFormat fileFormat) {
        return Paths.get(dirPath, fileName.getValue()) + fileFormat.getValue();
    }

    /**
     * The method checks if a file is a Java file.
     * @param file the file to inspect
     * @return a boolean value: {@code true} if the file is a Java file, {@code false} otherwise
     */
    public static boolean isJavaFile(File file) {
        String fileName = file.getName();
        // The file doesn't have an extension
        return fileName.endsWith(".java");
    }

    /**
     * The method reads a list from a JSON file.
     * @param filePath the path to the JSON file
     * @return the list of values read from the JSON file
     */
    public static List<?> readJSONList(String filePath) {
        File jsonFile = new File(filePath);
        if (!jsonFile.exists()) {
            String errMsg = String.format("JSON file %s not found.", filePath);
            logger.error(errMsg);
            return new ArrayList<>();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(
                    jsonFile,
                    new TypeReference<>() {
                    }
            );
        } catch (IOException e) {
            String errMsg = String.format("Unexpected error in processing the JSON file %s.", filePath);
            logger.error(errMsg);
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
