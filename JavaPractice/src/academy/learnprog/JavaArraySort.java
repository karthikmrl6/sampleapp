package academy.learnprog;
import java.util.Arrays;

public class JavaArraySort {
	public static void main(String[] args) {
		int[] intArray = { 20, 35, -15, 7, 55, 1, -22 };
		Arrays.sort(intArray);
		Arrays.parallelSort(intArray); // used for large datasets
		for (int i = 0; i < intArray.length; i++) {
			System.out.println(intArray[i]);
		}
	}
}
