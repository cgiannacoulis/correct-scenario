package org.example.designpatterns.builder;

public class House {
	private String foundation;
	private String roof;
	private String colour;
	private boolean furnished;
	private boolean brandNew;

	public House(String foundation, String roof, String colour, boolean furnished, boolean brandNew) {
		this.foundation = foundation;
		this.roof = roof;
		this.colour = colour;
		this.furnished = furnished;
		this.brandNew = brandNew;
	}

	private House(Builder builder) {
		setFoundation(builder.foundation);
		setRoof(builder.roof);
		setColour(builder.colour);
		setFurnished(builder.furnished);
		setBrandNew(builder.brandNew);
	}

	public String getFoundation() {
		return foundation;
	}

	public void setFoundation(String foundation) {
		this.foundation = foundation;
	}

	public String getRoof() {
		return roof;
	}

	public void setRoof(String roof) {
		this.roof = roof;
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	public boolean isFurnished() {
		return furnished;
	}

	public void setFurnished(boolean furnished) {
		this.furnished = furnished;
	}

	public boolean isBrandNew() {
		return brandNew;
	}

	public void setBrandNew(boolean brandNew) {
		this.brandNew = brandNew;
	}

	public static final class Builder {
		private String foundation;
		private String roof;
		private String colour;
		private boolean furnished;
		private boolean brandNew;

		public Builder() {
		}

		public Builder withFoundation(String val) {
			foundation = val;
			return this;
		}

		public Builder withRoof(String val) {
			roof = val;
			return this;
		}

		public Builder withColour(String val) {
			colour = val;
			return this;
		}

		public Builder withFurnished(boolean val) {
			furnished = val;
			return this;
		}

		public Builder withBrandNew(boolean val) {
			brandNew = val;
			return this;
		}

		public House build() {
			return new House(this);
		}
	}
}
