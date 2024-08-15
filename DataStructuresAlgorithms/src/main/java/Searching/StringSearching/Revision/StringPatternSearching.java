package Searching.StringSearching.Revision;

import java.util.Arrays;

public class StringPatternSearching {

	public static void main(String[] args) {

		String s = "ABABDABACDABABCABAB";
		String pat = "ABABCABAB";

		String txt1 = "AABAACAADAABAABA";
		String pat1 = "AABA";

//		searchPatternNaiveOpt(s, pat);
//
//		kmpPatternSearch(s, pat);
//
//		kmpPatternSearchRev(s, pat);
//
//		kmpPatternSearchRev(txt1, pat1);

		rabinKarpPatternSearch(s, pat);
		rabinKarpPatternSearch(txt1, pat1);
	}

	private static final int q = 101;
	private static final int b = 256;

	private static void rabinKarpPatternSearch(String txt, String pat) {
		int N = txt.length();
		int M = pat.length();
		int h = 1;
		int t = 0;// hash code for text
		int p = 0;// hash code for pattern

		// h = (b^m-1)%q;
		for (int i = 0; i < M - 1; i++) {
			h = (h * b) % q;
		}

		for (int i = 0; i < M; i++) {
			p = (p * b + pat.charAt(i)) % q;
			t = (t * b + txt.charAt(i)) % q;
		}

		for (int i = 0; i <= N - M; i++) {
			if (p == t) {
				int j;
				for (j = 0; j < M; j++) {
					if (txt.charAt(i + j) != pat.charAt(j)) {
						break;
					}
				}
				if (j == M) {
					System.out.println("The pattern found at " + i);
				}
			} else if (i < N - M) {
				t = ((t - txt.charAt(i) * h) * b + txt.charAt(i + M)) % q;
				if (t < 0) {
					t = t + q;
				}
			}
		}
	}

	private static void kmpPatternSearchRev(String s, String pat) {

		int[] lps = createLpsArrayNew(pat);

		System.out.println("The lps array is " + Arrays.toString(lps));

		int M = pat.length();
		int N = s.length();

		int i = 0, j = 0;
		while (i < N) {
			if (s.charAt(i) == pat.charAt(j)) {
				i++;
				j++;
			}
			if (j == M) {
				System.out.println("The pattern found at " + (i - j));
				j = lps[j - 1];
			} else if (i < N && s.charAt(i) != pat.charAt(j)) {
				if (j > 0) {
					j = lps[j - 1];
				} else {
					i++;
				}
			}
		}

	}

	private static int[] createLpsArrayNew(String pat) {

		int M = pat.length();
		int[] lps = new int[M];
		int len = 0;
		lps[0] = len;
		int i = 1;
		while (i < M) {
			if (pat.charAt(i) == pat.charAt(len)) {
				len++;
				lps[i++] = len;
			} else {
				if (len > 0) {
					len = lps[len - 1];
				} else {
					lps[i++] = 0;
				}
			}
		}

		return lps;
	}

	private static void searchPatternNaiveOpt(String pat, String txt) {
		int N = txt.length();
		int M = pat.length();

		for (int i = 0; i <= N - M; i++) {
			int j;
			for (j = 0; j < M; j++) {
				if (txt.charAt(i + j) != pat.charAt(j)) {
					break;
				}
			}
			if (j == M) {
				System.out.println("Pattern found at index " + i);
			}
		}
	}

	private static void kmpPatternSearch(String s, String pat) {
		int[] lps = createLpsArray(pat);

		System.out.println("The lps array is " + Arrays.toString(lps));

		int N = s.length();
		int M = pat.length();

		int i = 0, j = 0;
		while (i < N && j < M) {// (N-i) >= (M-j)
			if (s.charAt(i) == pat.charAt(j)) {
				i++;
				j++;
			}
			if (j == M) {
				System.out.println("The pattern found at " + (i - j));
				j = lps[j - 1];
			}
			// mismatch after j matches
			else if (i < N && s.charAt(i) != pat.charAt(j)) {
				// Do not match lps[0 to lps[j-1]] characters, they will match anyway
				if (j != 0) {
					j = lps[j - 1];
				} else {
					i++;
				}
			}
		}
	}

	private static int[] createLpsArray(String pat) {

		int m = pat.length();
		int len = 0;
		int lps[] = new int[m];
		lps[0] = 0;
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
					lps[i] = 0;
					i++;
				}
			}
		}
		return lps;
	}

}
