package DivideAndConquer.Sorting;

public class MergeSortTwoWay2ArrayIterative {

	public static void main(String[] args) {
		int [] A = {2, 5, 6, 8, 12, 15, 16};
		int [] B = {1, 3, 9, 11, 14, 18, 20};
		
		int [] C = mergeSortTwoWay(A, B, A.length, B.length);
		System.out.println("Sorted List");
		for(int c: C) {
			System.out.print(c + " ");
		}
				
	}

	private static int[] mergeSortTwoWay(int[] a, int[] b, int m, int n) {
		int [] C = new int[m+n];
		int i = 0, j = 0, k = 0;
		while(i < m && j<n) {
			if(a[i] < b[j]) {
				C[k++] = a[i++];
			} else {
				C[k++] = b[j++];
			}
		}
		while(i < m) {
			C[k++] = a[i++];
		}
		while(j < n) {
			C[k++] = b[j++];
		}
		
		return C;
	}

}
