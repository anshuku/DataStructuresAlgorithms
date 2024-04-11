package DivideAndConquer.Sorting;

/**
 * @author anshukumar
 *
 * Merge Sort - stable, large datasets, external sorting(data too large for memory),  adaptive, 
 * parallelizable (for multiple processors and threads), not in place(additional memory), 
 * not good for small datasets(slow), additional space for storing merged sub-arrays.
 * 
 * Recursive algo whose time complexity is given by below recurrence relation.
 * T(n) = 2*T(n/2) + theta(n);
 * It can be solved using Recurrence Tree method or Master method(case II). Divide array and linear time to merge two halves.
 * T: O(n*log(n)) all 3 | S: O(n) for auxiliary array
 * 
 * Pros:
 * Suitable for very large size list - millions of records which other sorts can't handle
 * It can help to Merge sort 2 Linked Lists without creating another Linked Lists.
 * Supports External sorting by merging in chunks/runs/pieces.
 * Stable - After merging the order of duplicate is maintained.
 * 
 * Cons:
 * It's not in place(in situ) and needs extra space for storing new merged array(not for LL)
 * Not for small problems(n<=15) but large ones as it's slower due to DnC(recursion). For small problems use -
 * Insertion(LL)/Bubble sort(O(n^2) and stable)
 * Recursive and uses Stack in memory with max height being that of tracing binary Tree. It needs log(n) stack space at a time.
 * It maybe creating many activation records(nodes). It needs extra space for auxiliary array(O(n+logn) -> theta(n))
 */
public class MergeSortTwoWay1ArrayIterative {

	public static void main(String[] args) {
		int [] A = {1, 3, 5, 9, 11, 14, 18, 20, 2, 5, 6, 8, 12, 15, 16};
		int s = 0;
		int e = A.length-1;
		
		mergeSort(A, s, e);
		System.out.println("Sorted List");
		for(int a: A) {
			System.out.print(a + " ");
		}
				
	}

	private static void mergeSort(int[] a, int l, int r) {
		
		if(l<r) {
			int mid = (l+r)/2;
			mergeSort(a, l, mid);
			mergeSort(a, mid+1, r);
			merge(a, l, mid, r);
		}
	}

	private static void merge(int[] a, int s, int mid, int e) {
		
		int l = mid-s+1;
		int r = e-mid;
		
		int [] L = new int[l];
		int [] R = new int[r];
		
		for(int i=0; i<l; i++) {
			L[i] = a[s+i];
		}
		
		for(int j=0; j<r; j++) {
			R[j] = a[mid+1+j];
		}
		
		int i = 0, j = 0, k = s;
		while(i < l && j < r) {
			if(L[i] < R[j]) {
				a[k++] = L[i++];
			} else {
				a[k++] = R[j++];
			}
		}
		while(i < l) {
			a[k++] = L[i++];
		}
		while(j < r) {
			a[k++] = R[j++];
		}
		
	}

}
