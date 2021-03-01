package org.example;

import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;

import java.util.*;

public class SetDemo {
	private static final Lorem generator = LoremIpsum.getInstance();

	public static void main(String[] args) {
		//demoHashSet();
		//demoTreeSet();
		demoLinkedHashSet();
	}

	private static void demoLinkedHashSet() {
		LinkedHashSet<String> firstSet = new LinkedHashSet<>();
		firstSet.addAll(Set.of("First element", "Second element", "Third element", "Fourth element", "Fifth element"));
		printSetContents(firstSet);
		System.out.printf("Size of the set is %d.", firstSet.size());
		System.out.println();
		System.out.printf("Removing fourth element from set resulted to %s.", firstSet.remove("Fourth element"));
		System.out.println();
		System.out.printf("Checking for existence resulted to %s.", firstSet.contains("Fourth element"));
		System.out.println();
		printSetContents(firstSet);
	}

	private static void demoTreeSet() {
		Set<String> firstTreeSet = new TreeSet<>();
		firstTreeSet.addAll(generateContent(20));
		printSetContents(firstTreeSet);

		TreeSet<String> secondTreeSet = new TreeSet<>();
		secondTreeSet.addAll(generateContent(20));
		printSetContents(secondTreeSet);
		System.out.printf("Here are the first (%s) and last element (%s) of the set.", secondTreeSet.first(),
				secondTreeSet.last());

		secondTreeSet.remove(secondTreeSet.first());
		secondTreeSet.remove(secondTreeSet.last());
		System.out.println();
		System.out.println("After removing first and last element");
		printSetContents(secondTreeSet);

		String firstElementRemoved = secondTreeSet.pollFirst();
		String lastElementRemoved = secondTreeSet.pollLast();
		System.out.println();
		System.out.printf("After polling head (%s) and tail(%s).", firstElementRemoved, lastElementRemoved);
		System.out.println();
		printSetContents(secondTreeSet);
	}

	private static void printSetContents(Set<String> targetSet) {
		for (String name : targetSet) {
			System.out.println(name);
		}
		System.out.println();
	}

	private static void demoHashSet() {
		// HashSet is the default Set implementation
		Set<String> firstSet = new HashSet<>();
		firstSet.add("First item");
		firstSet.add("Second item");
		firstSet.add("Third item");
		firstSet.add("Fourth item");
		firstSet.add("Fifth item");
		firstSet.addAll(generateStringNames());

		for (String item : firstSet) {
			System.out.println(item);
		}

		// Old way of iterating over a Set collection
		/*
			Iterator<String> firstSetIterator = firstSet.iterator();
			while (firstSetIterator.hasNext()) {
				String item = firstSetIterator.next();
				System.out.println(item);
			}
		*/

		System.out.println();

		//Ternary operator
		System.out.printf("Fifth element %s.", firstSet.contains("Fifth item") ? "exists" : "does not exist");
		System.out.println();

		// Create unmodifiable sets
		Set<String> unmodifiableFirstSet = Collections.unmodifiableSet(firstSet);
		// Will not work, will raise an exception
		//unmodifiableFirstSet.add("John");

		Set<String> unmodifiableSet = Set.copyOf(generateStringNames());
		// var unmodifiableSet2 = Set.<String>of("Yiannis", "George", "Mary", "Sofia", "Christos");

		// Will not work, will raise an exception
		// unmodifiableSet.add("Costas");

		for (String item : unmodifiableSet) {
			System.out.println(item);
		}
	}

	private static Set<String> generateStringNames() {
		Set<String> generatedNames = new HashSet<>();
		generatedNames.add("Yiannis");
		generatedNames.add("George");
		generatedNames.add("Costas");
		generatedNames.add("Mary");
		return generatedNames;
	}

	private static Set<String> generateContent(int howMany) {
		Set<String> generatedNames = new HashSet<>();
		for (int i = 0; i < howMany; i++) {
			generatedNames.add(generator.getFirstName());
		}
		return generatedNames;
	}
}
