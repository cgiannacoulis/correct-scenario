package org.example.designpatterns.decorator;

public class RoseBouquet extends FlowerBouquet {

	public RoseBouquet() {
		description = "Rose Bouquet";
	}

	@Override
	public double cost() {
		return 12;
	}
}
