package org.example.serialization;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class XMLService<T> {

	public void serializeToXML(List<T> data, String path) {
		try (OutputStream outputStream = new FileOutputStream(path + "xml.txt");
			 XMLEncoder encoder = new XMLEncoder(outputStream)) {
			encoder.setExceptionListener(e -> e.printStackTrace());
			encoder.writeObject(data);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}

	public List<T> deserializeFromXML(String path) {
		List<T> data = new ArrayList<>();
		try (InputStream inputStream = new FileInputStream(path + "xml.txt");
			 XMLDecoder decoder = new XMLDecoder(inputStream)) {
			data = (List<T>) decoder.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}
}
