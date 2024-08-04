package DisjointSets;

import java.util.Arrays;

/*
 * In usual scenario, the find operation can be slow if the tree structure of the set is highly unbalanced.
 * 
 * The one-try and two-try variation of splitting can be used to optimize the performance.
 * It reduces the number of times, the parent link needs to be followed.
 * It's done by temporarily modifying the parent links during the find operation to split the tree into smaller pieces.
 * 
 * Applications - Clustering, graph coloring and image segmentation.
 */
public class DijointSetFind {

	public static void main(String[] args) {

		int[] parent = { 0, 1, 2, 3, 4, 5, 6 };

		int index = find(4, parent);

		System.out.println("The representative of set via find is " + index);

		int[] p = { 0, 1, 0, 3, 2, 5, 4, 7, 6, 9, 8 };

		int idx = find(6, p);
		System.out.println("The representative of set is " + idx);

//		int i = findPathCompression(6, p);

//		int i = findPathCompressionCache(6, p);
		
		System.out.println("The parent array is " + Arrays.toString(p));

		int indexOneWay = findPathCompressionOneWay(10, p);
//		System.out.println("The representative of set via one way splitting path compression is " + Arrays.toString(p));

//		int b = findPathCompressionOneWay(10, p);
//		int b = findPathCompressionNot(10, p);

//		System.out.println("The representative of set via path compression is " + i);
//		System.out.println("The representative set after path compression is " + Arrays.toString(p));
		System.out.println("The representative of set via one way splitting path compression is " + Arrays.toString(p));
		System.out.println("The counter is " + counter);
	}

	static int counter = 0;

	private static int findPathCompressionOneWay(int i, int[] parent) {
		if (parent[i] != i) {
			int root = parent[i];
			parent[i] = findPathCompressionOneWay(parent[i], parent);
			if (parent[i] == i) {
				return root;
			}
		}
		return parent[i];
	}

	private static int findPathCompressionNot(int i, int[] parent) {
		if (parent[i] != i) {
			counter++;
			parent[i] = findPathCompressionNot(parent[i], parent);
		}
		return parent[i];
	}

	// Time complexity - O(logn) on average per call.
	// It speeds up the find operation by compressing the height of the tree by
	// inserting a caching mechanism.
	private static int findPathCompressionCache(int i, int[] parent) {
		// If i is the parent of itself
		if (parent[i] == i) {
			// then i is the representative
			return i;
		}

		int result = findPathCompressionCache(parent[i], parent);
		// Cache the result by moving i's node directly under the representative of
		// this set.
		parent[i] = result;
		return result;
	}

	// Collapsing find
	private static int findPathCompression(int i, int[] parent) {
		if (parent[i] == i) {
			return i;// i is the representative of its own set.
		}
		// Recursively find parent until reaching the root.
		parent[i] = findPathCompression(parent[i], parent);// path compression
		return parent[i];
	}

	// Takes O(n) time in worst case.
	public static int find(int i, int[] parent) {
		// If i is the parent of itself
		if (parent[i] == i) {
			// Then i is the representative of this set.
			return i;
		} else {
			// Else recursively find parent of this set.
			return find(parent[i], parent);
		}
	}

}
