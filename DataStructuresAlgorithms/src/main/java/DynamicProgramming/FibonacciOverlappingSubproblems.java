package DynamicProgramming;

/*
 * Dynamic programming is an algorithmic paradigm that solves a problem by breaking it into subproblems using recursion
 * and combining solutions to a subproblem.
 * It reuses the solution to a subproblem to avoid recomputing the solution.
 * 
 * It can be used in a problem if the problem has 2 properties: Overlapping Subproblem and Optimal substructures
 * 
 * I. Overlapping Subproblem
 * It's like Divide and Conquer in which the problem is divided into subproblems and subproblem solutions are combined.
 * but in DP, solutions to subproblems are needed again and again so it's stored to avoid recomputing.
 * The solutions are stored in a lookup table(array) and it's reused later.
 * 
 * In Fibonacci sequence for example the values are calculated multiple times
 * To store and reuse the values there are 2 approaches -
 * 1) Memoization (Top Down) - it uses recursion and a lookup table to store and retrieve data.
 * 2) Tabulation (Bottom Up) - the solutions for a subproblems in table are stored in bottom up fashion.
 * 
 * II. Optimal Substructure
 * 
 */
public class FibonacciOverlappingSubproblems {

	final int MAX = 100;
	final int NIL = -1;

	int[] lookup;

	// Initializes lookup table with -1 or NIL values
	void initializeLookup() {
		lookup = new int[MAX];
		for (int i = 0; i < MAX; i++) {
			lookup[i] = NIL;
		}
	}

	public static void main(String[] args) {

		int fibonacci = fibonacci(5);
		System.out.println("The fibonacci number is " + fibonacci);

		FibonacciOverlappingSubproblems fos = new FibonacciOverlappingSubproblems();
		fos.initializeLookup();

		int fibonacciMemoizationDp = fos.fibonacciMemoizationDp(5);

		System.out.println("The fibonacci number via Memoization dynamic programming is " + fibonacciMemoizationDp);

		int fibonacciTabulationDp = fos.fibonacciTabulationDp(5);

		System.out.println("The fibonacci number via Tabulation dynamic programming is " + fibonacciTabulationDp);

		int fibonacciTwoVar = fos.fibonacciTwoVar(5);

		System.out.println("The fibonacci number via 2 variables is " + fibonacciTwoVar);

	}

	// Time complexity: O(n)
	// Space complexity: O(1) since constant space is used
	private int fibonacciTwoVar(int n) {
		int a = 0;
		int b = 1;
		for (int i = 2; i <= n; i++) {
			int temp = b;
			b = a + b;
			a = temp;
		}
		return b;
	}

	// Tabulation - It builds a table to store and reuse data in a bottom up fashion
	// Time complexity: O(n)
	// Space complexity: O(n) for using a lookup table
	private int fibonacciTabulationDp(int n) {

		int[] lookup = new int[n + 1];
		lookup[1] = 1;
		for (int i = 2; i <= n; i++) {
			lookup[i] = lookup[i - 1] + lookup[i - 2];
		}
		return lookup[n];
	}

	// Memoization - It uses recursion and a lookup table to store and retrieve data
	// Time complexity: O(n) since the algorithm computes the values only once,
	// stores it and reuses. This makes it linear and efficient for larger n.
	// Space complexity: O(n) for using a lookup table
	private int fibonacciMemoizationDp(int n) {
		if (lookup[n] == NIL) {
			if (n <= 1) {
				lookup[n] = n;
			} else { // else block is important because for fib(1) or fib(0), the fib(n-1) is called
				lookup[n] = fibonacciMemoizationDp(n - 1) + fibonacciMemoizationDp(n - 2);
			}
		}
		return lookup[n];
	}

	// Time complexity: O(n^2)
	// Space complexity: O(1)
	private static int fibonacci(int n) {
		if (n <= 1) {
			return n;
		}
		return fibonacci(n - 1) + fibonacci(n - 2);
	}

}
