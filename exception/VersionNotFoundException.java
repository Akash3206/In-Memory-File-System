package exception;

public class VersionNotFoundException extends FileSystemException {

    public VersionNotFoundException(String operation) {
        super("No versions Available for " + operation);
    }
}