package com.datastructure.hashmap.customhashmap;

import java.util.LinkedList;

/**
 * Helper class to store a key-value pair
 */
class Pair {
    int key;
    int value;

    // Constructor to initialize key-value pair
    Pair(int key, int value) {
        this.key = key;
        this.value = value;
    }
}

/**
 * Custom HashMap implementation using separate chaining (array of linked lists)
 */
public class MyHashMap {

    private final int SIZE = 10;  // Number of buckets in the hash map
    private LinkedList<Pair>[] map; // Array of linked lists to handle collisions

    // Constructor to initialize the hash map
    public MyHashMap() {
        map = new LinkedList[SIZE];
        for (int i = 0; i < SIZE; i++) {
            map[i] = new LinkedList<>(); // Initialize each bucket as empty linked list
        }
    }

    // Hash function: returns index of bucket for a given key
    private int getHash(int key) {
        return key % SIZE;
    }

    /**
     * Insert a key-value pair into the hash map
     * If key already exists, update its value
     */
    public void put(int key, int value) {
        int index = getHash(key);        // Find bucket index
        LinkedList<Pair> bucket = map[index]; // Get linked list at that bucket

        // Check if key already exists in bucket
        for (Pair p : bucket) {
            if (p.key == key) {
                p.value = value; // Update existing key
                return;
            }
        }

        // If key does not exist, add new pair to bucket
        bucket.add(new Pair(key, value));
        System.out.println("Inserted: (" + key + ", " + value + ")");
    }

    /**
     * Retrieve value by key
     *
     * @param key key to search
     * @return value if found, null if not found
     */
    public Integer get(int key) {
        int index = getHash(key);
        LinkedList<Pair> bucket = map[index];

        for (Pair p : bucket) {
            if (p.key == key) return p.value; // Return value if key found
        }

        return null; // Key not found
    }

    /**
     * Remove a key-value pair by key
     *
     * @param key key to remove
     */
    public void remove(int key) {
        int index = getHash(key);
        LinkedList<Pair> bucket = map[index];

        for (Pair p : bucket) {
            if (p.key == key) {
                bucket.remove(p); // Remove pair from bucket
                System.out.println("Removed key: " + key);
                return;
            }
        }

        // Key not found
        System.out.println("Key not found: " + key);
    }

    /**
     * Display contents of the hash map
     * Shows all buckets and their key-value pairs
     */
    public void display() {
        System.out.println("\nHash Map Contents:");
        for (int i = 0; i < SIZE; i++) {
            System.out.print("Bucket " + i + ": ");
            for (Pair p : map[i]) {
                System.out.print("(" + p.key + "," + p.value + ") ");
            }
            System.out.println();
        }
    }
}
