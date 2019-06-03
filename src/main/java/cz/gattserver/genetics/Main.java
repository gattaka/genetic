package cz.gattserver.genetics;

public class Main {

	public static void main(String[] args) {

		// 1056430717 (111110111101111101101001111101) price: 2875 weight: 393
		int maxWeight = 400;
		String data = "22 61 28 24 1 31 6 73 38 92 5 168 11 65 20 4 46 54 3 165 32 17 14 251 42 146 35 45 33 147 21 108 4 211 15 78 8 216 40 59 39 235 2 152 17 187 9 9 44 3 16 40 12 72 43 67 7 175 25 126";

		// int maxWeight = 100;
		// String data = "27 38 2 86 41 112 1 0 25 66 1 97 34 195 3 85 50 42 12 223";

		String[] tuples = data.split(" ");
		if (tuples.length % 2 != 0)
			throw new IllegalStateException("Zadání neobsahuje dvojice Cena váha (lichý počet)");

		int tuplesCountLimit = Integer.SIZE;
		if (tuples.length / 2 > tuplesCountLimit)
			throw new IllegalStateException(
					"Počet položek (" + tuples.length / 2 + ") přesahuje maximální hodnotu (" + tuplesCountLimit + ")");

		Item[] items = new Item[tuples.length / 2];
		for (int i = 0; i < tuples.length / 2; i++)
			items[i] = new Item(Integer.parseInt(tuples[i * 2]), Integer.parseInt(tuples[i * 2 + 1]));

		System.out.println("Items: " + items.length);
		System.out.println("Max weight: " + maxWeight);

		// Nové maximum:
		new Genetic().solve(items, maxWeight);
		// new BruteForce().solve(items, maxWeight);

		System.out.println("Done");
	}

}
