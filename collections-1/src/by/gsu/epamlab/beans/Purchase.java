package by.gsu.epamlab.beans;

import by.gsu.epamlab.exceptions.NonpositiveArgumentException;

public class Purchase {
	private String commodityName;
	private int price;
	private int quantity;

	public Purchase() {
		super();
	}

	public Purchase(String commodityName, int price, int quantity) {
		super();
		setCommodityName(commodityName);
		setPrice(price);
		setQuantity(quantity);
	}

	public String getCommodityName() {
		return commodityName;
	}

	public void setCommodityName(String commodityName) {
		if ("".equals(commodityName)) {
			throw new IllegalArgumentException("Empty string field!");
		}
		this.commodityName = commodityName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		if (price < 0) {
			throw new NonpositiveArgumentException(price, "price field");
		}
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		if (quantity < 0) {
			throw new NonpositiveArgumentException(quantity, "quantity field");
		}
		this.quantity = quantity;
	}

	public int getCost() {
		return price * quantity;
	}

	@Override
	public String toString() {
		return commodityName + ";" + price + ";" + quantity + ";" + getCost();
	}

	public boolean equals(Purchase other) {
		if (this == other)
			return true;
		if (other == null)
			return false;
		if (other instanceof Purchase) {
			Purchase temp = (Purchase) other;
			return this.commodityName.equals(temp.commodityName)
					&& this.price == temp.price;
		} else
			return false;
	}

}
