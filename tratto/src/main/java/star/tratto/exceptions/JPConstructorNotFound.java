package star.tratto.exceptions;

public class JPConstructorNotFound extends Exception {
    public JPConstructorNotFound() {
        super();
    }

    public JPConstructorNotFound(String message) {
        super(message);
    }
}
