package algorithms.algorithmcomparison.runtimetest;

import java.util.ArrayList;

import algorithms.algorithmcomparison.algorithms.SortAlgorithm;

public class AlgorithmRuntimeTester {
	public static final int NUM_OF_ITERATIONS = 10;
	
	private SortAlgorithm algorithm;
	public AlgorithmRuntimeTester(SortAlgorithm algorithm) {
		this.algorithm = algorithm;
	}
	
	public RuntimeStatistics run(ArrayList<int[]> arrays) {
		long[] runtimeResults = new long[NUM_OF_ITERATIONS];
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