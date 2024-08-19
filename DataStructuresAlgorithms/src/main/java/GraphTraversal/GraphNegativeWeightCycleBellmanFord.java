package GraphTraversal;

import java.util.Arrays;

/*
 * Time Complexity: O(V*E) for connected graphs and O(E*V^2) for disconnected graphs
 * Auxiliary space: O(V) for dist[]
 */
public class GraphNegativeWeightCycleBellmanFord {

	final int INF = Integer.MAX_VALUE;

	// Represents a directed, weighted and connected graph
	static class Graph {
		int V;
		int E;

		// Graph is an array of edges
		Edge[] edges;
	}

	// Represents a weighted edge in a graph
	static class Edge {
		int src, dest, weight;
	}

	private static Graph createGraph(int V, int E) {
		Graph graph = new Graph();
		graph.V = V;
		graph.E = E;

		graph.edges = new Edge[E];

		for (int i = 0; i < E; i++) {
			graph.edges[i] = new Edge();
		}

		return graph;
	}

	public static void main(String[] args) {

		int V = 5, E = 8;

		Graph graph = createGraph(V, E);

		// Add edge 0-1 (or A-B in above figure)
		graph.edges[0].src = 0;
		graph.edges[0].dest = 1;
		graph.edges[0].weight = -1;

		// Add edge 0-2 (or A-C in above figure)
		graph.edges[1].src = 0;
		graph.edges[1].dest = 2;
		graph.edges[1].weight = 4;

		// Add edge 1-2 (or B-C in above figure)
		graph.edges[2].src = 1;
		graph.edges[2].dest = 2;
		graph.edges[2].weight = 3;

		// Add edge 1-3 (or B-D in above figure)
		graph.edges[3].src = 1;
		graph.edges[3].dest = 3;
		graph.edges[3].weight = 2;

		// Add edge 1-4 (or B-E in above figure)
		graph.edges[4].src = 1;
		graph.edges[4].dest = 4;
		graph.edges[4].weight = 2;

		// Add edge 3-2 (or D-C in above figure)
		graph.edges[5].src = 3;
		graph.edges[5].dest = 2;
		graph.edges[5].weight = 5;

		// Add edge 3-1 (or D-B in above figure)
		graph.edges[6].src = 3;
		graph.edges[6].dest = 1;
		graph.edges[6].weight = 1;

		// Add edge 4-3 (or ED in above figure)
		graph.edges[7].src = 4;
		graph.edges[7].dest = 3;
		graph.edges[7].weight = -3;

		GraphNegativeWeightCycleBellmanFord gnbf = new GraphNegativeWeightCycleBellmanFord();

		boolean isNegativeCycle = gnbf.detectNegativeCycleDisconnected(graph);

		System.out.println("There is a negative cycle? " + isNegativeCycle);

	}

	// Returns true if the given graph has negative weight cycle.
	private boolean detectNegativeCycleDisconnected(Graph graph) {

		int V = graph.V;

		// Array filled by bellman ford algorithm
		int[] dist = new int[V];

		// It keeps track of visited vertices to avoid recomputations
		boolean[] visited = new boolean[V];

		// Call bellman ford algorithm for unvisited vertices
		// The for loop takes care of disconnected vertices as well.
		for (int i = 0; i < V; i++) {
			if (!visited[i]) {

				if (isNegativeCycleBellmanFord(graph, i, dist)) {
					return true;
				}

				// Mark all vertices which are visited
				for (int j = 0; j < V; j++) {
					if (dist[j] != INF) {
						visited[j] = true;
					}
				}
			}
		}
		return false;
	}

	// Finds shortest distance from source to all other vertices and also detects
	// negative weight cycle
	private boolean isNegativeCycleBellmanFord(Graph graph, int source, int[] dist) {

		int V = graph.V;
		int E = graph.E;

		Arrays.fill(dist, INF);
		dist[source] = 0;

		// Relax all edges |V| - 1 times, a simple shortest path from source to any
		// other vertices can have at most |V| - 1 edges
		for (int i = 1; i < V; i++) {
			for (int j = 0; j < E; j++) {
				int src = graph.edges[j].src;
				int dest = graph.edges[j].dest;
				int weight = graph.edges[j].weight;

				if (dist[src] != INF && dist[src] + weight < dist[dest]) {
					dist[dest] = dist[src] + weight;
				}
			}
		}

		// If there is a shorter path then there is a cycle.
		for (int j = 0; j < E; j++) {
			int src = graph.edges[j].src;
			int dest = graph.edges[j].dest;
			int weight = graph.edges[j].weight;

			if (dist[src] != INF && dist[src] + weight < dist[dest]) {
				return true;
			}
		}

		System.out.println("At iteration: " + source + " the distance array is " + Arrays.toString(dist));
		return false;
	}

}
