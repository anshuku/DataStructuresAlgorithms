package DynamicProgramming.LongestCommonSubsequence;

/**
 * @author anshukumar
 * 
 *         The search for a longest common subsequence in 2 strings in which the
 *         set of characters are common in both strings even though the set of
 *         characters are not contiguous.
 *
 *         Uses Dynamic Programming - Memoization, there is a sequence of
 *         decisions to choose the longest common subsequence. The recursive
 *         algorithm is optimized by storing the values of previous function
 *         calls in a table.
 * 
 *         This is a top down approach but the table is filled from bottom to
 *         top. The time complexity for the given Tabular approach is O(2^n).
 */
public class LongestCommonSubsequenceMemoization {

	public static void main(String[] args) {
		String[] s1 = { "l", "o", "n", "g", "e", "s", "t" };
		String[] s2 = { "s", "t", "o", "n", "e" };
		int m = s1.length;
		int n = s2.length;

		int[][] lcs = new int[m + 1][n + 1];

		int len = lcsRecursive(lcs, s1, s2, 0, 0);

		System.out.println("The length of longest common subsequence is " + len);
	}

	private static int lcsRecursive(int[][] lcs, String[] s1, String[] s2, int i, int j) {
		int val1 = 0, val2 = 0;
		if (lcs[i][j] != 0) {
			return lcs[i][j];
		} else if (i == s1.length || j == s2.length) {
			return 0;
		} else if (s1[i] == s2[j]) {
			val1 = lcsRecursive(lcs, s1, s2, i + 1, j + 1);
			lcs[i][j] = val1;
			return 1 + val1;
		} else {
			val1 = lcsRecursive(lcs, s1, s2, i + 1, j);
			val2 = lcsRecursive(lcs, s1, s2, i, j + 1);
			lcs[i][j] = Math.max(val1, val2);
			return Math.max(val1, val2);
		}
	}

}
