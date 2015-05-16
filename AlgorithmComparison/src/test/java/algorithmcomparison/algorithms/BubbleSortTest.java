package algorithmcomparison.algorithms;

import static org.junit.Assert.*;

import java.lang.reflect.Method;

import org.junit.Before;
import org.junit.Test;

public class BubbleSortTest {
	private static final int array1[] = { 5, 9, 1, 2, 10000, 23452, 7857, 10, 93874, 233, 15687, 90};
	private static final int array1Sorted[] = {1, 2, 5, 9, 10, 90, 233, 7857, 10000, 15687, 23452, 93874};
	private static final int array2[] = {23231, 30303030, 123, 1, 0, 999999, 12, 81, 1923, 1937, 1991, 22, 345678};
	private static final int array2Sorted[] = {0, 1, 12, 22, 81, 123, 1923, 1937, 1991,23231, 345678, 999999, 30303030};
	private static final int array3[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 0};
	private static final int array3Sorted[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
	
	private Method bubbleSortMethod;
	private SortAlgorithm algorithm = new BubbleSort();
	
	@Before
	public void setUp() {
		try {
			bubbleSortMethod = algorithm.getClass().getDeclaredMethod("bubbleSort", int[].class);
			bubbleSortMethod.setAccessible(true);
		} catch (NoSuchMethodException | SecurityException e) {
			bubbleSortMethod = null;
		}
	}
	
	@Test
	public void test1() throws Exception {
		int[] result = (int[]) bubbleSortMethod.invoke(algorithm, array1);
		assertArrayEquals(array1Sorted, result);
	}
	
	@Test
	public void test2() throws Exception {
		int[] result = (int[]) bubbleSortMethod.invoke(algorithm, array2);
		assertArrayEquals(array2Sorted, result);
	}
	
	@Test
	public void test3() throws Exception {
		int[] result = (int[]) bubbleSortMethod.invoke(algorithm, array3);
		assertArrayEquals(array3Sorted, result);
	}
}
