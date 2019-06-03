package cz.gattserver.genetics;

import java.util.Optional;

import net.mintern.primitive.Primitive;

public class Genetic implements Solver {

	@Override
	public Solution solve(Item[] items, int maxWeight) {
		int geneCount = items.length;

		// kolik generací se nechá běžet
		int generations = 10;
		// kolik jedinců bude v generaci
		int population = 50;
		// kolik bude elita
		int elite = 5;
		// kolik bude idiotů
		int idiots = 2;

		// pokud jsou 4 věci je jedinec v rozsahu 0000 (nic) - 1111 (vše)
		// v takovém případně je maximální číslo identifikující jedince 2^4-1

		int max = (int) (Math.pow(2, geneCount));
		System.out.println("Max creature " + Utils.formatNumber((int) (max - 1), geneCount));

		Solution solution = new Solution(0, 0, 0);

		int[] creatures = new int[population];

		// init populace
		for (int i = 0; i < population; i++) {
			int creature = (int) (Math.random() * max);
			creatures[i] = creature;
		}

		for (int g = 1; g <= generations; g++) {
			System.out.println("Generation " + g);

			solution = sortAndEvaluate(solution, creatures, items, maxWeight);

			int[] creaturesForMating = new int[elite + idiots];
			int mateIndex = 0;

			System.out.println("  Elite (" + elite + "):");
			for (int cr = 0; cr < elite; cr++) {
				int creature = creatures[cr];
				creaturesForMating[mateIndex++] = creature;
				Item result = Utils.evaluateFitness(creature, maxWeight, items);
				System.out.println("    " + (cr + 1) + ". " + "(" + Utils.formatNumber(creature, items.length)
						+ ") price: " + result.getPrice() + " weight: " + result.getWeight() + " ");
			}

			System.out.println("  Idiots (" + idiots + "):");
			for (int cr = 0; cr < idiots; cr++) {
				int creature = creatures[creatures.length - 1 - cr];
				creaturesForMating[mateIndex++] = creature;
				Optional<Item> result = Optional.ofNullable(Utils.evaluateFitness(creature, maxWeight, items));
				System.out.println("    " + (cr + 1) + ". " + "(" + Utils.formatNumber(creature, items.length)
						+ ") price: " + (result.isPresent() ? result.get().getPrice() : "N/A") + " weight: "
						+ (result.isPresent() ? result.get().getWeight() : "N/A") + " ");
			}

			creatures = new int[population];
			int creatingIndex = 0;
			while (creatingIndex < population)
				for (int a = 0; a < creaturesForMating.length; a++)
					for (int b = 0; b < creaturesForMating.length; b++)
						if (a != b && creatingIndex < population)
							creatures[creatingIndex++] = mutate(
									childOf(creaturesForMating[a], creaturesForMating[b], geneCount), geneCount);
		}

		return sortAndEvaluate(solution, creatures, items, maxWeight);
	}

	private Solution sortAndEvaluate(Solution solution, int[] creatures, Item[] items, int maxWeight) {
		// seřaď jedince dle fitness
		Primitive.sort(creatures, (s1, s2) -> Optional.ofNullable(Utils.evaluateFitness(s2, maxWeight, items))
				.orElse(new Item(0, 0)).getPrice()
				- Optional.ofNullable(Utils.evaluateFitness(s1, maxWeight, items)).orElse(new Item(0, 0)).getPrice());

		// máme nové nejlepší řešení?
		int currentBest = creatures[0];
		Item bestResult = Utils.evaluateFitness(currentBest, maxWeight, items);
		if (bestResult != null && bestResult.getPrice() > solution.getPrice()) {
			solution = new Solution(currentBest, bestResult.getPrice(), bestResult.getWeight());
			System.out.println("  Nové maximum: " + solution.getBits() + " ("
					+ Utils.formatNumber(solution.getBits(), items.length) + ") price: " + solution.getPrice()
					+ " weight: " + solution.getWeight() + " ");
		}
		return solution;
	}

	public static int childOf(int parentA, int parentB, int genecount) {
		int halfcount = genecount / 2;
		// 00000000111 3 z 6 uvozovací nuly
		int maskB = (1 << halfcount) - 1;
		// 00000111000 3 z 6 uvozovací nuly
		int maskA = maskB << halfcount;
		return parentA & maskA | parentB & maskB;
	}

	public static int mutate(int creature, int genecount) {
		return changeBit(creature, (int) (Math.random() * genecount));
	}

	public static int changeBit(int creature, int pos) {
		int mask = 1 << pos;
		if ((creature & mask) > 0)
			return creature & ~mask;
		else
			return creature | mask;
	}

}
