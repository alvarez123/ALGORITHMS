package algorithms.algorithmcomparison.runtimetest;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import algorithms.algorithmcomparison.algorithms.BubbleSort;
import algorithms.algorithmcomparison.algorithms.MergeSort;
import algorithms.algorithmcomparison.generator.RandomArrayGenerator;

public class CorrectnessTest {

	private static final String fileName = "output.txt";
	
	private File outputFile;
	private AlgorithmRuntimeTester bubbleSort;
	private AlgorithmRuntimeTester mergeSort;
	private RandomArrayGenerator arrayGenerator;
	
	public CorrectnessTest(){
		outputFile = new File(fileName);
		bubbleSort = new AlgorithmRuntimeTester(new BubbleSort());
		mergeSort = new AlgorithmRuntimeTester(new MergeSort());
		arrayGenerator = new RandomArrayGenerator();
	}
	
	public void test() {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
			ArrayList<int[]> arrays = new ArrayList<int[]>();
			for(int i = 0; i < 10; i++){
				for(int j = 0; j < 10; j++)
					arrays.add(arrayGenerator.generate((i + 1) * 1000));	
					
					RuntimeStatistics bubble = bubbleSort.run(arrays);
					RuntimeStatistics merge = mergeSort.run(arrays);
					final int size=(i+1)*1000;
					
					writer.write(String.format("Algorithm:\t\t%-20s%s", "Bubble Sort", "Merge Sort"));
					writer.newLine();
					writer.write(String.format("Running Time:\t\t%-20f%f", bubble.getAverageRuntime(), merge.getAverageRuntime()));
					writer.newLine();
					writer.write(String.format("Array size:\t\t%-20d%d", size, size ));
					writer.newLine();
					writer.write(String.format("No. of key comparison:\t%-20f%f", bubble.getAverageKeyComparisons(), merge.getAverageKeyComparisons()));
					writer.newLine();
					writer.newLine();
					writer.write("Input\tBubble Sort\tMerge Sort");
					writer.newLine();
					
					ArrayList<int[]> rsBubble = bubbleSort.getResultarrays();
					ArrayList<int[]> rsMerge = mergeSort.getResultarrays();
				for(int k=0 ; k<10 ; k++){
					int[] inputarray=arrays.get(k);
				    int[] resultBubble=rsBubble.get(k);
				    int[] resultMerge=rsMerge.get(k);
					for(int t = 0; t < size; t++){
						writer.flush();
						writer.write(String.format("%d\t%d\t%d", inputarray[t], resultBubble[t], resultMerge[t]));
						writer.newLine();
					}
					writer.newLine();
				}
				arrays.clear();
				
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
