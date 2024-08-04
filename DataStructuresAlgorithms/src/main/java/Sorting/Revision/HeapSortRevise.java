package Sorting.Revision;

import java.util.Arrays;

/*
 * It's an in place(in situ) algorithm but not stable.
 * Time complexity: O(n*logn) always and space complexity is O(logn) due to complete binary tree.
 */
public class HeapSortRevise {

	public static void main(String[] args) {

		int[] A = { 1, 3, 5, 9, 11, 14, 18, 20, 2, 5, 6, 8, 12, 15, 16 };
		int size = A.length;

		heapSort(A, size);

		System.out.println("The sorted array by heap sort is " + Arrays.toString(A));

	}

	private static void heapSort(int[] a, int N) {

		// Heapify the last subtree having at least one child leaf node.
		for (int i = N / 2 - 1; i >= 0; i--) {
			heapify(a, i, N);
		}

		// Remove root element from top and put it at end of array by replacing the node at end 
		// and recursively heapify the new root.
		for (int i = N - 1; i > 0; i--) {
			int temp = a[0];
			a[0] = a[i];
			a[i] = temp;
			heapify(a, 0, i);
		}

	}

	private static void heapify(int[] a, int i, int n) {

		int maxVal = i;

		int left = 2 * i + 1;
		int right = 2 * i + 2;

		if (left < n && a[left] > a[maxVal]) {
			maxVal = left;
		}
		if (right < n && a[right] > a[maxVal]) {
			maxVal = right;
		}

		// if the root node is not having the highest value then replace it with child node's highest value
		// and recursively heapify at index having largest child node's value.
		if (maxVal != i) {
			int temp = a[maxVal];
			a[maxVal] = a[i];
			a[i] = temp;
			heapify(a, maxVal, n);
		}

	}

}
