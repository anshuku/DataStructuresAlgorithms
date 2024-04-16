package GraphTraversal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author anshukumar
 * 
 *         Kahn's algorithm for topological sorting - If it successfully removes
 *         all vertices from the graph, it's a DAG with no cycles.
 * 
 *         If there are remaining vertices with in-degrees greater than 0, it
 *         indicates a presence of at least 1 cycle. If we're unable to get all
 *         the vertices in topological sorting, then there must be at least one
 *         cycle.
 * 
 *         The below approach uses BFS via queue to reduce indegree.
 * 
 *         Time complexity: O(V+E) Space complexity: O(V), To store the visited
 *         and recursion stack O(V) space is needed.
 */
public class GraphCycleBFSKahnTopologicalSorting {

	static final int V = 6;

	static class Graph {
		int vertices;
		List<List<Integer>> adjList;

		Graph(int vertices) {
			this.vertices = this.vertices;
			adjList = new ArrayList<>();

			for (int v = 0; v < V; v++) {
				adjList.add(new ArrayList<>());
			}
		}

		public void addEdge(int u, int v) {
			this.adjList.get(u).add(v);
		}
	}

	public static void main(String[] args) {
		Graph graph = new Graph(V);
		graph.addEdge(0, 1);
		graph.addEdge(0, 2);
		graph.addEdge(1, 3);
		graph.addEdge(4, 1);
		graph.addEdge(4, 5);
		graph.addEdge(5, 3);

		boolean isCyclePresent = isCyclic(graph.adjList);
		System.out.println("The graph contains cycle - " + isCyclePresent);

	}

	private static boolean isCyclic(List<List<Integer>> adjList) {

		int[] inDegree = new int[V];

		int visited = 0;

		Queue<Integer> queue = new LinkedList<>();

		// calculate in-degree of each vertex
		for (int u = 0; u < V; u++) {
			for (int v : adjList.get(u)) {
				inDegree[v]++;
			}
		}

		// enqueue vertices with 0 in-degree
		for (int v = 0; v < V; v++) {
			if (inDegree[v] == 0) {
				queue.add(v);
			}
		}

		// BFS traversal
		while (!queue.isEmpty()) {
			int u = queue.poll();
			visited++;

			System.out.println("Current in degree array " + Arrays.toString(inDegree));

			// reduce in-degree of adjacent vertices
			for (int v : adjList.get(u)) {
				inDegree[v]--;

				// if in-degree becomes 0, enqueue the vertex
				if (inDegree[v] == 0) {
					queue.add(v);
				}
			}
		}
		System.out.println("The in degree array " + Arrays.toString(inDegree));
		return visited != V;
	}

}
