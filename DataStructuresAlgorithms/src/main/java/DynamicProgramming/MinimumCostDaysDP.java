package DynamicProgramming;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*
 * Given a sorted array arr[] consisting of N positive integers such that arr[i] represent the days 
 * in which a worker will work and an array cost[] of size 3 representing the salary paid to the 
 * workers for 1 day, 7 days and 30 days respectively, the task is to find the minimum cost required 
 * to have a worker for all the given days in arr[].
 * 
 * Input: arr[] = [2, 4, 6, 7, 8, 10, 17], cost[] = [3, 8, 20] Output: 14
 * 
 */
public class MinimumCostDaysDP {

	public static void main(String[] args) {
		int[] arr = { 2, 4, 6, 7, 8, 10, 17 };
		int[] cost = { 3, 8, 20 };

//		int[] arr = { 1, 2, 3, 4, 6, 7, 8, 9, 11, 15, 20, 29 };
//		int[] cost = { 3, 8, 20 };

//		int[] arr = { 1, 4, 6, 7 };
//		int[] cost = { 2, 7, 15 };

		int minCost = getMinCostForWorkMaxElement(arr, cost);

		System.out.println("The minimum cost required is " + minCost);

		int minCostRecursive = getMinCostForWorkRecursive(arr, cost, arr.length);

		System.out.println("The minimum cost required with recursion is " + minCostRecursive);

		int minCostMemoize = getMinCostForWorkMemoized(arr, cost, arr.length);

		System.out.println("The minimum cost required with memoization is " + minCostMemoize);

		int minCostQueue = getMinCostForWorkQueue(arr, cost, arr.length);

		System.out.println("The minimum cost required with queues is " + minCostQueue);

	}

	// Pair class to store the integer in the format Day, Cost
	static class Pair<K, V> {
		private K key;
		private V value;

		public Pair(K key, V value) {
			this.key = key;
			this.value = value;
		}

		public K getKey() {
			return key;
		}

		public V getValue() {
			return value;
		}

	}

	// Use two queues to keep the indices of days that are no earlier than ith day
	// by 7 days and 30 days. Idea behind using queue from number of expired days
	// which is polled.
	// Time complexity: O(N) where N is the size of the array
	// Space complexity: O(N) where N is the size of the array
	private static int getMinCostForWorkQueue(int[] days, int[] costs, int length) {

		// Queue stores Key Value pair in Day, Cost format.
		Queue<Pair<Integer, Integer>> qWeek = new LinkedList<>();
		Queue<Pair<Integer, Integer>> qMonth = new LinkedList<>();

		int ans = 0;

		// Loop over the days array
		for (int day : days) {

			// Remove expired days from both the queues.
			while (!qWeek.isEmpty() && qWeek.peek().getKey() + 7 <= day) {
				qWeek.poll();
			}

			while (!qMonth.isEmpty() && qMonth.peek().getKey() + 30 <= day) {
				qMonth.poll();
			}

			// Store the current day as key and the current days cost as value
			qWeek.add(new Pair<>(day, ans + costs[1]));
			qMonth.add(new Pair<>(day, ans + costs[2]));

			// Update ans
			ans = Math.min(ans + costs[0], Math.min(qWeek.peek().getValue(), qMonth.peek().getValue()));
		}
		return ans;
	}

	// Cache the result and don't recompute the repeated subproblem
	// Time complexity: O(N) where N is the size of the array
	// Space complexity: O(N*M) where M is max element
	private static int getMinCostForWorkMemoized(int[] days, int[] cost, int N) {

		int maxValidity = days[N - 1] + 30;
		int[][] dp = new int[N][maxValidity];
		for (int[] arr : dp) {
			Arrays.fill(arr, -1);
		}

		return minCostMemoized(days, cost, 0, 0, N, dp);
	}

	private static int minCostMemoized(int[] days, int[] costs, int i, int validity, int N, int[][] dp) {
		if (i >= N) {
			return 0;
		}
		if (dp[i][validity] != -1) {
			return dp[i][validity];
		} else if (days[i] <= validity) {
			return dp[i][validity] = minCostMemoized(days, costs, i + 1, validity, N, dp);
		} else {
			int ch1 = costs[0] + minCostMemoized(days, costs, i + 1, days[i], N, dp);
			int ch2 = costs[1] + minCostMemoized(days, costs, i + 1, days[i] + 6, N, dp);
			int ch3 = costs[2] + minCostMemoized(days, costs, i + 1, days[i] + 29, N, dp);
			return dp[i][validity] = Math.min(ch1, Math.min(ch2, ch3));
		}
	}

	// Uses Recursion in a top down manner.
	// 3 options: Select either first pass, second pass or third pass
	// If days fall within the validity period of previously selected pass,
	// one can travel without any expenses. +6 and +29 because the validity is inclusive 
	// Base case if we finish our journey then we won't have to pay anything.
	// Time complexity: O(3^N) where N is size of the array.
	// Space complexity: O(N)
	private static int getMinCostForWorkRecursive(int[] arr, int[] cost, int N) {
		return minCost(arr, cost, 0, 0, N);
	}

	private static int minCost(int[] days, int[] costs, int i, int validity, int N) {
		if (i >= N) {
			return 0;
		}
		if (days[i] <= validity) {
			return minCost(days, costs, i + 1, validity, N);
		} else {
			int ch1 = costs[0] + minCost(days, costs, i + 1, days[i], N);
			int ch2 = costs[1] + minCost(days, costs, i + 1, days[i] + 6, N);
			int ch3 = costs[2] + minCost(days, costs, i + 1, days[i] + 29, N);
			return Math.min(ch1, Math.min(ch2, ch3));
		}
	}

	// Time complexity: O(M), where M is the max element in days array
	// Space complexity: O(M)
	private static int getMinCostForWorkMaxElement(int[] days, int[] cost) {
		int N = days.length;
		int len = days[N - 1];

		int[] dp = new int[len + 1];

		// Minimum cost for Nth day
		dp[len] = Math.min(cost[2], Math.min(cost[0], cost[1]));

		int ptr = N - 2;
		// Traverse from right to left
		for (int i = len - 1; i > 0; i--) {
			// If the current index in dp array equals the value in days array at index ptr
			if (ptr >= 0 && i == days[ptr]) {

				int val1 = dp[i + 1] + cost[0];
				int val2 = cost[1], val3 = cost[2];
				if (i + 7 <= len) {
					val2 += dp[i + 7];
				}
				if (i + 30 <= len) {
					val3 += dp[i + 30];
				}

				dp[i] = Math.min(val1, Math.min(val2, val3));

				ptr--;
			} else {// If day is not in days array
				dp[i] = dp[i + 1];
			}
		}
		return dp[1];
	}

}
