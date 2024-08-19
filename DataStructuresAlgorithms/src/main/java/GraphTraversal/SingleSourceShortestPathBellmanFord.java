package GraphTraversal;

import java.util.Arrays;

/*
 * It's a single source shortest path algorithm which is used to find shortest path between a source and every other vertices.
 * It can be used for both weighted and unweighted graph as well as graphs having negative edge weights.
 * It's useful to detect negative weight cycle where the shortest path cannot be found.
 * 
 * It starts with a source node and finds distance to each node(maybe infinite).
 * The long distance paths are relaxed eventually as the shorter paths are found.
 * It's based on principle of relaxation of edges - for graph having N vertices, all edges should be relaxed N-1 times for sssp.
 * To detect a negative weight cycle, relax the edges one more time and if the distance for any node reduces, there is a cycle.
 * This is because certain edge having negative weight have been traversed once more in Nth iteration.
 * 
 * All edges should be relaxed N-1 times, since in worst case, the shortest distance between 2 vertices can have at most N-1 edges.
 * By relaxing the edges N-1 times, the algorithm ensures that the distances estimates of all vertices 
 * have been updated to their optimal values. Assuming there is no negative weight cycle reachable from source vertex.
 * It guarantees that algorithm has covered all possible paths of length upto N-1, max length of shortest path with N edges.
 * In the N-1 relaxations, we assume each vertex have been traversed only once. 
 * The reduction in distance in Nth relaxation indicates revisiting a vertex.
 * 
 * Working:
 * Initialize a dist[] with all distance as INF except source with 0 distance.
 * Then use the adjacency matrix to relax all the connected nodes via an edge from the source.
 * 
 * The algorithm might not work for disconnected graphs. It works if all the vertices are reachable from source vertex 0.
 * In this case run the algorithm for unvisited vertices having INF distance.
 * 
 * Time complexity for connected graph: 
 * O(E) in best case when there is no change between 1st and 2nd relaxation in dist[], stop the process
 * O(V*E) in average case
 * O(V*E) in worst case
 * 
 * Time complexity for disconnected graph: 
 * O(E*V^2) in all cases
 * 
 * Applications: In network routing, GPS navigation, Transportation and logistics, game development, robotics and Autonomous vehicles
 */
public class SingleSourceShortestPathBellmanFord {

	private static final int INF = Integer.MAX_VALUE;

	// A class for connected, directed, weighted Graph.
	static class Graph {

		int V;
		int E;
		Edge[] edges;

		Graph(int v, int e) {
			this.V = v;
			this.E = e;
			edges = new Edge[E];

			for (int i = 0; i < E; i++) {
				edges[i] = new Edge();
			}
		}
	}

	// A class for weighted edge in a graph
	static class Edge {
		int src;
		int dest;
		int weight;

		Edge() {
			src = dest = weight = 0;
		}

	}

	public static void main(String[] args) {

		int V = 5;// Total vertices is 5
		int E = 8;// Total edges is 8
		Graph graph = new Graph(V, E);

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

		SingleSourceShortestPathBellmanFord ssbf = new SingleSourceShortestPathBellmanFord();

		ssbf.bellmanFord(graph, 0);
	}

	// It finds the shortest distance from source to all other vertices.
	private void bellmanFord(Graph graph, int source) {

		int V = graph.V;
		int E = graph.E;

		int[] dist = new int[V];
		Arrays.fill(dist, INF);

		dist[source] = 0;

		// Relax all the edges |V| - 1 times
		// A simple shortest path from source to any other vertex can have at most |V|-1 edges
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

		printGraph(dist, V);

		// If there is a shorter path then there is a cycle. In O(E) TC.
		for (int j = 0; j < E; j++) {
			int src = graph.edges[j].src;
			int dest = graph.edges[j].dest;
			int weight = graph.edges[j].weight;

			if (dist[src] != INF && dist[src] + weight < dist[dest]) {
				System.out.println("There is a cycle in the graph");
				break;
			}
		}

	}

	private void printGraph(int[] dist, int V) {
		System.out.println("Vertex \t\t Distance from source");
		for (int i = 0; i < V; i++) {
			System.out.println(i + " \t\t " + dist[i]);
		}

	}

}
