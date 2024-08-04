package Revision;

import java.util.Arrays;

public class DisjoinSetFindUnionRevision {

	static int[] parent;

	DisjoinSetFindUnionRevision(int size) {
		parent = new int[size];
		for (int i = 0; i < size; i++) {
			parent[i] = i;
		}
	}

	public static void main(String[] args) {

		int size = 5;
		DisjoinSetFindUnionRevision dsfu = new DisjoinSetFindUnionRevision(size);

		dsfu.union(1, 2);
		dsfu.union(3, 4);

		System.out.println("The parent array is " + Arrays.toString(parent));

	}

	private void union(int i, int j) {

		int iRep = find(i);
		int jRep = find(j);

		parent[iRep] = jRep;

	}

	private int find(int i) {
		if (parent[i] == i) {
			return i;
		}
		parent[i] = find(parent[i]);
		return parent[i];
	}

}
