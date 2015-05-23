package algorithms.algorithmcomparison.runtimetest;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import algorithms.algorithmcomparison.algorithms.BubbleSort;
import algorithms.algorithmcomparison.algorithms.MergeSort;
import algorithms.algorithmcomparison.algorithms.SortAlgorithm;
import algorithms.algorithmcomparison.generator.RandomArrayGenerator;

public class CorrectnessTest {

	private static final String fileName = "output.txt";
	
	private File outputFile;
	private SortAlgorithm bubbleSort;
	private SortAlgorithm mergeSort;
	private RandomArrayGenerator arrayGenerator;
	
	public CorrectnessTest(){
		outputFile = new File(fileName);
		bubbleSort = new BubbleSort();
		mergeSort = new MergeSort();
		arrayGenerator = new RandomArrayGenerator();
	}
	
	public void test() {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
			for(int i = 0; i < 10; i++){
				for(int j = 0; j < 10; j++){
					final int size = (i + 1) * 1000;
					int[] inputArray = arrayGenerator.generate(size);
					bubbleSort.reset();
					mergeSort.reset();
					bubbleSort.sort(inputArray);
					mergeSort.sort(inputArray);
					
					writer.write(String.format("Algorithm:\t\t%-20s%s", "Bubble Sort", "Merge Sort"));
					writer.newLine();
					writer.write(String.format("Running Time:\t\t%-20d%d", bubbleSort.getRuntimeMilliseconds(), mergeSort.getRuntimeMilliseconds()));
					writer.newLine();
					writer.write(String.format("Array size:\t\t%-20d%d", size, size));
					writer.newLine();
					writer.write(String.format("No. of key comparison:\t%-20d%d", bubbleSort.getNumberOfKeyComparisons(), mergeSort.getNumberOfKeyComparisons()));
					writer.newLine();
					writer.newLine();
					writer.write("Input\tBubble Sort\tMerge Sort");
					writer.newLine();
					int[] resultBubble = bubbleSort.getResultArray();
					int[] resultMerge = mergeSort.getResultArray();
					for(int t = 0; t < size; t++){
						writer.flush();
						writer.write(String.format("%d\t%d\t%d", inputArray[j], resultBubble[j], resultMerge[j]));
						writer.newLine();
					}
				}
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
