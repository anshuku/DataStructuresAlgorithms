package Searching.StringSearching;

/*
 * Find all the occurrences of a pattern in a string text
 * 
 * Assume pattern's length is less than text (M<N)
 * 
 * Best Case: If pattern is present at the beginning of the text(early on) -> O(M) comparisons
 * 
 * Worst Case: If pattern is not present at all or present at the end -> O(M*(N-M+1)) comparisons
 * In worst case, for each position in text, algorithm may need to compare entire pattern against text.
 * This may happen due to few mismatch at end of pattern.
 * 
 * This doesn't work well in cases where there are many matching characters followed by a mismatching character
 * txt[] = “AAAAAAAAAAAAAAAAAB”, pat[] = “AAAAB”
 * txt[] = “ABABABCABABABCABABABC”, pat[] =  “ABABAC” (not worst, but a bad case for Naive)
 */
public class NaivePatternSearch {

	public static void main(String[] args) {
		String txt1 = "AABAACAADAABAABA";
		String pat1 = "AABA";

		String txt2 = "agd";
		String pat2 = "g";

		searchPatternNaive(pat1, txt1);

		searchPatternNaiveOptimized(pat1, txt1);

	}

	// Time complexity: O(M*(N-M+1)) or O(n^2)
	// Space Complexity: O(1)
	private static void searchPatternNaiveOptimized(String pat, String txt) {

		int N = txt.length();
		int M = pat.length();

		// A loop to slide pattern one by one
		for (int i = 0; i <= N - M; i++) {
			int j;
			// For current index i check for pattern match
			for (j = 0; j < M; j++) {
				if (txt.charAt(i + j) != pat.charAt(j)) {
					break;
				}
			}

			// If pattern matches at index i
			if (j == M) {
				System.out.println("The pattern found at index " + i);
			}
		}

	}

	// Time complexity: O(N*M) or O(n^2)
	// Space Complexity: O(1)
	private static void searchPatternNaive(String pat, String txt) {
		int M = pat.length();
		int N = txt.length();
		int j = 0;
		for (int i = 0; i < N; i++) {
			if (pat.charAt(j) == txt.charAt(i)) {
				int temp = i;
				while (j < M && i < N && pat.charAt(j) == txt.charAt(i)) {
					j++;
					i++;
					if (j == M) {
						System.out.println("The pattern found at index: " + (i - j));
					}
				}
				j = 0;
				i = temp;
			}
		}

	}

}
