package academy.learnprog;
public class BinarySearchAlgorithmRecurcive {
	public static boolean binarySearchRecursive(int[] array, int x, int left, int right) {
		if (left > right) {
			return false;
		}
		// int mid = (left + right) / 2; //this can cause overflow of integers
		int mid = left + ((right - left) / 2);
		if (array[mid] == x) {
			return true;
		} else if (x < array[mid]) {
			return binarySearchRecursive(array, x, left, mid - 1);
		} else {
			return binarySearchRecursive(array, x, mid + 1, right);
		}
	}

	public static void main(String args[]) {
		int arr[] = { 2, 3, 4, 10, 40 };
		int n = arr.length;
		int x = 10;
		boolean result = BinarySearchAlgorithmRecurcive.binarySearchRecursive(arr, x, 0, n - 1);
	}
	// Time Complexity - T(n) = T(n/2) + c
	// Auxiliary space is temporary or extra space used by an algorithm. This temporary space allocated in order to solve the problem. Space complexity is total space taken by the algorithm with respect to the input size. Space
	// complexity includes both auxiliary space and space taken by input size.
	// O(1) in case of iterative implementation. In case of recursive implementation, O(Logn) recursion call stack space.
}
