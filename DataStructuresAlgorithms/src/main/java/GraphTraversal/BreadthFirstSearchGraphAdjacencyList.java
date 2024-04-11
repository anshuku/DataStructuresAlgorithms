package GraphTraversal;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author anshukumar
 * 
 *         Time complexity - O(V+E), where V is the number of nodes and E is the
 *         number of edges.
 * 
 *         Auxiliary Space/Space Complexity - O(V)
 */
public class BreadthFirstSearchGraphAdjacencyList {

	static public class Graph {
		int vertices;
		LinkedList<Integer>[] adjList;

		@SuppressWarnings("unchecked")
		Graph(int vertices) {
			this.vertices = vertices;
			adjList = new LinkedList[vertices];
			for (int i = 0; i < vertices; i++) {
				adjList[i] = new LinkedList<Integer>();
			}
		}

		public void addEdge(int i, int j) {
			adjList[i].add(j);
		}
	}

	public static void main(String[] args) {
		int vertices = 5;
		Graph graph = new Graph(vertices);

		graph.addEdge(0, 1);
		graph.addEdge(0, 2);
		graph.addEdge(1, 3);
		graph.addEdge(1, 4);
		graph.addEdge(2, 4);

		breadthFirstSearch(0, vertices, graph.adjList);

	}

	private static void breadthFirstSearch(int startNode, int vertices, LinkedList<Integer>[] adjList) {

		boolean[] visited = new boolean[vertices];
		Queue<Integer> q = new LinkedList<>();
		//Index of adjList[] is marked as visited
		visited[startNode] = true;
		q.add(startNode);

		while (!q.isEmpty()) {

			int node = q.poll();
			System.out.println("The element is " + node);

			for (int val : adjList[node]) {
				if (!visited[val]) {
					visited[val] = true;
					q.add(val);
				}
			}
		}
	}

}
