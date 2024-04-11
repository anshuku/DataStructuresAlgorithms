package GraphTraversal;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class DepthFirstSearch2DArray {

	public static class Pair {
		public int first;
		public int second;

		Pair(int first, int second) {
			this.first = first;
			this.second = second;
		}

	}

	static int ROW = 4;
	static int COL = 4;

	static int[] dRow = { -1, 0, 1, 0 };
	static int[] dCol = { 0, 1, 0, -1 };

	public static void main(String... args) {

		int[][] arr = { { 1, 2, 3, 4 }, 
						{ 5, 6, 7, 8 }, 
						{ 9, 10, 11, 12 }, 
						{ 13, 14, 15, 16 } };

		System.out.println("The elements are");
		depthFirstSearch(arr, 0, 0);

	}

	private static void depthFirstSearch(int[][] arr, int x, int y) {

		Stack<Pair> s = new Stack<Pair>();
		boolean[][] visited = new boolean[ROW][COL];

		s.push(new Pair(x, y));

		while (!s.isEmpty()) {
			Pair p = s.pop();
			x = p.first;
			y = p.second;
			if(!visited[x][y]) {
				visited[x][y] = true;
				System.out.print(arr[x][y] + " ");
			}
			
			for (int i = 0; i < 4; i++) {
				int xVal = x + dRow[i];
				int yVal = y + dCol[i];

				if (isValid(xVal, yVal, visited)) {
					s.push(new Pair(xVal, yVal));
				}
			}
		}

	}

	private static boolean isValid(int x, int y, boolean[][] visited) {
		if (x < 0 || y < 0 || x >= ROW || y >= COL) {
			return false;
		}
		if (visited[x][y]) {
			return false;
		}
		return true;
	}

}
