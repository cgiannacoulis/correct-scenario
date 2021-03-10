package org.example.serialization;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {
	private static final String path = System.getProperty("user.home") + File.separator + "data_files" + File.separator;

	public static void main(String[] args) {
		List<Student> list = new ArrayList<>();
		Student person1 = new Student("Ioannis", 15, "Attiki", 5);
		Student person2 = new Student("Ioanniss", 155, "Attikiii", 10);
		Student person3 = new Student("Ioanniss", 1555, "Attikiiiii", 20);
		list.add(person1);
		list.add(person2);
		list.add(person3);
		ObjectSerialization<Student> objectSerialization = new ObjectSerialization<>();
		// Single object
//		objectSerialization.writeObjectToFile(person, path);
//		Person restoredPerson = objectSerialization.readObjectFromFile(path);
//		System.out.println(restoredPerson);

		// Multiple objects
//		objectSerialization.writeMultipleObjects(list, path);
//		List<Person> people = objectSerialization.readObjectsFromFile(path);
//		for (Person person : people) {
//			System.out.println(person);
//		}

		// Save list
		objectSerialization.writeList(list, path);
		List<Student> people = objectSerialization.readList(path);
		for (Person person : people) {
			System.out.println(person);
		}

		// Save list to XML
		XMLService<Student> xmlService = new XMLService<>();
		xmlService.serializeToXML(list, path);
		List<Student> people4 = xmlService.deserializeFromXML(path);
		for (Person person : people4) {
			System.out.println(person);
		}
	}

}
