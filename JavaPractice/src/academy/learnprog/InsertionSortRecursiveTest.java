package academy.learnprog;
public class InsertionSortRecursiveTest {
	public static void main(String[] args) {
		int[] intArray = { 20, 35, -15, 7, 55, 1, -22 };
		insertionSort(intArray, intArray.length);
	}

	public static void insertionSort(int[] intArray, int numItems) {
		if (numItems < 2) {
			return;
		}
		insertionSort(intArray, numItems - 1);
		int newElement = intArray[numItems - 1];
		int i;
		for (i = numItems - 1; i > 0 && intArray[i - 1] > newElement; i--) {
			intArray[i] = intArray[i - 1];
		}
		intArray[i] = newElement;
		System.out.println("Result of call when numItems = " + numItems);
		for (int k = 0; k < intArray.length; k++) {
			System.out.print(intArray[k]);
			if (k != (intArray.length - 1)) {
				System.out.print(",");
			}
		}
		System.out.println("");
		System.out.println("-------------");
	}
}
