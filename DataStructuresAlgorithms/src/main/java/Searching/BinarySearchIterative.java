package Searching;

import java.util.Arrays;

// Time complexity is log(n) via tree tracing
// minimum time or best case is O(1) and maximum time or worst case is O(logn)
// average time is O(logn) and can be found by Sum of all searches/total cases
// Time complexity :
// By Master's theorem
// T(n) = a*T(n/b) + theta(N^d)
// It means we divide the problems into a subproblems of size N/b in theta(N^d) time.
// Here at each step there is only 1 subproblem so a = 1, it's size is half the initial 
// problem so b = 2, this happens in constant time so d = 0.
// log b (a) = 0 = d
// So it's case 2 of master's theorem
// T(n) = O(n ^ (log b (a)) log N ^ (d + 1)) = O(log n)
public class BinarySearchIterative {

	public static void main(String[] args) {
//		int[] arr = { 3, 3, 6, 6, 8, 12, 14, 17, 25, 29, 31, 36, 36, 36, 42, 47, 53, 55, 62, 62 };
		int[] arr = { 1 };

		int key = 62;

		int indexExact = binarySearchExact(arr, arr.length, key);
		System.out.printf("Exact BS: index for key %d is %d", key, indexExact);
		System.out.println();

//		key = 36;// 12
//		key = 62;// 19
//		key = 1;// 0
//		key = 3;// 2
//		key = 4;// 2
//		key = 6;// 2
		int indexUpper = binarySearchUpper(arr, arr.length, key);
		System.out.printf("Upper BS: index for key %d is %d", key, indexUpper);
		System.out.println();

		key = 1;// 0
//		key = 2;// 0
//		key = 3;// 0
//		key = 4;// 2
//		key = 6;// 2
//		key = 62;// 17
//		key = 63;// 19
		int indexLower = binarySearchLower(arr, arr.length, key);
		System.out.printf("Lower BS: index for key %d is %d", key, indexLower);
		System.out.println();

		int indexInsertPosition = binarySearchInsertPosition(arr, arr.length, key);
		System.out.printf("Insert Position: index for key %d is %d", key, indexInsertPosition);
		System.out.println();

		// Arrays.binarySearch() gives the index of the search key if the element exists
		// otherwise it returns -(index) which starts from -1
		// use if (index > 0) logic
		int indexArrays = Arrays.binarySearch(arr, 62);
		System.out.printf("Arrays: index for key %d is %d", key, indexArrays);
	}

	// Find the exact value
	public static int binarySearchExact(int arr[], int n, int key) {
		int index = -1;
		int low = 0, high = n - 1;
		while (low <= high) {
			// Pivot point
			// In java we don't have arbitrary precision integers so use below for mid
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

	// Finding the upper bound - rightmost position
	// Here if arr[mid] < target then insert position is on mid's right
	// so we discard the left half of mid and also mid by start = mid + 1
	// Also, if arr[mid] == target still the insert position is on mid's right
	// so we discard the left half of mid by start = mid + 1
	// Here, left is the insert position and left - 1 is the largest element which
	// is not larger than the key.
	// check for nums[left/left - 1] == target for confirmation
	// Here, answer would be in range [left, right] at any point. All the indices
	// smaller than left would contain values smaller than target and all values at
	// indices greater than right would be greater than target until left <= right
	// Once left > right, left denotes the index of the smallest value which is just
	// greater than the target. This is because all values at indices greater than
	// right would be greater than target and value immediately next to index right
	// is at left(right + 1) after completion of Binary search algorithm.
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

	// arr m > target end = mid - 1
	// arr m == target end = mid - 1
	// arr m < target start = mid + 1
	// 1 3 5 | 0 2 1 | 2
	// 0 0 0 | 1 0 1
	// start increased at end due to < logic where start becomes mid + 1.
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

	// Insert position
	// a sorted array of distinct integers and a target value, return the index if
	// the target is found. If not, return the index where it would be if it were
	// inserted in order.
	// If the constraint - nums contains distinct values sorted in ascending order.
	// is given then lower bound binary search algorithm works as well.
	// If the target value is not found then condition start > end
	// Also arr[end] < target < arr[start] so return start to insert the key.
	private static int binarySearchInsertPosition(int[] arr, int length, int key) {
		int start = 0;
		int end = arr.length - 1;
		while (start <= end) {
			int mid = start + (end - start) / 2;
			if (arr[mid] == key) {
				return mid;
			} else if (arr[mid] < key) {
				start = mid + 1;
			} else {
				end = mid - 1;
			}
		}
		return start;
	}

}
