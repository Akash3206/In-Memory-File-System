package trie;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import entity.FileSystemEntity;
import exception.EntityAlreadyExistsException;

public class FileTrie {

    private TrieNode root;

    public FileTrie() {
        this.root = new TrieNode();
    }

    public void insert(String name, FileSystemEntity entity) {
        TrieNode node = root;

        for(char c : name.toCharArray()) {
            node = node.addChild(c);
        }
        
        if(node.isEnd())
            throw new EntityAlreadyExistsException(name);

        node.setEnd(true);
        node.setEntity(entity);
    }

    public FileSystemEntity search(String name) {
        TrieNode node = root;

        for(char c : name.toCharArray()) {
            if(node.getChild(c) == null)
                return null;

            node = node.getChild(c);
        }

        if(!node.isEnd())
            return null;
        
        return node.getEntity();
    }

    public boolean exists(String name) {
        return search(name) != null;
    }

    public List<FileSystemEntity> getAll() {
        return startsWith("");
    }
    
    // Get all files that startsWith the given prefix
    public List<FileSystemEntity> startsWith(String prefix) {
        TrieNode node = root;

        for(char c : prefix.toCharArray()) {
            
            if(node.getChild(c) == null)
                return new ArrayList<>();

            node = node.getChild(c);
        }
    
        List<FileSystemEntity> allNodes = new ArrayList<>();
        depthFirstSearch(node, allNodes);
        return allNodes;
    }
    private void depthFirstSearch(TrieNode node, List<FileSystemEntity> allNodes) {

        if(node.isEnd()) 
            allNodes.add(node.getEntity());
        
        Map<Character, TrieNode> children = node.getChildren();
        for(Map.Entry<Character, TrieNode> entry : children.entrySet()) {
            TrieNode childNode = entry.getValue();
            depthFirstSearch(childNode, allNodes);
        }
    }

    // Delete
    public void delete(String name) {
        recursiveDelete(0, name, root);
    }
    private boolean recursiveDelete(int i, String name, TrieNode node) {

        if(node == null)
            return false;

        if(i == name.length()) {
            if(!node.isEnd())
                    return false;

            node.setEnd(false);
            node.setEntity(null);
           
            return node.getChildren().isEmpty();
        }
        
        char c = name.charAt(i);
        TrieNode child = node.getChild(c);
        boolean remove = recursiveDelete(i + 1, name, child);

        if(remove) {
            node.removeChild(c);
        }

        return !node.isEnd() && node.getChildren().isEmpty();
    }
}