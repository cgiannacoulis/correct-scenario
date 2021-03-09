package org.example.designpatterns.factory;

public class ToastFactory {

	public Toast createToast(String type) {
		Toast toast = null;
		if (type.equals("tuna")) {
			toast = new TunaToast();
		} else {
			//toast = CheeseToast.createCheeseToast();
		}
		//toast.addIngredients();
		//toast.heatToast();
		return toast;
	}
}
