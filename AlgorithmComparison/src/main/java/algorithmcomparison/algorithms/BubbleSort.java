package algorithmcomparison.algorithms;

public class BubbleSort implements SortAlgorithm {
	private long runtimeMilliseconds;
	private int numberOfKeyComparisons;
	
	public BubbleSort() {
		reset();
	}

	public void sort(int[] array) {
		final long startTime = System.currentTimeMillis();
		bubbleSort(array);
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
	
	private int[] bubbleSort(final int[] array) {
		int temp = 0;
		int[] tempArray = new int[array.length];
		System.arraycopy(array, 0, tempArray, 0, array.length);
		for(int i=0; i < tempArray.length; i++){
			for(int j=1; j < (tempArray.length-i); j++){   
                if(tempArray[j-1] > tempArray[j]){
                        //swap the elements!
                        temp = tempArray[j-1];
                        tempArray[j-1] = tempArray[j];
                        tempArray[j] = temp;
                        numberOfKeyComparisons++;
                } 
			}
		}
		return tempArray;
	}
}
