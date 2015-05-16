package algorithmcomparison.generator;

import java.util.Random;

public class RandomArrayGenerator {
	
	private Random random;
	
	public RandomArrayGenerator() {
		random = new Random();
	}
	
	public RandomArrayGenerator(long seed) {
		random = new Random(seed);
	}
	
	public int[] generate(int n) {
		int[] array = new int[n];
		
		for(int i = 0; i < n; i++)
			array[i] = random.nextInt();
		
		return array;
	}
}
