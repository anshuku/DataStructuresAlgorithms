package Revision;

public class AlsoDSRevise2 {

	public static void main(String[] args) {
		int n = 5;
		int[] P = { 5, 4, 6, 2, 7 };
		int m[][] = new int[5][5];
		int s[][] = new int[5][5];
		int min = 0, q = 0;

		minMultipliedMatrix(P, n, m, s, min, q);

		System.out.println(
				"The minimum number of multiplication performed to get the matrices product is " + m[1][n - 1]);

	}

	private static void minMultipliedMatrix(int[] p, int n, int[][] m, int[][] s, int min, int q) {

		int j = 0;
		for (int d = 1; d < n - 1; d++) {
			min = 32767;
			for (int i = 1; i < n - d; i++) {
				j = i + d;
				for (int k = i; k < j; k++) {
					q = m[i][k] + m[k + 1][j] + p[i - 1] * p[k] * p[j];
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
