// Veasna Bun
// CS143
// Oct-19-2021

public class RecursionIntro {

// Part 1. Even digits up, odd digits down ========================================

	public static long eduodd(long n) {

		if (n < 0) {
			return eduodd(-n) * -1; // negate the negative if less than zero
		} else if (n <= 10 && n % 2 == 0) { // even digit increase by one
			return (n + 1) % 10;
		} else if (n <= 10 && n % 2 == 1) { // odd digit decrease by one
			return (n - 1) % 10;
		}
		return 10 * eduodd(n / 10) + eduodd(n % 10); // recursive method
	}

//Part 2a. Recursive definition =================================================== 

	public static int fibby(int n) {
		if (n == 0) {
			return 1;
		}
		return fibby(n / 3) + fibby((2 * n) / 3);
		// sooner of later value soon will return 1 when n == 0
	}

//Part 2b. Sparse table generation ================================================

	public static void printSparseTable(int start, int end) {
		System.out.println(start + " " + fibby(start)); // method start by printing the start
		printSparseTable(start, end, fibby(start));
		// this is than store so it can be check by the helper method
	}

	private static void printSparseTable(int start, int end, int before) {
		if (start <= end && before == fibby(start)) {// default by checking the first pass-down parameter
			printSparseTable(start + 1, end, before);
			// increment the start by one to check if the next value is the same or not
		} else if (start <= end && before != fibby(start)) { // if next value is not the same
			System.out.println(start + " " + fibby(start)); // print statement
			printSparseTable(start + 1, end, fibby(start));
			// increment start by one and also update the current to before
		}
	}

//Part 3a. Largest power of two less than ========================================

	public static int lp2lt(int n) {
		if (n <= 2) {
			return 1;
		}
		return 2 * lp2lt((n + 1) / 2);
		// x = (n+1) in the f(x) round up value so odd integer can return correctly
	}

//Part 3b. There can be only one =================================================

	public static int champion(boolean[] a) {
		return champion(a, 0, a.length - 1);
	}

	private static int champion(boolean[] a, int start, int end) {
		// base case
		if (end - start + 1 <= 2) {
			if (a[start] == a[end]) {
				return end;
			} else {
				return start;
			}
		}
		int groupA = champion(a, start, start + (lp2lt(end - start + 1)) - 1);
		int groupB = champion(a, start + lp2lt(end - start + 1), end);

		if (a[groupA] == a[groupB]) {
			return groupB;
		} else {
			return groupA;
		}
	}

}
