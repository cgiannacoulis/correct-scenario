package org.example.generics;

import java.util.ArrayList;
import java.util.List;

public class GenericDemo {
	public static void main(String[] args) {
		GenericDemo genericDemo = new GenericDemo();
		genericDemo.genericProblem();
		genericDemo.upperBound();
		genericDemo.lowerBound();
	}

	public void genericProblem(){
		List list = new ArrayList();
		list.add("Hello");
		list.add("World");
		list.add(5);

		for (Object item : list) {
			String str = (String)item;//run time error when casting the integer
			System.out.println("The item: " + str);
		}

		List<String> listString = new ArrayList<>();
		listString.add("Hello");
		//listString.add(Integer.valueOf(5)); compiler error
	}

	public void upperBound(){
		List<Integer> integerList = new ArrayList<>();
		integerList.add(5);
		integerList.add(15);
		integerList.add(10);
		System.out.println("The average of integer list is: "+calculateAverage(integerList));

		List<Double> doubleList = new ArrayList<>();
		doubleList.add(12.5);
		doubleList.add(15.5);
		doubleList.add(2.5);
		System.out.println("The average of double list is: "+calculateAverage(doubleList));

		/*
		List<String> strings = new ArrayList<>();
		calculateAverage(strings); compile-time error
		 */
	}

	private double calculateAverage(List<? extends Number> numbers){
		if (!numbers.isEmpty()){
			return numbers
					.stream()
					.mapToDouble(Number::doubleValue)
					.average()
					.getAsDouble();
		}
		return 0;
	}

	public void lowerBound(){
		List<Integer> integerList = new ArrayList<>();
		integerList.add(5);

		List<Number> numberList = new ArrayList<>();
		numberList.add(5);
		numberList.add(2.5);

		List<Double> doubleList = new ArrayList<>();
		doubleList.add(2.5);

		List<? super Integer> lowerBoundList;
		lowerBoundList = integerList;
		lowerBoundList = numberList;
		//lowerBoundList = doubleList; compile-time error
	}
}
