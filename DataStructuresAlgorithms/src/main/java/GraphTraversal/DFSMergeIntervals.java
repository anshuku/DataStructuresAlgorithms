package GraphTraversal;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/**
 * @author anshukumar
 * 
 *         Create Graph, Connected Components of graph and Merge Intervals
 * 
 */
public class DFSMergeIntervals {

	static Map<int[], LinkedList<int[]>> graph;
	static Map<Integer, LinkedList<int[]>> nodesInComp;
	static Set<int[]> visited;

	public static void main(String[] args) {

		int[][] intervals = { { 1, 3 }, { 2, 6 }, { 8, 10 }, { 15, 18 } };

		int[][] mergedIntervals = mergeIntervals(intervals);
		for (int[] interval : mergedIntervals) {
			System.out.println(Arrays.toString(interval));
		}
	}

	private static int[][] mergeIntervals(int[][] intervals) {
		createGraph(intervals);
		createConnectedComponents(intervals);

		List<int[]> merged = new LinkedList<>();

		for (int compCount = 0; compCount < nodesInComp.size(); compCount++) {
			merged.add(mergeNodes(nodesInComp.get(compCount)));
		}

		return merged.toArray(new int[merged.size()][]);
	}

	private static void createGraph(int[][] intervals) {

		graph = new HashMap<>();
		for (int[] interval : intervals) {
			graph.put(interval, new LinkedList<>());
		}

		for (int[] interval1 : intervals) {
			for (int[] interval2 : intervals) {

				if (checkOverlap(interval1, interval2)) {
					graph.get(interval1).add(interval2);
					graph.get(interval2).add(interval1);
				}
			}
		}
	}

	private static boolean checkOverlap(int[] a, int[] b) {
		if (a[0] <= b[1] && a[1] >= b[0]) {
			return true;
		}
		return false;
	}

	private static void createConnectedComponents(int[][] intervals) {
		nodesInComp = new HashMap<>();
		visited = new HashSet<>();

		int compNumber = 0;
		for (int[] interval : intervals) {
			if (!visited.contains(interval)) {
				markComponentDfs(interval, compNumber);
				compNumber++;
			}
		}
	}
	
	// Use depth-first search to mark all nodes in the same connected component with the same integer.
	private static void markComponentDfs(int[] start, int compNumber) {
		Stack<int[]> stack = new Stack<>();
		stack.add(start);

		while (!stack.empty()) {
			int[] node = stack.pop();
			if (!visited.contains(node)) {
				visited.add(node);

				if (nodesInComp.get(compNumber) == null) {
					nodesInComp.put(compNumber, new LinkedList<>());
				}
				nodesInComp.get(compNumber).add(node);

				for (int[] newNode : graph.get(node)) {
					stack.push(newNode);
				}
			}
		}
	}

	private static int[] mergeNodes(List<int[]> nodes) {

		int min = nodes.get(0)[0];
		for (int[] node : nodes) {
			min = Math.min(min, node[0]);
		}
		int max = nodes.get(0)[1];
		for (int[] node : nodes) {
			max = Math.max(max, node[1]);
		}
		return new int[] { min, max };
	}

}
