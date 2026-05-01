package GraphTraversal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

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
public class AStarSearch {

	static class Cell {
		int parentI;
		int parentJ;

		double f;
		double g;
		double h;
	}

	static class Node {
		int i;
		int j;

		double f;
	}

	private static int ROW;
	private static int COL;

	private static final int[][] direction = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 }, { 1, 1 }, { 1, -1 }, { -1, 1 },
			{ -1, -1 } };

	public static void main(String[] args) {
		int[] src = { 8, 0 };
		int[] dest = { 0, 0 };

		int[][] grid = { { 1, 0, 1, 1, 1, 1, 0, 1, 1, 1 }, { 1, 1, 1, 0, 1, 1, 1, 0, 1, 1 },
				{ 1, 1, 1, 0, 1, 1, 0, 1, 0, 1 }, { 0, 0, 1, 0, 1, 0, 0, 0, 0, 1 }, { 1, 1, 1, 0, 1, 1, 1, 0, 1, 0 },
				{ 1, 0, 1, 1, 1, 1, 0, 1, 0, 0 }, { 1, 0, 0, 0, 0, 1, 0, 0, 0, 1 }, { 1, 0, 1, 1, 1, 1, 0, 1, 1, 1 },
				{ 1, 1, 1, 0, 0, 0, 1, 0, 0, 1 } };

		ROW = grid.length;
		COL = grid[0].length;

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

		int i = src[0];
		int j = src[1];

		cellDetails[i][j].f = 0;
		cellDetails[i][j].g = 0;
		cellDetails[i][j].h = 0;

		cellDetails[i][j].parentI = i;
		cellDetails[i][j].parentJ = j;

		// In a perfect data structure like Fibonacci heap. decreaseKey(node, newF),
		// updates priority in-place while keeping heap valid in O(1) time.
		// Java PriorityQueue cannot update a node's priority, so we insert duplicate
		// pq.offer(new Node(x, y, newBetterCost)) and ignore old entry.
		// Node curr = pq.poll() if (visited[curr.x][curr.y]) continue;. It's lazy
		// deletion where old worse entries remain in heap and we skip them when popped.
		PriorityQueue<Node> openList = new PriorityQueue<>((a, b) -> Double.compare(a.f, b.f));
		Node node = new Node();
		node.i = i;
		node.j = j;
		node.f = 0;
		openList.offer(node);

		boolean[][] closedList = new boolean[ROW][COL];

		boolean destFound = false;

		while (!openList.isEmpty()) {
			Node cell = openList.poll();

			i = cell.i;
			j = cell.j;

			// The same node may appear multiple times. One may process stale worse node.
			// For lazy deletion skip it.
			if (closedList[i][j]) {
				continue;
			}

			closedList[i][j] = true;

			double fNew, gNew, hNew;
			for (int[] dir : direction) {
				int x = i + dir[0];
				int y = j + dir[1];

				double add = (dir[0] == 0 || dir[1] == 0) ? 1.0 : Math.sqrt(2);

				if (isValid(x, y)) {
					if (x == dest[0] && y == dest[1]) {
						System.out.println("The destination is found");
						cellDetails[x][y].parentI = i;
						cellDetails[x][y].parentJ = j;

						destFound = true;
						tracePath(cellDetails, dest);
						return;
					} else if (!closedList[x][y] && isUnblocked(grid, x, y)) {
						gNew = cellDetails[i][j].g + add;
						hNew = getHeuristic(x, y, dest);
						fNew = gNew + hNew;

						if (cellDetails[x][y].f == Double.POSITIVE_INFINITY || cellDetails[x][y].f > fNew) {
							Node newNode = new Node();
							newNode.i = x;
							newNode.j = y;
							newNode.f = fNew;

							openList.offer(newNode);

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
		if (!destFound) {
			System.out.println("The destination is not found");
		}
	}

	// Euclidean distance heuristic
	private static double getHeuristic(int x, int y, int[] dest) {
		return Math.sqrt((x - dest[0]) * (x - dest[0]) + (y - dest[1]) * (y - dest[1]));
	}

	private static void tracePath(Cell[][] cellDetails, int[] dest) {
		Set<int[]> pathSet = new LinkedHashSet<>();

		int x = dest[0], y = dest[1];
		while (!(cellDetails[x][y].parentI == x && cellDetails[x][y].parentJ == y)) {
			pathSet.add(new int[] { x, y });

			int tempX = cellDetails[x][y].parentI;
			int tempY = cellDetails[x][y].parentJ;

			x = tempX;
			y = tempY;
		}
		pathSet.add(new int[] { x, y });

		List<int[]> paths = new ArrayList<>(pathSet);

		Collections.reverse(paths);

		System.out.println("The path is");
		paths.forEach(path -> {
			System.out.print("->(" + path[0] + ", " + path[1] + ")");
		});
		System.out.println();
	}

	private static boolean isUnblocked(int[][] grid, int row, int col) {
		return grid[row][col] == 1;
	}

	private static boolean isValid(int row, int col) {
		return row >= 0 && row < ROW && col >= 0 && col < COL;
	}

}
