package com.datastructure.hashmap.customhashmap;

public class MainApp {
    public static void main(String[] args) {

        // Create an instance of our custom HashMap
        MyHashMap map = new MyHashMap();

        // Insert key-value pairs into the map
        map.put(1, 100);      // No collision, bucket 1
        map.put(2, 200);      // No collision, bucket 2
        map.put(12, 1200);    // Collision with key 2 (same bucket due to hash)
        map.put(22, 2200);    // Collision with key 2 (same bucket)

        // Display the contents of the hash map
        map.display();

        // Retrieve value associated with key 12
        System.out.println("\nGet key 12: " + map.get(12));

        // Remove key 2 from the map
        map.remove(2);
        // Display the map after removal
        map.display();

        // Attempt to remove a key that does not exist (50)
        map.remove(50); // Should print "Key not found"
    }
}
