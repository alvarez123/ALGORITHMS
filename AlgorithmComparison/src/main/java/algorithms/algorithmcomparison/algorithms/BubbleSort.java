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
		int lastSwap = array.length - 1;

		for (int i = 1; i < array.length; i++) {
			boolean is_sorted = true;
			int currentSwap = -1;

			for (int j = 0; j < lastSwap; j++) {
				numberOfKeyComparisons++;
				if (array[j] > array[j + 1]) {
					int temp = array[j];
					array[j] = array[j + 1];
					array[j + 1] = temp;
					is_sorted = false;
					currentSwap = j;
				}
			}
			if (is_sorted)
				break;
			lastSwap = currentSwap;
		}
		return array;

	}

	@Override
	public int[] getResultArray() {
		return resultArray;
	}
}
