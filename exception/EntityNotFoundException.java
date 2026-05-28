package exception;

public class EntityNotFoundException extends FileSystemException {

    public EntityNotFoundException(String name) {
        super("Entity not found: " + name);
    }
}