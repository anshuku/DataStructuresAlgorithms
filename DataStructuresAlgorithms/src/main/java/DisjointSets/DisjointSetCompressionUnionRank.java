package DisjointSets;

import java.util.Arrays;

/*
 * Time complexity: O(n) for creating n single item sets.
 * The two techniques - path compression with union by rank/size will make time complexity reach nearly constant time.
 * The final amortized time complexity is O(a(n))
 * Here, a(n) is inverse Ackermann function which grows very steadily(not exceeds for n < 10^600 approximately)
 * Space complexity: O(n) for storing n elements in Disjoint Set Data structure
 */
public class DisjointSetCompressionUnionRank {

	static int[] parent, rank;
	static int size;

	DisjointSetCompressionUnionRank(int size) {
		parent = new int[size];
		rank = new int[size];
		this.size = size;
		makeSet();
	}

	// Create n sets with single item in each.
	private void makeSet() {
		for (int i = 0; i < size; i++) {
			// initially all elements are in their own set.
			parent[i] = i;
		}
	}

	public static void main(String[] args) {

		int size = 5;
		DisjointSetCompressionUnionRank dscu = new DisjointSetCompressionUnionRank(size);
		System.out.println("The initial parent array is " + Arrays.toString(parent));
		System.out.println("The initial rank array is " + Arrays.toString(rank));

		// 0 is a friend of 2
		dscu.unionByRank(0, 2);
		// 4 is a friend of 2
		dscu.unionByRank(4, 2);
		// 3 is a friend of 1
		dscu.unionByRank(3, 1);

		System.out.println("The parent array is " + Arrays.toString(parent));
		System.out.println("The rank array is " + Arrays.toString(rank));

		System.out.println("Is 4 friend of 0 " + (dscu.findPathCompression(4) == dscu.findPathCompression(0)));

		System.out.println("Is 1 friend of 0 " + (dscu.findPathCompression(1) == dscu.findPathCompression(0)));

	}

	// unites the set that includes i with the set that includes j by rank
	private void unionByRank(int i, int j) {

		// finds representative of 2 sets
		int iRep = findPathCompression(i);
		int jRep = findPathCompression(j);

		if (iRep == jRep) {
			return;
		}

		int iRank = rank[iRep];
		int jRank = rank[jRep];

		// if iRep's rank is less than jRep's rank
		if (iRank < jRank) {
			// move iRep under jRep so that depth remains same
			parent[iRep] = jRep;
		}
		// if jRep's rank is less than iRep's rank
		else if (iRank > jRank) {
			// move jRep under iRep so that depth remains same
			parent[jRep] = iRep;
		}
		// if ranks are the same
		else {
			// move jRep under iRep, it doesn't matter
			parent[jRep] = iRep;
			// increment result tree rank by 1
			rank[iRep]++;
		}
	}

	// Returns representative of i's set.
	// Path compression flattens the tree structure of the set.
	private int findPathCompression(int i) {
		// Find representative of the set that i is an element of
		if (parent[i] != i) {
			// if i is not the parent of itself then i is not the representative of the set
			// so recursively call find on it's parent and move i's node directly under the
			// representative
			parent[i] = findPathCompression(parent[i]);
		}
		return parent[i];
	}

}
