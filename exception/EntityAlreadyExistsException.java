package exception;

public class EntityAlreadyExistsException extends FileSystemException {

    public EntityAlreadyExistsException(String name) {
        super("Entity already exists " + name);
    }
}