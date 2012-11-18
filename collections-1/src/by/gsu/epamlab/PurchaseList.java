package by.gsu.epamlab;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class PurchaseList {
	final static String EXTENSION = ".csv";
	final static String SEMICOLON = ";";
	final static String PATH_PREFIX = "src/";
	List<Purchase> purchases = new ArrayList<Purchase>();

	public PurchaseList() {
		super();
	}

	public PurchaseList(String filename) {
		super();
		try {
			Scanner sc = new Scanner(new FileReader(PATH_PREFIX + filename
					+ EXTENSION));

			String line;
			Purchase purchase;

			while (sc.hasNext()) {
				line = sc.nextLine();
				if ((purchase = PurchaseFactory.getClassFromFactory(line)) != null) {
					purchases.add(purchase);
				}
			}

		} catch (FileNotFoundException e) {
			System.err.println("File not found: " + e.getMessage());
		}
	}

	public List<Purchase> getPurchases() {
		return purchases;
	}

	public void setPurchases(List<Purchase> purchases) {
		this.purchases = purchases;
	}

	public void print() {
		for (Purchase purchase : purchases) {
			System.out.println(purchase);
		}
	}

	public void add(Purchase purchase) {
		if (purchase != null) {
			purchases.add(purchase);
		}
	}

	public void insert(int index, Purchase purchase) {
		if (purchase != null) {
			if (index > purchases.size()) {
				index = purchases.size();
			}
			try {
				purchases.add(index, purchase);
			} catch (IndexOutOfBoundsException e) {
				System.err.println(e.getMessage());
			}
		}
	}

	public void remove(int index) {
		try {
			purchases.remove(index);
		} catch (IndexOutOfBoundsException e) {
			System.err.println(e.getMessage());
		}
	}

	public int getTotalCost() {
		int cost = 0;
		for (Purchase purchase : purchases) {
			cost += purchase.getCost();
		}
		return cost;
	}

	public void printTable() {
		final String SEMICOLON = ";";
		final String TABULATION = "\t";
		final String TABLE_HEAD = "\nName;Price;Count;Cost;Discount";
		System.out.println(TABLE_HEAD.replaceAll(SEMICOLON, TABULATION));

		for (Purchase purchase : purchases) {
			System.out.println(purchase.toString().replaceAll(SEMICOLON,
					TABULATION));
		}

		System.out.println("Total cost: \t" + getTotalCost());
	}

	public Purchase get(int index) {
		try {
			return purchases.get(index);
		} catch (IndexOutOfBoundsException e) {
			System.err.println(e.getMessage());
		}
		return null;
	}

	public Purchase getReverse(int index) {
		try {
			return purchases.get(purchases.size() - index - 1);
		} catch (IndexOutOfBoundsException e) {
			System.err.println(e.getMessage());
		}
		return null;
	}
	
	public int size() {
		return purchases.size();
	}

	public void sort(Comparator<Purchase> comparator) {
		Collections.sort(purchases, comparator);
	}

	public void search(PurchaseList otherList, Comparator<Purchase> comparator, int index) {
		int position = -1;
		if (index < otherList.size()) {
			try {
				position = Collections.binarySearch(purchases,
						otherList.get(index), comparator);
			} catch (ClassCastException e) {
				System.err.println("Error: " + e.getMessage());
			}
			System.out.print("Element \"" + otherList.getPurchases().get(index) + "\" ");
			System.out.println ((position > 0)?("found at position " + position):("not found"));
			return;
		}
		System.out.println("Element with index " + index + " does not exist.");
	}
}
