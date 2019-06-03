package cz.gattserver.genetics;

public class BruteForce implements Solver {

	@Override
	public int solve(Item[] items, int maxWeight) {

		int max = (int) Math.pow(2, items.length);
		System.out.println("Max: " + max + " (" + Utils.formatNumber(max, items.length) + ")");

		int maxPrice = 0;
		int solution = 0;
		for (int i = 0; i < max; i++) {
			Item result = Utils.evaluateFitness(i, maxWeight, items);
			// System.out.println("Test: " + i + " (" + Utils.formatNumber(i, items.length) + ") price: "
			// + result.getPrice() + " weight: " + result.getWeight());
			if (result != null && result.getPrice() > maxPrice) {
				maxPrice = result.getPrice();
				solution = i;
				System.out.print("\nNové maximum: " + i + " (" + Utils.formatNumber(i, items.length) + ") price: "
						+ maxPrice + " weight: " + result.getWeight() + " ");
			} else {
				System.out.print(".");
			}
		}

		return solution;
	}

}
