package star.tratto.data;

public class ServerUnexpectedResponseCodeException extends RuntimeException {
    public ServerUnexpectedResponseCodeException(String message) {
        super(message);
    }
}
