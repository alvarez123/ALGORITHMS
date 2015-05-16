package algorithmcomparison.algorithms;

public interface SortAlgorithm {

	public void sort(int[] array);
	public int getNumberOfKeyComparisons();
	public int getRuntimeMilliseconds();
	public void reset();
}
