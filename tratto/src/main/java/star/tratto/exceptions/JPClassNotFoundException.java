package star.tratto.exceptions;

import java.io.IOException;

public class JPClassNotFoundException extends IOException {
    public JPClassNotFoundException() {
        super();
    }

    public JPClassNotFoundException(String message) {
        super(message);
    }
}
