package org.example.io;

import java.io.*;

public class MainBytes {

	private static final String directory =
			System.getProperty("user.home") + File.separator + "data_files" + File.separator;

	public static void main(String[] args) {
		// BYTES:
		writeBytesToFile();
		readBytesFromFile();
		writeByteStringToFile();
		readByteStringFromFile();
		writeByteBufferedToFile();
		readByteBufferedFromFile();
		copyBytesFromFileToFile();
	}

	private static void copyBytesFromFileToFile() {
		try (InputStream in = new FileInputStream(directory + "stringTest.txt");
			 OutputStream out = new FileOutputStream(directory + "stringTestCopy.txt")) {
			in.transferTo(out);
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}

	public static void writeByteBufferedToFile() {
		try (OutputStream out = new FileOutputStream(directory + "stringTest2.txt");
			 BufferedOutputStream bos = new BufferedOutputStream(out)) {
			String word = "Hello";
			bos.write(word.getBytes());

		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}

	public static void readByteBufferedFromFile() {
		try (InputStream in = new FileInputStream(directory + "stringTest2.txt");
			 BufferedInputStream bos = new BufferedInputStream(in)) {
			byte[] bytes = bos.readAllBytes();

			for (byte aByte : bytes) {
				System.out.print((char) aByte);
			}

		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}

	public static void writeByteStringToFile() {
		try (FileOutputStream out = new FileOutputStream(directory + "stringTest.txt")) {
			String word = "Hello";
			out.write(word.getBytes());
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}

	public static void readByteStringFromFile() {
		try (FileInputStream in = new FileInputStream(directory + "stringTest.txt")) {
			byte[] bytes = in.readAllBytes();
			for (byte aByte : bytes) {
				System.out.println(aByte);
			}
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}

	public static void writeBytesToFile() {
		try (FileOutputStream out = new FileOutputStream(directory + "test.txt")) {
			out.write(100);
			out.write(127);
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}

	public static void readBytesFromFile() {
		try (FileInputStream in = new FileInputStream(directory + "test.txt")) {
			byte[] bytes = in.readAllBytes();
			for (byte aByte : bytes) {
				System.out.println(aByte);
			}
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}
}
