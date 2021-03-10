package org.example.generics;

import java.util.ArrayList;
import java.util.List;

public class GenericClass<T extends Number> {
	private List<T> myNumbers;

	public GenericClass() {
		myNumbers = new ArrayList<>();
	}

	public void addNumber(T number){
		myNumbers.add(number);
	}

	public T getNumber(int index){
		return myNumbers.get(index);
	}

	public boolean isEqual(T t1, T t2){
		return t1.equals(t2);
	}

	public static void main(String[] args) {
		GenericClass<Integer> integerGenericClass = new GenericClass<>();
		integerGenericClass.addNumber(5);
		integerGenericClass.addNumber(50);
		integerGenericClass.addNumber(25);

		//String str = integerGenericClass.getNumber(0); compile-time error
	}
}
