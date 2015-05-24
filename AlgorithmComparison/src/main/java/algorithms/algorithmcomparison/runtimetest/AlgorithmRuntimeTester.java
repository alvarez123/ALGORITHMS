package algorithms.algorithmcomparison.runtimetest;

import java.util.ArrayList;

import algorithms.algorithmcomparison.algorithms.SortAlgorithm;

public class AlgorithmRuntimeTester {
	public static final int NUM_OF_ITERATIONS = 10;
	
	private SortAlgorithm algorithm;
	private ArrayList<int[]> resultarrays;
	public AlgorithmRuntimeTester(SortAlgorithm algorithm) {
		this.algorithm = algorithm;
		resultarrays= new ArrayList<int[]>();
	}
	
	public ArrayList<int[]> getResultarrays() {
		return resultarrays;
	}

	public RuntimeStatistics run(ArrayList<int[]> arrays) {
		long[] runtimeResults = new long[NUM_OF_ITERATIONS];
		long[] keyCompResults = new long[NUM_OF_ITERATIONS];
		resultarrays.clear();
		
		for(int i = 0; i < NUM_OF_ITERATIONS; i++){
			algorithm.reset();
			algorithm.sort(arrays.get(i));
			resultarrays.add(algorithm.getResultArray());
			runtimeResults[i] = algorithm.getRuntimeMilliseconds();
			keyCompResults[i] = algorithm.getNumberOfKeyComparisons();
		}
		return new RuntimeStatistics(runtimeResults, keyCompResults);
	}
}