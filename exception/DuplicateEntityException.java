package exception;

public class DuplicateEntityException extends FileSystemException {
    public DuplicateEntityException(String name) {
        super("Entity already exits: " + name);
    }
}
