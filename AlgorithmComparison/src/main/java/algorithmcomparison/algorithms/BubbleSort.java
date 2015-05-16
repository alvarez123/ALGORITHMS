package algorithmcomparison.algorithms;

public class BubbleSort implements SortAlgorithm {
	private int runtimeMilliseconds;
	private int numberOfKeyComparisons;

	public BubbleSort() {
		reset();
	}

	public void sort(int[] array) {
		int[] tempArray = new int[array.length];
		System.arraycopy(array, 0, tempArray, 0, array.length);
		final long startTime = System.currentTimeMillis();
		bubbleSort(array);
		runtimeMilliseconds = (int) (System.currentTimeMillis() - startTime);
	}

	public int getNumberOfKeyComparisons() {
		return numberOfKeyComparisons;
	}

	public int getRuntimeMilliseconds() {
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
					numberOfKeyComparisons++;
				}
			}
		}
		return array;
	}
}
