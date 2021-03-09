package org.example.designpatterns.adapter;

public class Main {
	public static void main(String[] args) {
		CSVFormattable csvFormattable = new CSVFormatter();
		csvFormattable.formatText("...");
		TextFormattable textFormattable = new TextFormatter();
		textFormattable.formatText("...");

		TextFormattable csvAdapter = new CSVAdapter(new CSVFormatter());
	}
}
