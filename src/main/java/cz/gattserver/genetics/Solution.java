package cz.gattserver.genetics;

public class Solution {
	
	private int bits;
	private int price;
	private int weight;

	public Solution(int bits, int price, int weight) {
		this.bits = bits;
		this.price = price;
		this.weight = weight;
	}

	public int getBits() {
		return bits;
	}

	public int getPrice() {
		return price;
	}

	public int getWeight() {
		return weight;
	}

}