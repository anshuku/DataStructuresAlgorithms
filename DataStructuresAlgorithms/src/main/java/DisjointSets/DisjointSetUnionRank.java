package DisjointSets;

import java.util.Arrays;

public class DisjointSetUnionRank {

	static int[] parent;
	static int[] rank;

	DisjointSetUnionRank(int size) {
		parent = new int[size];
		rank = new int[size];

		// Initialize each element as a separate set with rank 0.
		for (int i = 0; i < size; i++) {
			parent[i] = i;
			rank[i] = 0;
		}
	}

	public static void main(String[] args) {

		int size = 5;
		DisjointSetUnionRank dsur = new DisjointSetUnionRank(size);

		// Perform union operations
		dsur.unionByRank(0, 1);
		dsur.unionByRank(2, 3);
		dsur.unionByRank(1, 3);

		// Find representatives of each element and print the result
		for (int i = 0; i < size; i++) {
			System.out.println("The parent of " + i + " is " + dsur.find(i));
		}
		System.out.println("The parent array is " + Arrays.toString(parent));
		System.out.println("The rank array is " + Arrays.toString(rank));
	}

	// Unites the set that includes i and the set that includes j by rank
	private void unionByRank(int i, int j) {

		// Find the representatives(or root nodes) for the set that includes i and j
		int iRep = find(i);
		int jRep = find(j);

		if (iRep == jRep) {
			return;
		}

		int iRank = rank[iRep];
		int jRank = rank[jRep];

		// if i's rank is less than j's rank
		if (iRank < jRank) {
			// move i under j
			parent[iRep] = jRep;
		}
		// if j's rank is less than i's rank
		else if (iRank > jRank) {
			// move j under i
			parent[jRep] = iRep;
		}
		// if their ranks are the same
		else {
			// move i under j, it doesn't matter
			parent[iRep] = jRep;
			// increment the result tree's rank by 1
			rank[jRep]++;
		}

	}

	// Find the representative(root node) of the set with Path Compression
	private int find(int i) {
		if (parent[i] != i) {
			parent[i] = find(parent[i]);
		}
		return parent[i];
	}

}
