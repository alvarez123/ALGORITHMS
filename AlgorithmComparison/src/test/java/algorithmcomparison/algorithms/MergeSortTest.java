package algorithmcomparison.algorithms;

import static org.junit.Assert.*;

import java.lang.reflect.Method;

import org.junit.Before;
import org.junit.Test;

public class MergeSortTest {
	private static final int array1[] = { 5, 9, 1, 2, 10000, 23452, 7857, 10, 93874, 233, 15687, 90};
	private static final int array1Sorted[] = {1, 2, 5, 9, 10, 90, 233, 7857, 10000, 15687, 23452, 93874};
	private static final int array2[] = {23231, 30303030, 123, 1, 0, 999999, 12, 81, 1923, 1937, 1991, 22, 345678};
	private static final int array2Sorted[] = {0, 1, 12, 22, 81, 123, 1923, 1937, 1991,23231, 345678, 999999, 30303030};
	private static final int array3[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 0};
	private static final int array3Sorted[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
	
	private Method mergeSortMethod;
	private SortAlgorithm algorithm = new MergeSort();
	@Before
	public void setUp() {
		try {
			mergeSortMethod = algorithm.getClass().getDeclaredMethod("mergeSort", int[].class);
			mergeSortMethod.setAccessible(true);
		} catch (NoSuchMethodException | SecurityException e) {
			mergeSortMethod = null;
		}
	}
	
	@Test
	public void test1() throws Exception {
		final int[] result = (int[]) mergeSortMethod.invoke(algorithm, array1);
		assertArrayEquals(array1Sorted, result);
	}
	
	@Test
	public void test2() throws Exception {
		final int[] result = (int[]) mergeSortMethod.invoke(algorithm, array2);
		assertArrayEquals(array2Sorted, result);
	}
	
	@Test
	public void test3() throws Exception {
		final int[] result = (int[]) mergeSortMethod.invoke(algorithm, array3);
		assertArrayEquals(array3Sorted, result);
	}
}