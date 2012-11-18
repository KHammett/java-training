package by.gsu.epamlab;


public class Purchase {
	private String commodityName;
	private int price;
	private int quantity;

	public Purchase() {
		super();
	}

	public Purchase(String commodityName, int price, int quantity) {
		super();
		this.commodityName = commodityName;
		this.price = price;
		this.quantity = quantity;
	}

	public String getCommodityName() {
		return commodityName;
	}

	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		if (price < 0) {
			throw new IllegalArgumentException();
		}
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		if (quantity < 0) {
			throw new IllegalArgumentException();
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
		// TODO @Override?
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
