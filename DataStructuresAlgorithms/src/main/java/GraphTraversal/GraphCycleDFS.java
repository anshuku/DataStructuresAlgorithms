package GraphTraversal;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author anshukumar
 * 
 *         A cycle can be detected in a directed graph via Depth First Search Traversal.
 *         There is a cycle in a graph only if there is a back edge - node
 *         points to one of the ancestors.
 *         
 *         If a node is already present in recursion stack, then there is a cycle in graph.
 *         
 *         Time Complexity: O(V+E), same as DFS traversal
 *         Space Complexity: O(V), to store the visited and recursion stack O(V) space is needed.
 */
public class GraphCycleDFS {

	static final int V = 4;

	static class Graph {

		int vertices;
		List<List<Integer>> adjList;

		public Graph(int vertices) {
			this.vertices = vertices;
			adjList = new ArrayList<>();

			for (int v = 0; v < V; v++) {
				adjList.add(new ArrayList<>());
			}
		}

		void addEdge(int u, int v) {
			this.adjList.get(u).add(v);
		}
	}

	public static void main(String[] args) {

		Graph graph = new Graph(V);
		graph.addEdge(0, 1);
		graph.addEdge(0, 2);
		graph.addEdge(1, 2);
		graph.addEdge(2, 0);
		graph.addEdge(2, 3);
		graph.addEdge(3, 3);

		boolean isCyclic = isCyclic(graph.adjList);
		System.out.println("The graph is cyclic - " + isCyclic);
	}

	// Returns true if the graph contains a cycle, else false
	private static boolean isCyclic(List<List<Integer>> adjList) {

		boolean[] visited = new boolean[V];
		boolean[] recStack = new boolean[V];

		// Call the recursive helper function to detect cycle in different DFS trees
		for (int i = 0; i < V; i++) {
			if (isCyclicUtil(i, visited, recStack, adjList)) {
				return true;
			}
		}

		return false;
	}

	// Function to check if cycle exists
	private static boolean isCyclicUtil(int i, boolean[] visited, boolean[] recStack, List<List<Integer>> adjList) {

		if (recStack[i]) {
			return true;
		}

		if (visited[i]) {
			return false;
		}
		
		// Mark the current node as visited and part of recursion stack
		visited[i] = true;
		recStack[i] = true;

		List<Integer> list = adjList.get(i);

		for (int num : list) {
			if (isCyclicUtil(num, visited, recStack, adjList)) {
				return true;
			}
		}
		recStack[i] = false;
		return false;
	}

}
