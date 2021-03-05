package org.example.io;

import java.io.*;
import java.util.Scanner;

public class MainStandard {

	public static void main(String[] args) {
		//readInputWithBufferedReader();
		//readInputWithScanner();
		//readFileWithScanner();
		readInputWithConsole();
	}

	private static void readInputWithBufferedReader() {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			System.out.println("Hello, what is your name?");
			String answer = br.readLine();
			System.out.println(answer);
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}

	private static void readInputWithScanner() {
		try (Scanner scanner = new Scanner(System.in)) {
			System.out.println("Hello, where do you live?");
			String answer = scanner.nextLine();
			System.out.println(answer);
		}
	}

	private static void readFileWithScanner() {
		try (Reader reader = new FileReader(Directory.FILE_DIRECTORY.getPath() + "errorslogs.txt");
			 Scanner scanner = new Scanner(reader)) {
			while (scanner.hasNext()) {
				System.out.println(scanner.nextLine());
			}
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}

	private static void readInputWithConsole() {
		System.out.println("What is your OS?");
		String answer = System.console().readLine();
		System.out.println(answer);
	}
}
