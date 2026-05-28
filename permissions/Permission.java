package permissions;

public class Permission {

    private final boolean read;
    private final boolean write;
    private final boolean delete;
    private final boolean move;
    private final boolean execute;

    public Permission(boolean read, boolean write, boolean delete, boolean move, boolean execute) {
        this.read = read;
        this.write = write;
        this.delete = delete;
        this.move = move;
        this.execute = execute;
    }

    public boolean canRead() {
        return read;
    }

    public boolean canWrite() {
        return write;
    }

    public boolean canMove() {
        return move;
    }

    public boolean canDelete() {
        return delete;
    }

    public boolean canExecute() {
        return execute;
    }

    @Override
    public String toString() {
        return (read ? "r" : "-") + (write ? "w" : "-") 
            + (delete ? "d" : "-") + (move ? "m" : "-") + (execute ? "e" : "-");
    }
}