package Revision;

public class AlgoDSRevise {

	public static void main(String[] args) {
		int arr[] = {1, 3, 5, 9, 11, 14, 18, 20, 2, 5, 6, 8, 12, 15, 16};
		//int num = 4;
		
		//int index = binarySearchIterative(arr, num);
		//int index = binarySearchRecursive(arr, num, 0, arr.length-1);
		//System.out.printf("index for key %d is %d", num, index);
		
		//int [] A = {2, 5, 6, 8, 12, 15, 16};
		//int [] B = {1, 3, 9, 11, 14, 18, 20};
		
		//int [] arr = mergeSort2Way(A, B, A.length, B.length);
		
		//mergeSort(arr, 0, arr.length-1);
		
		//quickSort(arr, 0, arr.length-1);
		
		heapSort(arr, arr.length);
		
		System.out.println("Sorted arr is ");
		for(int val: arr) {
			System.out.print(val + " ");
		}

	}

	private static void heapSort(int[] arr, int N) {
		for(int i = (N/2) - 1; i>=0; i--) {
			heapify(arr, N, i);
		}
		
		for(int val: arr) {
			System.out.print(val + " ");
		}
		
		for(int i = N-1; i>0; i--) {
			int temp = arr[0];
			arr[0] = arr[i];
			arr[i] = temp;
			heapify(arr, i, 0);
		}
	}

	private static void heapify(int[] arr, int n, int i) {
		int largest = i;
		int left = 2*i+1;
		int right = 2*i+2;
		
		if(left < n && arr[left] > arr[largest]) {
			largest = left;
		}
		if(right < n && arr[right] > arr[largest]) {
			largest = right;
		}
		
		if(largest != i) {
			int temp = arr[i];
			arr[i] = arr[largest];
			arr[largest] = temp;
			heapify(arr, n, largest);
		}
	}

	private static void quickSort(int[] arr, int low, int high) {
		if(low < high) {
			//int pivotIndex = pivotStartIndex(arr, low, high);
			int pivotIndex = pivotMidIndex(arr, low, high);
			quickSort(arr, low, pivotIndex-1);
			quickSort(arr, pivotIndex+1, high);
		}
	}

	private static int pivotMidIndex(int[] arr, int low, int high) {
		int mid = (low+high)/2;
		int pivot = arr[mid];
		int i = low, j = high;
		while(i<j) {
			while(i<=high && arr[i] <= pivot) {
				i++;
			}
			while(j>=low && arr[j] > pivot) {
				j--;
			}
			if(i<j) {
				swap(arr, i, j);
			}
		}
		if(j != low) {
			swap(arr, mid, j);
		}
		return j;
	}

	private static int pivotStartIndex(int[] arr, int low, int high) {
		int pivot = arr[low];
		int i = low, j = high;
		while(i<j) {
			while(i<=high && arr[i] <= pivot) {
				i++;
			}
			while(j>=low && arr[j] > pivot) {
				j--;
			}
			if(i<j) {
				swap(arr, i, j);
			}
		}
		if(low != j) {
			swap(arr, low, j);
		}
		return j;
	}

	private static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
		
	}

	private static int[] mergeSort2Way(int[] A, int[] B, int a, int b) {
		int [] arr = new int[a+b];
		int i = 0, j = 0, k = 0;
		while(i<a && j<b) {
			if(A[i] < B[j]) {
				arr[k++] = A[i++];
			} else {
				arr[k++] = B[j++];
			}
		}
		while(i < a) {
			arr[k++] = A[i++];
		}
		while(j < b) {
			arr[k++] = B[j++];
		}
		return arr;
	}

	private static void mergeSort(int[] arr, int low, int high) {
		if(low < high) {
			int mid = (low+high)/2;
			mergeSort(arr, low, mid);
			mergeSort(arr, mid+1, high);
			merge(arr, low, high, mid);
		}
		
	}

	private static void merge(int[] arr, int low, int high, int mid) {
		int l = mid-low+1;
		int r = high-mid;
		int L[] = new int[l];
		int R[] = new int[r];
		
		for(int i = 0; i < l; i++) {
			L[i] = arr[low+i];
		}
		for(int j = 0; j < r; j++) {
			R[j] = arr[mid+1+j];
		}
		int k=low;
		int i = 0, j=0;
		while(i<l && j<r) {
			if(L[i] < R[j]) {
				arr[k++] = L[i++];
				
			} else {
				arr[k++] = R[j++];
			}
		}
		while(i<l) {
			arr[k++] = L[i++];
		}
		
		while(j<r) {
			arr[k++] = R[j++];
		}
	}

	private static int binarySearchRecursive(int[] arr, int num, int low, int high) {
		if(low >= high) {
			if(num == arr[low]) {
				return low;
			} else {
				return -1;
			}
		} else {
			int mid = (low+high)/2;
			if(num == arr[mid]) {
				return mid;
			} else if(num < arr[mid]) {
				return binarySearchRecursive(arr, num, low, mid-1);
			} else {
				return binarySearchRecursive(arr, num, mid+1, high);
			}
		} 
	}

	private static int binarySearchIterative(int[] arr, int num) {
		int low = 0;
		int high = arr.length-1;
		
		while(low <= high) {
			int mid = (low + high)/2;
			if(num == arr[mid]) {
				return mid;
			} else if(num < arr[mid]) {
				high = mid-1;
			} else {
				low = mid+1;
			}
		}
		return -1;
	}

}
