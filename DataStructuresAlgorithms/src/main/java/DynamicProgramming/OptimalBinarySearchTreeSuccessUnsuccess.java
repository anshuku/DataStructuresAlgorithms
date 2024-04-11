package DynamicProgramming;

/**
 * @author anshukumar
 * 
 *         The search for a given key out of n keys should happen in such a way
 *         that the cost is minimum When frequency of searching keys are given
 *         for both success and not successful scenarios
 *
 *         Uses Dynamic Programming, there is a sequence of decisions to choose
 *         the most cost optimized BST.
 * 
 *         The time complexity for the given Tabular approach is O(n^3)
 */
public class OptimalBinarySearchTreeSuccessUnsuccess {

	public static void main(String[] args) {

		int n = 4;
		int[] keys = { 0, 10, 20, 30, 40 };
		int[] f = { 0, 3, 3, 1, 1 };
		int[] q = { 2, 3, 1, 1, 1 };
		int[][] w = new int[n + 1][n + 1];
		int[][] c = new int[n + 1][n + 1];

		for (int d = 0; d <= n; d++) {
			for (int i = 0; i <= n - d; i++) {
				int j = i + d;
				if (i == j) {
					w[i][j] = q[i];
				} else {
					w[i][j] = w[i][j - 1] + q[j] + f[j];
				}
				int min = 32767;
				for (int k = i + 1; k <= j; k++) {
					int freq = 0;
					int val = c[i][k - 1] + c[k][j] + w[i][j];
					if (val < min) {
						min = val;
						c[i][j] = min;
						c[j][i] = k;
					}
				}
			}
		}

		System.out.println("The optimal binary search tree has searches = " + c[0][4]);

		System.out.println("probability array is ");
		for (int i = 0; i < w.length; i++) {
			for (int j = 0; j < w[0].length; j++) {
				System.out.print(w[i][j] + " ");
			}
			System.out.println();
		}

		System.out.println("cost array is ");
		for (int i = 0; i < c.length; i++) {
			for (int j = 0; j < c[0].length; j++) {
				System.out.print(c[i][j] + " ");
			}
			System.out.println();
		}
	}

}
