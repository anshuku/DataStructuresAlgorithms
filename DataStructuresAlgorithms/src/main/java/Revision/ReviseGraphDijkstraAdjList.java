package Revision;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class ReviseGraphDijkstraAdjList {
	
	final static int V = 9;
	
	static class Graph {
		int vertices;
		ArrayList<ArrayList<AdjListNode>> adjList;
		
		Graph(int vertices){
			this.vertices = vertices;
			this.adjList = new ArrayList<>();
			for(int v = 0; v<vertices; v++) {
				adjList.add(new ArrayList<>());
			}
		}
		
		void addEdge(int u, int v, int w) {
			this.adjList.get(u).add(new AdjListNode(v, w));
			this.adjList.get(v).add(new AdjListNode(u, w));
		}
	}
	
	static class AdjListNode{
		int vertex;
		int weight;
		
		public AdjListNode(int vertex, int weight){
			this.vertex = vertex;
			this.weight = weight;
		}
	}

	public static void main(String[] args) {
		
		int vertices = V;
		Graph graph = new Graph(vertices);
		
		ArrayList<ArrayList<AdjListNode>> adjList = graph.adjList;
		
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
		
		int source = 0;
		dijkstraSSSPAdjList(adjList, 0);
		
		
	}

	private static void dijkstraSSSPAdjList(ArrayList<ArrayList<AdjListNode>> adjList, int source) {
		
		int [] dist = new int[V];
		
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		PriorityQueue<AdjListNode> pq = new PriorityQueue<>((a, b) -> a.weight - b.weight);
		
		dist[source] = 0;
		
		pq.add(new AdjListNode(source, 0));
		
		while(!pq.isEmpty()) {
			
			AdjListNode u = pq.poll();
			
			for(AdjListNode v: adjList.get(u.vertex)) {
				
				if(dist[u.vertex] + v.weight < dist[v.vertex]) {
					dist[v.vertex] = dist[u.vertex] + v.weight;
					pq.add(new AdjListNode(v.vertex, dist[v.vertex]));
				}
			}
		}
		
		System.out.println("Vertex \t\t Min Distance");
		for(int v = 0; v<V; v++) {
			System.out.println(v + " \t\t " + dist[v]);
		}
		
	}

}
