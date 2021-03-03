package org.example.io;

import java.io.*;

public class MainCharacters {
	private static final String directory =
			System.getProperty("user.home") + File.separator + "data_files" + File.separator;

	public static void main(String[] args) {
		writeStringToFile();
		readStringFromFile();
		convertStackTraceToString();
	}

	public static void writeStringToFile() {
		try (Writer out = new FileWriter(directory + "CHARACTERstringTest.txt")) {
			String word = "Hello";
			out.write(word);
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}

	public static void writeStringBufferedToFile(String message) {
		try (Writer out = new FileWriter(directory + "errorslogs.txt"); BufferedWriter bw = new BufferedWriter(out)) {
			bw.write(message);
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}

	public static void readStringFromFile() {
		try (Reader in = new FileReader(directory + "stringTest.txt")) {
			int character;
			while ((character = in.read()) != -1) {
				System.out.println((char) character);
			}
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}

	private static void convertStackTraceToString() {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		try (Reader reader = new FileReader("ausduasdna")) {

		} catch (IOException ioException) {
			ioException.printStackTrace(pw);
			String s = sw.toString();
			writeStringBufferedToFile(s);
		}
	}

}
