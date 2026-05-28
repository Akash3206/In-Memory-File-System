package core;

import java.util.List;
import cache.LRUCache;
import entity.*;
import exception.EntityNotFoundException;
import exception.InvalidPathException;
import permissions.Permission;

public class FileSystem {

    private static FileSystem instance;
    private Directory root;
    private LRUCache cache;

    private FileSystem() {
        this.root = new Directory("root", new Permission(true, true, true, true, true));
        this.cache = new LRUCache(10);
    }

    public static FileSystem getInstance() {
        if(instance == null)
            instance = new FileSystem();
        return instance;
    }

    public void mkdir(String path, Permission permission) {

        int lastIndex = path.lastIndexOf("/");
        if(lastIndex <= 0)
            throw new InvalidPathException(path);

        String directoryName = path.substring(lastIndex + 1);

        Directory parent = PathResolver.resolveParent(root, path);

        Directory newDirectory = FileSystemFactory.createDirectory(directoryName, permission);

        parent.addChild(newDirectory);
    }

    public void touch(String path, Permission permission, String content, String fileType) {

        int lastIndex = path.lastIndexOf("/");
        if(lastIndex <= 0)
            throw new InvalidPathException(path);

        String fileName = path.substring(lastIndex + 1);

        Directory parent = PathResolver.resolveParent(root, path);

        File newFile = FileSystemFactory.createFile(fileName, permission, content, fileType);

        parent.addChild(newFile);    
    }

    public void rm(String path) {

        int lastIndex = path.lastIndexOf("/");

        if(lastIndex <= 0) 
            throw new InvalidPathException(path);
        
        String childName = path.substring(lastIndex + 1);

        Directory parent = PathResolver.resolveParent(root, path);
        
        cache.remove(path);
        parent.removeChild(childName);
    }

    public String cat(String path) {

        FileSystemEntity entity = resolveWithCache(path);

        if(!(entity instanceof File))
            throw new EntityNotFoundException(entity.getName());
        
        File file = (File)entity;
        return file.read();
    }

    public void write(String path, String content) {

        FileSystemEntity entity = resolveWithCache(path);

        if(!(entity instanceof File))
            throw new EntityNotFoundException(entity.getName());
        
        File file = (File)entity;
        file.write(content);
    }  

    public List<FileSystemEntity> ls(String path) {

        FileSystemEntity entity = resolveWithCache(path);

        if(!(entity instanceof Directory))
            throw new EntityNotFoundException(entity.getName());
        
        Directory directory = (Directory)entity;
        return directory.getChildren();
    }

    public void undo(String path) {

        FileSystemEntity entity = resolveWithCache(path);

        if(!(entity instanceof File))
            throw new EntityNotFoundException(entity.getName());

        File file = (File)entity;
        file.undo();
    }

    public void redo(String path) {

        FileSystemEntity entity = resolveWithCache(path);

        if(!(entity instanceof File))
            throw new EntityNotFoundException(entity.getName());

        File file = (File)entity;
        file.redo();
    }

    private FileSystemEntity resolveWithCache(String path) {

        FileSystemEntity entity = cache.get(path);

        if(entity == null) {
            entity = PathResolver.resolve(root, path);
            cache.put(path, entity);
        }
        
        return entity;
    }
}