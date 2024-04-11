package DynamicProgramming;

/**
 * @author anshukumar
 * 
 *         Multiply the matrices given by A1 * A2 * A3 * A4 5*4 4*6 6*2 2*7
 *
 *         Uses Dynamic Programming, there is a sequence of decisions to choose
 *         the most cost effective matrix for multiplication.
 *         
 *         The time complexity is O(n^3)
 */
public class MatrixChainMultiplication {

	public static void main(String[] args) {
		int n = 5;
		int[] P = { 5, 4, 6, 2, 7 };
		int m[][] = new int[5][5];
		int s[][] = new int[5][5];
		int min = 0, q = 0;

		minMultipliedMatrix(P, n, m, s, min, q);

		System.out.println("The minimum number of multiplication performed to get the matrices product is " + m[1][n-1]);
		
		//the sequence of matrix multiplied to get the product/parenthesization
		sequenceMatrixMultiplication(s);
		

	}

	private static void sequenceMatrixMultiplication(int[][] s) {
		
		
		
	}

	private static void minMultipliedMatrix(int[] P, int n, int[][] m, int[][] s, int min, int q) {
		int j = 0;
		// d is the difference between row-i and column-j which is the diagonal.
		for (int d = 1; d < n - 1; d++) {
			// i is the row number of the matrix.
			for (int i = 1; i < n - d; i++) {
				// j is the column number of the matrix.
				j = i + d;
				min = 32767;
				// this is used to get the minimum number of multiplications to get the product
				for (int k = i; k < j; k++) {
					q = m[i][k] + m[k + 1][j] + P[i - 1] * P[k] * P[j];
					if (q < min) {
						min = q;
						s[i][j] = k;
					}
				}
				m[i][j] = min;
			}
		}

	}

}
