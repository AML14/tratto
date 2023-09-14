import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class FileUtilsTest {
    private static final Path tempRoot = Paths.get("src/test/resources/temp");

    private Path setupFileEnvironment() {
        FileUtils.createDirectories(tempRoot);
        FileUtils.createDirectories(tempRoot.resolve("other1"));
        FileUtils.createDirectories(tempRoot.resolve("other2"));
        FileUtils.createDirectories(tempRoot.resolve("other3"));
        FileUtils.createFile(tempRoot.resolve("other1/tempFile_a.json"));
        FileUtils.createFile(tempRoot.resolve("other1/tempFile_b.json"));
        FileUtils.createFile(tempRoot.resolve("other1/nested_directory/tempFile_secret.json"));
        FileUtils.createFile(tempRoot.resolve("other3/bonus.txt"));
        return tempRoot;
    }

    @Test
    public void getFQNPathTest() {
        assertEquals(Paths.get("com", "example", "github", "MyClass.java"), FileUtils.getFQNPath("com.example.github.MyClass"));
        assertEquals(Paths.get("MyClass.java"), FileUtils.getFQNPath("MyClass"));
    }

    @Test
    public void isScaffoldingTest() {
        assertTrue(FileUtils.isScaffolding(Paths.get("Stack_ESTest_scaffolding.java")));
        assertFalse(FileUtils.isScaffolding(Paths.get("Stack_ESTest.java")));
    }

    @Test
    public void createFileTest() {
        Path path = tempRoot.resolve("tempFile.json");
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
        Path path = tempRoot.resolve("tempFile.json");
        try {
            FileUtils.writeJSON(path, List.of("input1", "input2", "input3"));
            Files.delete(path);
            Files.delete(path.getParent());
        } catch (IOException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void readStringTest() {
        Path path = tempRoot.resolve("tempFile.json");
        try {
            FileUtils.writeJSON(path, List.of("input1", "input2", "input3"));
            assertEquals("[ \"input1\", \"input2\", \"input3\" ]", FileUtils.readString(path));
            Files.delete(path);
            Files.delete(path.getParent());
        } catch (IOException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void findClassPathTest() {
        Path baseDir = Paths.get("src");
        Path packageClass = Paths.get("data", "OracleOutput.java");
        assertEquals(Paths.get("src", "main", "java", "data", "OracleOutput.java"), FileUtils.findClassPath(baseDir, packageClass));
        Path simpleClass = Paths.get("FileUtils.java");
        assertEquals(Paths.get("src", "main", "java", "FileUtils.java"), FileUtils.findClassPath(baseDir, simpleClass));
    }
}
