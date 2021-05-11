package academy.learnprog;
public class RecursiveFactorial {
	public static int recursiveFac(int num) {
		// we will get stack overflow error without this loop
		// what is tail recursion in java
		if (num == 0) {
			return 1;
		}
		return num * recursiveFac(num - 1);
	}

	public static int iterativeFac(int num) {
		if (num == 0) {
			return 1;
		}
		int factorial = 1;
		for (int i = 1; i <= num; i++) {
			factorial *= i;
		}
		return factorial;
	}

	public static void main(String args[]) {
		System.out.println(recursiveFac(2));
	}
}
