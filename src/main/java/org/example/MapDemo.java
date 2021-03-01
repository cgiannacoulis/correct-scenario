package org.example;

import java.util.HashMap;
import java.util.Map;

public class MapDemo {
	public static void main(String[] args) {
		// Default implementation
		Map<String, String> firstMap = new HashMap<>();
		firstMap.put("k1", " Value1");
		firstMap.put("k2", " Value2");
		firstMap.put("k3", " Value2");
		firstMap.put("k3", " Value3");

		for (String key : firstMap.keySet()) {
			System.out.printf("%s:%s", key, firstMap.get(key));
			System.out.println();
		}
		System.out.println();

		firstMap.forEach((k, v) -> System.out.printf("%s:%s, ", k, v));
		System.out.println();

		// Unomodifiable map
		Map<String, String> unmodifiableMap = Map.ofEntries(Map.entry("k1", "Value1"), Map.entry("k2", "Value2"));

		unmodifiableMap.forEach((k, v) -> System.out.printf("%s:%s, ", k, v));
		System.out.println();

		// Extract keys to an array
		String[] keysArray = firstMap.keySet().toArray(new String[firstMap.keySet().size()]);
		for (String key : keysArray) {
			System.out.printf("%s, ", key);
		}
		System.out.println();

		Map<String, String> secondMap = new HashMap<>();

		/* Replaced by the following method using lambdas
		if (firstMap.get("k4") != null) {
			secondMap.put("k4", firstMap.get("k4"));
		} else {
			secondMap.put("k4", "None");
		}
		*/
		secondMap.put("k1", firstMap.getOrDefault("k4", "None"));
		secondMap.computeIfAbsent("k2", s -> "None");
		secondMap.computeIfAbsent("k2", s -> "None again? Nop.");
		secondMap.forEach((x, z) -> System.out.printf("%s:%s, ", x, z));
		System.out.println();

		secondMap.computeIfPresent("k2", (k, v) -> v.toLowerCase());
		secondMap.forEach((x, z) -> System.out.printf("%s:%s, ", x, z));

	}
}
