package data.collection.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import data.collection.enums.FileFormat;
import data.collection.enums.FileName;
import data.collection.enums.Path;
import data.collection.exceptions.FileNotCreatedException;
import data.collection.exceptions.FolderCreationFailedException;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {
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
                case TXT:
                    // Convert content to string
                    String fileContent = (String) content;
                    // Create a FileWriter object with append flag set to true
                    FileWriter writer = new FileWriter(file, true);
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
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FolderCreationFailedException e) {
            e.printStackTrace();
        } catch (FileNotCreatedException e) {
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
                System.err.println("Error reading file: " + e.getMessage());
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

    public static File createFile(String dirPath, String fileName, FileFormat fileFormat) throws FolderCreationFailedException, IOException, FileNotCreatedException {
        String filePath = Paths.get(dirPath, fileName + fileFormat.getValue()).toString();
        File dir = new File(dirPath);
        File file = new File(filePath);

        if (!dir.exists()) {
            boolean success = dir.mkdirs();
            if (!success) {
                String errMsg = String.format("Unable to create folder %s", dirPath);
                System.err.println(errMsg);
                throw new FolderCreationFailedException();
            }
        }

        if (!file.exists()) {
            boolean fileCreated = file.createNewFile();
            if (!fileCreated) {
                String errMsg = String.format("Unable to create file %s to path %s.", fileName, dirPath);
                System.err.println(errMsg);
                throw new FileNotCreatedException();
            }
        }
        return file;
    }

    public static List<File> extractJavaFilesFromDirectory(File dir) {
        List<File> javaFileList = new ArrayList<>();
        for(File file: dir.listFiles()) {
            if (file.isDirectory()) {
                javaFileList.addAll(extractJavaFilesFromDirectory(file));
            }
            if (isJavaFile(file)) {
                javaFileList.add(file);
            }
        }
        return javaFileList;
    }

    public static String getAbsolutePath(String dir, FileName fileName, FileFormat fileFormat) {
        String filePath = Paths.get(dir, fileName.getValue()).toString() + fileFormat.getValue();
        return filePath;
    }

    public static boolean isJavaFile(File file) {
        String fileName = file.getName();
        if (fileName.endsWith(".java")) {
            // The file doesn't have an extension
            return true;
        }
        return false;
    }

    public static List<?> readJSONList(String path) {
        File jsonFile = new File(path);
        if (!jsonFile.exists()) {
            String errMsg = String.format("JSON file %s not found.", path);
            System.err.println(errMsg);
            return new ArrayList<>();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<?> tokenList = objectMapper.readValue(
                jsonFile,
                new TypeReference<List<?>>(){}
            );
            return tokenList;
        } catch (IOException e) {
            String errMsg = String.format("Unexpected error in processing the JSON file %s.", path);
            System.err.println(errMsg);
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
