package org.example.nestedclass;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {
	public static void main(String[] args) {
		// static nested class example
		OuterClass.InnerStaticClass innerStaticClass = new OuterClass.InnerStaticClass();
		innerStaticClass.testMethod();

		OuterClass.InnerStaticClass.testStaticMethod();

		// inner class example
		OuterClass outerClass = new OuterClass();
		OuterClass.InnerClass innerClass = outerClass.new InnerClass();
		innerClass.testMethod();

		// local class example
		LocalClassExample localClassExample = new LocalClassExample();
		localClassExample.testMethod();

		List<String> myList = new ArrayList<>();
		myList.add("Caki");
		myList.add("Ioannis");
		myList.add("Waki");
		myList.add("Ada");
		sortStrings(myList);

		for (String name : myList) {
			System.out.println(name);
		}
	}

	private static void sortStrings(List<String> stringList) {
		stringList.sort(new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		});
	}
}
