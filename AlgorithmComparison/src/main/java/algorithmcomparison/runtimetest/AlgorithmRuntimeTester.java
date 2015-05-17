package algorithmcomparison.runtimetest;

import java.util.ArrayList;

import algorithmcomparison.algorithms.SortAlgorithm;

public class AlgorithmRuntimeTester {
	public static final int NUM_OF_ITERATIONS = 10;
	
	private SortAlgorithm algorithm;
	public AlgorithmRuntimeTester(SortAlgorithm algorithm) {
		this.algorithm = algorithm;
	}
	
	public RuntimeStatistics run(ArrayList<int[]> arrays) {
		int[] runtimeResults = new int[NUM_OF_ITERATIONS];
		int[] keyCompResults = new int[NUM_OF_ITERATIONS];
		
		for(int i = 0; i < NUM_OF_ITERATIONS; i++){
			algorithm.reset();
			algorithm.sort(arrays.get(i));
			runtimeResults[i] = algorithm.getRuntimeMilliseconds();
			keyCompResults[i] = algorithm.getNumberOfKeyComparisons();
		}
		return new RuntimeStatistics(runtimeResults, keyCompResults);
	}
}