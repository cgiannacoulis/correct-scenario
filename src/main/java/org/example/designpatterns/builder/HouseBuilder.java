package org.example.designpatterns.builder;

public class HouseBuilder {
	private String foundation;
	private String roof;
	private String colour;
	private boolean furnished;
	private boolean brandNew;

	public HouseBuilder(String roof, String colour) {
		this.roof = roof;
		this.colour = colour;
	}

	public HouseBuilder setFoundation(String foundation) {
		this.foundation = foundation;
		return this;
	}

	public HouseBuilder setRoof(String roof) {
		this.roof = roof;
		return this;
	}

	public HouseBuilder setColour(String colour) {
		this.colour = colour;
		return this;
	}

	public HouseBuilder setFurnished(boolean furnished) {
		this.furnished = furnished;
		return this;
	}

	public HouseBuilder setBrandNew(boolean brandNew) {
		this.brandNew = brandNew;
		return this;
	}

	public House createHouse() {
		return new House(foundation, roof, colour, furnished, brandNew);
	}
}
