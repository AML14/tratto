package star.tratto.data;

import java.io.IOException;

public class ResolvedTypeNotFound extends IOException {
    public ResolvedTypeNotFound() {
        super();
    }

    public ResolvedTypeNotFound(String message) {
        super(message);
    }
}
