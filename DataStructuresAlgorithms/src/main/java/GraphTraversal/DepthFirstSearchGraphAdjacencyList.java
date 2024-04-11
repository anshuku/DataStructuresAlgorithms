package GraphTraversal;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Stack;

/**
 * @author anshukumar
 * 
 *         Time complexity: O(V + E), where V is the number of vertices and E is
 *         the number of edges in the graph.
 * 
 *         Space Complexity / Auxiliary Space: Iterative - O(V). Since an extra
 *         visited array is needed of size V. Recursive - O(V + E), since an
 *         extra visited array of size V is required, And stack size for
 *         iterative call to DFS function.
 */
public class DepthFirstSearchGraphAdjacencyList {

	public static class Graph {
		int vertices;
		LinkedList<Integer>[] adjList;

		Graph(int vertices) {
			this.vertices = vertices;
			adjList = new LinkedList[vertices];
			for (int i = 0; i < vertices; i++) {
				adjList[i] = new LinkedList<Integer>();
			}
		}

		public void addEdge(int i, int j) {
			this.adjList[i].add(j);

		}
	}

	public static void main(String[] args) {

		int vertices = 4;
		Graph graph = new Graph(4);

		graph.addEdge(0, 1);
		graph.addEdge(0, 2);
		graph.addEdge(1, 2);
		graph.addEdge(2, 0);
		graph.addEdge(2, 3);
		graph.addEdge(3, 3);

//		depthFirstSearch(graph.adjList, 2, vertices);

		depthFirstSearchRecursiveStack(graph.adjList, 2, vertices);

	}

	private static void depthFirstSearchRecursiveStack(LinkedList<Integer>[] adjList, int node, int vertices) {
		boolean visited[] = new boolean[vertices];

		dfsRecursive(node, adjList, visited);
	}

	private static void dfsRecursive(int node, LinkedList<Integer>[] adjList, boolean[] visited) {
		
		if(!visited[node]) {
			visited[node] = true;
			System.out.println("The element is " + node);
		}
		
		ListIterator<Integer> listItr = adjList[node].listIterator();
		while (listItr.hasNext()) {
			int val = listItr.next();
			if (!visited[val]) {
				dfsRecursive(val, adjList, visited);
			}
		}
	}

	private static void depthFirstSearch(LinkedList<Integer>[] adjList, int i, int vertices) {
		
		Stack<Integer> s = new Stack();
		boolean[] visited = new boolean[vertices];
		s.push(i);
		
		while (!s.isEmpty()) {
			int node = s.pop();
			if(!visited[node]) {
				visited[node] = true;
				System.out.println("The element is " + node);
			}

			for (int val : adjList[node]) {
				if (!visited[val]) {
					s.push(val);
				}
			}
		}
		//iterativeBFS(s, adjList, visited);

	}

	private static void iterativeBFS(Stack<Integer> s, LinkedList<Integer>[] adjList, boolean[] visited) {
		while (!s.isEmpty()) {
			int node = s.pop();
			
			if(!visited[node]) {
				visited[node] = true;
				System.out.println("The element is " + node);
			}

			for (int val : adjList[node]) {
				if (!visited[val]) {
					s.push(val);
				}
			}
		}

	}

}
