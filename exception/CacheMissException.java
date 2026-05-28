package exception;

public class CacheMissException extends FileSystemException {
    public CacheMissException(String path) {
        super("Cache miss for the " + path);
    }
}
