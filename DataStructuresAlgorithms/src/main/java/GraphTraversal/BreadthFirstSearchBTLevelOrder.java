package GraphTraversal;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author anshukumar
 * 
 *         The Graph can be traversed via Breadth First Search(BFS) or Depth
 *         First Search(DFS) Algorithm 
 *         In BFS - First an element is selected and explored and then only the connected node having other
 *         connected nodes are selected and explored.
 * 
 *         In BFS - a Queue(interface) is used and hence it follow First In First Out
 *         principle
 */
public class BreadthFirstSearchBTLevelOrder {

	public static class Node {
		int val;
		Node left;
		Node right;

		public Node(int n) {
			this.val = n;
			this.left = null;
			this.right = null;
		}
	}

	public static void main(String[] args) {

		Node node = new Node(1);
		node.left = new Node(2);
		node.right = new Node(3);
		node.left.left = new Node(4);
		node.left.right = new Node(5);
		node.right.left = new Node(6);
		node.right.right = new Node(7);

		breadthFirstSearch(node);

	}

	private static void breadthFirstSearch(Node node) {

		Queue<Node> q = new LinkedList<>();

		q.add(node);

		while (!q.isEmpty()) {
			Node n = q.poll();
			System.out.println("The element is " + n.val);

			if (n.left != null) {
				q.add(n.left);
			}
			if (n.right != null) {
				q.add(n.right);
			}
		}

	}

}
