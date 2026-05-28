package core;

import java.util.ArrayList;
import java.util.List;
import entity.*;
import exception.EntityNotFoundException;
import exception.InvalidPathException;

public class PathResolver {

    public static FileSystemEntity resolve(Directory root, String path) {
        
        List<String> parts = getPathParts(path);
        int n = parts.size();

        if(n == 0)
            throw new InvalidPathException(path);

        if(!parts.get(0).equals(root.getName()))
            throw new InvalidPathException(path);

        FileSystemEntity current = root;
        for(int i = 1; i < n; i++) {
            if(!(current instanceof Directory))
                throw new EntityNotFoundException(current.getName()); 

            Directory currentDirectory = (Directory) current; 

            FileSystemEntity child = currentDirectory.getChild(parts.get(i));

            if(child == null)
                throw new InvalidPathException(path);

            current = child;
        }
        return current;
    }

    public static Directory resolveParent(Directory root, String path) {
        int ind = path.lastIndexOf("/");

        if(ind <= 0) 
            throw new InvalidPathException(path);

        path = path.substring(0, ind);

        FileSystemEntity parent = resolve(root, path);

        if(!(parent instanceof Directory)) 
            throw new EntityNotFoundException(parent.getName());

        return (Directory) parent;
    }

    public static List<String> getPathParts(String path) {
        String[] strs = path.split("/");

        List<String> parts = new ArrayList<>();
        for(String s : strs) {
            if(!s.isEmpty())
                parts.add(s);
        }

        return parts;
    }
}