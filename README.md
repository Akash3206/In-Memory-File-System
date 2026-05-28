# In-Memory File System

An object-oriented in-memory file system simulator built using Java to explore low-level system design concepts, data structures, and file organization techniques.

## Features

* Hierarchical directory and file management
* Trie-based path traversal for efficient lookup
* Core file operations:

  * Create
  * Read
  * Write
  * Delete
  * Directory management
* Recursive traversal for nested directories
* Custom permission handling:

  * Read
  * Write
  * Delete
  * Execute
* Exception-based access control
* File versioning with rollback support
* LRU caching for recently accessed file retrieval
* Fully in-memory storage without disk persistence

---

## Tech Stack

* **Language:** Java
* **Concepts Used:**

  * Object-Oriented Programming (OOP)
  * Data Structures & Algorithms
  * Trie Data Structure
  * LRU Cache
  * Recursive Tree Traversal
  * Version Control Logic
  * Exception Handling

---

## Project Structure

filesystem/
│
├── core/
│   ├── FileSystem.java
│   ├── FileSystemFactory.java
│   └── PathResolver.java
│
├── entity/
│   ├── FileSystemEntity.java
│   ├── Directory.java
│   └── File.java
│
├── permissions/
│   ├── Permission.java
│   └── AccessManager.java
│
├── versioning/
│   ├── FileState.java
│   └── VersionManager.java
│
├── cache/
│   └── LRUCache.java
│
└── Main.java
```

---

## System Design Overview

### Trie-Based File Organization

The file system uses a Trie-like hierarchical structure where:

* Each directory acts as a node
* Files and subdirectories are stored as children
* Path traversal is optimized for nested lookup operations

Example:

```plaintext
/root
   ├── docs
   │     └── notes.txt
   └── images
         └── photo.png
```

---

## Core Functionalities

### File Operations

* Create files/directories
* Read file contents
* Update/write file contents
* Delete files/directories
* Navigate nested paths

### Permission Management

Each file supports custom permissions:

* READ
* WRITE
* DELETE
* EXECUTE

Unauthorized operations throw custom exceptions.

### File Versioning

Every modification creates a new file state snapshot.

Supports:

* Version history tracking
* Rollback to previous versions

### LRU Cache

Recently accessed files are cached using an LRU eviction policy to improve retrieval efficiency.

---

## Example Usage

```java
FileSystem fs = FileSystemFactory.create();

fs.createDirectory("/docs");
fs.createFile("/docs/notes.txt", "Hello World");

String content = fs.readFile("/docs/notes.txt");

fs.writeFile("/docs/notes.txt", "Updated Content");

fs.rollback("/docs/notes.txt", 1);
```

---

## Learning Outcomes

This project helped in understanding:

* Low-level system design principles
* File system architecture
* Efficient path resolution techniques
* OOP design patterns
* Cache eviction strategies
* State/version management systems

---

## Future Improvements

* Persistent disk storage
* Multi-user support
* Concurrency handling
* Command-line interface
* File compression
* Search indexing
* Symbolic links

---
