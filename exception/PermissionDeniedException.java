package exception;

public class PermissionDeniedException extends FileSystemException {

    public PermissionDeniedException(String operation) {
        super("You don't have permission to " + operation + " this file");
    }
}