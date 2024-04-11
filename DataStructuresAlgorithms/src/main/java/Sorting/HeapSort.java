package Sorting;


/**
 * @author anshukumar
 *
 * HeapSort uses Heap data structure sort an array. Below is Max Heap creation and deletion
 * It's time complexity is O(n*log(n)) always and has log(n) space complexity due to recursive call stack. It can be O(1) space complexity for iterative implementation.
 * It is in-place and unstable, but can be made stable.
 * It's 2-3 times slower than well implemented QuickSort due to lack of locality of reference.
 * It's not a divide and conquer algorithm but a recursive one.
 */
public class HeapSort {

	public static void main(String[] args) {
		
		int [] A = {1, 3, 5, 9, 11, 14, 18, 20, 2, 5, 6, 8, 12, 15, 16};
		int size = A.length;
		heapSort(A, size);
		System.out.println("Sorted List ");
		for(int a: A) {
			System.out.print(a+ " ");
		}
	}

	private static void heapSort(int[] arr, int N) {
		//Heapify the array starting from the last subtree having at least one child leaf node
		for(int i = N/2-1; i>=0; i--) {
			heapify(arr, N, i);
		}
		//Remove root element from top and put it at end of the array
		for(int i = N-1; i>0; i--) {
			int temp = arr[0];
			arr[0] = arr[i];
			arr[i] = temp;
			heapify(arr, i, 0);
		}
	}

	private static void heapify(int[] arr, int N, int i) {
		int largest = i;
		int left = 2*i + 1;
		int right = 2*i + 2;
		if(left < N && arr[left] > arr[largest]) {
			largest = left;
		}
		if(right < N && arr[right] > arr[largest]) {
			largest = right;
		}
		//If the root element is not the largest then swap with the largest child node's value and recursively heapify at largest index.
		if(largest != i) {
			int temp = arr[largest];
			arr[largest] = arr[i];
			arr[i] = temp;
			heapify(arr, N, largest);
		}	
	}
}
