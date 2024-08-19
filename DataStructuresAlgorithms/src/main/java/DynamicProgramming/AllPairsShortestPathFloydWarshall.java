package DynamicProgramming;

/**
 * @author anshukumar
 *
 * Uses Dynamic Programming as there are sequence of decisions to reach the most optimal solutions(shortest path between each vertex)
 * Floyd and Warshall Algorithm is used to find All Pairs Shortest Path between vertices having positive and negative edge weights.
 * It works for both directed and undirected weighted graphs.
 * It follows DP approach to check every possible path going via every possible nodes 
 * to calculate shortest distance between every pair of nodes.
 * 
 * It does not work for graph having negative cycles(sum of weights(edges) in the cycle is negative)
 *         
 * The algorithm, is named after it's creators Robert Floyd and Stephen Warshall
 * It's useful for network(shortest path - network routing) and connectivity problems, flight connectivity, GIS.   
 * It's better for dense graphs and not sparse graphs.
 * 
 * Dense graphs - A graph where the number of edges is way more than the vertices. O(V^3)
 * Sparse graphs - A graph having lower number of edges. Still O(V^3), Use Johnson's Algorithm for O(V^2logV + V*E).
 * 
 * It takes all the intermediate vertex/nodes(from 1 to N including start and end) between start and end node as an intermediate.
 * When a vertex number k is picked as an intermediate vertex the vertex numbers 0 to k-1 is already considered.
 * 
 * The non connected vertices are given by Integer.MAX_VALUE-100 because the a[i][k] + a[k][j]
 * will result into backward(nagative int) addition.
 * 
 * The program shows the shortest distances, it can also print the shortest path 
 * by storing predecessor information in a separate 2-D matrix.
 * 
 * Time complexity: O(V^3) where V is number of vertex.
 * Space complexity: O(1).
 */
public class AllPairsShortestPathFloydWarshall {

	public static void main(String[] args) {

		int A[][] = { { 0, 3, Integer.MAX_VALUE - 100, 7 },
					  { 8, 0, 2, Integer.MAX_VALUE - 100 },
					  { 5, Integer.MAX_VALUE - 100, 0, 1 },
					  { 2, Integer.MAX_VALUE - 100, Integer.MAX_VALUE, 0 } };
		System.out.println("Original Array is:");
		for (int i = 0; i < A.length; i++) {
			for (int j = 0; j < A[0].length; j++) {
				System.out.print(A[i][j] + " ");
			}
			System.out.println();
		}

		allPairsShortestPath(A);

		System.out.println("Array with All Pairs shortest path is");
		for (int i = 0; i < A.length; i++) {
			for (int j = 0; j < A[0].length; j++) {
				System.out.print(A[i][j] + " ");
			}
			System.out.println();
		}

	}

	private static void allPairsShortestPath(int[][] a) {
		// K is the current matrix being generated and it is the intermediate vertex
		// Add all vertices one by one to the set of intermediate vertices
		// Before start of an iteration, we've shortest distance between all pairs of vertices such that 
		// shortest distance only has vertices in set{0, 1, 2,... ,k-1} considered as intermediate.
		// After end of an iteration, vertex k is added to the set of intermediate vertices to the set considered.
		for (int k = 0; k < a.length; k++) {
			// Source vertex i
			for (int i = 0; i < a.length; i++) {
				// Destination vertex j for above picked source i
				for (int j = 0; j < a.length; j++) {
					// if vertex k is on the shortest path from i to j then update the distance i->j
					a[i][j] = Math.min(a[i][j], a[i][k] + a[k][j]);
				}
			}
		}

	}

}
