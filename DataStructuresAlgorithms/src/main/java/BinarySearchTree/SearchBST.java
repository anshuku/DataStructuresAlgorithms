package BinarySearchTree;

/* 
 * Binary Search Tree is a data structure used to organize and store data in a sorted manner.
 * Each node has at most 2 children - left child and right child. 
 * Left child has value less than parent node and right child contains value greater than the parent node.
 * The hierarchical structure allows for efficient searching, insertion and deletion.
 * 
 * There must be no duplicate nodes but there can be same values with a different handling approach.
 * 
 * Consider BST as a sorted array.
 * 
 * A BST may not be a Full(2^n - 1 nodes where n is level) or Complete Binary tree(like Heap).
 */
public class SearchBST {

	static class Node {
		int key;
		Node left, right;

		public Node(int item) {
			this.key = item;
			left = right = null;
		}
	}

	public static void main(String[] args) {

		// Creating the following BST
        //      50
        //     /  \
        //    30   70
        //   / \   / \
        //  20 40 60 80
		
		Node root = new Node(50);
		root.left = new Node(30);
		root.right = new Node(70);
		root.left.left = new Node(20);
		root.left.right = new Node(40);
		root.right.left = new Node(60);
		root.right.right = new Node(80);

		Node node1 = searchRecursive(root, 80);
		Node node2 = searchRecursive(root, 15);

		System.out.println("The value of the node1 via recursion is " + node1.key);
		System.out.println("The value of the node2 via recursion is null - " + (node2 == null));

		Node node3 = search(root, 80);
		Node node4 = search(root, 15);

		System.out.println("The value of the node3 is " + node3.key);
		System.out.println("The value of the node4 is null - " + (node4 == null));

	}

	// Searches for a key in the BST via an iterative approach.
	// Time complexity: O(h) where h is the height of the BST.
	// Space complexity: O(1) as constant extra space is needed.
	private static Node search(Node node, int key) {

		// Traverse the node until we reach null
		while (node != null) {
			if (key == node.key) {
				return node; // Key found
			} else if (key < node.key) {// Left subtree is the new tree
				node = node.left;
			} else {// Right subtree is the new tree
				node = node.right;
			}
		}
		return null;// Key not present
	}

	// Searches for a key in the BST via a recursive approach.
	// Time complexity: O(h) where h is the height of the BST.
	// Space complexity: O(h) where h is the height of BST due to space needed for
	// recursion stack.
	private static Node searchRecursive(Node node, int key) {
		// Base case: when node is null or key is present in the root
		if (node == null || node.key == key) {
			return node;
		}

		// When key is smaller than the root's key
		if (key < node.key) {
			return searchRecursive(node.left, key);
		} // When key is greater than the root's key
		else {
			return searchRecursive(node.right, key);
		}
	}
	


	// Creating the following BST
	//      10
    //     /  \
    //    5    15
    //         / \
    //        12 18

}
