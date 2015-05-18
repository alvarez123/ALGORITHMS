package algorithmcomparison.algorithms;

public class MergeSort implements SortAlgorithm {

	private long runtimeMilliseconds;
	private int numberOfKeyComparisons;
	

	private int[] numbers;
	private int[] helper;
	private int number;
	
	public MergeSort() {
		reset();
	}

	public void sort(final int[] array) {
		this.numbers=array;
		number=array.length;
		this.helper = new int[number];
		//System.arraycopy(array, 0, numbers, 0, array.length);
		final long startTime = System.nanoTime();
		mergeSort(0,number-1);
		runtimeMilliseconds = System.nanoTime() - startTime;
	}

	public int getNumberOfKeyComparisons() {
		return numberOfKeyComparisons;
	}

	public long getRuntimeMilliseconds() {
		return runtimeMilliseconds;
	}

	public void reset() {
		runtimeMilliseconds = 0;
		numberOfKeyComparisons = 0;
	}
	
	private void mergeSort(int low, int high) {
	    // check if low is smaller then high, if not then the array is sorted
	    if (low < high) {
	      // Get the index of the element which is in the middle
	      int middle = low + (high - low) / 2;
	      // Sort the left side of the array
	      mergeSort(low, middle);
	      // Sort the right side of the array
	      mergeSort(middle + 1, high);
	      // Combine them both
	      merge(low, middle, high);
	    }
	  }

	  private void merge(int low, int middle, int high) {
         
		 int c=0;
	    // Copy both parts into the helper array
	    for (int i = low; i <= high; i++) {
	      helper[i] = numbers[i];
	    }

	    int i = low;
	    int j = middle + 1;
	    int k = low;
	    // Copy the smallest values from either the left or the right side back
	    // to the original array
	    while (i <= middle && j <= high) {
	      if (helper[i] <= helper[j]) {
	        numbers[k] = helper[i];
	        i++;
	      } else {
	        numbers[k] = helper[j];
	        j++;
	      }
	      k++;
	      c++;
	    }
	    numberOfKeyComparisons+=c;
	    // Copy the rest of the left side of the array into the target array
	    while (i <= middle) {
	      numbers[k] = helper[i];
	      k++;
	      i++;
	    }

	  }

	@Override
	public int[] getResultArray() {
		return numbers;
	}
	
	/*private int[] mergeSort(final int[] array) {
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
	}*/
}