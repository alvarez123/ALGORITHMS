package algorithmcomparison.runtimetest;

import algorithmcomparison.algorithms.SortAlgorithm;
import algorithmcomparison.generator.RandomArrayGenerator;

public class AlgorithmRuntimeTester {
	private static final int NUM_OF_ITERATIONS = 10;
	
	private RandomArrayGenerator arrayGenerator;
	private SortAlgorithm algorithm;
	public AlgorithmRuntimeTester(SortAlgorithm algorithm) {
		this.algorithm = algorithm;
		arrayGenerator = new RandomArrayGenerator();
	}
	
	public RuntimeStatistics run(int inputSize) {
		int[] runtimeResults = new int[NUM_OF_ITERATIONS];
		int[] keyCompResults = new int[NUM_OF_ITERATIONS];
		
		for(int i = 0; i < NUM_OF_ITERATIONS; i++){
			algorithm.reset();
			algorithm.sort(arrayGenerator.generate(inputSize));
			runtimeResults[i] = algorithm.getRuntimeMilliseconds();
			keyCompResults[i] = algorithm.getNumberOfKeyComparisons();
		}
		return new RuntimeStatistics(runtimeResults, keyCompResults);
	}
}