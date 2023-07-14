package star.tratto.util;

import org.junit.jupiter.api.Test;
import star.tratto.identifiers.FileFormat;
import star.tratto.identifiers.FileName;
import star.tratto.identifiers.IOPath;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class FileUtilsTest {
    @Test
    public void getAllJavaFilesFromDirectoryTest() {
        Path dir = Paths.get("src/test/java/star/tratto/oraclegrammar/custom");
        try {
            List<String> fileNames = FileUtils.getAllJavaFilesFromDirectory(dir)
                    .stream()
                    .map(p -> p.getFileName().toString())
                    .toList();
            assertEquals(List.of("SplitterTest.java", "ParserTest.java"), fileNames);
        } catch (IOException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void getAllJavaFilesFromDirectoryDoesNotExistTest() {
        Path dir = Paths.get("arda/middle-earth/hobbiton");
        try {
            FileUtils.getAllJavaFilesFromDirectory(dir);
            fail();
        } catch (IOException ignored) {}
    }

    @Test
    public void getAbsolutePathTest() {
        assertEquals("some/random/dir/ignore_file.java", FileUtils.getAbsolutePath("some/random/dir", FileName.IGNORE_FILE, FileFormat.JAVA));
    }

    @Test
    public void readJSONListTest() {
        String filePath = Paths.get(
                IOPath.REPOS.getValue(),
                FileName.IGNORE_FILE.getValue() + FileFormat.JSON.getExtension()
        ).toString();
        try {
            List<String> ignoreFileList = FileUtils.readJSONList(filePath)
                    .stream()
                    .map(e -> (String) e)
                    .collect(Collectors.toList());
            assertEquals(List.of(".DS_Store", "package-info"), ignoreFileList);
        } catch (IOException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void readJSONListDoesNotExistTest() {
        String filePath = "../../no_file_to_see_here.json";
        try {
            FileUtils.readJSONList(filePath);
            fail();
        } catch (IOException ignored) {}
    }
}
