package Searching.StringSearching;

public class KMPAlgorithm {

	public static void main(String[] args) {

		String s = "AAAAABAAAAAABA";
		String pat = "AAAA";

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
			if (j == m) {
				System.out.println("Pattern found - " + pat + " at index " + (i-j));
				j = lps[j - 1];
			} else if (s.charAt(i) == pat.charAt(j)) {
				i++;
				j++;
			} else if (s.charAt(i) != pat.charAt(j) && j > 0) {
				j = lps[j - 1];
			} else {
				i++;
			}
		}

	}

	private static int[] computeLPSArr(String pat) {

		int n = pat.length();
		int[] lps = new int[n];
		int len = 0;
		lps[0] = len;
		int i = 1;
		while (i < n) {
			if (pat.charAt(i) == pat.charAt(len)) {
				len++;
				lps[i] = len;
				i++;
			} else if (pat.charAt(i) != pat.charAt(len) && len != 0) {
				len = lps[len - 1];
			} else {
				lps[i] = len;
				i++;
			}
		}
		return lps;
	}

}
