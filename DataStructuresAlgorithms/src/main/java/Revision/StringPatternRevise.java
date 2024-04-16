package Revision;

import java.util.Arrays;

public class StringPatternRevise {

	public static void main(String[] args) {

		String s = "ABABDABACDABABCABAB";
		String pat = "ABABCABAB";

		int lps[] = computeLPSArray(pat);
		System.out.println("lps array is " + Arrays.toString(lps));

		int counter = 0;

		int count = compareStrings(s, pat, lps, counter);
		System.out.println("Total number of pattern matches - " + count);
	}

	private static int compareStrings(String s, String pat, int[] lps, int count) {
		int n = s.length();
		int m = pat.length();
		for (int i = 0, j = 0; i < n;) {
			if (s.charAt(i) == pat.charAt(j)) {
				i++;
				j++;
			}
			if (j == m) {
				System.out.println("There is match for pattern " + pat + " at index " + (i - j));
				j = lps[j - 1];
				count++;
			} else if (s.charAt(i) != pat.charAt(j)) {
				if (j > 0) {
					j = lps[j - 1];
				} else {
					i++;
				}
			}
		}
		return count;
	}

	private static int[] computeLPSArray(String pat) {
		int m = pat.length();
		int lps[] = new int[m];

		int len = 0;
		lps[0] = 0;

		for (int i = 1; i < m;) {
			if (pat.charAt(i) == pat.charAt(len)) {
				len++;
				lps[i] = len;
				i++;
			} else if (pat.charAt(i) != pat.charAt(len) && len > 0) {
				len = lps[len - 1];
			} else {
				lps[i] = len;
				i++;
			}
		}

		return lps;
	}

}
