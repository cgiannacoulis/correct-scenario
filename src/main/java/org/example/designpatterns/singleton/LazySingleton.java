package org.example.designpatterns.singleton;

public class LazySingleton {
	private static LazySingleton instance = null;

	public static LazySingleton getInstance() {
		if (instance == null) {
			instance = new LazySingleton();
		}
		return instance;
	}
}
