package GraphTraversal;

import java.util.Arrays;

/*
 * It's used in case of Sparse graphs which may have negative weights utilizing both Bellman Ford's and and Dijkstra's algorithm.
 * If Dijkstra's algorithm is applied for each vertex considering it as source, then it takes O(V*VlogV) time
 * But Dijkstra's algorithm doesn't work on negative weight edges. With Johnson's algorithm all the edges are reweighted 
 * to make it non negative via Bellman Ford Algorithm and then Dijkstra's algorithm is applied.
 * In case of a sparse graph (not complete where Floyd Warshall Algorithm takes O(V^3) time instead of O(V*E) since E = V^2)
 * 
 * Working:
 * Add an extra edge s with directed edge connecting towards all vertices to a modified graph. The edges from s have 0 weight.
 * Apply Bellman ford algorithm with s as source to get the shortest distance of s to all other vertices as h[] value.
 * It assigns a weight to every vertex from the table h[].
 * Remove the edge s and for each edge (u, v) in original graph add the corresponding h[u] - h[v] vertex weight values.
 * New weight = w(u,v) + h[u] - h[v]. Also, h[v] <= h[u] + w(u,v)
 * The property means the shortest distance from s to vis smaller than or equal to shortest distance from s to u plus w(u,v).
 * All set of paths between two vertices is increased by same amount such that new weight becomes non negative. 
 * This is done in a separate graph excluding INF to the given weight(u,v) to make the weights positive.
 * Apply Dijkstra's algorithm for all the vertices to get single source shortest path in V*V(logV) time.
 * 
 * The Bellman Ford algorithm is called once and Dijkstra's algorithm is called V times.
 * The time complexity of Bellman Ford algorithm is O(V*E) and time complexity of Dijkstra is O(V*logV)
 * Time complexity: O(V^2logV + V*E) for sparse graph. For dense graph, TC from Bellman Ford is O(V*V^2) since E = V^2.
 * Auxiliary space: O(V^2)
 */
public class AllPairsShortestPathJohnson {

	private static final int INF = Integer.MAX_VALUE;

	public static void main(String[] args) {
		// Define a graph with possible negative weight;

		int[][] graph= {
				{0, -5, 2, 3},
				{INF, 0, 4, INF},
				{INF, INF, 0, 1},
				{INF, INF, INF, 0}
		};

		johnsonAllPairsShortestPath(graph);
	}

	private static void johnsonAllPairsShortestPath(int[][] graph) {
		// Number of vertices
		int V = graph.length;
		// Stores edges in terms of start and end vertex and the weight in the 3rd index
		int[][] edges = new int[V * V][3];

		int index = 0;
		// Collect all the edges from the graph
		for (int i = 0; i < V; i++) {
			for (int j = 0; j < V; j++) {
				if (graph[i][j] != 0) { 
					edges[index++] = new int[] { i, j, graph[i][j] };
				}
			}
		}
		
		// Get modified weights to remove negative weights using Bellman Ford
		int[] alteredWeights = bellmanFordAlgorithm(edges, V);

		System.out.println("The altered weights is " + Arrays.toString(alteredWeights));

		int[][] alteredGraph = new int[V][V];

		// Modify the weights of the edges to ensure all weights are non-negative.
		for (int i = 0; i < V; i++) {
			for (int j = 0; j < V; j++) {
				if (graph[i][j] != INF) {
					alteredGraph[i][j] = graph[i][j] + alteredWeights[i] - alteredWeights[j];
				} else {
					alteredGraph[i][j] = INF;
				}
			} 
		}

		System.out.println("The modified graph is:");
		for (int[] edge : alteredGraph) {
			System.out.println(Arrays.toString(edge));
		}

		// All the weights are positive now
		// Run dijkstra's algorithm with each vertex as the source
		for (int source = 0; source < V; source++) {
			System.out.println("Shortest distance with source at vertex " + source);
			dijkstraAlgorithm(graph, alteredGraph, source);
		}
	}

	// Performs shortest distance from source vertex to all the vertices
	private static int[] bellmanFordAlgorithm(int[][] edges, int V) {
		int[] dist = new int[V + 1]; // Distance array with an extra vertex
		Arrays.fill(dist, INF);
		dist[V] = 0; // Distance of new source vertex added is 0

		// Add edges from the new source vertex to all the other vertices
		int[][] edgesWithExtra = Arrays.copyOf(edges, edges.length + V);
		for (int i = 0; i < V; i++) {
			edgesWithExtra[edges.length + i] = new int[] { V, i, 0 };
		}

		// Relax all edges |V| - 1 times 
		for (int i = 0; i < V; i++) {// since total vertex is V+1 now so < V
			for (int[] edge : edgesWithExtra) {
				if (dist[edge[0]] != INF && dist[edge[0]] + edge[2] < dist[edge[1]]) {
					dist[edge[1]] = dist[edge[0]] + edge[2];
				}
			}
		}

		return Arrays.copyOf(dist, V);// Returns distances excluding newly added vertex V
	}

	private static void dijkstraAlgorithm(int[][] graph, int[][] alteredGraph, int source) {
		int V = graph.length; // Number of vertices
		int[] dist = new int[V]; // Distance array to store shortest distance from source
		boolean[] sptSet = new boolean[V]; // Boolean array to track visited vertices
		
		// Initialize distances with infinity and source distance as 0
		Arrays.fill(dist, INF);
		dist[source] = 0;
		
		// Compute shortest path for all vertices
		for(int count = 0; count < V-1; count++) {
			// Pick the vertex with min distance that hasn't been visited.
			int u = minDistance(dist, sptSet);
			sptSet[u] = true; // Mark this vertex as visited
			
			// Update distance value for adjacent vertices
			for(int v = 0; v<V; v++) {
				
				//Check for updates to the distance values
				if(!sptSet[v] && graph[u][v] != 0 && dist[u] != INF && dist[u] + alteredGraph[u][v] < dist[v]) {
					dist[v] = dist[u] + alteredGraph[u][v];
				}
				
			}
		}
		
		System.out.println("The distance array: " + Arrays.toString(dist));
		
		// Print the shortest distances from the source vertex
		System.out.println("The shortest distance from source: " + source);
		for(int i =0; i<V; i++) {
			System.out.println("Vertex " + i + ": " + (dist[i] == INF ? "INF" : dist[i]));
		}
		
	}

	// Function to find the vertex with the minimum distance the 
    // source that has not been included in the shortest path tree
	private static int minDistance(int[] dist, boolean[] sptSet) {
		int min = INF, minIndex = -1;
		
		// Update minIndex if a smaller distance is found
		for(int v = 0; v < dist.length; v++) {
			if(!sptSet[v] && dist[v] <= min) {
				min = dist[v];
				minIndex = v;
			}
		}
		return minIndex;
	}
}
