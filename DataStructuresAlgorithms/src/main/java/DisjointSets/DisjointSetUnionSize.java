package DisjointSets;

import java.util.Arrays;

// Time complexity: O(logn) without path compression
public class DisjointSetUnionSize {

	static int[] parent;
	static int[] size;

	DisjointSetUnionSize(int n) {
		parent = new int[n];
		size = new int[n];

		for (int i = 0; i < n; i++) {
			parent[i] = i;
			// size[i] = 1;
		}
		// Size array is initialized with 1s
		Arrays.fill(size, 1);
	}

	public static void main(String[] args) {

		int n = 5;
		DisjointSetUnionSize dsus = new DisjointSetUnionSize(n);

		// Perform union operations
		dsus.unionBySize(0, 1);
		dsus.unionBySize(2, 3);
		dsus.unionBySize(0, 4);

		// Print the representative of each element after unions
		for (int i = 0; i < n; i++) {
			System.out.println("The parent of " + i + " is " + dsus.find(i));
		}

		System.out.println("The parent array is " + Arrays.toString(parent));
		System.out.println("The size array is " + Arrays.toString(size));

	}

	// Unites the set that includes i and the set that includes j by size
	private void unionBySize(int i, int j) {

		int iRep = find(i);
		int jRep = find(j);

		if (iRep == jRep) {//needed?
			return;
		}
		
		int iSize = size[iRep];
		int jSize = size[jRep];

		// if i's size is less than j's size
		if (iSize < jSize) {
			// then move i under j
			parent[iRep] = jRep;
			// increment j's size by i's size
			size[jRep] += size[iRep];
		}
		// if j's size is less than i's size
		else {
			// then move j under i
			parent[jRep] = iRep;
			// increment i's size by j's size
			size[iRep] += size[jRep];
		}

	}

	// Finds the representative(root node) of the set that includes i
	private int find(int i) {
		if (parent[i] != i) {
			// Path compression: make the parent of i, the root of the set
			parent[i] = find(parent[i]);
		}
		return parent[i];
	}

}
