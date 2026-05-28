package entity;

import java.util.List;
import permissions.Permission;
import trie.FileTrie;

public class Directory extends FileSystemEntity {

    private FileTrie children;

    public Directory(String name, Permission permission) {
        super(name, permission);
        this.children = new FileTrie();
    }

    public void addChild(FileSystemEntity entity) {
        if(entity == null)
            throw new RuntimeException("Entity cannot be null");

        if(children.exists(entity.getName())) {
            throw new RuntimeException("Entity already exists");
        }

        entity.setParent(this);

        children.insert(entity.getName() ,entity);
        updateModifiedTime();
    }

    public void removeChild(String name) {
        if(!exists(name))
            throw new RuntimeException("Entity not found");

        FileSystemEntity child = children.search(name);

        child.setParent(null);
        children.delete(name);
        updateModifiedTime();
    }

    public FileSystemEntity getChild(String name) {
        return children.search(name);
    }

    public boolean exists(String name) {
        return children.exists(name);
    }

    public List<FileSystemEntity> getChildren() {
        return children.getAll();
    }

    public List<FileSystemEntity> searchPrefix(String prefix) {
        return children.startsWith(prefix);
    }

    // public int getSize() {
    //     return 0;
    // }
}