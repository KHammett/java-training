import java.lang.reflect.Array;
import java.util.Comparator;

import by.gsu.epamlab.*;

public class Runner {
	/**
	 * @param args
	 */
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		final String TERMINATE_WARNING = "Program will be terminated";
		final int FIRST_FILE_ARGS_POSITION = 0;
		final int SECOND_FILE_ARGS_POSITION = 1;
		final int COMPARATOR_ARGS_POSITION = 2;
		final int TOTAL_ARGS = 3;
		final int INITIAL = 0;

		if (Array.getLength(args) < TOTAL_ARGS) {
			System.out.println("Expected " + TOTAL_ARGS + " arguments, but "
					+ Array.getLength(args) + " given.");
			return;
		}
		
		final String FIRST_FILE = args[FIRST_FILE_ARGS_POSITION];
		final String SECOND_FILE = args[SECOND_FILE_ARGS_POSITION];
		final String COMPARATOR_VERSION = "by.gsu.epamlab.comparators." + 
				args[COMPARATOR_ARGS_POSITION];

		PurchaseList firstList = new PurchaseList(FIRST_FILE);
		if (firstList.size() == 0) {
			System.err.println ("Cant read main data file!\n" + 
					TERMINATE_WARNING);
			return;
		}
		
		PurchaseList secondList = new PurchaseList(SECOND_FILE);
		if (secondList.size() == 0) {
			System.err.println ("Cant read addon data file!\n" + 
					TERMINATE_WARNING);
			return;
		}
		
		firstList.printTable();

		firstList.insert(INITIAL, secondList.getReverse(INITIAL));
		firstList.insert(1000, secondList.get(INITIAL));
		firstList.insert(2, secondList.get(2));
		firstList.remove(3);
		firstList.remove(10);
		firstList.remove(-5);

		firstList.printTable();

		Comparator<Purchase> comparator = null;
		try {
			comparator = (Comparator<Purchase>) Class.forName(
					COMPARATOR_VERSION).newInstance();
			firstList.sort(comparator);
		} catch (Exception e) {
			System.err.println("Can't load comparator!\n  Classname: " + 
					e.getMessage() +
					"\n" + TERMINATE_WARNING);
			return;
		}
		
		firstList.sort(comparator);

		firstList.printTable();

		firstList.search(secondList, comparator, 1);
		firstList.search(secondList, comparator, 3);

	}

}
