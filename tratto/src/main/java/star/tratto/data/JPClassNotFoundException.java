package star.tratto.data;

import java.io.IOException;

public class JPClassNotFoundException extends IOException {
    public JPClassNotFoundException() {
        super();
    }

    public JPClassNotFoundException(String message) {
        super(message);
    }

    public JPClassNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
