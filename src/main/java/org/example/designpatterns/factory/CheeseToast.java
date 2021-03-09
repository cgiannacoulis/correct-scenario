package org.example.designpatterns.factory;

public class CheeseToast extends Toast {

	private CheeseToast(int quality) {
		super.setQuality(quality);
	}

	public static CheeseToast createCheeseToast(int quality) {
		return new CheeseToast(quality);
	}

	@Override
	public void addIngredients() {
		System.out.println("Adding cheese.");
	}

}
