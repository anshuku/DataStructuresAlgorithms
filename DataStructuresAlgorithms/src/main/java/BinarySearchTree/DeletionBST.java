package BinarySearchTree;

/*
 * To delete a node with specific key in Binary Search Tree
 * Scenarios:
 * Delete a leaf node - just make the leaf node as null.
 * Node to be deleted has one child - replace the node with the child node.
 * Node to be deleted has both children - delete the node such that the resulting tree follow BST property:
 * For this find an inorder successor(or predecessor) for the node. Copy the contents of successor to the node being deleted
 * And then delete the inorder successor.
 * For deleting a node in Binary Search Tree, follow steps - 
 * Need to figure out what will be the replacement of the node to be deleted.
 * There should be minimal disruption to existing tree structure
 * The replacement is taken from deleted node's left or right subtree
 * If taken from the left subtree, take the largest value
 * If taken from the right subtree, take the smallest value
 * 
 * Inorder successor(or predecessor) of node is taken because it'll always be greater than all the elements in left subtree 
 * of the node and it's always be smaller than all other nodes in the right subtree of the node. This preserves BST property.
 */
public class DeletionBST {

	static class Node {
		int val;
		Node left, right;

		Node(int val) {
			this.val = val;
		}
	}

	public static void main(String[] args) {
		

		// Creating the following BST
		//      10
	    //     /  \
	    //    5    15
	    //     \   / \
	    //      8 12 18
		//     / \
		//    6   9

		Node root = new Node(10);
		root.left = new Node(5);
//		root.left.left = new Node(2);
		root.left.right = new Node(8);
		root.left.right.left = new Node(6);
		root.left.right.right = new Node(9);
		root.right = new Node(15);
		root.right.left = new Node(12);
		root.right.right = new Node(18);
//		Node root = new Node(1);
//		root.right = new Node(2);

		inOrder(root);
		System.out.println();
		
		int key = 8;

		root = deleteRecursive(root, key);

		inOrder(root);
		System.out.println();
		
		key = 10;

		root = deleteIterative(root, key);

		inOrder(root);
		System.out.println();
		
		key = 15;

		root = deleteRecursiveOptimized(root, key);

		inOrder(root);
	}

	// Below method optimizes the case when the node to be deleted has both
	// left and right children which needed two traversals in right subtree.
	// While finding inorder successor, keep track of it's parent as well.
	// With help of parent, one can delete the node without recursive call.
	// Time Complexity: O(h) for h comparison where h is height of tree.
	// Space Complexity: O(h) for recursion call stack
	private static Node deleteRecursiveOptimized(Node node, int key) {
		// Base case
		if (node == null) {
			return node;
		}

		// If key to be deleted is smaller than the node's key
		// The key might be lying in the left subtree
		if (key < node.val) {
			node.left = deleteRecursiveOptimized(node.left, key);
			// Mandatory to return if there is no else block for key == node.val
			return node;
		}
		// If key to be deleted is greater than the node's key
		// The key might be lying in the right subtree
		else if (key > node.val) {
			node.right = deleteRecursiveOptimized(node.right, key);
			return node;
		}

		// If key to be deleted is same as the node's key
		// Node with at most one child
		if (node.left == null) {
			return node.right;
		} else if (node.right == null) {
			return node.left;
		}

		// Node with two child
		// Find the inorder successor and successor's parent
		Node parent = node;
		Node succ = node.right;
		while (succ.left != null) {
			parent = succ;
			succ = succ.left;
		}
		// Delete the inorder successor
		if (parent.left == succ) {
			parent.left = succ.right;
		} else {
			parent.right = succ.right;
		}
		// Replace node's value with inorder successor's value
		node.val = succ.val;

		return node;
	}

	// Time Complexity: O(h) for h comparison where h is height of tree.
	// Space Complexity: O(1)
	private static Node deleteIterative(Node node, int key) {
		if (node == null) {
			return node;
		}
		Node curr = node;
		Node parent = null; // Parent is initially null, cautious
		// Check if key is present in BST
		// Parent is the parent of the node containing key to be deleted
		while (curr != null && curr.val != key) {
			parent = curr;
			if (key < curr.val) {
				curr = curr.left;
			} else {
				curr = curr.right;
			}
		}
		// Key not present
		if (curr == null) {
			return node;
		}
		// Curr has the key
		// If node to be deleted has at most one child
		if (curr.left == null || curr.right == null) {
			Node newNode = null;

			// If left child(as well as right child) does not exist
			if (curr.left == null) {
				newNode = curr.right;
			} else { // If only right child does not exist
				newNode = curr.left;
			}

			// If the node to be deleted is the root
			if (parent == null) {
				return newNode;
			}

			// If current node to be deleted is parent's left or right child
			// Replace the node with newNode
			if (parent.left == curr) {
				parent.left = newNode;
			} else {
				parent.right = newNode;
			}
		} else { // Node to be deleted has 2 children

			// p stores the parent of node to be deleted
			Node p = null;
			// Inorder successor in right subtree
			Node succ = curr.right;
			while (succ.left != null) {
				p = succ;
				succ = succ.left;
			}
			// If node to be deleted is not immediate parent
			if (p != null) {
				p.left = succ.right;
			} else { // If node to be deleted is the immediate parent
				curr.right = succ.right;
			}
			// Replace the node's key with successor's key
			curr.val = succ.val;

		}
		return node;
	}

	// It deletes the node with the given key and returns the modified root
	// Time Complexity: O(h) for h comparison where h is height of tree.
	// Space Complexity: O(h) for recursion call stack
	private static Node deleteRecursive(Node node, int key) {

		// Base case for recursion
		if (node == null) {
			return node;
		}

		// If key to be searched is in a subtree.
		if (key < node.val) {
			node.left = deleteRecursive(node.left, key);
		} else if (key > node.val) {
			node.right = deleteRecursive(node.right, key);
		} else {
			// If node's val matches with the given key

			// When root has no children or only right children
			if (node.left == null) {
				return node.right;
			}

			// When root has only left children
			if (node.right == null) {
				return node.left;
			}

			// When both children are present
			Node successor = getSuccessor(node);
			node.val = successor.val;
			node.right = deleteRecursive(node.right, successor.val);
		}

		return node;
	}

	// Not generic inorder successor function.
	// It works when right child is not empty, a case we need for delete in BST.
	// Time complexity: O(n) where n is number of nodes
	private static Node getSuccessor(Node curr) {
		curr = curr.right;
		while (curr != null && curr.left != null) {
			curr = curr.left;
		}
		return curr;
	}

	// Give the node's value in non-decreasing order
	// The left child is visited first, then the root, and then the right child.
	private static void inOrder(Node node) {

		if (node != null) {
			inOrder(node.left);
			System.out.print(node.val + " ");
			inOrder(node.right);
		}
	}

}
