package by.gsu.epamlab;

import java.lang.reflect.Array;
import by.gsu.epamlab.beans.*;
import by.gsu.epamlab.exceptions.*;

public class PurchaseFactory {
	private final static int GENERAL_PURCHASE_ARGC = 3;
	private final static int DISCOUNT_PURCHASE_ARGC = 4;
	private final static int NAME_POSITION = 0;
	private final static int PRICE_POSITION = 1;
	private final static int QUANTITY_POSITION = 2;
	private final static int DISCOUNT_POSITION = 3;

	public static Purchase getClassFromFactory(String line) throws CSVLineException {
		String[] items = line.split(";");
		try {
			String name = items[NAME_POSITION];
			int price = Integer.parseInt(items[PRICE_POSITION]);
			int quantity = Integer.parseInt(items[QUANTITY_POSITION]);

			switch (Array.getLength(items)) {
			case GENERAL_PURCHASE_ARGC:
				return new Purchase(name, price, quantity);

			case DISCOUNT_PURCHASE_ARGC:
				int discount = Integer.parseInt(items[DISCOUNT_POSITION]);
				if ((price * quantity - discount) <= 0) {
					throw new CSVLineException(line,
							"Discount seems to be incorrect.");
				}
				return new PriceDiscountPurchase(name, price, quantity,
						discount);

			default:
				throw new CSVLineException(line, "Wrong argument count.");
			}

		} catch (CSVLineException e) {
			System.err.println(e.getMessage());
		} catch (NonpositiveArgumentException e) {
			System.err.println(e.getMessage());
		} catch (IllegalArgumentException e) {
			System.err.println("Constant doesn't match expected type.\n\tLine: "
					+ line);
		} catch (ArrayIndexOutOfBoundsException e) {
			System.err.println("Argument expected!\n\tLine: " + line);
		}

		return null;
	}
}