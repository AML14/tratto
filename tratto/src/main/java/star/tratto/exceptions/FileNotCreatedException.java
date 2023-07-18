package star.tratto.exceptions;

import java.io.IOException;

public class FileNotCreatedException extends IOException {
    public FileNotCreatedException() {
        super();
    }

    public FileNotCreatedException(String message) {
        super(message);
    }
}
