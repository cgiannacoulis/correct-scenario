package org.example.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MainNIO {

	public static void main(String[] args) {
		//writeToFile();
		//readFromFile();
		//createDirectoryAndFile();
		deleteDirectoryAndFile();
		//Path newDirectory = Paths.get(Directory.FILE_DIRECTORY.getPath() + "newDirectory");
		//System.out.println(newDirectory);
	}

	private static void writeToFile() {
		Path directory = Paths.get(Directory.FILE_DIRECTORY.getPath() + "nioWrite.txt");
		try (BufferedWriter bw = Files.newBufferedWriter(directory)) {
			bw.write("Hello");
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}

	private static void readFromFile() {
		Path directory = Paths.get(Directory.FILE_DIRECTORY.getPath() + "nioWrite.txt");
		try (BufferedReader br = Files.newBufferedReader(directory)) {
			String line = br.readLine();
			System.out.println(line);
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}

	private static void createDirectoryAndFile() {
		Path newDirectory = Paths.get(Directory.FILE_DIRECTORY.getPath() + "newDirectory");
		try {
			Files.createDirectory(newDirectory);
			Path newFile = Paths.get(newDirectory + "sample_file.txt");
			Files.createFile(newFile);
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}

	private static void deleteDirectoryAndFile() {
		try {
			Path directory = Paths.get(Directory.FILE_DIRECTORY.getPath() + "newDirectory");
			String[] list = directory.toFile().list();
			for (String fileName : list) {
				Path filePath = Paths.get(directory + File.separator + fileName);
				Files.delete(filePath);
			}
			Files.delete(directory);
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}

	}
}
