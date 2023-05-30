package star.tratto.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import star.tratto.identifiers.file.FileFormat;
import star.tratto.identifiers.file.FileName;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class FileUtils {

    /**
     * The method writes the content passed to the function to a file of a given format {@link FileFormat}. If the {@code append}
     * parameter is {@code true} the method appends the content to the file (if it already exists), otherwise it create
     * a new file and write the content within it.
     *
     * @param dirPath The path to the directory where the file must be saved
     * @param fileName The name of the file where to write the content
     * @param fileFormat The format of the file {@link FileFormat}
     * @param content The content to write within the file
     * @param append A boolean value. If true, the content is appended to the file (if it exists). Otherwise, the
     *               method creates a new file.
     * @throws IOException If the file cannot be written
     */
    public static void write(
            String dirPath,
            String fileName,
            FileFormat fileFormat,
            Object content,
            Boolean append
    ) throws IOException {
        // create a new file
        File file = createFile(dirPath, fileName, fileFormat);
        // depending on the file format, save the content, accordingly
        switch (fileFormat) {
            case TXT:
                // Convert content to string
                String fileContent = (String) content;
                // Create a FileWriter object with append flag set to true
                FileWriter writer = new FileWriter(file, append);
                // Write content to the file
                writer.write(fileContent + "\n");
                // Close the FileWriter object
                writer.close();
                break;
            case JSON:
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, content);
                break;
            default:
                String errMsg = String.format("File format %s not yet supported to save the content of a file.", fileFormat.getValue());
                System.err.println(errMsg);
                break;
        }
    }

    /**
     * The method checks if the content passed to the function is already written within a file.
     *
     * @param dirPath The path to the directory where the file must be saved
     * @param fileName The name of the file where to write the content
     * @param fileFormat The format of the file {@link FileFormat}
     * @param content The content to write within the file
     * @return A boolean value: {@code true} if the method finds the content within the file, {@code false} otherwise.
     */
    public static boolean isInFile(
            String dirPath,
            String fileName,
            FileFormat fileFormat,
            String content
    ) {
        String filePath = Paths.get(dirPath, fileName + fileFormat.getValue()).toString();
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
                System.err.println("Error reading file: " + e.getMessage());
            }
        }
        return found;
    }

    /**
     * The method creates a file {@link File} within a given directory.
     *
     * @param dirPath The path to the directory where the file must be saved
     * @param fileName The name of the file where to write the content
     * @param fileFormat The format of the file {@link FileFormat}.
     *
     * @return The file created {@link File}
     * @throws IOException If the file cannot be created
     */
    public static File createFile(String dirPath, String fileName, FileFormat fileFormat) throws IOException {
        String filePath = Paths.get(dirPath, fileName + fileFormat.getValue()).toString();
        File dir = new File(dirPath);
        File file = new File(filePath);
        if (!dir.exists()) {
            boolean success = dir.mkdirs();
            if (!success) {
                String errMsg = String.format("Unable to create folder %s", dirPath);
                System.err.println(errMsg);
            }
        }
        if (!file.exists()) {
            boolean fileCreated = file.createNewFile();
            if (!fileCreated) {
                String errMsg = String.format("Unable to create file %s to path %s.", fileName, dirPath);
                System.err.println(errMsg);
            }
        }
        return file;
    }

    /**
     * The method gets the list of all the files that exist within a given directory.
     *
     * @param dir The directory from which to extract all the files contained
     * @return The list of all the files existing within the given directory
     */
    public static List<File> extractJavaFilesFromDirectory(File dir) {
        List<File> javaFileList = new ArrayList<>();
        for (File file: dir.listFiles()) {
            if (file.isDirectory()) {
                javaFileList.addAll(extractJavaFilesFromDirectory(file));
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
     * @param dirPath The path to the directory where the file must be saved
     * @param fileName The name of the file where to write the content
     * @param fileFormat The format of the file {@link FileFormat}
     * @return
     */
    public static String getAbsolutePathToFile(String dirPath, FileName fileName, FileFormat fileFormat) {
        String filePath = Paths.get(dirPath, fileName.getValue()).toString() + fileFormat.getValue();
        return filePath;
    }

    /**
     * The method checks if a file is a Java file.
     * @param file The file to inspect
     * @return A boolean value: {@code true} if the file is a Java file, {@code false} otherwise.
     */
    public static boolean isJavaFile(File file) {
        String fileName = file.getName();
        if (fileName.endsWith(".java")) {
            // The file doesn't have an extension
            return true;
        }
        return false;
    }

    /**
     * The method reads a list from a JSON file and returns a corresponding Java list.
     * @param filePath The path to the JSON file.
     *
     * @return The list of Java objects parsed from the JSON list
     */
    public static List<?> readJSONList(String filePath) {
        File jsonFile = new File(filePath);
        if (!jsonFile.exists()) {
            String errMsg = String.format("JSON file %s not found.", filePath);
            System.err.println(errMsg);
            return new ArrayList<>();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<?> tokenList = objectMapper.readValue(
                jsonFile,
                new TypeReference<List<?>>() {}
            );
            return tokenList;
        } catch (IOException e) {
            String errMsg = String.format("Unexpected error in processing the JSON file %s.", filePath);
            System.err.println(errMsg);
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
