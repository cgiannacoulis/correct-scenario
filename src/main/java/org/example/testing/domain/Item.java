package org.example.testing.domain;

import java.math.BigDecimal;
import java.util.Objects;

public class Item {
	private String name;
	private BigDecimal price;
	private Integer quantity;

	public Item(String name, BigDecimal price, Integer quantity) {
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Item{" + "name='" + name + '\'' + ", price=" + price + ", quantity=" + quantity + '}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Item item = (Item) o;
		return Objects.equals(name, item.name) && Objects.equals(price, item.price);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, price);
	}
}
