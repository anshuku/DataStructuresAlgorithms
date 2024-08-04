package GraphTraversal;

import java.util.HashSet;
import java.util.Set;

/**
 * @author anshukumar
 * 
 * The time Complexity of the implementation is O(V^2). If the input graph is represented using adjacency list, 
 * it can be reduced to O(E * log V) with the help of a binary heap.
 * Dijkstra’s algorithm doesn’t work for graphs with negative weight cycles and negative edges.
 */

public class GraphDijkstraSingleSourceShortestPathAdjMatrix {

	static final int V = 9;

	public static void main(String[] args) {

		int graph[][] = new int[][] { { 0, 4, 0,  0,  0, 0,  0,  8, 0 },
									  { 4, 0, 8,  0,  0, 0,  0, 11, 0 },
									  { 0, 8, 0,  7,  0, 4,  0,  0, 2 },
									  { 0, 0, 7,  0,  9, 14, 0,  0, 0 },
									  { 0, 0, 0,  9,  0, 10, 0,  0, 0 },
									  { 0, 0, 4, 14, 10, 0,  2,  0, 0 },
									  { 0, 0, 0,  0,  0, 2,  0,  1, 6 },
									  { 8, 11,0,  0,  0, 0,  1,  0, 7 },
									  { 0, 0, 2,  0,  0, 0,  6,  7, 0 } };

		dijkstraSingleSourceShortestPath(graph, 0);
	}

	private static void dijkstraSingleSourceShortestPath(int[][] graph, int source) {

		// the shortest path tree array will be true if a vertex is included or
		// if the distance of the vertex from source is finalized
		boolean[] sptSet = new boolean[V];

		// dist array stores all the shortest distance of vertices from source vertex
		int[] dist = new int[V];

		// Initialize all distance of vertex as infinite and stpSet visited as false
		for (int i = 0; i < V; i++) {
			sptSet[i] = false;// not needed as default is false
			dist[i] = Integer.MAX_VALUE;
		}
		
		// Distance of source vertex from itself is 0
		dist[source] = 0;

		for (int count = 0; count < V - 1; count++) {
			int u = findMinDistanceUnvisitedVertex(dist, sptSet);
			sptSet[u] = true;
			
			//Update the distance of adjacent vertex of picked vertex
			for(int v = 0; v<V; v++) {
				if(!sptSet[v] && graph[u][v] != 0 && 
						(dist[u] != Integer.MAX_VALUE) &&
						(dist[u] + graph[u][v] < dist[v])) {
					dist[v] = dist[u] + graph[u][v];
				}
			}
		}
		
		System.out.println("Vertex \t\t Minimum Distance");
		for(int i = 0; i<V; i++) {
			System.out.println(i + " \t\t " + dist[i]);
		}
	}

	private static int findMinDistanceUnvisitedVertex(int[] dist, boolean[] sptSet) {
		int min = Integer.MAX_VALUE, min_index = -1;
		
		for(int i = 0; i<V; i++) {
			if(!sptSet[i] && dist[i] <= min) {
				min = dist[i];
				min_index = i;
			}
		}
		return min_index;
	}

}
