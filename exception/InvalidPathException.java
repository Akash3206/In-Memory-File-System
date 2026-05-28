package exception;

public class InvalidPathException extends FileSystemException {
    public InvalidPathException(String path) {
        super("Invalid Path: " + path);
    }
}
