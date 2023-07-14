package star.tratto.exceptions;

import java.io.IOException;

public class FolderCreationFailedException extends IOException {
    public FolderCreationFailedException() {
        super();
    }

    public FolderCreationFailedException(String message) {
        super(message);
    }
}
