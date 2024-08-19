package DynamicProgramming;

import java.util.Arrays;

/*
 * Dynamic programming is an algorithmic paradigm that solves a problem by breaking it into subproblems 
 * using recursion and combining solutions to a subproblem.
 * 
 * Two main properties which suggests a problem can be solved using DP:
 * Overlapping Subproblems and Optimal Substructure
 * 
 * II. Optimal Substructure
 * In DP, ideal base property alludes that the ideal answer for a subproblem can be obtained from 
 * ideal answer from of subproblems.
 * This property is utilized to plan DP calculation to tackle streamlining issues by separating them
 * into more modest subproblems and then consolidating them to get the answer for the first issue.
 * 
 * A given problem is said to have optimal substructure if the optimal solution of a given problem can be obtained
 * by using the optimal solution to its subproblems instead of trying every possible way to solve the subproblems.
 * 
 * Example - All Pair Shortest path algorithm like Floyd-Warshall and 
 * Single Source Shortest Path for negative weight edges given by Bellman-Ford has optimal substructure property.
 * 
 * Standard problems having optimal substructure - Longest Common Subsequence, Coin Change,
 * Count ways to reach nth stair, Edit distance, Cutting a rod, Fibonacci numbers
 * 
 * Longest Path problem doesn't have optimal substructure property - longest simple path without a cycle.
 * This is because the longest path between two nodes is not a combination of longest path between a start to middle to end nodes.
 * To solve Problems like Rat in maze where all path needs to be explored use Recursion and Backtracking.
 */
public class AllPairsShortestPathOptimalSubstructure {
	
	public static final int INF = 99999;

	public static void main(String[] args) {
		
//		int [][] graph = {
//				{0, 5, INF, 10},
//				{INF, 0, 3, INF},
//				{INF, INF, 0, 1},
//				{INF, INF, INF, 0}
//		};
		
		int graph[][] = { 
				 {0, 1, INF, INF},
                 {INF, 0, -1, INF},
                 {INF, INF, 0, -1},
                 {-1, INF, INF, 0}
        };
		 
		int V =  graph.length;

		int shortestPath = allPairsShortestPath(graph, 0, 3);
		
		for(int[] edge: graph) {
			System.out.println(Arrays.toString(edge));
		}
		
		// This works for both connected and disconnected directed weighted graphs in O(V) TC.
		// A negative weight cycle is a cycle in a graph whose edges sum to a negative value.
		// If the distance of any vertex from itself becomes negative, there is a negative weight cycle
		for(int i = 0; i < V; i++) {
			if(graph[i][i] < 0) {
				System.out.println("There is a negative cycle");
				break;
			}
		}

		System.out.println("The shortest path from 0 to 3 is " + shortestPath);

	}

	private static int allPairsShortestPath(int[][] graph, int u, int v) {
		int n = graph.length;
		for (int k = 0; k < n; k++) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
				}
			}
		}
		return graph[u][v];
	}

}
