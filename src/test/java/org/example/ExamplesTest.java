package org.example;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class ExamplesTest {

	@Test
	void example1() {
		fail("This test will fail.");
	}

	@Test
	void example2() {
		int expected = 10;
		int actual = 5;

		assertEquals(expected, actual, "The two numbers should be equal");
	}

	@Test
	void example3() {
		boolean[] expected = {true, true, true};
		boolean[] actual = {true, true, true};

		assertArrayEquals(expected, actual);
	}

	@Test
	void example4() {
		Exception exception = assertThrows(ArithmeticException.class,()->{int division = 1/0;});
		assertEquals("/ by zero", exception.getMessage());
	}

	@Test
	@DisplayName("This test is the example 5")
	void example5() {
		assertTimeout(Duration.ofMillis(1),()->{
			Random random = new Random();
			int randomInt = random.nextInt(100);
			int[] myArray = new int[randomInt];
			for (int i = 0; i < myArray.length; i++) {
				myArray[i] = random.nextInt(100);
			}
		});
	}

	@Test
	@Disabled("Implement when a functionality is ready")
	@DisplayName("This test is disabled")
	void example6() {
		fail();
	}

	@Test
	@DisplayName("This test is disabled")
	@DisabledOnOs(OS.WINDOWS)
	void example7() {
		fail();
	}

	@RepeatedTest(3)
	@DisplayName("This test will be repeated 3 times")
	void example8(RepetitionInfo repetitionInfo) {
		System.out.println("Current repetition is: "+repetitionInfo.getCurrentRepetition());
		Random random = new Random();
		assertEquals(1,random.nextInt(3));
	}

	@ParameterizedTest
	@ValueSource(ints = {1, 4, 7, 8})
	@DisplayName("This is a parameterized test")
	void example9(int value) {
		assertEquals(0, value % 2, value + " is not even");
	}

	@ParameterizedTest
	@MethodSource("numberOfUsers")
	@DisplayName("This a parameterized test with method as input")
	void example10(int number, List<String> userList) {
		assertEquals(number,userList.size());
	}

	private static Stream<Arguments> numberOfUsers(){
		return  Stream.of(
				arguments(1, Arrays.asList("User 1")),
				arguments(2, Arrays.asList("User 1","User 2")),
				arguments(3, Arrays.asList("User 1","User 2","User 3"))
				);
	}
}
