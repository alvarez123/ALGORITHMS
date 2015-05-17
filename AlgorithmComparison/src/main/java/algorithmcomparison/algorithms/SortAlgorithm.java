package algorithmcomparison.algorithms;

public interface SortAlgorithm {

	public void sort(final int[] array);
	public int getNumberOfKeyComparisons();
	public long getRuntimeMilliseconds();
	public void reset();
}
