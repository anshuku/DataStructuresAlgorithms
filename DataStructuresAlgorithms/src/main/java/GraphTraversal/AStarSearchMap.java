package GraphTraversal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/*
 * A-Star / A* Algorithm
 * 
 * This algo is used to approximate the shortest path in real-life situatations (map, games where there are hindrance).
 * We can consider a 2D grid having several obstacles and we start from a source cell to reach towards goal cell.
 * 
 * What? - This is one of the best and most popular technique used in path-finding and graph traversal.
 * 
 * Why? - A* algorithms, unlike other traversal techniques, has "brains" and is smart. Many games and web-based maps
 * use this algorthm to find the shortest path very efficiently(approximation).
 * 
 * Explanation - Consider a square grid with many obstacles and we're given starting cell and target cell.
 * We want to reach target cell(if possible) quickly. With A* algorithm at each step it picks the node/cell having the 
 * lowest 'f' value which is = 'g' + 'h' and it process that node/cell.
 * 
 * g = The movement cost to move from the starting point to a given(current) square on the grid. following the path
 * generated to get there.
 * 
 * h = heurestic = The estimated movement cost to move from that given(current) square on the grid to the final cell.
 * It's nothing but a smart guess. The actual distance can differ due to various things(objects, walls, water, etc.)
 * It can be calculated in many wats.
 * 
 * Algorithm:
 * We create 2 lists - Open List and Closed List(like Dijkstra Algorithm?)
 * 1. Initialize the open list and put the starting node inside it(can leave its f at 0).
 * 2. Initalize the closed list
 * 3. While the open list is not empty
 * > a) Find the node with the least f on the open list, called q.
 * > b) Pop q off the open list
 * > c) Generate q's 8 successors and set their parents to q.
 * > d) For each successor
 * > > i) If successor is the goal, stop search
 * > > ii) else, compute both g and h for successor
 * > > successor.g = q.g + distance between successor and q.
 * > > successor.h = distance from goal to successor (3 heuristics - Manhattan, Diagonal, Eucledean)
 * > > successor.f = successor.g + successor.h
 * > > iii) If a node with the same position as successor is in OPEN list which as a lower f than successor,
 * > > skip the successor.
 * > > iv) If a node with the same position as successor is in the CLOSED which has a lower f than successor,
 * > > skuo the successor. Otherwise, add the node to the open list.
 * > end (for loop)
 * > e) Push q on the closed list
 * > end (while loop)
 * 
 * A* algorithm makes the most intelligent choice at each step.
 * 
 * Heuristics - We can get g but to get h: 
 * A) Either calculate exact value of h(time consuming)
 * B) Approximate the value of h using some heuristics(takes less time)
 * 
 * A) Exact heuristics: There are some methods to find it but generally that is time consuming.
 * > 1) Pre-compute the distance between each pair of cells before running the A* algo.
 * > 2) If there are no blocked cells/obstacles then we can just find the exact h without pre-computation
 * > via distance forumla/Euclidean Distance.
 * B) Approximation heuristics: There are 3 approximation heuristics to find h:
 * > 1) Manhattan Distance - It's the sum of absolute values of differences in the goal's x and y and current cell's x and y.
 * > h = abs(current.x - goal.x) + abs(current.y - goal.y). We use it when we move in only 4 directions.
 * > 2) Diagonal Distance - It's the max of absolute values of differences in the goal's x and y and current cell's x and y.
 * > dx = ab(current.x - goal.x) | dy = abs(current.y - goal.y).
 * > h = D*(dx + dy) + (D2 - 2*D)*min(dx, dy), where D=length of each node(~1) and D2=diagonal distance between nodes(~rt2).
 * > Above formula considers different costs for cardinal(D) and diagonal(D2) moves. It always follow D2 >= D and D2 <= D.
 * > Chebyshev Distance(D = 1, D2 = 1): Used when diagonal moces cost the same as cardinal moves. h(n) = max(dx, dy).
 * > Octile Distance(D = 1, D2 = rt2): Used when diagonal moves are more expensive. Ir represents the actual geometric distance.
 * > Integer Approximation: To avoid floating-point calculations, one can scale the costs by 10, D = 10, D2 = 14.
 * > We use it, when it's allowed to move in 8 directions only(King  moves in Chess).
 * > 3) Euclidean Distance - It's the distance between the current cell and the goal cell using distance formula:
 * > h = sqrt((current.x - goal.x)^2 + (current.y - goal.y)^2). We use it when it's allowed to move in any direction.
 * 
 * Dijksta is a special case of A* algo, where h = 0 for all nodes.
 * 
 * Implementation: 
 * We use set data structure for open list and boolean hash table/array for closed list.
 * The implementation is similar to Dijkstra's algo. We use a Fibonacci heap to implement the open list instead of
 * binary heap/self-balancing tree, as it takes O(1) averabe time to insert into open list and to decrease key.
 * To reduce the time taken to calculate g, we can use Dynamic programming.
 * 
 * Limitations:
 * Even though it's the best path finding algorithm, it doesn't produce the shortest path always, due to heavy
 * reliance on heuristics/approximations to calculate - h.
 * 
 * Applications:
 * We can use this when the search space is not a grid and is a graph, just like for a 2D grid.
 * 
 * Time complexity - In a graph, it may take one to travel all the edge to reach the destination cell from the source cell.
 * So, worst case time complexity is O(E), where E = number of edges.
 * 
 * Auxiliary space - In worst case we can have all the edges inside the open list, so it's O(V), where V=number of vertices.
 * 
 * Summary: 
 * When to use BFS over A* and Dijkstra over A* to find the shortest paths:
 * 1) 1 source and 1 destination - Use A* search algo for unweighted as well as weighted graphs.
 * 2) 1 source, all destination - 
 * > Use BFS for Unweighted Graphs
 * > Use Dijkstra for Weighted Graphs without negative weights
 * > Use Bellman Ford for Weighted Graphs with negative weights
 * 3) Between every pair of nodes - Floyd-Warshall, Johnson's algorithm.
 */
