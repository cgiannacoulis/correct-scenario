package org.example.designpatterns.singleton;

public class Main {
	public static void main(String[] args) {
		LazySingleton instance = LazySingleton.getInstance();
		LazySingleton.getInstance();
	}
}
