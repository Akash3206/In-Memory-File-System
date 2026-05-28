package versioning;

import java.util.ArrayDeque;
import java.util.Deque;

public class VersionManager {

    private Deque<FileState> undo;
    private Deque<FileState> redo;

    public VersionManager() {
        this.undo = new ArrayDeque<>();
        this.redo = new ArrayDeque<>();
    }

    public void save(FileState state) {
        undo.push(state);
        redo.clear();
    }

    public FileState undo(FileState current) {
        if(!canUndo()) {
            throw new RuntimeException("No states to undo");
        }

        redo.push(current);
        return undo.pop();
    }

    public FileState redo(FileState current) {
        if (!canRedo()) {
            throw new RuntimeException("No states to redo");
        }

        undo.push(current);
        return redo.pop();
    }

    public boolean canUndo() {
        return !undo.isEmpty();
    }

    public boolean canRedo() {
        return !redo.isEmpty();
    }
}