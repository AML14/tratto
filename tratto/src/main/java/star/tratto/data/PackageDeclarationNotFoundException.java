package star.tratto.data;

import java.io.IOException;

public class PackageDeclarationNotFoundException extends IOException {
    public PackageDeclarationNotFoundException() {
        super();
    }

    public PackageDeclarationNotFoundException(String message) {
        super(message);
    }
}
