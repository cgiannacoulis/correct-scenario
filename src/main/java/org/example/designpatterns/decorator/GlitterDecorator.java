package org.example.designpatterns.decorator;

public class GlitterDecorator extends FlowerBouquet {
	FlowerBouquet flowerBouquet;

	public GlitterDecorator(FlowerBouquet flowerBouquet) {
		this.flowerBouquet = flowerBouquet;
	}

	public String getDescription() {
		return flowerBouquet.description + ", glitter";
	}

	@Override
	public double cost() {
		return flowerBouquet.cost() + 3;
	}
}