public class AStarSearchMap {

	static class Cell {
		int parentI, parentJ;
		double f, g, h;

		Cell() {
			this.parentI = 0;
			this.parentJ = 0;
			this.f = 0;
			this.g = 0;
			this.h = 0;
		}
	}

	private static final int ROW = 9;
	private static final int COL = 10;

	private static final int[][] direction = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 }, { 1, 1 }, { 1, -1 }, { -1, 1 },
			{ -1, -1 } };

	public static void main(String[] args) {

		// 1 = cell not blocked
		// 0 = cell blocked
		int[][] grid = { { 1, 0, 1, 1, 1, 1, 0, 1, 1, 1 }, { 1, 1, 1, 0, 1, 1, 1, 0, 1, 1 },
				{ 1, 1, 1, 0, 1, 1, 0, 1, 0, 1 }, { 0, 0, 1, 0, 1, 0, 0, 0, 0, 1 }, { 1, 1, 1, 0, 1, 1, 1, 0, 1, 0 },
				{ 1, 0, 1, 1, 1, 1, 0, 1, 0, 0 }, { 1, 0, 0, 0, 0, 1, 0, 0, 0, 1 }, { 1, 0, 1, 1, 1, 1, 0, 1, 1, 1 },
				{ 1, 1, 1, 0, 0, 0, 1, 0, 0, 1 } };

		// Source is the left-most bottom-most corner
		int[] src = { 8, 0 };

		// Destination is the left-most top-most corner
		int[] dest = { 0, 0 };

		aStarSearch(grid, src, dest);
	}

	private static void aStarSearch(int[][] grid, int[] src, int[] dest) {
		if (!isValid(src[0], src[1]) || !isValid(dest[0], dest[1])) {
			System.out.println("The source or destination is invalid");
			return;
		}

		if (!isUnblocked(grid, src[0], src[1]) || !isUnblocked(grid, dest[0], dest[1])) {
			System.out.println("The source or destination is blocked");
			return;
		}
		if (src[0] == dest[0] && src[1] == dest[1]) {
			System.out.println("The source is the destination.");
			return;
		}

		boolean[][] closedList = new boolean[ROW][COL];
		Cell[][] cellDetails = new Cell[ROW][COL];

		for (int i = 0; i < ROW; i++) {
			for (int j = 0; j < COL; j++) {
				cellDetails[i][j] = new Cell();

				cellDetails[i][j].f = Double.POSITIVE_INFINITY;
				cellDetails[i][j].g = Double.POSITIVE_INFINITY;
				cellDetails[i][j].h = Double.POSITIVE_INFINITY;

				cellDetails[i][j].parentI = -1;
				cellDetails[i][j].parentJ = -1;
			}
		}

		int i = src[0], j = src[1];
		cellDetails[i][j].f = 0;
		cellDetails[i][j].g = 0;
		cellDetails[i][j].h = 0;

		cellDetails[i][j].parentI = i;
		cellDetails[i][j].parentJ = j;

		// In A* and Dijkstra's algorithm, decrease-key means: We found a
		// better(smaller) cost for a node already in the priority queue -> update its
		// priority. We check if a better path exists but then: Insert a new entry,
		// don't remove/update old one. Here there is no priority ordering (we manually
		// scan → O(n)). Duplicate f values overwrite nodes, no decrease key possible.
//		Map<Double, int[]> openList = new HashMap<>();
		Map<Double, List<int[]>> openList = new TreeMap<>();
		List<int[]> coordinates = new ArrayList<>();
		coordinates.add(new int[] { i, j });
		openList.put(0.0, coordinates);

		boolean foundDest = false;

		while (!openList.isEmpty()) {
//			Map.Entry<Double, int[]> p = openList.entrySet().iterator().next();
//
//			for (Map.Entry<Double, int[]> q : openList.entrySet()) {
//				if (q.getKey() < p.getKey()) {
//					p = q;
//				}
//			}
//
//			openList.remove(p.getKey());

			Map.Entry<Double, List<int[]>> entry = ((TreeMap<Double, List<int[]>>) openList).firstEntry();

			int[] cell = entry.getValue().remove(0);

			if (entry.getValue().isEmpty()) {
				openList.remove(entry.getKey());
			}
			i = cell[0];
			j = cell[1];

			if (closedList[i][j]) {
				continue;
			}

			closedList[i][j] = true;

			double gNew, hNew, fNew;

			for (int[] dir : direction) {
				int x = i + dir[0];
				int y = j + dir[1];

				double add = 1.414;
				if (dir[0] == 0 || dir[1] == 0) {
					add = 1;
				}

				if (isValid(x, y)) {
					if (x == dest[0] && y == dest[1]) {
						cellDetails[x][y].parentI = i;
						cellDetails[x][y].parentJ = j;

						System.out.println("The destination cell is found");
						tracePath(cellDetails, dest);
						foundDest = true;
						return;
					} else if (!closedList[x][y] && isUnblocked(grid, x, y)) {
						gNew = cellDetails[i][j].g + add;
						hNew = calculateHValue(x, y, dest);
						fNew = gNew + hNew;

						// cellDetails[x][y].f > fNew is where logical decrease-key check occurs
						// We found a better path -> should decrease key. Here actual update is
						// incorrect as we should do decreaseKey(exisiting node) instead of insert new
						// node.
						if (cellDetails[x][y].f == Double.POSITIVE_INFINITY || cellDetails[x][y].f > fNew) {
							// Can cause bug as 2 nodes can have same fNew
							// HashMap will overwrite previous entry and one can lose nodes which leads to
							// incorrect paths.
//							openList.put(fNew, new int[] { x, y });
							// Below will not overwrite in case of same key = f.
							openList.computeIfAbsent(fNew, k -> new ArrayList<>()).add(new int[] { x, y });

							cellDetails[x][y].f = fNew;
							cellDetails[x][y].g = gNew;
							cellDetails[x][y].h = hNew;

							cellDetails[x][y].parentI = i;
							cellDetails[x][y].parentJ = j;
						}
					}
				}
			}
		}
		if (!foundDest) {
			System.out.println("Failed to find the destination cell");
		}
	}

	private static void tracePath(Cell[][] cellDetails, int[] dest) {
		int row = dest[0], col = dest[1];

		// We can use a LinkedHashSet
		Map<int[], Boolean> path = new LinkedHashMap<>();

		while (!(cellDetails[row][col].parentI == row && cellDetails[row][col].parentJ == col)) {
			path.put(new int[] { row, col }, true);

			int tempRow = cellDetails[row][col].parentI;
			int tempCol = cellDetails[row][col].parentJ;

			row = tempRow;
			col = tempCol;
		}

		path.put(new int[] { row, col }, true);

		List<int[]> pathList = new ArrayList<>(path.keySet());
		Collections.reverse(pathList);

		System.out.println("The path is");
		pathList.forEach(p -> {
			System.out.print("-> (" + p[0] + ", " + p[1] + ")");
		});
		System.out.println();
	}

	private static boolean isValid(int x, int y) {
		return x >= 0 && x < ROW && y >= 0 && y < COL;
	}

	private static boolean isUnblocked(int[][] grid, int x, int y) {
		return grid[x][y] == 1;
	}

	private static double calculateHValue(int x, int y, int[] dest) {
		return Math.sqrt((x - dest[0]) * (x - dest[0]) + (y - dest[1]) * (y - dest[1]));
	}

}
