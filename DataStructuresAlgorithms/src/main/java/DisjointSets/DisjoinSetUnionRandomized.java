package DisjointSets;

import java.util.Arrays;
import java.util.Random;

/*
 * Randomization is used to choose an element as the root of the merged set. 
 * This helps to balance the tree and improves the performance of the algorithm.
 * 
 * It uses randomized linking for the union operation.
 * 
 */
public class DisjoinSetUnionRandomized {

	Random random;

	private static int[] parent;
	private static int[] size;

	DisjoinSetUnionRandomized(int n) {
		parent = new int[n];
		size = new int[n];

		for (int i = 0; i < n; i++) {
			parent[i] = i;
			size[i] = 1;
		}
	}

	public static void main(String[] args) {
		DisjoinSetUnionRandomized dsur = new DisjoinSetUnionRandomized(5);

		System.out.println("The parent array is " + Arrays.toString(parent));
		System.out.println("The size array is " + Arrays.toString(size));

		dsur.union(0, 1);
		dsur.union(1, 2);
		dsur.union(3, 4);

		System.out.println("The parent array is " + Arrays.toString(parent));
		System.out.println("The size array is " + Arrays.toString(size));
	}

	// Uses randomized linking for the union operation
	private void union(int x, int y) {

		int rootX = find(x);
		int rootY = find(y);

		if (rootX == rootY) {
			return;
		}

		random = new Random();
		if (random.nextInt(2) == 0) {
			int temp = rootX;
			rootX = rootY;
			rootY = temp;
		}

		parent[rootX] = rootY;
		size[rootY] += size[rootX];

	}

	private int find(int i) {
		if (parent[i] != i) {
			parent[i] = find(parent[i]);
		}
		return parent[i];
	}

}
