package entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import permissions.Permission;

public abstract class FileSystemEntity {

    private String name;
    private Permission permission;
    private LocalDateTime createdTime;
    private LocalDateTime modifiedTime;
    private FileSystemEntity parent;

    public FileSystemEntity(String name, Permission permission) {
        this.name = name;
        this.permission = permission;
        this.createdTime = LocalDateTime.now();
        this.modifiedTime = LocalDateTime.now();
    }

    public String getName() {
        return name;
    }

    public void rename(String newName) {
        if(newName == null || newName.length() == 0)
            return;

        this.name = newName;
        updateModifiedTime();
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
        updateModifiedTime();
    }

    public LocalDateTime getCreatedAt() {
        return createdTime;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedTime;
    }

    protected void updateModifiedTime() {
        modifiedTime = LocalDateTime.now();
    }

    protected void setParent(FileSystemEntity parent) {
        this.parent = parent;
    }

    public FileSystemEntity getParent() {
        return parent;
    }

    public String getPath() {

        List<String> parts = new ArrayList<>();

        FileSystemEntity curr = this;
        while(curr != null) {

            parts.add(curr.getName());
            curr = curr.getParent();

        }

        Collections.reverse(parts);
        return "/" + String.join("/", parts);
    }

}   