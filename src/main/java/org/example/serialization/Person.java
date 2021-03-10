package org.example.serialization;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Person implements Serializable {
	private static final long serialVersionUID = 445;
	private transient final String address = "Attiki";
	private String name;
	private int age;

	public Person() {
	}

	public Person(String name, int age, String address) {
		this.name = name;
		this.age = age;
		//this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	private void writeObject(ObjectOutputStream objectOutputStream) {
		try {
			//this.name = String.valueOf(this.name.hashCode());
			objectOutputStream.defaultWriteObject();
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}

	private void readObject(ObjectInputStream objectInputStream) {
		try {
			objectInputStream.defaultReadObject();
		} catch (IOException | ClassNotFoundException ioException) {
			ioException.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return "Person{" + "name='" + name + '\'' + ", age=" + age + ", address='" + address + '\'' + '}';
	}
}
