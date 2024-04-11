package Backtracking;

public class NQueens {

	public static void main(String[] args) {
		
		int [][] c = new int[4][4];
		int [][] a = new int[4][4];
		int count = 0;
		//int num = numberOfArrangements(c, 0, 0);

	}

	private static int numberOfArrangements(int[][] c, int i, int j, int count) {
		while(i<4 && j<4) {
			
			fillArr(c, i, j);
			if(isValid(c)) {
				
			}
		}
		return 0;
	}

	private static boolean isValid(int[][] c) {
		
		//while()
		return false;
	}

	private static void fillArr(int[][] c, int i, int j) {
		
		//for(int i)
		
	}

}
