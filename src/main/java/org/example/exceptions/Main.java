package org.example.exceptions;

public class Main {
	public static void main(String[] args) {

	}

	public static void method1() throws NegativeNumberException {
		int number = -5;

		if (number < 0) {
			throw new NegativeNumberException("User used negative number.");
		}
	}
}
