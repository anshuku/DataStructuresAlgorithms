package GraphTraversal;

import java.util.Stack;

import GraphTraversal.BreadthFirstSearchBTLevelOrder.Node;

/**
 * @author anshukumar
 * 
 *         The Graph can be traversed via Breadth First Search(BFS) or Depth
 *         First Search(DFS) Algorithm
 * 
 *         In DFS - First an element is selected and then the next adjacent node
 *         is visited and first one is suspended and then again the next
 *         adjacent vertex is selected and process is repeated.
 * 
 *         In BFS - a Stack(class) is used and hence it follow Last In First Out
 *         principle
 */
public class DepthFirstSearchBTPreOrder {
	
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

		depthFirstSearch(node);

	}

	private static void depthFirstSearch(Node node) {
		
		Stack<Node> s = new Stack<>();
		s.push(node);
		
		while(!s.empty()) {
			Node n = s.pop();
			System.out.println("The element is " + n.val);

			if (n.right != null) {
				s.push(n.right);
			}
			
			if (n.left != null) {
				s.push(n.left);
			}
		}
		
		
	}
	

}
