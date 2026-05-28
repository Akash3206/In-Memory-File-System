package exception;

public class InvalidDirectoryOperationException extends FileSystemException {
    public InvalidDirectoryOperationException(String path) {
        super("Invalid directory path " + path);
    }
}
