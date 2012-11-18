package by.gsu.epamlab.beans;

public class PriceDiscountPurchase extends Purchase {
	private int discount;

	public PriceDiscountPurchase() {
		super();
		setDiscount(discount);
	}

	public PriceDiscountPurchase(String commodityName, int price, int quantity,
			int discount) {
		super(commodityName, price, quantity);
		this.discount = discount;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		if (discount < 0) {
			throw new IllegalArgumentException();
		}
		this.discount = discount;
	}

	@Override
	public int getCost() {
		return (getPrice() - discount) * getQuantity();
	}

	@Override
	public String toString() {
		return super.toString() + ";" + discount;
	}
}
