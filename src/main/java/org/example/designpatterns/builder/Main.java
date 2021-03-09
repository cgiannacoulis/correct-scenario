package org.example.designpatterns.builder;

public class Main {
	public static void main(String[] args) {
		House myHouse = new HouseBuilder("Ceramic", "Red").setFoundation("Concrete").setBrandNew(false)
				.setFurnished(true).createHouse();

		House myHouse2 = new House.Builder().withBrandNew(false).build();

	}
}
