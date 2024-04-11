package DynamicProgramming;

/**
 * @author anshukumar
 * 
 *         Fill the knapsack with max weight given by m in such a way that
 *         profit is maximum. The objects are either chosen with whole weight or
 *         not chosen at all.
 *
 *         Uses Dynamic Programming, there is a sequence of decisions to choose
 *         the most profit making products.
 * 
 *         The time complexity for the given Tabular approach is O(n^2 )
 */
public class KnapSackZeroOne {

	public static void main(String[] args) {

		int m = 8;
		int n = 4;
		int[] wt = { 0, 2, 3, 4, 5 };
		int[] P = { 0, 1, 2, 5, 6 };
		int k[][] = new int[n + 1][m + 1];

		for (int i = 0; i <= n; i++) {
			for (int w = 0; w <= m; w++) {
				if (i == 0 || w == 0) {
					k[i][w] = 0;
				} else if (wt[i] <= w) {
					k[i][w] = Math.max(k[i - 1][w], k[i - 1][w - wt[i]] + P[i]);
				} else {
					k[i][w] = k[i - 1][w];
				}
			}
		}
		System.out.println("The max profit is " + k[n][m]);

		int i = n, w = m;
		while (i > 0 && w > 0) {
			if (k[i][w] == k[i - 1][w]) {
				System.out.println(
						"Not chosen: The product having weight " + wt[i] + " and profit " + P[i] + " having index " + i);
				i--;
			} else {
				System.out.println(
						"The chosen product has weight " + wt[i] + " and profit " + P[i] + " having index " + i);
				w = w - wt[i];
				i--;
			}
		}
	}

}
