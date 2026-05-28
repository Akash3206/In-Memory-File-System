package core;

import entity.File;
import entity.Directory;
import permissions.Permission;

public class FileSystemFactory {

    public static File createFile(String name, Permission permission, String content, String fileType) {
        validate(name);
        return new File(name, permission, content, fileType);
    }

    public static Directory createDirectory(String name, Permission permission) {
        validate(name);
        return new Directory(name, permission);
    }

    private static void validate(String name) {
        if(name == null || name.trim().isEmpty())  {
            throw new RuntimeException("Invalid name");
        }
    }
}