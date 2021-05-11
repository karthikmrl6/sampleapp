package academy.learnprog;
import java.util.Arrays;

public class SelectionSortTest {
	public static void main(String[] args) {
		int[] intArray = { 20, 35, -15, 7, 55, 1, -22 };
		for (int lastUnsortedIndex = intArray.length - 1; lastUnsortedIndex > 0; lastUnsortedIndex--) {
			int largest = 0;
			for (int i = 1; i < lastUnsortedIndex; i++) {
				if (intArray[i] > intArray[largest]) {
					largest = i;
				}
			}
			swap(lastUnsortedIndex, largest, intArray);
		}
		System.out.println(Arrays.toString(intArray));
	}

	public static void swap(int lastUnsortedIndex, int largest, int[] intArray) {
		int temp = intArray[largest];
		intArray[largest] = intArray[lastUnsortedIndex];
		intArray[lastUnsortedIndex] = temp;
	}
}
