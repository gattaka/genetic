package cz.gattserver.genetics;

public class Utils {

	public static Item evaluateFitness(int creature, int maxWeight, Item[] items) {
		int weight = 0;
		int price = 0;
		for (int item = 0; item < items.length; item++) {
			int pos = 1 << item;
			if ((pos & creature) > 0) {
				weight += items[item].getWeight();
				if (weight > maxWeight)
					return null;
				price += items[item].getPrice();
			}
		}
		return new Item(weight, price);
	}

	public static String formatNumber(int number, int places) {
		return String.format("%" + places + "s", Integer.toBinaryString(number)).replace(' ', '0');
	}

}
