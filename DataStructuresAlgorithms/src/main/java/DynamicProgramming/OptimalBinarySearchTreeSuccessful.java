package DynamicProgramming;

/**
 * @author anshukumar
 * 
 *         The search for a given key out of n keys should happen in such a way
 *         that the cost is minimum When frequency of searching the keys are not
 *         given, then the height balanced binary search tree is optimal When
 *         frequency of searching keys are given, then need to use DP to get the
 *         Optimal BST
 *
 *         Uses Dynamic Programming, there is a sequence of decisions to choose
 *         the most cost optimized BST.
 * 
 *         The time complexity for the given Tabular approach is O(n^3)
 */
public class OptimalBinarySearchTreeSuccessful {

	public static void main(String... args) {

		int n = 4;
		int[] keys = { 0, 10, 20, 30, 40 };
		int[] f = { 0, 4, 2, 6, 3 };
		int freq = 0;
		int[][] c = new int[n + 1][n + 1];

		// difference or diagonal starts from 0 and goes till 4
		for (int d = 0; d <= n; d++) {
			int j = 0;
			// row starts from 0 and goes till 4
			for (int i = 0; i <= n - d; i++) {
				j = i + d;
				int min = 32767;
				int q = 0;

				for (int a = i + 1; a <= j; a++) {
					freq += f[a];
				}
				System.out.println("freq " + freq);
				for (int k = i + 1; k <= j; k++) {
					q = c[i][k - 1] + c[k][j] + freq;
					if (q < min) {
						min = q;
						c[i][j] = min;
						c[j][i] = k;
					}
				}

				freq = 0;
			}
		}

		System.out.println("The optimal binary search tree has searches = " + c[0][4]);

		for (int i = 0; i < c.length; i++) {
			for (int j = 0; j < c[0].length; j++) {
				System.out.print(c[i][j] + " ");
			}
			System.out.println();
		}
	}
}
