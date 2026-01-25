package com.collections.mapinterface;

import java.util.*;

// Find key with maximum value in Map<String, Integer>
public class MaxValueKey {

	// Method one - using loop
	public static String findKeyWithMaxValue(Map<String, Integer> map) {
		if (map == null || map.isEmpty()) {
			return null;
		}

		String maxKey = null;
		int maxValue = Integer.MIN_VALUE;

		for (Map.Entry<String, Integer> entry : map.entrySet()) {
			if (entry.getValue() > maxValue) {
				maxValue = entry.getValue();
				maxKey = entry.getKey();
			}
		}

		return maxKey;
	}

	// Method two - using Collections.max & Comparator
	public static String findKeyWithMaxValueStream(Map<String, Integer> map) {
		if (map == null || map.isEmpty()) {
			return null;
		}

		return Collections.max(map.entrySet(), Map.Entry.comparingByValue()).getKey();
	}

	public static void main(String[] args) {
		Map<String, Integer> scores = new HashMap<>();
		scores.put("A", 10);
		scores.put("B", 20);
		scores.put("C", 15);
		scores.put("D", 25);
		scores.put("E", 20);

		System.out.println("Map: " + scores);

		// Using loop method
		System.out.println("Key with max value (loop)   : " + findKeyWithMaxValue(scores));

		// Using Collections.max
		System.out.println("Key with max value (stream) : " + findKeyWithMaxValueStream(scores));
	}
}