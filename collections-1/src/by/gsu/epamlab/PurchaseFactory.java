package by.gsu.epamlab;

import java.lang.reflect.Array;

public class PurchaseFactory {
	private final static int GENERAL_PURCHASE_ARGC = 3;
	private final static int DISCOUNT_PURCHASE_ARGC = 4;
	private final static int NAME_POSITION = 0;
	private final static int PRICE_POSITION = 1;
	private final static int QUANTITY_POSITION = 2;
	private final static int DISCOUNT_POSITION = 3;
	
	public static Purchase getClassFromFactory(String line) {
		String[] items = line.split(";");
		try {
			String name = items[NAME_POSITION];
			int price = Integer.parseInt(items[PRICE_POSITION]);
			int quantity = Integer.parseInt(items[QUANTITY_POSITION]);
			
			if ("".equals(name) || price < 0 || quantity < 0) {
				System.err.println (		
				"Illegal format of numeric or string constant in line: " + line);
				return null;
			}
			
			switch (Array.getLength(items)) {
			case GENERAL_PURCHASE_ARGC:
				return new Purchase(name, price, quantity);

			case DISCOUNT_PURCHASE_ARGC:
				int discount = Integer.parseInt(items[DISCOUNT_POSITION]);
				if ((price * quantity - discount) <= 0 || discount <= 0) {
					System.err.println (		
						"Discount seems to be incorrect in line: " + line);
					return null;
				}
				return new PriceDiscountPurchase(name, price, quantity,
						discount);

			default:
				System.err.println (		
						"Wrong argument count in line: " + line);
				return null;
			}
			
		} catch (IllegalArgumentException e) {
			System.err.println("Constant don't match expected type in line: " + line);
		} catch (ArrayIndexOutOfBoundsException e) {
			System.err.println("Argument expected in line: " + line);
		}
		
		return null;
	}
}