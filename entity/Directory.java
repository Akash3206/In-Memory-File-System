package entity;

import java.util.List;

import exception.DuplicateEntityException;
import exception.EntityNotFoundException;
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
            throw new EntityNotFoundException("null");

        if(children.exists(entity.getName())) {
            throw new DuplicateEntityException(entity.getName());
        }

        entity.setParent(this);

        children.insert(entity.getName() ,entity);
        updateModifiedTime();
    }

    public void removeChild(String name) {
        if(!exists(name))
            throw new EntityNotFoundException(name);

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
}