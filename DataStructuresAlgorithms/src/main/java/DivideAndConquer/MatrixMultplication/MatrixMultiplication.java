package DivideAndConquer.MatrixMultplication;

public class MatrixMultiplication {

	public static void main(String[] args) {
		
		int [][] m1 = {{1,2},{3,4},{1,4},{2,3}};
		int [][] m2 = {{5,6},{7,8}};
		
		for(int i=0; i<m1.length; i++) {//row length
			for(int j=0; j<m1[0].length; j++) {//column length
				System.out.print(m1[i][j] + " ");
			}
			System.out.println();
		}
		
		System.out.println("================");
		
		for(int i=0; i<m2.length; i++) {
			for(int j=0; j<m2[0].length; j++) {
				System.out.print(m2[i][j] + " ");
			}
			System.out.println();
		}
		
		System.out.println("========Multiplied========");
		
		int [][] multiplied = matrixMultiplier(m1, m2);
		
		if(multiplied != null) {
			for(int i=0; i<multiplied.length; i++) {
				for(int j=0; j<multiplied[0].length; j++) {
					System.out.print(multiplied[i][j] + " ");
				}
				System.out.println();
			}
		} else {
			System.out.println("matrices can't be multiplied or input has issue");
		}
	}

	private static int[][] matrixMultiplier(int[][] m1, int[][] m2) {
		if(m1[0].length != m2.length) {
			return null;
		}
		int C[][] = new int[m1[0].length][m2.length];
		for(int i=0; i<C.length; i++) {
			for(int j=0; j<C[0].length; j++) {
				C[i][j] = 0;
				for(int k=0; k<C.length; k++) {
					C[i][j] += m1[i][k]*m2[k][j];
				}
			}
		}

		return C;
	}

}
