package data.collection.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import data.collection.enums.FileFormat;

import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

import data.collection.enums.FileName;
import data.collection.enums.Path;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class FileUtilsTest {
    String prefix = "src/test/java/data/collection/";

    @Test
    public void testCreateFileNew() throws Exception {
        String dirPath = prefix + "dummy";
        String fileName = "new_file";
        FileFormat format = FileFormat.TXT;
        File newFile = FileUtils.createFile(dirPath, fileName, format);
        assertTrue(new File(dirPath).isDirectory());
        assertTrue(newFile.exists());
        assertEquals(newFile, new File(prefix + "dummy/new_file.txt"));
        boolean isDeleted = newFile.delete();
        assertTrue(isDeleted);
    }

    @Test
    public void testCreateFilePreexisting() throws Exception {
        String dirPath = prefix + "dummy";
        String fileName = "Dummy";
        FileFormat format = FileFormat.JAVA;
        File newFile = FileUtils.createFile(dirPath, fileName, format);
        assertTrue(new File(dirPath).isDirectory());
        assertTrue(newFile.exists());
        assertEquals(newFile, new File(prefix + "dummy/Dummy.java"));
    }

    @Test
    public void testAppendToFileTxt() throws Exception {
        // add to existing file.
        String dirPath = prefix + "dummy";
        String fileName = "basic-text";
        FileFormat format = FileFormat.TXT;
        String projectName = "";
        String content = "Goodbye";
        FileUtils.appendToFile(dirPath, fileName, format, projectName, content);
        // test new file contents.
        File file = new File(prefix + "dummy/basic-text.txt");
        Scanner scanner = new Scanner(file);
        assertEquals("Hello", scanner.nextLine());
        assertEquals("Goodbye", scanner.nextLine());
        // remove file and re-write.
        boolean isDeleted = file.delete();
        assertTrue(isDeleted);
        FileUtils.appendToFile(dirPath, "basic-text", format, projectName, "Hello");
    }

    @Test
    public void testAppendToFileJson() throws Exception {
        // add to existing file.
        String dirPath = prefix + "dummy";
        String fileName = "basic-json";
        FileFormat format = FileFormat.JSON;
        String projectName = "";
        String content = "{ \"team\" : \"TRATTO\" }";
        ObjectMapper objectMapper = new ObjectMapper();
        Object json = objectMapper.readValue(content, Object.class);
        // currently overwrites rather than appends.
        // FileUtils.appendToFile(dirPath, fileName, format, projectName, json);
    }

    @Test
    public void testAppendToFileExclusive() throws Exception {
        // add to existing file.
        String dirPath = prefix + "dummy";
        String fileName = "basic-text";
        FileFormat format = FileFormat.TXT;
        String projectName = "";
        String content = "Goodbye";
        FileUtils.appendToFileExclusive(dirPath, fileName, format, projectName, content);
        // test new file contents.
        File file = new File(prefix + "dummy/basic-text.txt");
        Scanner scanner = new Scanner(file);
        assertEquals("Hello", scanner.nextLine());
        assertEquals("Goodbye", scanner.nextLine());
        // remove file and re-write.
        boolean isDeleted = file.delete();
        assertTrue(isDeleted);
        FileUtils.appendToFile(dirPath, "basic-text", format, projectName, "Hello");
    }

    @Test
    public void testAppendToFileExclusiveFound() throws Exception {
        // add existing content to file.
        String dirPath = prefix + "dummy";
        String fileName = "basic-text";
        FileFormat format = FileFormat.TXT;
        String projectName = "";
        String content = "Hello";
        FileUtils.appendToFileExclusive(dirPath, fileName, format, projectName, content);
        // test that file contents have not changed.
        File file = new File(prefix + "dummy/basic-text.txt");
        Scanner scanner = new Scanner(file);
        assertEquals("Hello", scanner.nextLine());
        assertFalse(scanner.hasNext());
    }

    @Test
    public void testExtractJavaFilesFromDirectory() {
        // get all files in dummy directory.
        File dir = new File(prefix + "dummy");
        List<File> files = FileUtils.extractJavaFilesFromDirectory(dir);
        Collections.sort(files);
        // get expected output files.
        ArrayList<File> expected = new ArrayList<>();
        expected.add(new File(prefix + "dummy/Dummy.java"));
        expected.add(new File(prefix + "dummy/DummyChild.java"));
        expected.add(new File(prefix + "dummy/OtherDummy.java"));
        Collections.sort(expected);
        assertEquals(expected, files);
    }

    @Test
    public void testExtractJavaFilesFromDirectoryEmpty() {
        File dir = new File("src/main/repos");
        assertEquals(new ArrayList<>(), FileUtils.extractJavaFilesFromDirectory(dir));
    }

    @Test
    public void testGetAbsolutePathToFile() {
        String dirPath = Path.REPOS.getValue();
        FileName fileName = FileName.INPUT_PROJECTS;
        FileFormat format = FileFormat.JSON;
        String path = FileUtils.getAbsolutePathToFile(dirPath, fileName, format);
        assertEquals(new File("src/main/repos/inputProjects.json").getAbsolutePath(), path);
    }

    @Test
    public void testIsJavaFileTrue() {
        File javaFile = new File(prefix + "dummy/Dummy.java");
        assertTrue(FileUtils.isJavaFile(javaFile));
    }

    @Test
    public void testIsJavaFileFalse() {
        File textFile = new File(prefix + "dummy/basic-text.txt");
        assertFalse(FileUtils.isJavaFile(textFile));
    }
}
