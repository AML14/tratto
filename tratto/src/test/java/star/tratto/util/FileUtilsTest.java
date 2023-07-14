package star.tratto.util;

import org.junit.jupiter.api.Test;
import star.tratto.identifiers.FileFormat;
import star.tratto.identifiers.FileName;
import star.tratto.identifiers.IOPath;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class FileUtilsTest {
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
