package cz.gattserver.genetics;

public class Main {

	public static void main(String[] args) {

		// int maxWeight = 40;
		// String data = "14 223 38 230 3 54 1 214 13 118 4 147 15 16 2 104 5 56 49 154 40 106 24 234 18 34 33 195 7 74
		// 10 129 12 159 42 37 41 10 11 185 6 243 45 87 32 57 20 87 9 26 16 201 39 0 23 128 39 194 21 10 46 1 8 28 30 59
		// 26 130 35 160 22 91 34 180 19 16 31 1 17 72";
		int maxWeight = 100;
		String data = "27 38 2 86 41 112 1 0 25 66 1 97 34 195 3 85 50 42 12 223";
		String[] tuples = data.split(" ");
		if (tuples.length % 2 != 0)
			throw new IllegalStateException("Zadání neobsahuje dvojice Cena váha (lichý počet)");

		Item[] items = new Item[tuples.length / 2];
		for (int i = 0; i < tuples.length / 2; i++)
			items[i] = new Item(Integer.parseInt(tuples[i * 2]), Integer.parseInt(tuples[i * 2 + 1]));

		System.out.println("Items: " + items.length);

		// Nové maximum:
		new Genetic().solve(items, maxWeight);
		// new BruteForce().solve(items, maxWeight);
	}

}
