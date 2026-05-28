package entity;

import exception.InvalidFileOperationException;
import permissions.Permission;
import versioning.FileState;
import versioning.VersionManager;

public class File extends FileSystemEntity {

    private int size;
    private String content;
    private String fileType;
    private VersionManager versionManager;

    public File(String name, Permission permission, String content, String fileType) {

        super(name, permission);

        this.size = content.length();
        this.content = content;
        this.fileType = fileType;
        this.versionManager = new VersionManager();
    }

    public String read() {
        if(!getPermission().canRead())
            throw new InvalidFileOperationException("read");

        return content;
    }

    public void write(String newContent) {
        if(!getPermission().canWrite())
            throw new InvalidFileOperationException("write");

        versionManager.save(new FileState(content));
        this.content = newContent;
        this.size = content.length();

        updateModifiedTime();
    }

    public void undo() {
        FileState prevState = versionManager.undo(new FileState(content));

        restore(prevState);
        updateModifiedTime();
    }

    public void redo() {
        FileState futureState = versionManager.redo(new FileState(content));

        restore(futureState);
        updateModifiedTime();
    }

    public int getSize() {
        return size;
    }

    public String getFileType() {
        return fileType;
    }

    public void restore(FileState state) {
        this.content = state.getContent();
        this.size = state.getSize();
    }
}