package org.example.designpatterns.adapter;

public class TextFormatter implements TextFormattable {
	@Override
	public String formatText(String text) {
		return text.replace(".", "\n");
	}
}
