package cache;

import entity.FileSystemEntity;

public class CacheNode {
    
    private String path;
    private FileSystemEntity entity;
    private CacheNode prev;
    private CacheNode next;

    public CacheNode(String path, FileSystemEntity entity) {
        this.path = path;
        this.entity = entity;
        this.prev = null;
        this.next = null;
    }

    public String getPath() {
        return path;
    }

    public FileSystemEntity getEntity() {
        return entity;
    }

    public CacheNode getPrev() {
        return prev;
    }

    public CacheNode getNext() {
        return next;
    }

    public void setPrev(CacheNode prev) {
        this.prev = prev;
    }

    public void setNext(CacheNode next) {
        this.next = next;
    }

    public void setEntity(FileSystemEntity entity) {
        this.entity = entity;
    }
}
