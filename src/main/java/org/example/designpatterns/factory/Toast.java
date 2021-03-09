package org.example.designpatterns.factory;

public abstract class Toast {
	private int quality;

	public abstract void addIngredients();

	public void heatToast() {
		System.out.println("Heating toast");
	}

	public int getQuality() {
		return quality;
	}

	public void setQuality(int quality) {
		this.quality = quality;
	}
}
