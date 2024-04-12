package GraphTraversal;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;


/**
 * @author anshukumar
 * 
 *         The Graph is having Binary Weight of 0 or 1. So, BFS can be applied to traverse it.
 *         Since only the edges from a given vertex are checked, the Time Complexity of the BFS approach is O(V+E).
 *         This problem can also be solved by Dijkstra but the time complexity will be O(E + V Log V).
 */
public class BFSZeroOneShortestPathBinaryWeightGraph {

	static final int V = 9;

	static class Node {
		int to;
		int weight;

		Node(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}
	}

	static class Graph {

		int vertices;
		ArrayList<Node>[] adjList;

		Graph(int vertices) {
			this.vertices = vertices;
			adjList = new ArrayList[vertices];
			for (int v = 0; v < V; v++) {
				adjList[v] = new ArrayList<Node>();
			}
		}

		void addEdge(int u, int v, int w) {
			this.adjList[u].add(new Node(v, w));
			this.adjList[v].add(new Node(u, w));
		}
	}

	public static void main(String[] args) {

		Graph graph = new Graph(V);

		graph.addEdge(0, 1, 0);
		graph.addEdge(0, 7, 1);
		graph.addEdge(1, 7, 1);
		graph.addEdge(1, 2, 1);
		graph.addEdge(2, 3, 0);
		graph.addEdge(2, 5, 0);
		graph.addEdge(2, 8, 1);
		graph.addEdge(3, 4, 1);
		graph.addEdge(3, 5, 1);
		graph.addEdge(4, 5, 1);
		graph.addEdge(5, 6, 1);
		graph.addEdge(6, 7, 1);
		graph.addEdge(7, 8, 1);

		shortestPathZeroOneBW(graph.adjList, 0);

	}

	private static void shortestPathZeroOneBW(ArrayList<Node>[] adjList, int source) {

		int[] dist = new int[V];

		Arrays.fill(dist, Integer.MAX_VALUE);

		dist[source] = 0;

		Deque<Integer> deque = new ArrayDeque<>();

		deque.addLast(source);

		while (!deque.isEmpty()) {

			int u = deque.removeFirst();

			//iterate till the size of the ArrayList at an index/number of edges from a vertex
			for (int v = 0; v < adjList[u].size(); v++) {

				if (dist[u] + adjList[u].get(v).weight < dist[adjList[u].get(v).to]) {
					dist[adjList[u].get(v).to] = dist[u] + adjList[u].get(v).weight;

					if (adjList[u].get(v).weight == 0) {
						deque.addFirst(adjList[u].get(v).to);
					} else {
						deque.addLast(adjList[u].get(v).to);
					}
				}
			}
		}
		System.out.println("Vertex \t\t Min distance");
		for (int i = 0; i < V; i++) {
			System.out.println(i + " \t\t x" + dist[i]);
		}

	}

}
