package org.example.designpatterns.factory;

public class Main {
	public static void main(String[] args) {
		ToastFactory toastFactory = new ToastFactory();
		Toast tunaToast = toastFactory.createToast("tuna");
		Toast cheeseToast = toastFactory.createToast("");
		CheeseToast cheeseToast2 = CheeseToast.createCheeseToast(5);
	}
}
