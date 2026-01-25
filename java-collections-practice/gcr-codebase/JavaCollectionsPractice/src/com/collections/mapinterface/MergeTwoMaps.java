package com.collections.mapinterface;

import java.util.*;

// Merge two maps - sum values for common keys
public class MergeTwoMaps {

	// Method one - using putIfAbsent
	public static Map<String, Integer> mergeMaps(Map<String, Integer> map1, Map<String, Integer> map2) {
		Map<String, Integer> result = new HashMap<>(map1);

		for (Map.Entry<String, Integer> entry : map2.entrySet()) {
			String key = entry.getKey();
			Integer value = entry.getValue();

			// If key exists: sum values, else: just put new value
			result.merge(key, value, Integer::sum);
		}

		return result;
	}

	// Method two - using getOrDefault
	public static Map<String, Integer> mergeMapsClassic(Map<String, Integer> map1, Map<String, Integer> map2) {
		Map<String, Integer> result = new HashMap<>(map1);

		for (Map.Entry<String, Integer> entry : map2.entrySet()) {
			String key = entry.getKey();
			int newValue = entry.getValue();

			// Sum if key exists, otherwise use new value
			result.put(key, result.getOrDefault(key, 0) + newValue);
		}

		return result;
	}

	public static void main(String[] args) {
		Map<String, Integer> map1 = new HashMap<>();
		map1.put("A", 1);
		map1.put("B", 2);

		Map<String, Integer> map2 = new HashMap<>();
		map2.put("B", 3);
		map2.put("C", 4);

		System.out.println("Map1: " + map1);
		System.out.println("Map2: " + map2);

		// method one
		Map<String, Integer> merged = mergeMaps(map1, map2);
		System.out.println("Merged (m 1): " + merged);

		// method two
		Map<String, Integer> mergedClassic = mergeMapsClassic(map1, map2);
		System.out.println("Merged (m 2): " + mergedClassic);
	}
}