package cz.gattserver.genetics;

import static org.junit.Assert.*;

import org.junit.Test;

public class GeneticTest {

	@Test
	public void testChildOf() {
		int count = 8;
		int a = 0b01011110;
		int b = 0b10110011;

		int childAB = Genetic.childOf(a, b, count);
		int childBA = Genetic.childOf(b, a, count);

		assertEquals(0b01010011, childAB);
		assertEquals(0b10111110, childBA);
	}

}
