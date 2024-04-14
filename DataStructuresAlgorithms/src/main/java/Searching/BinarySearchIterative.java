package Searching;

public class BinarySearchIterative {//Time complexity is log(n) via tree tracing
	//minimum time or best case is O(1) and maximum time or worst case is O(logn)
	//average time is O(logn) and can be found by Sum of all searches/total cases

	public static void main(String[] args) {
		int[] arr = {3, 6, 8, 12, 14, 17, 25, 29, 31, 36, 42, 47, 53, 55, 62};
		
		int key = 62;
		
		int index = BinarySearch(arr, arr.length, key);
		System.out.printf("index for key %d is %d", key, index);
	}
	
	public static int BinarySearch(int arr[], int n, int key) {
		int index = -1;
		int low = 0, high = n-1;
		while(low <= high) {
			
			int mid = (low+high)/2;
			if(key == arr[mid]) {
				return mid;
			} else if (key < arr[mid]) {
				high = mid-1;
			} else {
				low = mid + 1;
			}
		}
		return index;
	}

}
