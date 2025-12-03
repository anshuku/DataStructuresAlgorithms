package Searching;

import java.util.Arrays;

//Time complexity is log(n) via tree tracing
// minimum time or best case is O(1) and maximum time or worst case is O(logn)
// average time is O(logn) and can be found by Sum of all searches/total cases
public class BinarySearchIterative {

	public static void main(String[] args) {
		int[] arr = { 3, 3, 6, 8, 12, 14, 17, 25, 29, 31, 36, 36, 36, 42, 47, 53, 55, 62, 62 };

		int key = 62;

		int indexExact = binarySearchExact(arr, arr.length, key);
		System.out.printf("Exact BS: index for key %d is %d", key, indexExact);
		System.out.println();

//		key = 36;// 12
//		key = 62;//19
		key = 3;// 2
//		key = 4;// 2
		int indexUpper = binarySearchUpper(arr, arr.length, key);
		System.out.printf("Upper BS: index for key %d is %d", key, indexUpper);
		System.out.println();

//		key = 3;// 0
//		key = 2;// 0
		key = 4;// 0
//		key = 62;// 0
//		key = 63;// 0
		int indexLower = binarySearchLower(arr, arr.length, key);
		System.out.printf("Lower BS: index for key %d is %d", key, indexLower);
		System.out.println();

		int indexArrays = Arrays.binarySearch(arr, 62);
		System.out.printf("Arrays: index for key %d is %d", key, indexArrays);
	}

	// Finding the lower bound - leftmost position
	// Here, left is the insert position and left - 1 is the largest element which
	// is not smaller than the key.
	// check for nums[left/left + 1] == target for confirmation
	private static int binarySearchLower(int[] arr, int length, int key) {
		int start = 0;
		int end = arr.length - 1;
		while (start <= end) {
			int mid = start + (end - start) / 2;
			if (arr[mid] < key) {
				start = mid + 1;
			} else {
				end = mid - 1;
			}
		}
		// return start + 1
//		int end = arr.length;
//		while (start < end) {
//			int mid = start + (end - start) / 2;
//			if (arr[mid] >= key) {
//				end = mid - 1;
//			} else {
//				start = mid;
//			}
//		}
//		return start + 1;

		// return start
//		int end = arr.length;
//		while (start < end) {
//			int mid = start + (end - start) / 2;
//			if (arr[mid] >= key) {
//				end = mid;
//			} else {
//				start = mid + 1;
//			}
//		}
		return start;
	}

	// Finding the upper bound - rightmost position
	// Here if arr[mid] < target then insert position is on mid's right
	// so we discard the left half of mid and also mid by start = mid + 1
	// Also, if arr[mid] == target still the insert position is on mid's right
	// so we discard the left half of mid by start = mid + 1
	// Here, left is the insert position and left - 1 is the largest element which
	// is not larger than the key.
	// check for nums[left/left - 1] == target for confirmation
	private static int binarySearchUpper(int[] arr, int length, int key) {
		int start = 0;
		int end = arr.length - 1;
		while (start <= end) {
			int mid = start + (end - start) / 2;
			if (arr[mid] <= key) {
				start = mid + 1;
			} else {
				end = mid - 1;
			}
		}
		// if start < end condition is in while
		// take end as n as max insert position can be n.
		// then if arr[mid] > key, still the insert position can be mid
		// so we keep end = mid to discard right half while keeping mid
		// fails for end = n - 1 where key to placed is at end
		// so use end = n and return left
//		int end = arr.length;
//		while (start < end) {
//			int mid = start + (end - start) / 2;
//			if (arr[mid] <= key) {
//				start = mid + 1;
//			} else {
//				end = mid;
//			}
//		}
		return start;
	}

	// Find the exact value
	public static int binarySearchExact(int arr[], int n, int key) {
		int index = -1;
		int low = 0, high = n - 1;
		while (low <= high) {
			// Pivot point
			int mid = low + (high - low) / 2;
			if (key == arr[mid]) {
				return mid;
			} else if (key < arr[mid]) {
				high = mid - 1;
			} else {
				low = mid + 1;
			}
		}
		return index;
	}

}
