package algorithmcomparison.algorithms;

public class MergeSort implements SortAlgorithm {

	private long runtimeMilliseconds;
	private int numberOfKeyComparisons;
	
	public MergeSort() {
		reset();
	}

	public void sort(int[] array) {
		final long startTime = System.currentTimeMillis();
		mergeSort(array);
		runtimeMilliseconds = System.currentTimeMillis() - startTime;
	}

	public int getNumberOfKeyComparisons() {
		return numberOfKeyComparisons;
	}

	public long getRuntimeMilliseconds() {
		return runtimeMilliseconds;
	}

	public void reset() {
		runtimeMilliseconds = 0L;
		numberOfKeyComparisons = 0;
	}
	
	private int[] mergeSort(final int[] array) {
		if(array.length == 1)
			return array;
		int mid = array.length / 2;
		int[] left = new int[mid];
		int[] right = new int[array.length - mid];
		System.arraycopy(array, 0, left, 0, mid);
		System.arraycopy(array, mid, right, 0, array.length - mid);
		return merge(mergeSort(left), mergeSort(right));
	}

	private int[] merge(int[] array1, int[] array2) {
		int[] dest = new int[array1.length + array2.length];
		int left = 0;
		int right = 0;
		int i = 0;
		while(left < array1.length && right < array2.length) {
			if(array1[left] < array2[right]){
				dest[i] = array1[left];
				left++;
			} else {
				dest[i] = array2[right];
				right++;
			}
			i++;
		}
		numberOfKeyComparisons += i;
		
		if(left < array1.length)
			System.arraycopy(array1, left, dest, i, array1.length - left);
		
		else if(right < array2.length){
			System.arraycopy(array2, right, dest, i, array2.length - right);
		}
		
		return dest;
	}
}
