package Searching.StringSearching;

/*
 * Rabin Karp algorithm matches the hash value of pattern with the hash value of current substring of text.
 * If the hash value matches then only match characters of pattern and text.
 * 
 * The hash value of text is calculated using a rolling hash function
 * 
 * Hashing needs 3 inputs - 
 * A prime number 'p' or size of data type is chosen as modulus to avoid data type capacity overflow issue 
 * and ensures proper hash value distribution.
 * Base 'b' - usually a prime number which is the size of character set(256 for ASCII chars) or size of mapped table. 
 * Pattern length - length of the pattern 
 * 
 * For a window of text of length equal to pattern hash is calculated by adding hash for individual chars
 * hash for a character at ith position= c*(b^(pat_len-i-1))%p, where c is numeric code for character
 * 
 * Using rolling hash to generate next hash for txt t which includes (i+m) th character and removes ith character
 * hash = (t - (txt[i-pat_len]*b^(pat_len-1))%p)*b + txt[i]
 * example - include (i+m)th and remove (i)th where h = pow(b, m-1)%p
 * hash = (t - txt.charAt(i)*h)*b + txt.charAt(i+m))%p
 * 
 * The hash function is called Rabin Fingerprint function
 * 
 * Time Complexity: O(n+m) in best and average case and O(n*m) in worst case
 * worst case arises when all the characters are same in pattern and text
 * 
 * Space Complexity: O(1)
 */
public class RabinKarpAlgorithm {

	// d is the number of characters in input alphabet
	public static final int d = 256;// base - b

	public static void main(String[] args) {

		String txt = "GEEKS FOR GEEKS";
		String pat = "GEEK";

//		String txt = "ABABDABACDABABCABAB";
//		String pat = "ABABCABAB";

		int q = 101; // p - prime number modulus
		rabinKarpSearch(txt, pat, q);

	}

	private static void rabinKarpSearch(String txt, String pat, int q) {

		int hash = 0;
		int M = pat.length();
		int N = txt.length();

		// not the correct hash so avoid
//		for (int i = 0; i < M; i++) {
//			hash += (pat.charAt(i) * Math.pow(d, M - i - 1)) % q;
//		}

		int p = 0; // hash value of pattern
		int t = 0; // hash value of text

		int h = 1;

		// Calculates hash as pow(d, m-1)%q
		for (int i = 0; i < M - 1; i++) {
			h = (h * d) % q;
		}

		// Calculates hash value of pattern and first window of text
		for (int i = 0; i < M; i++) {
			p = (p * d + pat.charAt(i)) % q;
			t = (t * d + txt.charAt(i)) % q;
		}
		System.out.println("The hash value of pattern - p is " + p);

		// Slide the pattern over text one by one
		for (int i = 0; i <= N - M; i++) {
			// if hash values of pattern and text matches then match individual characters
			if (p == t) {
				int j;
				for (j = 0; j < M; j++) {
					if (pat.charAt(j) != txt.charAt(i + j)) {
						break;
					}
				}
				// p == t and pat[0,..M-1] = txt[i, i+1, ...i+M-1]
				if (j == M) {
					System.out.println("The pattern found at index " + i);
				}
			}
			// Calculate hash value for next window of text,
			// Remove leading digit and add trailing digit.
			if (i < N - M) {
				t = ((t - txt.charAt(i) * h) * d + txt.charAt(i + M)) % q;

				// For negative hash value of text, convert it to positive
				if (t < 0) {
					t = (t + q);
				}
			}
		}

	}

}
