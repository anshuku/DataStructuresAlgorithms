package Backtracking;

/**
 * @author anshukumar
 * 
 *         The Hamiltonian cycles are paths in a directed/undirected graph such
 *         that all the vertices are traveled once before reaching the starting
 *         vertex. 
 *         Since there are chances that same cycle is repeated, the
 *         first starting vertex is fixed with 1 of the vertices and from 2nd vertex
 *         onwards the graph is traversed.
 * 
 *         The Time complexity for the given approach is (n-1)! or (n-1)^n so
 *         O(n^n) THe Space complexity is O(1)
 */
public class HamiltonianCycleGraph {

	static int count = 0;

	public static void main(String[] args) {

		int[][] G = { { 0, 1, 1, 0, 1 }, 
					  { 1, 0, 1, 1, 1 }, 
					  { 1, 1, 0, 1, 0 }, 
					  { 0, 1, 1, 0, 1 }, 
					  { 1, 1, 0, 1, 0 } };
		int n = 4;
		int x[] = new int[n + 1];
		x[0] = 0;

		hamiltonian(G, x, 1, n);

		System.out.println("The number of hamiltonian cycles are " + count);

	}

	private static void hamiltonian(int[][] g, int[] x, int k, int n) {

		do {
			nextVertex(g, x, k, n);

			if (x[k] == 0) {
				return;
			}
			if (k == n) {
				System.out.println("The cycle is ");
				for (int i = 0; i <= n; i++) {
					System.out.print((x[i] + 1) + " ");
				}
				System.out.print("1");//the cycle has 1 as end
				System.out.println();
				count++;

			} else {
				hamiltonian(g, x, k + 1, n);
			}
		} while (true);

	}

	private static void nextVertex(int[][] g, int[] x, int k, int n) {

		do {
			x[k] = (x[k] + 1) % (n + 1); //gets next vertex value
			if (x[k] == 0)//if the last vertex has crossed
				return;
			if (g[x[k - 1]][x[k]] != 0) { //check if there is an edge from previous vertex to current vertex in graph
				int j = 1;
				for (; j < k; j++) { // check if there is no repetition of vertex
					if (x[j] == x[k])
						break;
				}
				if (j == k) {
					// check if last vertex is not reached and if it's last vertex then there is an edge from it to 1st vertex
					if (k < n || (k == n && g[x[n]][x[0]] != 0))
						return;
				}
			}
		} while (true);

	}

}
