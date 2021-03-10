package org.example.serialization;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ObjectSerialization<T> {

	public void writeObjectToFile(T object, String path) {
		try (OutputStream outputStream = new FileOutputStream(path + "object.txt");
			 ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream)) {
			objectOutputStream.writeObject(object);
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}

	public T readObjectFromFile(String path) {
		T object = null;
		try (InputStream inputStream = new FileInputStream(path + "object.txt");
			 ObjectInputStream objectInputStream = new ObjectInputStream(inputStream)) {
			object = (T) objectInputStream.readObject();
		} catch (IOException | ClassNotFoundException ioException) {
			ioException.printStackTrace();
		}
		return object;
	}

	public void writeMultipleObjects(List<T> data, String path) {
		try (OutputStream outputStream = new FileOutputStream(path + "objects.txt");
			 ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream)) {
			for (T datum : data) {
				objectOutputStream.writeObject(datum);
			}
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}

	public List<T> readObjectsFromFile(String path) {
		List<T> data = new ArrayList<>();
		try (InputStream inputStream = new FileInputStream(path + "objects.txt");
			 ObjectInputStream objectInputStream = new ObjectInputStream(inputStream)) {
			while (true) {
				T datum = (T) objectInputStream.readObject();
				data.add(datum);
			}
		} catch (EOFException e) {
			System.out.println("Reached end of file (EOF)");
		} catch (IOException | ClassNotFoundException ioException) {
			ioException.printStackTrace();
		}
		return data;
	}

	public void writeList(List<T> data, String path) {
		try (OutputStream outputStream = new FileOutputStream(path + "list.txt");
			 ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream)) {
			objectOutputStream.writeObject(data);
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}

	public List<T> readList(String path) {
		List<T> data = new ArrayList<>();
		try (InputStream inputStream = new FileInputStream(path + "list.txt");
			 ObjectInputStream objectInputStream = new ObjectInputStream(inputStream)) {
			data = (List<T>) objectInputStream.readObject();
		} catch (IOException | ClassNotFoundException ioException) {
			ioException.printStackTrace();
		}
		return data;
	}
}
