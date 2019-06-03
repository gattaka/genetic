package cz.gattserver.genetics;

import static org.junit.Assert.*;

import org.junit.Test;

public class GeneticTest {

	@Test
	public void testChildOf() {
		int count = 8;
		int a = 0b01011110;
		int b = 0b10110011;

		assertEquals(0b01010011, Genetic.childOf(a, b, count));
		assertEquals(0b10111110, Genetic.childOf(b, a, count));
	}

	@Test
	public void testChangeBit() {
		assertEquals(0b01011111, Genetic.changeBit(0b01011110, 0));
		assertEquals(0b01011100, Genetic.changeBit(0b01011110, 1));
		assertEquals(0b01010110, Genetic.changeBit(0b01011110, 3));
		assertEquals(0b11011110, Genetic.changeBit(0b01011110, 7));
	}

}
