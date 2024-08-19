package Revision;

import java.util.Arrays;

public class ReviseJohnsonAlgorithm {

	final static int INF = Integer.MAX_VALUE;

	public static void main(String[] args) {

		int[][] graph= {
				{0, -5, 2, 3},
				{INF, 0, 4, INF},
				{INF, INF, 0, 1},
				{INF, INF, INF, 0}
		};
		
		johnsonAlgorithm(graph);
	}

	private static void johnsonAlgorithm(int[][] graph) {
		int V = graph.length;

		int[][] edges = new int[V * V][3];

		int index = 0;
		for (int i = 0; i < V; i++) {
			for (int j = 0; j < V; j++) {
//				if (graph[i][j] != 0) {
					edges[index++] = new int[] { i, j, graph[i][j] };
//				}
			}
		}

		int[] h = bellmanFordAlgorithm(edges, V);

		System.out.println("The dist array is " + Arrays.toString(h));

		int[][] alteredGraph = new int[V][V];
		for (int i = 0; i < V; i++) {
			for (int j = 0; j < V; j++) {
				if (graph[i][j] != INF) {
					alteredGraph[i][j] = graph[i][j] + h[i] - h[j];
				} else {
					alteredGraph[i][j] = INF;
				}
			}
		}
		System.out.println("The edge weight array for modified graph is:");
		for (int[] edge : alteredGraph) {
			System.out.println(Arrays.toString(edge));
		}

		for (int source = 0; source < V; source++) {
			dijkstraAlgorithm(graph, alteredGraph, source);
		}

	}

	private static int[] bellmanFordAlgorithm(int[][] edges, int V) {

		int len = edges.length;
		int[][] edgesWithExtra = Arrays.copyOf(edges, edges.length + V);

		for (int i = 0; i < V; i++) {
			edgesWithExtra[len + i] = new int[] { V, i, 0 };
		}

		int[] dist = new int[V + 1];

		Arrays.fill(dist, INF);
		dist[V] = 0;

		for (int i = 0; i < V; i++) {
			for (int[] edge : edgesWithExtra) {
				if (dist[edge[0]] != INF && dist[edge[0]] + edge[2] < dist[edge[1]]) {
					dist[edge[1]] = dist[edge[0]] + edge[2];
				}
			}
		}
		return Arrays.copyOf(dist, V);
	}

	private static void dijkstraAlgorithm(int[][] graph, int[][] alteredGraph, int source) {

		int V = graph.length;

		int[] dist = new int[V];
		Arrays.fill(dist, INF);
		dist[source] = 0;

		boolean[] visited = new boolean[V];

		for (int count = 0; count < V - 1; count++) {

			int u = findMinDistanceUnvisitedVertex(dist, visited);
			visited[u] = true;

			for (int v = 0; v < V; v++) {
				if (dist[u] != INF && !visited[v] && graph[u][v] != 0
						&& dist[u] + alteredGraph[u][v] < dist[v]) {
					dist[v] = dist[u] + alteredGraph[u][v];
				}
			}
		}
		System.out.println("The distance array: " + Arrays.toString(dist));

		System.out.println("Vertex \t\t Min distance");
		for (int i = 0; i < V; i++) {
			System.out.println(i + " \t\t " + (dist[i] == INF ? "INF" : dist[i]));
		}

	}

	private static int findMinDistanceUnvisitedVertex(int[] dist, boolean[] visited) {

		int minIndex = -1, min = INF;

		for (int i = 0; i < dist.length; i++) {
			if (dist[i] <= min && !visited[i]) {
				min = dist[i];
				minIndex = i;
			}
		}

		return minIndex;
	}

}
