package academy.learnprog;
public class RadixSortTest1 {
	public static void radixSingleSort(String[] input, int position, int radix) {
		int numItems = input.length;
		int[] countArray = new int[radix];
		for (String valueString : input) {
			countArray[getIndex(position, valueString)]++;
		}
		// Adjust the count array
		for (int j = 1; j < radix; j++) {
			countArray[j] += countArray[j - 1];
		}
		String[] temp = new String[numItems];
		for (int tempIndex = numItems - 1; tempIndex >= 0; tempIndex--) {
			temp[--countArray[getIndex(position, input[tempIndex])]] = input[tempIndex];
		}
		for (int tempIndex = 0; tempIndex < numItems; tempIndex++) {
			input[tempIndex] = temp[tempIndex];
		}
	}

	public static int getIndex(int position, String valueString) {
		return valueString.charAt(position) - 'a';
	}

	public static void radixSort(String[] input, int radix, int width) {
		for (int i = width - 1; i >= 0; i--) { // least signifant position is at the right. start from the right
			radixSingleSort(input, i, radix);
		}
	}

	public static void main(String[] args) {
		String[] radixArray = { "bcdef", "dbaqc", "abcde", "omadd", "bbbbb" };
		radixSort(radixArray, 26, 5);
		for (int i = 0; i < radixArray.length; i++) {
			System.out.println(radixArray[i]);
		}
	}
}
