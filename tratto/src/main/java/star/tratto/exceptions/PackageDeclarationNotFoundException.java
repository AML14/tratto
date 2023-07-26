package star.tratto.exceptions;

import java.io.IOException;

public class PackageDeclarationNotFoundException extends IOException {
    public PackageDeclarationNotFoundException() {
        super();
    }

    public PackageDeclarationNotFoundException(String message) {
        super(message);
    }
}
