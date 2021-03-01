package org.example;

import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;

import java.util.*;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.counting;

public class StreamDemo {
	private static final Lorem generator = LoremIpsum.getInstance();

	public static void main(String[] args) {
		// Basic methods of creating Streams
		Stream<String> emptyStream = Stream.empty();
		Stream<String> firstStream = Stream.of("Athens", "Thesalloniki", "Patra", "Iraklio", "Larisa", "Ioannina");
		Stream<String> secondStream = Pattern.compile(",").splitAsStream("a,b,c,d,e,f");
		Stream<String> thirdStream = Arrays.asList("one", "two", "three").stream();

		String[] characterArray = new String[]{"a", "b", "c", "d", "e", "f"};
		Stream<String> characterStream = Arrays.stream(characterArray);
		Stream<String> limitedCharacterStream = Arrays.stream(characterArray, 0, 2);

		// Stream Builder (trickier)
		Stream<String> streamBuilder = Stream.<String>builder().add("a").add("b").add("c").build();

		Stream<String> generatedStream = Stream.generate(() -> "sample text").limit(10);
		Stream<Integer> generatedStream2 = Stream.iterate(10, i -> 1 + 2).limit(10);

		IntStream intStream = IntStream.range(1, 101);
		LongStream longStream = LongStream.range(1, 101);

		// Joint example
		System.out.println("Joint example");
		System.out.println("---------------------------");
		List<String> digestedList = generateContent(100).stream().filter(i -> i.startsWith("A"))
				.map(String::toUpperCase).sorted().collect(Collectors.toList());
		digestedList.forEach(i -> System.out.printf("%s, ", i));
		System.out.println();

		// Foreach
		System.out.println("Foreach");
		System.out.println("---------------------------");
		generateContent().forEach(System.out::println);
		System.out.println();

		// Limit
		System.out.println("Limit");
		System.out.println("---------------------------");
		generateContent().limit(5).forEach(System.out::println);
		System.out.println();

		// Skip
		System.out.println("Skip");
		System.out.println("---------------------------");
		generateContent().skip(2).forEach(System.out::println);
		System.out.println();

		// Distinct
		System.out.println("Distinct");
		System.out.println("---------------------------");
		generateContent().distinct().forEach(System.out::println);
		System.out.println();

		// Sorted
		System.out.println("Sorted");
		System.out.println("---------------------------");
		generateContent().sorted().forEach(System.out::println);
		System.out.println();

		// Sorted customized
		System.out.println("Sorted");
		System.out.println("---------------------------");
		generateContent().sorted(Comparator.comparing(String::length).thenComparing(String::valueOf))
				.forEach(System.out::println);
		System.out.println();

		// Map
		System.out.println("Map");
		System.out.println("---------------------------");
		generateContent().sorted(Comparator.comparing(String::length).thenComparing(String::valueOf))
				.map(String::toLowerCase).forEach(System.out::println);
		System.out.println();

		// Map to Integer
		System.out.println("Map to Integer");
		System.out.println("---------------------------");
		generateContent().sorted(Comparator.comparing(String::length).thenComparing(String::valueOf))
				.mapToInt(String::length).forEach(System.out::println);
		System.out.println();

		// FlatMap
		System.out.println("FlatMap");
		System.out.println("---------------------------");
		generateContent().distinct().sorted().flatMap(s -> s.chars().mapToObj(i -> (char) i))
				.forEach(System.out::println);
		System.out.println();

		// Foreach ordered
		System.out.println("Foreach ordered");
		System.out.println("---------------------------");
		generateContent().forEachOrdered(System.out::println);
		System.out.println();

		// Collectors to Set
		System.out.println("Collectors to Set");
		System.out.println("---------------------------");
		generateContent().collect(Collectors.toSet()).forEach(System.out::println);
		System.out.println();

		// Collectors to specific implementation
		System.out.println("Collectors to specific implementation");
		System.out.println("---------------------------");
		LinkedList<String> myList = generateContent().collect(Collectors.toCollection(LinkedList::new));
		myList.forEach(System.out::println);
		System.out.println();

		// Stream to array
		System.out.println("Stream to array");
		System.out.println("---------------------------");
		String[] myStringArray = generateContent().distinct().sorted().toArray(String[]::new);
		Arrays.asList(myStringArray).forEach(System.out::println);
		System.out.println();

		// Stream to map
		System.out.println("Stream to map");
		System.out.println("---------------------------");
		generateContent().distinct().collect(Collectors.toMap(Function.identity(), s -> (int) s.chars().count()))
				.forEach((k, v) -> System.out.println("key:" + k + ", value:" + v));
		System.out.println();

		// Collect grouping by
		System.out.println("Collect grouping by");
		System.out.println("---------------------------");
		Map<Character, List<String>> groupingByMap = generateContent().distinct().sorted()
				.collect(Collectors.groupingBy(s -> s.charAt(0)));
		System.out.println(groupingByMap);
		System.out.println();

		// Collect grouping by downstream
		System.out.println("Collect grouping by downstream");
		System.out.println("---------------------------");
		Map<Character, Long> groupingByDownstreamMap = generateContent().distinct().sorted()
				.collect(Collectors.groupingBy(s -> s.charAt(0), counting()));
		System.out.println(groupingByDownstreamMap);
		System.out.println();

		// Lab target 1: Filter by string length, sort descending, print out
		System.out.println("Lab target 1");
		System.out.println("---------------------------");
		generateContent(20).stream().distinct().filter(s -> s.length() >= 5).sorted(Comparator.reverseOrder())
				.forEach(System.out::println);
		System.out.println();

		// Lab target 2: anyMatch
		System.out.println("Lab target 2");
		System.out.println("---------------------------");
		boolean foundElementsMatchingCriteria = generateContent(20).stream().anyMatch(s -> s.length() >= 4);
		System.out.println();

		/*
		 * After Java Standard Edition module conclusion, we will share a source code containing even more samples
		 * of terminal operations.
		 */
	}

	private static Stream<String> generateContent() {
		return Stream.of("John", "Costas", "Nick", "Costas", "Christos", "Mary", "Catherine", "Nick", "Mary");
	}

	private static List<String> generateContent(int howMany) {
		List<String> generatedNames = new ArrayList<>();
		for (int i = 0; i < howMany; i++) {
			generatedNames.add(generator.getFirstName());
		}
		return generatedNames;
	}
}
