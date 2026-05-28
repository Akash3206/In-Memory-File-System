# In-Memory File System (Java)

A modular and extensible in-memory file system built in Java using object-oriented design principles, Trie-based indexing, LRU caching, and stack-based versioning.

---

# Features

## Core File System Operations

* Create directories (`mkdir`)
* Create files (`touch`)
* Delete files/directories (`rm`)
* Read file content (`cat`)
* Write/update file content (`write`)
* List directory contents (`ls`)

---

## Trie-Based Directory Indexing

Each directory internally uses a custom Trie data structure for:

* Fast child lookup
* Prefix-based search
* Efficient insertion/deletion

### Supported Trie Operations

* Insert entity
* Search entity
* Delete entity
* Prefix search

---

## Stack-Based Versioning

Files support:

* Undo
* Redo

Implemented using:

* Undo stack
* Redo stack

Every file write stores the previous file state automatically.

---

## LRU Cache Integration

The filesystem integrates a custom Least Recently Used (LRU) cache to optimize:

* Path resolution
* Repeated file access

### Cache Characteristics

* O(1) get
* O(1) put
* O(1) eviction

Implemented using:

* HashMap
* Doubly Linked List

---

# Architecture

```text
FileSystem
│
├── PathResolver
├── LRUCache
├── Directory
│   └── FileTrie
│       └── TrieNode
│
├── File
│   └── VersionManager
│       └── FileState
│
├── FileSystemFactory
│
└── Custom Exceptions
```

---

# Project Structure

```text
src/
│
├── cache/
│   ├── CacheNode.java
│   └── LRUCache.java
│
├── core/
│   ├── FileSystem.java
│   └── PathResolver.java
│
├── entity/
│   ├── Directory.java
│   ├── File.java
│   └── FileSystemEntity.java
│
├── exception/
│   ├── DuplicateEntityException.java
│   ├── EntityNotFoundException.java
│   ├── FileSystemException.java
│   ├── InvalidDirectoryException.java
│   ├── InvalidFileOperationException.java
│   ├── InvalidPathException.java
│   ├── PermissionDeniedException.java
│   └── VersionNotFoundException.java
│
├── factory/
│   └── FileSystemFactory.java
│
├── permissions/
│   └── Permission.java
│
├── trie/
│   ├── FileTrie.java
│   └── TrieNode.java
│
└── versioning/
    ├── FileState.java
    └── VersionManager.java
```

---

# Design Patterns Used

## Singleton Pattern

Used in:

* `FileSystem`

Ensures only one filesystem instance exists.

---

## Factory Pattern

Used in:

* `FileSystemFactory`

Centralizes object creation for:

* Files
* Directories

---

## Composite Pattern

Used in:

* `FileSystemEntity`
* `Directory`
* `File`

Allows files and directories to be treated uniformly.

---

# Time Complexities

| Operation       | Complexity        |
| --------------- | ----------------- |
| Path Resolution | O(depth)          |
| Trie Search     | O(length of name) |
| Trie Insert     | O(length of name) |
| Trie Delete     | O(length of name) |
| LRU Get         | O(1)              |
| LRU Put         | O(1)              |
| Undo/Redo       | O(1)              |

---

# Sample Usage

```java
FileSystem fs = FileSystem.getInstance();

Permission p = new Permission(
    true,
    true,
    true,
    true,
    false
);

fs.mkdir("/root/docs", p);

fs.touch(
    "/root/docs/a.txt",
    p,
    "hello",
    "txt"
);

System.out.println(
    fs.cat("/root/docs/a.txt")
);

fs.write(
    "/root/docs/a.txt",
    "hello world"
);

fs.undo("/root/docs/a.txt");

System.out.println(
    fs.cat("/root/docs/a.txt")
);

List<FileSystemEntity> files =
    fs.ls("/root/docs");
```

---

# Custom Exceptions

The project uses a dedicated exception hierarchy for cleaner error handling.

Examples:

* InvalidPathException
* PermissionDeniedException
* DuplicateEntityException
* EntityNotFoundException
* VersionNotFoundException

---

# Current Capabilities

## Supported

* Trie-backed directory hierarchy
* Prefix-based file search
* Undo/Redo versioning
* LRU path caching
* Recursive path traversal
* Object-oriented modular architecture

---

# Planned Enhancements

* Move/Rename operations
* Recursive delete (`rm -r`)
* Thread safety using locks
* Persistent storage
* File compression
* Search filters
* Access logs
* Snapshot/version history
* Symbolic links

---

# Key Learning Outcomes

This project demonstrates:

* Low-Level Design (LLD)
* Data Structure Integration
* Cache Design
* Object-Oriented Programming
* Design Patterns
* File System Architecture
* Recursive Traversal
* Trie Applications
* Stack-Based State Management

---

# Tech Stack

* Java
* OOP
* Trie
* HashMap
* Doubly Linked List
* Stack / Deque
* Custom Exception Hierarchy

---
