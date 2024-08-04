package DisjointSets;

import java.util.Arrays;

public class DisjointSetRevision {

	private static int[] parent, rank;
	private static int[] size;

	DisjointSetRevision(int n) {
		parent = new int[n];
		size = new int[n];
		rank = new int[n];
		for (int i = 0; i < n; i++) {
			parent[i] = i;
			// size[i] = 1;
		}
		Arrays.fill(size, 1);
	}

	public static void main(String[] args) {

		DisjointSetRevision dsr = new DisjointSetRevision(5);

		System.out.println("The parent array is " + Arrays.toString(parent));

//		dsr.union(0, 1);
//		dsr.union(2, 3);
//		dsr.union(1, 3);
		
		dsr.unionByRank(0, 1);
		dsr.unionByRank(2, 3);
		dsr.unionByRank(1, 3);

		System.out.println("The parent array post union is " + Arrays.toString(parent));
		System.out.println("The rank array post union is " + Arrays.toString(rank));
//		System.out.println("The size array post union is " + Arrays.toString(size));
	}

	private void unionByRank(int i, int j) {
		int iRep = find(i);
		int jRep = find(j);
		
		if(iRep == jRep) {
			return;
		}
		
		int iRank = rank[iRep];
		int jRank = rank[jRep];
		
		if(iRank < jRank) {
			parent[iRep] = jRep;
		} else if (jRank < iRank) {
			parent[jRep] = iRep;
		} else {
			parent[jRep] = iRep;
			rank[iRep]++;
		}
	}

	// unites the set which contains i to the set which contains j
	private void union(int i, int j) {

		int iRep = find(i);
		int jRep = find(j);

		if (iRep == jRep) {
			return;
		}

		int iSize = size[iRep];
		int jSize = size[jRep];

		if (iSize < jSize) {
			parent[iRep] = jRep;
			size[jRep] += size[iRep];
		} else {
			parent[jRep] = iRep;
			size[iRep] += size[jRep];
		}
	}

	private int find(int i) {
		if (parent[i] != i) {
			parent[i] = find(parent[i]);
		}
		return parent[i];
	}

}
