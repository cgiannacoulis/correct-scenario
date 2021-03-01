package org.example;

import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;

import java.util.*;

public class ListDemo {
	private static final Lorem generator = LoremIpsum.getInstance();

	public static void main(String[] args) {
		//demoDefaultList();
		demoLinkedList();
	}

	private static void demoLinkedList() {
		LinkedList<String> firstList = new LinkedList<>();
		firstList.add("A");
		firstList.add("B");
		firstList.add("D");
		firstList.add("E");
		firstList.add("J");

		firstList.add(2, "C");
		firstList.set(5, "F");
		firstList.add(1, "W");
		firstList.add("7");
		printListContents(firstList);

		firstList.remove("W");
		firstList.remove(firstList.size() - 1);
		printListContents(firstList);

		firstList.removeFirst();
		firstList.removeLast();
		printListContents(firstList);

		firstList.add("P");
		firstList.set(2, "P");
		printListContents(firstList);

		firstList.removeFirstOccurrence("P");
		printListContents(firstList);

		firstList.clear();

		firstList.addAll(generateContent(10));
		firstList.sort(null);
		printListContents(firstList);

		System.out.printf("Peek this element '%s' from list (Does not affect the list).", firstList.peek());
		System.out.println();
		System.out.printf("Poll this element '%s' from list (Affects the list by removing 1st element.",
				firstList.poll());
		System.out.println();
		System.out
				.printf("Pops this element '%s' from list (Affects the list by removing 1st element.", firstList.pop());
		System.out.println();
		System.out.printf("Offer this element '%s' from list (Affects the list by adding an element.",
				firstList.offer("Ioannis"));
		System.out.println();
		System.out.printf("Offer first this element '%s' from list (Affects the list by adding an element in the head.",
				firstList.offerFirst("Christos"));
		System.out.println();
		printListContents(firstList);
	}

	private static void demoDefaultList() {
		List<String> firstList = Arrays.asList("First content", "Second Content");
		printListContents(firstList);
		// Won't work
		// firstList.add("Third content");

		firstList.set(0, "New first content");
		firstList.set(1, "New second content");
		printListContents(firstList);

		// ArrayList is the default List implementation
		List<String> secondList = new ArrayList<>();
		secondList.add("First");
		secondList.add(null);
		secondList.add(null);
		secondList.add("First");
		printListContents(secondList);

		//Unmodifiable lists
		List<String> unmodifiableList1 = List.of("one", "two");
		printListContents(unmodifiableList1);
		List<String> unmodifiableList2 = List.copyOf(generateContent(5));
		printListContents(unmodifiableList2);

		// Cannot be sorted as it is immutable
		// List<String> targetList = List.of("Yiannis", "Ioanna", "Costas","costas","yiannis", "mary");
		List<String> targetList = generateContent(10);
		printListContents(targetList);
		targetList.sort(String::compareToIgnoreCase);
		printListContents(targetList);

		targetList.removeIf(s -> s.toLowerCase().contains("a"));
		printListContents(targetList);

		// Sorting using Collections utility class
		Collections.sort(targetList);

		// List convention to arrays
		String[] stringArray = new String[targetList.size()];
		targetList.toArray(stringArray);

		List<String> emptyList = Collections.emptyList();
	}

	private static void printListContents(List<String> targetList) {
		for (String name : targetList) {
			System.out.println(name);
		}
		System.out.println();
	}

	private static List<String> generateStringNames() {
		List<String> generatedNames = new ArrayList<>();
		generatedNames.add("Yiannis");
		generatedNames.add("George");
		generatedNames.add("Costas");
		generatedNames.add("Mary");
		return generatedNames;
	}

	private static List<String> generateContent(int howMany) {
		List<String> generatedNames = new ArrayList<>();
		for (int i = 0; i < howMany; i++) {
			generatedNames.add(generator.getFirstName());
		}
		return generatedNames;
	}
}
