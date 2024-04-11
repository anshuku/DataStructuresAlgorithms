package DynamicProgramming.LongestCommonSubsequence;

/**
 * @author anshukumar
 * 
 *         The search for a longest common subsequence in 2 strings in which the
 *         set of characters are common in both strings even though the set of
 *         characters are not contiguous.
 *
 *         Uses Dynamic Programming - Tabulation, there is a sequence of
 *         decisions to choose the longest common subsequence. A table is used
 *         for storing the values of previous results in a table.
 * 
 *         This is a top down approach but the table is filled from bottom to
 *         top. The time complexity for the given Tabular approach is O(2^n).
 */
public class LongestCommonSubsequenceDynamicProgramming {

	public static void main(String[] args) {
		String[] s1 = { "", "l", "o", "n", "g", "e", "s", "t" };
		String[] s2 = { "", "s", "t", "o", "n", "e" };
		int m = s1.length;
		int n = s2.length;

		int[][] lcs = new int[m][n];

		for (int i = 1; i < m; i++) {
			for (int j = 1; j < n; j++) {
				if (s1[i] == s2[j]) {
					lcs[i][j] = 1 + lcs[i - 1][j - 1];
				} else {
					lcs[i][j] = Math.max(lcs[i - 1][j], lcs[i][j - 1]);
				}
			}
		}

		System.out.println("The length of longest common subsequence is " + lcs[m - 1][n - 1]);

		// to get the lcs[8][6]
		int i = m - 1;// rows - 8 s1
		int j = n - 1;// cols - 6 s2
		int left = 0;
		int right = 0;
		StringBuilder s = new StringBuilder();
		while (i > 0 && j > 0) {
			if (s1[i] != s2[j]) {
				left = lcs[i][j - 1];
				right = lcs[i - 1][j];
				if (left > right) {
					j--;
				} else {
					i--;
				}
			} else {
				s.append(s1[i]);
				i--;
				j--;

			}
		}

		System.out.println("The longest common subsequence is " + s.reverse().toString());

	}

}
