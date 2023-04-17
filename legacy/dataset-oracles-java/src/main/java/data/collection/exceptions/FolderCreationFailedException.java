package data.collection.exceptions;

public class FolderCreationFailedException extends Exception {
    public FolderCreationFailedException() {
        super();
    }

    public FolderCreationFailedException(String message) {
        super(message);
    }
}
