package cz.gattserver.genetics;

public class BruteForce implements Solver {

	@Override
	public Solution solve(Item[] items, int maxWeight) {

		int max = (int) Math.pow(2, items.length);
		System.out.println("Max: " + max + " (" + Utils.formatNumber(max, items.length) + ")");

		Solution solution = new Solution(0, 0, 0);
		for (int i = 0; i < max; i++) {
			Item result = Utils.evaluateFitness(i, maxWeight, items);
			// System.out.println("Test: " + i + " (" + Utils.formatNumber(i, items.length) + ") price: "
			// + result.getPrice() + " weight: " + result.getWeight());
			if (result != null && result.getPrice() > solution.getPrice()) {
				solution = new Solution(i, result.getPrice(), result.getWeight());
				System.out.print("\nNové maximum: " + solution.getBits() + " ("
						+ Utils.formatNumber(solution.getBits(), items.length) + ") price: " + solution.getPrice()
						+ " weight: " + solution.getWeight() + " ");
			} else {
				System.out.print(".");
			}
		}

		return solution;
	}

}
