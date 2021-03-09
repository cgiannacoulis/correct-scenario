package org.example.designpatterns.decorator;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class Main {
	public static void main(String[] args) throws FileNotFoundException {
		FlowerBouquet roses = new RoseBouquet();
		System.out.println("Description: " + roses.getDescription() + " cost: " + roses.cost());

		FlowerBouquet decoratedRoses = new GlitterDecorator(roses);
		System.out.println("Description: " + decoratedRoses.getDescription() + " cost: " + decoratedRoses.cost());

		InputStream inputStream = new FileInputStream("text..txt");
		InputStream bufferedInputStream = new BufferedInputStream(inputStream);
	}
}
