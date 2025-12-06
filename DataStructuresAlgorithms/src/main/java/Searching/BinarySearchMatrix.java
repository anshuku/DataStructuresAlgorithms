package Searching;

// Time complexity - O(log(m*n))
// Space complexity - O(1)
public class BinarySearchMatrix {

	public static void main(String[] args) {
		int[][] grid = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 }, { 13, 14, 15, 16 } };
//		int[][] grid = { { 1, 2 }, { 4, 5 } };
		int key = 4;

		int index = binarySearchMatrix(grid, key);
		System.out.printf("The index of the key %d is %d.", key, index);

	}

	private static int binarySearchMatrix(int[][] grid, int key) {
		int m = grid.length;
		int n = grid[0].length;
		int start = 0;
		int end = m * n - 1;
		int index = -1;

		while (start <= end) {
			int mid = start + (end - start) / 2;
			// We are going column wise completely to traverse the grid
			// so we divide and find remainder with column instead of row.
			int row = mid / n, col = mid % n;
			if (grid[row][col] == key) {
				return mid;
			} else if (grid[row][col] < key) {
				start = mid + 1;
			} else {
				end = mid - 1;
			}
		}
		return index;
	}
}
