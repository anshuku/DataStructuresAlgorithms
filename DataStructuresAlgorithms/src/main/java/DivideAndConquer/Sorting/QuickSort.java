package DivideAndConquer.Sorting;

/**
 * @author anshukumar
 *
 * Quicksort allows elements to arrange themselves and it's not the fastest sorting algorithm.
 * It follows DnC and is recursive in nature.
 * It works on finding an element in a sorted position called Pivot position via Partitioning algorithm.
 * All elements on LHS are shorter and RHS are greater than Pivot element.
 * Best case time is O(n*logn) but the element should be at median which is hard to achieve.
 * Worst case time is O(n^2) in case the pivot is taken from start of array.
 * To fix worst case take pivot at the middle(fixes already sorted sorted array) or at random position, but there can be scenario where worst case arises.
 * The space complexity is determined by the stack space since it's recursive in Nature. At best it's O(logn) and O(n) as worst.
 */
public class QuickSort {

	public static void main(String[] args) {
		
		int [] A = {1, 3, 5, 9, 11, 14, 18, 20, 2, 5, 6, 8, 12, 15, 16};
		//int [] A = {10, 8, 5, 4, 3, 2, 1};
		int s = 0;
		int e = A.length - 1;
		
		quickSort(A, s, e);
		System.out.println("Sorted List");
		for(int a: A) {
			System.out.print(a + " ");
		}	
	}
	
	public static void quickSort(int [] A, int l, int h) {
		System.out.println("low " + l + " high " + h);
		if(l < h) {
			int index = partition(A, l, h);
			System.out.println("partition index " + index);
			quickSort(A, l, index-1);
			quickSort(A, index+1, h);
		}
	}

	private static int partition(int[] A, int l, int h) {
		int mid = (l+h)/2;
		int pivot = A[mid];
		System.out.println("partition - low " + l + " high " + h);
		System.out.println("pivot " + pivot);
		int i = l, j = h;
		while(i<j) {
			while(i<=h && A[i] <= pivot) {
				i++;
			}
			while(j>=l && A[j] > pivot) {
				j--;
			} 
			System.out.println("i " + i + " j " + j);
			if(i<j) {
				swap(A, i, j);
			}
		}
		System.out.println("pivot " + pivot + " low " + l + " j " + j);
		if(l!=j) {
			swap(A, mid, j);
		}
		
		System.out.println("Sorted List in process");
		for(int a: A) {
			System.out.print(a + " ");
		}
		System.out.println();
		return j;
	}

	private static void swap(int [] A, int i, int j) {
		int temp = A[i];
		A[i] = A[j];
		A[j] = temp;
	}

}
