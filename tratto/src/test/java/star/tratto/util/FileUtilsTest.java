package star.tratto.util;

import org.junit.jupiter.api.Test;
import star.tratto.data.IOPath;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class FileUtilsTest {
    @Test
    public void createFileTest() {
        Path path = Paths.get("src/test/java/star/tratto/util/temp/tempFile.json");
        try {
            FileUtils.createFile(path);
            Files.delete(path);
            Files.delete(path.getParent());
        } catch (IOException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void writeTest() {
        Path path = Paths.get("src/test/java/star/tratto/util/temp/tempFile.json");
        try {
            FileUtils.write(path, List.of("input1", "input2", "input3"));
            Files.delete(path);
            Files.delete(path.getParent());
        } catch (IOException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void writeChunksTest() {
        Path path = Paths.get("src/test/java/star/tratto/util/temp/tempFile.json");
        try {
            FileUtils.writeChunks(path, List.of("input1", "input2", "input3"));
            Files.delete(Paths.get("src/test/java/star/tratto/util/temp/tempFile_0.json"));
            Files.delete(Paths.get("src/test/java/star/tratto/util/temp/tempFile_1.json"));
            Files.delete(Paths.get("src/test/java/star/tratto/util/temp/tempFile_2.json"));
            Files.delete(path.getParent());
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void getAllJavaFilesFromDirectoryTest() {
        Path dir = Paths.get("src/test/java/star/tratto/oraclegrammar/custom");
        try {
            List<String> fileNames = FileUtils.getAllJavaFilesFromDirectory(dir)
                    .stream()
                    .map(p -> p.getFileName().toString())
                    .toList();
            assertEquals(2, fileNames.size());
            assertTrue(fileNames.contains("SplitterTest.java"));
            assertTrue(fileNames.contains("ParserTest.java"));
        } catch (Error e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void readJSONListTest() {
        Path path = IOPath.IGNORE_FILE.getPath();
        try {
            List<String> ignoreFileList = FileUtils.readJSONList(path)
                    .stream()
                    .map(e -> (String) e)
                    .collect(Collectors.toList());
            assertEquals(List.of(".DS_Store", "package-info"), ignoreFileList);
        } catch (Error e) {
            e.printStackTrace();
            fail();
        }
    }
}
