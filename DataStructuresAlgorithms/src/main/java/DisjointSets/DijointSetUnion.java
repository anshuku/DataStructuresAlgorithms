package DisjointSets;

import java.util.Arrays;

public class DijointSetUnion {

	private static int[] parent;

	public DijointSetUnion(int n) {
		// Initialize the parent array with each element as it's own representative
		parent = new int[n];
		for (int i = 0; i < n; i++) {
			parent[i] = i;
		}
	}

	public static void main(String[] args) {

		int size = 5;
		DijointSetUnion uf = new DijointSetUnion(size);

		// Perform union operations as needed.
		uf.union(1, 2);
		uf.union(3, 4);
	

		System.out.println("The parent array is " + Arrays.toString(parent));
		System.out.println("The parent for index 1 is " + uf.find(1) + " index 2 is " + uf.find(2) + " index 3 is "
				+ uf.find(3) + " index 4 is " + uf.find(4));

		boolean inSameSet = uf.find(1) == uf.find(2);

		System.out.println("The elements 1 and 2 are in same set - " + inSameSet);

	}

	public int find(int i) {
		if (parent[i] == i) {
			return i;
		}
		parent[i] = find(parent[i]); // Path compression
		return parent[i];
	}

	// Takes O(n) time in worst case having tree of length n.
	// Unite the set which contains element i and the set which contains element j
	public void union(int i, int j) {

		int iRep = find(i); // Find representative of set containing i
		int jRep = find(j); // Find representative of set containing j

		// Make representative of i's set be the representative of j's set
		parent[iRep] = jRep;

	}

}
