package org.example.io;

import java.io.File;
import java.util.Date;

public class MainFile {
	public static void main(String[] args) {
		File dataFiles = new File(Directory.FILE_DIRECTORY.getPath());
		//listFileAttributes(dataFiles);
		listSubFiles(dataFiles);

	}

	private static void listFileAttributes(File file) {
		System.out.println(file.getName());
		System.out.println(file.getAbsolutePath());
		System.out.println(file.canWrite());
		System.out.println(file.canRead());
		System.out.println(file.isFile());
		System.out.println(file.isDirectory());
		System.out.println(new Date(file.lastModified()));
		System.out.println(file.length());
	}

	private static void listSubFiles(File file) {
		String[] list = file.list();
		for (int i = 0; i < list.length; i++) {
			File f = new File(Directory.FILE_DIRECTORY.getPath(), list[i]);
			if (f.isDirectory()) {
				System.out.println(f.getName());
			}
		}
	}
}
