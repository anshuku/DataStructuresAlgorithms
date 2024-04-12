package Revision;

import java.util.Arrays;

public class ReviseGraphDijkstra2D {

	final static int V = 9;
	
	public static void main(String[] args) {

		int graph[][] = new int[][] { { 0, 4, 0, 0, 0, 0, 0, 8, 0 }, 
									  { 4, 0, 8, 0, 0, 0, 0, 11, 0 },
				                      { 0, 8, 0, 7, 0, 4, 0, 0, 2 }, 
				                      { 0, 0, 7, 0, 9, 14, 0, 0, 0 }, 
				                      { 0, 0, 0, 9, 0, 10, 0, 0, 0 },
				                      { 0, 0, 4, 14, 10, 0, 2, 0, 0 }, 
				                      { 0, 0, 0, 0, 0, 2, 0, 1, 6 }, 
				                      { 8, 11, 0, 0, 0, 0, 1, 0, 7 },
				                      { 0, 0, 2, 0, 0, 0, 6, 7, 0 } };

		dijkstraSingleSourceShortestPath(graph, 0);

	}

	private static void dijkstraSingleSourceShortestPath(int[][] graph, int source) {
		
		boolean [] sptSet = new boolean[V];
		
		int [] dist = new int[V];
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		dist[source] = 0;
		
		for(int i = 0; i<V-1; i++) {
			
			int u = getMinDistanceVertex(dist, sptSet);
			sptSet[u] = true;
			
			for(int v=0; v<V; v++) {
				if(!sptSet[v] && graph[u][v] != 0 &&
						dist[u] != Integer.MAX_VALUE &&
						dist[u] + graph[u][v] < dist[v]) {
					dist[v] = dist[u] + graph[u][v];
				}
			}
		}
		
		System.out.println("Vertex \t\t Min distance");
		for(int v=0; v<V; v++) {
			System.out.println(v + " \t\t " + dist[v]);
		}
		
	}

	private static int getMinDistanceVertex(int [] dist, boolean [] sptSet) {
		int min_index = -1, min = Integer.MAX_VALUE;
		
		for(int v = 0; v<V; v++) {
			if(!sptSet[v] && dist[v] <= min) {
				min = dist[v];
				min_index = v;
			}
		}
		return min_index;
	}

}
