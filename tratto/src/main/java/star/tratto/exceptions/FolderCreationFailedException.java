package star.tratto.exceptions;

public class FolderCreationFailedException extends Exception {
    public FolderCreationFailedException() {
        super();
    }

    public FolderCreationFailedException(String message) {
        super(message);
    }
}
