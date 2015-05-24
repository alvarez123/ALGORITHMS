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
		int temp = 0;
		for (int i = 0; i < array.length; i++) {
			for (int j = 1; j < (array.length - i); j++) {
				if (array[j - 1] > array[j]) {
					// swap the elements!
					temp = array[j - 1];
					array[j - 1] = array[j];
					array[j] = temp;
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
