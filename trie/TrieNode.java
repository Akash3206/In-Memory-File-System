package trie;

import java.util.HashMap;
import java.util.Map;
import entity.FileSystemEntity;

public class TrieNode {

    private Map<Character, TrieNode> children;
    private boolean isEnd;
    private FileSystemEntity entity;

    public TrieNode() {
        this.children = new HashMap<>();
        this.isEnd = false;
        this.entity = null;
    }

    public TrieNode getChild(char c) {
        return children.get(c);
    }

    public TrieNode addChild(char c) {
        return children.computeIfAbsent(c, k -> new TrieNode());
    }

    public void removeChild(char c) {
        children.remove(c);
    }
 
    public void setEnd(boolean value) {
        isEnd = value;
    }

    public boolean isEnd() {
        return isEnd;
    }

    public void setEntity(FileSystemEntity entity) {
        this.entity = entity;
    }

    public FileSystemEntity getEntity() {
        return entity;
    }

    public Map<Character, TrieNode> getChildren() {
        return children;
    }
}