package exception;

public class InvalidFileOperationException extends FileSystemException {
    public InvalidFileOperationException(String operation) {
        super("File does not exist " + operation);
    }
}
