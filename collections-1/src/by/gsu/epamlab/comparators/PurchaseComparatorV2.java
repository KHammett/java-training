package by.gsu.epamlab.comparators;

import java.util.Comparator;
import by.gsu.epamlab.*;
import by.gsu.epamlab.beans.Purchase;

public class PurchaseComparatorV2 implements Comparator<Purchase> {

	@Override
	public int compare(Purchase purchase1, Purchase purchase2) {
		String name1 = purchase1.getCommodityName();
		String name2 = purchase2.getCommodityName();
		if (name1.equals(name2)) {
			if (purchase1.getClass() != purchase2.getClass()) {
				if (purchase1.getClass() == Purchase.class) {
					return -1;
				}
				return 1;
			}
			return purchase1.getCost() - purchase2.getCost();
		}
		return name1.compareTo(name2);
	}

}