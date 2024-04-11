package GraphTraversal;

import java.util.Stack;

import GraphTraversal.DepthFirstSearchBTPreOrder.Node;

public class GraphArticulationPoint {
	
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
		node.left = new Node(4);
		node.right = new Node(2);
		node.left.right = new Node(3);
		node.right.left = node.left.right;
		node.right.left.left = new Node(5);
		node.right.left.right = new Node(6);

		articulationPoint(node); 

	}

	private static void articulationPoint(Node node) {
		
		boolean [] visited = new boolean[7];
		Stack<Node> s = new Stack<>();
		s.push(node);
		visited[node.val] = true;
		
		while(!s.empty()) {
			Node n = s.pop();
			System.out.println("The element is " + n.val);

			if (n.right != null && !visited[n.right.val]) {
				s.push(n.right);
				visited[n.right.val] = true;
			}
			
			if (n.left != null && !visited[n.left.val]) {
				s.push(n.left);
				visited[n.left.val] = true;
			}
		}
		
	}

}
