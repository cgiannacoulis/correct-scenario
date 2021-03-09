package org.example.designpatterns.adapter;

public class CSVFormatter implements CSVFormattable {
	@Override
	public String formatText(String text) {
		return text.replace(".", ",");
	}
}
