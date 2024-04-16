package Searching.StringSearching;

/**
 * @author anshukumar
 * 
 *         We construct an LPS(longest prefix which is also a suffix) array to
 *         minimize double checks.
 * 
 *         Time complexity: O(n+m) in the worst case. Space complexity: O(M) for
 *         lps array.
 */
public class KMPAlgorithm {

	public static void main(String[] args) {

		String s = "ABABDABACDABABCABAB";
		String pat = "ABABCABAB";

		int lps[] = computeLPSArr(pat);

		for (int i : lps) {
			System.out.print(i + " ");
		}
		System.out.println();
		int n = s.length();
		int m = pat.length();

		int i = 0;
		int j = 0;
		while (i < n) {
			if (s.charAt(i) == pat.charAt(j)) {
				i++;
				j++;
			}
			if (j == m) {
				System.out.println("Pattern found - " + pat + " at index " + (i - j));
				j = lps[j - 1];
			} else if (i < n && s.charAt(i) != pat.charAt(j)) {
				if (j > 0) {
					j = lps[j - 1];
				} else {
					i++;
				}
			}
		}

	}

	private static int[] computeLPSArr(String pat) {

		int m = pat.length();
		int[] lps = new int[m];
		int len = 0;
		lps[0] = len;
		int i = 1;
		while (i < m) {
			if (pat.charAt(i) == pat.charAt(len)) {
				len++;
				lps[i] = len;
				i++;
			} else {
				if (len != 0) {
					len = lps[len - 1];
				} else {
					lps[i] = len;
					i++;
				}
			}
		}
		return lps;
	}

}
