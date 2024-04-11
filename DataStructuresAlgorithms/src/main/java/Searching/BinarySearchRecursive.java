package Searching;

public class BinarySearchRecursive {//Recursive way

	public static void main(String[] args) {
		int[] arr = {3, 6, 8, 12, 14, 17, 25, 29, 31, 36, 42, 47, 53, 55, 62};
		int key = 7;
		int low = 0, high = arr.length-1;
		
		int index = BinarySearch(arr, low, high, key);
		System.out.printf("index for key %d is %d", key, index);

	}
	
	public static int BinarySearch(int [] arr, int low, int high, int key) {
		if(low == high) {
			if (key == arr[low]) {
				return low;
			} else {
				return -1;
			}
		} else {
			int mid = (low + high)/2;
			if(key == arr[mid]) {
				return mid;
			} else if(key < arr[mid]) {
				return BinarySearch(arr, low, mid-1, key);
			} else {
				return BinarySearch(arr, mid+1, high, key);
			}
			
		}
		
	}

}
