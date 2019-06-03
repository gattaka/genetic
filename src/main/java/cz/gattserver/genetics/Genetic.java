package cz.gattserver.genetics;

import java.util.Optional;

import net.mintern.primitive.Primitive;

public class Genetic implements Solver {

	@Override
	public int solve(Item[] items, int maxWeight) {
		int geneCount = items.length;

		// kolik generací se nechá běžet
		int generations = 2;
		// kolik jedinců bude v generaci
		int population = 50;

		// pokud jsou 4 věci je jedinec v rozsahu 0000 (nic) - 1111 (vše)
		// v takovém případně je maximální číslo identifikující jedince 2^4-1

		int max = (int) (Math.pow(2, geneCount));
		System.out.println("Max creature " + Utils.formatNumber((int) (max - 1), geneCount));

		int[] creatures = new int[population];

		// init populace
		for (int i = 0; i < population; i++) {
			int creature = (int) (Math.random() * max);
			creatures[i] = creature;
		}

		for (int g = 0; g < generations; g++) {
			System.out.println("Generation " + g);

			// seřaď jedince dle fitness
			Primitive.sort(creatures,
					(s1, s2) -> Optional.ofNullable(Utils.evaluateFitness(s2, maxWeight, items)).orElse(new Item(0, 0))
							.getPrice()
							- Optional.ofNullable(Utils.evaluateFitness(s1, maxWeight, items)).orElse(new Item(0, 0))
									.getPrice());

			System.out.println("  Top 5:");
			for (int cr = 0; cr < 5; cr++) {
				int creature = creatures[cr];
				Item result = Utils.evaluateFitness(creature, maxWeight, items);
				System.out.println("    " + (cr + 1) + ". " + "(" + Utils.formatNumber(creature, items.length)
						+ ") price: " + result.getPrice() + " weight: " + result.getWeight() + " ");
			}
		}

		return 0;
	}

	public static int childOf(int parentA, int parentB, int genecount) {
		int halfcount = genecount / 2;
		// 00000000111 3 z 6 uvozovací nuly
		int maskB = (1 << halfcount) - 1;
		// 00000111000 3 z 6 uvozovací nuly
		int maskA = maskB << halfcount;
		return parentA & maskA | parentB & maskB;
	}

}
