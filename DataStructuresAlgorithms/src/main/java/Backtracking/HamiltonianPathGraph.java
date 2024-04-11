package Backtracking;

/**
 * @author anshukumar
 * 
 *         The Hamiltonian cycles are paths in a directed/undirected graph such
 *         that all the vertices are traveled once and it's not necessary to
 *         reach the starting vertex. 
 *         Since there are chances that same cycle is
 *         repeated, the first starting vertex is fixed with 1 of the vertices
 *         and from 2nd vertex onwards the graph is traversed.
 * 
 *         The Time complexity for the given approach is (n-1)! (n-1)^n so
 *         O(n^n) THe Space complexity is O(1)
 */
public class HamiltonianPathGraph {
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

		System.out.println("The number of hamiltonian paths are " + count);

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
				System.out.println();
				count++;

			} else {
				hamiltonian(g, x, k + 1, n);
			}
		} while (true);

	}

	private static void nextVertex(int[][] g, int[] x, int k, int n) {

		do {
			x[k] = (x[k] + 1) % (n + 1);
			if (x[k] == 0)
				return;
			if (g[x[k - 1]][x[k]] != 0) {
				int j = 1;
				for (; j < k; j++) {
					if (x[j] == x[k])
						break;
				}
				if (j == k) {
					return;
				}
			}
		} while (true);

	}

}
