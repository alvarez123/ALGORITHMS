package algorithmcomparison.runtimetest;

import algorithmcomparison.algorithms.SortAlgorithm;

public class AlgorithmRuntimeTester {
	private static final int NUM_OF_ITERATIONS = 10;
	
	private SortAlgorithm algorithm;
	public AlgorithmRuntimeTester(SortAlgorithm algorithm) {
		this.algorithm = algorithm;
	}
	
	public RuntimeStatistics run(int[] array) {
		int[] runtimeResults = new int[NUM_OF_ITERATIONS];
		int[] keyCompResults = new int[NUM_OF_ITERATIONS];
		
		for(int i = 0; i < NUM_OF_ITERATIONS; i++){
			algorithm.reset();
			algorithm.sort(array);
			runtimeResults[i] = algorithm.getRuntimeMilliseconds();
			keyCompResults[i] = algorithm.getNumberOfKeyComparisons();
		}
		return new RuntimeStatistics(runtimeResults, keyCompResults);
	}
}