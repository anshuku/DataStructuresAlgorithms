package Revision;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class RevisionBFSDFS2D {

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

	static int[] dRow = { -1, 0, 1, 0 };
	static int[] dCol = { 0, 1, 0, -1 };

	private static boolean isValid(int x, int y, boolean[][] visited) {
		if (x < 0 || x >= ROW || y < 0 || y >= COL) {
			return false;
		}
		if (visited[x][y]) {
			return false;
		}
		return true;
	}

	public static void main(String[] args) {
		int[][] grid = { { 1, 2, 3, 4 }, 
						 { 5, 6, 7, 8 }, 
						 { 9, 10, 11, 12 }, 
						 { 13, 14, 15, 16 } };

		//breadthFirstSearch(grid, 0, 0);
		depthFirstSearch(grid, 0, 0);

	}

	private static void depthFirstSearch(int[][] grid, int x, int y) {
		boolean [][] visited = new boolean[ROW][COL];
		
		Stack<Pair> s = new Stack<>();
		s.push(new Pair(x, y));
		
		while(!s.empty()) {
			Pair p = s.pop();
			x = p.first;
			y = p.second;
			
			if(!visited[x][y]) {
				visited[x][y] = true;
				System.out.println("The element is " + grid[x][y]);
			}
			
			for(int i = 0; i<ROW; i++) {
				int xVal = x + dRow[i];
				int yVal = y + dCol[i];
				
				if(isValid(xVal, yVal, visited)) {
					s.push(new Pair(xVal, yVal));
				}
			}
		}
	}

	private static void breadthFirstSearch(int[][] grid, int i, int j) {
		Queue<Pair> q = new LinkedList<>();
		boolean[][] visited = new boolean[ROW][COL];

		q.add(new Pair(i, j));
		visited[i][j] = true;

		while (!q.isEmpty()) {
			Pair p = q.poll();
			int x = p.first;
			int y = p.second;
			System.out.println("The current element is " + grid[x][y]);

			for (int k = 0; k < ROW; k++) {
				int xVal = x + dRow[k];
				int yVal = y + dCol[k];

				if (isValid(xVal, yVal, visited)) {
					visited[xVal][yVal] = true;
					q.add(new Pair(xVal, yVal));
				}
			}
		}
	}

}
