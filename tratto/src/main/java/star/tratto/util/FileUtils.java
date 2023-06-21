package star.tratto.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import star.tratto.identifiers.file.FileFormat;
import star.tratto.identifiers.file.FileName;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class FileUtils {

    /**
     * The method writes the content passed to the function to a file of a given format {@link FileFormat}. If the {@code append}
     * parameter is {@code true} the method appends the content to the file (if it already exists), otherwise it create
     * a new file and write the content within it.
     *
     * @param dirPath the path to the directory where the file must be saved
     * @param fileName the name of the file where to write the content
     * @param fileFormat the format of the file {@link FileFormat}
     * @param content the content to write within the file
     * @param append a boolean value. If true, the content is appended to the file (if it exists). Otherwise, the
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
            case TXT -> {
                // Convert content to string
                String fileContent = (String) content;
                // Create a FileWriter object with append flag set to true
                FileWriter writer = new FileWriter(file, append);
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
                System.err.println(errMsg);
            }
        }
    }

    /**
     * The method creates a file {@link File} within a given directory.
     *
     * @param dirPath the path to the directory where the file must be saved
     * @param fileName the name of the file where to write the content
     * @param fileFormat the format of the file {@link FileFormat}.
     *
     * @return the file created {@link File}
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
     * @param dir the directory from which to extract all the files contained
     * @return the list of all the files existing within the given directory
     */
    public static List<File> extractJavaFilesFromDirectory(File dir) {
        List<File> javaFileList = new ArrayList<>();
        for (File file: Objects.requireNonNull(dir.listFiles())) {
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
     * @param dirPath the path to the directory where the file must be saved
     * @param fileName the name of the file where to write the content
     * @param fileFormat the format of the file {@link FileFormat}
     * @return the complete path to a file.
     */
    public static String getAbsolutePathToFile(String dirPath, FileName fileName, FileFormat fileFormat) {
        return Paths.get(dirPath, fileName.getValue()).toString() + fileFormat.getValue();
    }

    /**
     * The method checks if a file is a Java file.
     * @param file the file to inspect
     * @return a boolean value: {@code true} if the file is a Java file, {@code false} otherwise.
     */
    public static boolean isJavaFile(File file) {
        String fileName = file.getName();
        // The file doesn't have an extension
        return fileName.endsWith(".java");
    }

    /**
     * The method reads a list from a JSON file and returns a corresponding Java list.
     * @param filePath the path to the JSON file
     *
     * @return the list of Java objects parsed from the JSON list
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
        } catch (IOException e) {
            String errMsg = String.format("Unexpected error in processing the JSON file %s.", filePath);
            System.err.println(errMsg);
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
