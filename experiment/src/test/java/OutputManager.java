import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ExtensionContext.Store.CloseableResource;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * This class temporarily moves the "output" directory to a temporary location
 * to avoid deleting previous results. All test files should have the class
 * annotation:
 * <pre>
 *     {@code @ExtendWith({OutputManager.class})}
 * </pre>
 */
public class OutputManager implements BeforeAllCallback, CloseableResource {
    private static final Path outputPath = Paths.get("output");
    private static final Path tempPath = Paths.get("temp-output");
    private static boolean isStarted = false;

    @Override
    public void beforeAll(ExtensionContext extensionContext) {
        if (!isStarted) {
            isStarted = true;
            if (Files.exists(outputPath)) {
                FileUtils.move(outputPath, tempPath);
            }
        }
    }

    @Override
    public void close() {
        if (Files.exists(tempPath)) {
            FileUtils.move(tempPath, outputPath);
        }
    }
}
