package Searching.StringSearching;

/**
 * @author anshukumar
 * 
 *         The KPM Algorithm uses the degenerating property of a pattern:
 *         Pattern having same sub-pattern appearing more than once in the
 *         pattern. It improves the Time Complexity to O(n+m).
 * 
 *         Whenever there is a mismatch after some matches or the matching has
 *         happened for a window, the algorithm knows the characters in the text
 *         of the new window. It will avoid matching characters which will match
 *         anyway.
 * 
 *         To know how many characters to be skipped: We pre-process the pattern
 *         and construct an LPS(longest proper prefix which is also a suffix)
 *         array to know how many characters to be skipped and minimize double
 *         checks.
 * 
 *         We use value from lps[] to decide the next character to match.
 * 
 *         In case there is a mismatch/pattern found at jth index of pattern
 *         then we don't need to match lps[j-1] characters, j = lps[j-1]
 * 
 *         Time complexity: O(n+m) in the worst case.
 * 
 *         Space complexity: O(M) for lps array.
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
		while (i < n) {// (N-i) >= (M-j)
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

	// Time complexity in worst case is O(2*M)
	private static int[] computeLPSArr(String pat) {

		int m = pat.length();
		int[] lps = new int[m];
		// length of the previous longest prefix suffix ""
		int len = 0;
		lps[0] = len;
		int i = 1;
		// it calculates lps[i] from i = 1 to m-1
		while (i < m) {
			if (pat.charAt(i) == pat.charAt(len)) {
				len++;
				lps[i] = len;
				i++;
			} else {
				if (len != 0) {
					len = lps[len - 1];
					// don't increment i here
				} else {
					lps[i] = len;
					i++;
				}
			}
		}
		return lps;
	}

}
