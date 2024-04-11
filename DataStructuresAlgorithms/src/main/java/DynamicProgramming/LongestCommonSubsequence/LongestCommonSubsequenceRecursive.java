package DynamicProgramming.LongestCommonSubsequence;

/**
 * @author anshukumar
 * 
 *         The search for a longest common subsequence in 2 strings in which the
 *         set of characters are common in both strings even though the set of
 *         characters are not contiguous.
 *
 *         Uses recursion, there is a recursive approach to solve this and we
 *         find all the possibilities. A recursion tree is formed and max value
 *         is obtained in case there are no match(most frequent) In case of
 *         match the indices are increased by 1 and 1 is added to result of the
 *         increase index search.
 * 
 *         This is a top down approach. The time complexity for the given
 *         Tabular approach is O(2^n)
 */
public class LongestCommonSubsequenceRecursive {

	public static void main(String[] args) {

		String[] s1 = { "l", "o", "n", "g", "e", "s", "t" };
		String[] s2 = { "s", "t", "o", "n", "e" };
		int m = s1.length;
		int n = s2.length;

		int len = lcsRecursive(s1, s2, 0, 0);


		System.out.println("The length of longest common subsequence is " + len);

	}

	private static int lcsRecursive(String[] s1, String[] s2, int i, int j) { 
		if (i == s1.length || j == s2.length) {
			return 0;
		} else if (s1[i] == s2[j]) {
			return 1 + lcsRecursive(s1, s2, i + 1, j + 1);
		} else {
			return Math.max(lcsRecursive(s1, s2, i + 1, j), lcsRecursive(s1, s2, i, j + 1));
		}
	}

}
