package algorithms.algorithmcomparison.algorithms;

public class BubbleSort implements SortAlgorithm {
	private long runtimeMilliseconds;
	private long numberOfKeyComparisons;
	private int[] resultArray = null;

	public BubbleSort() {
		reset();
	}

	public void sort(int[] array) {
		resultArray = new int[array.length];
		System.arraycopy(array, 0, resultArray, 0, array.length);
		final long startTime = System.nanoTime();
		bubbleSort(resultArray);
		runtimeMilliseconds = System.nanoTime() - startTime;

	}

	public long getNumberOfKeyComparisons() {
		return numberOfKeyComparisons;
	}

	public long getRuntimeMilliseconds() {
		return runtimeMilliseconds;
	}

	public void reset() {
		runtimeMilliseconds = 0;
		numberOfKeyComparisons = 0;
	}

	private int[] bubbleSort(final int[] array) {
		int j;
		boolean flag = true; // set flag to true to begin first pass
		int temp; // holding variable

		while (flag) {
			flag = false; // set flag to false awaiting a possible swap
			for (j = 0; j < array.length - 1; j++) {
				if (array[j] > array[j + 1]) // change to > for descending sort
				{
					temp = array[j]; // swap elements
					array[j] = array[j + 1];
					array[j + 1] = temp;
					flag = true; // shows a swap occurred
				}
				numberOfKeyComparisons++;
			}
		}
		return array;
	}

	@Override
	public int[] getResultArray() {
		return resultArray;
	}
}
