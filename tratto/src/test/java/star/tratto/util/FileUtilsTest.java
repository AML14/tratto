package star.tratto.util;

import org.junit.jupiter.api.Test;
import star.tratto.data.TrattoPath;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class FileUtilsTest {
    private Path setupFileEnvironment() {
        Path root = Paths.get("src/test/java/star/tratto/util/temp");
        FileUtils.createDirectories(root);
        FileUtils.createDirectories(root.resolve("other1"));
        FileUtils.createDirectories(root.resolve("other2"));
        FileUtils.createDirectories(root.resolve("other3"));
        FileUtils.createFile(root.resolve("other1/tempFile_a.json"));
        FileUtils.createFile(root.resolve("other1/tempFile_b.json"));
        FileUtils.createFile(root.resolve("other1/nested_directory/tempFile_secret.json"));
        FileUtils.createFile(root.resolve("other3/bonus.txt"));
        return root;
    }

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
    public void deleteDirectoryTest() {
        Path root = setupFileEnvironment();
        try {
            FileUtils.deleteDirectory(root);
        } catch (Error e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void copyTest() {
        try {
            Path root = setupFileEnvironment();
            Path other = root.getParent().resolve("otherTemp");
            FileUtils.copy(root, other);
            FileUtils.deleteDirectory(other);
            FileUtils.deleteDirectory(root);
        } catch (Error e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void moveTest() {
        try {
            Path root = setupFileEnvironment();
            Path other = root.getParent().resolve("otherTemp");
            FileUtils.move(root, other);
            FileUtils.deleteDirectory(other);
        } catch (Error e) {
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
    public void readStringTest() {
        Path path = Paths.get("src/test/java/star/tratto/util/temp/tempFile.json");
        try {
            FileUtils.write(path, List.of("input1", "input2", "input3"));
            assertEquals("[ \"input1\", \"input2\", \"input3\" ]", FileUtils.readString(path));
            Files.delete(path);
            Files.delete(path.getParent());
        } catch (IOException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void getAllJavaFilesUnderDirectoryTest() {
        Path dir = Paths.get("src/test/java/star/tratto/oraclegrammar/custom");
        try {
            List<String> fileNames = FileUtils.getAllJavaFilesUnderDirectory(dir)
                    .stream()
                    .map(p -> p.getFileName().toString())
                    .toList();
            assertEquals(3, fileNames.size());
            assertTrue(fileNames.contains("SplitterTest.java"));
            assertTrue(fileNames.contains("ParserTest.java"));
            assertTrue(fileNames.contains("OracleAlternatesGeneratorTest.java"));
        } catch (Error e) {
            e.printStackTrace();
            fail();
        }
    }
}
