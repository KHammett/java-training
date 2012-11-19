package by.gsu.epamlab.comparators;

import java.util.Comparator;
import by.gsu.epamlab.beans.*;

public class PurchaseComparatorV1 implements Comparator<Purchase> {

	@Override
	public int compare(Purchase purchase1, Purchase purchase2) {
		String name1 = purchase1.getCommodityName();
		String name2 = purchase2.getCommodityName();
		if (name1.equals(name2)) {
			if (purchase1 instanceof Purchase != purchase2 instanceof PriceDiscountPurchase) {
				if (!(purchase1 instanceof PriceDiscountPurchase)) {
					return -1;
				}
				return 1;
			}
			return purchase1.getCost() - purchase2.getCost();
		}
		return name1.compareTo(name2);
	}
}