package Revision;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Queue;
import java.util.Stack;

//DFS
public class RevisionBFSDFS {

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

		private void addEdge(int i, int j) {
			this.adjList[i].add(j);
		}
	}

	public static void main(String[] args) {
		int vertices = 4;
		Graph graph = new Graph(vertices);

		graph.addEdge(0, 1);
		graph.addEdge(0, 2);
		graph.addEdge(1, 2);
		graph.addEdge(2, 0);
		graph.addEdge(2, 3);
		graph.addEdge(3, 3);

//		depthFirstSearch(graph.adjList, 0);
		depthFirstSearchRecursive(graph.adjList, 2, vertices);
	}

	private static void depthFirstSearchRecursive(LinkedList<Integer>[] adjList, int node, int vertices) {
		boolean [] visited = new boolean[vertices];
		
		dfsRecursive(adjList, node, visited);
	}

	private static void dfsRecursive(LinkedList<Integer>[] adjList, int node, boolean[] visited) {
		
		if(!visited[node]) {
			System.out.println("The node is " + node);
			visited[node] = true;
		}
		
		ListIterator<Integer> listItr = adjList[node].listIterator();
		while(listItr.hasNext()) {
			int num = listItr.next();
			if(!visited[num]) {
				dfsRecursive(adjList, num, visited);
			}
		}
	}

	private static void depthFirstSearch(LinkedList<Integer>[] adjList, int startNode) {
		
		boolean[] visited = new boolean[adjList.length];
		Stack<Integer> s = new Stack<>();
		s.push(startNode);
		
		while (!s.isEmpty()) {
			int node = s.pop();
			if(!visited[node]) {
				visited[node] = true;
				System.out.println("The current node is " + node);
			}
			for (int num : adjList[node]) {
				if (!visited[num]) {
					s.push(num);
				}
			}
		}
	}
}
