package com.collections.mapinterface;

import java.util.*;

// Invert a Map<Key, Values> to Map<Value, List<Keys>>
// Groups keys with same value into a List
public class InvertMap {

	public static Map<Integer, List<String>> invertMap(Map<String, Integer> original) {
		Map<Integer, List<String>> inverted = new HashMap<>();

		for (Map.Entry<String, Integer> entry : original.entrySet()) {
			String key = entry.getKey();
			Integer value = entry.getValue();

			// Get or create the list for this value
			List<String> keysList = inverted.getOrDefault(value, new ArrayList<>());
			keysList.add(key);

			// Put back into inverted map
			inverted.put(value, keysList);
		}

		return inverted;
	}

	public static void main(String[] args) {
		Map<String, Integer> original = new HashMap<>();
		original.put("A", 1);
		original.put("B", 2);
		original.put("C", 1);
		original.put("D", 3);
		original.put("E", 2);

		System.out.println("Original map:");
		System.out.println(original);

		Map<Integer, List<String>> result = invertMap(original);

		System.out.println("\nInverted map:");
		System.out.println(result);
	}
}