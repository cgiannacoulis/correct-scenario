package org.example.serialization;

import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Student extends Person {
	private int grade;

	public Student() {
	}

	public Student(String name, int age, String address, int grade) {
		super(name, age, address);
		this.grade = grade;
	}

	private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
		throw new NotSerializableException();
	}

	private void readObject(ObjectInputStream objectInputStream) throws IOException {
		throw new NotSerializableException();
	}

}
