package cache;

import java.util.HashMap;
import java.util.Map;
import entity.FileSystemEntity;

public class LRUCache {
    private int capacity;
    private Map<String, CacheNode> cache;
    private CacheNode head;
    private CacheNode tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.head = null;
        this.tail = null;
    }

    private void removeNode(CacheNode node) {

        CacheNode prev = node.getPrev();

        CacheNode next = node.getNext();

        if(prev != null) {
            prev.setNext(next);;
        }else {
            head = next;
        }

        if(next != null) {
            next.setPrev(prev);
        }else {
            tail = prev;
        }

        node.setPrev(null);
        node.setNext(null);
    }

    private void insertAtFront(CacheNode node) {
        if(head == null) {
            head = tail = node;
            return;
        }

        head.setPrev(node);
        node.setNext(head);
        node.setPrev(null);
        head = node;
    }

    public FileSystemEntity get(String path) {
        if(!cache.containsKey(path))
            return null;

        CacheNode node = cache.get(path);
        removeNode(node);
        insertAtFront(node);

        return node.getEntity();
    }

    public void put(String path, FileSystemEntity entity) {

        if(cache.containsKey(path)) {
            CacheNode node = cache.get(path);
            node.setEntity(entity);
            removeNode(node);
            insertAtFront(node);
        }else {

            if(cache.size() == capacity) {
                cache.remove(tail.getPath());
                removeNode(tail);
            }

            CacheNode node = new CacheNode(path, entity);
            insertAtFront(node);
            cache.put(path, node);
        }
    }

    public void remove(String path) {
        if(!cache.containsKey(path))
            return;

        cache.remove(path);
    }
}