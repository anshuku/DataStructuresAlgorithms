package Sorting;

import java.util.Arrays;

/*
 * It has O(n*logn) time complexity
 * It's stable but not in-place(in situ)
 */
public class MergeSort {

	public static void main(String[] args) {
		int[] arr = { 1, 3, 5, 9, 11, 14, 18, 20, 2, 5, 6, 8, 12, 15, 16 };

		int s = 0;
		int e = arr.length - 1;

		mergeSort(arr, s, e);

		System.out.println("The sorted array is " + Arrays.toString(arr));

	}

	private static void mergeSort(int[] arr, int s, int e) {
		if (s < e) {
			int mid = (s + e) / 2;
			mergeSort(arr, s, mid);
			mergeSort(arr, mid + 1, e);
			merge(arr, s, mid, e);
		}

	}

	private static void merge(int[] arr, int s, int mid, int e) {

		int a = mid - s + 1;
		int b = e - mid;

		int[] L = new int[a];
		int[] R = new int[b];
		for (int i = 0; i < a; i++) {
			L[i] = arr[s + i];
		}
		for (int i = 0; i < b; i++) {
			R[i] = arr[mid + 1 + i];
		}
		int i = 0, j = 0, k = s;
		while (i < a && j < b) {
			if (L[i] < R[j]) {
				arr[k++] = L[i++];
			} else {
				arr[k++] = R[j++];
			}
		}
		while (i < a) {
			arr[k++] = L[i++];
		}
		while (j < b) {
			arr[k++] = R[j++];
		}
	}

}
