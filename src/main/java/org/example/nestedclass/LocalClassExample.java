package org.example.nestedclass;

public class LocalClassExample {

	public void testMethod() {
		String name = "Ioannis";
		class LocalClass {
			public void localClassTestMethod() {
				System.out.println(name);
				System.out.println("hello from local class");
			}
		}
		LocalClass localClass = new LocalClass();
		localClass.localClassTestMethod();
	}
}
