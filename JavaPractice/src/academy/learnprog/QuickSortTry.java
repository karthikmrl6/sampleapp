package academy.learnprog;
public class QuickSortTry {
	public static void quicksort(int[] array) {
		quicksort(array, 0, array.length - 1);
	}

	public static void quicksort(int[] array, int left, int right) {
		if (left >= right) {
			return;
		}
		int pivot = array[(left + right) / 2];
		int index = partition(array, left, right, pivot);
		quicksort(array, left, index - 1);
		quicksort(array, index, right);
	}

	public static int partition(int[] array, int left, int right, int pivot) {
		while (left <= right) {
			while (array[left] < pivot) {
				left++;
			}
			while (array[right] > pivot) {
				right--;
			}
			if (left <= right) {
				swap(array, left, right); // do he swap here
				left++;
				right--;
			}
		}
		return left;
	}

	public static void swap(int[] array, int left, int right) {
		int temp = array[left];
		array[left] = array[right];
		array[right] = temp;
	}
	// average performance = O(n log n)
	// In the worst case, it makes O(n*n) comparisons, though this behavior is rare
	// When implemented well, it can be about two or three times faster than its main competitors, merge sort and heapsort
	// Quicksort is a divide-and-conquer algorithm.
	// It works by selecting a 'pivot' element from the array
	// and partitioning the other elements into two sub-arrays, according to whether they are less than or greater than the pivot
	// https://www.geeksforgeeks.org/can-quicksort-implemented-onlogn-worst-case-time-complexity/
}
