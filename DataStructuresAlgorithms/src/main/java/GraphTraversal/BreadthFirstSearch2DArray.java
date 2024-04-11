package GraphTraversal;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author anshukumar
 * 
 *         Time Complexity: O(N * M) Auxiliary Space: O(N * M)
 */

public class BreadthFirstSearch2DArray {

	static class Pair {
		int first;
		int second;

		Pair(int first, int second) {
			this.first = first;
			this.second = second;
		}

	}

	static int ROW = 4;
	static int COL = 4;

//	static int dRow[] = { -1, 0, 1, 0 };
//	static int dCol[] = { 0, 1, 0, -1 };
	static int dRow[] = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int dCol[] = { 0, 1, 1, 1, 0, -1, -1, -1 };

	public static boolean isValid(int row, int col, boolean[][] visited) {

		if (row < 0 || col < 0 || row >= ROW || col >= COL) {
			return false;
		}

		if (visited[row][col]) {
			return false;
		}
		return true;

	}

	public static void main(String[] args) {

		int grid[][] = { { 1, 2, 3, 4 }, 
						 { 5, 6, 7, 8 }, 
						 { 9, 10, 11, 12 }, 
						 { 13, 14, 15, 16 } 
						 };

		boolean visited[][] = new boolean[ROW][COL];

		breadthFirstSearch(grid, visited, 0, 0);

	}

	private static void breadthFirstSearch(int[][] grid, boolean[][] visited, int x, int y) {

		Queue<Pair> q = new LinkedList<>();
		q.add(new Pair(x, y));
		visited[x][y] = true;

		while (!q.isEmpty()) {
			Pair p = q.poll();
			x = p.first;
			y = p.second;
			System.out.print(grid[x][y] + " ");

			for (int i = 0; i < 8; i++) {
				int xVal = x + dRow[i];
				int yVal = y + dCol[i];

				if (isValid(xVal, yVal, visited)) {
					q.add(new Pair(xVal, yVal));
					visited[xVal][yVal] = true;
				}
			}
		}
	}

}
