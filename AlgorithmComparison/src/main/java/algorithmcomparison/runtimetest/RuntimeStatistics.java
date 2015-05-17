package algorithmcomparison.runtimetest;

public class RuntimeStatistics {

	private double averageRuntime;
	private double stDevRuntime;
	private double averageKeyComparisons;
	private double stDevKeyComparisons;
	
	public RuntimeStatistics(long[] runtimeResults, int[] keyCompResults) {
		averageRuntime = getAverage(runtimeResults);
		stDevRuntime = getStDev(runtimeResults, averageRuntime);
		averageKeyComparisons = getAverage(keyCompResults);
		stDevKeyComparisons = getStDev(keyCompResults, averageKeyComparisons);
	}
	
	public double getAverageRuntime() {
		return averageRuntime;
	}

	public double getStDevRuntime() {
		return stDevRuntime;
	}

	public double getAverageKeyComparisons() {
		return averageKeyComparisons;
	}

	public double getStDevKeyComparisons() {
		return stDevKeyComparisons;
	}

	private double getAverage(int[] array) {
		int sum = 0;
		for(int i : array)
			sum += i;
		return (double) sum / array.length;
	}
	
	private double getStDev(int[] array, double avg) {
		double tot = 0;
		for(int i = 0; i < array.length; i++)
			tot += Math.pow(array[i] - avg, 2);
		return Math.sqrt(tot);
	}
	
	private double getAverage(long[] array) {
		long sum = 0;
		for(long i : array)
			sum += i;
		return (double) sum / array.length;
	}
	
	private double getStDev(long[] array, double avg) {
		double tot = 0;
		for(int i = 0; i < array.length; i++)
			tot += Math.pow(array[i] - avg, 2);
		return Math.sqrt(tot);
	}
}
