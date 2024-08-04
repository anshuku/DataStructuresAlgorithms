package GraphTraversal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author anshukumar
 * 
 *         The graph is represented using adjacency list, the time complexity is
 *         reduced to O(E * log V) with the help of a binary min heap/priority
 *         queue. The Priority queue helps to perform BFS.
 * 
 *         Dijkstra’s algorithm doesn’t work for graphs with negative weight
 *         cycles and negative edges.
 */
public class GraphDijkstraSingleSourceShortestPathAdjList {

	static final int V = 9;

	static class Graph {

		int vertices;
		ArrayList<ArrayList<AdjListNode>> adjList;

		Graph(int vertices) {
			this.vertices = vertices;
			adjList = new ArrayList();
			for (int i = 0; i < V; i++) {
				adjList.add(new ArrayList<>());
			}
		}

		public void addEdge(int u, int v, int w) {

			this.adjList.get(u).add(new AdjListNode(v, w));
			this.adjList.get(v).add(new AdjListNode(u, w));

		}
	}

	static class AdjListNode {
		int vertex;
		int weight;

		public AdjListNode(int vertex, int weight) {
			this.vertex = vertex;
			this.weight = weight;
		}

		public int getVertex() {
			return vertex;
		}

		public int getWeight() {
			return weight;
		}
	}

	public static void main(String[] args) {

		Graph graph = new Graph(V);

		int source = 0;

		ArrayList<ArrayList<AdjListNode>> adjList = graph.adjList;

//		  graph.addEdge(0, 1, 4);
//        graph.addEdge(0, 7, 8);
//        graph.addEdge(1, 2, 8);
//        graph.addEdge(1, 7, 11);
//        graph.addEdge(2, 3, 7);
//        graph.addEdge(2, 8, 2);
//        graph.addEdge(2, 5, 4);
//        graph.addEdge(3, 4, 9);
//        graph.addEdge(3, 5, 14);
//        graph.addEdge(4, 5, 10);
//        graph.addEdge(5, 6, 2);
//        graph.addEdge(6, 7, 1);
//        graph.addEdge(6, 8, 6);
//        graph.addEdge(7, 8, 7);

		adjList.get(0).add(new AdjListNode(1, 4));
		adjList.get(0).add(new AdjListNode(7, 8));
		adjList.get(1).add(new AdjListNode(2, 8));
		adjList.get(1).add(new AdjListNode(7, 11));
		adjList.get(1).add(new AdjListNode(0, 7));
		adjList.get(2).add(new AdjListNode(1, 8));
		adjList.get(2).add(new AdjListNode(3, 7));
		adjList.get(2).add(new AdjListNode(8, 2));
		adjList.get(2).add(new AdjListNode(5, 4));
		adjList.get(3).add(new AdjListNode(2, 7));
		adjList.get(3).add(new AdjListNode(4, 9));
		adjList.get(3).add(new AdjListNode(5, 14));
		adjList.get(4).add(new AdjListNode(3, 9));
		adjList.get(4).add(new AdjListNode(5, 10));
		adjList.get(5).add(new AdjListNode(4, 10));
		adjList.get(5).add(new AdjListNode(6, 2));
		adjList.get(6).add(new AdjListNode(5, 2));
		adjList.get(6).add(new AdjListNode(7, 1));
		adjList.get(6).add(new AdjListNode(8, 6));
		adjList.get(7).add(new AdjListNode(0, 8));
		adjList.get(7).add(new AdjListNode(1, 11));
		adjList.get(7).add(new AdjListNode(6, 1));
		adjList.get(7).add(new AdjListNode(8, 7));
		adjList.get(8).add(new AdjListNode(2, 2));
		adjList.get(8).add(new AdjListNode(6, 6));
		adjList.get(8).add(new AdjListNode(7, 1));

		dijkstraSSSPAdjList(adjList, source);

	}

	private static void dijkstraSSSPAdjList(ArrayList<ArrayList<AdjListNode>> adjList, int source) {

		int[] dist = new int[V];
		Arrays.fill(dist, Integer.MAX_VALUE);

//		PriorityQueue<AdjListNode> pq = new PriorityQueue<>(V, Comparator.comparingInt(o -> o.getWeight()));

//		Min Heap
		PriorityQueue<AdjListNode> pq = new PriorityQueue<>((a, b) -> a.getWeight() - b.getWeight());

		pq.add(new AdjListNode(source, 0));

		dist[source] = 0;

		while (!pq.isEmpty()) {

			AdjListNode u = pq.poll();

			// Min Heap always returns the smallest weight/distance
			for (AdjListNode v : adjList.get(u.getVertex())) {

				if (dist[u.vertex] + v.weight < dist[v.vertex]) {
					dist[v.vertex] = dist[u.vertex] + v.weight;
					pq.add(new AdjListNode(v.vertex, dist[v.vertex]));
				}
			}

		}

		System.out.println("Vertex \t\t Minimum distance");
		for (int v = 0; v < V; v++) {
			System.out.println(v + " \t\t " + dist[v]);
		}

	}

}
