package Sorting;

import java.util.Arrays;

/*
 * It's an in place(in-situ) algorithm but not stable(relative order of equal items may not be preserved)
 * Time complexity is O(n*logn) in best case and it can be O(n^2)
 */
public class QuickSort {

	public static void main(String[] args) {

		int[] A = { 1, 3, 5, 9, 11, 14, 18, 20, 2, 5, 6, 8, 12, 15, 16 };

		int s = 0;
		int e = A.length - 1;

		quickSort(A, s, e);

		System.out.println("Sorted List is " + Arrays.toString(A));
	}

	private static void quickSort(int[] a, int s, int e) {

		if (s < e) {
			int pivotIndex = getPivotIndex(a, s, e);
			quickSort(a, s, pivotIndex - 1);
			quickSort(a, pivotIndex + 1, e);
		}
	}

	private static int getPivotIndex(int[] a, int s, int e) {
		int mid = (s + e) / 2;
		int pivot = a[mid];
		int i = s, j = e;
		while (i < j) {
			while (i <= e && a[i] <= pivot) {
				i++;
			}
			while (j >= s && a[j] > pivot) {
				j--;
			}
			if (i < j) {
				swap(i, j, a);
			}
		}
		if (s != j) {
			swap(mid, j, a);
		}
		return j;
	}

	private static void swap(int i, int j, int[] a) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;

	}

}
